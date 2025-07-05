package com.diaryapp.controller;

import com.diaryapp.dto.DiaryStats;
import com.diaryapp.model.Diary;
import com.diaryapp.model.User;
import com.diaryapp.model.UserDiary;
import com.diaryapp.service.DiaryService;
import com.diaryapp.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/diaries")
public class DiaryController {

    private final DiaryService diaryService;
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("userId", "user123");
        return "index";
    }

    @GetMapping("/{userId}")
    public String list(@PathVariable String userId, Model model) {
        List<Diary> diaries = diaryService.getDiaries(userId);
        diaries.sort(Comparator.comparing(Diary::getDate, Comparator.nullsLast(LocalDate::compareTo)).reversed());
        model.addAttribute("diaries", diaries);
        model.addAttribute("userId", userId);
        return "diary_list";
    }


    @GetMapping("/{userId}/add")
    public String addForm(@PathVariable String userId, Model model) {
        model.addAttribute("diary", new Diary());
        model.addAttribute("userId", userId);
        model.addAttribute("actionUrl", "/diaries/" + userId + "/save");
        return "diary_form";
    }

    @PostMapping("/{userId}/save")
    public String saveDiary(@PathVariable String userId,
                            @ModelAttribute Diary diary,
                            @RequestParam(required = false) String participants) {

        if (diary.getDate() == null || diary.getTitle() == null || diary.getTitle().isEmpty()) {
            return "redirect:/diaries/" + userId + "/add?error=missing_fields";
        }

        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isEmpty()) {
            return "redirect:/users?error=user_not_found";
        }
        User user = optionalUser.get();
        diary.setUser(user);

        diary.setId(UUID.randomUUID().toString());

        if (participants != null && !participants.isEmpty()) {
            diary.setParticipants(Arrays.asList(participants.split("\\s*,\\s*")));
        } else {
            diary.setParticipants(Collections.emptyList());
        }

        UserDiary userDiary = new UserDiary();
        userDiary.setUser(user);
        userDiary.setDiary(diary);

        diary.setUserDiaries(List.of(userDiary));
        user.setUserDiaries(List.of(userDiary));

        diaryService.addDiary(userId, diary);

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
        model.addAttribute("actionUrl", "/diaries/" + userId + "/update");
        return "diary_form";
    }

    @PostMapping("/{userId}/update")
    public String updateDiary(@PathVariable String userId,
                              @ModelAttribute Diary diary,
                              @RequestParam(required = false) String participants) {

        if (diary.getDate() == null || diary.getTitle() == null || diary.getTitle().isEmpty()) {
            return "redirect:/diaries/" + userId + "/edit/" + diary.getId() + "?error=missing_fields";
        }

        Diary existingDiary = diaryService.getById(userId, diary.getId());
        if (existingDiary == null) {
            return "redirect:/diaries/" + userId + "?error=not_found";
        }

        existingDiary.setDate(diary.getDate());
        existingDiary.setTitle(diary.getTitle());
        existingDiary.setActivity(diary.getActivity());
        existingDiary.setAmount(diary.getAmount());
        existingDiary.setLocation(diary.getLocation());
        existingDiary.setNotes(diary.getNotes());
        existingDiary.setFeeling(diary.getFeeling());
        existingDiary.setRating(diary.getRating());

        if (participants != null && !participants.isEmpty()) {
            existingDiary.setParticipants(Arrays.asList(participants.split("\\s*,\\s*")));
        } else {
            existingDiary.setParticipants(Collections.emptyList());
        }

        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isEmpty()) {
            return "redirect:/users?error=user_not_found";
        }
        existingDiary.setUser(optionalUser.get());

        diaryService.updateDiary(userId, existingDiary.getId(), existingDiary);

        return "redirect:/diaries/" + userId;
    }

    @GetMapping("/{userId}/delete/{diaryId}")
    public String deleteDiary(@PathVariable String userId, @PathVariable String diaryId) {
        diaryService.deleteDiary(userId, diaryId);
        return "redirect:/diaries/" + userId;
    }

    @GetMapping("/{userId}/stats")
    public String showStats(@PathVariable String userId, Model model) {
        List<Diary> diaries = diaryService.getDiaries(userId);

        int totalDiaries = diaries.size();
        double totalExpense = diaries.stream()
                .filter(d -> d.getAmount() != null)
                .mapToDouble(Diary::getAmount)
                .sum();

        String formattedExpense = String.format("%,.0f vnđ", totalExpense);

        model.addAttribute("totalDiaries", totalDiaries);
        model.addAttribute("formattedExpense", formattedExpense);

        return "diary_statistics";
    }

    @GetMapping("/{userId}/search")
    public String searchDiaries(@PathVariable String userId,
                                @RequestParam(required = false) String keyword,
                                @RequestParam(required = false) String feeling,
                                @RequestParam(required = false) Integer rating,
                                @RequestParam(required = false) String sort,
                                @RequestParam(required = false) String month,
                                @RequestParam(required = false) Integer year,
                                Model model) {

        List<Diary> diaries = diaryService.getDiaries(userId);

        if (keyword != null && !keyword.isEmpty()) {
            String lowered = keyword.toLowerCase();
            diaries = diaries.stream()
                    .filter(d ->
                            Optional.ofNullable(d.getTitle()).orElse("").toLowerCase().contains(lowered)
                                    || Optional.ofNullable(d.getActivity()).orElse("").toLowerCase().contains(lowered)
                    )
                    .collect(Collectors.toList());
        }

        if (feeling != null && !feeling.isEmpty()) {
            diaries = diaries.stream()
                    .filter(d -> feeling.equalsIgnoreCase(d.getFeeling()))
                    .collect(Collectors.toList());
        }

        if (rating != null) {
            diaries = diaries.stream()
                    .filter(d -> d.getRating() != null && d.getRating().equals(rating))
                    .collect(Collectors.toList());
        }

        if (month != null && !month.isEmpty() && year != null) {
            diaries = diaries.stream()
                    .filter(d -> {
                        if (d.getDate() == null) return false;
                        String diaryMonth = String.format("%02d", d.getDate().getMonthValue());
                        int diaryYear = d.getDate().getYear();
                        return diaryMonth.equals(month) && diaryYear == year;
                    })
                    .collect(Collectors.toList());
        }

        if ("asc".equals(sort)) {
            diaries.sort(Comparator.comparing(Diary::getRating, Comparator.nullsLast(Integer::compareTo)));
        } else if ("desc".equals(sort)) {
            diaries.sort(Comparator.comparing(Diary::getRating, Comparator.nullsLast(Integer::compareTo)).reversed());
        } else if ("date_asc".equals(sort)) {
            diaries.sort(Comparator.comparing(Diary::getDate, Comparator.nullsLast(LocalDate::compareTo)));
        } else if ("date_desc".equals(sort)) {
            diaries.sort(Comparator.comparing(Diary::getDate, Comparator.nullsLast(LocalDate::compareTo)).reversed());
        }

        model.addAttribute("diaries", diaries);
        model.addAttribute("userId", userId);
        model.addAttribute("keyword", keyword);
        model.addAttribute("feeling", feeling);
        model.addAttribute("rating", rating);
        model.addAttribute("sort", sort);
        model.addAttribute("month", month);
        model.addAttribute("year", year);

        return "diary_list";
    }
}