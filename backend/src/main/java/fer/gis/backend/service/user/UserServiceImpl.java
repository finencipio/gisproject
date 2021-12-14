package fer.gis.backend.service.user;

import fer.gis.backend.model.dto.user.UserRegistrationModel;
import fer.gis.backend.model.enitity.User;
import fer.gis.backend.model.enitity.VerificationToken;
import fer.gis.backend.model.repository.UserRepository;
import fer.gis.backend.model.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                                    Collections.emptyList());
    }

    @Override
    public void registerNewUser(UserRegistrationModel userRegistrationModel) {
        if (userRepository.existsByEmail(userRegistrationModel.getEmail())) {
            throw new IllegalStateException("User with that email address already exists");
        }
        if (userRepository.existsByUsername(userRegistrationModel.getUsername())) {
            throw new IllegalStateException("User with that username already exists");
        }

        User user = User.builder()
                .username(userRegistrationModel.getUsername())
                .password(passwordEncoder.encode(userRegistrationModel.getPassword()))
                .email(userRegistrationModel.getEmail())
                .confirmed(true)
                .firstname(userRegistrationModel.getFirstname())
                .lastname(userRegistrationModel.getLastname())
                .build();

        userRepository.save(user);
        /*
        smtp connection for gmail cannot be established
        try {
            eventPublisher.publishEvent(new OnRegistrationAppEvent("localhost:8080/mediateka", userRepository.findByUsername(user.getUsername())));
        }catch (Exception exc){
            deleteUserAccount(user.getUsername());
            throw new RuntimeException(exc);
        }
         */

    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(token, user, Date.from(Instant.now().plusSeconds(86400)));
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public void confirmRegistration(String token) {
        VerificationToken verificationToken = getVerificationToken(token);
        if (verificationToken == null) {
            throw new IllegalArgumentException("Token is invalid!");
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            throw new IllegalStateException("Token is expired!");
        }

        user.setConfirmed(true);
        userRepository.save(user);
    }

    @Override
    public void deleteUserAccount(String username) {
        VerificationToken verificationToken = verificationTokenRepository.findByUser(userRepository.findByUsername(username));
        if (verificationToken != null) {
            verificationTokenRepository.delete(verificationToken);
        }
        userRepository.deleteUserByUsername(username);
    }

    @Override
    public void persistUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Collection<User> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
            .collect(Collectors.toList());
    }

    private VerificationToken getVerificationToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }
}