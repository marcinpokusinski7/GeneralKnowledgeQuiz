package com.example.generalknowledgequiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.generalknowledgequiz.factory.LevelFactory;
import com.example.generalknowledgequiz.factory.QuizLevel;

public class QuizCategoriesActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_categories);

        TextView difficulty_text = findViewById(R.id.app_difficulty_text);
        TextView easy = findViewById(R.id.difficulty_easy);
        TextView average = findViewById(R.id.difficulty_average);
        TextView hard = findViewById(R.id.difficulty_hard);

        Button btn_easy = findViewById(R.id.btn_go_easy);

        LevelFactory levelFactory = new LevelFactory();
        QuizLevel quizLevelEasy = levelFactory.getLevel(1);
        QuizLevel quizLevelAverage = levelFactory.getLevel(2);
        QuizLevel quizLevelHard = levelFactory.getLevel(3);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("name");
        difficulty_text.setText("Choose difficulty, " +userName);

        easy.setText(new StringBuilder().append(quizLevelEasy.difficultyLevel().toString()));
        average.setText(new StringBuilder().append(quizLevelAverage.difficultyLevel().toString()));
        hard.setText(new StringBuilder().append(quizLevelHard.difficultyLevel().toString()));

        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
               fragmentTransaction.replace(R.id.difficulty_activity, new QuizFragment()).commit();
            }
        });
    }
}