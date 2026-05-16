package NatkaBlog.data.repositories;

import NatkaBlog.data.entities.ReservationEntity;
import NatkaBlog.models.enums.ReservationState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    long countByClassEntity_ClassId(long classId);
    boolean existsByUser_UserIdAndClassEntity_ClassId(long userId, long classId);
    long countByClassEntity_ClassIdAndState(
            long classId,
            ReservationState state
    );
    List<ReservationEntity> findByUser_UserId(long userId);

}
