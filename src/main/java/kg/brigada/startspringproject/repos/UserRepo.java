package kg.brigada.startspringproject.repos;

import kg.brigada.startspringproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

    User getByUsername(String username);
}
