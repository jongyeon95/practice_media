package com.jongyeon.practice_media.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaFile {

        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private int idx;

        @Column(nullable=false)
        private String originalFileName;

        @Column(nullable=false)
        private String storedFilePath;

        @Column(nullable = false)
        private String storedFileName;

        @Column(nullable=false)
        private long fileSize;

        @Column(nullable=false)
        private String creatorId;

        @Column(nullable=false)
        private LocalDateTime createdDatetime = LocalDateTime.now();

        private String updatorId;

        private LocalDateTime updateDatetime;

        @Column(nullable=false)
        private String mediaType;

        @Column(nullable = false)
        private String FileExtension;


    }
