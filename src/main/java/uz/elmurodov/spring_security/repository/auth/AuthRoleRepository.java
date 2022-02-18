package uz.elmurodov.spring_security.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.elmurodov.spring_security.entity.auth.AuthRole;

import java.util.Optional;

public interface AuthRoleRepository extends JpaRepository<AuthRole, Long> {
    Optional<AuthRole> findAuthRoleByCode(String code);
}
