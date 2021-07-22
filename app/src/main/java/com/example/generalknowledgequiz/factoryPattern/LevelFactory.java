package com.example.generalknowledgequiz.factoryPattern;

public class LevelFactory {

    public QuizLevel getLevel(int level) {
        if (level == 1) {
            return new GeneralKnowledgeQuiz();
        } else if (level == 3) {
            return new ProgrammingQuiz();
        }
        return null;
    }
}
