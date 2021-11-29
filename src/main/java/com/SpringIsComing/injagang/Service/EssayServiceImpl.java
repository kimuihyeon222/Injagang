package com.SpringIsComing.injagang.Service;

import com.SpringIsComing.injagang.DTO.EssayWriteDTO;
import com.SpringIsComing.injagang.Entity.Essay;
import com.SpringIsComing.injagang.Entity.EssayContent;
import com.SpringIsComing.injagang.Repository.EssayRepository;
import com.SpringIsComing.injagang.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.SpringIsComing.injagang.Entity.Member;
import com.SpringIsComing.injagang.Repository.EssayRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;


import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EssayServiceImpl implements EssayService {

    private final EssayRepository essayRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<Essay> findEssays(Member member) {

        return essayRepository.findEssaysByMember(member);

    }

    @Override
    public Long save(Essay essay) {
        Essay save = essayRepository.save(essay);
        return save.getId();
    }


    @Override
    public Member findMember(String nickName) {
        return memberRepository.findByNickname(nickName);
    }


    //    RequiredArgsConstructor -> final이 붙은것들 생성자 만들어줌
//    생성자는 하나만 있으면 자동으로 추가 해줌
//    @Autowired
//    public EssayServiceImpl(EssayRepository essayRepository) {
//        this.essayRepository = essayRepository;
//    }
    @Override
    public Long storeEssay(Essay essay) {
        Essay save = essayRepository.save(essay);
        //redirectatt~~~ 이거 쓰고 저장할때 생성된거 id값 리턴
        return save.getId();
    }

    public Essay findEssay(Long essayId) {
        return essayRepository.findById(essayId)
                .orElseThrow(()-> new IllegalArgumentException("아몰랑"));
    }

    public EssayWriteDTO readEssay(Long id) {
        Optional<Essay> result = essayRepository.findById(id);
        return result.isPresent() ? essayEntityToDto(result.get()) : null;
    }
}