package com.korea.project.member;

import com.korea.project.company.Company;
import com.korea.project.question.Question;
import com.korea.project.review.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String loginId;
    private String password;
    private String email;
    private LocalDateTime createDate;


    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Company> companyList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Review> reviewList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Question> questionList;

    private String profile_image;

    private String Role;

    private String provider;
    private String providerId;
}
