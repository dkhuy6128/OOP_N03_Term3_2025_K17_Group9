package com.diaryapp.service;

import com.diaryapp.model.Diary;
import com.diaryapp.model.User;
import com.diaryapp.model.UserDiary;
import com.diaryapp.model.UserDiaryId;
import com.diaryapp.repository.DiaryRepository;
import com.diaryapp.repository.UserDiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryServiceImpl extends DiaryService {
    @Autowired
    private DiaryRepository diaryRepository;
    @Autowired
    private UserDiaryRepository userDiaryRepository;

    @Override
    public void addDiary(String userId, Diary diary) {
        diaryRepository.save(diary);
        UserDiary userDiary = new UserDiary();
        userDiary.setId(new UserDiaryId(userId, diary.getId()));
        userDiary.setUser(new User(userId, null));
        userDiary.setDiary(diary);
        userDiaryRepository.save(userDiary);
    }

    @Override
    public List<Diary> getDiaries(String userId) {
        return userDiaryRepository.findByUserId(userId)
                .stream()
                .map(UserDiary::getDiary)
                .collect(Collectors.toList());
    }

    @Override
    public Diary getById(String userId, String diaryId) {
        return userDiaryRepository.findByUserIdAndDiaryId(userId, diaryId)
                .map(UserDiary::getDiary)
                .orElse(null);
    }

    @Override
    public boolean updateDiary(String userId, String diaryId, Diary updatedDiary) {
        return userDiaryRepository.findByUserIdAndDiaryId(userId, diaryId)
                .map(userDiary -> {
                    Diary existingDiary = userDiary.getDiary();
                    existingDiary.setDate(updatedDiary.getDate());
                    existingDiary.setTitle(updatedDiary.getTitle());
                    existingDiary.setActivity(updatedDiary.getActivity());
                    existingDiary.setAmount(updatedDiary.getAmount());
                    existingDiary.setLocation(updatedDiary.getLocation());
                    existingDiary.setNotes(updatedDiary.getNotes());
                    existingDiary.setFeeling(updatedDiary.getFeeling());
                    existingDiary.setRating(updatedDiary.getRating());
                    existingDiary.setParticipants(updatedDiary.getParticipants());
                    diaryRepository.save(existingDiary);
                    return true;
                }).orElse(false);
    }


    @Override
    public boolean deleteDiary(String userId, String diaryId) {
        return userDiaryRepository.findByUserIdAndDiaryId(userId, diaryId)
                .map(userDiary -> {
                    userDiaryRepository.delete(userDiary);
                    diaryRepository.deleteById(diaryId);
                    return true;
                }).orElse(false);
    }

    @Override
    public void clearAll(String userId) {
        userDiaryRepository.findByUserId(userId)
                .forEach(userDiary -> {
                    diaryRepository.deleteById(userDiary.getDiary().getId());
                    userDiaryRepository.delete(userDiary);
                });
    }
}