package com.example.generalknowledgequiz.factoryPattern;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.generalknowledgequiz.QuizCategoriesActivity;
import com.example.generalknowledgequiz.QuizQuestionsActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ProgrammingQuestions implements QuizLevel{


    @Override
    public String difficultyLevel() {
        return "Programming Quiz";
    }

    @Override
    public String lastAttempt() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateCurrent = df.format(c.getTime());
        return "Last attempt: " +dateCurrent;
    }


}
