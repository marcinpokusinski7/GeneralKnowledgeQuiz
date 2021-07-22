package com.example.generalknowledgequiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.generalknowledgequiz.adapterPattern.LastScoreActivity;
import com.example.generalknowledgequiz.factoryPattern.LevelFactory;
import com.example.generalknowledgequiz.factoryPattern.QuizLevel;

public class QuizCategoriesActivity extends AppCompatActivity {
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USER = "user";
    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String KEY_PROG = "myKey";
    private static final String KEY_GEN= "myKey1";

    SharedPreferences sharedPreferences;

    Button btn_easy, btn_goPro, btn_go_hc;

    TextView difficulty_text, general, programmingAttempt, generalAttempt, programming;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_categories);

        generalAttempt = findViewById(R.id.attemptGeneral);
        programmingAttempt = findViewById(R.id.attemptProgramming);
        difficulty_text = findViewById(R.id.app_difficulty_text);
        general = findViewById(R.id.difficulty_easy);
        programming = findViewById(R.id.difficulty_programming);

        btn_easy = findViewById(R.id.btn_go_easy);
        btn_goPro = findViewById(R.id.btn_go_program);
        btn_go_hc = findViewById(R.id.btn_go_hc);

        LevelFactory levelFactory = new LevelFactory();
        QuizLevel quizGeneral = levelFactory.getLevel(1);
        QuizLevel quizProgramming = levelFactory.getLevel(3);


        Intent intent = getIntent();

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        programmingAttempt.setText(sharedPreferences.getString(KEY_PROG, ""));
        generalAttempt.setText(sharedPreferences.getString(KEY_GEN, ""));


        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String user = sharedPreferences.getString(KEY_USER, null);
        if(user != null){
            difficulty_text.setText("Hello " + user +
                    ",\nchoose difficulty");
        }else {
            difficulty_text.setText("Hello " + intent.getStringExtra("name") +
                    ",\nchoose difficulty");
        }

        general.setText(new StringBuilder().append(quizGeneral.difficultyLevel().toString()));
        programming.setText(new StringBuilder().append(quizProgramming.difficultyLevel().toString()));


        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString(KEY_GEN, quizGeneral.lastAttempt().toString());
                editor.apply();

                Intent intent = new Intent(QuizCategoriesActivity.this, QuizQuestionsActivity.class);
                intent.putExtra("quiz", 2);
                startActivity(intent);
            }
        });
        btn_goPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString(KEY_PROG, quizGeneral.lastAttempt().toString());
                editor.apply();

                Intent intent = new Intent(QuizCategoriesActivity.this, QuizQuestionsActivity.class);
                intent.putExtra("quiz", 1);
                startActivity(intent);
            }
        });
        btn_go_hc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizCategoriesActivity.this, LastScoreActivity.class);
                startActivity(intent);
            }
        });

    }


}