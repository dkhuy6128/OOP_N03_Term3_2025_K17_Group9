package com.diaryapp.repository;

import com.diaryapp.model.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, String> {
}