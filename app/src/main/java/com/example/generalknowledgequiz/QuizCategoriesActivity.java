package com.example.generalknowledgequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.generalknowledgequiz.factoryPattern.LevelFactory;
import com.example.generalknowledgequiz.factoryPattern.QuizLevel;

public class QuizCategoriesActivity extends AppCompatActivity {
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USER = "user";
    private static final int REQUEST_CODE_QUIZ = 1;
    SharedPreferences sharedPreferences;

    Button btn_easy, btn_goPro;

    TextView difficulty_text, general, flag, programming;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_categories);

        difficulty_text = findViewById(R.id.app_difficulty_text);
        general = findViewById(R.id.difficulty_easy);
        programming = findViewById(R.id.difficulty_programming);

        btn_easy = findViewById(R.id.btn_go_easy);
        btn_goPro = findViewById(R.id.btn_go_program);

        LevelFactory levelFactory = new LevelFactory();
        QuizLevel quizLevelEasy = levelFactory.getLevel(1);
        QuizLevel quizProgramming = levelFactory.getLevel(3);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String user = sharedPreferences.getString(KEY_USER, null);



        difficulty_text.setText("Hello " + user +
                ",\nchoose difficulty");

        general.setText(new StringBuilder().append(quizLevelEasy.difficultyLevel().toString()));
        programming.setText(new StringBuilder().append(quizProgramming.difficultyLevel().toString()));

        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizCategoriesActivity.this, QuizQuestionsActivity.class);
                intent.putExtra("quiz", 2);
                startActivity(intent);
            }
        });
        btn_goPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizCategoriesActivity.this, QuizQuestionsActivity.class);
                intent.putExtra("quiz", 1);
                startActivity(intent);
            }
        });
    }
}