package uz.iDev.spring_security.config.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ADMIN')")
public @interface HasAdminRole {
}
