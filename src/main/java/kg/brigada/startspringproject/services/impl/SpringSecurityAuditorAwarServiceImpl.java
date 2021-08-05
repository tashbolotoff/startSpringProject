package kg.brigada.startspringproject.services.impl;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class SpringSecurityAuditorAwarServiceImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.ofNullable("admin");
        }
        try{
            return Optional.ofNullable(((UserDetails) authentication.getPrincipal()).getUsername());
        }catch (ClassCastException classCastException){
            return Optional.ofNullable("admin");
        }
    }
}
