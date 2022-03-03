package uz.iDev.spring_security.config.security;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.iDev.spring_security.entity.auth.AuthUser;
import uz.iDev.spring_security.repository.auth.AuthUserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final AuthUserRepository authUserRepository;

    public UserDetailsService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = authUserRepository.findAuthUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDetails(user);
    }
}
