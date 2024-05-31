package com.korea.project.company;

import com.korea.project.member.Member;
import com.korea.project.member.MemberRepository;
import com.korea.project.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;
    @GetMapping("/create")
    public String create(CompanyForm companyForm){
        return "company_form";
    }

    @PostMapping("/create")
    public String create(@Valid CompanyForm companyForm, BindingResult bindingResult, @RequestParam("file")MultipartFile file){
        if (bindingResult.hasErrors()) {
            return "redirect:/company/create";
        }
        String url = companyService.temp_save(file);
        companyService.save(companyForm.getName(),url);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Company> companyList = companyService.getCompanyList();

        model.addAttribute("companyList", companyList);

        return "main";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Company company = companyService.getCompany(id);
        if (company == null) {
            // 회사 정보를 찾지 못한 경우 처리 (예: 404 페이지로 리다이렉트)
            return "redirect:/error";
        }
        model.addAttribute("company", company);
        return "company_detail";
    }

    @GetMapping("/search")
    public String search() {
        return "search_company";
    }

    @PostMapping("/search")
    public String search(@RequestParam("name") String name, Model model) {
        List<Company> searchCompany = companyService.getSearchList(name);
        model.addAttribute("searchCompany", searchCompany);

        return "search_company";
    }

}
