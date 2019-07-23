package pollingapp.repository;

import pollingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String userName, String email);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUsername(String userName);

    Boolean existsByUsername(String userName);

    Boolean existsByEmail(String email);
}