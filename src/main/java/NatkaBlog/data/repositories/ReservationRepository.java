package NatkaBlog.data.repositories;

import NatkaBlog.data.entities.ReservationEntity;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {

}
