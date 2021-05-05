package ru.itlab.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itlab.models.Message;
import ru.itlab.models.User;
import ru.itlab.services.MesService;
import ru.itlab.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
public class MesController {
    @Autowired
    MesService mesService;

    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @GetMapping("/findEmployee/newMes")
    public String prepareNewMes(Model model){
        model.addAttribute("newMes", new Message());

        return "CreateNewMes";

    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping("/findEmployee/newMes")
    public String saveMes(@RequestParam(required = true, defaultValue = "") int userId, @RequestParam(required = true, defaultValue = "") String action, @ModelAttribute("newMes") Message newMes){
        User myAcc = userService.findUserById(userId);
        log.info(newMes.getMes());
        if(action.equals("create")) mesService.saveMes(newMes);
        myAcc.getMessageList().add(newMes);
        userService.updateUser(myAcc);

        return "redirect:/findEmployee";
    }
}
