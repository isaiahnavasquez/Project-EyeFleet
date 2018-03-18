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
-- Table structure for table `truck_part`
--

DROP TABLE IF EXISTS `truck_part`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `truck_part` (
  `truck_part_id` varchar(50) NOT NULL,
  `quantity_on_hand` int(11) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`truck_part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `truck_part`
--

LOCK TABLES `truck_part` WRITE;
/*!40000 ALTER TABLE `truck_part` DISABLE KEYS */;
INSERT INTO `truck_part` VALUES ('AIR CMPRSRE',0,'air compressure'),('AIR DRYR',0,'air dryer'),('AIR GVRNR',0,'air governor'),('AIR HOSE',0,'air hose'),('ALTRNTR',0,'alternator'),('BRK CLNDER',0,'break cylinder'),('BRK FLD',0,'break fluid'),('BRK MSTER',0,'break master'),('BTRY',0,'battery'),('CLCTH SLVE',0,'clutch sleeve'),('CLTCH BSTR',0,'clutch booster'),('CLUTCH DSK',0,'clutch disk'),('CLUTCH FNGER',0,'clutch finger'),('CLUTCH LNG',0,'clutch lining'),('CLUTCH MSTR',0,'clutch master'),('CRS JOIN',0,'cross join'),('EGNTN SWTCH',0,'egnition switch'),('ENGN OIL #40',0,'engine oil #40'),('FL LNE HSE',0,'fuel line hose'),('FOOT VLVE',0,'foot valve'),('FSE',0,'fuse'),('FUEL FLTR',0,'fuel filter'),('GUB BRNG - IN',0,'GUB bearing - inner'),('GUB BRNG - OUT',0,'GUB bearing - outer'),('HDRO BCK',0,'hydro back'),('INJCT PMP CLBRT',0,'injection pump calibrate'),('INJCTR CLBRTE',0,'injector calibrate'),('KNG PIN',0,'king pin'),('OIL FLTR',0,'oil filter'),('PRSSR PLT',0,'pressure plate'),('RBR BRK',0,'rubber break'),('RBR CLTCH',0,'rubber clutch'),('RBR CUP',0,'rubber cup'),('RBR CUSSN',0,'rubber cushion'),('REAR SPRNG',0,'rear spring'),('SPNDLE BRNG - IN',0,'spindle bearing - inner'),('SPNDLE BRNG - OUT',0,'spindle bearing - outer'),('STL TBE BRK SSTM',0,'steel tube break system'),('STRNG BSTR',0,'steering booster'),('STRNG GR BOX',0,'steering gear box'),('STRNG OIL ATF #10.68',0,'steering oil ATF #10.68'),('STRTR',0,'starter'),('TRBO',0,'turbo'),('TRE',0,'tire'),('TRNSMSN OIL',0,'transmission oil'),('VLVE CLRNCE LGHT',0,'valve clearance light'),('WHL BLT - LFT',0,'wheel bolt - left'),('WHL BLT - RHT',0,'wheel bolt - right');
/*!40000 ALTER TABLE `truck_part` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-09-02 18:06:30
