package fer.gis.backend.service.registration;

import fer.gis.backend.model.enitity.user.User;
import fer.gis.backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationListener implements
        ApplicationListener<OnRegistrationAppEvent> {

    private final UserService userService;
    private final JavaMailSender mailSender;
 
    @Override
    public void onApplicationEvent(OnRegistrationAppEvent event) {
        this.requestRegistrationConfirmation(event);
    }
 
    private void requestRegistrationConfirmation(OnRegistrationAppEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);
         
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "/users/registrationConfirm?token=" + token;

        String message = "User verification procedure: \n\n";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\n" + "http://" + confirmationUrl);
        mailSender.send(email);
    }
}