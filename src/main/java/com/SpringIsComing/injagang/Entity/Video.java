package com.SpringIsComing.injagang.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//면접 영상 Entity
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    @GeneratedValue
    @Column(name = "VIDEO_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Interview interview;

    private String videoURL; //영상 url
    private String videoName; //영상 제목
}
