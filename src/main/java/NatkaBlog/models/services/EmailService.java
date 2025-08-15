package NatkaBlog.models.services;

import NatkaBlog.models.dto.ContactDTO;
import org.springframework.mail.javamail.JavaMailSender;

public interface EmailService {
    public void sendEmail(ContactDTO contactEmail);
}
