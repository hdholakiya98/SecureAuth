package registerLogin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import registerLogin.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    //@Query("select b from Book b where b.username = ?1 and b.password = ?2")
    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}
