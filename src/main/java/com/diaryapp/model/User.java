package com.diaryapp.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;

    private String name;

    // Quan hệ 1-n với bảng trung gian
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserDiary> userDiaries = new ArrayList<>();

    // Constructors
    public User() {}

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserDiary> getUserDiaries() {
        return userDiaries;
    }

    public void setUserDiaries(List<UserDiary> userDiaries) {
        this.userDiaries = userDiaries;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
