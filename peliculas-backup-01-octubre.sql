-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: peliculas
-- ------------------------------------------------------
-- Server version	5.7.31-0ubuntu0.18.04.1

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distribuidora`
--

LOCK TABLES `distribuidora` WRITE;
/*!40000 ALTER TABLE `distribuidora` DISABLE KEYS */;
INSERT INTO `distribuidora` VALUES (17,'bloomhouse'),(6,'columbia'),(4,'disney'),(8,'metro goldwyn mayer'),(2,'paramount'),(5,'pixar'),(7,'tristar'),(1,'univeral'),(3,'warner bros');
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
  `id_usuario` int(11) NOT NULL,
  `fecha_creacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_validacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `peliculas_UN` (`nombre`),
  KEY `FK_distribuidora` (`id_distribuidora`),
  KEY `FK_usuario` (`id_usuario`),
  CONSTRAINT `FK_distribuidora` FOREIGN KEY (`id_distribuidora`) REFERENCES `distribuidora` (`id`),
  CONSTRAINT `FK_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peliculas`
--

LOCK TABLES `peliculas` WRITE;
/*!40000 ALTER TABLE `peliculas` DISABLE KEYS */;
INSERT INTO `peliculas` VALUES (1,'la milla verde',115,1995,'http://es.web.img3.acsta.net/c_215_290/medias/nmedia/18/74/37/43/20156807.jpg',2,1,'2020-07-13 12:27:54',NULL),(2,'waterworld',98,1997,'http://es.web.img3.acsta.net/c_215_290/medias/nmedia/18/76/85/32/20151582.jpg',4,1,'2020-07-13 12:27:54',NULL),(3,'scary movie',86,2001,'http://es.web.img3.acsta.net/c_215_290/pictures/14/03/06/09/44/283111.jpg',5,1,'2020-07-13 12:27:54',NULL),(4,'la jungla de cristal',96,1996,'http://es.web.img3.acsta.net/c_215_290/medias/nmedia/18/67/73/71/19802298.jpg',1,1,'2020-07-13 12:27:54',NULL),(5,'el señor de los anillos',176,2008,'http://es.web.img2.acsta.net/c_215_290/medias/nmedia/18/89/67/45/20061512.jpg',6,4,'2020-07-13 12:27:54','2020-07-20 10:55:54'),(6,'el caballero oscuro',153,2010,'http://es.web.img3.acsta.net/c_215_290/medias/nmedia/18/66/74/01/20350623.jpg',3,1,'2020-07-13 12:27:54',NULL),(7,'black hawk derribado',118,1998,'http://es.web.img3.acsta.net/c_215_290/pictures/15/11/05/10/02/222501.jpg',7,1,'2020-07-13 12:27:54',NULL),(8,'serenity',100,2003,'http://es.web.img3.acsta.net/c_215_290/pictures/14/03/11/11/29/510111.jpg',1,1,'2020-07-13 12:27:54',NULL),(9,'matrix',110,2006,'http://es.web.img3.acsta.net/c_215_290/pictures/19/08/07/09/08/5783887.jpg',3,1,'2020-07-13 12:27:54',NULL),(10,'iron man 3',123,2015,'http://es.web.img2.acsta.net/c_215_290/medias/nmedia/18/77/86/82/20486826.jpg',5,1,'2020-07-13 12:27:54',NULL),(11,'vengadores infinity war',159,2019,'http://es.web.img3.acsta.net/c_215_290/pictures/18/03/16/15/33/3988420.jpg',7,1,'2020-07-13 12:27:54',NULL),(12,'la guerra de las galaxias',127,1984,'http://es.web.img3.acsta.net/c_215_290/medias/nmedia/18/71/18/12/20061511.jpg',6,1,'2020-07-13 12:27:54',NULL),(19,'las dos torres',183,2007,'http://es.web.img2.acsta.net/c_215_290/medias/nmedia/18/89/85/69/20070008.jpg',2,4,'2020-07-13 12:27:54',NULL),(20,'regreso al futuro',97,1983,'http://es.web.img3.acsta.net/c_215_290/pictures/14/03/11/10/30/351336.jpg',3,1,'2020-07-13 12:27:54',NULL),(21,'el retorno del rey',189,2004,'http://es.web.img3.acsta.net/c_215_290/medias/nmedia/18/89/68/19/20061877.jpg',4,4,'2020-07-13 12:27:54',NULL),(25,'deadpool',118,2015,'http://es.web.img3.acsta.net/c_215_290/pictures/15/12/04/10/48/099822.jpg',1,1,'2020-07-13 12:27:54',NULL),(27,'toy story',98,1998,'http://es.web.img2.acsta.net/c_215_290/pictures/14/03/17/10/20/509771.jpg',5,1,'2020-07-13 12:27:54',NULL);
/*!40000 ALTER TABLE `peliculas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UN_rol` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=422 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (421,'administrador'),(256,'usuario');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
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
  `id_rol` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `usuario_UN` (`nombre`),
  KEY `FK_rol` (`id_rol`),
  CONSTRAINT `FK_rol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','admin','https://picsum.photos/50/75',421),(2,'juan','12345','https://picsum.photos/50/75',256),(3,'luis','12345','https://picsum.photos/50/75',256),(4,'ana','12345','https://picsum.photos/50/75',256),(5,'maria','12345','https://picsum.photos/50/75',256),(6,'pedro','12345','https://picsum.photos/50/75',256),(7,'sara','12345','https://picsum.photos/50/75',256);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_uf2177`
--

DROP TABLE IF EXISTS `usuarios_uf2177`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios_uf2177` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `contrasenia` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `id_rol` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UN_nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=374 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_uf2177`
--

LOCK TABLES `usuarios_uf2177` WRITE;
/*!40000 ALTER TABLE `usuarios_uf2177` DISABLE KEYS */;
INSERT INTO `usuarios_uf2177` VALUES (277,'Magee','123456',1),(278,'Kermit','123456',1),(279,'Oleg','123456',1),(280,'Cassidy','123456',1),(281,'Aaron','123456',1),(282,'Hedwig','123456',1),(283,'Alan','123456',1),(284,'Emery','123456',1),(285,'Macy','123456',1),(286,'Walker','123456',1),(287,'Xavier','123456',1),(288,'Dale','123456',1),(289,'Cole','123456',1),(290,'Zephr','123456',1),(291,'Charissa','123456',1),(292,'Lance','123456',1),(293,'David','123456',1),(294,'Ezra','123456',1),(295,'Quinn','123456',1),(296,'Cherokee','123456',1),(297,'Cody','123456',1),(298,'Katelyn','123456',1),(299,'Carly','123456',1),(300,'Asher','123456',1),(301,'Larissa','123456',1),(302,'Teegan','123456',1),(303,'Courtney','123456',1),(304,'Rhona','123456',1),(305,'Caldwell','123456',1),(306,'Wendy','123456',1),(307,'Rajah','123456',1),(308,'Evan','123456',1),(309,'Danielle','123456',1),(310,'Camilla','123456',1),(311,'Tana','123456',1),(312,'Lunea','123456',1),(313,'Leslie','123456',1),(314,'Cruz','123456',1),(315,'Eden','123456',1),(316,'Byron','123456',1),(317,'Ryder','123456',1),(318,'Ronan','123456',1),(319,'Norman','123456',1),(320,'Whoopi','123456',1),(321,'Honorato','123456',1),(322,'Tallulah','123456',1),(323,'Gloria','123456',1),(324,'Ira','123456',1),(325,'Barry','123456',1),(326,'Callum','123456',1),(327,'Zia','123456',1),(328,'Logan','123456',1),(329,'Xena','123456',1),(330,'Paul','123456',1),(331,'Elmo','123456',1),(332,'Gil','123456',1),(333,'Eliana','123456',1),(334,'Ursula','123456',1),(335,'Colby','123456',1),(336,'Basil','123456',1),(337,'Justine','123456',1),(338,'Craig','123456',1),(339,'Candace','123456',1),(340,'Burton','123456',1),(341,'Sean','123456',1),(342,'Urielle','123456',1),(343,'Tanek','123456',1),(344,'Guy','123456',1),(345,'Uta','123456',1),(346,'Desiree','123456',1),(347,'Kenneth','123456',1),(348,'Talon','123456',1),(349,'Yuli','123456',1),(350,'Jennifer','123456',1),(351,'Camille','123456',1),(352,'Amos','123456',1),(353,'Fallon','123456',1),(354,'Micah','123456',1),(355,'Darius','123456',1),(356,'Audra','123456',1),(357,'Jonas','123456',1),(359,'Daryl','123456',1),(360,'Xyla','123456',1),(361,'Caesar','123456',1),(362,'Carolyn','123456',1),(363,'Wanda','123456',1),(364,'Porter','123456',1),(365,'Jordan','123456',1),(366,'Cadman','123456',1),(367,'Hiram','123456',1),(369,'Oscar','123456',1),(370,'Levi','123456',1),(371,'Bell','123456',1),(372,'Shoshana','123456',1),(373,'Lani','123456',1);
/*!40000 ALTER TABLE `usuarios_uf2177` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `v_usuario_peliculas`
--

DROP TABLE IF EXISTS `v_usuario_peliculas`;
/*!50001 DROP VIEW IF EXISTS `v_usuario_peliculas`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_usuario_peliculas` AS SELECT 
 1 AS `id_usuario`,
 1 AS `total`,
 1 AS `pendientes`,
 1 AS `aprobadas`*/;
SET character_set_client = @saved_cs_client;

--
-- Current Database: `peliculas`
--

USE `peliculas`;

--
-- Final view structure for view `v_usuario_peliculas`
--

/*!50001 DROP VIEW IF EXISTS `v_usuario_peliculas`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`debian-sys-maint`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_usuario_peliculas` AS select `peliculas`.`id_usuario` AS `id_usuario`,count(`peliculas`.`id_usuario`) AS `total`,sum(isnull(`peliculas`.`fecha_validacion`)) AS `pendientes`,count(`peliculas`.`fecha_validacion`) AS `aprobadas` from `peliculas` group by `peliculas`.`id_usuario` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-01 11:55:27
