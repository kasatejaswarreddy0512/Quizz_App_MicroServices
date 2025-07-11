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


    // Create a new quiz
    public ResponseEntity<String> createQuiz(String categoryName, Long numQ, String title){
        List<Long>  questions=quizInterface.getQuestionsForQuiz(categoryName,numQ).getBody();

        Quiz quiz= new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    // Get questions for a quiz by ID
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Long id){
        Quiz quiz= quizRepository.findById(id).get();

        List<Long> questionIds=quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>>  questions=quizInterface.getQuestionsFromId(questionIds);
        return questions;
    }


    // Calculate the result of a quiz based on responses
    public ResponseEntity<Long> calculateResult(Long id, List<Response> responses){
        ResponseEntity<Long> score= quizInterface.getScore(responses);
        return score;
    }

}
