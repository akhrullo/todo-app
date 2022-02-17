package uz.elmurodov.spring_security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.elmurodov.spring_security.configs.annotations.HasAdminCreateAuthority;

@RestController
@RequestMapping("/secured/*")
public class SecuredController {

    @PreAuthorize("hasAuthority('view_student_mark')")
    @GetMapping("student-profile")
    public String studentProfile() {
        return "Student Profile Page";
    }

    @HasAdminCreateAuthority
    @GetMapping("admin-create")
    public String adminCreate() {
        return "Admin create Page";
    }

    @PreAuthorize("hasAuthority('manager_create')")
    @GetMapping("manager-create")
    public String managerCreate() {
        return "Manager Create Page";
    }

    @GetMapping("manager-delete")
    public String managerDelete() {
        return "manager delete Page";
    }

    @GetMapping("teacher-create")
    public String teacherCreate() {
        return "teacher Create Page";
    }

    @GetMapping("teacher-delete")
    public String teacherDelete() {
        return "teacher delete Page";
    }

    @GetMapping("student-create")
    public String studentCreate() {
        return "student Create Page";
    }

    @GetMapping("student-delete")
    public String studentDelete() {
        return "student delete Page";
    }

}
