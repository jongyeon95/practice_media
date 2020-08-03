package com.jongyeon.practice_media.controller;

import com.jongyeon.practice_media.entity.MediaFile;
import com.jongyeon.practice_media.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;


@Slf4j
@Controller
public class FileUploadController {


    @GetMapping("/fileUpload")
    public String fileUploadPage(){
        return "fileUpload";
    }

    @Autowired
    private FileService fileService;

    @ResponseBody
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("#############Request upload#############");

        //System.out.println(request.getRemoteAddr());
        String newFileName, originalFileExtension,contentType,mediaType,path;


        originalFileExtension= FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        contentType=multipartFile.getContentType();


        if(contentType.contains("image")){
            mediaType="image";
        }
        else if(contentType.contains("video")){
            String[] typeStr=contentType.split("/");
            log.info("This is Mp4???");

            if(!typeStr[typeStr.length-1].equals("mp4")){
                log.info("No It is "+typeStr[typeStr.length-1]);
                return "Not Mp4";
            }
            log.info("Yes it is");
            mediaType="video";
        }
        else{
            return "no support";
        }


        newFileName=Long.toString(System.nanoTime())+"."+originalFileExtension;
        log.info("new file name :"+newFileName);
        path="src\\main\\resources\\static\\"+mediaType+"s\\";
        MediaFile mediaFile=new MediaFile().builder().FileExtension(originalFileExtension).fileSize(multipartFile.getSize())
                .originalFileName(multipartFile.getOriginalFilename()).storedFilePath(path).storedFileName(newFileName)
                .mediaType(mediaType).createdDatetime(LocalDateTime.now())
                .creatorId("admin").build();


        fileService.save(mediaFile);
        log.info("file path :"+path);

        File targetFile = new File(path+newFileName);
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
        log.info("Success Upload");
        return "ok";
    }

}
