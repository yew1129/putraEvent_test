// File: Event.java
package com.example.myapptest;

public class event {

    String title, venue, description, image;

    public event(){}
    public event(String title, String venue, String description, String image) {
        this.title = title;
        this.venue = venue;
        this.description = description;
        this.image = image;
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

    public String getImageUrl() {
        return image;
    }

    public void setImageUrl(String image) {
        this.image = image;
    }
}
