package kg.brigada.startspringproject.services;

import kg.brigada.startspringproject.entities.UserRole;

import java.util.List;
import java.util.UUID;

public interface UserRoleService {

    UserRole create(UserRole userRole);

    List<UserRole> findAll();

    UserRole update(UserRole userRole);

    UserRole getById(UUID id);
}
