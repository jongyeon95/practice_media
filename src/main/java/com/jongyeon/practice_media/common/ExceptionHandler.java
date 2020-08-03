package com.jongyeon.practice_media.common;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandler {
    private Logger log= LoggerFactory.getLogger(this.getClass());
    @org.springframework.web.bind.annotation.ExceptionHandler({SizeLimitExceededException.class, MaxUploadSizeExceededException.class})
    public void UploadFileSizeExceptionHandler(HttpServletRequest request, Exception ex){

        log.error("Upload Size Exception");

    }
}
