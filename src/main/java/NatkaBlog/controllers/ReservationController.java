package NatkaBlog.controllers;

import NatkaBlog.models.dto.ReservationDTO;
import NatkaBlog.models.dto.mappers.ReservationMapper;
import NatkaBlog.models.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("create")
    public String renderCreateForm(
            @ModelAttribute ReservationDTO reservationDTO
            ) {
        return "pages/reservation/create";
    }

    @PostMapping("create")
    public String createReservation(){

    }

    @GetMapping("my")
    public String renderMyReservationsPage(){

    }

    @PostMapping("{reservationId}/cancel")
    public String cancelReservation(){

    }


}
