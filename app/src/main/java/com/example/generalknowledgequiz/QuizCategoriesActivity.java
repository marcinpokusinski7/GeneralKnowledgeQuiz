package com.example.generalknowledgequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.generalknowledgequiz.factory.LevelFactory;
import com.example.generalknowledgequiz.factory.QuizLevel;

public class QuizCategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_categories);

        TextView easy = findViewById(R.id.difficulty_easy);
        TextView average = findViewById(R.id.difficulty_average);
        TextView hard = findViewById(R.id.difficulty_hard);

        LevelFactory levelFactory = new LevelFactory();
        QuizLevel quizLevelEasy = levelFactory.getLevel(1);
        QuizLevel quizLevelAverage = levelFactory.getLevel(2);
        QuizLevel quizLevelHard = levelFactory.getLevel(3);

        easy.setText(new StringBuilder().append(quizLevelEasy.difficultyLevel().toString()));
        average.setText(new StringBuilder().append(quizLevelAverage.difficultyLevel().toString()));
        hard.setText(new StringBuilder().append(quizLevelHard.difficultyLevel().toString()));

    }
}