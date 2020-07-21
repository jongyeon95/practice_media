package com.jongyeon.practice_media.service;

import com.jongyeon.practice_media.dto.FileDto;
import com.jongyeon.practice_media.entity.File;
import com.jongyeon.practice_media.repository.MediaFileRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class FileService {


    @Autowired
    private MediaFileRepository mediaFileRepository;

    public List<File> findAll(){

        return mediaFileRepository.findAll();

    }


}
