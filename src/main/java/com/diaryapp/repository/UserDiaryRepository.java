package com.diaryapp.repository;

import com.diaryapp.model.UserDiary;
import com.diaryapp.model.UserDiaryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserDiaryRepository extends JpaRepository<UserDiary, UserDiaryId> {
    @Query("SELECT ud FROM UserDiary ud WHERE ud.user.id = :userId")
    List<UserDiary> findByUserId(String userId);

    @Query("SELECT ud FROM UserDiary ud WHERE ud.user.id = :userId AND ud.diary.id = :diaryId")
    Optional<UserDiary> findByUserIdAndDiaryId(String userId, String diaryId);

    @Query("SELECT COUNT(ud) > 0 FROM UserDiary ud WHERE ud.user.id = :userId AND ud.diary.id = :diaryId")
    boolean existsByUserIdAndDiaryId(String userId, String diaryId);
}