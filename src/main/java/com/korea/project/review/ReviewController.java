package com.korea.project.review;

import com.korea.project.company.Company;
import com.korea.project.company.CompanyForm;
import com.korea.project.company.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final CompanyService companyService;

    @GetMapping("/create/{id}")
    public String create(ReviewForm reviewForm, @PathVariable("id") Long id){
        return "review_form";
    }

    @PostMapping("/create/{id}")
    public String create(@Valid ReviewForm reviewForm, BindingResult bindingResult, Model model, @PathVariable("id") Long id){
        Company company = this.companyService.getCompany(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("company", company);
            return "review_form";
        }

        Review review = this.reviewService.save(reviewForm.getTitle(), reviewForm.getContent(), reviewForm.getGood(), reviewForm.getBad(), company);
        return "redirect:/company/%d".formatted(review.getCompany().getId());
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Review> reviewList = reviewService.getReviewList();
        model.addAttribute("reviewList", reviewList);
        return "company_detail";
    }



}
