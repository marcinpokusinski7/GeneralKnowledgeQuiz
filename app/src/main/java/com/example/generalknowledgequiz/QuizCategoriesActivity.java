package com.example.generalknowledgequiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.generalknowledgequiz.builderPattern.User;
import com.example.generalknowledgequiz.factoryPattern.LevelFactory;
import com.example.generalknowledgequiz.factoryPattern.QuizLevel;

public class QuizCategoriesActivity extends AppCompatActivity {
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USER = "user";
    SharedPreferences sharedPreferences;

    TextView difficulty_text, general, flag, programming;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_categories);

        difficulty_text = findViewById(R.id.app_difficulty_text);
        general = findViewById(R.id.difficulty_easy);
        flag = findViewById(R.id.difficulty_average);
        programming = findViewById(R.id.difficulty_programming);

        Button btn_easy = findViewById(R.id.btn_go_easy);

        LevelFactory levelFactory = new LevelFactory();
        QuizLevel quizLevelEasy = levelFactory.getLevel(1);
        QuizLevel quizLevelAverage = levelFactory.getLevel(2);
        QuizLevel quizProgramming = levelFactory.getLevel(3);


        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String user = sharedPreferences.getString(KEY_USER, null);

        difficulty_text.setText("Hello " + user +
                ",\nchoose difficulty");

        general.setText(new StringBuilder().append(quizLevelEasy.difficultyLevel().toString()));
        flag.setText(new StringBuilder().append(quizLevelAverage.difficultyLevel().toString()));
        programming.setText(new StringBuilder().append(quizProgramming.difficultyLevel().toString()));

        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizCategoriesActivity.this, QuizQuestionActivity.class);
                startActivity(intent);

            }
        });
    }
}