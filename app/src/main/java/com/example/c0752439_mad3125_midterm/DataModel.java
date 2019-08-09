package com.example.c0752439_mad3125_midterm;

public class DataModel {
    String title, launchYear;
    String imageUrl;

    public DataModel(String title, String launchYear, String imageUrl) {
        this.title = title;
        this.launchYear = launchYear;
        this.imageUrl = imageUrl;
    }

    public DataModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLaunchYear() {
        return launchYear;
    }

    public void setLaunchYear(String launchYear) {
        this.launchYear = launchYear;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}