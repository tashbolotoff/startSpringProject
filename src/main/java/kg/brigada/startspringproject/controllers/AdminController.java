package kg.brigada.startspringproject.controllers;

import kg.brigada.startspringproject.entities.Permission;
import kg.brigada.startspringproject.entities.PermissionCategory;
import kg.brigada.startspringproject.entities.User;
import kg.brigada.startspringproject.models.Counter;
import kg.brigada.startspringproject.models.PermissionBoolModel;
import kg.brigada.startspringproject.models.PermissionModel;
import kg.brigada.startspringproject.repos.PermissionCategoryRepo;
import kg.brigada.startspringproject.services.PermissionService;
import kg.brigada.startspringproject.services.UserRoleService;
import kg.brigada.startspringproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private PermissionCategoryRepo permissionCategoryRepo;

    @PreAuthorize("isAuthenticated() and hasPermission('USER_READ', 'SUPER_ADMIN')")
    @GetMapping("/user/list")
    public String getAllUsers(Model model){
        model.addAttribute("userRoles", userRoleService.findAll());
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }
    @PreAuthorize("isAuthenticated() and hasPermission('USER_CREATE', 'SUPER_ADMIN')")
    @PostMapping("/user/add")
    public String addUser(@ModelAttribute("user") User user){
        userService.create(user);
        return "redirect:/admin/user/list";
    }
    @PreAuthorize("isAuthenticated() and hasPermission('USER_UPDATE', 'SUPER_ADMIN')")
    @PostMapping("/user/update")
    public String updateUser(@ModelAttribute("user") User user){
        userService.update(user);
        return "redirect:/admin/user/list";
    }
    @PreAuthorize("isAuthenticated() and hasPermission('USER_UPDATE', 'SUPER_ADMIN')")
    @PostMapping("/user/changeStatus")
    public String changeStatus(@ModelAttribute("changeUserId") Long id){
        userService.changeActiveStatus(id);
        return "redirect:/admin/user/list";
    }
    @PreAuthorize("isAuthenticated() and hasPermission('SUPER_ADMIN')")
    @GetMapping("/userRole/permissions")
    public String getPermissionList(Model model) {
        model.addAttribute("roles", userRoleService.findAll());
        ArrayList<PermissionBoolModel> permissionBoolModels = new ArrayList<>();
        for (PermissionCategory permissionCategory : permissionCategoryRepo.findAll()) {
            for (Permission permission : permissionCategory.getPermissions()) {
                PermissionBoolModel permissionBoolModel = new PermissionBoolModel();
                permissionBoolModel.setPermissionBool(false);
                permissionBoolModel.setPermId(permission.getId());
                permissionBoolModels.add(permissionBoolModel);
            }
        }
        PermissionModel permissionModel = new PermissionModel();
        permissionModel.setPermissionBools(permissionBoolModels);

        model.addAttribute("permissionModel", permissionModel);
        model.addAttribute("counter", new Counter());
        model.addAttribute("permissionCategories", permissionCategoryRepo.findAll());
        return "permission/form";
    }
    @PreAuthorize("isAuthenticated() and hasPermission('SUPER_ADMIN')")
    @PostMapping("/userRole/permission/add")
    public String updatePermissions(@ModelAttribute("PermissionModel") PermissionModel permissionModel) {
        permissionService.grantSelectedPrivileges(permissionModel);
        return "redirect:/admin/userRole/permissions";
    }


}
