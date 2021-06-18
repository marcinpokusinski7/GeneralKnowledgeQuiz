package com.example.generalknowledgequiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_start = findViewById(R.id.start);
        TextInputEditText text_start_name = findViewById(R.id.text_start_name);

        View decorView = getWindow().getDecorView(); // Hide system ui bar
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
        );


        btn_start.setOnClickListener(view -> {
           String textString = text_start_name.getText().toString();
            if (textString.matches("")) {
                Toast.makeText(MainActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                System.out.println("Dziala");
            } else {
                Intent intent = new Intent(this, QuizCategoriesActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}