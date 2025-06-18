package model;

public class User {
    private String name;
    private String userID;

    public User(String name, String userID) {
        this.name = name;
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUser(String name, String userID) {
        this.name = name;
        this.userID = userID;
    }

    public String toString() {
        return name + userID;
    }
}
