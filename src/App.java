import DiaryApp.DiaryApp;
import Service.DiaryService;
import model.Diary;

import java.time.LocalDate;
import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DiaryApp app = new DiaryApp();

        System.out.print("Nhập ID người dùng: ");
        String userID = sc.nextLine();
        System.out.print("Nhập tên người dùng: ");
        String name = sc.nextLine();
        app.addUser(userID, name);

        while (true) {
            System.out.println("\n--- Nhập nhật ký mới ---");

            System.out.print("Nhập ID nhật ký: ");
            String diaryID = sc.nextLine();

            System.out.print("Nhập tiêu đề: ");
            String title = sc.nextLine();

            System.out.print("Nhập địa điểm: ");
            String location = sc.nextLine();

            System.out.print("Nhập ngày (yyyy-mm-dd): ");
            LocalDate date = LocalDate.parse(sc.nextLine());

            System.out.print("Nhập loại hoạt động: ");
            String activityType = sc.nextLine();

            System.out.print("Nhập chi tiêu: ");
            double amountSpent = Double.parseDouble(sc.nextLine());

            System.out.print("Nhập cảm nhận: ");
            String feeling = sc.nextLine();

            System.out.print("Nhập đánh giá (1-5): ");
            int rating = Integer.parseInt(sc.nextLine());

            System.out.print("Nhập ghi chú thêm: ");
            String notes = sc.nextLine();

            System.out.print("Nhập người tham gia (cách nhau bởi dấu phẩy): ");
            List<String> people = Arrays.asList(sc.nextLine().split(",\\s*"));

            Diary diary = new Diary(diaryID, date, title, location, people, activityType, amountSpent, feeling, rating, notes);
            app.addDiary(userID, diary);

            System.out.print("Thêm nhật ký khác? (y/n): ");
            if (!sc.nextLine().equalsIgnoreCase("y")) break;
        }

        System.out.println("\n== Thống kê ==");
        System.out.println("Tổng chi tiêu: " + app.getTotalSpent(userID));
        System.out.printf("Đánh giá trung bình: %.2f / 5\n", app.getAverageRating(userID));

        // Nhập tháng và năm để lọc nhật ký
        System.out.println("\n== Lọc nhật ký theo tháng ==");
        System.out.print("Nhập tháng (1-12): ");
        int month = sc.nextInt();
        System.out.print("Nhập năm: ");
        int year = sc.nextInt();

        // Lấy nhật ký thực tế của người dùng
        List<Diary> userDiaries = app.getDiariesByUser(userID); // Giả sử bạn có phương thức này

        // In nhật ký theo tháng
        DiaryService service = new DiaryService();
        service.printDiariesByMonth(userDiaries, month, year);

        sc.close();
    }
}
