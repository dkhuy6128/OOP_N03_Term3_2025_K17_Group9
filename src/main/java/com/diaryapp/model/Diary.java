package com.diaryapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "diaries")
public class Diary {

    @Id
    private String id;

    private LocalDate date;
    private String title;
    private String location;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "diary_participants", joinColumns = @JoinColumn(name = "diary_id"))
    @Column(name = "participant_name")
    private List<String> participants;

    private String activity;
    private Double amount;
    private String feeling;
    private Integer rating;
    private String notes;

    // Liên kết ngược đến User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Quan hệ đến bảng trung gian UserDiary
    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserDiary> userDiaries = new ArrayList<>();

    // Constructors
    public Diary() {}

    public Diary(String id, LocalDate date, String title, String location, List<String> participants,
                 String activity, Double amount, String feeling, Integer rating, String notes) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.location = location;
        this.participants = participants;
        this.activity = activity;
        this.amount = amount;
        this.feeling = feeling;
        this.rating = rating;
        this.notes = notes;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserDiary> getUserDiaries() {
        return userDiaries;
    }

    public void setUserDiaries(List<UserDiary> userDiaries) {
        this.userDiaries = userDiaries;
    }
}
