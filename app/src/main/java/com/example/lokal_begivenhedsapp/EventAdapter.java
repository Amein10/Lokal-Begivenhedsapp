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

public class EventAdapter extends BaseAdapter {

    Context context;
    ArrayList<Event> events;

    public EventAdapter(Context context, ArrayList<Event> events) {
        this.context = context;
        this.events = events;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Denne metode laver layoutet for hver begivenhed i listen
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Event event = events.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.event_item, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.eventNameTextView);
        TextView dateTextView = convertView.findViewById(R.id.eventDateTextView);
        TextView shortDescriptionTextView = convertView.findViewById(R.id.eventShortDescriptionTextView);

        Button detailsButton = convertView.findViewById(R.id.detailsButton);
        Button signupButton = convertView.findViewById(R.id.signupButton);

        nameTextView.setText(event.name);
        dateTextView.setText(event.date);
        shortDescriptionTextView.setText(event.shortDescription);

        detailsButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("name", event.name);
            intent.putExtra("date", event.date);
            intent.putExtra("fullDescription", event.fullDescription);
            intent.putExtra("url", event.url);

            context.startActivity(intent);
        });

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