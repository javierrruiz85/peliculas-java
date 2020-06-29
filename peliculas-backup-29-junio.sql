-- MySQL dump 10.13  Distrib 5.7.30, for Linux (x86_64)
--
-- Host: localhost    Database: peliculas
-- ------------------------------------------------------
-- Server version	5.7.30-0ubuntu0.18.04.1

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
-- Current Database: `peliculas`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `peliculas` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci */;

USE `peliculas`;

--
-- Table structure for table `distribuidora`
--

DROP TABLE IF EXISTS `distribuidora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `distribuidora` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `distribuidora_UN` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distribuidora`
--

LOCK TABLES `distribuidora` WRITE;
/*!40000 ALTER TABLE `distribuidora` DISABLE KEYS */;
INSERT INTO `distribuidora` VALUES (6,'columbia'),(4,'disney'),(2,'paramount'),(5,'pixar'),(7,'tristar'),(1,'univeral'),(3,'warner bros');
/*!40000 ALTER TABLE `distribuidora` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genero`
--

DROP TABLE IF EXISTS `genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genero` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `genero_UN` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero`
--

LOCK TABLES `genero` WRITE;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` VALUES (1,'accion'),(14,'animacion'),(2,'aventura'),(3,'belica'),(7,'ciencia ficcion'),(8,'comedia'),(4,'drama'),(10,'fantasia'),(12,'historico'),(9,'musical'),(13,'policiaco'),(5,'romance'),(11,'suspense'),(6,'terror');
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peliculas`
--

DROP TABLE IF EXISTS `peliculas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `peliculas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `duracion` int(11) NOT NULL,
  `anio` int(11) NOT NULL,
  `caratula` varchar(255) COLLATE latin1_spanish_ci NOT NULL DEFAULT 'https://picsum.photos/50/75',
  `id_distribuidora` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `peliculas_UN` (`nombre`),
  KEY `FK_distribuidora` (`id_distribuidora`),
  CONSTRAINT `FK_distribuidora` FOREIGN KEY (`id_distribuidora`) REFERENCES `distribuidora` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peliculas`
--

LOCK TABLES `peliculas` WRITE;
/*!40000 ALTER TABLE `peliculas` DISABLE KEYS */;
INSERT INTO `peliculas` VALUES (1,'la milla verde',115,1995,'http://es.web.img3.acsta.net/c_215_290/medias/nmedia/18/74/37/43/20156807.jpg',2),(2,'waterworld',98,1997,'http://es.web.img3.acsta.net/c_215_290/medias/nmedia/18/76/85/32/20151582.jpg',4),(3,'scary movie',86,2001,'http://es.web.img3.acsta.net/c_215_290/pictures/14/03/06/09/44/283111.jpg',5),(4,'la jungla de cristal',96,1996,'http://es.web.img3.acsta.net/c_215_290/medias/nmedia/18/67/73/71/19802298.jpg',1),(5,'el señor de los anillos',176,2008,'http://es.web.img2.acsta.net/c_215_290/medias/nmedia/18/89/67/45/20061512.jpg',6),(6,'el caballero oscuro',153,2010,'http://es.web.img3.acsta.net/c_215_290/medias/nmedia/18/66/74/01/20350623.jpg',3),(7,'black hawk derribado',118,1998,'http://es.web.img3.acsta.net/c_215_290/pictures/15/11/05/10/02/222501.jpg',7),(8,'serenity',100,2003,'http://es.web.img3.acsta.net/c_215_290/pictures/14/03/11/11/29/510111.jpg',1),(9,'matrix',110,2006,'http://es.web.img3.acsta.net/c_215_290/pictures/19/08/07/09/08/5783887.jpg',3),(10,'iron man 3',123,2015,'http://es.web.img2.acsta.net/c_215_290/medias/nmedia/18/77/86/82/20486826.jpg',5),(11,'vengadores infinity war',159,2019,'http://es.web.img3.acsta.net/c_215_290/pictures/18/03/16/15/33/3988420.jpg',7),(12,'la guerra de las galaxias',127,1984,'http://es.web.img3.acsta.net/c_215_290/medias/nmedia/18/71/18/12/20061511.jpg',6),(19,'las dos torres',183,2007,'http://es.web.img2.acsta.net/c_215_290/medias/nmedia/18/89/85/69/20070008.jpg',2),(20,'regreso al futuro',97,1983,'http://es.web.img3.acsta.net/c_215_290/pictures/14/03/11/10/30/351336.jpg',3),(21,'el retorno del rey',189,2004,'http://es.web.img3.acsta.net/c_215_290/medias/nmedia/18/89/68/19/20061877.jpg',4),(25,'deadpool',118,2015,'http://es.web.img3.acsta.net/c_215_290/pictures/15/12/04/10/48/099822.jpg',1),(27,'toy story',98,1998,'http://es.web.img2.acsta.net/c_215_290/pictures/14/03/17/10/20/509771.jpg',5);
/*!40000 ALTER TABLE `peliculas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `pass` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `imagen` varchar(255) COLLATE latin1_spanish_ci NOT NULL DEFAULT 'https://picsum.photos/50/75',
  PRIMARY KEY (`id`),
  UNIQUE KEY `usuario_UN` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','admin','https://picsum.photos/50/75'),(2,'juan','12345','https://picsum.photos/50/75'),(3,'luis','12345','https://picsum.photos/50/75'),(4,'ana','12345','https://picsum.photos/50/75'),(5,'maria','12345','https://picsum.photos/50/75'),(6,'pedro','12345','https://picsum.photos/50/75'),(7,'sara','12345','https://picsum.photos/50/75');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-29  9:41:12
