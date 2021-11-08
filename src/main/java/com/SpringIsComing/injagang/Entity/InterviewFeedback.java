package com.SpringIsComing.injagang.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//면접 첨삭 Entity
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewFeedback {
    @Id
    @GeneratedValue
    @Column(name = "IF_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Interview interview;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String comment; //문항별 첨삭 내용
    private LocalDateTime date; //글쓴 날짜
}
