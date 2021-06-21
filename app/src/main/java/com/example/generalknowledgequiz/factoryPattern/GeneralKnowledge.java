package com.example.generalknowledgequiz.factoryPattern;

import com.example.generalknowledgequiz.db.Question;

import java.util.List;

public class GeneralKnowledge implements QuizLevel{
    @Override
    public String difficultyLevel() {
        return "General Quiz";
    }


}
