package com.ktsr.service;


import com.ktsr.fegin.QuizInterface;
import com.ktsr.model.QuestionWrapper;
import com.ktsr.model.Quiz;
import com.ktsr.model.QuizDto;
import com.ktsr.model.Response;
import com.ktsr.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String categoryName, Long numQ, String title){
        List<Long>  questuions=quizInterface.getQuestionsForQuiz(categoryName,numQ).getBody();

        Quiz quiz= new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questuions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Long id){
        Quiz quiz= quizRepository.findById(id).get();

        List<Long> questionIds=quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>>  questions=quizInterface.getQuestionsFromId(questionIds);
        return questions;
    }

    public ResponseEntity<Long> calculateResult(Long id, List<Response> responses){
        ResponseEntity<Long> score= quizInterface.getScore(responses);
        return score;
    }

}
