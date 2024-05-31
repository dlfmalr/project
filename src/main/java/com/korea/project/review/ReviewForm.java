package com.korea.project.review;

import com.korea.project.company.Company;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ReviewForm {
    @NotEmpty(message = "제목은 필수 입력 항목입니다.")
    private String title;
    @NotEmpty(message = "내용은 필수 입력 항목입니다.")
    private String content;
    @NotEmpty(message = "장점은 필수 입력 항목입니다.")
    private String good;
    @NotEmpty(message = "단점은 필수 입력 항목입니다.")
    private String bad;
    private Company company;
}
