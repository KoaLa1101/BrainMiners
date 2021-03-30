package ru.itlab.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itlab.services.UserService;

import javax.servlet.http.HttpServletRequest;


@Controller
@Slf4j
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;


}
