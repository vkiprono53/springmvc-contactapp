package com.vkiprono.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("home")
public class HomeController {
    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public String hello(){
        return "home";
    }
}
