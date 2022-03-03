package uz.iDev.spring_security.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.iDev.spring_security.dto.auth.AuthUserCreateDto;
import uz.iDev.spring_security.service.AuthUserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth/*")
public class AuthUserController {

    @Autowired
    private AuthUserService authUserService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new AuthUserCreateDto());

        return "auth/registration";
    }

    @PostMapping("/registration")
    public String addAuthUser(@ModelAttribute("userForm") @Valid AuthUserCreateDto userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }
//        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
//            model.addAttribute("passwordError", "Пароли не совпадают");
//            return "registration";
//        }
        if (!authUserService.saveUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "auth/registration";
        }

        return "redirect:/";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "auth/login";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logoutPage() {
        return "auth/logout";
    }
}
