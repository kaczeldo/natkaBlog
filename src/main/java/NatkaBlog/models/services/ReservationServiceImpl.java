package NatkaBlog.models.services;

import NatkaBlog.data.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl {
    @Autowired
    private ReservationRepository reservationRepository;
}
