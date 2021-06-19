package com.example.generalknowledgequiz.factory;

import com.example.generalknowledgequiz.pojo.Question;

public class Easy implements QuizLevel{
    @Override
    public String difficultyLevel() {
        return "Easy";
    }
}
