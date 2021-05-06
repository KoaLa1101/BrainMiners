package ru.itlab.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import ru.itlab.annotations.MyLog;
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

    @Autowired
    UserService userService;

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

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @GetMapping("/findEmployee")
    public String prepareAllEmployee(Model model){
        User.Role role = User.Role.EMPLOYER;
        model.addAttribute("allEmployee", userService.allUserByRole(role));
        model.addAttribute("filter", new Properties());
        return "findEmployee";
    }


    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @PostMapping("/findEmployee")
    public String filterEmployee(@ModelAttribute("filter") Properties filter, Model model, HttpServletRequest request){
        Properties props = new Properties();
        if(!filter.getBusyness().equals("_")) props.setBusyness(filter.getBusyness());
        if(!filter.getEducation().equals("_")) props.setEducation(filter.getEducation());
        if(!filter.getExperience().equals("_")) props.setExperience(filter.getExperience());
        if(!filter.getLevelOfEnglish().equals("_")) props.setLevelOfEnglish(filter.getLevelOfEnglish());
        if(!filter.getSalaryWork().equals("_")) props.setSalaryWork(filter.getSalaryWork());
        if(!filter.getSphereOfWork().equals("_")) props.setSphereOfWork(filter.getSphereOfWork());
        props.setUser((User) request.getSession().getAttribute("myAcc"));
        System.out.println( props.toString());
        model.addAttribute("allEmployee", userService.findAllByProperties(props));
        return "findEmployee";
    }



}