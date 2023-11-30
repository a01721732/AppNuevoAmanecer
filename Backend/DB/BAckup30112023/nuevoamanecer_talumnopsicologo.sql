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
-- Table structure for table `talumnopsicologo`
--

DROP TABLE IF EXISTS `talumnopsicologo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `talumnopsicologo` (
  `iIdAlumnoPsicologo` int NOT NULL AUTO_INCREMENT,
  `iIdPsicologo` int NOT NULL,
  `iIdAlumno` int NOT NULL,
  `dFinCicloEscolar` datetime DEFAULT NULL,
  PRIMARY KEY (`iIdAlumnoPsicologo`),
  KEY `fk_tPsicolo_tAlumno_idx` (`iIdPsicologo`),
  KEY `fk_tAlumno_tPsicologo_idx` (`iIdAlumno`),
  CONSTRAINT `fk_tAlumno_tPsicologo` FOREIGN KEY (`iIdAlumno`) REFERENCES `talumno` (`iIdAlumno`),
  CONSTRAINT `fk_tPsicologo_tAlumno` FOREIGN KEY (`iIdPsicologo`) REFERENCES `tpsicologo` (`iIdPsicologo`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `talumnopsicologo`
--

LOCK TABLES `talumnopsicologo` WRITE;
/*!40000 ALTER TABLE `talumnopsicologo` DISABLE KEYS */;
INSERT INTO `talumnopsicologo` VALUES (5,1,8,NULL),(6,1,9,NULL),(7,2,10,NULL),(9,3,12,NULL),(10,3,13,NULL),(11,3,14,NULL),(12,3,15,NULL),(13,3,16,NULL),(14,1,17,NULL),(15,1,18,NULL),(16,2,17,NULL),(17,2,18,NULL),(18,3,17,NULL),(19,3,18,NULL),(20,4,17,NULL),(21,4,18,NULL),(22,1,19,NULL),(34,2,11,NULL),(35,1,16,NULL),(36,1,15,NULL);
/*!40000 ALTER TABLE `talumnopsicologo` ENABLE KEYS */;
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
