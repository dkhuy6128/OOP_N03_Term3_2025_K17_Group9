# OOP_N03_Term3_2025_K17_Group28  
https://github.com/nglthu/OOP_N03_Term3_2025_K17  

## Member:  
- Đỗ Khắc Huy  

## Xây dựng ứng dụng nhật kí đi chơi  
Ứng dụng giúp người dùng:  
- Ghi lại các khoản chi tiêu (ăn uống, đi chơi, ..)  
- Ghi lại cảm nhận / cảm xúc liên quan đến nơi đó  
- Đưa ra đánh giá cá nhân của người dùng (thích - không thích, 1 - 5 sao, ..)  
- Thống kê chi tiêu và cảm xúc theo thời gian (ngày, tuần, tháng)  

---

## Stage 1  

### 1. User (Thông tin người dùng)  
class User {
    String userID;   // ID người dùng
    String name;     // Tên người dùng
}
### 2. Diary (Thông tin nhật kí)
class Diary {
    String diaryID;         // Mã định danh nhật kí
    LocalDate date;         // Ngày viết nhật kí
    String title;           // Tiêu đề
    String location;        // Địa điểm
    List<String> people;    // Danh sách người tham gia
    String activityType;    // Loại hoạt động (đi ăn, đi chơi, ...)
    Double amountSpent;     // Chi tiêu
    String feeling;         // Cảm nhận buổi hôm đó
    int rating;             // Đánh giá
    String notes;           // Ghi chú
}
### 3. UserDiary (liên kết thông tin người dùng với nhật kí)
class UserDiary {
    String userID;    // ID của người dùng
    String diaryID;   // ID của mục nhật ký
}
