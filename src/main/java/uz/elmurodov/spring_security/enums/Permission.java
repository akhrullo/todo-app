package uz.elmurodov.spring_security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Getter
public enum Permission {
    TEACHER_CREATE("teacher_create"),
    TEACHER_DELETE("teacher_delete"),
    MANAGER_CREATE("manager_create"),
    MANAGER_DELETE("manager_delete"),
    STUDENT_CREATE("student_create"),
    STUDENT_DELETE("student_delete"),
    ADMIN_CREATE("admin_create"),
    VIEW_STUDENT_MARK("view_student_mark");
    private final String value;

}
