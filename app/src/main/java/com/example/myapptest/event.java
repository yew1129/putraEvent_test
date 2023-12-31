// File: Event.java
package com.example.myapptest;

public class event {

    String title, date, description, image;

    public event(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

//    public String getDes() {
//        return des;
//    }
//
//    public void setDes(String des) {
//        this.des = des;
//    }

    public event(String title, String date, String description, String des, String image) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.image = image;
//        this.des =des;
    }



}
