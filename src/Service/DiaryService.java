package Service;

import java.time.LocalDate;
import java.util.List;
import model.Diary;

public class DiaryService {

    public void printDiariesByMonth(List<Diary> diaries, int month, int year) {
        boolean found = false;
        System.out.println("Nhat ky trong thang " + month + "/" + year + ":");
        for (Diary diary : diaries) {
            LocalDate date = diary.getDate();
            if (date.getMonthValue() == month && date.getYear() == year) {
                System.out.println("-");
                System.out.println("Tieu de: " + diary.getTitle());
                System.out.println("Ngay: " + date);
                System.out.println("Dia diem: " + diary.getLocation());
                System.out.println("Loai hoat dong: " +  diary.getActivityType());
                System.out.println("Chi tieu: " + diary.getAmountSpent() + "VND");
                System.out.println("Cam xuc: " + diary.getFeeling());
                System.out.println("Danh gia: " + diary.getRating());
                System.out.println("Ghi chu: " + diary.getNotes());
                System.out.println("Nguoi tham gia: " + diary.getPeople());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Khong co nhat ki nao trong thang nay.");
        }
    }
}
/* Miêu tả phương thức
 + hiển thị danh sách các nhật kí được tạo trong một tháng cụ thể do người dùng nhập từ bàn phím
 + yêu cầu người dùng nhập tháng và năm từ bàn phím, sau đó lọc danh sách nhật kí
 + nếu tìm thấy, in ra thông tin chi tiết từng nhật kí(tiêu đề, địa điểm, ...)
 + nếu không, thông báo không có nhật kí trong tháng đó */