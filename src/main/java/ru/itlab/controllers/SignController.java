package ru.itlab.controllers;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itlab.converter.OauthConverter;
import ru.itlab.models.User;
import ru.itlab.models.forms.HhForm;
import ru.itlab.models.forms.LoginForm;
import ru.itlab.others.Hh;
import ru.itlab.others.Vk_auth;
import ru.itlab.services.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static ru.itlab.others.Hh.hh;


/*🥰🥵*/
@Controller
@Slf4j
public class SignController {
    @Autowired
    private UserService userService;

    @GetMapping("/signUp")
    public String showSignUpForm(Model model) {
        model.addAttribute("userForm", new User());

        return "signUp";
    }

    @PostMapping("/signUp")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {
        log.info(userForm + "Пришедшая форма");
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

    @GetMapping("/oauth")
    public String loginWithHH(@RequestParam("code") String code, Model model) throws IOException, ExecutionException, InterruptedException, OAuthSystemException {

        HhForm hhForm = hh.getOauthUser(code);
        model.addAttribute("partOfHhForm", hhForm);
        model.addAttribute("hhForm", new HhForm());
        return "oauthForm";
    }

    @PostMapping("/oauth")
    public String signUpOauth( @ModelAttribute("hhForm")HhForm hhForm){
        OauthConverter oauthConverter = new OauthConverter();
        User user = oauthConverter.convert(hhForm);
        userService.saveUser(user);

        return "redirect:/";
    }

    @GetMapping("/oauthVk")
    public String loginWithVk(@RequestParam("code") String code, Model model){
        try {
            Vk_auth vk_auth = new Vk_auth(code);
            String str = vk_auth.getToken();
            log.info("str: " + str);
        } catch (ClientException | ApiException e) {
            e.printStackTrace();
        }

        return "oauthFrom";
    }
}
