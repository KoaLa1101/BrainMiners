package ru.itlab.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itlab.models.Message;
import ru.itlab.models.Templates;
import ru.itlab.models.User;
import ru.itlab.services.TempService;
import ru.itlab.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class TempController {
    @Autowired
    TempService tempService;

    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @GetMapping("/profile/createNewTemp")
    public String showTempForm(Model model, HttpServletRequest request){
        model.addAttribute("newTemp", new Templates());
        model.addAttribute("myAcc", request.getSession().getAttribute("myAcc"));

        return "createTemp";
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @PostMapping("/profile/createNewTemp")
    public String saveTemp(HttpServletRequest request, @ModelAttribute("newTemp") Templates templates){
        User myAcc = (User) request.getSession().getAttribute("myAcc");
        if(!templates.getUserList().contains(myAcc))
        templates.getUserList().add(myAcc);
        tempService.saveTemp(templates);
        myAcc.getTemplatesList().add(templates);
        request.getSession().setAttribute("myAcc", myAcc);

        return "redirect:/profile";
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @GetMapping("/profile/myTemps")
    public String prepareShowMyTemps(Model model, HttpServletRequest request){
        User myAccFromSession = (User) request.getSession().getAttribute("myAcc");
        User myAcc = userService.findUserById(myAccFromSession.getId());
        List<Templates> templatesList = tempService.findAllByUserList(myAcc);
        log.info(templatesList.toString());
        model.addAttribute("myTemps", templatesList);
        model.addAttribute("myAcc", myAcc);
        request.getSession().setAttribute("myAcc", myAcc);

        return "myTemps";
    }


}
