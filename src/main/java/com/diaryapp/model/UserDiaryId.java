// com.diaryapp.model.UserDiaryId.java (giữ nguyên, thêm @Embeddable)
package com.diaryapp.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UserDiaryId implements Serializable {
    private String userId;
    private String diaryId;

    public UserDiaryId() {}

    public UserDiaryId(String userId, String diaryId) {
        this.userId = userId;
        this.diaryId = diaryId;
    }

    // Getters, setters, equals, and hashCode (giữ nguyên)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDiaryId that = (UserDiaryId) o;
        return userId.equals(that.userId) && diaryId.equals(that.diaryId);
    }

    @Override
    public int hashCode() {
        return 31 * userId.hashCode() + diaryId.hashCode();
    }
}