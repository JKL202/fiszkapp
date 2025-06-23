package com.example.fiszkapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Informacje o bazie danych
    private static final String DATABASE_NAME = "fiszkapp.db";
    private static final int DATABASE_VERSION = 1;

    // Nazwa tabeli i kolumny
    public static final String TABLE_FLASHCARDS = "flashcards";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_ANSWER = "answer";

    // W klasie DatabaseHelper
    public Cursor getAllFlashcards() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(
                TABLE_FLASHCARDS,
                new String[]{COLUMN_ID, COLUMN_QUESTION, COLUMN_ANSWER},
                null, null, null, null, null
        );
    }

    // Zapytanie tworzące tabelę
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_FLASHCARDS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_QUESTION + " TEXT NOT NULL, " +
                    COLUMN_ANSWER + " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLASHCARDS);
        onCreate(db);
    }

    // Metoda do dodawania fiszki
    public long addFlashcard(Flashcard flashcard) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION, flashcard.getQuestion());
        values.put(COLUMN_ANSWER, flashcard.getAnswer());
        long id = db.insert(TABLE_FLASHCARDS, null, values);
        db.close();
        return id;
    }

}
