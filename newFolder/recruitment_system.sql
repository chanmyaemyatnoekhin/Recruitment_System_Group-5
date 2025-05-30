-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- ホスト: 127.0.0.1
-- 生成日時: 2025-05-29 11:54:09
-- サーバのバージョン： 10.4.32-MariaDB
-- PHP のバージョン: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- データベース: `recruitment_system`
--

-- --------------------------------------------------------

--
-- テーブルの構造 `recruitment`
--

CREATE TABLE `recruitment` (
  `recruitment_id` int(11) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `phone_number` varchar(100) NOT NULL,
  `nrc_number` varchar(100) NOT NULL,
  `address` varchar(255) NOT NULL,
  `marital_status` enum('SINGLE','MARRIED') NOT NULL,
  `gender` enum('MALE','FEMALE') NOT NULL,
  `position` varchar(100) NOT NULL,
  `upload_resume` varchar(255) NOT NULL,
  `education_background` varchar(255) NOT NULL,
  `status` enum('PENDING','APPROVE','RETURN') NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `office_branch` varchar(100) NOT NULL,
  `admin_comment` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- テーブルのデータのダンプ `recruitment`
--

INSERT INTO `recruitment` (`recruitment_id`, `full_name`, `phone_number`, `nrc_number`, `address`, `marital_status`, `gender`, `position`, `upload_resume`, `education_background`, `status`, `user_id`, `created_at`, `office_branch`, `admin_comment`) VALUES
(3, 'MAMA', '09768457100', '', 'Yangon', 'SINGLE', 'FEMALE', 'Software Developer', 'C:\\Group-5-Recruitment-System\\RecruitmentSystem\\uploadedFiles\\MAMA_1748495257511_', 'High School', 'PENDING', 5, '2025-05-29 05:07:37', 'Yangon', ''),
(4, 'MgMg', '09455658339', '', 'yangon', 'SINGLE', 'MALE', 'Designer', 'C:\\Group-5-Recruitment-System\\GROUP-5File\\MgMg_1748501112727_', 'Bachelor\'s Degree', 'PENDING', 6, '2025-05-29 06:45:12', 'Mandalay', '');

-- --------------------------------------------------------

--
-- テーブルの構造 `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` enum('ADMIN','USER') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- テーブルのデータのダンプ `user`
--

INSERT INTO `user` (`user_id`, `name`, `email`, `password`, `role`, `created_at`) VALUES
(1, '', 'coffee.cm2000@gmail.com', 'coffee2000', 'ADMIN', '2025-05-23 08:31:43'),
(2, '', 'admin@gmail.com', 'admin123', 'ADMIN', '2025-05-23 08:33:27'),
(3, '', 'baozi.cm2000@gmail.com', 'baozi2000', 'USER', '2025-05-23 08:34:39'),
(4, '', 'winewine1623@gmail.com', 'wine1623', 'USER', '2025-05-23 08:35:30'),
(5, 'Ma Ma', 'mama@gmail.com', 'ma1234', 'USER', '2025-05-26 08:10:43'),
(6, 'Mg Mg ', 'mg@gmail.com', 'mg1234', 'USER', '2025-05-26 08:14:53'),
(7, 'asd', 'asd@gmail.com', 'asddd', 'USER', '2025-05-27 09:11:05');

--
-- ダンプしたテーブルのインデックス
--

--
-- テーブルのインデックス `recruitment`
--
ALTER TABLE `recruitment`
  ADD PRIMARY KEY (`recruitment_id`);

--
-- テーブルのインデックス `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- ダンプしたテーブルの AUTO_INCREMENT
--

--
-- テーブルの AUTO_INCREMENT `recruitment`
--
ALTER TABLE `recruitment`
  MODIFY `recruitment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- テーブルの AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
