package uz.elmurodov.spring_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/*")
public class AuthController {
    @RequestMapping("login")
    private String loginPaged() {
        return "auth/login";
    }
    @RequestMapping("logout")
    private String logoutPaged() {
        return "auth/logout";
    }
}
