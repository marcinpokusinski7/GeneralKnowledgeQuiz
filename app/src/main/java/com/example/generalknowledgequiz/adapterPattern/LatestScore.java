package com.example.generalknowledgequiz.adapterPattern;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class LatestScore {
    private String quizType;
    private String score;

    public LatestScore(String quizType, String score) {
        this.quizType = quizType;
        this.score = score;
    }
    public LatestScore(){

    }

    public String getQuizType() {
        return quizType;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }


    public ArrayList<LatestScore> createScoreList(int numScore){
        ArrayList<LatestScore> latestScoresList = new ArrayList<LatestScore>();
        latestScoresList.add(new LatestScore("Quiz Type and score "," "));

        return latestScoresList;
    }
}
