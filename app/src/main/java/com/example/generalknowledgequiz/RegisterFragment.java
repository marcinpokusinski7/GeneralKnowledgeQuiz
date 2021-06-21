package com.example.generalknowledgequiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.generalknowledgequiz.builderPattern.User;

public class RegisterFragment extends Fragment {
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USER = "user";

    EditText login, password, email;
    Button signIn;

    SharedPreferences sharedPreferences;


    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_register, container, false);

        login = view.findViewById(R.id.text_login);
        password = view.findViewById(R.id.text_password);
        email = view.findViewById(R.id.text_email);
        signIn = view.findViewById(R.id.btn_signin);

        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (login.getText().toString().isEmpty() || login.getText().toString().isEmpty() || login.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Enter data", Toast.LENGTH_SHORT).show();

                } else {
                    User user = new User.Builder()
                            .setLogin(login.getText().toString())
                            .setPassword(password.getText().toString())
                            .setEmail(email.getText().toString())
                            .create();
                    editor.putString(KEY_USER, user.toString());
                    editor.apply();
                    Intent intent = new Intent(getActivity(), QuizCategoriesActivity.class);
                    startActivity(intent);
                }

            }
        });


        return view;
    }
}