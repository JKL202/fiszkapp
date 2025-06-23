package com.example.fiszkapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class StudyActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private TextView textViewQuestion, textViewAnswer;
    private Button buttonShowAnswer, buttonPrev, buttonNext;
    private Cursor cursor;
    private int totalFlashcards = 0;
    private int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        dbHelper = new DatabaseHelper(this);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewAnswer = findViewById(R.id.textViewAnswer);
        buttonShowAnswer = findViewById(R.id.buttonShowAnswer);
        buttonPrev = findViewById(R.id.buttonPrev);
        buttonNext = findViewById(R.id.buttonNext);

        // Pobierz fiszki z bazy
        cursor = dbHelper.getAllFlashcards();
        totalFlashcards = cursor.getCount();

        if (totalFlashcards == 0) {
            textViewQuestion.setText("Brak fiszek do nauki");
            buttonShowAnswer.setEnabled(false);
            buttonPrev.setEnabled(false);
            buttonNext.setEnabled(false);
            return;
        }

        // Pokaż pierwszą fiszkę
        showFlashcard(0);

        buttonShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewAnswer.setVisibility(View.VISIBLE);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition < totalFlashcards - 1) {
                    currentPosition++;
                    showFlashcard(currentPosition);
                } else {
                    Toast.makeText(StudyActivity.this,
                            "To już ostatnia fiszka",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition > 0) {
                    currentPosition--;
                    showFlashcard(currentPosition);
                } else {
                    Toast.makeText(StudyActivity.this,
                            "To pierwsza fiszka",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showFlashcard(int position) {
        if (cursor.moveToPosition(position)) {
            String question = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_QUESTION));
            String answer = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ANSWER));

            textViewQuestion.setText(question);
            textViewAnswer.setText(answer);
            textViewAnswer.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        cursor.close();
        dbHelper.close();
        super.onDestroy();
    }
}