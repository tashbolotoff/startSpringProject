package kg.brigada.startspringproject.services.impl;

import kg.brigada.startspringproject.entities.UserRole;
import kg.brigada.startspringproject.repos.UserRoleRepo;
import kg.brigada.startspringproject.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Override
    public UserRole create(UserRole userRole) {
        return userRoleRepo.save(userRole);
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepo.findAll();
    }

    @Override
    public UserRole update(UserRole userRole) {
        return userRoleRepo.save(userRole);
    }

    @Override
    public UserRole getById(UUID id) {
        return userRoleRepo.getById(id);
    }
}
