-- --------------------------------------------------------
-- Host:                         localhost
-- Versi server:                 5.7.24 - MySQL Community Server (GPL)
-- OS Server:                    Win64
-- HeidiSQL Versi:               9.5.0.5332
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

-- Membuang data untuk tabel mandalahayu.administrasi: ~3 rows (lebih kurang)
/*!40000 ALTER TABLE `administrasi` DISABLE KEYS */;
INSERT INTO `administrasi` (`id_adm`, `id_mrd`, `spp`, `uang_pkl`, `dp_kbm`, `sisa_kbm`) VALUES
	('101042016', '40042016', 200000, 200000, 200000, 0),
	('102042017', '41042017', 200000, 0, 200000, 200000),
	('103042018', '42042018', 200000, 200000, 200000, 200000);
/*!40000 ALTER TABLE `administrasi` ENABLE KEYS */;

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

-- Membuang data untuk tabel mandalahayu.murid: ~4 rows (lebih kurang)
/*!40000 ALTER TABLE `murid` DISABLE KEYS */;
INSERT INTO `murid` (`id_mrd`, `nama`, `pend`, `kelas`, `jurusan`) VALUES
	('40042016', 'Andi', 'X', 'TKJ-01', 'Teknik Komputer Jaringan'),
	('41042017', 'Agung', 'XI', 'TKR-02', 'Teknik Kendaraan Ringan'),
	('42042018', 'Anton', 'XII', 'TAB-03', 'Teknik Alat Berat');
/*!40000 ALTER TABLE `murid` ENABLE KEYS */;

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

-- Membuang data untuk tabel mandalahayu.pembayaran: ~6 rows (lebih kurang)
/*!40000 ALTER TABLE `pembayaran` DISABLE KEYS */;
INSERT INTO `pembayaran` (`no`, `id_adm`, `id_pet`, `kategori`, `harga`, `tanggal`) VALUES
	(1, '101042016', '12019', 'SPP', 200000, '2019-04-22'),
	(2, '101042016', '12019', 'Uang Pangkal', 200000, '2019-04-22'),
	(3, '101042016', '12019', 'DP KBM', 200000, '2019-04-22'),
	(4, '101042016', '12019', 'KBM', 200000, '2019-04-22');
/*!40000 ALTER TABLE `pembayaran` ENABLE KEYS */;

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

-- Membuang data untuk tabel mandalahayu.petugas: ~3 rows (lebih kurang)
/*!40000 ALTER TABLE `petugas` DISABLE KEYS */;
INSERT INTO `petugas` (`no`, `id_pet`, `nama`, `sandi`, `nip`, `status`) VALUES
	(1, '0012019', 'Dika Pramudia', '2083818f4c13c7c8c74805a8b6344e5542973c3ed376fa40d1df21cf0263052e', '201643501903', 'Admin'),
	(2, '0022019', 'Egi Ramadhan', '1361539f0f553f5be2c814e464cc663c6f9e5c8400ce617ad5c1d15f24b90775', '201643501938', 'User'),
	(3, '0032019', 'Apriyanti', '377fe18f63621ffe03eab42a2925a88a42faf612e400221cf552525a2b0bdd02', '201643500583', 'User'),
	(5, '0042019', 'rohman', '9d773b12bd7ee84d46381046d439ae146798572cd59beab8638f79844d4d074d', '201643501904', 'Admin');
/*!40000 ALTER TABLE `petugas` ENABLE KEYS */;

-- membuang struktur untuk table mandalahayu.sekolah
CREATE TABLE IF NOT EXISTS `sekolah` (
  `pend` varchar(10) NOT NULL,
  `hrg_spp` int(100) NOT NULL,
  `hrg_upkl` int(100) NOT NULL,
  `hrg_dkbm` int(100) NOT NULL,
  `hrg_skbm` int(100) NOT NULL,
  PRIMARY KEY (`pend`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Membuang data untuk tabel mandalahayu.sekolah: ~3 rows (lebih kurang)
/*!40000 ALTER TABLE `sekolah` DISABLE KEYS */;
INSERT INTO `sekolah` (`pend`, `hrg_spp`, `hrg_upkl`, `hrg_dkbm`, `hrg_skbm`) VALUES
	('X', 4, 4, 4, 4),
	('XI', 4, 4, 4, 4),
	('XII', 4, 4, 4, 4);
/*!40000 ALTER TABLE `sekolah` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
