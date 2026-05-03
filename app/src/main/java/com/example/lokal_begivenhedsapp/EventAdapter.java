package com.example.lokal_begivenhedsapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

// Adapteren forbinder data (Event-objekter) med ListView og bestemmer hvordan hvert element vises
public class EventAdapter extends BaseAdapter {

    Context context;              // Bruges til at starte aktiviteter og lave dialoger
    ArrayList<Event> events;      // Liste med begivenheder

    // Constructor modtager context og liste af events
    public EventAdapter(Context context, ArrayList<Event> events) {
        this.context = context;
        this.events = events;
    }

    // Returnerer antal elementer i listen
    @Override
    public int getCount() {
        return events.size();
    }

    // Returnerer et bestemt element fra listen
    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    // Returnerer id (her bruger vi bare position)
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Denne metode laver layoutet for hver begivenhed i listen
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Henter den aktuelle begivenhed
        Event event = events.get(position);

        // Hvis view ikke findes, oprettes et nyt fra XML layout
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.event_item, parent, false);
        }

        // Finder UI-elementer i layoutet
        TextView nameTextView = convertView.findViewById(R.id.eventNameTextView);
        TextView dateTextView = convertView.findViewById(R.id.eventDateTextView);
        TextView shortDescriptionTextView = convertView.findViewById(R.id.eventShortDescriptionTextView);

        Button detailsButton = convertView.findViewById(R.id.detailsButton);
        Button signupButton = convertView.findViewById(R.id.signupButton);

        // Sætter data ind i UI-elementerne
        nameTextView.setText(event.name);
        dateTextView.setText(event.date);
        shortDescriptionTextView.setText(event.shortDescription);

        // Når man trykker på "Detaljer", åbnes DetailActivity med data fra den valgte begivenhed
        detailsButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("name", event.name);
            intent.putExtra("date", event.date);
            intent.putExtra("fullDescription", event.fullDescription);
            intent.putExtra("url", event.url);

            context.startActivity(intent);
        });

        // Når man trykker på "Tilmeld", vises en dialogboks med bekræftelse
        signupButton.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Tilmelding")
                    .setMessage("Er du sikker på, du vil tilmelde dig " + event.name + "?")
                    .setPositiveButton("Ja", null)
                    .setNegativeButton("Nej", null)
                    .show();
        });

        return convertView;
    }
}