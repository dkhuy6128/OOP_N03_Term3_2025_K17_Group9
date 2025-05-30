package DiaryApp;

import model.User;
import model.Diary;
import model.UserDiary;
import java.util.*;

public class DiaryApp {
    Map<String, User> users = new HashMap<>();
    Map<String, Diary> diaries = new HashMap<>();
    Map<String, List<String>> userDiaries = new HashMap<>(); // userID -> list of diaryIDs

    public void addUser(String userID, String name) {
        users.put(userID, new User(userID, name));
    }

    public void addDiary(String userID, Diary diary) {
        diaries.put(diary.getDiaryID(), diary);
        userDiaries.computeIfAbsent(userID, k -> new ArrayList<>()).add(diary.getDiaryID());
    }

    public List<Diary> getUserDiaries(String userID) {
        List<String> diaryIDs = userDiaries.getOrDefault(userID, new ArrayList<>());
        List<Diary> result = new ArrayList<>();
        for (String id : diaryIDs) {
            result.add(diaries.get(id));
        }
        return result;
    }

    public double getTotalSpent(String userID) {
        return getUserDiaries(userID).stream()
                .mapToDouble(d -> d.getAmountSpent())
                .sum();
    }

    public double getAverageRating(String userID) {
        List<Diary> userDiaryList = getUserDiaries(userID);
        return userDiaryList.stream()
                .mapToInt(d -> d.getRating())
                .average()
                .orElse(0);
    }

    public List<Diary> getDiariesByUser(String userID) {
        return getUserDiaries(userID);
    }    
}

