// com.diaryapp.model.UserDiary.java
package com.diaryapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_diaries")
@IdClass(UserDiaryId.class)
public class UserDiary {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Id
    @Column(name = "diary_id")
    private String diaryId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "diary_id", insertable = false, updatable = false)
    private Diary diary;

    public UserDiary() {}

    public UserDiary(String userId, String diaryId, User user, Diary diary) {
        this.userId = userId;
        this.diaryId = diaryId;
        this.user = user;
        this.diary = diary;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(String diaryId) {
        this.diaryId = diaryId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }

    @Override
    public String toString() {
        return "UserDiary{" +
                "userId='" + userId + '\'' +
                ", diaryId='" + diaryId + '\'' +
                ", user=" + (user != null ? user.getId() : null) +
                ", diary=" + (diary != null ? diary.getId() : null) +
                '}';
    }

    public void setId(UserDiaryId userDiaryId) {
    }
}