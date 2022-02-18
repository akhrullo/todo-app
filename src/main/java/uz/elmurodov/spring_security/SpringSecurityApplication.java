package uz.elmurodov.spring_security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import uz.elmurodov.spring_security.entity.auth.AuthRole;
import uz.elmurodov.spring_security.entity.auth.AuthUser;
import uz.elmurodov.spring_security.repository.auth.AuthRoleRepository;
import uz.elmurodov.spring_security.repository.auth.AuthUserRepository;

import java.util.UUID;

@SpringBootApplication
public class SpringSecurityApplication /*implements CommandLineRunner */{


    private final AuthUserRepository authUserRepository;
    private final AuthRoleRepository authRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public SpringSecurityApplication(AuthUserRepository authUserRepository, AuthRoleRepository authRoleRepository, PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.authRoleRepository = authRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Transactional(timeout = 10)
    public void run(String... args) throws Exception {

        AuthUser admin = new AuthUser();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        AuthRole adminRole = authRoleRepository.findAuthRoleByCode("ADMIN").get();
        admin.setRole(adminRole);
        admin.setCode(UUID.randomUUID());

        authUserRepository.save(admin);


        AuthUser manager = new AuthUser();
        manager.setUsername("manager");
        manager.setPassword(passwordEncoder.encode("manager123"));
        manager.setCode(UUID.randomUUID());
        AuthRole managerRole = authRoleRepository.findAuthRoleByCode("MANAGER").get();
        manager.setRole(managerRole);
        authUserRepository.save(manager);
//        authUserRepository.saveAll(List.of(user1, user2));

    }
}
