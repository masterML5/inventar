-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 21, 2022 at 01:40 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `it-inventar`
--

-- --------------------------------------------------------

--
-- Table structure for table `inventar`
--

CREATE TABLE `inventar` (
  `id_inventar` int(11) NOT NULL,
  `id_kategorija` int(11) NOT NULL,
  `id_lokacija` int(11) NOT NULL,
  `id_racunar` int(11) DEFAULT NULL,
  `id_stampac` int(11) DEFAULT NULL,
  `naziv` varchar(50) NOT NULL,
  `lokacija` varchar(50) NOT NULL,
  `kolicina` int(11) NOT NULL,
  `uneo` varchar(50) NOT NULL,
  `editovao` varchar(50) DEFAULT NULL,
  `uneo_ts` datetime NOT NULL,
  `edit_ts` datetime DEFAULT NULL,
  `datum` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `inventar`
--

INSERT INTO `inventar` (`id_inventar`, `id_kategorija`, `id_lokacija`, `id_racunar`, `id_stampac`, `naziv`, `lokacija`, `kolicina`, `uneo`, `editovao`, `uneo_ts`, `edit_ts`, `datum`) VALUES
(1, 2, 18, NULL, NULL, '', '', 0, '', NULL, '0000-00-00 00:00:00', NULL, '');

-- --------------------------------------------------------

--
-- Table structure for table `kategorija`
--

CREATE TABLE `kategorija` (
  `id_kategorija` int(11) NOT NULL,
  `naziv` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kategorija`
--

INSERT INTO `kategorija` (`id_kategorija`, `naziv`) VALUES
(1, 'Racunari'),
(2, 'Toneri'),
(3, 'Monitori'),
(4, 'Kablovi'),
(5, 'Adapteri'),
(6, 'Komponente'),
(7, 'Stampaci'),
(8, 'Mrezna oprema'),
(9, 'UPS'),
(10, 'Serveri');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `createdAt` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `editedAt` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `username`, `password`, `email`, `name`, `lastname`, `createdAt`, `editedAt`) VALUES
(1, 'milos', '$2a$12$/1AcxR/D6.Rknfweg6f55u8PydKuduUZX65NXmBOnlLThCwvgvXCW', 'milos.jelic@pionir.rs', NULL, NULL, '2022-10-20 06:51:03', NULL),
(2, 'administrator', '$2a$12$0.typcQ.vAsDVinnIQG3ium0MGggvr6IxOd5BP.cbpPwYlE36x8Ha', 'milos.jelic@pionir.rs', 'Admin', 'Admin', '2022-10-20 08:15:44', NULL),
(18, 'testt', '$2a$12$LgWi2Atp6.dslt9sK1zVj.Xlxj5q7NNCitdHC6FAOfcFTBQbd96RG', 'test@test.com', 'test', 'test', '2022-10-21 06:54:29', NULL),
(19, 'ff', '$2a$12$ji3pwj.1kJZmDFQ7y0Ywfu1tZBZTb3OyaLx6Yi4Oy40oYlHCN6AvW', 'ff', 'ff', 'ff', '2022-10-21 07:01:37', NULL),
(20, 'tt', '$2a$12$OfGbxo2y081w9.1/l6/vx.40cMoemsoXUOUa69Dls0F/oT8P/Hs5G', 'tt', 'test', 'tt', '2022-10-21 07:03:14', NULL),
(21, 'testtt', '$2a$12$3gzvOwhsYEmhxz/hqfiX9uc1x9.wpCqwQTa6nfKs4R4aovL/yAu4C', 'test', 'asd', 'asd', '2022-10-21 11:35:10', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `lokacija`
--

CREATE TABLE `lokacija` (
  `id_lokacija` int(11) NOT NULL,
  `naziv` varchar(100) NOT NULL DEFAULT 'lokacija_noname'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lokacija`
--

INSERT INTO `lokacija` (`id_lokacija`, `naziv`) VALUES
(1, 'Prodaja'),
(2, 'Finansije'),
(3, 'MGR'),
(4, 'Opsti sektor'),
(5, 'Magacin sirovina'),
(6, 'Restoran'),
(7, 'Tehnolozi'),
(8, 'PJ Bombon'),
(9, 'PJ Pecivo'),
(10, 'IT Sektor'),
(11, 'PJ Cokolada'),
(12, 'Tehnicki magacin'),
(13, 'Prodavnice'),
(14, 'Porta'),
(15, 'Transport'),
(16, 'Energetika'),
(17, 'Radionica'),
(18, 'IT Sektor rezervna oprema');

-- --------------------------------------------------------

--
-- Table structure for table `racunari`
--

CREATE TABLE `racunari` (
  `id_racunar` int(11) NOT NULL,
  `id_kategorija` int(11) NOT NULL,
  `id_lokacija` int(11) NOT NULL DEFAULT 0,
  `inv_broj` int(11) NOT NULL,
  `specifikacija` varchar(255) DEFAULT '',
  `os` enum('Windows 98','Windows XP','Windows 7','Windows 8','Windows 10','Windows 11','Linux') NOT NULL,
  `office` varchar(50) NOT NULL DEFAULT '',
  `korisnik` varchar(50) NOT NULL DEFAULT '',
  `ip_adresa` varchar(50) NOT NULL DEFAULT '',
  `os_key` varchar(50) NOT NULL DEFAULT '',
  `office_key` varchar(50) NOT NULL DEFAULT '',
  `datum` varchar(50) NOT NULL DEFAULT '',
  `uneo` varchar(50) NOT NULL DEFAULT '',
  `editovao` varchar(50) NOT NULL DEFAULT '',
  `uneo_ts` timestamp NULL DEFAULT NULL,
  `edit_ts` timestamp NULL DEFAULT NULL,
  `lokacija` varchar(50) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `stampaci`
--

CREATE TABLE `stampaci` (
  `id_stampaci` int(11) NOT NULL,
  `id_lokacija` int(11) NOT NULL,
  `inv_broj` int(11) DEFAULT NULL,
  `model` varchar(50) NOT NULL,
  `marka` varchar(50) NOT NULL,
  `toner` varchar(50) NOT NULL,
  `vrsta` enum('Monograf','Fotokopir','Multifunkcijski') DEFAULT NULL,
  `mrezni` enum('mrezni','usb') DEFAULT NULL,
  `ip_adresa` varchar(50) DEFAULT NULL,
  `lokacija` varchar(50) NOT NULL,
  `uneo` varchar(50) NOT NULL,
  `editovao` varchar(50) DEFAULT NULL,
  `uneo_ts` datetime NOT NULL,
  `edit_ts` datetime DEFAULT NULL,
  `datum` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `inventar`
--
ALTER TABLE `inventar`
  ADD PRIMARY KEY (`id_inventar`),
  ADD KEY `FK_kategorija_inventar` (`id_kategorija`),
  ADD KEY `FK_lokacija_inventar` (`id_lokacija`),
  ADD KEY `FK_racunar_inventar` (`id_racunar`),
  ADD KEY `FK_stampac_inventar` (`id_stampac`);

--
-- Indexes for table `kategorija`
--
ALTER TABLE `kategorija`
  ADD PRIMARY KEY (`id_kategorija`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD UNIQUE KEY `Username` (`username`) USING BTREE;

--
-- Indexes for table `lokacija`
--
ALTER TABLE `lokacija`
  ADD PRIMARY KEY (`id_lokacija`);

--
-- Indexes for table `racunari`
--
ALTER TABLE `racunari`
  ADD PRIMARY KEY (`id_racunar`),
  ADD KEY `FK_kategorija` (`id_kategorija`),
  ADD KEY `FK_lokacija_racunari` (`id_lokacija`);

--
-- Indexes for table `stampaci`
--
ALTER TABLE `stampaci`
  ADD PRIMARY KEY (`id_stampaci`),
  ADD KEY `FK_lokacija_stampaci` (`id_lokacija`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `inventar`
--
ALTER TABLE `inventar`
  MODIFY `id_inventar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `kategorija`
--
ALTER TABLE `kategorija`
  MODIFY `id_kategorija` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `lokacija`
--
ALTER TABLE `lokacija`
  MODIFY `id_lokacija` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `racunari`
--
ALTER TABLE `racunari`
  MODIFY `id_racunar` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `stampaci`
--
ALTER TABLE `stampaci`
  MODIFY `id_stampaci` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `inventar`
--
ALTER TABLE `inventar`
  ADD CONSTRAINT `FK_kategorija_inventar` FOREIGN KEY (`id_kategorija`) REFERENCES `kategorija` (`id_kategorija`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_lokacija_inventar` FOREIGN KEY (`id_lokacija`) REFERENCES `lokacija` (`id_lokacija`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_racunar_inventar` FOREIGN KEY (`id_racunar`) REFERENCES `racunari` (`id_racunar`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_stampac_inventar` FOREIGN KEY (`id_stampac`) REFERENCES `stampaci` (`id_stampaci`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `racunari`
--
ALTER TABLE `racunari`
  ADD CONSTRAINT `FK_kategorija` FOREIGN KEY (`id_kategorija`) REFERENCES `kategorija` (`id_kategorija`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_lokacija_racunari` FOREIGN KEY (`id_lokacija`) REFERENCES `lokacija` (`id_lokacija`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `stampaci`
--
ALTER TABLE `stampaci`
  ADD CONSTRAINT `FK_lokacija_stampaci` FOREIGN KEY (`id_lokacija`) REFERENCES `lokacija` (`id_lokacija`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
