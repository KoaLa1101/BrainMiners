package ru.itlab.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itlab.services.UserService;

@Controller
@Slf4j
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    //🤪🤪🤪
    public String userList(Model model, Authentication authentication) {
        log.info(authentication.getAuthorities().toString());
        log.info(authentication.getName());
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @PostMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    //🤪🤪🤪
    public String deleteUser(@RequestParam(required = true, defaultValue = "") int userId, @RequestParam(required = true, defaultValue = "") String action, Model model) {
        if (action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }
}


