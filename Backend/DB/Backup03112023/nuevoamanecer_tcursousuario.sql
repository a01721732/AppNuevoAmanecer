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
-- Table structure for table `tcursousuario`
--

DROP TABLE IF EXISTS `tcursousuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tcursousuario` (
  `iIdCurso` int NOT NULL,
  `iIdPsicologo` int NOT NULL,
  `iIdAlumno` int NOT NULL,
  PRIMARY KEY (`iIdCurso`),
  KEY `FK_tPsicologo_tCursoUsuario` (`iIdPsicologo`),
  KEY `FK_tAlumno_tCursoUsuario` (`iIdAlumno`),
  CONSTRAINT `FK_tAlumno_tCursoUsuario` FOREIGN KEY (`iIdAlumno`) REFERENCES `talumno` (`iIdAlumno`),
  CONSTRAINT `FK_tCurso_tCursoUsuario` FOREIGN KEY (`iIdCurso`) REFERENCES `tcurso` (`iIdCurso`),
  CONSTRAINT `FK_tPsicologo_tCursoUsuario` FOREIGN KEY (`iIdPsicologo`) REFERENCES `tpsicologo` (`iIdPsicologo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tcursousuario`
--

LOCK TABLES `tcursousuario` WRITE;
/*!40000 ALTER TABLE `tcursousuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `tcursousuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-03  8:18:12
