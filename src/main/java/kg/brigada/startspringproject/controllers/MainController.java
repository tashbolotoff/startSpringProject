package kg.brigada.startspringproject.controllers;

import kg.brigada.startspringproject.entities.User;
import kg.brigada.startspringproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@Controller
@ApiIgnore
public class MainController {

    @Autowired
    private UserService userService;

    private User currentUser;

    @GetMapping("/")
    public String getMainPage(Model model) {
        getCurrentUser();
        return "index";
    }

    @GetMapping("/success")
    public String getMainPage(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin/user/list";
        }
        return "redirect:/";
    }


    public void setUserCredentials(Model model) {
        getCurrentUser();
        Long id = currentUser.getId();
//        model.addAttribute("name", employeeService.getEmployeeByUserId(id) != null ? employeeService.getEmployeeByUserId(id).getName() : "Имя");
//        model.addAttribute("surname", employeeService.getEmployeeByUserId(id) != null ? employeeService.getEmployeeByUserId(id).getSurname() : "Фамилия");
//        model.addAttribute("position", employeeService.getEmployeeByUserId(id) != null ? employeeService.getEmployeeByUserId(id).getPosition().getName() : "Должность");
    }

    private void getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() != "anonymousUser") {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            currentUser = userService.getByUsername(userDetails.getUsername());
        }
    }
}
