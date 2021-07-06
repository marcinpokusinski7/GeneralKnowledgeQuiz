package com.example.generalknowledgequiz.adapterPattern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.generalknowledgequiz.R;

import java.util.List;

public class HighscoreActivity extends AppCompatActivity {

    List<LatestScore> scores;
    LatestScore latestScore = new LatestScore("Programming", 3);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        RecyclerView scoresView = findViewById(R.id.rvScores);
        Button back_hc = findViewById(R.id.btn_back_hc);

        scores = latestScore.createScoreList(10);

        scores.add(1, new LatestScore("Programming", 5));

        ScoreAdapter adapter = new ScoreAdapter(scores);

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