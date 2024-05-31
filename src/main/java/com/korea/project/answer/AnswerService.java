package com.korea.project.answer;

import com.korea.project.member.Member;
import com.korea.project.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    public Answer create(Question question, String content, Member member) {
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setContent(content);
        answer.setAuthor(member);
        answer.setCreateDate(LocalDateTime.now());
        answerRepository.save(answer);
        return answer;
    }
}
