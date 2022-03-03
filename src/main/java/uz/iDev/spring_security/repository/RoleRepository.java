package uz.iDev.spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.iDev.spring_security.entity.auth.AuthRole;

public interface RoleRepository extends JpaRepository<AuthRole, Long> {
    AuthRole getAuthRoleById(Long id);
}
