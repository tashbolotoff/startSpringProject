package kg.brigada.startspringproject.services.impl;

import kg.brigada.startspringproject.entities.User;
import kg.brigada.startspringproject.exceptions.RecordNotFoundException;
import kg.brigada.startspringproject.repos.UserRepo;
import kg.brigada.startspringproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User create(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User getById(UUID uuid) {
        return userRepo.getById(uuid);
    }

    @Override
    public User update(User user) {
        return userRepo.save(user);
    }

    @Override
    public User changeActiveStatus(UUID uuid, Boolean active) {
        return userRepo.findById(uuid).map(newUser -> {
            newUser.setIsActive(active);
            return userRepo.save(newUser);
        }).orElseThrow(() -> new RecordNotFoundException("Record not found with id "+uuid));
    }

    @Override
    public User getByUsername(String username) {
        return userRepo.getByUsername(username);
    }
}
