package fer.gis.backend.model.repository.user;

import fer.gis.backend.model.enitity.user.User;
import fer.gis.backend.model.enitity.user.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);
    VerificationToken findByUser(User user);
}
