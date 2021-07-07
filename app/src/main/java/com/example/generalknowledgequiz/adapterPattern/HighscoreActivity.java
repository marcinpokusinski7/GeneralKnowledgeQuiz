package com.example.generalknowledgequiz.adapterPattern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.generalknowledgequiz.QuizCategoriesActivity;
import com.example.generalknowledgequiz.QuizQuestionsActivity;
import com.example.generalknowledgequiz.R;

import java.util.List;

public class HighscoreActivity extends AppCompatActivity {
    private static final String SHARED_PREF_SCORE = "latestScore";
    private static final String SHARED_PREF_QUIZ_TYPE = "quizType";
    SharedPreferences sharedPreferences;

    List<LatestScore> scores;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        RecyclerView scoresView = findViewById(R.id.rvScores);
        Button back_hc = findViewById(R.id.btn_back_hc);

        sharedPreferences = getSharedPreferences(SHARED_PREF_QUIZ_TYPE, MODE_PRIVATE);
        sharedPreferences = getSharedPreferences(SHARED_PREF_SCORE, MODE_PRIVATE);
        String score = sharedPreferences.getString(SHARED_PREF_SCORE, null);

        scores = new LatestScore().createScoreList(10);



        ScoreAdapter adapter = new ScoreAdapter(scores);
        scores.add(1, new LatestScore(sharedPreferences.getString(SHARED_PREF_QUIZ_TYPE, null)+": ", score));

        scoresView.setAdapter(adapter);

        scoresView.setLayoutManager(new LinearLayoutManager(this));

        back_hc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}