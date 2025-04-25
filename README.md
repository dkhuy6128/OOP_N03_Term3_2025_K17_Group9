# OOP_N03_Term3_2025_K17_Group28
https://github.com/nglthu/OOP_N03_Term3_2025_K17
# Member:
  Đỗ Khắc Huy

# Xây dựng ứng dụng nhật kí đi chơi
Ứng dụng giúp người dùng:
 + Ghi lại các khoản chi tiêu (ăn uống, đi chơi, ..)
 + Ghi lại cảm nhận / cảm xúc liên quan đến nơi đó
 + Đưa ra đánh giá cá nhân của người dùng (thích - không thích, 1 - 5 sao, ..)
 + Thống kê chi tiêu và cảm xúc theo thời gian (ngày, tuần, tháng)

# Stage 1
1. User (Thông tin người dùng) 
Class User {
    String userID; (id người dùng)
    String name; (tên người dùng)
}

2. Diary (Thông tin liên quan đến nhật kí)
Class Diary {
    String diaryID;        (mã định danh nhật kí)
    LocalDate date;        (ngày viết nhật kí)
    String title;          (tiêu đề)
    String location;       (địa điểm)
    List<String> people;   (danh sách người tham gia)
    String activityType;   (loại hoạt động(đi ăn, đi chơi, ...))
    Double amountSpent;    (chi tiêu)
    String feeling;        (cảm nhận buổi hôm đó)
    int rating;            (đánh giá)
    String notes;          (ghi chú)
}
4. UserDiary (liên kết giữa người dùng và nhật kí)
Class UserDiary {
    String userID;   // ID của người dùng
    String diaryID;  // ID của mục nhật ký
}
