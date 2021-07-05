package com.example.generalknowledgequiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private static final String KEY_USER = "user";
    private static final String SHARED_PREF_NAME = "mypref";

    Button btn_start, btn_sign_in;
    FloatingActionButton btn_floating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_sign_in = findViewById(R.id.btn_sign_in);
        btn_start = findViewById(R.id.start);

        TextInputEditText text_start_name = findViewById(R.id.text_start_name);


        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String user = sharedPreferences.getString(KEY_USER, null);


        if (user != null) {
            Intent intent = new Intent(MainActivity.this, QuizCategoriesActivity.class);
            startActivity(intent);
        }


        btn_start.setOnClickListener(view -> {
            String textString = text_start_name.getText().toString();
            if (textString.matches("")) {
                Toast.makeText(MainActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, QuizCategoriesActivity.class);
                intent.putExtra("name", textString);
                startActivity(intent);
                finish();
            }
        });

        btn_sign_in.setOnClickListener(view -> {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.main_activity, new RegisterFragment(), "reg");
            transaction.addToBackStack(null);
            transaction.commit();
        });

    }


}