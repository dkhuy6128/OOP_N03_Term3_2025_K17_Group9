package model;

import java.time.LocalDate;

public class Diary {
    private String diaryID;
    private LocalDate date;
    private String title;
    private String location;
    private double amountSpent;
    private String feeling;
    private int rating;

    public Diary(String diaryID, LocalDate date, String title, String location, double amountSpent, String feeling, int rating) {
        this.diaryID = diaryID;
        this.date = date;
        this.title = title;
        this.location = location;
        this.amountSpent = amountSpent;
        this.feeling = feeling;
        this.rating = rating;
    }

    public String getDiaryID() {
        return diaryID;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public double getAmountSpent() {
        return amountSpent;
    }

    public String getFeeling() {
        return feeling;
    }

    public int getRating() {
        return rating;
    }
}
