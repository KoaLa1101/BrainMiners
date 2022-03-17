package ru.itlab.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itlab.services.UserService;

@Controller
@Slf4j
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    //🤪🤪🤪
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @PostMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    //🤪🤪
    public boolean deleteUser(@RequestParam(required = true, defaultValue = "") int userId) {
       return userService.deleteUser(userId);
    }
}


