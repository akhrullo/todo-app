package uz.elmurodov.spring_security.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.elmurodov.spring_security.entity.auth.AuthUser;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findAuthUserByUsername(String username);

}
