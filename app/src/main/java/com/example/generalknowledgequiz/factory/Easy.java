package com.example.generalknowledgequiz.factory;

import com.example.generalknowledgequiz.pojo.Question;

import java.util.List;

public class Easy implements QuizLevel{
    @Override
    public String difficultyLevel() {
        return "Easy";
    }

    public List<Question> getQuestions(List<Question> questionList){
        questionList.add(new Question("Siema", 1 , "A", "B", "C", "D", 2));
        return questionList;
    }
}
