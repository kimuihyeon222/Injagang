package com.SpringIsComing.injagang.Controller;

import com.SpringIsComing.injagang.DTO.TemplateViewDTO;
import com.SpringIsComing.injagang.Entity.InterviewQuestion;
import com.SpringIsComing.injagang.Entity.QuestionType;
import com.SpringIsComing.injagang.Service.InterviewQuestionService;
import com.SpringIsComing.injagang.Service.TemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AdminController {
    private final TemplateService templateService;
    private final InterviewQuestionService interviewQuestionService;

    //관리자 권한이 있는 사람만 접속 가능
    @GetMapping("/admin")
    public String adminPage(@SessionAttribute("loginSession") String nickname, Model model) {
        List<TemplateViewDTO> dtoList = templateService.findTemplates();

        log.info("dtoList = {}", dtoList);
        model.addAttribute("templateList", dtoList);
        return "admin/main";
    }

    @GetMapping("/template/read")
    public String readTemplate(@RequestParam Long id, Model model) {
        TemplateViewDTO dto = templateService.findTemplate(id);

        log.info("dto = {}", dto);
        model.addAttribute("dto", dto);
        return "admin/template-read";
    }

    @GetMapping("/template/accept")
    public String templateAccept(@RequestParam Long id) {
        log.info("-----------templateAccept-----------");
        templateService.grantTemplate(id);
        return "redirect:/admin";
    }

    @GetMapping("/template/revoke")
    public String templateRevoke(@RequestParam Long id) {
        log.info("-----------templateRevoke-----------");
        templateService.revokeTemplate(id);
        return "redirect:/admin";
    }

    @GetMapping("/template/delete")
    public String templateDelete(@RequestParam Long id) {
        log.info("-----------templateDelete-----------");
        templateService.deleteTemplate(id);
        return "redirect:/admin";
    }


    @GetMapping("/admin/questions")
    public String question(Model model) {

        model.addAttribute("questions", interviewQuestionService.findAll());

        return "admin/questions";
    }

    @GetMapping("/admin/add")
    public String addQuestion(@ModelAttribute("question") InterviewQuestion interviewQuestion) {

        return "admin/add";
    }

    @PostMapping("/admin/add")
    public String saveQuestion(@ModelAttribute("question") InterviewQuestion interviewQuestion) {
        log.info("병신={}",interviewQuestion.getTitle());
        log.info("병신2={}",interviewQuestion.getQuestionType());

        interviewQuestionService.save(interviewQuestion);

        return "redirect:/admin/add";
    }

    @ModelAttribute("questionTypes")
    public QuestionType[] questionTypes(){
        return QuestionType.values();
    }


    @GetMapping("/admin/delete/{id}")
    public String deleteQuestion(@PathVariable("id") Long id) {
        interviewQuestionService.deleteInterviewQuestions(id);

        return "redirect:/admin/questions";
    }
}