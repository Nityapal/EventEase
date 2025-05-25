package com.proj.eventease.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proj.eventease.entities.User;
import com.proj.eventease.forms.UserForm;
import com.proj.eventease.helpers.Message;
import com.proj.eventease.helpers.MessageType;
import com.proj.eventease.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/homepage")
    public String home(Model m){
        System.out.println("Home page handler");
        m.addAttribute("name", "Clubs");
        m.addAttribute("clubname", "exe");
        m.addAttribute("dept", "electrical");
        return "home";
    }

    //about controller
    @RequestMapping("/about")
    public String aboutPage(){
        System.out.println("about page");
        return "about";
    }

    //services controller
    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("services page");
        return "services";
    }

    //contact controller
    @GetMapping("/contact")
    public String contactPage(){
        System.out.println("contact page");
        return "contact";
    }

    //login
    @GetMapping("/login")
    public String loginPage(){
        System.out.println("login page");
        return "login";
    }

    //register
    @GetMapping("/register")
    public String registerPage(Model m){
        UserForm userForm= new UserForm();
        //userForm.setFirstname("nitya");
        m.addAttribute("userForm", userForm);
        return "register";
    }

    //processing register
    @RequestMapping(value= "/do-register",method= RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm, HttpSession session){
        System.out.println("processing registration");
        //fetch
        System.out.println(userForm);

        //save to db
        // User user= User.builder()
        // .firstname(userForm.getFirstname())
        // .lastname(userForm.getLastname())
        // .email(userForm.getEmail())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("")
        // .build();

        User user= new User();
        user.setFirstname(userForm.getFirstname());
        user.setLastname(userForm.getLastname());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("null"); 

        User savedUser =userService.saveUser(user);
        System.out.println("user saved");

        //add the message
        Message message= Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message",message);

        //redirect login page
        return "redirect:/register";
    }

}
