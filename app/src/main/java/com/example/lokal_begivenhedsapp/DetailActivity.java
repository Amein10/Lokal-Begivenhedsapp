package com.example.lokal_begivenhedsapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

// Denne activity viser detaljer om en valgt begivenhed
public class DetailActivity extends AppCompatActivity {

    // TextViews til at vise navn, dato og beskrivelse
    TextView titleTextView, dateTextView, descriptionTextView;

    // Knapper til navigation og handling
    Button backButton, browserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Sætter layoutet for denne skærm
        setContentView(R.layout.activity_detail);

        // Finder UI-elementer fra XML via deres id
        titleTextView = findViewById(R.id.titleTextView);
        dateTextView = findViewById(R.id.dateTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        backButton = findViewById(R.id.backButton);
        browserButton = findViewById(R.id.browserButton);

        // Henter data fra Intent (sendt fra startskærmen)
        String name = getIntent().getStringExtra("name");
        String date = getIntent().getStringExtra("date");
        String fullDescription = getIntent().getStringExtra("fullDescription");
        String url = getIntent().getStringExtra("url");

        // Viser data på skærmen
        titleTextView.setText(name);
        dateTextView.setText(date);
        descriptionTextView.setText(fullDescription);

        // Tilbage-knap: lukker denne skærm og går tilbage til forrige activity
        backButton.setOnClickListener(v -> finish());

        // Browser-knap: åbner begivenhedens link i telefonens browser
        browserButton.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        });
    }
}