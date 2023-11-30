-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: nuevoamanecer
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tpsicologo`
--

DROP TABLE IF EXISTS `tpsicologo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tpsicologo` (
  `iIdPsicologo` int NOT NULL,
  `sUserNamePsicologo` varchar(100) NOT NULL,
  `sPasswordPsicologo` varbinary(255) NOT NULL,
  `sNombrePsicologo` varchar(50) NOT NULL,
  `sApellidoPsicologo` varchar(100) NOT NULL,
  `iTelefonoPsicologo` varchar(15) NOT NULL,
  `sEmailPsicologo` varchar(50) DEFAULT NULL,
  `bActivo` bit(1) NOT NULL,
  PRIMARY KEY (`iIdPsicologo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tpsicologo`
--

LOCK TABLES `tpsicologo` WRITE;
/*!40000 ALTER TABLE `tpsicologo` DISABLE KEYS */;
INSERT INTO `tpsicologo` VALUES (1,'laura.flores',_binary '12345','Laura','Flores','8117480711','laura.flores@nuevoamanecer.edu.mx',_binary ''),(2,'anahi',_binary '12345','Anahi','','8171234406','anahi@nuevoamanecer.edu.mx',_binary ''),(3,'karen',_binary '12345','Karen','','8124394446','karen@nuevoamanecer.edu.mx',_binary ''),(4,'rosy',_binary '12345','Rosy','','8183588896','rosy@nuevoamanecer.edu.mx',_binary ''),(5,'eliezer.cavazos','','Eliezitor','Cavazos','8124394445','eliezer@gmail.com',_binary '\0'),(6,'yuvi.thinker','','Yuvi','Thinkerman','12351415','a00834121@tec.mx',_binary '\0');
/*!40000 ALTER TABLE `tpsicologo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-30  9:59:54
