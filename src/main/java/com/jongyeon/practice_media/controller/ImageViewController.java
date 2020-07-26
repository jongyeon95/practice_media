package com.jongyeon.practice_media.controller;

import com.jongyeon.practice_media.service.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpRequest;

@RestController
public class ImageViewController {

    @Autowired
    FileService fileService;

    @GetMapping("/imageApi/{name}")
    public byte[] imageApi(@PathVariable("name") String name, HttpServletRequest request) throws IOException {

        File path=new File("src\\main\\resources\\static\\images\\"+name);
        InputStream imageStream=new FileInputStream(path.getAbsolutePath());
        byte[] imageByteArray= IOUtils.toByteArray(imageStream);
        imageStream.close();
        return imageByteArray;

    }

}
