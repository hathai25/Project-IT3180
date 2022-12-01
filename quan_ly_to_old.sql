-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 29, 2022 at 05:15 AM
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
-- Database: `quan_ly_to_old`
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
(3, 476530, 1),
(3, 888397, 1);

-- --------------------------------------------------------

--
-- Table structure for table `lichhoatdong`
--

CREATE TABLE `lichhoatdong` (
  `MaHoatDong` int(11) NOT NULL,
  `TenHoatDong` text NOT NULL,
  `ThoiGianBatDau` text NOT NULL,
  `ThoiGianKetThuc` text NOT NULL,
  `DuocDuyet` varchar(15) NOT NULL,
  `MaNguoiTao` varchar(12) NOT NULL,
  `ThoiGianTao` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lichhoatdong`
--

INSERT INTO `lichhoatdong` (`MaHoatDong`, `TenHoatDong`, `ThoiGianBatDau`, `ThoiGianKetThuc`, `DuocDuyet`, `MaNguoiTao`, `ThoiGianTao`) VALUES
(3, 'Tuyên truyền về vệ sinh an toàn thực phẩm', '2022-11-16 8:15', '2022-11-16 10:50', 'Chấp nhận', '234554324567', '2022-11-16 10:5'),
(272512, 'ádda', '2022-11-03 8:03', '2022-11-04 9:02', 'Chưa duyệt', '43111', '2022-11-29 11:10'),
(288774, 'vvv', '2022-11-23 07:05', '2022-11-12 8:15', 'Chưa duyệt', '43111', '2022-11-29 11:02'),
(499368, 'mmmm', '2022-11-02 07:5', '2022-11-17 08:10', 'Chưa duyệt', '43111', '2022-11-16 10:5'),
(718990, 'dxfcg gh', '2022-11-24 07:00:00', '2022-11-30 09:00:00', 'Chấp nhận', '234554324567', '2022-11-16 10:5'),
(764440, 'vvvv', '2022-11-25 09:02', '2022-11-11 5:15', 'Chưa duyệt', '43111', '2022-11-29 11:05');

-- --------------------------------------------------------

--
-- Table structure for table `nhankhau`
--

CREATE TABLE `nhankhau` (
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

INSERT INTO `nhankhau` (`HoTen`, `BiDanh`, `NgaySinh`, `CCCD`, `NoiSinh`, `GioiTinh`, `NguyenQuan`, `DanToc`, `NoiThuongTru`, `TonGiao`, `QuocTich`, `DiaChiHienNay`, `NgheNghiep`, `MaHoKhau`) VALUES
('Thái Thị Hồng Nhung', 'phó bí thư', '1992-06-22', '234554324567', 'Lam sơn, Thanh Hóa', 'Nữ', 'Lam Sơn, Thanh Hóa', 'Kinh', 'Trung đô, Lam sơn, thanh hóa', 'Ấn Độ giáo', 'Việt Nam', 'phố 7 phường La Khê', 'Giáo viên', 234565679),
('Thái Thị Hồng Nhung', 'phó bí thư', '1992-06-22', '43111', 'Lam sơn, Thanh Hóa', 'Nữ', 'Lam Sơn, Thanh Hóa', 'Kinh', 'Trung đô, Lam sơn, thanh hóa', 'Ấn Độ giáo', 'Việt Nam', 'phố 7 phường La Khê', 'Giáo viên', 234565679);

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
(3, 234565679, 'phố 7 phường La Khê', '234554324567');

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
(1, 'admin', '12112312f12917a1571a51a714318914a10e14a18011f1c3', 'totruong'),
(2, 'admin2', '1c81421581e91c31901591a819a1b717d18416d1da1b9109', 'canbo');

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
  ADD PRIMARY KEY (`MaHoatDong`,`MaDoDung`),
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
  ADD PRIMARY KEY (`CCCD`),
  ADD KEY `MaHoKhau` (`MaHoKhau`);

--
-- Indexes for table `sohokhau`
--
ALTER TABLE `sohokhau`
  ADD PRIMARY KEY (`MaHoKhau`),
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
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `hoatdong_cosovatchat`
--
ALTER TABLE `hoatdong_cosovatchat`
  ADD CONSTRAINT `hoatdong_cosovatchat_ibfk_1` FOREIGN KEY (`MaHoatDong`) REFERENCES `lichhoatdong` (`MaHoatDong`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `hoatdong_cosovatchat_ibfk_2` FOREIGN KEY (`MaDoDung`) REFERENCES `cosovatchat` (`MaDoDung`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `lichhoatdong`
--
ALTER TABLE `lichhoatdong`
  ADD CONSTRAINT `lichhoatdong_ibfk_1` FOREIGN KEY (`MaNguoiTao`) REFERENCES `nhankhau` (`CCCD`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `nhankhau`
--
ALTER TABLE `nhankhau`
  ADD CONSTRAINT `nhankhau_ibfk_1` FOREIGN KEY (`MaHoKhau`) REFERENCES `sohokhau` (`MaHoKhau`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sohokhau`
--
ALTER TABLE `sohokhau`
  ADD CONSTRAINT `sohokhau_ibfk_1` FOREIGN KEY (`MaChuHo`) REFERENCES `nhankhau` (`CCCD`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
