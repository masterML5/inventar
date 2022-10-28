-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 28, 2022 at 02:38 PM
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
  `napomena` longtext DEFAULT NULL,
  `uneo` varchar(50) NOT NULL,
  `editovao` varchar(50) DEFAULT NULL,
  `uneo_ts` timestamp NOT NULL DEFAULT current_timestamp(),
  `edit_ts` datetime DEFAULT NULL,
  `datum` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `inventar`
--

INSERT INTO `inventar` (`id_inventar`, `id_kategorija`, `id_lokacija`, `id_racunar`, `id_stampac`, `naziv`, `lokacija`, `kolicina`, `napomena`, `uneo`, `editovao`, `uneo_ts`, `edit_ts`, `datum`) VALUES
(1, 2, 18, NULL, NULL, '', '', 0, NULL, '', NULL, '0000-00-00 00:00:00', NULL, '');

-- --------------------------------------------------------

--
-- Table structure for table `kategorija`
--

CREATE TABLE `kategorija` (
  `id_kategorija` int(11) NOT NULL,
  `naziv` varchar(50) NOT NULL,
  `uneo` varchar(50) NOT NULL,
  `editovao` varchar(50) DEFAULT NULL,
  `uneo_ts` timestamp NOT NULL DEFAULT current_timestamp(),
  `edit_ts` timestamp NULL DEFAULT NULL,
  `datum` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kategorija`
--

INSERT INTO `kategorija` (`id_kategorija`, `naziv`, `uneo`, `editovao`, `uneo_ts`, `edit_ts`, `datum`) VALUES
(1, 'Racunari', '', NULL, '2022-10-28 12:37:32', NULL, ''),
(2, 'Toneri', '', NULL, '2022-10-28 12:37:32', NULL, ''),
(3, 'Monitori', '', NULL, '2022-10-28 12:37:32', NULL, ''),
(4, 'Kablovi', '', NULL, '2022-10-28 12:37:32', NULL, ''),
(5, 'Adapteri', '', NULL, '2022-10-28 12:37:32', NULL, ''),
(6, 'Komponente', '', NULL, '2022-10-28 12:37:32', NULL, ''),
(7, 'Stampaci', '', NULL, '2022-10-28 12:37:32', NULL, ''),
(8, 'Mrezna oprema', '', NULL, '2022-10-28 12:37:32', NULL, ''),
(9, 'UPS', '', NULL, '2022-10-28 12:37:32', NULL, ''),
(10, 'Serveri', '', NULL, '2022-10-28 12:37:32', NULL, '');

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
  `createdAt` timestamp NOT NULL DEFAULT current_timestamp(),
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
  `naziv` varchar(100) NOT NULL DEFAULT 'lokacija_noname',
  `uneo` varchar(50) NOT NULL,
  `editovao` varchar(50) DEFAULT NULL,
  `uneo_ts` timestamp NOT NULL DEFAULT current_timestamp(),
  `edit_ts` timestamp NULL DEFAULT NULL,
  `datum` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lokacija`
--

INSERT INTO `lokacija` (`id_lokacija`, `naziv`, `uneo`, `editovao`, `uneo_ts`, `edit_ts`, `datum`) VALUES
(1, 'Prodaja', '', NULL, '2022-10-28 12:35:21', NULL, ''),
(2, 'Finansije', '', NULL, '2022-10-28 12:35:21', NULL, ''),
(3, 'MGR', '', NULL, '2022-10-28 12:35:21', NULL, ''),
(4, 'Opsti sektor', '', NULL, '2022-10-28 12:35:21', NULL, ''),
(5, 'Magacin sirovina', '', NULL, '2022-10-28 12:35:21', NULL, ''),
(6, 'Restoran', '', NULL, '2022-10-28 12:35:21', NULL, ''),
(7, 'Tehnolozi', '', NULL, '2022-10-28 12:35:21', NULL, ''),
(8, 'PJ Bombon', '', NULL, '2022-10-28 12:35:21', NULL, ''),
(9, 'PJ Pecivo', '', NULL, '2022-10-28 12:35:21', NULL, ''),
(10, 'IT Sektor', '', NULL, '2022-10-28 12:35:21', NULL, ''),
(11, 'PJ Cokolada', '', NULL, '2022-10-28 12:35:21', NULL, ''),
(12, 'Tehnicki magacin', '', NULL, '2022-10-28 12:35:21', NULL, ''),
(13, 'Prodavnice', '', NULL, '2022-10-28 12:35:21', NULL, ''),
(14, 'Porta', '', NULL, '2022-10-28 12:35:21', NULL, ''),
(15, 'Transport', '', NULL, '2022-10-28 12:35:21', NULL, ''),
(16, 'Energetika', '', NULL, '2022-10-28 12:35:21', NULL, ''),
(17, 'Radionica', '', NULL, '2022-10-28 12:35:21', NULL, ''),
(18, 'IT Sektor rezervna oprema', '', NULL, '2022-10-28 12:35:21', NULL, '');

-- --------------------------------------------------------

--
-- Table structure for table `podkategorija`
--

CREATE TABLE `podkategorija` (
  `id_podkategorija` int(11) NOT NULL,
  `id_kategorija` int(11) NOT NULL,
  `naziv` varchar(50) DEFAULT NULL,
  `uneo` varchar(50) NOT NULL,
  `editovao` varchar(50) DEFAULT NULL,
  `uneo_ts` datetime NOT NULL DEFAULT current_timestamp(),
  `edit_ts` datetime DEFAULT NULL,
  `datum` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `prijem`
--

CREATE TABLE `prijem` (
  `id_prijem` int(11) NOT NULL,
  `broj_prijem` int(11) NOT NULL,
  `id_kategorija` int(11) NOT NULL,
  `id_podkategorija` int(11) DEFAULT NULL,
  `id_lokacija` int(11) NOT NULL,
  `id_racunar` int(11) DEFAULT NULL,
  `id_stampac` int(11) DEFAULT NULL,
  `naziv` varchar(100) NOT NULL DEFAULT '',
  `kolicina` int(11) NOT NULL,
  `napomena` longtext DEFAULT NULL,
  `uneo` varchar(50) NOT NULL,
  `editovao` varchar(50) DEFAULT NULL,
  `uneo_ts` timestamp NOT NULL DEFAULT current_timestamp(),
  `edit_ts` timestamp NULL DEFAULT NULL,
  `datum` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `racunari`
--

CREATE TABLE `racunari` (
  `id_racunar` int(11) NOT NULL,
  `id_kategorija` int(11) NOT NULL,
  `id_lokacija` int(11) NOT NULL DEFAULT 0,
  `inv_broj` int(11) DEFAULT NULL,
  `specifikacija` longtext DEFAULT NULL,
  `os` enum('Windows 98','Windows XP','Windows 7','Windows 8','Windows 10','Windows 11','Linux') NOT NULL,
  `office` varchar(50) NOT NULL DEFAULT '',
  `korisnik` varchar(50) NOT NULL DEFAULT '',
  `ip_adresa` varchar(50) NOT NULL DEFAULT '',
  `mac_adresa` varchar(50) NOT NULL,
  `os_key` varchar(50) NOT NULL DEFAULT '',
  `office_key` varchar(50) NOT NULL DEFAULT '',
  `datum` varchar(50) NOT NULL DEFAULT '',
  `uneo` varchar(50) NOT NULL DEFAULT '',
  `editovao` varchar(50) NOT NULL DEFAULT '',
  `uneo_ts` timestamp NOT NULL DEFAULT current_timestamp(),
  `edit_ts` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `racunari`
--

INSERT INTO `racunari` (`id_racunar`, `id_kategorija`, `id_lokacija`, `inv_broj`, `specifikacija`, `os`, `office`, `korisnik`, `ip_adresa`, `mac_adresa`, `os_key`, `office_key`, `datum`, `uneo`, `editovao`, `uneo_ts`, `edit_ts`) VALUES
(1, 1, 7, 12321, 'test', 'Windows 10', 'Office 2019', 'test', '10.11.124.22', 'AE:A3:A2:A2:A1', 'asdasd--asdasdas-asdasd', 'asdas-asd-asda', 'ff', '2022-10-28', '', '2022-10-28 08:46:20', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `stampaci`
--

CREATE TABLE `stampaci` (
  `id_stampaci` int(11) NOT NULL,
  `id_kategorija` int(11) NOT NULL,
  `id_lokacija` int(11) NOT NULL,
  `inv_broj` int(11) DEFAULT NULL,
  `model` varchar(50) NOT NULL,
  `marka` varchar(50) NOT NULL,
  `toner` varchar(50) NOT NULL,
  `vrsta` enum('Monograf','Fotokopir','Multifunkcijski') DEFAULT NULL,
  `mrezni` enum('mrezni','usb') DEFAULT NULL,
  `ip_adresa` varchar(50) DEFAULT NULL,
  `lokacijaKorisnik` varchar(50) NOT NULL,
  `uneo` varchar(50) NOT NULL,
  `editovao` varchar(50) DEFAULT NULL,
  `uneo_ts` datetime NOT NULL DEFAULT current_timestamp(),
  `edit_ts` datetime DEFAULT NULL,
  `datum` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stampaci`
--

INSERT INTO `stampaci` (`id_stampaci`, `id_kategorija`, `id_lokacija`, `inv_broj`, `model`, `marka`, `toner`, `vrsta`, `mrezni`, `ip_adresa`, `lokacijaKorisnik`, `uneo`, `editovao`, `uneo_ts`, `edit_ts`, `datum`) VALUES
(1, 7, 6, 55555, '111', 'HP', 'CF259', 'Multifunkcijski', 'mrezni', '10.11.124.22', 'Danijela', '', NULL, '2022-10-27 09:00:19', NULL, NULL),
(2, 7, 2, 21321, 'LBP351X', 'Canon', 'CRG039', 'Monograf', 'mrezni', '10.11.124.106', 'Pozadi', 'ff', NULL, '2022-10-27 09:00:20', NULL, '2022-10-26'),
(7, 7, 2, 213221, 'LBP351X', 'Canon', 'CRG039', 'Monograf', 'mrezni', '10.11.124.106', 'Pozadi', 'ff', NULL, '2022-10-27 09:06:20', NULL, '2022-10-27');

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
  ADD PRIMARY KEY (`id_kategorija`),
  ADD UNIQUE KEY `naziv` (`naziv`);

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
-- Indexes for table `podkategorija`
--
ALTER TABLE `podkategorija`
  ADD PRIMARY KEY (`id_podkategorija`),
  ADD KEY `FK_podkategorija` (`id_kategorija`);

--
-- Indexes for table `prijem`
--
ALTER TABLE `prijem`
  ADD PRIMARY KEY (`id_prijem`),
  ADD UNIQUE KEY `broj_prijem` (`broj_prijem`),
  ADD KEY `FK_prijem_kategorija` (`id_kategorija`),
  ADD KEY `FK_prijem_podkategorija` (`id_podkategorija`),
  ADD KEY `FK_prijem_lokacija` (`id_lokacija`),
  ADD KEY `FK_prijem_racunar` (`id_racunar`),
  ADD KEY `FK_prijem_stampaci` (`id_stampac`);

--
-- Indexes for table `racunari`
--
ALTER TABLE `racunari`
  ADD PRIMARY KEY (`id_racunar`),
  ADD UNIQUE KEY `inv_broj` (`inv_broj`),
  ADD KEY `FK_kategorija` (`id_kategorija`),
  ADD KEY `FK_lokacija_racunari` (`id_lokacija`);

--
-- Indexes for table `stampaci`
--
ALTER TABLE `stampaci`
  ADD PRIMARY KEY (`id_stampaci`),
  ADD UNIQUE KEY `inv_broj` (`inv_broj`),
  ADD KEY `FK_lokacija_stampaci` (`id_lokacija`),
  ADD KEY `FK_kategorija_stampaci` (`id_kategorija`);

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
  MODIFY `id_kategorija` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

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
-- AUTO_INCREMENT for table `podkategorija`
--
ALTER TABLE `podkategorija`
  MODIFY `id_podkategorija` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `prijem`
--
ALTER TABLE `prijem`
  MODIFY `id_prijem` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `racunari`
--
ALTER TABLE `racunari`
  MODIFY `id_racunar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `stampaci`
--
ALTER TABLE `stampaci`
  MODIFY `id_stampaci` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

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
-- Constraints for table `podkategorija`
--
ALTER TABLE `podkategorija`
  ADD CONSTRAINT `FK_podkategorija` FOREIGN KEY (`id_kategorija`) REFERENCES `kategorija` (`id_kategorija`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `prijem`
--
ALTER TABLE `prijem`
  ADD CONSTRAINT `FK_prijem_kategorija` FOREIGN KEY (`id_kategorija`) REFERENCES `kategorija` (`id_kategorija`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_prijem_lokacija` FOREIGN KEY (`id_lokacija`) REFERENCES `lokacija` (`id_lokacija`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_prijem_podkategorija` FOREIGN KEY (`id_podkategorija`) REFERENCES `podkategorija` (`id_podkategorija`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_prijem_racunar` FOREIGN KEY (`id_racunar`) REFERENCES `racunari` (`id_racunar`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_prijem_stampaci` FOREIGN KEY (`id_stampac`) REFERENCES `stampaci` (`id_stampaci`) ON DELETE NO ACTION ON UPDATE CASCADE;

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
  ADD CONSTRAINT `FK_kategorija_stampaci` FOREIGN KEY (`id_kategorija`) REFERENCES `kategorija` (`id_kategorija`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_lokacija_stampaci` FOREIGN KEY (`id_lokacija`) REFERENCES `lokacija` (`id_lokacija`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
