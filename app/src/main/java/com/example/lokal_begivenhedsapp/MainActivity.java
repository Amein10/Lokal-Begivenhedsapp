package com.example.lokal_begivenhedsapp;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Event> events = new ArrayList<>();
    ListView eventListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventListView = findViewById(R.id.eventListView);

        // Hardcodede begivenheder
        events.add(new Event(
                "Fællesmøde",
                "12. maj 2026",
                "Møde for alle medlemmer.",
                "Vi holder fællesmøde, hvor vi taler om kommende aktiviteter i foreningen.",
                "https://www.google.com"
        ));

        events.add(new Event(
                "Sommerfest",
                "20. juni 2026",
                "Hyggelig sommerfest.",
                "Foreningen inviterer alle medlemmer til sommerfest med mad, musik og aktiviteter.",
                "https://www.google.com"
        ));

        events.add(new Event(
                "Frivilligdag",
                "5. juli 2026",
                "Hjælp til praktiske opgaver.",
                "Vi mødes og hjælper med oprydning, planlægning og små praktiske opgaver.",
                "https://www.google.com"
        ));

        ArrayList<String> eventTexts = new ArrayList<>();

        for (Event event : events) {
            eventTexts.add(event.name + "\n" + event.date + "\n" + event.shortDescription);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                eventTexts
        );

        eventListView.setAdapter(adapter);

        // Når brugeren klikker på en begivenhed, åbnes detaljeskærmen
        eventListView.setOnItemClickListener((parent, view, position, id) -> {
            Event selectedEvent = events.get(position);

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("name", selectedEvent.name);
            intent.putExtra("date", selectedEvent.date);
            intent.putExtra("fullDescription", selectedEvent.fullDescription);
            intent.putExtra("url", selectedEvent.url);

            startActivity(intent);
        });
    }
}