package com.diaryapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class DiaryRequest {
    private LocalDate date;
    private String title;
    private String location;
    private List<String> participants;
    private String activity;
    private double amount;
    private String feeling;
    private int rating;
    private String notes;
}
