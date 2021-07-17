package com.example.generalknowledgequiz.factoryPattern;

import android.content.Context;
import android.widget.TextView;

public class LevelFactory {

    public QuizLevel getLevel(int level) {
        if (level == 1) {
            return new GeneralKnowledge();
        } else if (level == 3) {
            return new ProgrammingQuestions();
        }
        return null;
    }
}
