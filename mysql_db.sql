-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 08, 2022 at 03:57 AM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mysql_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `cosovatchat`
--

CREATE TABLE `cosovatchat` (
  `MaDoDung` int(11) NOT NULL,
  `TenDoDung` varchar(30) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `SoLuongKhaDung` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cosovatchat`
--

INSERT INTO `cosovatchat` (`MaDoDung`, `TenDoDung`, `SoLuong`, `SoLuongKhaDung`) VALUES
(232986, 'MÁY ẢNH', 3, 1),
(454271, 'MICROPHONE', 5, 2),
(476093, 'LOA', 4, 4),
(476530, 'MÁY CHIẾU', 2, 0),
(521616, 'BÀN', 12, 8),
(590662, 'MÀN HÌNH', 3, 2),
(637670, 'LAPTOP', 4, 2),
(687545, 'GHẾ', 100, 38),
(888397, 'PHÔNG BẠT', 6, 4),
(934848, 'ĐÈN', 16, 5);

-- --------------------------------------------------------

--
-- Table structure for table `hoatdong_cosovatchat`
--

CREATE TABLE `hoatdong_cosovatchat` (
  `MaHoatDong` int(11) NOT NULL,
  `MaDoDung` int(11) NOT NULL,
  `SoLuong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hoatdong_cosovatchat`
--

INSERT INTO `hoatdong_cosovatchat` (`MaHoatDong`, `MaDoDung`, `SoLuong`) VALUES
(1, 232986, 1),
(1, 454271, 1),
(2, 888397, 1),
(2, 934848, 1),
(3, 476530, 1),
(3, 888397, 1),
(4, 232986, 1),
(4, 476093, 1),
(5, 637670, 1),
(6, 521616, 1),
(6, 687545, 1),
(6, 934848, 1),
(5, 888397, 3);

-- --------------------------------------------------------

--
-- Table structure for table `lichhoatdong`
--

CREATE TABLE `lichhoatdong` (
  `MaHoatDong` int(11) NOT NULL,
  `TenHoatDong` text NOT NULL,
  `ThoiGianBatDau` datetime NOT NULL,
  `ThoiGianKetThuc` datetime NOT NULL,
  `DuocDuyet` varchar(15) NOT NULL,
  `ThoiGianTao` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `MaNguoiTao` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lichhoatdong`
--

INSERT INTO `lichhoatdong` (`MaHoatDong`, `TenHoatDong`, `ThoiGianBatDau`, `ThoiGianKetThuc`, `DuocDuyet`, `ThoiGianTao`, `MaNguoiTao`) VALUES
(1, 'Họp tổ dân phố', '2022-11-05 07:00:00', '2022-11-05 09:00:00', 'Đã duyệt', '2022-11-05 08:14:26', '245676866712'),
(2, 'Họp hội phụ nữ', '2022-11-05 14:00:00', '2022-11-05 16:00:00', 'Đã duyệt', '2022-11-05 08:06:33', '123443215678'),
(3, 'Tuyên truyền về vệ sinh an toàn thực phẩm', '2022-11-05 08:00:00', '2022-11-05 10:00:00', 'Từ chối', '2022-11-05 08:10:08', '234554324567'),
(4, 'Tiêm chủng vắc-xin ngừa Covid', '2022-11-06 07:00:00', '2022-11-06 09:00:00', 'Chờ duyệt', '2022-11-05 08:10:08', '345612347654'),
(5, 'Tổ chức đám cưới', '2022-11-07 07:00:00', '2022-11-05 19:00:00', 'Đã duyệt', '2022-11-05 08:13:35', '334675689678'),
(6, 'Tổ chức bán sản phẩm sữa TH truemilk', '2022-11-08 07:00:00', '2022-11-08 10:00:00', 'Đã duyệt', '2022-11-05 08:14:14', '343467679090');

-- --------------------------------------------------------

--
-- Table structure for table `nhankhau`
--

CREATE TABLE `nhankhau` (
  `STT` int(11) NOT NULL,
  `HoTen` text NOT NULL,
  `BiDanh` text NOT NULL,
  `NgaySinh` date NOT NULL,
  `CCCD` varchar(12) NOT NULL,
  `NoiSinh` text NOT NULL,
  `GioiTinh` text NOT NULL,
  `NguyenQuan` text NOT NULL,
  `DanToc` text NOT NULL,
  `NoiThuongTru` text NOT NULL,
  `TonGiao` text NOT NULL,
  `QuocTich` text NOT NULL,
  `DiaChiHienNay` text NOT NULL,
  `NgheNghiep` text NOT NULL,
  `MaHoKhau` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhankhau`
--

INSERT INTO `nhankhau` (`STT`, `HoTen`, `BiDanh`, `NgaySinh`, `CCCD`, `NoiSinh`, `GioiTinh`, `NguyenQuan`, `DanToc`, `NoiThuongTru`, `TonGiao`, `QuocTich`, `DiaChiHienNay`, `NgheNghiep`, `MaHoKhau`) VALUES
(1, 'Thái Đình Đức', 'nguoi dan 1', '1990-06-11', '245676866712', 'Anh Sơn, Nghệ An', 'Nam', 'Anh Sơn, Nghệ An', 'Kinh', 'Vinh, Nghệ An', 'Không', 'Việt Nam', 'phố 7 phường La Khê', 'Công nhân', 245738373),
(2, 'Trần Tuấn Anh ', 'người dân 2', '2002-03-23', '123443215678', 'Phường Cửa Nam, thành phố Vinh, Nghệ An', 'Nam', 'vinh, nghệ an', 'Kinh', 'cửa nam, thành phố vinh, nghệ an', 'Hindu giáo', 'Việt Nam', 'phố 7 phường La Khê', 'Sinh viên', 123434567),
(3, 'Thái Thị Hồng Nhung', 'phó bí thư', '1992-06-11', '234554324567', 'Lam sơn, Thanh Hóa', 'Nữ', 'Lam Sơn, Thanh Hóa', 'Kinh', 'Trung đô, Lam sơn, thanh hóa', 'Ấn Độ giáo', 'Việt Nam', 'phố 7 phường La Khê', 'Giáo viên', 234565679),
(4, 'Nguyễn Hoàng Nam ', 'người dân 3', '2002-05-02', '345612347654', 'Hạ Long, Ninh Bình', 'Nam', 'Hạ long, ninh bình', 'Kinh', 'Thành phố Hạ long, ninh bình', 'Không', 'Việt Nam', 'phố 7 phường La Khê', 'Sinh viên', 234598768),
(5, 'Phan Tuấn Trâm', 'bí thư', '1995-07-12', '334675689678', 'Quận 1, Thành phố Hồ chí minh', 'Nữ', 'Quận 1, Thành phố Hồ chí minh', 'Kinh', 'Quận 3, Thành phố Hồ chí minh', 'Thiên Chúa giáo', 'Việt Nam', 'phố 7 phường La Khê', 'Nhân viên văn phòng', 342345679),
(6, 'Thái Thị Mỹ Hạnh', 'người dân 4', '2005-06-18', '343467679090', 'Quận Bình Thạch, thành phố Hồ Chí Minh', 'Nữ', 'Quận Bình Thạch, thành phố Hồ Chí Minh', 'Kinh', 'Quận Bình Thạch, thành phố Hồ Chí Minh', 'Không', 'Việt Nam', 'phố 7 phường La Khê', 'Học Sinh', 676789894);

-- --------------------------------------------------------

--
-- Table structure for table `sohokhau`
--

CREATE TABLE `sohokhau` (
  `STT` int(11) NOT NULL,
  `MaHoKhau` int(11) NOT NULL,
  `DiaChi` text NOT NULL DEFAULT 'phố 7 phường La Khê',
  `MaChuHo` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sohokhau`
--

INSERT INTO `sohokhau` (`STT`, `MaHoKhau`, `DiaChi`, `MaChuHo`) VALUES
(1, 245738373, 'phố 7 phường La Khê', '245676866712'),
(2, 123434567, 'phố 7 phường La Khê', '123443215678'),
(3, 234565679, 'phố 7 phường La Khê', '234554324567'),
(4, 234598768, 'phố 7 phường La Khê', '345612347654'),
(5, 342345679, 'phố 7 phường La Khê', '334675689678'),
(6, 676789894, 'phố 7 phường La Khê', '343467679090');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userId` int(11) NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL,
  `role` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `username`, `password`, `role`) VALUES
(1, 'admin', '12112312f12917a1571a51a714318914a10e14a18011f1c3', 'totruong');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cosovatchat`
--
ALTER TABLE `cosovatchat`
  ADD PRIMARY KEY (`MaDoDung`);

--
-- Indexes for table `hoatdong_cosovatchat`
--
ALTER TABLE `hoatdong_cosovatchat`
  ADD KEY `MaHoatDong` (`MaHoatDong`),
  ADD KEY `MaDoDung` (`MaDoDung`);

--
-- Indexes for table `lichhoatdong`
--
ALTER TABLE `lichhoatdong`
  ADD PRIMARY KEY (`MaHoatDong`),
  ADD KEY `MaNguoiTao` (`MaNguoiTao`);

--
-- Indexes for table `nhankhau`
--
ALTER TABLE `nhankhau`
  ADD PRIMARY KEY (`STT`),
  ADD UNIQUE KEY `CCCD_2` (`CCCD`),
  ADD KEY `CCCD` (`CCCD`),
  ADD KEY `MaHoKhau` (`MaHoKhau`);

--
-- Indexes for table `sohokhau`
--
ALTER TABLE `sohokhau`
  ADD PRIMARY KEY (`STT`),
  ADD UNIQUE KEY `MaHoKhau` (`MaHoKhau`),
  ADD KEY `MaChuHo` (`MaChuHo`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `nhankhau`
--
ALTER TABLE `nhankhau`
  MODIFY `STT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `sohokhau`
--
ALTER TABLE `sohokhau`
  MODIFY `STT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `hoatdong_cosovatchat`
--
ALTER TABLE `hoatdong_cosovatchat`
  ADD CONSTRAINT `hoatdong_cosovatchat_ibfk_1` FOREIGN KEY (`MaDoDung`) REFERENCES `cosovatchat` (`MaDoDung`),
  ADD CONSTRAINT `hoatdong_cosovatchat_ibfk_2` FOREIGN KEY (`MaHoatDong`) REFERENCES `lichhoatdong` (`MaHoatDong`);

--
-- Constraints for table `lichhoatdong`
--
ALTER TABLE `lichhoatdong`
  ADD CONSTRAINT `lichhoatdong_ibfk_1` FOREIGN KEY (`MaNguoiTao`) REFERENCES `nhankhau` (`CCCD`);

--
-- Constraints for table `nhankhau`
--
ALTER TABLE `nhankhau`
  ADD CONSTRAINT `nhankhau_ibfk_1` FOREIGN KEY (`MaHoKhau`) REFERENCES `sohokhau` (`MaHoKhau`);

--
-- Constraints for table `sohokhau`
--
ALTER TABLE `sohokhau`
  ADD CONSTRAINT `sohokhau_ibfk_1` FOREIGN KEY (`MaChuHo`) REFERENCES `nhankhau` (`CCCD`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
