package com.example.lokal_begivenhedsapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Event> allEvents = new ArrayList<>();
    ArrayList<Event> shownEvents = new ArrayList<>();

    ListView eventListView;
    EditText searchEditText;
    Button searchButton;

    EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventListView = findViewById(R.id.eventListView);
        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);

        // Hardcodede begivenheder
        allEvents.add(new Event(
                "Fællesmøde",
                "12. maj 2026",
                "Møde for alle medlemmer.",
                "Vi holder fællesmøde, hvor vi taler om kommende aktiviteter i foreningen.",
                "https://www.google.com"
        ));

        allEvents.add(new Event(
                "Sommerfest",
                "20. juni 2026",
                "Hyggelig sommerfest.",
                "Foreningen inviterer alle medlemmer til sommerfest med mad, musik og aktiviteter.",
                "https://www.google.com"
        ));

        allEvents.add(new Event(
                "Frivilligdag",
                "5. juli 2026",
                "Hjælp til praktiske opgaver.",
                "Vi mødes og hjælper med oprydning, planlægning og små praktiske opgaver.",
                "https://www.google.com"
        ));

        shownEvents.addAll(allEvents);

        adapter = new EventAdapter(this, shownEvents);
        eventListView.setAdapter(adapter);

        // Søgeknap filtrerer listen efter navn
        searchButton.setOnClickListener(v -> {
            String searchText = searchEditText.getText().toString().toLowerCase();

            shownEvents.clear();

            for (Event event : allEvents) {
                if (event.name.toLowerCase().contains(searchText)) {
                    shownEvents.add(event);
                }
            }

            adapter.notifyDataSetChanged();
        });
    }
}