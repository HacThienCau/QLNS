# 📚 Ứng dụng quản lý nhà sách

<p align="center">
  <a href="https://www.uit.edu.vn/" title="University of Information Technology" style="border: none;">
    <img src="https://i.imgur.com/WmMnSRt.png" alt="University of Information Technology">
  </a>
</p>

<h1 align="center"><b>SE104.P11 - Nhập môn Công nghệ Phần mềm</b></h1>

## 👥 Thành viên nhóm:
| **STT** | **MSSV** | **Họ tên**            |  **Email**                  |
| ------- | -------------- | ------------------------ | -------------------------- |
| 1       | 22521641       | Nguyễn Đăng Hương Uyên   | 22521641@gm.uit.edu.vn     |
| 2       | 22521701       | Đỗ Mai Tường Vy          | 22521701@gm.uit.edu.vn     |
| 3       | 22521170       | Thái Kiều Phương         | 22521170@gm.uit.edu.vn     |
| 4       | 22520762       | Lê Ngọc Duy Linh         | 22520762@gm.uit.edu.vn     |
| 5       | 21521102       | Trần Cao Long            | 21521102@gm.uit.edu.vn     |


## 🏠 Giới thiệu
**QLNS (Quản Lý Nhà Sách)** là phần mềm quản lý được phát triển bằng Java Swing trên môi trường NetBeans IDE, sử dụng cơ sở dữ liệu MySQL để lưu trữ thông tin.
Ứng dụng giúp người quản lý dễ dàng thao tác các nghiệp vụ như quản lý sách, nhân viên, khách hàng, hóa đơn và thống kê doanh thu.
Phần mềm được thiết kế hướng đến tính trực quan, dễ sử dụng và có thể triển khai tại các cửa hàng sách vừa và nhỏ.

## ✨ Tính năng
- Quản lý sách: thêm, sửa, xóa, tìm kiếm, cập nhật thông tin sách.
- Quản lý nhân viên và khách hàng: lưu trữ thông tin cá nhân, phân quyền và truy xuất nhanh.
- Quản lý hóa đơn: tạo và lưu trữ hóa đơn mua hàng.
- Thống kê và báo cáo: hiển thị doanh thu, lượng bán, tồn kho.
- Đăng nhập & phân quyền: đảm bảo tính bảo mật và quyền truy cập.
- Kết nối cơ sở dữ liệu MySQL: đảm bảo lưu trữ dữ liệu ổn định, an toàn.
- Cấu hình quy định nghiệp vụ (số lượng tối thiểu, nợ tối đa, ...).

## 🏗️ Kiến trúc hệ thống

### Kiến trúc tổng thể
Phần mềm Quản lý Nhà Sách sử dụng kiến trúc 2 lớp (Client – Server):
- Client (Máy khách):
Là phần giao diện mà người dùng trực tiếp tương tác, nơi nhập dữ liệu và gửi yêu cầu đến máy chủ.
Client xử lý các logic nghiệp vụ cơ bản và hiển thị thông tin cho người dùng.
- Server (Máy chủ):
Là nơi lưu trữ và quản lý dữ liệu, cung cấp dịch vụ cho Client.
Trong đồ án này, Server = Dữ liệu, còn Client = Giao diện + Xử lý

#### Trong đồ án này:
- Server = Dữ liệu
- Client = Giao diện + Xử lý

→ Mô hình Thick Client (ứng dụng xử lý phần lớn nghiệp vụ phía client, kết nối trực tiếp MySQL thông qua JDBC).

### Các thành phần chính
| **STT** | **Thành phần** | **Diễn giải**            
| ------- | ----------------- | ------------------------ | 
| 1       | Client            | Ứng dụng Java (NetBeans) — giao diện người dùng và xử lý nghiệp vụ.
| 2       | Server            | MySQL — lưu trữ dữ liệu: sách, hóa đơn, khách hàng, phiếu nhập, ...
| 3       | JDBC API          | API để Java giao tiếp với cơ sở dữ liệu.
| 4       | JDBC Driver Manager  | Quản lý driver JDBC, thiết lập kết nối.
| 5       | MySQL JDBC Driver     | Driver kết nối Java ↔ MySQL.

### Thiết kế dữ liệu 
![Sơ đồ dữ liệu](Database Diagram.png)

## ⚙️ Tech Stack
- **Ngôn ngữ & IDE** : Java (Swing), NetBeans IDE
- **Database** : MySQL
- **Libraries & Tools** : JDBC API, MySQL JDBC Driver
 
## 📂 Cấu trúc thư mục
```bash
QLNS/
├── build/
├── libs/
├── nbproject/
├── src/
│   ├── com/
│   │   ├── component/
│   │   ├── event/
│   │   ├── swing/         
│   │   └── model/      
│   ├── icon/  
│   └── UI/ 
├── .gitignore
├── README.md
├── build.xml
└── manifest.mf
```

## 🛠️ Cài đặt & chạy ứng dụng

### Các bước
1. Clone repository:

```bash 
git clone https://github.com/HacThienCau/QLNS
cd QLNS
```

2. Mở project trong NetBeans: **File** > **Open Project** → chọn thư mục **QLNS**.
3. Tạo cơ sở dữ liệu MySQL. Bạn có thể copy script SQL từ `createTableQLNS.sql`.
4. Chỉnh cấu hình kết nối DB: Mở lớp cấu hình kết nối trong `DatabaseConnect.java`, sửa thông tin:
```bash
String url = "jdbc:mysql://localhost:3306/your_database";
String user = "your_username";
String password = "your_password";
```
5. Chạy project trong NetBeans: **Run Project (F6)**.

## 📧 Liên hệ
Nếu bạn có bất kỳ câu hỏi nào hoặc cần hỗ trợ, vui lòng liên hệ với tôi qua email: dragneel.takeshi@gmail.com hoặc 22521641@gm.uit.edu.vn
