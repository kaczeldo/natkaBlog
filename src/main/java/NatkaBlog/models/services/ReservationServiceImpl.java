package NatkaBlog.models.services;

import NatkaBlog.data.entities.ClassEntity;
import NatkaBlog.data.entities.ReservationEntity;
import NatkaBlog.data.entities.UserEntity;
import NatkaBlog.data.repositories.ClassRepository;
import NatkaBlog.data.repositories.ReservationRepository;
import NatkaBlog.data.repositories.UserRepository;
import NatkaBlog.models.dto.ReservationDTO;
import NatkaBlog.models.dto.mappers.ReservationMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
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
        /*
        Logic:
        1. get the reservation entity
        2. get the class enitity attached to it
        3. check capacity of the class
        4. if there is free spot, make the reservation
         */
        Long classId = dto.getClassId();
        ClassEntity classEntity = classRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("Class not found"));
        long reserved = reservationRepository.countByClassEntity_ClassId(classId);
        int capacity = classEntity.getCapacity();
        if ((capacity - reserved) <= 0){
            throw new IllegalArgumentException("The capacity is full");
        }
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setClassEntity(classEntity);
        UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        reservationEntity.setUser(user);
        reservationEntity.setCreatedAt(Instant.now());
        reservationRepository.save(reservationEntity);
    }

    @Override
    public List<ReservationDTO> getByUser(long userId) {
        return List.of();
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

    }
}
