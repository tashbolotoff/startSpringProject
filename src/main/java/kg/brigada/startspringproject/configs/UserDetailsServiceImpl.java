package kg.brigada.startspringproject.configs;

import kg.brigada.startspringproject.entities.Permission;
import kg.brigada.startspringproject.entities.User;
import kg.brigada.startspringproject.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.getByUsername(username);
        if (user == null || !user.getIsActive()) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(user));
    }


    private List<GrantedAuthority> mapToGrantedAuthorities(User user) {
        SimpleGrantedAuthority auth = new SimpleGrantedAuthority(user.getUserRole().getName());
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Permission permission : user.getUserRole().getPermissions()){
            authorities.add(new SimpleGrantedAuthority(permission.getName()));
        }
        authorities.add(auth);
        return authorities;
    }

}
