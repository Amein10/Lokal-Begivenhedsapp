package com.example.lokal_begivenhedsapp;

public class Event {
    String name;
    String date;
    String shortDescription;
    String fullDescription;
    String url;

    public Event(String name, String date, String shortDescription, String fullDescription, String url) {
        this.name = name;
        this.date = date;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.url = url;
    }
}