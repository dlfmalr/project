package com.korea.project.question;

import com.korea.project.answer.AnswerForm;
import com.korea.project.company.Company;
import com.korea.project.company.CompanyService;
import com.korea.project.review.Review;
import com.korea.project.review.ReviewForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    private final CompanyService companyService;
    @GetMapping("/create/{id}")
    public String create(QuestionForm questionForm, @PathVariable("id") Long id){
        return "question_form";
    }

    @PostMapping("/create/{id}")
    public String create(@Valid QuestionForm questionForm, BindingResult bindingResult, Model model, @PathVariable("id") Long id){
        Company company = this.companyService.getCompany(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("company", company);
            return "question_form";
        }

        Question question = this.questionService.save(questionForm.getSubject(), questionForm.getContent(), company);
        return "redirect:/company/%d".formatted(question.getCompany().getId());
    }
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model, AnswerForm answerForm) {
        Question question = questionService.getQuestion(id);
        if (question == null) {
            // 회사 정보를 찾지 못한 경우 처리 (예: 404 페이지로 리다이렉트)
            return "redirect:/error";
        }
        model.addAttribute("question", question);
        return "question_detail";
    }
}
