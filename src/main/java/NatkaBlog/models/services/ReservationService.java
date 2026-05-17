package NatkaBlog.models.services;

import NatkaBlog.models.dto.ReservationDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    void create(ReservationDTO reservationDTO);

    List<ReservationDTO> getByUser(long userId);

    List<ReservationDTO> getByClass(long classId);

    List<ReservationDTO> getByDate(LocalDate date);

    ReservationDTO getById(long reservationId);

    List<ReservationDTO> getFromDate(LocalDate date);

    void remove(long reservationId);
}
