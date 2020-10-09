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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distribuidora`
--

LOCK TABLES `distribuidora` WRITE;
/*!40000 ALTER TABLE `distribuidora` DISABLE KEYS */;
INSERT INTO `distribuidora` VALUES (22,'Bailey-Heidenreich'),(37,'Beer, Grimes and Harvey'),(17,'bloomhouse'),(27,'Bode, Feeney and Marquardt'),(6,'columbia'),(32,'Connelly, Robel and Kihn'),(23,'Dach-Bernier'),(4,'disney'),(38,'eliminar'),(26,'Gleichner, Medhurst and Grimes'),(34,'Gleichner-Herman'),(19,'Grady-Hoppe'),(21,'Gulgowski-Leannon'),(28,'Hoeger, Davis and Heidenreich'),(33,'Kilback-Davis'),(36,'Langworth Inc'),(30,'Legros Ltd'),(31,'Mertz, Block and Cormier'),(8,'metro goldwyn mayer'),(18,'Mraz LLC'),(24,'Ortiz-Rutherford'),(2,'paramount'),(5,'pixar'),(25,'Pouros and Sons'),(35,'Romaguera-Huel'),(29,'Sauer LLC'),(20,'Torphy Group'),(7,'tristar'),(1,'univeral'),(3,'warner bros');
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
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peliculas`
--

