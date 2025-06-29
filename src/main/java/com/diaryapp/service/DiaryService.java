package com.diaryapp.service;

import com.diaryapp.model.Diary;
import com.diaryapp.model.User;
import com.diaryapp.model.UserDiary;
import com.diaryapp.repository.DiaryRepository;
import com.diaryapp.repository.UserDiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public abstract class DiaryService {
    @Autowired
    private DiaryRepository diaryRepository;
    @Autowired
    private UserDiaryRepository userDiaryRepository;

    public void addDiary(String userId, Diary diary) {
        diaryRepository.save(diary);
        UserDiary userDiary = new UserDiary();
        userDiary.setUser(new User(userId, null)); // Tạo User tạm với chỉ userId
        userDiary.setDiary(diary);
        userDiaryRepository.save(userDiary);
    }

    public List<Diary> getDiaries(String userId) {
        return userDiaryRepository.findByUserId(userId)
                .stream()
                .map(UserDiary::getDiary)
                .collect(Collectors.toList());
    }

    public Diary getById(String userId, String diaryId) {
        return userDiaryRepository.findByUserIdAndDiaryId(userId, diaryId)
                .map(UserDiary::getDiary)
                .orElse(null);
    }

    public boolean updateDiary(String userId, String diaryId, Diary diary) {
        if (userDiaryRepository.existsByUserIdAndDiaryId(userId, diaryId)) {
            diary.setId(diaryId);
            diaryRepository.save(diary);
        }
        return false;
    }

    public boolean deleteDiary(String userId, String diaryId) {
        userDiaryRepository.findByUserIdAndDiaryId(userId, diaryId)
                .ifPresent(userDiary -> {
                    userDiaryRepository.delete(userDiary); // Xóa mối quan hệ trước
                    diaryRepository.deleteById(diaryId);  // Xóa diary
                });
        return false;
    }

    public abstract void clearAll(String userId);
}