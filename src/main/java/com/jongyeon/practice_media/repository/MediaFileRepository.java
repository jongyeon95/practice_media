package com.jongyeon.practice_media.repository;


import com.jongyeon.practice_media.entity.MediaFile;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MediaFileRepository extends JpaRepository<MediaFile,Integer> {

    @Transactional
    List<MediaFile> findAll();


}
