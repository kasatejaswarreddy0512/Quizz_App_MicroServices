package com.ktsr.service;

import com.ktsr.model.Question;
import com.ktsr.model.QuestionWrapper;
import com.ktsr.model.Response;
import com.ktsr.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    //add Question
    public Question addQuestion(Question question){
        return questionRepository.save(question);
    }

    //Get All Questions
    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    // Get Question By Category
    public List<Question> getQuestionByCategory(String category){
        return questionRepository.findByCategory(category);
    }

    //Get Question for Quiz
    public List<Long> getQuestionsForQuiz(String category, Long numQuestions) {
        return questionRepository.findRandomQuestionsByCategory(category,numQuestions);
    }


    // Add question from IDs using Question Wrapper Class
    public List<QuestionWrapper> getQuestionFromIds(List<Long> questionsIds) {
        List<QuestionWrapper> wrappers=new ArrayList<>();
        List<Question> questions=new ArrayList<>();

        for(Long id:questionsIds){
            questions.add(questionRepository.findById(id).get());
        }

        for(Question q:questions){
            QuestionWrapper questionWrapper=new QuestionWrapper();
            questionWrapper.setId(q.getId());
            questionWrapper.setQuestionTitle(q.getQuestionTitle());
            questionWrapper.setOption1(q.getOption1());
            questionWrapper.setOption2(q.getOption2());
            questionWrapper.setOption3(q.getOption3());
            questionWrapper.setOption4(q.getOption4());
            wrappers.add(questionWrapper);
        }
        return wrappers;

    }

    public Long getScore(List<Response> responses) {
        long right=0;

        for(Response response:responses){
            Question question=questionRepository.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())){
                right++;
            }
        }
        return right;
    }
}
