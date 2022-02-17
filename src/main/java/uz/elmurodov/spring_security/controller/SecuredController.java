package uz.elmurodov.spring_security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecuredController {


    @GetMapping("/secured")
    @ResponseBody
    public String securedPage() {
        return "secured page";
    }

    @Secured("ADMIN")
    @GetMapping("/secured-role-admin")
    @ResponseBody
    public String securedRoleAdminPage() {
        return "secured-role-admin page";
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/secured-role-manager")
    @ResponseBody
    public String securedRoleManagerPage() {
        return "secured-role-manager page";
    }

    @PreAuthorize("hasAuthority('manager_delete')")
    @GetMapping("/secured-delete-manager")
    @ResponseBody
    public String securedDeleteManagerPage() {
        return "secured-delete-manager page";
    }

}
