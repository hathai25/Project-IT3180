-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 06, 2023 lúc 08:28 AM
-- Phiên bản máy phục vụ: 10.4.25-MariaDB
-- Phiên bản PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quan_ly_to`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cccd`
--

CREATE TABLE `cccd` (
  `ID` int(11) NOT NULL,
  `idNhankhau` int(11) NOT NULL,
  `CCCD` bigint(15) NOT NULL,
  `NgayCap` date DEFAULT '2020-01-01',
  `NoiCap` text DEFAULT 'Hà Nội'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `cccd`
--

INSERT INTO `cccd` (`ID`, `idNhankhau`, `CCCD`, `NgayCap`, `NoiCap`) VALUES
(6, 1, 123456789258, '2020-01-01', 'Hà Nội'),
(12, 25, 147445611111, '2020-01-01', 'Hà Nội'),
(15, 23, 26202244, '0000-00-00', ''),
(17, 30, 12345678, '2020-01-01', 'Hà Nội'),
(19, 32, 25678916, '2020-01-01', 'Hà Nội'),
(25, 51, 147896325456, '2020-01-01', 'Hà Nội'),
(26, 53, 26202001235, '2020-01-01', 'Hà Nội'),
(28, 57, 12345678915, '2020-01-01', 'Hà Nội'),
(29, 59, 26202001456, '2020-01-01', 'Hà Nội');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cosovatchat`
--

CREATE TABLE `cosovatchat` (
  `MaDoDung` int(11) NOT NULL,
  `TenDoDung` varchar(30) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `SoLuongKhaDung` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `cosovatchat`
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
(610329, 'Laptop', 100, 100),
(637670, 'LAPTOP', 4, 2),
(687545, 'GHẾ', 100, 100),
(888397, 'PHÔNG BẠT', 6, 4),
(934848, 'ĐÈN', 16, 16);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoatdong_cosovatchat`
--

CREATE TABLE `hoatdong_cosovatchat` (
  `MaHoatDong` int(11) NOT NULL,
  `MaDoDung` int(11) NOT NULL,
  `SoLuong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khaitu`
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
-- Cấu trúc bảng cho bảng `lichhoatdong`
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
-- Cấu trúc bảng cho bảng `nhankhau`
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

--
-- Đang đổ dữ liệu cho bảng `nhankhau`
--

INSERT INTO `nhankhau` (`ID`, `HoTen`, `BiDanh`, `NgaySinh`, `NoiSinh`, `GioiTinh`, `NguyenQuan`, `DanToc`, `NoiThuongTru`, `TonGiao`, `QuocTich`, `DiaChiHienNay`, `NgheNghiep`) VALUES
(1, 'bach', 'hh', '2023-01-05', 'hh', 'hhh', 'hhh', 'hh', 'hh', 'hh', 'Việt Nahhhhm', 'hh', 'sinh viên'),
(17, 'ddd', 'dd', '2023-01-03', 'dd', 'Nam', 'd', 'd', 'd', 'd', 'd', 'd', 'd'),
(18, 'bbb', 'bb', '2023-02-11', 'bb', 'Nam', 'b', 'bb', 'b', 'b', 'b', 'b', 'b'),
(19, 'vvv', 'vv', '2023-01-21', 'vv', 'Nam', 'vv', 'vv', 'vv', 'vv', 'vv', 'vvv', 'vv'),
(20, 'vv', 'vv', '2023-02-01', 'v', 'Nam', 'v', 'v', 'v', 'v', 'v', 'v', 'v'),
(21, 'nnn', 'nn', '2023-01-25', 'nn', 'Nam', 'nn', 'nn', 'nnnn', 'nn', 'nn', 'nnn', 'n'),
(22, 'ggg', 'gg', '2023-01-25', 'ggg', 'Nam', 'gg', 'gg', 'gg', 'gg', 'gg', 'g', 'gg'),
(23, 'ddd', 'ddd', '2023-02-01', 'dd', 'Nam', 'd', 'ddd', 'd', 'd', 'dd', 'd', 'dd'),
(24, 'tttt', 'tt', '2023-01-25', 'tttt', 'Nam', 'ttt', 'tttt', 'ttt', 'tttt', 'tttt', 'tttt', 'ttt'),
(25, 'bach bach bach', 'bbb', '2023-01-25', 'bb', 'Nam', 'bbb', 'bb', 'bb', 'bb', 'bb', 'bbb', 'bb'),
(26, 'cay vl luôn', 'vvv', '2023-02-02', 'vv', 'Nam', 'v', 'vv', 'v', 'v', 'v', 'v', 'v'),
(27, 'ttt', 'tt', '2023-01-28', 'tt', 'Nam', 'ttt', 'tt', 'tt', 'tt', 'tt', 'ttt', 'ttt'),
(28, 'tao cố lắm rồi', 'ff', '2023-01-17', 'ff', 'Nam', 'f', 'f', 'f', 'f', 'f', 'f', 'f'),
(29, 'cố lần 2', 'f', '2023-01-11', 'f', 'Nam', 'nn', 'f', 'nn', 'f', 'f', 'nnn', 'f'),
(30, 'vlllllllllllllllll', '1111', '2023-01-19', '111', 'Nam', 'nnn', '111', 'nnn', 'nnn', 'nn', 'nnnn', 'nnn'),
(31, 'freefire', 'ff', '2023-01-18', 'fff', 'Nam', 'f', 'ff', 'ff', 'ff', 'ff', 'fff', 'fff'),
(32, 'bbbbbach', 'h', '2023-01-18', 'h', 'Nam', 'h', 'h', 'h', 'h', 'h', 'h', 'h'),
(33, 'vvvvbbbbbbb', 'b', '2023-01-17', 'b', 'Nam', 'b', 'b', 'b', 'bb', 'b', 'b', 'bb'),
(34, 'rrrr', 'rr', '2023-01-26', 'rr', 'Nam', 'rr', 'rr', 'rr', 'rrr', 'rr', 'rr', 'rrr'),
(35, 'bbb', 'bb', '2023-01-24', 'bb', 'Nam', 'b', 'bb', 'bbbb', 'bbb', 'bbb', 'bbb', 'bb'),
(36, 'nguyễn xuân bách', 'v', '2023-01-17', 'vv', 'Nam', 'v', 'v', 'vv', 'v', 'v', 'v', 'vvv'),
(37, 'bách đaya', 't', '2023-01-18', 'tt', 'Nam', 'tt', 't', 't', 'Không', 'Việt Nam', 't', 't'),
(38, 'chạy thử lần n', 't', '2023-01-18', 't', 'Nam', 't', 't', 'tt', 'Không', 'Việt Nam', 't', 't'),
(39, 'ddddd', 't', '2023-01-10', 't', 'Nam', 'tt', 't', 't', 't', 't', 't', 't'),
(40, 'đang chạy thử', 'tttt', '2023-01-18', 'tt', 'Nam', 'ttt', 'tttt', 'tttttt', 'tttt', 'tttt', 'tttt', 'ttttt'),
(41, 'nfnnfnffnfnn', 't', '2023-02-01', 'ttt', 'Nam', 'tttt', 't', 'tt', 'Không', 'Việt Nam', 'ttt', 't'),
(42, 'nfnnfnffnfnn', 't', '2023-02-01', 'ttt', 'Nam', 'tttt', 't', 'tt', 'Không', 'Việt Nam', 'ttt', 't'),
(43, 'lại là thầy đây', 'vI', '2023-02-01', '  V', 'Nam', 'c', ' c', 'c', 'c', 'c', 'c', 'c'),
(44, 'ggggggggg', 'gg', '2023-01-23', 'g', 'Nam', 'g', 'gg', 'g', 'g', 'g', 'g', 'g'),
(45, 'ggggggggg', 'gg', '2023-01-23', 'g', 'Nam', 'g', 'gg', 'g', 'g', 'g', 'g', 'g'),
(46, 'bachhhhrrggvggg', 'ggg', '2023-01-25', 'gg', 'Nam', 't', 'gg', 't', 'gg', 'gg', 't', 'ggg'),
(47, 'bachhhhrrggvggg', 'ggg', '2023-01-25', 'gg', 'Nam', 't', 'gg', 't', 'gg', 'gg', 't', 'ggg'),
(48, 'gggggggg', 'ggg', '2023-01-23', 'ggg', 'Nam', 'gg', 'gg', 'gg', 'gg', 'gg', 'ggg', 'ggg'),
(49, 'nguyễn xuân bách', 'bách', '2023-01-23', 'vp', 'Nam', 'vp', 'kinh', 'hn', 'ko', 'vn', 'hn', 'sv'),
(50, 'nguyễn xuân bách', 'bách', '2023-01-23', 'vp', 'Nam', 'vp', 'kinh', 'hn', 'ko', 'vn', 'hn', 'sv'),
(51, 'nguyễn XUân Bahc', 'v', '2023-01-10', 'v', 'Nam', 'v', 'v', 'v', 'v', 'v', 'v', 'v'),
(52, 'nguyễn XUân Bahc', 'v', '2023-01-10', 'v', 'Nam', 'v', 'v', 'v', 'v', 'v', 'v', 'v'),
(53, 'ttttttttt', 't', '2023-01-17', 't', 'Nam', 't', 't', 't', 't', 't', 't', 't'),
(54, 'ttttttttt', 't', '2023-01-17', 't', 'Nam', 't', 't', 't', 't', 't', 't', 't'),
(55, 'vvvv', 'v', '2023-01-18', 'v', 'Nam', 'v', 'v', 'v', 'v', 'v', 'v', 'v'),
(56, 'vvvv', 'v', '2023-01-18', 'v', 'Nam', 'v', 'v', 'v', 'v', 'v', 'v', 'v'),
(57, 'Nguyễn Xuân Bách', 'hh', '2023-02-01', 'hhh', 'Nam', 'hh', 'hh', 'hh', 'hh', 'hh', 'hh', 'hhhhh'),
(58, 'Nguyễn Xuân Bách', 'hh', '2023-02-01', 'hhh', 'Nam', 'hh', 'hh', 'hh', 'hh', 'hh', 'hh', 'hhhhh'),
(59, 'bách đây chứ đâu', 'b', '2023-01-10', 'b', 'Nam', 'b', 'b', 'b', 'b', 'b', 'b', 'b'),
(60, 'bách đây chứ đâu', 'b', '2023-01-10', 'b', 'Nam', 'b', 'b', 'b', 'b', 'b', 'b', 'b');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sohokhau`
--

CREATE TABLE `sohokhau` (
  `ID` int(11) NOT NULL,
  `MaHoKhau` int(11) NOT NULL,
  `DiaChi` text NOT NULL DEFAULT 'phố 7 phường La Khê',
  `MaChuHo` int(12) NOT NULL,
  `NgayLap` date NOT NULL,
  `NgayChuyenDi` date NOT NULL,
  `LyDoChuyen` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tamtru`
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
-- Cấu trúc bảng cho bảng `tamvang`
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
-- Cấu trúc bảng cho bảng `thanhviencuaho`
--

CREATE TABLE `thanhviencuaho` (
  `idNhanKhau` int(11) NOT NULL,
  `idHoKhau` int(11) NOT NULL,
  `quanHeVoiChuHo` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `userId` int(11) NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL,
  `role` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`userId`, `username`, `password`, `role`) VALUES
(1, 'admin', '12112312f12917a1571a51a714318914a10e14a18011f1c3', 'totruong'),
(2, 'admin2', '1c81421581e91c31901591a819a1b717d18416d1da1b9109', 'canbo'),
(3, 'cocc', '1a11191391a31011c31ae11510118d1f51591a81a21b1102', 'canbo');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `cccd`
--
ALTER TABLE `cccd`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `idNhankhau_2` (`idNhankhau`),
  ADD KEY `idNhankhau` (`idNhankhau`);

--
-- Chỉ mục cho bảng `cosovatchat`
--
ALTER TABLE `cosovatchat`
  ADD PRIMARY KEY (`MaDoDung`);

--
-- Chỉ mục cho bảng `hoatdong_cosovatchat`
--
ALTER TABLE `hoatdong_cosovatchat`
  ADD PRIMARY KEY (`MaHoatDong`,`MaDoDung`),
  ADD KEY `MaHoatDong` (`MaHoatDong`),
  ADD KEY `MaDoDung` (`MaDoDung`);

--
-- Chỉ mục cho bảng `khaitu`
--
ALTER TABLE `khaitu`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `idNguoiChet` (`idNguoiChet`),
  ADD KEY `idNguoiKhai` (`idNguoiKhai`);

--
-- Chỉ mục cho bảng `lichhoatdong`
--
ALTER TABLE `lichhoatdong`
  ADD PRIMARY KEY (`MaHoatDong`),
  ADD KEY `MaNguoiTao` (`MaNguoiTao`);

--
-- Chỉ mục cho bảng `nhankhau`
--
ALTER TABLE `nhankhau`
  ADD PRIMARY KEY (`ID`);

--
-- Chỉ mục cho bảng `sohokhau`
--
ALTER TABLE `sohokhau`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `MaHoKhau` (`MaHoKhau`),
  ADD KEY `MaChuHo` (`MaChuHo`);

--
-- Chỉ mục cho bảng `tamtru`
--
ALTER TABLE `tamtru`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `idNhankhau` (`idNhankhau`);

--
-- Chỉ mục cho bảng `tamvang`
--
ALTER TABLE `tamvang`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `idNhankhau` (`idNhankhau`);

--
-- Chỉ mục cho bảng `thanhviencuaho`
--
ALTER TABLE `thanhviencuaho`
  ADD PRIMARY KEY (`idNhanKhau`,`idHoKhau`),
  ADD KEY `idHoKhau` (`idHoKhau`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `cccd`
--
ALTER TABLE `cccd`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT cho bảng `nhankhau`
--
ALTER TABLE `nhankhau`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `cccd`
--
ALTER TABLE `cccd`
  ADD CONSTRAINT `cccd_ibfk_1` FOREIGN KEY (`idNhankhau`) REFERENCES `nhankhau` (`ID`);

--
-- Các ràng buộc cho bảng `hoatdong_cosovatchat`
--
ALTER TABLE `hoatdong_cosovatchat`
  ADD CONSTRAINT `hoatdong_cosovatchat_ibfk_1` FOREIGN KEY (`MaHoatDong`) REFERENCES `lichhoatdong` (`MaHoatDong`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `hoatdong_cosovatchat_ibfk_2` FOREIGN KEY (`MaDoDung`) REFERENCES `cosovatchat` (`MaDoDung`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `khaitu`
--
ALTER TABLE `khaitu`
  ADD CONSTRAINT `khaitu_ibfk_1` FOREIGN KEY (`idNguoiKhai`) REFERENCES `nhankhau` (`ID`),
  ADD CONSTRAINT `khaitu_ibfk_2` FOREIGN KEY (`idNguoiChet`) REFERENCES `nhankhau` (`ID`);

--
-- Các ràng buộc cho bảng `lichhoatdong`
--
ALTER TABLE `lichhoatdong`
  ADD CONSTRAINT `lichhoatdong_ibfk_1` FOREIGN KEY (`MaNguoiTao`) REFERENCES `nhankhau` (`ID`);

--
-- Các ràng buộc cho bảng `sohokhau`
--
ALTER TABLE `sohokhau`
  ADD CONSTRAINT `sohokhau_ibfk_1` FOREIGN KEY (`MaChuHo`) REFERENCES `nhankhau` (`ID`);

--
-- Các ràng buộc cho bảng `tamtru`
--
ALTER TABLE `tamtru`
  ADD CONSTRAINT `tamtru_ibfk_1` FOREIGN KEY (`idNhankhau`) REFERENCES `nhankhau` (`ID`);

--
-- Các ràng buộc cho bảng `tamvang`
--
ALTER TABLE `tamvang`
  ADD CONSTRAINT `tamvang_ibfk_1` FOREIGN KEY (`idNhankhau`) REFERENCES `nhankhau` (`ID`);

--
-- Các ràng buộc cho bảng `thanhviencuaho`
--
ALTER TABLE `thanhviencuaho`
  ADD CONSTRAINT `thanhviencuaho_ibfk_2` FOREIGN KEY (`idHoKhau`) REFERENCES `sohokhau` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `thanhviencuaho_ibfk_3` FOREIGN KEY (`idNhanKhau`) REFERENCES `nhankhau` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