LOCK TABLES `peliculas` WRITE;
/*!40000 ALTER TABLE `peliculas` DISABLE KEYS */;
INSERT INTO `peliculas` VALUES (1,'la milla verde',115,1995,'http://es.web.img3.acsta.net/c_215_290/medias/nmedia/18/74/37/43/20156807.jpg',2,1,'2020-07-13 12:27:54',NULL),(2,'waterworld',98,1997,'http://es.web.img3.acsta.net/c_215_290/medias/nmedia/18/76/85/32/20151582.jpg',4,1,'2020-07-13 12:27:54',NULL),(3,'scary movie',86,2001,'http://es.web.img3.acsta.net/c_215_290/pictures/14/03/06/09/44/283111.jpg',5,1,'2020-07-13 12:27:54',NULL),(4,'la jungla de cristal',96,1996,'http://es.web.img3.acsta.net/c_215_290/medias/nmedia/18/67/73/71/19802298.jpg',1,1,'2020-07-13 12:27:54',NULL),(5,'el seÃ±or de los anillos',195,2009,'http://es.web.img2.acsta.net/c_215_290/medias/nmedia/18/89/67/45/20061512.jpg',6,4,'2020-07-13 12:27:54','2020-07-20 10:55:54'),(6,'el caballero oscuro',153,2010,'http://es.web.img3.acsta.net/c_215_290/medias/nmedia/18/66/74/01/20350623.jpg',3,1,'2020-07-13 12:27:54',NULL),(7,'black hawk derribado',118,1998,'http://es.web.img3.acsta.net/c_215_290/pictures/15/11/05/10/02/222501.jpg',7,1,'2020-07-13 12:27:54',NULL),(8,'serenity',100,2003,'http://es.web.img3.acsta.net/c_215_290/pictures/14/03/11/11/29/510111.jpg',1,1,'2020-07-13 12:27:54',NULL),(9,'matrix',110,2006,'http://es.web.img3.acsta.net/c_215_290/pictures/19/08/07/09/08/5783887.jpg',3,1,'2020-07-13 12:27:54',NULL),(10,'iron man 3',123,2015,'http://es.web.img2.acsta.net/c_215_290/medias/nmedia/18/77/86/82/20486826.jpg',5,1,'2020-07-13 12:27:54',NULL),(11,'vengadores infinity war',159,2019,'http://es.web.img3.acsta.net/c_215_290/pictures/18/03/16/15/33/3988420.jpg',7,1,'2020-07-13 12:27:54',NULL),(12,'la guerra de las galaxias',127,1984,'http://es.web.img3.acsta.net/c_215_290/medias/nmedia/18/71/18/12/20061511.jpg',6,1,'2020-07-13 12:27:54',NULL),(19,'las dos torres',183,2007,'http://es.web.img2.acsta.net/c_215_290/medias/nmedia/18/89/85/69/20070008.jpg',2,4,'2020-07-13 12:27:54',NULL),(20,'regreso al futuro',97,1983,'http://es.web.img3.acsta.net/c_215_290/pictures/14/03/11/10/30/351336.jpg',3,1,'2020-07-13 12:27:54',NULL),(21,'el retorno del rey',189,2004,'http://es.web.img3.acsta.net/c_215_290/medias/nmedia/18/89/68/19/20061877.jpg',4,4,'2020-07-13 12:27:54',NULL),(25,'deadpool',118,2015,'http://es.web.img3.acsta.net/c_215_290/pictures/15/12/04/10/48/099822.jpg',1,1,'2020-07-13 12:27:54',NULL),(27,'toy story',98,1998,'http://es.web.img2.acsta.net/c_215_290/pictures/14/03/17/10/20/509771.jpg',5,1,'2020-07-13 12:27:54',NULL),(28,'Optio ad ipsum in accusantium alias quo.',126,1988,'http://lorempixel.com/400/400/city/',1,8,'2019-06-20 15:51:01','2016-06-11 14:16:17'),(29,'Minus optio voluptatem magni sunt sunt qui.',164,1992,'http://lorempixel.com/400/400/city/',7,4,'2017-04-10 13:27:58','1999-03-09 11:24:07'),(30,'Dicta ipsa a architecto incidunt.',80,2011,'http://lorempixel.com/400/400/city/',1,1,'1993-03-19 14:32:13','2013-06-22 16:56:26'),(31,'Libero esse earum dolor corrupti enim.',148,1991,'http://lorempixel.com/400/400/city/',8,1,'2006-04-05 16:05:40','1985-01-03 02:23:23'),(32,'Autem eligendi est cupiditate voluptate consequuntur sequi.',216,1987,'http://lorempixel.com/400/400/city/',1,6,'2007-08-02 00:48:43','1970-01-19 14:29:36'),(33,'Quia fuga voluptatibus fugiat error iste libero.',225,2008,'http://lorempixel.com/400/400/city/',3,3,'1972-10-15 22:09:45','2014-02-19 21:49:08'),(34,'Voluptate sit est explicabo.',134,2020,'http://lorempixel.com/400/400/city/',3,4,'1983-04-17 00:15:46','2013-04-05 09:35:12'),(35,'Placeat qui eaque quae fugit.',169,1973,'http://lorempixel.com/400/400/city/',4,3,'1996-11-08 03:32:38','1979-04-08 02:19:49'),(36,'Harum rerum impedit in iste.',170,2010,'http://lorempixel.com/400/400/city/',7,1,'1995-03-21 14:36:15','2011-10-07 10:25:02'),(37,'Neque cumque dolores consequatur amet.',85,2018,'http://lorempixel.com/400/400/city/',1,1,'1977-02-07 12:15:02','1978-03-12 07:11:48'),(38,'Dolore voluptas accusamus quas non omnis.',92,1998,'http://lorempixel.com/400/400/city/',1,8,'1976-08-16 01:09:50','2009-09-29 07:17:38'),(39,'Ea omnis fuga omnis at sunt nihil non.',144,2015,'http://lorempixel.com/400/400/city/',3,1,'2019-03-20 17:29:56','2018-08-16 19:39:30'),(40,'Dicta consequatur aut earum officiis error illo dolore.',163,1976,'http://lorempixel.com/400/400/city/',5,1,'1976-01-13 15:37:09','2011-01-24 14:35:27'),(41,'Et minus doloremque ut autem error in.',124,1972,'http://lorempixel.com/400/400/city/',1,6,'1981-01-07 20:57:45','2003-07-24 16:29:31'),(42,'Vel pariatur delectus omnis consequatur saepe consequatur.',226,1987,'http://lorempixel.com/400/400/city/',7,7,'1982-03-09 00:02:48','2018-02-10 22:20:41'),(43,'Dignissimos doloremque tempore ex eos sit laudantium vero.',99,2014,'http://lorempixel.com/400/400/city/',4,3,'1982-03-14 23:34:58','2012-02-23 00:22:18'),(44,'Est cum dicta recusandae nam nostrum.',274,1987,'http://lorempixel.com/400/400/city/',1,2,'1997-05-04 17:24:19','2006-03-04 20:11:14'),(45,'Iusto cumque consequatur id occaecati.',80,2008,'http://lorempixel.com/400/400/city/',3,6,'2013-01-09 05:25:48','2017-03-31 08:18:42'),(46,'Qui nam dolor dolor ut dolorem at nulla.',259,2010,'http://lorempixel.com/400/400/city/',7,3,'1976-06-11 02:54:29','1973-01-22 15:39:42'),(47,'Explicabo nihil maxime eum ratione cum rem.',233,2005,'http://lorempixel.com/400/400/city/',5,8,'1971-08-24 00:28:44','1977-10-22 20:01:54'),(50,'el procedimiento del miedo',65,2002,'https://picsum.photos/50/75',23,1,'2020-10-09 09:44:38',NULL),(51,'esto es un drama 2',125,2020,'https://picsum.photos/50/75',4,1,'2020-10-09 10:10:57',NULL),(54,'prueba5454',150,2012,'https://picsum.photos/50/75',6,4,'2020-10-09 10:31:33',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','admin','https://picsum.photos/50/75',421),(2,'juan','12345','https://picsum.photos/50/75',256),(3,'luis','12345','https://picsum.photos/50/75',256),(4,'ana','12345','https://picsum.photos/50/75',256),(5,'maria','12345','https://picsum.photos/50/75',256),(6,'pedro','12345','https://picsum.photos/50/75',256),(7,'sara','12345','https://picsum.photos/50/75',256),(8,'Ms. Crystel Skiles II','6a1396878e92bf44b100f0f3c27a6d76','http://lorempixel.com/400/400/people/',256),(9,'Yasmine Shields','4c735a36c3046184f3f8bc3007c8a537','http://lorempixel.com/400/400/people/',256),(10,'Thalia Lemke V','3a2c021dca7a7743399b37a729f4c802','http://lorempixel.com/400/400/people/',256),(11,'Mr. Junius Wilderman V','b4eeb4ed1ca6350c6d5859bca8d49f2a','http://lorempixel.com/400/400/people/',256),(12,'Mitchel Legros II','aaac24df147c7e53a127339664fa2113','http://lorempixel.com/400/400/people/',256),(13,'Miss Priscilla Kohler','ff4310e1744fabe3f5d1c0ff50fbfd0a','http://lorempixel.com/400/400/people/',256),(14,'Garry Dibbert','29a669ac5747143b0f2c2328da342f76','http://lorempixel.com/400/400/people/',256),(15,'Dr. Nicklaus Abshire II','bfae823fba97e6f7a0d23cd0178add77','http://lorempixel.com/400/400/people/',256),(16,'Adela Windler','3ff9d30e40075d7b3ec50a7d9eecfa75','http://lorempixel.com/400/400/people/',256),(17,'Shanelle Ratke III','ac45f30fafeb62ad776ce2888ebbea33','http://lorempixel.com/400/400/people/',256),(18,'Prof. Dawson D\'Amore III','2cd6b2ee15f926b4eca710d28d45877f','http://lorempixel.com/400/400/people/',256),(19,'Madonna Sipes Jr.','3ff83be676d9dc4c1ae09061726099fb','http://lorempixel.com/400/400/people/',256),(20,'Laney Heidenreich Jr.','fb0db64c7bcc6bce5c29d7dfe38dff47','http://lorempixel.com/400/400/people/',256),(21,'Jakob Koelpin','136c9c7f051e4671f11dd05b7f19fed9','http://lorempixel.com/400/400/people/',256),(22,'Mr. Fred Hodkiewicz I','90f5ebf6bb2986a80cb42ecbc58058af','http://lorempixel.com/400/400/people/',256),(23,'Mallie Volkman','e2fa9ec21ab372baca6ecc42931c63d1','http://lorempixel.com/400/400/people/',256),(24,'Aurelio Carroll','a6d1299f8ec1f9cebff840f69f2ff973','http://lorempixel.com/400/400/people/',256),(25,'Abigayle Cormier','1bf36461236d08f59e3fbff5ed44ae15','http://lorempixel.com/400/400/people/',256),(26,'Elody Macejkovic','4b393b4d3ea9b7baf1e0db0e4934470d','http://lorempixel.com/400/400/people/',256),(27,'Noel Waters','dda747f011e499bbcc39d2728bca2432','http://lorempixel.com/400/400/people/',256);
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
-- Dumping routines for database 'peliculas'
--
/*!50003 DROP FUNCTION IF EXISTS `fn_case_con_nombre` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`debian-sys-maint`@`localhost` FUNCTION `fn_case_con_nombre`( pNombre VARCHAR(50), pIdioma VARCHAR(2) ) RETURNS varchar(100) CHARSET latin1
BEGIN
 
  DECLARE saludo VARCHAR(50);
 
  IF pNombre is NULL OR pNombre = '' THEN 
  SET pNombre = 'Anonimo';
  END IF;
 
  CASE pIdioma
   WHEN 'es' THEN SET saludo = CONCAT('Hola, ', pNombre);
   WHEN 'eu' THEN SET saludo = CONCAT('Kaixo, ', pNombre);
   WHEN 'en' THEN SET saludo = CONCAT('Hello, ', pNombre);
   ELSE BEGIN
   SET saludo = CONCAT('ERROR: Idioma desconocido ', pIdioma);  
      END;    
  END CASE;
  
  RETURN saludo;
 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `fn_ejemplo_case` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`debian-sys-maint`@`localhost` FUNCTION `fn_ejemplo_case`( pIdioma VARCHAR(2) ) RETURNS varchar(50) CHARSET latin1
BEGIN
	
	DECLARE saludo VARCHAR(50) DEFAULT '';

	CASE pIdioma 
		WHEN 'es' THEN BEGIN
			SET saludo = 'Bien Venido';
		END;	
	
		WHEN 'eu' THEN BEGIN
			SET saludo = 'Ongi Etorri';
		END;
	
		WHEN 'en' THEN BEGIN
			SET saludo = 'Wellcome';
		END;
	
		ELSE BEGIN
			SET saludo = 'Lo siento, no conozoco el idioma';
		END;	
	
	END CASE;

    RETURN saludo;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `fn_hola_mundo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`debian-sys-maint`@`localhost` FUNCTION `fn_hola_mundo`() RETURNS varchar(100) CHARSET latin1
BEGIN
	RETURN 'Hola mundo';
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `fn_saludar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`debian-sys-maint`@`localhost` FUNCTION `fn_saludar`( fNombre VARCHAR(100) ) RETURNS varchar(100) CHARSET latin1
BEGIN
	
	DECLARE salida VARCHAR(100) DEFAULT 'Lo siento pero no se tu nombre';
  
	IF pNombre IS NOT NULL AND "" <> TRIM(pNombre) THEN

		SET salida = CONCAT('Hola ', pNombre);
   
	END IF;

	RETURN salida;

	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_distribuidora_delete` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`debian-sys-maint`@`localhost` PROCEDURE `pa_distribuidora_delete`( IN pId INT )
BEGIN
	
	DELETE FROM distribuidora WHERE id = pId;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_distribuidora_insertar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`debian-sys-maint`@`localhost` PROCEDURE `pa_distribuidora_insertar`( IN pNombre VARCHAR(100), OUT pIdGenerado INT)
BEGIN
	
	-- realizar la INSERT 
	INSERT INTO distribuidora (nombre) VALUES (TRIM(pNombre));

	-- guardamos en el parametro de salida el ultimo id generado usando una funcion
	SET pIdGenerado = LAST_INSERT_ID(); 

	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_distribuidora_listar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`debian-sys-maint`@`localhost` PROCEDURE `pa_distribuidora_listar`()
BEGIN
	
	-- el nombre de los procedimientos, en este caso pa_distribuidora_listar, es por:
	-- pa: procedimiento almacenado
	-- distribuidora: nombre de la tabla a la que hace referencia
	-- listar: accion que realiza el procedimiento
	
	SELECT nombre FROM distribuidora ORDER BY nombre ASC;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_distribuidora_update` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`debian-sys-maint`@`localhost` PROCEDURE `pa_distribuidora_update`(IN pNombre VARCHAR(100), IN pId INT)
BEGIN
	
	UPDATE distribuidora SET nombre = TRIM(pNombre) WHERE id = pId;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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

-- Dump completed on 2020-10-09 10:46:39
