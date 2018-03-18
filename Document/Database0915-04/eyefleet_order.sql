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
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_ordered` date DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `scheduled_date` date DEFAULT NULL,
  `place` varchar(45) DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `product_id` varchar(10) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_order_customer1_idx` (`customer_id`),
  KEY `fk_order_product1_idx` (`product_id`),
  CONSTRAINT `fk_order_customer1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (24,'2014-09-15','delivered',8,'2014-09-15','stack',5,'ADO'),(25,'2014-09-15','delivered',10,'2014-09-15','stack',6,'ADO'),(26,'2014-09-15','delivered',10,'2014-09-15','stack',4,'ADO'),(27,'2014-09-15','delivered',8,'2014-09-15','stack',4,'PREM'),(28,'2014-09-15','delivered',8,'2014-09-16','stack',8,'PREM'),(29,'2014-09-15','delivered',10,'2014-09-16','stack',8,'ULG'),(30,'2014-10-15','delivered',10,'2014-10-15','stack',5,'ADO'),(31,'2014-10-15','delivered',8,'2014-10-15','stack',5,'ULG'),(32,'2014-10-15','delivered',6,'2014-10-15','stack',5,'ULG'),(33,'2014-10-15','delivered',10,'2014-10-15','stack',5,'ULG'),(34,'2014-10-15','delivered',10,'2014-10-15','stack',5,'ULG'),(35,'2014-10-15','delivered',10,'2014-10-15','stack',5,'PREM'),(36,'2014-10-15','delivered',4,'2014-10-15','stack',5,'PREM'),(37,'2014-11-15','delivered',4,'2014-10-15','stack',5,'PREM'),(38,'2014-11-15','delivered',10,'2014-10-15','stack',5,'ADO'),(39,'2014-11-15','delivered',10,'2014-10-15','stack',5,'ADO'),(40,'2014-11-15','delivered',6,'2014-10-15','stack',5,'ADO'),(41,'2014-11-15','delivered',6,'2014-10-15','stack',5,'PREM'),(42,'2014-11-15','delivered',6,'2014-10-15','stack',5,'PREM'),(43,'2014-11-15','delivered',14,'2014-10-15','stack',5,'ULG'),(44,'2014-12-15','delivered',14,'2014-10-15','stack',5,'ULG'),(45,'2014-12-15','delivered',14,'2014-10-15','stack',5,'ADO'),(46,'2014-12-15','delivered',2,'2014-10-15','stack',5,'PREM'),(47,'2014-12-15','delivered',6,'2014-10-15','stack',5,'ULG');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-09-15 10:49:19
