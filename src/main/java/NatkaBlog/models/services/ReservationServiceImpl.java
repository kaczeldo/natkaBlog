package NatkaBlog.models.services;

import NatkaBlog.data.repositories.ReservationRepository;
import NatkaBlog.models.dto.ReservationDTO;
import NatkaBlog.models.dto.mappers.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public void create(ReservationDTO reservation){
        

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
    public void edit(ReservationDTO reservationDTO) {

    }

    @Override
    public void remove(long reservationId) {

    }
}
