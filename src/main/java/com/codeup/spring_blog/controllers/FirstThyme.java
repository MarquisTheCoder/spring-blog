package com.codeup.spring_blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FirstThyme {

    @GetMapping("/thyme/{test}")
    public String home(@PathVariable String test, Model model){
        model.addAttribute("test", test);
        return "showing-data-test";
    }
}
