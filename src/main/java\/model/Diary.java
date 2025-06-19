package DiaryApp.model;

import java.time.LocalDate;
import java.util.List;

public class Diary {
    private String diaryID;
    private LocalDate date;
    private String title;
    private String location;
    private List<String> people;
    private String activityType;
    public Double amountSpent;
    private String feeling;
    private int rating;
    private String notes;

    public Diary(String diaryID, LocalDate date, String title, String location, List<String> people,
                 String activityType, Double amountSpent, String feeling, int rating, String notes) {
        this.diaryID = diaryID;
        this.date = date;
        this.title = title;
        this.location = location;
        this.people = people;
        this.activityType = activityType;
        this.amountSpent = amountSpent;
        this.feeling = feeling;
        this.rating = rating;
        this.notes = notes;
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

    public List<String> getPeople() {
        return people;
    }

    public String getActivityType() {
        return activityType;
    }

    public Double getAmountSpent() {
        return amountSpent;
    }

    public String getFeeling() {
        return feeling;
    }

    public int getRating() {
        return rating;
    }

    public String getNotes() {
        return notes;
    }
}
