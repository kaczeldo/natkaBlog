package NatkaBlog.data.repositories;

import NatkaBlog.data.entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    long countByClassEntity_ClassId(long classId);

}
