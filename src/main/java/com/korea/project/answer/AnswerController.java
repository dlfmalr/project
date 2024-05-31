package com.korea.project.answer;

import com.korea.project.member.Member;
import com.korea.project.member.MemberService;
import com.korea.project.question.Question;
import com.korea.project.question.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;
    private final MemberService memberService;
    private final QuestionService questionService;
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Long id, @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
        Question question = this.questionService.getQuestion(id);
        Member member = this.memberService.getMember(principal.getName());
        if (bindingResult.hasErrors()) {
            return "question_detail";
        }
        model.addAttribute("question", question);
        Answer answer = this.answerService.create(question, answerForm.getContent(), member);
        return String.format("redirect:/question/%s#answer_%s", answer.getQuestion().getId(), answer.getId());
    }
}
