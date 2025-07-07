package com.ktsr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizDto {

    private String categoryName;
    private Long numQuestions;
    private String title;

}
