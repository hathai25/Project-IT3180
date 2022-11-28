-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2022 at 04:30 PM
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
-- Database: `quan_ly_to`
--

-- --------------------------------------------------------

--
-- Table structure for table `cccd`
--

CREATE TABLE `cccd` (
  `ID` int(11) NOT NULL,
  `idNhankhau` int(11) NOT NULL,
  `CCCD` int(11) NOT NULL,
  `NgayCap` date NOT NULL,
  `NoiCap` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(134658, 'ADF', 86, 6345),
(213354, 'SDAF', 35, 123),
(232986, 'MÁY ẢNH', 3, 1),
(265756, 'Nến', 50, 50),
(315468, 'ASDFLJK', 123, 123),
(336721, 'Bàn chải sạch', 5, 5),
(476093, 'LOA', 4, 4),
(476530, 'MÁY CHIẾU', 2, 0),
(521616, 'BÀN', 12, 8),
(590662, 'MÀN HÌNH', 3, 3),
(610329, 'Laptop', 100, 100),
(637670, 'LAPTOP', 4, 2),
(687545, 'GHẾ', 100, 100),
(888397, 'PHÔNG BẠT', 6, 4),
(934848, 'ĐÈN', 16, 16);

-- --------------------------------------------------------

--
-- Table structure for table `hoatdong_cosovatchat`
--

CREATE TABLE `hoatdong_cosovatchat` (
  `MaHoatDong` int(11) NOT NULL,
  `MaDoDung` int(11) NOT NULL,
  `SoLuong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `khaitu`
--

CREATE TABLE `khaitu` (
  `ID` int(11) NOT NULL,
  `idNguoiKhai` int(11) NOT NULL,
  `idNguoiChet` int(11) NOT NULL,
  `ngayKhai` date NOT NULL,
  `lyDoChet` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `MaNguoiTao` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `nhankhau`
--

CREATE TABLE `nhankhau` (
  `ID` int(11) NOT NULL,
  `HoTen` text NOT NULL,
  `BiDanh` text DEFAULT NULL,
  `NgaySinh` date NOT NULL,
  `NoiSinh` text NOT NULL,
  `GioiTinh` text NOT NULL,
  `NguyenQuan` text NOT NULL,
  `DanToc` text NOT NULL,
  `NoiThuongTru` text NOT NULL,
  `TonGiao` text DEFAULT NULL,
  `QuocTich` text DEFAULT 'Việt Nam',
  `DiaChiHienNay` text NOT NULL,
  `NgheNghiep` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `sohokhau`
--

CREATE TABLE `sohokhau` (
  `MaHoKhau` int(11) NOT NULL,
  `DiaChi` text NOT NULL DEFAULT 'phố 7 phường La Khê',
  `MaChuHo` int(12) NOT NULL,
  `NgayLap` date NOT NULL,
  `NgayChuyenDi` date NOT NULL,
  `LyDoChuyen` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tamtru`
--

CREATE TABLE `tamtru` (
  `ID` int(11) NOT NULL,
  `idNhankhau` int(11) NOT NULL,
  `sdtNgDangKi` int(11) NOT NULL,
  `noiTamTru` text NOT NULL,
  `tuNgay` date NOT NULL,
  `denNgay` date NOT NULL,
  `lido` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tamvang`
--

CREATE TABLE `tamvang` (
  `ID` int(11) NOT NULL,
  `idNhankhau` int(11) NOT NULL,
  `tuNgay` date NOT NULL,
  `denNgay` date NOT NULL,
  `lydo` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `thanhviencuaho`
--

CREATE TABLE `thanhviencuaho` (
  `idNhanKhau` int(11) NOT NULL,
  `idHoKhau` int(11) NOT NULL,
  `quanHeVoiChuHo` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(2, 'admin2', '1c81421581e91c31901591a819a1b717d18416d1da1b9109', 'canbo'),
(3, 'cocc', '1a11191391a31011c31ae11510118d1f51591a81a21b1102', 'canbo');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cccd`
--
ALTER TABLE `cccd`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `idNhankhau_2` (`idNhankhau`),
  ADD KEY `idNhankhau` (`idNhankhau`);

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
-- Indexes for table `khaitu`
--
ALTER TABLE `khaitu`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `idNguoiChet` (`idNguoiChet`),
  ADD KEY `idNguoiKhai` (`idNguoiKhai`);

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
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `sohokhau`
--
ALTER TABLE `sohokhau`
  ADD PRIMARY KEY (`MaHoKhau`),
  ADD KEY `MaChuHo` (`MaChuHo`);

--
-- Indexes for table `tamtru`
--
ALTER TABLE `tamtru`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `idNhankhau` (`idNhankhau`);

--
-- Indexes for table `tamvang`
--
ALTER TABLE `tamvang`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `idNhankhau` (`idNhankhau`);

--
-- Indexes for table `thanhviencuaho`
--
ALTER TABLE `thanhviencuaho`
  ADD PRIMARY KEY (`idNhanKhau`,`idHoKhau`),
  ADD KEY `idHoKhau` (`idHoKhau`);

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
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cccd`
--
ALTER TABLE `cccd`
  ADD CONSTRAINT `cccd_ibfk_1` FOREIGN KEY (`idNhankhau`) REFERENCES `nhankhau` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `hoatdong_cosovatchat`
--
ALTER TABLE `hoatdong_cosovatchat`
  ADD CONSTRAINT `hoatdong_cosovatchat_ibfk_1` FOREIGN KEY (`MaHoatDong`) REFERENCES `lichhoatdong` (`MaHoatDong`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `hoatdong_cosovatchat_ibfk_2` FOREIGN KEY (`MaDoDung`) REFERENCES `cosovatchat` (`MaDoDung`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `khaitu`
--
ALTER TABLE `khaitu`
  ADD CONSTRAINT `khaitu_ibfk_1` FOREIGN KEY (`idNguoiKhai`) REFERENCES `nhankhau` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `khaitu_ibfk_2` FOREIGN KEY (`idNguoiChet`) REFERENCES `nhankhau` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `lichhoatdong`
--
ALTER TABLE `lichhoatdong`
  ADD CONSTRAINT `lichhoatdong_ibfk_1` FOREIGN KEY (`MaNguoiTao`) REFERENCES `nhankhau` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sohokhau`
--
ALTER TABLE `sohokhau`
  ADD CONSTRAINT `sohokhau_ibfk_1` FOREIGN KEY (`MaChuHo`) REFERENCES `nhankhau` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tamtru`
--
ALTER TABLE `tamtru`
  ADD CONSTRAINT `tamtru_ibfk_1` FOREIGN KEY (`idNhankhau`) REFERENCES `nhankhau` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tamvang`
--
ALTER TABLE `tamvang`
  ADD CONSTRAINT `tamvang_ibfk_1` FOREIGN KEY (`idNhankhau`) REFERENCES `nhankhau` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `thanhviencuaho`
--
ALTER TABLE `thanhviencuaho`
  ADD CONSTRAINT `thanhviencuaho_ibfk_1` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhankhau` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `thanhviencuaho_ibfk_2` FOREIGN KEY (`idHoKhau`) REFERENCES `sohokhau` (`MaHoKhau`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
