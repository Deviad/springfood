-- MySQL dump 10.16  Distrib 10.1.22-MariaDB, for osx10.11 (x86_64)
--
-- Host: localhost    Database: springfood
-- ------------------------------------------------------
-- Server version	10.1.22-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_username_idx` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (7,'Davide3','$2a$10$Ij5.NtORvQaIdgRWHoxcmux70Y65W9I3K.y8NuCjBfuKqgqYqjDd.'),(13,'dvd.pugliese@gmail.com','$2a$10$S8vaJV6aGg/q/RSnTeU3wOEwHbgRB6bOftEuK6IEw.XAKhALIvnDa'),(34,'Davide5','$2a$10$rxfUtjC29vYQrvZFxfmMpuLtA.OK31lQ1YhQi2wbJvdLXEb5oVH9.'),(36,'Davide6','$2a$10$WMED7p1TBQk067oQfDCh6.y6ntqu2cwEBzy3zxvz6HM4kh2vdncHu'),(38,'Davide7','$2a$10$3IqvYOWynSoChm7jO2HWbeTP3t/b1oschi4UOyszwOj8lmls3PHtC'),(39,'Davide10','$2a$10$BP7kGNEBp1kduXmg/vXb.uQkfAy7BS/5Xso20h1e5z3if9UHbCeRS'),(41,'Davide11','$2a$10$RnO4hafnrz/KMnRuMb6IzuInnT2FGrW092g00SwHGWVqRpYuLFwMO'),(43,'Davide12','$2a$10$Ym4IMqkNJimBl.oP5mUwxe1UbkSLjGj2rE1fj1aHWkpAMvHrQc/12'),(44,'Davide2020','$2a$10$rgBMZ3UqDHZETwlluZIPbeMdodKquzPUPnsKxavpBtvimaNxjY5IO'),(52,'Davide2021','$2a$10$LIFYXk3jB.T.UiP5RojdGOPFBe4syRECqCVKJX2pNFwxOeu7ejPoa'),(54,'Davide2022','$2a$10$ckpM6wEdgPj5SNkgdhIcjO8qfB1bbwGxXb9SP9JLeuDYm/70Rkx3i'),(55,'Davide204','$2a$10$F9PSYEMDneinuksQjS3HsuA8GSL2YawkY/W0dIDjHJKDx2tC8.3HS'),(57,'davide@gmail.com','$2a$10$1mq6kvkxx3Phr1XZLJRZu.rBE3WGMnLV3S7FWN0QnE/15ep4x89P2'),(58,'davideasdas@gmail.com','$2a$10$aTn5hImZmncVcGmKsS3AHuqEWtByx2e2DWLxY.4Znm5qOPyiuRpSy'),(59,'davideasdaasdsads@gmail.com','$2a$10$kqoCsW/k5aCtuzo8bRO8kO7YUmM02Z1BebqoahjMGQD440x/d3KRK'),(64,'asdasdas@gmail.com','$2a$10$C4EWitBhfXimZn50H9CXROIjRxf05ggh85y541KIb4zkyRTpCVt3O'),(66,'mannaggia@gmail.com','$2a$10$1ixLVYjy7/3Yr0WVmDhVAud2ld1leMUyzIh55nT27ZRDkeYHB01ci');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-03  8:49:57
