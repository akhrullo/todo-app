package uz.iDev.spring_security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.iDev.spring_security.dto.auth.AuthUserCreateDto;
import uz.iDev.spring_security.entity.auth.AuthUser;
import uz.iDev.spring_security.repository.auth.AuthRoleRepository;
import uz.iDev.spring_security.repository.auth.AuthUserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthUserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;


    private final AuthUserRepository userRepository;


    private final AuthRoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthUserService(AuthUserRepository userRepository, AuthRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = userRepository.findAuthUserByUsername(username).get();

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return (UserDetails) user;
    }

    public AuthUser findUserById(Long userId) {
        Optional<AuthUser> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new AuthUser());
    }

    public List<AuthUser> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(AuthUserCreateDto userDto) {
//        String tUsername = userRepository.findAuthUserByUsername(userDto.getUsername()).get().getUsername();
//
//        if (userDto.getUsername().equals(tUsername)) {
//            return false;
//        }

        AuthUser newUser = new AuthUser();

        newUser.setUsername(userDto.getUsername());


        newUser.setCode(UUID.randomUUID());
        newUser.setRole(roleRepository.findAuthRoleByCode("MANAGER").get());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(newUser);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<AuthUser> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM AuthUser u WHERE u.id > :paramId", AuthUser.class)
                .setParameter("paramId", idMin).getResultList();
    }
}