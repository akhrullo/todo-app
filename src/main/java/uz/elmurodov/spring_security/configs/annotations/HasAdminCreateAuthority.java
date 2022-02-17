package uz.elmurodov.spring_security.configs.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasAuthority('admin_create')")
public @interface HasAdminCreateAuthority {
}
