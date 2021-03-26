package ru.itlab.controllers;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import ru.itlab.models.User;
import ru.itlab.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        if(user == null) return "index";
        else model.addAttribute("myAcc", userService.loadUserByUsername(user.getUsername()));

        return "index";
    }

    @RequestMapping("/change")
    //🤪🤪🤪
    public String change(@RequestParam String locale, HttpServletRequest request, HttpServletResponse response) {
        String[] localeData = locale.split("_");
        System.out.println(new Locale(localeData[0], localeData[1]));
        localeResolver.setLocale(request, response, new Locale(localeData[0], localeData[1]));
        return "redirect:" + request.getHeader("Referer");
    }
}
