package com.example.generalknowledgequiz.database;

import android.provider.BaseColumns;

public final class QuizContract {
    public static class GeneralQuestions implements BaseColumns {
        public static final String TABLE_NAME = "general_questions";
        public static final String COLUMN_NAME_TITLE = "general_questions";
        public static final String COLUMN_NAME_SUBTITLE = "general_questions_two";
    }

    private QuizContract() {
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + GeneralQuestions.TABLE_NAME + " (" +
                    GeneralQuestions._ID + " INTEGER PRIMARY KEY," +
                    GeneralQuestions.COLUMN_NAME_TITLE + " TEXT," +
                    GeneralQuestions.COLUMN_NAME_SUBTITLE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + GeneralQuestions.TABLE_NAME;


}
