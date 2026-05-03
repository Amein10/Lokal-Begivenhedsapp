package com.example.lokal_begivenhedsapp;

// Denne klasse repræsenterer en begivenhed i appen
public class Event {

    // Variabler der indeholder information om begivenheden
    String name;              // Navn på begivenheden
    String date;              // Dato for begivenheden
    String shortDescription;  // Kort beskrivelse (vises i listen)
    String fullDescription;   // Fuld beskrivelse (vises i detaljeskærm)
    String url;               // Link til ekstern side (åbnes i browser)

    // Constructor bruges til at oprette en ny begivenhed med alle værdier
    public Event(String name, String date, String shortDescription, String fullDescription, String url) {
        this.name = name;
        this.date = date;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.url = url;
    }
}