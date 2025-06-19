package DiaryApp.controller;

import DiaryApp.dto.DiaryRequest;
import DiaryApp.model.Diary;
import DiaryApp.service.DiaryService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/diaries")
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/{userId}")
    public Map<String, String> addDiary(@PathVariable String userId, @RequestBody DiaryRequest request) {
        try {
            String diaryId = UUID.randomUUID().toString();

            Diary diary = new Diary(
                    diaryId,
                    request.date,
                    request.title,
                    request.location,
                    request.participants,
                    request.activity,
                    request.amount,
                    request.feeling,
                    request.rating,
                    request.notes
            );

            diaryService.addDiary(userId, diary);

            return Map.of("message", "Diary saved", "id", diaryId);
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm nhật ký: " + e.getMessage());
            e.printStackTrace();
            return Map.of("error", "Không thể lưu nhật ký");
        } finally {
            System.out.println("Đã xử lý xong yêu cầu thêm nhật ký.");
        }
    }

    @GetMapping("/{userId}")
    public List<Diary> getDiaries(@PathVariable String userId) {
        try {
            return diaryService.getDiaries(userId);
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách nhật ký: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>(); // Trả về danh sách rỗng nếu lỗi
        } finally {
            System.out.println("Đã xử lý xong yêu cầu lấy nhật ký.");
        }
    }
}
