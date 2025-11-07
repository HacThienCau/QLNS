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
3. Tạo cơ sở dữ liệu MySQL. Bạn có thể import script SQL (MySQL) mẫu này:
```
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: quanlynhasach
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `baocaocongno`
--

DROP TABLE IF EXISTS `baocaocongno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `baocaocongno` (
  `MABCCN` varchar(100) NOT NULL,
  `MAKH` int NOT NULL,
  `NODAU` decimal(18,2) DEFAULT NULL,
  `PHATSINH` decimal(18,2) DEFAULT NULL,
  `NOCUOI` decimal(18,2) DEFAULT NULL,
  PRIMARY KEY (`MABCCN`,`MAKH`),
  KEY `FR_MAKHBCCN` (`MAKH`),
  CONSTRAINT `FR_MAKHBCCN` FOREIGN KEY (`MAKH`) REFERENCES `khachhang` (`MAKH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baocaocongno`
--

LOCK TABLES `baocaocongno` WRITE;
/*!40000 ALTER TABLE `baocaocongno` DISABLE KEYS */;
INSERT INTO `baocaocongno` VALUES ('12/2024',3,0.00,-10000.00,-10000.00);
/*!40000 ALTER TABLE `baocaocongno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baocaotonkho`
--

DROP TABLE IF EXISTS `baocaotonkho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `baocaotonkho` (
  `MABCTK` varchar(100) NOT NULL,
  `MASACH` int NOT NULL,
  `TONDAU` int DEFAULT NULL,
  `PHATSINH` int DEFAULT NULL,
  `TONCUOI` int DEFAULT NULL,
  PRIMARY KEY (`MABCTK`,`MASACH`),
  KEY `FR_MASACHBCTK` (`MASACH`),
  CONSTRAINT `FR_MASACHBCTK` FOREIGN KEY (`MASACH`) REFERENCES `sach` (`MASACH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baocaotonkho`
--

LOCK TABLES `baocaotonkho` WRITE;
/*!40000 ALTER TABLE `baocaotonkho` DISABLE KEYS */;
INSERT INTO `baocaotonkho` VALUES ('12/2024',1,0,200,200);
/*!40000 ALTER TABLE `baocaotonkho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitiethoadon`
--

DROP TABLE IF EXISTS `chitiethoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitiethoadon` (
  `MAHD` int NOT NULL,
  `MASACH` int NOT NULL,
  `SLBAN` int DEFAULT NULL,
  `ThanhTien` decimal(18,2) DEFAULT NULL,
  PRIMARY KEY (`MAHD`,`MASACH`),
  KEY `FR_MAHDCTHD` (`MAHD`),
  KEY `FR_MASACHCTHD` (`MASACH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiethoadon`
--

LOCK TABLES `chitiethoadon` WRITE;
/*!40000 ALTER TABLE `chitiethoadon` DISABLE KEYS */;
INSERT INTO `chitiethoadon` VALUES (1,1,1,100800.00),(1,2,1,79800.00),(2,3,1,56700.00),(2,4,2,126000.00),(3,1,1,100800.00),(3,2,1,79800.00),(3,6,2,23100.00),(4,1,2,100800.00),(5,5,3,75600.00),(6,6,2,23100.00);
/*!40000 ALTER TABLE `chitiethoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitietnhapsach`
--

DROP TABLE IF EXISTS `chitietnhapsach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietnhapsach` (
  `MAPHIEUNHAP` int NOT NULL,
  `MASACH` int NOT NULL,
  `GIANHAP` decimal(18,2) DEFAULT NULL,
  `SLNHAP` int DEFAULT NULL,
  PRIMARY KEY (`MAPHIEUNHAP`,`MASACH`),
  KEY `FR_MAPNCTPN` (`MAPHIEUNHAP`),
  KEY `FR_MASACHCTPN` (`MASACH`),
  CONSTRAINT `FR_MAPNCTPN` FOREIGN KEY (`MAPHIEUNHAP`) REFERENCES `phieunhapsach` (`MAPHIEUNHAP`),
  CONSTRAINT `FR_MASACHCTPN` FOREIGN KEY (`MASACH`) REFERENCES `sach` (`MASACH`),
  CONSTRAINT `CK_CTNHAP_GIANHAP` CHECK ((`GIANHAP` > 0)),
  CONSTRAINT `CK_CTNHAP_SLNHAP` CHECK ((`SLNHAP` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietnhapsach`
--

LOCK TABLES `chitietnhapsach` WRITE;
/*!40000 ALTER TABLE `chitietnhapsach` DISABLE KEYS */;
INSERT INTO `chitietnhapsach` VALUES (7,1,98000.00,200),(8,2,76000.00,200),(8,3,54000.00,200),(9,4,120000.00,200),(9,5,72000.00,200),(10,6,22000.00,200),(11,6,22000.00,200),(12,1,96000.00,150),(13,2,65000.00,200);
/*!40000 ALTER TABLE `chitietnhapsach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cttacgia`
--

DROP TABLE IF EXISTS `cttacgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cttacgia` (
  `MATG` int NOT NULL,
  `MADAUSACH` int NOT NULL,
  PRIMARY KEY (`MATG`,`MADAUSACH`),
  KEY `FK_CTTACGIA_TACGIA` (`MATG`),
  KEY `FK_CTTACGIA_DAUSACH` (`MADAUSACH`),
  CONSTRAINT `FK_CTTACGIA_DAUSACH` FOREIGN KEY (`MADAUSACH`) REFERENCES `dausach` (`MADAUSACH`),
  CONSTRAINT `FK_CTTACGIA_TACGIA` FOREIGN KEY (`MATG`) REFERENCES `tacgia` (`MATG`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cttacgia`
--

LOCK TABLES `cttacgia` WRITE;
/*!40000 ALTER TABLE `cttacgia` DISABLE KEYS */;
INSERT INTO `cttacgia` VALUES (1,1),(1,2),(2,3),(3,4),(4,5),(5,6);
/*!40000 ALTER TABLE `cttacgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dausach`
--

DROP TABLE IF EXISTS `dausach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dausach` (
  `MADAUSACH` int NOT NULL AUTO_INCREMENT,
  `TENDAUSACH` varchar(100) DEFAULT NULL,
  `MATHELOAI` int DEFAULT NULL,
  PRIMARY KEY (`MADAUSACH`),
  KEY `FK_DAUSACH_THELOAI` (`MATHELOAI`),
  CONSTRAINT `FK_DAUSACH_THELOAI` FOREIGN KEY (`MATHELOAI`) REFERENCES `theloai` (`MATHELOAI`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dausach`
--

LOCK TABLES `dausach` WRITE;
/*!40000 ALTER TABLE `dausach` DISABLE KEYS */;
INSERT INTO `dausach` VALUES (1,'Mắt Biếc',1),(2,'Tôi Thấy Hoa Vàng Trên Cỏ Xanh',1),(3,'Yêu Hồ Kỳ Nữ',2),(4,'Con Thú Mù',2),(5,'Trường Ca Achilles',8),(6,'Cây Cam Ngọt Của Tôi',8);
/*!40000 ALTER TABLE `dausach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `MAHD` int NOT NULL AUTO_INCREMENT,
  `MAKH` int DEFAULT NULL,
  `NGAYLAPHD` date DEFAULT NULL,
  `TONGTIEN` decimal(18,2) DEFAULT NULL,
  `DATRA` decimal(18,2) DEFAULT NULL,
  `CONLAI` decimal(18,2) DEFAULT NULL,
  PRIMARY KEY (`MAHD`),
  KEY `FR_MAKHHD` (`MAKH`),
  CONSTRAINT `FR_MAKHHD` FOREIGN KEY (`MAKH`) REFERENCES `khachhang` (`MAKH`),
  CONSTRAINT `CK_HOADON_DATRA` CHECK ((`DATRA` >= 0)),
  CONSTRAINT `CK_HOADON_TONGTIEN` CHECK ((`TONGTIEN` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES (1,4,'2025-01-06',180600.00,200000.00,19400.00),(2,3,'2025-01-08',308700.00,300000.00,-8700.00),(3,5,'2025-01-08',226800.00,250000.00,23200.00),(4,4,'2025-01-05',201600.00,50000.00,-151600.00),(5,4,'2025-01-08',226800.00,200000.00,-26800.00),(6,3,'2025-01-08',46200.00,50000.00,3800.00);
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `MAKH` int NOT NULL AUTO_INCREMENT,
  `TENKH` varchar(100) DEFAULT NULL,
  `DIACHI` varchar(100) DEFAULT NULL,
  `SODT` varchar(10) DEFAULT NULL,
  `TONGNO` decimal(18,2) DEFAULT NULL,
  PRIMARY KEY (`MAKH`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES (1,'Trần Cao Long','KTX KHU B','21521102',0.00),(2,'Thái Kiều Phương',NULL,'22521170',0.00),(3,'Đỗ Mai Tường Vy','KTX KHU B','22521701',-1300.00),(4,'Nguyễn Uyên','11A Bình Chiểu','22521641',128400.00),(5,'Lê Ngọc Duy Linh',NULL,'22520762',0.00);
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieunhapsach`
--

DROP TABLE IF EXISTS `phieunhapsach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieunhapsach` (
  `MAPHIEUNHAP` int NOT NULL AUTO_INCREMENT,
  `TONGSLNHAP` int DEFAULT NULL,
  `TONGTIEN` decimal(18,2) DEFAULT NULL,
  `NGAYNHAP` date DEFAULT NULL,
  PRIMARY KEY (`MAPHIEUNHAP`),
  CONSTRAINT `CK_PHIEUNHAPSACH_TONGCHIPHI` CHECK ((`TONGTIEN` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieunhapsach`
--

LOCK TABLES `phieunhapsach` WRITE;
/*!40000 ALTER TABLE `phieunhapsach` DISABLE KEYS */;
INSERT INTO `phieunhapsach` VALUES (7,200,19600000.00,'2024-12-28'),(8,400,26000000.00,'2025-01-06'),(9,400,38400000.00,'2025-01-07'),(10,200,4400000.00,'2025-01-07'),(11,200,4400000.00,'2025-01-08'),(12,150,14400000.00,'2025-01-08'),(13,200,13000000.00,'2025-01-08');
/*!40000 ALTER TABLE `phieunhapsach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieuthutien`
--

DROP TABLE IF EXISTS `phieuthutien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieuthutien` (
  `MAPHIEUTHU` int NOT NULL AUTO_INCREMENT,
  `MAKH` int DEFAULT NULL,
  `NGAYTHU` date DEFAULT NULL,
  `SOTIEN` decimal(18,2) DEFAULT NULL,
  PRIMARY KEY (`MAPHIEUTHU`),
  KEY `FR_MAKHPTT` (`MAKH`),
  CONSTRAINT `FR_MAKHPTT` FOREIGN KEY (`MAKH`) REFERENCES `khachhang` (`MAKH`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieuthutien`
--

LOCK TABLES `phieuthutien` WRITE;
/*!40000 ALTER TABLE `phieuthutien` DISABLE KEYS */;
INSERT INTO `phieuthutien` VALUES (3,3,'2024-12-23',10000.00),(4,4,'2025-01-05',50000.00);
/*!40000 ALTER TABLE `phieuthutien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sach`
--

DROP TABLE IF EXISTS `sach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sach` (
  `MASACH` int NOT NULL AUTO_INCREMENT,
  `NXB` varchar(50) DEFAULT NULL,
  `MADAUSACH` int DEFAULT NULL,
  `NAMXB` varchar(4) DEFAULT NULL,
  `SLTON` int DEFAULT NULL,
  `DONGIANHAP` decimal(18,2) DEFAULT NULL,
  PRIMARY KEY (`MASACH`),
  CONSTRAINT `CK_SACH_DONGIANHAP` CHECK ((`DONGIANHAP` >= 0)),
  CONSTRAINT `CK_SACH_SLTONKHO` CHECK ((`SLTON` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sach`
--

LOCK TABLES `sach` WRITE;
/*!40000 ALTER TABLE `sach` DISABLE KEYS */;
INSERT INTO `sach` VALUES (1,'Nhà xuất bản Trẻ',1,'2019',346,96000.00),(2,'Nhà xuất bản Trẻ',2,'2023',398,65000.00),(3,'Nhà xuất bản Hội Nhà Văn',3,'2024',199,54000.00),(4,'Nhà xuất bản Hội Nhà Văn',4,'2024',198,120000.00),(5,'Nhà xuất bản Kim Đồng',5,'2020',197,72000.00),(6,'Nhà xuất bản Hội Nhà Văn',6,'2020',396,22000.00);
/*!40000 ALTER TABLE `sach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tacgia`
--

DROP TABLE IF EXISTS `tacgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tacgia` (
  `MATG` int NOT NULL AUTO_INCREMENT,
  `TENTG` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`MATG`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tacgia`
--

LOCK TABLES `tacgia` WRITE;
/*!40000 ALTER TABLE `tacgia` DISABLE KEYS */;
INSERT INTO `tacgia` VALUES (1,'Nguyễn Nhật Ánh'),(2,'Tĩnh Thủy'),(3,'Edogawa Ranpo'),(4,'Madeline Miller'),(5,'José Mauro de Vasconcelos');
/*!40000 ALTER TABLE `tacgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thamso`
--

DROP TABLE IF EXISTS `thamso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thamso` (
  `SOLUONGNHAPTOITHIEU` int DEFAULT NULL,
  `SOLUONGTONTOIDATRUOCNHAP` int DEFAULT NULL,
  `TILETINHGIABAN` float DEFAULT NULL,
  `KIEMTRATIENTHU` int DEFAULT NULL,
  `SONOTOIDA` int DEFAULT NULL,
  `SOLUONGTONTOITHIEUSAUBAN` int DEFAULT NULL,
  CONSTRAINT `CK_KIEMTRATIENTHU` CHECK ((`KIEMTRATIENTHU` in (0,1))),
  CONSTRAINT `CK_SLNHAPTOITHIEU` CHECK ((`SOLUONGNHAPTOITHIEU` > 0)),
  CONSTRAINT `CK_SLTONTOIDATRUOCKHINHAP` CHECK ((`SOLUONGTONTOIDATRUOCNHAP` >= 0)),
  CONSTRAINT `CK_SOLUONGTONTOITHIEUSAUBAN` CHECK ((`SOLUONGTONTOITHIEUSAUBAN` >= 0)),
  CONSTRAINT `CK_SONOTOIDA` CHECK ((`SONOTOIDA` > 0)),
  CONSTRAINT `CK_TILETINHGIABAN` CHECK (((`TILETINHGIABAN` >= 1) and (`TILETINHGIABAN` <= 10)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thamso`
--

LOCK TABLES `thamso` WRITE;
/*!40000 ALTER TABLE `thamso` DISABLE KEYS */;
INSERT INTO `thamso` VALUES (150,300,1.05,1,1000000,20);
/*!40000 ALTER TABLE `thamso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theloai`
--

DROP TABLE IF EXISTS `theloai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theloai` (
  `MATHELOAI` int NOT NULL AUTO_INCREMENT,
  `TENTHELOAI` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`MATHELOAI`),
  UNIQUE KEY `MATHELOAI_UNIQUE` (`MATHELOAI`),
  UNIQUE KEY `TENTHELOAI_UNIQUE` (`TENTHELOAI`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theloai`
--

LOCK TABLES `theloai` WRITE;
/*!40000 ALTER TABLE `theloai` DISABLE KEYS */;
INSERT INTO `theloai` VALUES (2,'Huyền Bí'),(6,'Khoa Học'),(7,'Kinh Dị'),(3,'Tản Văn'),(4,'Thiếu Nhi'),(8,'Tiểu thuyết'),(1,'Truyện Dài');
/*!40000 ALTER TABLE `theloai` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-05 14:36:02

```
4. Chỉnh cấu hình kết nối DB: Mở lớp cấu hình kết nối trong `DatabaseConnect.java`, sửa thông tin:
```bash
String url = "jdbc:mysql://localhost:3306/your_database";
String user = "your_username";
String password = "your_password";
```
5. Chạy project trong NetBeans: **Run Project (F6)**.

## 📧 Liên hệ
Nếu bạn có bất kỳ câu hỏi nào hoặc cần hỗ trợ, vui lòng liên hệ với tôi qua email: dragneel.takeshi@gmail.com hoặc 22521641@gm.uit.edu.vn
