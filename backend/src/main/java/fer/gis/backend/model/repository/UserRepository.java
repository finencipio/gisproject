package fer.gis.backend.model.repository;

import fer.gis.backend.model.enitity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByUserId(Long userId);
    User findByEmail(String email);
    void deleteUserByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}

