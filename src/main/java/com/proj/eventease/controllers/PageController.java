package com.proj.eventease.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

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
        return "about";
    }

    //services controller

}
