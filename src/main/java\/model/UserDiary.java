package model;

public class UserDiary {
    private String userID;  // ID của người dùng
    private String diaryID; // ID của nhật ký
    
    public UserDiary(String userID, String diaryID) {
        this.userID = userID;
        this.diaryID = diaryID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDiaryID() {
        return diaryID;
    }

    public void setDiaryID(String diaryID) {
        this.diaryID = diaryID;
    }
    @Override
    public String toString() {
        return "UserDiary{" +
               "userID='" + userID + '\'' +
               ", diaryID='" + diaryID + '\'' +
               '}';
    }
}
