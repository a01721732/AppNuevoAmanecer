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
-- Table structure for table `talumno`
--

DROP TABLE IF EXISTS `talumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `talumno` (
  `iIdAlumno` int NOT NULL AUTO_INCREMENT,
  `sNombre` varchar(50) NOT NULL,
  `sApellido` varchar(50) NOT NULL,
  `iEdad` tinyint DEFAULT NULL,
  `iIdNivelCognitivo` tinyint NOT NULL,
  `bActivo` bit(1) NOT NULL,
  PRIMARY KEY (`iIdAlumno`),
  KEY `FK_tNivelCognitivo` (`iIdNivelCognitivo`),
  CONSTRAINT `FK_tNivelCognitivo` FOREIGN KEY (`iIdNivelCognitivo`) REFERENCES `tnivelcognitivo` (`iIdNivelCognitivo`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `talumno`
--

LOCK TABLES `talumno` WRITE;
/*!40000 ALTER TABLE `talumno` DISABLE KEYS */;
INSERT INTO `talumno` VALUES (8,'Francisco Javier','Castañeda Dávila',4,4,_binary ''),(9,'Lucas','Ramos del Rio',5,3,_binary ''),(10,'Jesús Patricio','Jerónimo Sánchez',11,4,_binary ''),(11,'Leonel Ponce','Legaspe',5,3,_binary ''),(12,'Owen Lorenzo','González Robles',5,3,_binary ''),(13,'Emiliano','Torres Rivas',5,4,_binary ''),(14,'Christian Javier','Paz del Ángel',6,3,_binary ''),(15,'Rubén','Torres Hernández',11,3,_binary ''),(16,'Cristo','Gaspar Mora',10,4,_binary ''),(17,'Eliezer','Cavazos Rochin',18,4,_binary ''),(18,'Carlos','Cuilty',20,4,_binary ''),(19,'Alumno','Alumno',100,-1,_binary ''),(20,'Prueba','Prueba',100,1,_binary '\0'),(21,'Prueba2','Prueba2',12,1,_binary '\0'),(22,'Prueba3','Prueba3',14,1,_binary '\0');
/*!40000 ALTER TABLE `talumno` ENABLE KEYS */;
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
