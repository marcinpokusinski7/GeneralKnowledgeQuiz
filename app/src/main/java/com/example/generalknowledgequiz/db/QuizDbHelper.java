package com.example.generalknowledgequiz.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.generalknowledgequiz.db.QuizContract.RandomQuestions;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "testd.db";
    private static final int DATABASE_VERSION = 1;
    //signleton
    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    //signleton
    private QuizDbHelper(Context context) { // to not make any new objects, just to return the same
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());//for whole app
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;


        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                QuizContract.CategoriesTable.TABLE_NAME + "( " +
                QuizContract.CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                RandomQuestions.TABLE_NAME + " ( " +
                RandomQuestions._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RandomQuestions.COLUMN_QUESTION + " TEXT, " +
                RandomQuestions.COLUMN_OPTION1 + " TEXT, " +
                RandomQuestions.COLUMN_OPTION2 + " TEXT, " +
                RandomQuestions.COLUMN_OPTION3 + " TEXT, " +
                RandomQuestions.COLUMN_OPTION4 + " TEXT, " +
                RandomQuestions.COLUMN_ANSWER_NR + " INTEGER, " +
                RandomQuestions.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + RandomQuestions.COLUMN_CATEGORY_ID + ") REFERENCES " +
                QuizContract.CategoriesTable.TABLE_NAME + "(" + QuizContract.CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RandomQuestions.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("Programming");
        addCategory(c1);
        Category c2 = new Category("General");
        addCategory(c2);
    }

    private void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(QuizContract.CategoriesTable.TABLE_NAME, null, cv);
    }


    private void fillQuestionsTable() {
        Question q1 = new Question("What is the capital of Poland?", "Cracow", "Warsaw", "Wroclaw", "Gdansk", 2, Category.GENERAL);
        addQuestion(q1);
        Question q2 = new Question("How many times has Andy Murray won Wimbledon playing singles?", "4", "3", "2", "1", 3, Category.GENERAL);
        addQuestion(q2);
        Question q3 = new Question("What does IPA stand for?", "International Police Association", "International Phonetic Alphabet", "Indian Pale Ale", "Isopropylic Alcohol", 3, Category.GENERAL);
        addQuestion(q3);
        Question q4 = new Question("Who painted the Mona Lisa?", "Hieronim Bosch", "Claude Monet", "Franz Schubert", "Leonardo da Vinci", 4, Category.GENERAL);
        addQuestion(q4);
        Question q5 = new Question("Which British actor will play Batman in the upcoming reboot directed by Matt Reeves?", "Hanz Zimmer", "Robert Pattinson", "Arnold Schwarzenegger", "Jean Claude Van Damme", 2, Category.GENERAL);
        addQuestion(q5);
        Question q6 = new Question("Which British actor will play Batman in the upcoming reboot directed by Matt Reeves?", "Hanz Zimmer", "Robert Pattinson", "Arnold Schwarzenegger", "Jean Claude Van Damme", 2, Category.PROGRAMMING);
        addQuestion(q6);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(RandomQuestions.COLUMN_QUESTION, question.getQuestion());
        cv.put(RandomQuestions.COLUMN_OPTION1, question.getOption1());
        cv.put(RandomQuestions.COLUMN_OPTION2, question.getOption2());
        cv.put(RandomQuestions.COLUMN_OPTION3, question.getOption3());
        cv.put(RandomQuestions.COLUMN_OPTION4, question.getOption4());
        cv.put(RandomQuestions.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(RandomQuestions.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(RandomQuestions.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.CategoriesTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(QuizContract.CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(QuizContract.CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }
        c.close();
        return categoryList;
    }


    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + RandomQuestions.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(RandomQuestions._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(RandomQuestions.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(RandomQuestions.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(RandomQuestions.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(RandomQuestions.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(RandomQuestions.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(RandomQuestions.COLUMN_ANSWER_NR)));
                question.setCategoryID(c.getInt(c.getColumnIndex(RandomQuestions.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        String selection = RandomQuestions.COLUMN_CATEGORY_ID;
        String[] selectionArgs = new String[]{String.valueOf(categoryID)};
        Cursor c = db.rawQuery("SELECT * FROM " + RandomQuestions.TABLE_NAME +
                " WHERE " + RandomQuestions.COLUMN_CATEGORY_ID + " = ?", selectionArgs);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(RandomQuestions._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(RandomQuestions.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(RandomQuestions.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(RandomQuestions.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(RandomQuestions.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(RandomQuestions.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(RandomQuestions.COLUMN_ANSWER_NR)));
                question.setCategoryID(c.getInt(c.getColumnIndex(RandomQuestions.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
