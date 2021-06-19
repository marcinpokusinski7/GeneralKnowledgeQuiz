package com.example.generalknowledgequiz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.generalknowledgequiz.pojo.Question;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "test.db";
    public static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;


    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;

        final String SQL_CREATE_GENERAL_QUESTIONS = "CREATE TABLE " +
                QuizContract.GeneralQuestions.TABLE_NAME + " ( " +
                QuizContract.GeneralQuestions._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.GeneralQuestions.COLUMN_QUESTION + " TEXT, " +
                QuizContract.GeneralQuestions.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.GeneralQuestions.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.GeneralQuestions.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.GeneralQuestions.COLUMN_OPTION4 + " TEXT, " +
                QuizContract.GeneralQuestions.COLUMN_ANSWER_NR + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_GENERAL_QUESTIONS);
        fillQuestionsQuiz();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.GeneralQuestions.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsQuiz() {
        Question q1 = new Question("A is correct", "A", "B", "C", "D", 1);
        addQuestion(q1);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.GeneralQuestions.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.GeneralQuestions.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.GeneralQuestions.COLUMN_OPTION1, question.getOption2());
        cv.put(QuizContract.GeneralQuestions.COLUMN_OPTION1, question.getOption3());
        cv.put(QuizContract.GeneralQuestions.COLUMN_OPTION1, question.getOption4());
        cv.put(QuizContract.GeneralQuestions.COLUMN_OPTION1, question.getAnswerNr());
        db.insert(QuizContract.GeneralQuestions.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.GeneralQuestions.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.GeneralQuestions.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.GeneralQuestions.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.GeneralQuestions.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.GeneralQuestions.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.GeneralQuestions.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.GeneralQuestions.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
