package ru.itlab.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import ru.itlab.models.User;
import ru.itlab.models.forms.OauthForm;
import ru.itlab.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Locale;

@Controller
@Slf4j
public class MyController {

    @Autowired
    private UserService userService;
    @Autowired
    private LocaleResolver localeResolver;

    @RequestMapping("/")
    public String defaultPath(Model model, @AuthenticationPrincipal User user) {
        if (user == null) {
            return "index";
        } else {
            model.addAttribute("myAcc", userService.loadUserByUsername(user.getUsername()));
        }

        return "index";
    }

    @GetMapping("/home")
    public String loginOauth2(Model model, Principal principal, HttpServletRequest request) {
        String username = getUsername(principal);
        User user = userService.loadUserByUsername(username);
        if (user == null) {
            model.addAttribute("oauth2Form", new User());
            return "oauth2Form";
        }
        else{
            request.getSession().setAttribute("myAcc", user);
            return "redirect:/";
        }
    }

    @PostMapping("/home")
    //🤮🤬
    public String signUpOauth2(Principal principal, @ModelAttribute("oauth2Form") @Valid OauthForm form, BindingResult bindingResult, Model model, HttpServletRequest request) {
        String[] str = principal.toString().split("]],")[1].split(",");
        if (bindingResult.hasErrors()) return "oauth2Form";
        if (!form.getPassword().equals(form.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Passwords are not equals");
            return "oauth2Form";
        }
        createUserFromOauth2(str[4].split("=")[1], str[12].split("=")[1], form, request, principal);
        return "redirect:/";
    }


    @RequestMapping("/change")
    //🤪🤪🤪
    public String change(@RequestParam String locale, HttpServletRequest request, HttpServletResponse response) {
        String[] localeData = locale.split("_");
        localeResolver.setLocale(request, response, new Locale(localeData[0], localeData[1]));
        return "redirect:" + request.getHeader("Referer");
    }

    private boolean createUserFromOauth2(String firstName, String lastName, OauthForm form, HttpServletRequest request,Principal principal) {
        String username = getUsername(principal);
        User user = userService.loadUserByUsername(username);
        if (user == null) {
            User userForm = new User();
            userForm.setFirstName(firstName);
            userForm.setLastName(lastName);
            userForm.setUsername(username);
            userForm.setRole(form.getRole());
            userForm.setPassword(form.getPassword());
            userForm.setPasswordConfirm(form.getPasswordConfirm());
            return userService.saveUser(userForm);
        } else {
            request.getSession().setAttribute("myAcc", user);
            log.info(user.toString());
        }
        return false;
    }

    private String getUsername(Principal principal){
        String[] str = principal.toString().split("]],")[1].split(",");
        return str[4].split("=")[1] + "_" + str[12].split("=")[1];
    }
}
