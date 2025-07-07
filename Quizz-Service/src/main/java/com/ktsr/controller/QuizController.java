package com.ktsr.controller;

import com.ktsr.model.QuestionWrapper;
import com.ktsr.model.QuizDto;
import com.ktsr.model.Response;
import com.ktsr.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("quiz")
public class QuizController {

    private final QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNumQuestions(),quizDto.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Long id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Long> submitQuiz(@PathVariable Long id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }
}
