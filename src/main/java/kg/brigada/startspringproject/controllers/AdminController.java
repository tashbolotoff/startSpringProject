package kg.brigada.startspringproject.controllers;

import kg.brigada.startspringproject.entities.User;
import kg.brigada.startspringproject.services.UserRoleService;
import kg.brigada.startspringproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/user/list")
    public String getAllUsers(Model model){
        model.addAttribute("userRoles", userRoleService.findAll());
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String getUsetForm(){
        return "user/form";
    }

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute("user") User user){
        userService.create(user);
        return "redirect:/admin/user/list";
    }
    @PostMapping("/user/update")
    public String updateUser(@ModelAttribute("user") User user){
        userService.update(user);
        return "redirect:/admin/user/list";
    }
    @PostMapping("/user/changeStatus")
    public String changeStatus(@ModelAttribute("changeUserId") Long id){
        userService.changeActiveStatus(id);
        return "redirect:/admin/user/list";
    }
}
