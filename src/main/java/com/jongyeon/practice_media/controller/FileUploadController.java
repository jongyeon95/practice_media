package com.jongyeon.practice_media.controller;

import com.jongyeon.practice_media.entity.MediaFile;
import com.jongyeon.practice_media.repository.MediaFileRepository;
import com.jongyeon.practice_media.service.FileService;
import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Controller
public class FileUploadController {

    @GetMapping("/fileUpload")
    public String fileUploadPage(){
        return "fileUpload";
    }

    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile){
        log.info("#############Upload MediaFile#############");

        String newFileName, originalFileExtension,contentType,mediaType,path;


        originalFileExtension= FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        contentType=multipartFile.getContentType();

        if(multipartFile.getSize()<=0)
            return "redirect:/fileUpload";

        if(contentType.contains("image")){
            mediaType="image";
        }
        else if(contentType.contains("video")){
            mediaType="video";
        }
        else{
            mediaType="Unknown";
        }

        newFileName=Long.toString(System.nanoTime())+"."+originalFileExtension;
        log.info("new file name :"+newFileName);
        path="src\\main\\resources\\static\\"+mediaType+"s\\"+newFileName;
        MediaFile mediaFile=new MediaFile().builder().fileFormat(originalFileExtension).fileSize(multipartFile.getSize())
                .originalFileName(multipartFile.getOriginalFilename()).storedFilePath(path)
                .mediaType(mediaType).createdDatetime(LocalDateTime.now())
                .creatorId("admin").build();


        fileService.save(mediaFile);
        log.info(" file path :"+path);

        File targetFile = new File(path);
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
        return "redirect:/";
    }
}
