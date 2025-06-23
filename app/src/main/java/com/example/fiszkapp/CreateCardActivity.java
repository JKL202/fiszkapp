package com.example.fiszkapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CreateCardActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);

        // Inicjalizacja bazy danych
        dbHelper = new DatabaseHelper(this);

        EditText editTextQuestion = findViewById(R.id.editTextQuestion);
        EditText editTextAnswer = findViewById(R.id.editTextAnswer);
        Button buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = editTextQuestion.getText().toString().trim();
                String answer = editTextAnswer.getText().toString().trim();

                if(question.isEmpty() || answer.isEmpty()) {
                    Toast.makeText(CreateCardActivity.this,
                            "Wypełnij oba pola!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Utwórz obiekt fiszki i zapisz do bazy
                Flashcard flashcard = new Flashcard(question, answer);
                long id = dbHelper.addFlashcard(flashcard);

                if(id != -1) {
                    Toast.makeText(CreateCardActivity.this,
                            "Fiszka zapisana! ID: " + id,
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(CreateCardActivity.this,
                            "Błąd zapisu fiszki",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}