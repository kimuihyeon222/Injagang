package com.SpringIsComing.injagang.Service;

import com.SpringIsComing.injagang.Entity.Essay;
import com.SpringIsComing.injagang.Entity.EssayFeedback;
import com.SpringIsComing.injagang.Entity.EssayFeedbackComment;
import com.SpringIsComing.injagang.Entity.Member;
import com.SpringIsComing.injagang.Repository.EssayFeedbackRepository;
import com.SpringIsComing.injagang.Repository.EssayRepository;
import com.SpringIsComing.injagang.Repository.FeedbackCommentRepository;
import com.SpringIsComing.injagang.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class FeedbackCommentServiceImpl implements FeedbackCommentService{
    private final MemberRepository memberRepository;
    private final EssayRepository essayRepository;
    private final EssayFeedbackRepository essayFeedbackRepository;
    private final FeedbackCommentRepository feedbackCommentRepository;

    @Override
    public List<EssayFeedbackComment> findFeedbackComment(String nickname, Long essayId, Long essayFeedbackId) {
        Member member = memberRepository.findByNickname(nickname);
        Essay essay = essayRepository.findById(essayId).orElseThrow(
                ()-> new IllegalArgumentException("자소서 없어요")
        );
        EssayFeedback essayFeedback = essayFeedbackRepository.findFeedbackByMemberAndEssay(member, essay);
        return feedbackCommentRepository.findByEssayFeedback(essayFeedback);
    }
}
