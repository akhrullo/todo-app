package uz.elmurodov.spring_security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @ResponseBody
    @GetMapping("")
    protected String unsecurePage() {
        return "<h1>Un Secure Page</h1>";
    }


    @ResponseBody
    @GetMapping("/secure")
    protected String securePage() {
        return "<h1>Secure Page</h1>";
    }

    @Secured("ADMIN")
    @GetMapping("/admin")
    protected String adminPage() {
        return "<h1>Admin PAge</h1>";
    }


}
