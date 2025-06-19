package main.java.diaryapp.dto;

import java.time.LocalDate;
import java.util.List;

public class DiaryRequest {
    public String title;
    public String location;
    public List<String> participants;
    public String activity;
    public double amount;
    public String feeling;
    public double rating;
    public String notes;
    public LocalDate date;
}
