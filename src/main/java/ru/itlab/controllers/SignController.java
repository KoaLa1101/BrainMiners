package ru.itlab.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itlab.models.User;
import ru.itlab.models.forms.LoginForm;
import ru.itlab.services.UserService;

import javax.validation.Valid;


/*🥰🥵*/
@Controller
@Slf4j
public class SignController {
    @Autowired
    private UserService userService;

    @GetMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute("userForm", new User());

        return "signUp";
    }

    @PostMapping("/signUp")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {
        log.info(userForm +"Пришедшая форма");
        if (bindingResult.hasErrors()) return "signUp";
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Passwords are not equals");
            return "signUp";
        }
        if (!userService.saveUser(userForm)) {
            model.addAttribute("usernameError", "User with username like it is exist");
            return "signUp";
        }

        return "redirect:/";
    }

    @RequestMapping("/signIn")
    @PreAuthorize("isAnonymous()")
    public String showSignInForm(@ModelAttribute("loginForm") LoginForm loginForm) {

        return "signIn";
    }
}
