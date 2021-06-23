package com.example.generalknowledgequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.generalknowledgequiz.db.Question;
import com.example.generalknowledgequiz.db.QuizDbHelper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class QuizQuestionActivity extends AppCompatActivity {

    private TextView tv_question;
    private TextView tv_score;
    private ProgressBar progressBar;
    private TextView textViewQuestioncount;
    private RadioGroup rbGroup;
    private RadioButton option1;
    private RadioButton option2;
    private RadioButton option3;
    private RadioButton option4;
    private Button btn_submit;
    private ColorStateList textColorDefaultRb;
    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    private int score;
    private boolean answered;
    private boolean tvSelected = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);



        tv_question = findViewById(R.id.tv_question);
        tv_score = findViewById(R.id.tv_score);
        textViewQuestioncount = findViewById(R.id.tv_progress);
        progressBar = findViewById(R.id.progress_bar);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        rbGroup = findViewById(R.id.radio_group);


        btn_submit = findViewById(R.id.btn_submit);
        textColorDefaultRb = option1.getTextColors();

        QuizDbHelper dbHelper = new QuizDbHelper(QuizQuestionActivity.this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (option1.isChecked() || option2.isChecked() || option3.isChecked() || option4.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizQuestionActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {
        option1.setTextColor(textColorDefaultRb);
        option2.setTextColor(textColorDefaultRb);
        option3.setTextColor(textColorDefaultRb);
        option4.setTextColor(textColorDefaultRb);

        rbGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            tv_question.setText(currentQuestion.getQuestion());
            option1.setText(currentQuestion.getOption1());
            option2.setText(currentQuestion.getOption2());
            option3.setText(currentQuestion.getOption3());
            option4.setText(currentQuestion.getOption4());

            questionCounter++;
            textViewQuestioncount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            btn_submit.setText("Confirm");
        } else {
            finishQuiz();
        }
    }

    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;
        if (answerNr == currentQuestion.getAnswerNr()) {
            score++;
            tv_score.setText("Score: " + score);
        }
        showSolution();
    }

    private void showSolution() {
        option1.setTextColor(Color.RED);
        option2.setTextColor(Color.RED);
        option3.setTextColor(Color.RED);
        option4.setTextColor(Color.RED);
        System.out.println("-----------------------------------------------"+currentQuestion.getAnswerNr());
        switch (currentQuestion.getAnswerNr()) {
            case 1:
                option1.setTextColor(Color.GREEN);
                tv_question.setText("Answer 1 is correct");
                break;
            case 2:
                option2.setTextColor(Color.GREEN);
                tv_question.setText("Answer 2 is correct");
                break;
            case 3:
                option3.setTextColor(Color.GREEN);
                tv_question.setText("Answer 3 is correct");
                break;
            case 4:
                option4.setTextColor(Color.GREEN);
                tv_question.setText("Answer 4 is correct");
                break;
        }
        if (questionCounter < questionCountTotal) {
            btn_submit.setText("Next");
        } else {
            btn_submit.setText("Finish");
        }
    }

    private void finishQuiz() {
       finish();
    }

}