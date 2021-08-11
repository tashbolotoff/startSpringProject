package kg.brigada.startspringproject.services.impl;

import kg.brigada.startspringproject.entities.User;
import kg.brigada.startspringproject.exceptions.RecordNotFoundException;
import kg.brigada.startspringproject.repos.UserRepo;
import kg.brigada.startspringproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        user.setIsActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User getById(Long uuid) {
        return userRepo.getById(uuid);
    }

    @Override
    public User update(User user) {
        return userRepo.findById(user.getId())
                .map(newUser -> {
                    if (user.getUserRole() != null)
                        newUser.setUserRole(user.getUserRole());
                    if (user.getPassword()!=null || user.getPassword().length() > 1)
                        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
                    if (user.getLastName() != null || user.getLastName().length() > 1)
                        newUser.setLastName(user.getLastName());
                    if (user.getFirstName() != null || user.getFirstName().length() > 1)
                        newUser.setFirstName(user.getFirstName());
                    if (user.getMiddleName() != null || user.getMiddleName().length() > 1)
                        newUser.setMiddleName(user.getMiddleName());
                    if (user.getEmail() != null || user.getEmail().length() > 1)
                        newUser.setEmail(user.getEmail());
                    if (user.getPhone() != null || user.getPhone().length() > 1)
                        newUser.setPhone(user.getPhone());

                    return userRepo.save(newUser);
                }).orElseThrow(() -> new RecordNotFoundException("Not found with id: "+user.getId()));
    }

    @Override
    public User changeActiveStatus(Long uuid) {
        User user = userRepo.getById(uuid);
        if (user.getIsActive()) {
            user.setIsActive(false);
        } else if (!user.getIsActive()) {
            user.setIsActive(true);
        }
        return userRepo.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return userRepo.getByUsername(username);
    }
}
