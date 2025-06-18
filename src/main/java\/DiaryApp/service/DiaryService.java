package DiaryApp.service;

import DiaryApp.model.Diary;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DiaryService {
    private final Map<String, List<Diary>> diaryData = new HashMap<>();

    public void addDiary(String userId, Diary diary) {
        diaryData.computeIfAbsent(userId, k -> new ArrayList<>()).add(diary);
    }

    public List<Diary> getDiaries(String userId) {
        return diaryData.getOrDefault(userId, Collections.emptyList());
    }
}
