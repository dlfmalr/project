package com.korea.project.review;

import com.korea.project.company.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Review save(String title, String content, String good, String bad, Company company) {
        Review review = new Review();
        review.setTitle(title);
        review.setContent(content);
        review.setGood(good);
        review.setBad(bad);
        review.setCompany(company);
        review.setCreateDate(LocalDateTime.now());
        this.reviewRepository.save(review);
        return review;
    }

    public List<Review> getReviewList() {
        return reviewRepository.findAll();
    }

    public void saveReviewWithRating(Review review, int rating) {
        // 리뷰 엔티티에 별점 정보 저장
        review.setRating(rating);
        reviewRepository.save(review);
    }

    public List<Review> getAllReviewsWithRatings() {
        // 모든 리뷰 엔티티 가져오기
        List<Review> reviews = reviewRepository.findAll();
        // 각 리뷰 엔티티의 별점 정보를 문자열로 변환하여 저장
        for (Review review : reviews) {
            review.setStars(getStars(review.getRating()));
        }
        return reviews;
    }

    // 별점 정보를 별 모양으로 변환하는 메서드
    private String getStars(int rating) {
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < rating; i++) {
            stars.append("★");
        }
        return stars.toString();
    }
}
