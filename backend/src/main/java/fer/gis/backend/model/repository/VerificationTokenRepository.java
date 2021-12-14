package fer.gis.backend.model.repository;

import fer.gis.backend.model.enitity.User;
import fer.gis.backend.model.enitity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);
    VerificationToken findByUser(User user);
}
