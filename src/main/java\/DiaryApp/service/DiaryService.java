package DiaryApp.service;

import DiaryApp.model.Diary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    // ✅ Hàm in nhật ký theo tháng
    public void printDiariesByMonth(String userId, int month, int year) {
        List<Diary> diaries = getDiaries(userId);
        try {
            boolean found = false;
            System.out.println("Nhật ký trong tháng " + month + "/" + year + ":");
            for (Diary diary : diaries) {
                LocalDate date = diary.getDate();
                if (date.getMonthValue() == month && date.getYear() == year) {
                    System.out.println("-");
                    System.out.println("Tiêu đề: " + diary.getTitle());
                    System.out.println("Ngày: " + date);
                    System.out.println("Địa điểm: " + diary.getLocation());
                    System.out.println("Loại hoạt động: " +  diary.getActivityType());
                    System.out.println("Chi tiêu: " + diary.getAmountSpent() + " VND");
                    System.out.println("Cảm xúc: " + diary.getFeeling());
                    System.out.println("Đánh giá: " + diary.getRating());
                    System.out.println("Ghi chú: " + diary.getNotes());
                    System.out.println("Người tham gia: " + diary.getPeople());
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Không có nhật ký nào trong tháng này.");
            }
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi khi in nhật ký: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Kết thúc xử lý in nhật ký theo tháng.");
        }
    }
}
