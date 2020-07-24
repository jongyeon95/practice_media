package com.jongyeon.practice_media.service;

import com.jongyeon.practice_media.entity.MediaFile;
import com.jongyeon.practice_media.repository.MediaFileRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class FileService {


    @Autowired
    private MediaFileRepository mediaFileRepository;

    public List<MediaFile> findAll(){

        return mediaFileRepository.findAll();


    }

    public void save(MediaFile mediaFile){
        mediaFileRepository.save(mediaFile);

    }

    public List<MediaFile> findAllByMediaType(String s){
        return mediaFileRepository.findAllByMediaType(s);
    }


}
