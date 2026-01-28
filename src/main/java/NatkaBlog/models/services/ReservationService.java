package NatkaBlog.models.services;

import NatkaBlog.models.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {
    void create(ReservationDTO reservationDTO);

    List<ReservationDTO> getByUser(long userId);

    List<ReservationDTO> getByClass(long classId);

    void edit(ReservationDTO reservationDTO);
    void remove(long reservationId);
}
