-- --------------------------------------------------------
-- Host:                         localhost
-- Versi server:                 8.0.15 - MySQL Community Server - GPL
-- OS Server:                    Win64
-- HeidiSQL Versi:               10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Membuang struktur basisdata untuk mandalahayu
CREATE DATABASE IF NOT EXISTS `mandalahayu` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `mandalahayu`;

-- membuang struktur untuk table mandalahayu.administrasi
CREATE TABLE IF NOT EXISTS `administrasi` (
  `id_adm` varchar(50) NOT NULL,
  `id_mrd` varchar(50) NOT NULL,
  `spp` int(100) NOT NULL,
  `uang_pkl` int(100) NOT NULL,
  `dp_kbm` int(100) NOT NULL,
  `sisa_kbm` int(100) NOT NULL,
  PRIMARY KEY (`id_adm`),
  KEY `id_murid` (`id_mrd`),
  CONSTRAINT `FK_administrasi_murid` FOREIGN KEY (`id_mrd`) REFERENCES `murid` (`id_mrd`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Pengeluaran data tidak dipilih.

-- membuang struktur untuk table mandalahayu.murid
CREATE TABLE IF NOT EXISTS `murid` (
  `id_mrd` varchar(50) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `pend` varchar(10) NOT NULL,
  `kelas` varchar(15) NOT NULL,
  `jurusan` varchar(40) NOT NULL,
  PRIMARY KEY (`id_mrd`),
  KEY `pend` (`pend`),
  CONSTRAINT `FK_murid_sekolah` FOREIGN KEY (`pend`) REFERENCES `sekolah` (`pend`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Pengeluaran data tidak dipilih.

-- membuang struktur untuk table mandalahayu.pembayaran
CREATE TABLE IF NOT EXISTS `pembayaran` (
  `no` int(100) NOT NULL AUTO_INCREMENT,
  `id_adm` varchar(50) NOT NULL,
  `id_pet` varchar(50) NOT NULL,
  `kategori` varchar(30) NOT NULL,
  `harga` int(100) NOT NULL,
  `tanggal` date NOT NULL,
  PRIMARY KEY (`no`),
  KEY `id_adm` (`id_adm`),
  CONSTRAINT `FK_pembayaran_administrasi` FOREIGN KEY (`id_adm`) REFERENCES `administrasi` (`id_adm`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Pengeluaran data tidak dipilih.

-- membuang struktur untuk table mandalahayu.petugas
CREATE TABLE IF NOT EXISTS `petugas` (
  `no` int(10) NOT NULL AUTO_INCREMENT,
  `id_pet` varchar(15) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `sandi` varchar(200) NOT NULL,
  `nip` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`id_pet`),
  UNIQUE KEY `Index 2` (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Pengeluaran data tidak dipilih.

-- membuang struktur untuk table mandalahayu.sekolah
CREATE TABLE IF NOT EXISTS `sekolah` (
  `pend` varchar(10) NOT NULL,
  `hrg_spp` int(100) NOT NULL,
  `hrg_upkl` int(100) NOT NULL,
  `hrg_dkbm` int(100) NOT NULL,
  `hrg_skbm` int(100) NOT NULL,
  PRIMARY KEY (`pend`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Pengeluaran data tidak dipilih.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
