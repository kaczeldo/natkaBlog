package NatkaBlog.models.services;

import NatkaBlog.data.entities.ClassEntity;
import NatkaBlog.data.entities.ReservationEntity;
import NatkaBlog.data.entities.UserEntity;
import NatkaBlog.data.repositories.ClassRepository;
import NatkaBlog.data.repositories.ReservationRepository;
import NatkaBlog.data.repositories.UserRepository;
import NatkaBlog.models.dto.ReservationDTO;
import NatkaBlog.models.dto.mappers.ReservationMapper;
import NatkaBlog.models.enums.ReservationState;
import NatkaBlog.models.exceptions.ClassCapacityExceededException;
import NatkaBlog.models.exceptions.DuplicateReservationException;
import NatkaBlog.models.exceptions.PastClassReservationException;
import NatkaBlog.models.exceptions.ReservationNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public void create(ReservationDTO dto){

        long classId = dto.getClassId();
        long userId = dto.getUserId();

        ClassEntity classEntity = classRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("Class not found"));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // validace zda rezervace jiz nevyprsela
        if (classEntity.getClassFrom().isBefore(LocalDateTime.now())) {
            throw new PastClassReservationException("Cannot reserve past class");
        }

        // validace jestli rezervace jiz existuje
        boolean alreadyExists = reservationRepository.existsByUser_UserIdAndClassEntity_ClassId(userId, classId);
        if (alreadyExists) {
            throw new DuplicateReservationException("User already has reservation for this class");
        }

        long reserved = reservationRepository.countByClassEntity_ClassIdAndState(
                classId, ReservationState.ACTIVE
        );
        int capacity = classEntity.getCapacity();
        if ((capacity - reserved) <= 0){
            throw new ClassCapacityExceededException("The capacity is full");
        }
        ReservationEntity reservationEntity = new ReservationEntity();

        reservationEntity.setClassEntity(classEntity);
        reservationEntity.setUser(user);
        reservationEntity.setCreatedAt(Instant.now());
        reservationEntity.setState(ReservationState.ACTIVE);

        user.getReservations().add(reservationEntity);

        reservationRepository.save(reservationEntity);
    }

    /**
     * Tato metoda vraci rezervovane lekce danym uzivatelem.
     * Napr. uzivatel kazczeldo ma tyto rezervace: a vrati vsechny rezervace
     * @param userId
     * @return {List<ReservationEntity>}
     */
    @Override
    public List<ReservationDTO> getByUser(long userId) {
        return reservationRepository.
                findByUser_UserIdAndStateOrderByClassEntity_ClassFromAsc(userId, ReservationState.ACTIVE).
                stream().
                map(reservationMapper::toDTO).
                toList();
    }

    /**
     * Tato metoda vraci vsechny rezervace na danou hodinu/lekci
     * @param classId
     * @return {List<ReservationEntity>} seznam (List) rezervaci pro dane class Id
     */
    @Override
    public List<ReservationDTO> getByClass(long classId) {
        return reservationRepository
                .findByClassEntity_ClassIdAndStateOrderByCreatedAtAsc(classId, ReservationState.ACTIVE)
                .stream()
                .map(reservationMapper::toDTO)
                .toList();
    }

    /**
     * Tato metoda vraci vsechny rezervace na hodinu/lekci v dane datum.
     * Takze pokud existuje nejaka rezervace na lekci, ktera se odehrava v dane datum,
     * vrati ji to.
     * @param date
     * @return {List<ReservationEntity>}
     */
    @Override
    public List<ReservationDTO> getByDate(LocalDate date) {

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        return  reservationRepository
                .findByClassEntity_ClassFromBetweenAndStateOrderByClassEntity_ClassFromAsc(start, end, ReservationState.ACTIVE)
                .stream()
                .map(reservationMapper::toDTO)
                .toList();
    }

    /**
     * Vraci rezervaci podle id.
     * @param reservationId
     * @return
     */
    @Override
    public ReservationDTO getById(long reservationId) {
        return reservationMapper.toDTO(reservationRepository
                .findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException("reservation not found")));
    }

    /**
     * Vraci vsechny budouci rezervace od daneho data, vcetne.
     * @param date
     * @return
     */
    @Override
    public List<ReservationDTO> getFromDate(LocalDate date){
        return reservationRepository
                .findByClassEntity_ClassFromGreaterThanEqualAndStateOrderByClassEntity_ClassFromAsc(date.atStartOfDay(),
                        ReservationState.ACTIVE)
                .stream()
                .map(reservationMapper::toDTO)
                .toList();
    }

    @Override
    public void remove(long reservationId) {
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with id: " + reservationId));

        reservation.setState(ReservationState.CANCELLED);
    }
}
