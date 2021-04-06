package ru.itlab.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping("/")
    public String defaultPath(Model model, @AuthenticationPrincipal User user ,HttpServletRequest request) {
        if (user == null) {
            return "index";
        } else {
            request.getSession().setAttribute("myAcc", userService.loadUserByUsername(user.getUsername()));
            model.addAttribute("myAcc", request.getSession().getAttribute("myAcc"));
        }

        return "index";
    }

    @GetMapping("/authByGoogle")
    public String loginOauth2(Model model, Principal principal, HttpServletRequest request) {
        String username = getUsername(principal);
        User user = userService.loadUserByUsername(username);
        log.info(principal.toString());
        if (user == null) {
            model.addAttribute("oauth2Form", new User());
            return "oauth2Form";
        }
        else{
            request.getSession().setAttribute("myAcc", user);
            return "redirect:/";
        }
    }

    @PostMapping("/authByGoogle")
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

    @RequestMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String showProfile(Model model, HttpServletRequest request){
        model.addAttribute("myAcc", request.getSession().getAttribute("myAcc"));

        return "profile";
    }

    @GetMapping("/profile/edit")
    @PreAuthorize("isAuthenticated()")
    public String showEditProfile(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("myAcc");
        user.setPassword(user.getPasswordConfirm());
        model.addAttribute("userForm", user);
        model.addAttribute("password", model.getAttribute("passwordConfirm"));
        log.info(model.toString());

        return "editProfileForm";
    }

    @PostMapping("/profile/edit")
    @PreAuthorize("isAuthenticated()")
    public String editProfile(Model model, @ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, HttpServletRequest request){
        User oldUser = (User) request.getSession().getAttribute("myAcc");
        if (bindingResult.hasErrors()) return "editProfileForm";
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Passwords are not equals");
            return "editProfileForm";
        }
        if (userService.loadUserByUsername(userForm.getUsername()) == null && userForm.getUsername().equals(oldUser.getUsername())) {
            model.addAttribute("usernameError", "User with username like it is exist");
            return "editProfileForm";
        }
        userService.updateUser(oldUser, userForm);
        request.getSession().setAttribute("myAcc", userForm);


        return "redirect:/profile";
    }

    @RequestMapping("/login/oauth")
    public String showCode(@RequestParam String code, ModelMap map){
        map.put("code", code);
        return "showCode";
    }

    //🤬🤬🤬
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
            request.getSession().setAttribute("myAcc", userForm);
            return userService.saveUser(userForm);
        } else {
            request.getSession().setAttribute("myAcc", user);
            log.info(user.toString());
        }
        return false;
    }


    //☢☢☢
    private String getUsername(Principal principal){
        String[] str = principal.toString().split("]],")[1].split(",");
        return str[4].split("=")[1] + "_" + str[12].split("=")[1];
    }
}
