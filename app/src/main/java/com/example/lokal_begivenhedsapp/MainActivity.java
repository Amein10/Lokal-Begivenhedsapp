package com.example.lokal_begivenhedsapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

// MainActivity er appens startskærm, hvor alle begivenheder vises i en liste
public class MainActivity extends AppCompatActivity {

    // Liste med alle begivenheder (hardcoded data)
    ArrayList<Event> allEvents = new ArrayList<>();

    // Liste der vises på skærmen (bruges til søgning/filtrering)
    ArrayList<Event> shownEvents = new ArrayList<>();

    // UI-elementer fra layoutet
    ListView eventListView;
    EditText searchEditText;
    Button searchButton;

    // Adapter der forbinder data med ListView
    EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Sætter layoutet for startskærmen
        setContentView(R.layout.activity_main);

        // Finder UI-elementer fra XML via deres id
        eventListView = findViewById(R.id.eventListView);
        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);

        // Opretter hardcodede begivenheder
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

        // Kopierer alle events til listen der vises
        shownEvents.addAll(allEvents);

        // Opretter adapter og kobler den til ListView
        adapter = new EventAdapter(this, shownEvents);
        eventListView.setAdapter(adapter);

        // Når brugeren trykker på søg, filtreres listen efter navn
        searchButton.setOnClickListener(v -> {
            String searchText = searchEditText.getText().toString().toLowerCase();

            // Tømmer listen
            shownEvents.clear();

            // Tilføjer kun events der matcher søgeteksten
            for (Event event : allEvents) {
                if (event.name.toLowerCase().contains(searchText)) {
                    shownEvents.add(event);
                }
            }

            // Opdaterer listen på skærmen
            adapter.notifyDataSetChanged();
        });

        // Når man klikker på et element i listen, åbnes detaljeskærmen
        eventListView.setOnItemClickListener((parent, view, position, id) -> {

            // Finder den valgte begivenhed
            Event selectedEvent = shownEvents.get(position);

            // Opretter intent og sender data videre til DetailActivity
            android.content.Intent intent = new android.content.Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("name", selectedEvent.name);
            intent.putExtra("date", selectedEvent.date);
            intent.putExtra("fullDescription", selectedEvent.fullDescription);
            intent.putExtra("url", selectedEvent.url);

            // Starter detaljeskærmen
            startActivity(intent);
        });
    }
}