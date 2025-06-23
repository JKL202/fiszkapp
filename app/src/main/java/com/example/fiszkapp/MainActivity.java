package com.example.fiszkapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ustawia nasz layout

        // 1. Znajdź przyciski w layoutcie
        Button createButton = findViewById(R.id.btn_create);
        Button studyButton = findViewById(R.id.btn_study);

        // 2. Ustaw działanie dla przycisku tworzenia fiszek
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Przejdź do ekranu tworzenia fiszek
                Intent intent = new Intent(MainActivity.this, CreateCardActivity.class);
                startActivity(intent);
            }
        });

        // 3. Ustaw działanie dla przycisku nauki
        studyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Przejdź do trybu nauki
                Intent intent = new Intent(MainActivity.this, StudyActivity.class);
                startActivity(intent);
            }
        });
    }
}