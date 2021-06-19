package com.example.generalknowledgequiz.factory;

public class LevelFactory {

    public QuizLevel getLevel(int level){
        if(level == 1){
            return new Easy();
        } else if (level == 2){
            return new Average();
        } else if (level == 3){
            return new Hard();
        }
        return null;
    }
}
