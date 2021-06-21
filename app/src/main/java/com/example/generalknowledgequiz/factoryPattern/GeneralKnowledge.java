package com.example.generalknowledgequiz.factoryPattern;

import com.example.generalknowledgequiz.pojo.Question;

import java.util.List;

public class GeneralKnowledge implements QuizLevel{
    @Override
    public String difficultyLevel() {
        return "General Quiz";
    }

    public List<Question> getQuestions(List<Question> questionList){
        questionList.add(new Question("Siema", 1 , "A", "B", "C", "D", 2));
        return questionList;
    }
}
