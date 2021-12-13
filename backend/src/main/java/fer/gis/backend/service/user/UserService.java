package fer.gis.backend.service.user;

import fer.gis.backend.model.dto.user.UserRegistrationModel;
import fer.gis.backend.model.enitity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

public interface UserService extends UserDetailsService {

    void registerNewUser(UserRegistrationModel userRegistrationModel);

    void createVerificationToken(User user, String token);

    void confirmRegistration(String token);

    void deleteUserAccount(String username);

    void persistUser(User user);

    User findByUsername(String username);

    Collection<User> getAllUsers();
}
