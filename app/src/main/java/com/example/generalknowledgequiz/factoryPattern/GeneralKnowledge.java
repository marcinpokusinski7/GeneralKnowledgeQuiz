package com.example.generalknowledgequiz.factoryPattern;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.generalknowledgequiz.db.Question;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class GeneralKnowledge implements QuizLevel {
    @Override
    public String difficultyLevel() {
        return "General Quiz";
    }

    @Override
    public String lastAttempt() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        String dateCurrent = df.format(c.getTime());
        return "Last attempt: " +dateCurrent;
    }


}
