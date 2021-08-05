package kg.brigada.startspringproject.configs;

import io.sentry.Sentry;
import kg.brigada.startspringproject.entities.User;
import kg.brigada.startspringproject.exceptions.RecordNotFoundException;
import kg.brigada.startspringproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("principal") // for access the method below from controller (@PreAuthorize)
public class UserAccessSecurity {
    @Autowired
    private UserService userService;

    public boolean hasUserId(Authentication authentication, Long userId) { //method for checking id of principal with #userId
        try {
            User user = userService.getById(userId);
            if(user.getUsername().equals(authentication.getName())) {
                return true;
            }
        } catch (RecordNotFoundException e) {
            Sentry.captureException(e);
            e.printStackTrace();
        }
        return false;
    }
}
