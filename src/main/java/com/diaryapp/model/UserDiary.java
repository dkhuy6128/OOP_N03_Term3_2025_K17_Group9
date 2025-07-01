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
    public String getUserId() {
        return userId;
    }
    public String getDiaryId() {
        return diaryId;
    }
    public User getUser() {
        return user;
    }
    public Diary getDiary() {
        return diary;
    }
    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            this.userId = user.getId();
        }
    }
    public void setDiary(Diary diary) {
        this.diary = diary;
        if (diary != null) {
            this.diaryId = diary.getId();
        }
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
