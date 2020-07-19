package com.jongyeon.practice_media.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String Index(){
        return "index";
    }

    @GetMapping("/image")
    public String imageView(Model model){
        model.addAttribute("imageDir","/images/test.png");
        return "image";
    }
}
