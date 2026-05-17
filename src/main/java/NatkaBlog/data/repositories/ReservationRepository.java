package NatkaBlog.data.repositories;

import NatkaBlog.data.entities.ReservationEntity;
import NatkaBlog.models.enums.ReservationState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    long countByClassEntity_ClassId(long classId);
    boolean existsByUser_UserIdAndClassEntity_ClassId(long userId, long classId);
    long countByClassEntity_ClassIdAndState(
            long classId,
            ReservationState state
    );

    List<ReservationEntity> findByUser_UserIdAndStateOrderByClassEntity_ClassFromAsc(long userId,
                                                                                     ReservationState state);
    List<ReservationEntity> findByClassEntity_ClassIdAndStateOrderByCreatedAtAsc(long classId, ReservationState state);

    List<ReservationEntity> findByClassEntity_ClassFromBetweenAndStateOrderByClassEntity_ClassFromAsc(
            LocalDateTime start, LocalDateTime end, ReservationState state);

    List<ReservationEntity> findByClassEntity_ClassFromGreaterThanAndState(LocalDateTime date, ReservationState state);
    List<ReservationEntity> findByClassEntity_ClassFromGreaterThanEqualAndStateOrderByClassEntity_ClassFromAsc
            (LocalDateTime date, ReservationState state);

}
