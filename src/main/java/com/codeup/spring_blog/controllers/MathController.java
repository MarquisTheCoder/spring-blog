package com.codeup.spring_blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{x}/and/{y}")
    @ResponseBody
    public String add(@PathVariable int x, @PathVariable int y){
        return String.format("Your result is: %d", x + y);
    }
    @GetMapping("/subtract/{x}/and/{y}")
    @ResponseBody
    public String subtract(@PathVariable int x, @PathVariable int y){
        return String.format("Your result is: %d", x - y);
    }

    @GetMapping("/multiply/{x}/by/{y}")
    @ResponseBody
    public String multiply(@PathVariable int x, @PathVariable int y){
        return String.format("Your result is: %d", x * y);
    }
    @GetMapping("/divide/{x}/by/{y}")
    public String divide(@PathVariable int x, @PathVariable int y){
        return String.format("Your result is: %d", x / y);
    }
}
