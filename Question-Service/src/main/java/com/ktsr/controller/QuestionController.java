package com.ktsr.controller;

import com.ktsr.model.Question;
import com.ktsr.model.QuestionWrapper;
import com.ktsr.model.Response;
import com.ktsr.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("question")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    Environment environment;

    @PostMapping("add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
    }

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return ResponseEntity.ok(questionService.getQuestionByCategory(category));
    }

    @GetMapping("generate")
    public ResponseEntity<List<Long>> getQuestionsForQuiz
            (@RequestParam String categoryName, @RequestParam Long numQuestions){
        return ResponseEntity.ok(questionService.getQuestionsForQuiz(categoryName,numQuestions).getBody());
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Long> questionsIds){
        System.out.println(environment.getProperty("local.server.port"));
        return ResponseEntity.ok(questionService.getQuestionFromIds(questionsIds));
    }

    @PostMapping("getScore")
    public ResponseEntity<Long> getScore(@RequestBody List<Response> responses){
        return ResponseEntity.ok(questionService.getScore(responses));
    }

    

}
