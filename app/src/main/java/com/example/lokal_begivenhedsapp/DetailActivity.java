package com.example.lokal_begivenhedsapp;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView titleTextView, dateTextView, descriptionTextView;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        titleTextView = findViewById(R.id.titleTextView);
        dateTextView = findViewById(R.id.dateTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        backButton = findViewById(R.id.backButton);

        String name = getIntent().getStringExtra("name");
        String date = getIntent().getStringExtra("date");
        String fullDescription = getIntent().getStringExtra("fullDescription");

        titleTextView.setText(name);
        dateTextView.setText(date);
        descriptionTextView.setText(fullDescription);

        backButton.setOnClickListener(v -> finish());
    }
}