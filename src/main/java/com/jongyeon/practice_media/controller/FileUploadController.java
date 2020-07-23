package com.jongyeon.practice_media.controller;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Controller
public class FileUploadController {

    @GetMapping("/fileUpload")
    public String fileUploadPage(){
        return "fileUpload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile){
        log.info("#############Upload File#############");
        File targetFile = new File("src\\main\\resources\\static\\images\\" + multipartFile.getOriginalFilename());
        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
            log.info("Type: "+multipartFile.getContentType());
            log.info("Origin Name: "+multipartFile.getOriginalFilename());
            log.info("Name: "+multipartFile.getName());
            log.info("Size: "+multipartFile.getSize());


        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            e.printStackTrace();
        }
        return "redirect:/fileUpload";
    }
}
