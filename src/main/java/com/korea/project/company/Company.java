package com.korea.project.company;

import com.korea.project.member.Member;
import com.korea.project.question.Question;
import com.korea.project.review.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private LocalDateTime createDate;
    private String url;
    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Review> reviewList;

    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Question> questionList;
}
