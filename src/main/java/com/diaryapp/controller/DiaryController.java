package com.diaryapp.controller;

import com.diaryapp.model.Diary;
import com.diaryapp.model.User;
import com.diaryapp.model.UserDiary;
import com.diaryapp.service.DiaryService;
import com.diaryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/diaries")
public class DiaryController {

    private final DiaryService diaryService;
    @Autowired
    private UserService userService;


    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping("/{userId}")
    public String list(@PathVariable String userId, Model model) {
        List<Diary> diaries = diaryService.getDiaries(userId);
        model.addAttribute("diaries", diaries);
        model.addAttribute("userId", userId);
        return "diary_list";
    }

    @GetMapping("/{userId}/add")
    public String addForm(@PathVariable String userId, Model model) {
        model.addAttribute("diary", new Diary());
        model.addAttribute("userId", userId);
        return "diary_form";
    }

    @PostMapping("/{userId}/save")
    public String saveDiary(@PathVariable String userId,
                            @ModelAttribute Diary diary,
                            @RequestParam(required = false) String participants) {

        // Validate
        if (diary.getDate() == null || diary.getTitle() == null || diary.getTitle().isEmpty()) {
            return "redirect:/diaries/" + userId + "/add?error=missing_fields";
        }

        // Lấy user
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isEmpty()) {
            return "redirect:/users?error=user_not_found";
        }
        User user = optionalUser.get();
        diary.setUser(user);
        // Gán ID cho diary
        diary.setId(UUID.randomUUID().toString());

        // Gán participants
        if (participants != null && !participants.isEmpty()) {
            diary.setParticipants(Arrays.asList(participants.split("\\s*,\\s*")));
        } else {
            diary.setParticipants(Collections.emptyList());
        }

        // Tạo đối tượng trung gian UserDiary
        UserDiary userDiary = new UserDiary();
        userDiary.setUser(user);
        userDiary.setDiary(diary);

        // Thiết lập liên kết hai chiều
        diary.setUserDiaries(List.of(userDiary));
        user.setUserDiaries(List.of(userDiary));

        // Lưu
        diaryService.addDiary(userId, diary); // Hoặc diaryRepository.save(diary);

        return "redirect:/diaries/" + userId;
    }


    @GetMapping("/{userId}/edit/{diaryId}")
    public String editForm(@PathVariable String userId, @PathVariable String diaryId, Model model) {
        Diary diary = diaryService.getById(userId, diaryId);
        if (diary == null) {
            return "redirect:/diaries/" + userId + "?error=not_found";
        }
        model.addAttribute("diary", diary);
        model.addAttribute("userId", userId);
        return "diary_form";
    }

    @PostMapping("/{userId}/update")
    public String updateDiary(@PathVariable String userId, @ModelAttribute Diary diary, @RequestParam(required = false) String participants) {
        if (diary.getDate() == null || diary.getTitle() == null || diary.getTitle().isEmpty()) {
            return "redirect:/diaries/" + userId + "/edit/" + diary.getId() + "?error=missing_fields";
        }
        // Xử lý danh sách participants
        if (participants != null && !participants.isEmpty()) {
            diary.setParticipants(Arrays.asList(participants.split("\\s*,\\s*")));
        } else {
            diary.setParticipants(Collections.emptyList());
        }
        diaryService.updateDiary(userId, diary.getId(), diary);
        return "redirect:/diaries/" + userId;
    }

    @GetMapping("/{userId}/delete/{diaryId}")
    public String deleteDiary(@PathVariable String userId, @PathVariable String diaryId) {
        diaryService.deleteDiary(userId, diaryId);
        return "redirect:/diaries/" + userId;
    }

    @GetMapping("/{userId}/search")
    public String searchDiaries(@PathVariable String userId,
                                @RequestParam(required = false) String keyword,
                                @RequestParam(required = false) String feeling,
                                @RequestParam(required = false) Integer rating,
                                @RequestParam(required = false) String sort,
                                Model model) {

        List<Diary> diaries = diaryService.getDiaries(userId);

        // Lọc theo từ khóa
        if (keyword != null && !keyword.isEmpty()) {
            String lowered = keyword.toLowerCase();
            diaries = diaries.stream()
                    .filter(d ->
                            Optional.ofNullable(d.getTitle()).orElse("").toLowerCase().contains(lowered)
                                    || Optional.ofNullable(d.getActivity()).orElse("").toLowerCase().contains(lowered)
                    )
                    .collect(Collectors.toList());
        }

        // Lọc theo cảm xúc
        if (feeling != null && !feeling.isEmpty()) {
            diaries = diaries.stream()
                    .filter(d -> feeling.equalsIgnoreCase(d.getFeeling()))
                    .collect(Collectors.toList());
        }

        // Lọc theo số sao
        if (rating != null) {
            diaries = diaries.stream()
                    .filter(d -> d.getRating() != null && d.getRating().equals(rating))
                    .collect(Collectors.toList());
        }

        // Sắp xếp theo số sao
        if ("asc".equals(sort)) {
            diaries.sort(Comparator.comparing(Diary::getRating, Comparator.nullsLast(Integer::compareTo)));
        } else if ("desc".equals(sort)) {
            diaries.sort(Comparator.comparing(Diary::getRating, Comparator.nullsLast(Integer::compareTo)).reversed());
        }

        // Truyền dữ liệu cho View
        model.addAttribute("diaries", diaries);
        model.addAttribute("userId", userId);
        model.addAttribute("keyword", keyword);
        model.addAttribute("feeling", feeling);
        model.addAttribute("rating", rating);
        model.addAttribute("sort", sort);

        return "diary_list";
    }
}