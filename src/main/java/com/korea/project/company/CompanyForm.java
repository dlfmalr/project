package com.korea.project.company;

import com.korea.project.member.Member;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;

@Getter
@Setter
public class CompanyForm {
    @NotEmpty(message = "회사 이름은 필수 항목입니다.")
    private String name;

}
