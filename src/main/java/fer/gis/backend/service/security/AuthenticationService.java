package fer.gis.backend.service.security;

import com.auth0.jwt.JWT;
import fer.gis.backend.model.enitity.user.User;
import fer.gis.backend.model.repository.user.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static fer.gis.backend.security.SecurityConstants.EXPIRATION_TIME;
import static fer.gis.backend.security.SecurityConstants.SECRET;

@Component
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User authenticate(@NonNull String username, @NonNull String password) {
        User user = userRepository.findByUsername(username);
        if(user != null
                && user.getConfirmed()
                && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            throw new IllegalArgumentException("wrong username and/or password!");
        }
    }

    public String createJWTToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(Date.from(Instant.now().plusSeconds(EXPIRATION_TIME)))
                .sign(HMAC512(SECRET.getBytes()));
    }
}
