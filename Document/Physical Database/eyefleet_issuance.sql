CREATE DATABASE  IF NOT EXISTS `eyefleet` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `eyefleet`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: eyefleet
-- ------------------------------------------------------
-- Server version	5.5.37

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
-- Table structure for table `issuance`
--

DROP TABLE IF EXISTS `issuance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issuance` (
  `issuance_detail` int(11) NOT NULL AUTO_INCREMENT,
  `date_issued` date DEFAULT NULL,
  `issuance_validity` int(11) DEFAULT NULL,
  `equipment_id` varchar(10) NOT NULL,
  `driver_id` varchar(20) NOT NULL,
  `date_until` date DEFAULT NULL,
  PRIMARY KEY (`issuance_detail`),
  KEY `fk_issuance_equipment1_idx` (`equipment_id`),
  KEY `fk_issuance_driver1_idx` (`driver_id`),
  CONSTRAINT `fk_issuance_equipment1` FOREIGN KEY (`equipment_id`) REFERENCES `equipment` (`equipment_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_issuance_driver1` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`driver_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issuance`
--

LOCK TABLES `issuance` WRITE;
/*!40000 ALTER TABLE `issuance` DISABLE KEYS */;
INSERT INTO `issuance` VALUES (1,'2014-08-10',9,'GLVES','villamor, j.','2014-08-20'),(5,'2014-08-10',9,'GLVES','macahilo, r.','2014-08-20'),(6,'2014-08-10',9,'HLMT','impuesto, d.','2014-08-20'),(7,'2014-08-10',29,'SHOES','dayanan, h.','2014-09-09'),(8,'2014-08-10',29,'SHOES','villamor, j.','2014-09-09'),(9,'2014-08-10',9,'HLMT','villamor, j.','2014-08-20'),(10,'2014-08-10',9,'HLMT','bualan, a.','2014-08-20'),(11,'2014-08-10',9,'GLVES','barinque, a.','2014-08-20'),(12,'2014-08-10',29,'SHOES','antoque, l.','2014-09-09'),(13,'2014-08-10',9,'GLVES','cayawin, r.','2014-08-20'),(14,'2014-08-10',9,'HLMT','driver_id','2014-08-20'),(15,'2014-08-10',9,'HLMT','villamor, j.','2014-08-20'),(16,'2014-08-10',9,'GLVES','cayawin, r.','2014-08-20'),(17,'2014-08-10',29,'SHOES','bualan, a.','2014-09-09'),(18,'2014-08-10',29,'SHOES','villamor, j.','2014-09-09'),(19,'2014-08-10',0,'SHOES','antoque, l.','2014-09-09'),(20,'2014-08-05',24,'SHOES','antoque, l.','2014-05-09'),(21,'2014-08-11',10,'HLMT','barinque, a.','2014-08-21'),(22,'2014-08-11',10,'GLVES','barinque, a.','2014-08-21'),(23,'2014-08-11',10,'HLMT','barinque, a.','2014-08-21'),(24,'2014-08-11',10,'GLVES','barinque, a.','2014-08-21'),(25,'2014-08-11',30,'SHOES','barinque, a.','2014-09-10'),(26,'2014-08-11',10,'GLVES','barinque, a.','2014-08-21'),(27,'2014-08-11',10,'HLMT','bualan, a.','2014-08-21'),(28,'2014-08-11',10,'HLMT','cayawin, r.','2014-08-21'),(29,'2014-08-11',10,'GLVES','dayanan, h.','2014-08-21'),(30,'2014-08-11',10,'HLMT','dayanan, h.','2014-08-21'),(31,'2014-08-11',30,'SHOES','dayanan, h.','2014-09-10'),(32,'2014-08-11',10,'GLVES','dayanan, h.','2014-08-21'),(33,'2014-08-11',30,'SHOES','dayanan, h.','2014-09-10'),(34,'2014-08-11',10,'GLVES','villamor, j.','2014-08-21'),(35,'2014-08-11',10,'GLVES','dayanan, h.','2014-08-21'),(36,'2014-08-11',10,'HLMT','villamor, j.','2014-08-21'),(37,'2014-08-11',10,'GLVES','impuesto, d.','2014-08-21'),(38,'2014-08-11',10,'GLVES','impuesto, d.','2014-08-21'),(39,'2014-08-11',10,'GLVES','impuesto, d.','2014-08-21');
/*!40000 ALTER TABLE `issuance` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-11 12:22:18
