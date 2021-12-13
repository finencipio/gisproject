package fer.gis.backend.model.repository;

import fer.gis.backend.model.enitity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User findByUserId(Long userId);
    User findByEmail(String email);
    void deleteUserByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}

