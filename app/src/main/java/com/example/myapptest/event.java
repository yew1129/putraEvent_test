// File: Event.java
package com.example.myapptest;

public class event {

    String title, venue, description;

    public event(){}
    public event(String title, String venue, String description) {
        this.title = title;
        this.venue = venue;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
