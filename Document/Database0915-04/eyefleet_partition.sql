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
-- Table structure for table `partition`
--

DROP TABLE IF EXISTS `partition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partition` (
  `partition_id` varchar(20) NOT NULL,
  `capacity` int(11) DEFAULT NULL,
  `truck_id` varchar(20) NOT NULL,
  `position` int(11) DEFAULT NULL,
  PRIMARY KEY (`partition_id`),
  KEY `fk_partition_truck1_idx` (`truck_id`),
  CONSTRAINT `fk_partition_truck1` FOREIGN KEY (`truck_id`) REFERENCES `truck` (`truck_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partition`
--

LOCK TABLES `partition` WRITE;
/*!40000 ALTER TABLE `partition` DISABLE KEYS */;
INSERT INTO `partition` VALUES ('14.1.2.1',2,'291 - 14.1',2),('14.1.2.2',2,'291 - 14.1',3),('14.1.2.3',2,'291 - 14.1',4),('14.1.4.1',4,'291 - 14.1',1),('14.1.4.2',4,'291 - 14.1',5),('14.2.2.1',2,'245 - 14.2',1),('14.2.2.2',2,'245 - 14.2',3),('14.2.2.3',2,'245 - 14.2',5),('14.2.4.1',4,'245 - 14.2',2),('14.2.4.2',4,'245 - 14.2',4),('14.3.2.1',2,'876 - 14.3',2),('14.3.2.2',2,'876 - 14.3',3),('14.3.2.3',2,'876 - 14.3',4),('14.3.4.1',4,'876 - 14.3',1),('14.3.4.2',4,'876 - 14.3',5),('14.4.2.1',2,'817 - 14.4',2),('14.4.2.2',2,'817 - 14.4',3),('14.4.2.3',2,'817 - 14.4',4),('14.4.4.1',4,'817 - 14.4',1),('14.4.4.2',4,'817 - 14.4',5),('14.5.2.1',2,'410 - 14.5',1),('14.5.2.2',2,'410 - 14.5',2),('14.5.2.3',2,'410 - 14.5',5),('14.5.4.1',4,'410 - 14.5',3),('14.5.4.2',4,'410 - 14.5',4),('16.1.2.1',2,'812 - 16.1',1),('16.1.2.2',2,'812 - 16.1',2),('16.1.2.3',2,'812 - 16.1',5),('16.1.2.4',2,'812 - 16.1',6),('16.1.4.1',4,'812 - 16.1',3),('16.1.4.2',4,'812 - 16.1',4),('18.1.2.1',2,'819 - 18.1',2),('18.1.2.2',2,'819 - 18.1',3),('18.1.4.1',4,'819 - 18.1',1),('18.1.4.2',4,'819 - 18.1',5),('18.1.6.1',6,'819 - 18.1',4),('2.1.1.1',1,'822 - 2.1',1),('2.1.1.2',1,'822 - 2.1',2),('20.1.2.1',2,'813 - 20.1',1),('20.1.2.2',2,'813 - 20.1',2),('20.1.4.1',4,'813 - 20.1',4),('20.1.6.1',6,'813 - 20.1',3),('20.1.6.2',6,'813 - 20.1',5);
/*!40000 ALTER TABLE `partition` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-09-15 10:49:11
