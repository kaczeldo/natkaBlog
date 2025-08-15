package NatkaBlog.controllers;

import NatkaBlog.models.dto.ContactDTO;
import NatkaBlog.models.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {

    @Autowired
    private EmailService emailService;

    @GetMapping("contact")
    public String renderContact(@ModelAttribute ContactDTO contactDTO) {
        return "pages/home/contact";
    }

    @PostMapping("contact")
    public String sendContactMessage(@ModelAttribute ContactDTO contactEmail,
        RedirectAttributes redirectAttributes) {
        emailService.sendEmail(contactEmail);
        redirectAttributes.addFlashAttribute("success", "The email was sent successfully!");
        return "redirect:/contact";
    }

}
