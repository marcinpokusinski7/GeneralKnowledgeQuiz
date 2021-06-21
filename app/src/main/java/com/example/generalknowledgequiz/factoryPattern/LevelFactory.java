package com.example.generalknowledgequiz.factoryPattern;

public class LevelFactory {

    public QuizLevel getLevel(int level){
        if(level == 1){
            return new GeneralKnowledge();
        } else if (level == 2) {
            return new FlagQuiz();
        }
        return null;
    }
}
