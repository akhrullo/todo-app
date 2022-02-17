package uz.elmurodov.spring_security.enums;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(Sets.newHashSet(
            Permission.TEACHER_CREATE,
            Permission.TEACHER_DELETE,
            Permission.MANAGER_CREATE,
            Permission.MANAGER_DELETE,
            Permission.STUDENT_CREATE,
            Permission.STUDENT_DELETE,
            Permission.ADMIN_CREATE)),
    MANAGER(Sets.newHashSet(
            Permission.TEACHER_CREATE,
            Permission.TEACHER_DELETE,
            Permission.MANAGER_CREATE,
            Permission.MANAGER_DELETE)),
    TEACHER(Sets.newHashSet(Permission.TEACHER_CREATE,
            Permission.TEACHER_DELETE,
            Permission.STUDENT_CREATE,
            Permission.STUDENT_DELETE)),
    STUDENT(Sets.newHashSet(Permission.VIEW_STUDENT_MARK));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public String[] getAuthorities() {
        return permissions.stream().map(Permission::getValue).toArray(String[]::new);
    }
}

