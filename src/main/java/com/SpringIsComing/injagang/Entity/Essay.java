package com.SpringIsComing.injagang.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//자기소개서 Entity
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Essay{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ESSAY_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member writer;

    @OneToMany(mappedBy = "essay")
    @Builder.Default
    private List<Reply> replies = new ArrayList<>(); //달린 댓글들

    @OneToMany(mappedBy = "essay")
    @Builder.Default
    private List<EssayContent> contents =  new ArrayList<>(); //자소서 문항들

    @OneToMany(mappedBy = "essay")
    @Builder.Default
    private List<EssayFeedback> feedbacks = new ArrayList<>(); //피드백들

    private Integer access; //공개 범위(0,null: private, 1: 친구공개, 2:게시판 등록)
    private String essayTitle; //자소서 제목
    private String title; //제목
    private String text; //내용 글
    private LocalDateTime date; //최신 작성 시간

    //공개범위 수정
    public void setAccess(int val){
        this.access = val;
    }

    //게시물 제목 수정
    public void setTitle(String str){
        this.title = str;
    }

    //게시물 내용 글 수정
    public void setText(String str){
        this.text = str;
    }

    //게시물 최신 수정 시간 수정
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
