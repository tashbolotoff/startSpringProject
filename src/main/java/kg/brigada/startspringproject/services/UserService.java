package kg.brigada.startspringproject.services;

import kg.brigada.startspringproject.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User create(User user);

    List<User> findAll();

    User getById(UUID uuid);

    User update(User user);

    User changeActiveStatus(UUID uuid, Boolean active);

    User getByUsername(String username);
}
