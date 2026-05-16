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
            throw new IllegalArgumentException("Cannot reserve past class");
        }

        if (classEntity.getClassTo().isAfter((LocalDateTime.now()))) {
            throw new IllegalArgumentException("Cannot reserve completed class");
        }

        // validace jestli rezervace jiz existuje
        boolean alreadyExists = reservationRepository.existsByUser_UserIdAndClassEntity_ClassId(userId, classId);
        if (alreadyExists) {
            throw new IllegalArgumentException("User already has reservation for this class");
        }

        long reserved = reservationRepository.countByClassEntity_ClassIdAndState(
                classId, ReservationState.ACTIVE
        );
        int capacity = classEntity.getCapacity();
        if ((capacity - reserved) <= 0){
            throw new IllegalArgumentException("The capacity is full");
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
                findByUser_UserId(userId).
                stream().
                map(reservationMapper::toDTO).
                toList();
    }

    @Override
    public List<ReservationDTO> getByClass(long classId) {
        return List.of();
    }

    @Override
    public List<ReservationDTO> getByDate(LocalDate date) {
        return List.of();
    }

    @Override
    public ReservationDTO getById(long reservationId) {
        return null;
    }

    @Override
    public void edit(ReservationDTO reservationDTO) {

    }

    @Override
    public void remove(long reservationId) {
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with id: " + reservationId));

        reservationRepository.delete(reservation);
    }
}
