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
-- Table structure for table `order_assignment`
--

DROP TABLE IF EXISTS `order_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_assignment` (
  `order_assignment_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_assigned` date DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `order_id` int(11) NOT NULL,
  `partition_id` varchar(20) NOT NULL,
  `deployment_batch_id` int(11) NOT NULL,
  PRIMARY KEY (`order_assignment_id`),
  KEY `fk_order_assignment_order1_idx` (`order_id`),
  KEY `fk_order_assignment_partition1_idx` (`partition_id`),
  KEY `fk_order_assignment_deployment_batch1_idx` (`deployment_batch_id`),
  CONSTRAINT `fk_order_assignment_deployment_batch1` FOREIGN KEY (`deployment_batch_id`) REFERENCES `deployment_batch` (`deployment_batch_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_assignment_order1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_assignment_partition1` FOREIGN KEY (`partition_id`) REFERENCES `partition` (`partition_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_assignment`
--

LOCK TABLES `order_assignment` WRITE;
/*!40000 ALTER TABLE `order_assignment` DISABLE KEYS */;
INSERT INTO `order_assignment` VALUES (29,'2014-09-15','delivered',4,24,'18.1.4.1',15),(30,'2014-09-15','failed',2,25,'18.1.2.1',15),(31,'2014-09-15','delivered',2,25,'18.1.2.2',15),(32,'2014-09-15','delivered',6,25,'18.1.6.1',15),(33,'2014-09-15','delivered',4,24,'18.1.4.2',15),(34,'2014-09-15','delivered',4,27,'18.1.4.1',16),(35,'2014-09-15','delivered',2,27,'18.1.2.1',16),(36,'2014-09-15','delivered',2,27,'18.1.2.2',16),(37,'2014-09-15','delivered',6,26,'18.1.6.1',16),(38,'2014-09-15','delivered',4,26,'18.1.4.2',16),(39,'2014-09-15','delivered',2,28,'20.1.2.1',17),(40,'2014-09-15','delivered',6,29,'20.1.6.1',17),(41,'2014-09-15','delivered',4,29,'20.1.4.1',17),(42,'2014-09-15','delivered',6,28,'20.1.6.2',17);
/*!40000 ALTER TABLE `order_assignment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-09-15 10:49:12
