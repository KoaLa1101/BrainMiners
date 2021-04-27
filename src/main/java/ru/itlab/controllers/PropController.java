package ru.itlab.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itlab.models.Properties;
import ru.itlab.models.User;
import ru.itlab.repositories.UserRepository;
import ru.itlab.services.PropService;
import ru.itlab.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
public class PropController {

    @Autowired
    PropService propService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/profile/myProps")
    public String showPropForm(Model model, HttpServletRequest request) {
        if(request.getSession().getAttribute("myProps")!=null)
            model.addAttribute("propForm",request.getSession().getAttribute("myProps"));
        else
            model.addAttribute("propForm", new Properties());

        return "properties";
    }

    @PostMapping("/profile/myProps")
    public String myProps(@ModelAttribute("propForm") @Valid Properties propForm, Model model, HttpServletRequest request) {
        User myAcc = (User) request.getSession().getAttribute("myAcc");
        propForm.setId(myAcc.getId());

        myAcc.setProperties(propForm);
        request.getSession().setAttribute("myAcc", myAcc);
        if (propService.saveProp(propForm)) {
            request.getSession().setAttribute("myProps", propForm);
            userRepository.updateProps(myAcc.getProperties().getId(), myAcc.getId());
            return "redirect:/";
        } else {
            return "redirect:/profile/myProps";
        }
    }


}
