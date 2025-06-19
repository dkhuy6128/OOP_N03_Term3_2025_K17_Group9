package DiaryApp.DiaryApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DiaryApp {
    public static void main(String[] args) {
        try {
            SpringApplication.run(DiaryApp.class, args);
        } catch (Exception e) {
            System.out.println("Lỗi khi khởi động ứng dụng: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Ứng dụng đã thực thi xong hàm main.");
        }
    }
}
