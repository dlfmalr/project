package com.korea.project.member;

import com.korea.project.answer.Answer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/signup")
    public String signup(MemberForm memberForm) {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberForm memberForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }
        memberService.save(memberForm.getLoginId(), memberForm.getPassword(), memberForm.getEmail());

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(MemberForm memberForm) {
        return "login_form";
    }

    @GetMapping("/logout")
    public String logout(MemberForm memberForm) {
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal MemberDetail principal, Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Member member = this.memberService.getMember(principal.getUsername());
        model.addAttribute("member", member);
        if(!model.containsAttribute("url") || model.getAttribute("url")==null)
            model.addAttribute("url",member.getProfile_image());
        return "profile";
    }


    @PostMapping("/imageform")
    public String imageform(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        String url = null;
        if(file.getContentType().contains("image"))
            url = memberService.temp_save(file);
        redirectAttributes.addFlashAttribute("url",url);
        return "redirect:/profile";
    }

    @PostMapping("/imagesaveform")
    public String imagesaveform(Principal principal, @RequestParam(value = "url",defaultValue = "")String url){
        if(url.isBlank())
            return "redirect:/user/profile";
        Member member = this.memberService.getMember(principal.getName());
        memberService.save(member,url);
        return "redirect:/profile";
    }

}
