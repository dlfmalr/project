package com.korea.project.review;

import com.korea.project.company.Company;
import com.korea.project.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String good;
    private String bad;
    private int rating;
    private String stars;
    private LocalDateTime createDate;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Member member;
}
