package az.turbo.backend.configs;

import az.turbo.backend.users.application.UserService;
import az.turbo.backend.users.application.dto.UserPasswordDto;
import az.turbo.backend.users.application.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserPasswordDto userPasswordDto = userService.retrieveByEmail(username);

        if (userPasswordDto == null) {
            throw new UserNotFoundException("User not found!");
        }

        return new User(userPasswordDto.getEmail(),
                userPasswordDto.getPassword(),
                getAuthorities(userPasswordDto.getRole()));
    }

    private List<GrantedAuthority> getAuthorities(String role) {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }
}
