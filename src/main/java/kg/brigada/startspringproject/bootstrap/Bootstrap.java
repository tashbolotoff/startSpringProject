package kg.brigada.startspringproject.bootstrap;

import kg.brigada.startspringproject.entities.Permission;
import kg.brigada.startspringproject.entities.PermissionCategory;
import kg.brigada.startspringproject.entities.User;
import kg.brigada.startspringproject.entities.UserRole;
import kg.brigada.startspringproject.repos.PermissionCategoryRepo;
import kg.brigada.startspringproject.repos.PermissionRepo;
import kg.brigada.startspringproject.repos.UserRepo;
import kg.brigada.startspringproject.repos.UserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Bootstrap implements CommandLineRunner {
    @Autowired
    private PermissionRepo permissionRepo;

    @Autowired
    private PermissionCategoryRepo permissionCategoryRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        createPermissionsAndUsers();
    }

    private void createPermissionsAndUsers() {
        /**
         * Permissions
         **/
        Permission permissionAdmin = Permission.builder()
                .name("SUPER_ADMIN")
                .build();
        permissionRepo.save(permissionAdmin);
        PermissionCategory permissionCategoryUser = PermissionCategory.builder()
                .name("Users")
                .nameRu("Пользователи")
                .build();
        permissionCategoryRepo.save(permissionCategoryUser);
        Permission permissionUserCreate = Permission.builder()
                .name("USER_CREATE")
                .nameRu("Добавление пользователей")
                .permissionCategory(permissionCategoryUser)
                .build();
        permissionRepo.save(permissionUserCreate);

        Permission permissionUserUpdate = Permission.builder()
                .name("USER_UPDATE")
                .nameRu("Изменение пользователей")
                .permissionCategory(permissionCategoryUser)
                .build();
        permissionRepo.save(permissionUserUpdate);

        Permission permissionUserRead = Permission.builder()
                .name("USER_READ")
                .nameRu("Просмотр пользователей")
                .permissionCategory(permissionCategoryUser)
                .build();
        permissionRepo.save(permissionUserRead);


        /**
         * User Roles
         **/
        UserRole userRoleAdmin = UserRole.builder()
                .name("ROLE_ADMIN")
                .build();
        ArrayList<Permission> permissionArrayList = new ArrayList<>();
        permissionArrayList.add(permissionAdmin);
        userRoleAdmin.setPermissions(permissionArrayList);
        if (userRoleAdmin.getPermissions() != null) {
            permissionArrayList.addAll(userRoleAdmin.getPermissions());
        }
        userRoleRepo.save(userRoleAdmin);

        /**
         * Users
         **/
        User userAdmin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("123"))
                .isActive(true)
                .userRole(userRoleAdmin)
                .email("brigada@brigada.kg")
                .phone("+996(555)55-55-55")
                .firstName("Админов")
                .lastName("Админ")
                .middleName("Админович")
                .build();
        userRepo.save(userAdmin);
    }
}
