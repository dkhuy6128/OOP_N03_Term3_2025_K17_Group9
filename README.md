# 📔 Ứng Dụng Quản Lý Nhật Ký Cá Nhân

> Ứng dụng web giúp người dùng ghi lại nhật ký hằng ngày: hoạt động, cảm xúc, địa điểm, chi tiêu, người tham gia,… Với giao diện thân thiện và hệ thống quản lý rõ ràng theo từng người dùng.

---

## 🔑 Chức Năng Chính

### 👤 Người dùng
- 🧑‍💼 Thêm / sửa / xoá người dùng
- 🔍 Tìm kiếm người dùng theo tên hoặc ID

### 📓 Nhật ký cá nhân
- 📝 Thêm / sửa / xoá nhật ký
- 🗓️ Quản lý các trường: ngày viết, tiêu đề, địa điểm, hoạt động, chi tiêu, cảm xúc, đánh giá, ghi chú
- 👥 Thêm người tham gia
- 🔗 Gán nhiều người dùng cho một nhật ký, và ngược lại

---

## ⚙️ Công Nghệ Sử Dụng

| Thành phần    | Công nghệ                                      |
|---------------|------------------------------------------------|
| Backend       | Spring Boot 3.5.3                              |
| Frontend      | Thymeleaf + Tailwind CSS                      |
| Database      | MySQL 8.x                                      |
| Build Tool    | Maven Wrapper (`./mvnw`)                       |
| Java          | Java 17                                        |

---

## 📋 Môi Trường Cần Thiết

- Java 17 hoặc cao hơn  
- Maven 3.8+ (đã kèm `mvnw`)  
- MySQL 8.x  
- IDE: IntelliJ IDEA / Eclipse / VS Code

---

## 🚀 Hướng Dẫn Cài Đặt

### 1️⃣ Clone dự án

```bash
git clone https://github.com/dkhuy6128/OOP_N03_Term3_2025_K17_Group9/
cd diaryapp
```

Hoặc tải về bằng nút "Download ZIP" và giải nén.

---

### 2️⃣ Tạo cơ sở dữ liệu MySQL

Đăng nhập MySQL rồi tạo database:

```sql
CREATE DATABASE IF NOT EXISTS diarydb;
```

Sau đó chạy 2 file SQL có sẵn:

```sql
source src/main/resources/database/schema.sql;
source src/main/resources/database/data.sql;
```
⚠️ Nếu gặp lỗi Failed to open file khi dùng source
📌 Lỗi thường do sai đường dẫn hoặc MySQL đang ở sai thư mục

💡 Cách xử lý:
Tìm đường dẫn tuyệt đối tới file schema.sql và data.sql trên máy bạn
Ví dụ:

swift
Sao chép
Chỉnh sửa
C:/Users/YourName/Downloads/diaryapp/src/main/resources/database/schema.sql
Dùng lệnh source với đường dẫn đầy đủ trong MySQL terminal:

sql
Sao chép
Chỉnh sửa
source C:/Users/YourName/Downloads/diaryapp/src/main/resources/database/schema.sql;
source C:/Users/YourName/Downloads/diaryapp/src/main/resources/database/data.sql;
✅ Nếu vẫn lỗi, hãy thử copy 2 file SQL ra thư mục dễ truy cập, như C:/SQL/, rồi chạy:

sql
Sao chép
Chỉnh sửa
source C:/SQL/schema.sql;
source C:/SQL/data.sql;
🧠 Ghi nhớ:
Không cần dấu " " hoặc ' ' quanh đường dẫn

Dùng dấu / hoặc \\ đều được trên Windows

Đảm bảo bạn đã USE diarydb; trước khi chạy file data.sql

🎯 Sau khi chạy thành công, các bảng và dữ liệu mẫu sẽ được tạo sẵn trong database diarydb.

> ✅ Bạn có thể chạy bằng MySQL Workbench hoặc terminal đều được.

---

### 3️⃣ Cấu hình kết nối cơ sở dữ liệu

Mở file:

#### `src/main/resources/application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/diarydb
spring.datasource.username=root
spring.datasource.password=your_password
```

> 🔐 Thay `your_password` bằng mật khẩu MySQL thật trên máy bạn.

---

### 4️⃣ Chạy ứng dụng

#### 👉 Cách 1: Qua IntelliJ IDEA (giao diện)
- Mở project
- Chạy file `DiaryappApplication.java`
- Truy cập [http://localhost:8080](http://localhost:8080)

#### 👉 Cách 2: Qua terminal
```bash
./mvnw spring-boot:run
```

---

## 👨‍💻 Phân Công Thành Viên

### 1. **Đỗ Khắc Huy**
- 🔧 Back-end (Service, Controller, xử lý logic nghiệp vụ)  
- 🧪 Kiểm thử và fix bug Back-end  
- 🛠️ Thiết kế cơ sở dữ liệu (bảng `users`, `diaries`, `user_diaries`, `diary_participants`)  
- 🧮 Viết API quản lý người dùng và nhật ký  
- ⚙️ Cấu hình `application.properties` và khởi tạo DB từ `schema.sql`, `data.sql`

### 2. **Đỗ Huy**
- 🌐 Front-end (Thymeleaf + Tailwind CSS)  
- 🖼️ Thiết kế giao diện viết nhật ký, danh sách, thống kê  
- 💅 CSS layout, responsive cho toàn bộ UI  
- 🧪 Kiểm thử UI & logic kết nối Front–Back  
- 🎨 Thiết kế giao diện người tham gia (participants)

### 3. **Khắc Huy Đỗ**
- 📝 Viết tài liệu `README.md`, hướng dẫn cài đặt & triển khai  
- 🧰 Xử lý cấu hình Maven (`pom.xml`, `./mvnw`)  
- 🗂️ Quản lý thư mục tài nguyên (`resources/`)  
- 🧪 Kiểm tra toàn bộ flow app (từ nhập → lưu → thống kê)  
- 🔗 Thử nghiệm tương thích với MySQL và IDE khác nhau

---

## 📌 Ghi Chú

- Ứng dụng sử dụng `schema.sql` + `data.sql` nên **cần đảm bảo MySQL đang chạy trước khi khởi động app**
- Nếu không muốn load dữ liệu mẫu, có thể để trống file `data.sql`
- Mật khẩu MySQL nên đặt đúng và dùng quyền đủ để tạo bảng

---

## 📬 Liên Hệ

- Email: [23017163@st.phenikaa-uni.edu.vn@gmail.com](mailto:23017163@st.phenikaa-uni.edu.vn)  
- GitHub: [github.com/dkhuy6128](https://github.com/dkhuy6128)

---
