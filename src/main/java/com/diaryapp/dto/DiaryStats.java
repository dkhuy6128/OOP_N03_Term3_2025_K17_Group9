package com.diaryapp.dto;

import com.diaryapp.model.Diary;

import java.time.LocalDate;

public class DiaryStats {
    private String id;
    private String title;
    private LocalDate date;
    private String feeling;
    private Double amount;

    public DiaryStats(Diary diary) {
        this.id = diary.getId();
        this.title = diary.getTitle();
        this.date = diary.getDate();
        this.feeling = diary.getFeeling();
        this.amount = diary.getAmount();
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public LocalDate getDate() { return date; }
    public String getFeeling() { return feeling; }
    public Double getAmount() { return amount; }
}
