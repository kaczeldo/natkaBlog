package NatkaBlog.models.services;

import NatkaBlog.models.dto.ContactDTO;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }


    @Override
    public void sendEmail(ContactDTO contactEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(contactEmail.getEmail());
        message.setSubject(contactEmail.getSubject());
        message.setText("From: " + contactEmail.getEmail() + "\n\n" + contactEmail.getMessage());
        mailSender.send(message);
    }
}
