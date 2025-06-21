# Xây dựng ứng dụng quản lý Nhật ký cá nhân  
🧩 Yêu cầu:
Giao diện Java Spring Boot.

Có chức năng quản lý nhật ký và người dùng.

🔧 Chức năng chính:  
✅ Thêm, sửa, xóa người dùng (User)  
✅ Liệt kê thông tin về người dùng, có thể lọc ra các người dùng theo tên hoặc ID  
✅ Có chức năng quản lý nhật ký cá nhân (Diary)  
Thêm, sửa, xóa nhật ký  
 
Nhật ký bao gồm các thông tin: ngày viết, tiêu đề, địa điểm, người tham gia, loại hoạt động (đi ăn, đi chơi,...), chi tiêu, cảm xúc, đánh giá và ghi chú.   

✅ Có chức năng gán người dùng cho nhật ký   
Một nhật ký có thể liên kết với nhiều người dùng, và một người dùng có thể có nhiều nhật ký.  

💾 Lưu trữ dữ liệu:  
Dữ liệu được lưu trữ xuống file nhị phân để đảm bảo an toàn và dễ dàng truy xuất.

🗂️ Cấu trúc chương trình:
Cần tạo các lớp liên quan đến người dùng (User), nhật ký (Diary) và liên kết người dùng với nhật ký (UserDiary) để đọc, ghi xuống một hay nhiều file.

🧠 Làm việc với dữ liệu trong bộ nhớ:
Dữ liệu sẽ được lưu trữ trong các Collection tùy chọn như: ArrayList, LinkedList, Map, v.v. trong quá trình xử lý nghiệp vụ.

💡 Mở rộng (tuỳ chọn):
Sinh viên có thể thêm các chức năng như:

Tìm kiếm nhật ký theo từ khóa trong tiêu đề hoặc ghi chú.

Lọc nhật ký theo ngày viết, loại hoạt động, hoặc mức độ cảm xúc.

Xuất báo cáo hoạt động hoặc chi tiêu theo tháng/năm.

Tính tổng chi tiêu hoặc số lượng hoạt động mỗi người dùng.
