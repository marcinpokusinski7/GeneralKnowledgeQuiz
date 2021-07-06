package com.example.generalknowledgequiz.adapterPattern;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class LatestScore {
    private String quizType;
    private int score;

    public LatestScore(String quizType, int score) {
        this.quizType = quizType;
        this.score = score;
    }

    public String getQuizType() {
        return quizType;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public ArrayList<LatestScore> createScoreList(int numScore){
        ArrayList<LatestScore> latestScoresList = new ArrayList<LatestScore>();
        latestScoresList.add(new LatestScore("Quiz: " +quizType +" Score: ", score));

        return latestScoresList;
    }
}
