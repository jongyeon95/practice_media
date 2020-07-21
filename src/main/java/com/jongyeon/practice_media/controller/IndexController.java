package com.jongyeon.practice_media.controller;

import com.jongyeon.practice_media.dto.FileDto;
import com.jongyeon.practice_media.entity.File;
import com.jongyeon.practice_media.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private FileService fileService;

    @GetMapping("/")
    public String Index(Model model){
        List<File> list= fileService.findAll();
       model.addAttribute("list",list);
        return "index";
    }

    @GetMapping("/image")
    public String imageView(Model model){
        model.addAttribute("imageDir","/images/test.png");
        return "image";
    }

    @GetMapping("/video")
    public String videoView(Model model){
        model.addAttribute("videoDir","/videos/test.mp4");
        return "video";
    }
}
