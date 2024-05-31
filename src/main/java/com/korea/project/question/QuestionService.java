package com.korea.project.question;

import com.korea.project.DataNotFoundException;
import com.korea.project.company.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getQuestionList() {
        return questionRepository.findAll();
    }

    public Question getQuestion(Long id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public Question save(String subject, String content, Company company) {
        Question question = new Question();
        question.setSubject(subject);
        question.setContent(content);
        question.setCompany(company);
        question.setCreateDate(LocalDateTime.now());

        questionRepository.save(question);

        return question;
    }
}
