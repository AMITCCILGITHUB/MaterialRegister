CREATE DATABASE  IF NOT EXISTS `metalplants` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `metalplants`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: metalplants
-- ------------------------------------------------------
-- Server version	5.5.27

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
-- Table structure for table `usermaster`
--

DROP TABLE IF EXISTS `usermaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usermaster` (
  `User_Name` varchar(64) NOT NULL DEFAULT '',
  `Password` varchar(32) NOT NULL,
  `Role_Code` int(11) NOT NULL,
  `User_Status` varchar(8) NOT NULL DEFAULT 'TRUE',
  PRIMARY KEY (`User_Name`),
  UNIQUE KEY `User_Name` (`User_Name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usermaster`
--

LOCK TABLES `usermaster` WRITE;
/*!40000 ALTER TABLE `usermaster` DISABLE KEYS */;
INSERT INTO `usermaster` VALUES ('Admin','1',1001,'TRUE'),('Amit','1',1001,'TRUE');
/*!40000 ALTER TABLE `usermaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specificationmaster`
--

DROP TABLE IF EXISTS `specificationmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specificationmaster` (
  `Specification_Code` int(11) NOT NULL DEFAULT '0',
  `Specification_Name` varchar(64) DEFAULT NULL,
  `Remarks` varchar(128) DEFAULT NULL,
  `Status` varchar(8) NOT NULL DEFAULT 'TRUE',
  `Created_By` varchar(32) NOT NULL DEFAULT 'SYSTEM',
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Specification_Code`),
  UNIQUE KEY `Specification_Code_UNIQUE` (`Specification_Code`),
  KEY `Specification_Name` (`Specification_Name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specificationmaster`
--

LOCK TABLES `specificationmaster` WRITE;
/*!40000 ALTER TABLE `specificationmaster` DISABLE KEYS */;
INSERT INTO `specificationmaster` VALUES (1001,'IS 2062 Gr A','IS 2062 Gr A','TRUE','SYSTEM','2012-10-27 16:35:33'),(1002,'IS 2062 Gr B','IS 2062 Gr B','TRUE','SYSTEM','2012-10-27 16:35:33'),(1003,'IS 2062 Gr C','IS 2062 Gr C','TRUE','SYSTEM','2012-10-27 16:35:33'),(1004,'IS 1239','IS 1239','TRUE','SYSTEM','2012-10-27 16:35:33'),(1005,'IS 3589','IS 3589','TRUE','SYSTEM','2012-10-27 16:35:33'),(1006,'SA 515 Gr 70','SA 515 Gr 70','TRUE','SYSTEM','2012-10-27 16:35:33'),(1007,'SA 516 Gr 70','SA 516 Gr 70','TRUE','SYSTEM','2012-10-27 16:35:33'),(1008,'SA 106','SA 106','TRUE','SYSTEM','2012-10-27 16:35:33'),(1009,'SA 179','SA 179','TRUE','SYSTEM','2012-10-27 16:35:33'),(1010,'A 105','A 105','TRUE','SYSTEM','2012-10-27 16:35:33'),(1011,'SA 240 Typ 304','SA 240 Typ 304','TRUE','SYSTEM','2012-10-27 16:35:33'),(1012,'SA 240 Typ 304L','SA 240 Typ 304L','TRUE','SYSTEM','2012-10-27 16:35:33'),(1013,'SA 240 Typ 316','SA 240 Typ 316','TRUE','SYSTEM','2012-10-27 16:35:33'),(1014,'SA 240 Typ 316L','SA 240 Typ 316L','TRUE','SYSTEM','2012-10-27 16:35:33'),(1015,'EN8','EN8','TRUE','SYSTEM','2012-10-27 16:35:33'),(1016,'SA 479 Typ 304','SA 479 Typ 304','TRUE','SYSTEM','2012-10-27 16:35:33'),(1017,'SA 479 Typ 316','SA 479 Typ 316','TRUE','SYSTEM','2012-10-27 16:35:33'),(1018,'SA 213 Tp 304','SA 213 Tp 304','TRUE','SYSTEM','2012-10-27 16:35:33'),(1019,'SA 213 Tp 304L','SA 213 Tp 304L','TRUE','SYSTEM','2012-10-27 16:35:33'),(1020,'SA 213 Tp 316','SA 213 Tp 316','TRUE','SYSTEM','2012-10-27 16:35:33'),(1021,'SA 213 Tp 316L','SA 213 Tp 316L','TRUE','SYSTEM','2012-10-27 16:35:33'),(1022,'SA 312 Tp 304','SA 312 Tp 304','TRUE','SYSTEM','2012-10-27 16:35:33'),(1023,'SA 312 Tp 304L','SA 312 Tp 304L','TRUE','SYSTEM','2012-10-27 16:35:33'),(1024,'SA 312 Tp 316','SA 312 Tp 316','TRUE','SYSTEM','2012-10-27 16:35:33'),(1025,'SA 312 Tp 316L','SA 312 Tp 316L','TRUE','SYSTEM','2012-10-27 16:35:33'),(1026,'SA 249 Tp 304','SA 249 Tp 304','TRUE','SYSTEM','2012-10-27 16:35:33'),(1027,'SA 249 Tp 304L','SA 249 Tp 304L','TRUE','SYSTEM','2012-10-27 16:35:33'),(1028,'SA 249 Tp 316','SA 249 Tp 316','TRUE','SYSTEM','2012-10-27 16:35:33'),(1029,'SA 249 Tp 316L','SA 249 Tp 316L','TRUE','SYSTEM','2012-10-27 16:35:33'),(1030,'A 182 F 304','A 182 F 304','TRUE','SYSTEM','2012-10-27 16:35:33'),(1031,'A 182 F 304L','A 182 F 304L','TRUE','SYSTEM','2012-10-27 16:35:33'),(1032,'A 182 F 316','A 182 F 316','TRUE','SYSTEM','2012-10-27 16:35:33'),(1033,'A 182 F 316L','A 182 F 316L','TRUE','SYSTEM','2012-10-27 16:35:33'),(1034,'Hastalloy C22','Hastalloy C22','TRUE','SYSTEM','2012-10-27 16:35:33'),(1035,'Hastalloy C276','Hastalloy C276','TRUE','SYSTEM','2012-10-27 16:35:33');
/*!40000 ALTER TABLE `specificationmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agencymaster`
--

DROP TABLE IF EXISTS `agencymaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agencymaster` (
  `Agency_Code` int(11) NOT NULL DEFAULT '0',
  `Agency_Name` varchar(64) DEFAULT NULL,
  `Remarks` varchar(128) DEFAULT NULL,
  `Status` varchar(8) NOT NULL DEFAULT 'TRUE',
  `Created_By` varchar(32) NOT NULL DEFAULT 'SYSTEM',
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Agency_Code`),
  UNIQUE KEY `Agency_Code_UNIQUE` (`Agency_Code`),
  KEY `Agency_Name` (`Agency_Name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agencymaster`
--

LOCK TABLES `agencymaster` WRITE;
/*!40000 ALTER TABLE `agencymaster` DISABLE KEYS */;
INSERT INTO `agencymaster` VALUES (1001,'Internal','Internal','TRUE','SYSTEM','2012-10-27 16:30:19'),(1002,'Industrial Insp Bureau','Industrial Insp Bureau','TRUE','SYSTEM','2012-10-27 16:30:19'),(1003,'Signate Engrs','Signate Engrs','TRUE','SYSTEM','2012-10-27 16:30:19'),(1004,'Electromech Engg. Ent','Electromech Engg. Enterprise','TRUE','SYSTEM','2012-10-27 16:30:19'),(1005,'Mott MacDonald','Mott MacDonald','TRUE','SYSTEM','2012-10-27 16:30:19'),(1006,'Joshi & Associates','Joshi & Associates','TRUE','SYSTEM','2012-10-27 16:30:19'),(1007,'Customer','Customer','TRUE','SYSTEM','2012-10-27 16:30:19');
/*!40000 ALTER TABLE `agencymaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customermaster`
--

DROP TABLE IF EXISTS `customermaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customermaster` (
  `Customer_Code` int(11) NOT NULL DEFAULT '0',
  `Customer_Name` varchar(64) DEFAULT NULL,
  `Remarks` varchar(128) DEFAULT NULL,
  `Status` varchar(8) NOT NULL DEFAULT 'TRUE',
  `Created_By` varchar(32) NOT NULL DEFAULT 'SYSTEM',
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Customer_Code`),
  UNIQUE KEY `Customer_Code_UNIQUE` (`Customer_Code`),
  KEY `Customer_Name` (`Customer_Name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customermaster`
--

LOCK TABLES `customermaster` WRITE;
/*!40000 ALTER TABLE `customermaster` DISABLE KEYS */;
INSERT INTO `customermaster` VALUES (1001,'Multi Org Pvt. Ltd.','Multi Org Pvt. Ltd.','TRUE','SYSTEM','2012-10-27 16:31:42'),(1002,'Orchid Chem & Pharma Ltd.','Orchid Chem & Pharma Ltd.','TRUE','SYSTEM','2012-10-27 16:31:42'),(1003,'Emcure Pharma Ltd.','Emcure Pharma Ltd.','TRUE','SYSTEM','2012-10-27 16:31:42'),(1004,'Abhideep Chem Pvt. Ltd.','Abhideep Chem Pvt. Ltd.','TRUE','SYSTEM','2012-10-27 16:31:42'),(1005,'Sterling Biotech Ltd.','Sterling Biotech Ltd.','TRUE','SYSTEM','2012-10-27 16:31:42'),(1006,'Gopal Corpn Ltd.','Gopal Corpn Ltd.','TRUE','SYSTEM','2012-10-27 16:31:42'),(1007,'Machin Fabrik','Machin Fabrik','TRUE','SYSTEM','2012-10-27 16:31:42'),(1008,'S H Kelkar & Co Pvt. Ltd.','S H Kelkar & Co Pvt. Ltd.','TRUE','SYSTEM','2012-10-27 16:31:42'),(1009,'K V Arochem Pvt. Ltd.','K V Arochem Pvt. Ltd.','TRUE','SYSTEM','2012-10-27 16:31:42'),(1010,'Keva Biotech Pvt. Ltd.','Keva Biotech Pvt. Ltd.','TRUE','SYSTEM','2012-10-27 16:31:42'),(1011,'Asian Paints Ltd.','Asian Paints Ltd.','TRUE','SYSTEM','2012-10-27 16:31:42'),(1012,'United Engg Company','United Engg Company','TRUE','SYSTEM','2012-10-27 16:31:42'),(1013,'Vinati Organics Ltd.','Vinati Organics Ltd.','TRUE','SYSTEM','2012-10-27 16:31:42'),(1014,'Viral Alkalies Ltd.','Viral Alkalies Ltd.','TRUE','SYSTEM','2012-10-27 16:31:42'),(1015,'Hikal Ltd','Hikal Ltd','TRUE','SYSTEM','2012-10-27 16:31:42');
/*!40000 ALTER TABLE `customermaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laboratorymaster`
--

DROP TABLE IF EXISTS `laboratorymaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `laboratorymaster` (
  `Laboratory_Code` int(11) NOT NULL DEFAULT '0',
  `Laboratory_Name` varchar(64) DEFAULT NULL,
  `Remarks` varchar(128) DEFAULT NULL,
  `Status` varchar(8) NOT NULL DEFAULT 'TRUE',
  `Created_By` varchar(32) NOT NULL DEFAULT 'SYSTEM',
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Laboratory_Code`),
  UNIQUE KEY `Laboratory_Code_UNIQUE` (`Laboratory_Code`),
  KEY `Laboratory_Name` (`Laboratory_Name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laboratorymaster`
--

LOCK TABLES `laboratorymaster` WRITE;
/*!40000 ALTER TABLE `laboratorymaster` DISABLE KEYS */;
INSERT INTO `laboratorymaster` VALUES (1001,'Elca Lab','Elca Lab','TRUE','SYSTEM','2012-10-27 16:34:10'),(1002,'Metallugrical Ser','Metallugrical Ser','TRUE','SYSTEM','2012-10-27 16:34:10'),(1003,'Metallurgical Lab','Metallurgical Lab','TRUE','SYSTEM','2012-10-27 16:34:10');
/*!40000 ALTER TABLE `laboratorymaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rolemaster`
--

DROP TABLE IF EXISTS `rolemaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rolemaster` (
  `Role_Code` int(11) NOT NULL DEFAULT '0',
  `Role_Name` varchar(64) DEFAULT NULL,
  `Remarks` varchar(128) DEFAULT NULL,
  `Status` varchar(8) NOT NULL DEFAULT 'TRUE',
  `Created_By` varchar(32) NOT NULL DEFAULT 'SYSTEM',
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Role_Code`),
  UNIQUE KEY `Role_Code_UNIQUE` (`Role_Code`),
  KEY `Role_Name` (`Role_Name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rolemaster`
--

LOCK TABLES `rolemaster` WRITE;
/*!40000 ALTER TABLE `rolemaster` DISABLE KEYS */;
INSERT INTO `rolemaster` VALUES (1001,'Admin','Asministrator','TRUE','SYSTEM','2012-10-27 16:35:05'),(1002,'User','Allowed to insert details','TRUE','SYSTEM','2012-10-27 16:35:05'),(1003,'Viewer','Alowed to view details','TRUE','SYSTEM','2012-10-27 16:35:05');
/*!40000 ALTER TABLE `rolemaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materialmaster`
--

DROP TABLE IF EXISTS `materialmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materialmaster` (
  `Material_Code` int(11) NOT NULL,
  `Ct_Number` varchar(64) DEFAULT NULL,
  `Inspection_Agency` int(11) DEFAULT NULL,
  `Specification` int(11) DEFAULT NULL,
  `Item` int(11) DEFAULT NULL,
  `Size` varchar(64) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `Heat_Number` varchar(128) DEFAULT NULL,
  `Plate_Number` varchar(128) DEFAULT NULL,
  `Status` varchar(8) NOT NULL DEFAULT 'TRUE',
  `Created_By` varchar(32) NOT NULL DEFAULT 'SYSTEM',
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Material_Code`),
  UNIQUE KEY `Material_Code_UNIQUE` (`Material_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materialmaster`
--

LOCK TABLES `materialmaster` WRITE;
/*!40000 ALTER TABLE `materialmaster` DISABLE KEYS */;
INSERT INTO `materialmaster` VALUES (1001,'MP12/1',1001,1013,1003,'24',24,'heat','plate','TRUE','SYSTEM','2012-11-05 14:31:13');
/*!40000 ALTER TABLE `materialmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testmaster`
--

DROP TABLE IF EXISTS `testmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testmaster` (
  `Test_Code` int(11) NOT NULL DEFAULT '0',
  `Test_Name` varchar(64) DEFAULT NULL,
  `Remarks` varchar(128) DEFAULT NULL,
  `Status` varchar(8) NOT NULL DEFAULT 'TRUE',
  `Created_By` varchar(32) NOT NULL DEFAULT 'SYSTEM',
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Test_Code`),
  UNIQUE KEY `Test_Code_UNIQUE` (`Test_Code`),
  KEY `Test_Name` (`Test_Name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testmaster`
--

LOCK TABLES `testmaster` WRITE;
/*!40000 ALTER TABLE `testmaster` DISABLE KEYS */;
INSERT INTO `testmaster` VALUES (1001,'Chemical','Chemical','TRUE','SYSTEM','2012-10-27 16:29:19'),(1002,'IGC - A','IGC - A','TRUE','SYSTEM','2012-10-27 16:29:19'),(1003,'IGC - E','IGC - E','TRUE','SYSTEM','2012-10-27 16:29:19'),(1004,'Macro','Macro','TRUE','SYSTEM','2012-10-27 16:29:19'),(1005,'Tensile','Tensile','TRUE','SYSTEM','2012-10-27 16:29:19'),(1006,'Bend','Bend','TRUE','SYSTEM','2012-10-27 16:29:19'),(1007,'Flattening','Flattening','TRUE','SYSTEM','2012-10-27 16:29:19'),(1008,'Flarring','Flarring','TRUE','SYSTEM','2012-10-27 16:29:19'),(1009,'Radiography','Radiography','TRUE','SYSTEM','2012-10-27 16:29:19'),(1010,'Ultrasound','Ultrasound','TRUE','SYSTEM','2012-10-27 16:29:19');
/*!40000 ALTER TABLE `testmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemmaster`
--

DROP TABLE IF EXISTS `itemmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemmaster` (
  `Item_Code` int(11) NOT NULL DEFAULT '0',
  `Item_Name` varchar(64) DEFAULT NULL,
  `Remarks` varchar(128) DEFAULT NULL,
  `Status` varchar(8) NOT NULL DEFAULT 'TRUE',
  `Created_By` varchar(32) NOT NULL DEFAULT 'SYSTEM',
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Item_Code`),
  UNIQUE KEY `Item_Code_UNIQUE` (`Item_Code`),
  KEY `Item_Name` (`Item_Name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemmaster`
--

LOCK TABLES `itemmaster` WRITE;
/*!40000 ALTER TABLE `itemmaster` DISABLE KEYS */;
INSERT INTO `itemmaster` VALUES (1001,'Sheet','Sheet','TRUE','SYSTEM','2012-10-27 16:36:30'),(1002,'Plate','Plate','TRUE','SYSTEM','2012-10-27 16:36:30'),(1003,'Round Bar','Round Bar','TRUE','SYSTEM','2012-10-27 16:36:30'),(1004,'Pipe','Pipe','TRUE','SYSTEM','2012-10-27 16:36:30'),(1005,'Tube','Tube','TRUE','SYSTEM','2012-10-27 16:36:30'),(1006,'Angle','Angle','TRUE','SYSTEM','2012-10-27 16:36:30'),(1007,'Channel','Channel','TRUE','SYSTEM','2012-10-27 16:36:30'),(1008,'Beam','Beam','TRUE','SYSTEM','2012-10-27 16:36:30'),(1009,'Flat','Flat','TRUE','SYSTEM','2012-10-27 16:36:30'),(1010,'Profile','Profile','TRUE','SYSTEM','2012-10-27 16:36:30'),(1011,'Others','Others','TRUE','SYSTEM','2012-10-27 16:36:30');
/*!40000 ALTER TABLE `itemmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materialtests`
--

DROP TABLE IF EXISTS `materialtests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materialtests` (
  `Test_Code` int(11) NOT NULL,
  `Sample_Id` varchar(32) DEFAULT NULL,
  `Test` int(11) DEFAULT NULL,
  `Customer` int(11) DEFAULT NULL,
  `Equipments` varchar(128) DEFAULT NULL,
  `Laboratory` int(11) DEFAULT NULL,
  `Report_Date` varchar(64) DEFAULT NULL,
  `Report_Number` varchar(128) DEFAULT NULL,
  `Result` int(11) DEFAULT NULL,
  `Remarks` varchar(128) DEFAULT NULL,
  `Witnessed_by` varchar(128) DEFAULT NULL,
  `Failure_Reason` varchar(256) DEFAULT NULL,
  `Material_Code` int(11) DEFAULT NULL,
  `Status` varchar(8) NOT NULL DEFAULT 'TRUE',
  `Created_By` varchar(32) NOT NULL DEFAULT 'SYSTEM',
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Test_Code`),
  UNIQUE KEY `Test_Code_UNIQUE` (`Test_Code`),
  KEY `FK_Material_Code_idx` (`Material_Code`),
  CONSTRAINT `FK_Material_Code` FOREIGN KEY (`Material_Code`) REFERENCES `materialmaster` (`Material_Code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materialtests`
--

LOCK TABLES `materialtests` WRITE;
/*!40000 ALTER TABLE `materialtests` DISABLE KEYS */;
INSERT INTO `materialtests` VALUES (1001,'B',1005,1009,'-',1001,'05-11-12','-',1002,'-','-','-',1001,'TRUE','SYSTEM','2012-11-05 14:31:35'),(1002,'A',1001,1002,'-',1002,'01-11-12','-',1001,'-','-','-',1001,'TRUE','SYSTEM','2012-11-05 14:31:14');
/*!40000 ALTER TABLE `materialtests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `heatchartmaster`
--

DROP TABLE IF EXISTS `heatchartmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `heatchartmaster` (
  `Chart_Number` varchar(64) NOT NULL DEFAULT '',
  `Equipment` varchar(128) DEFAULT NULL,
  `Customer` varchar(128) DEFAULT NULL,
  `Po_Details` varchar(128) DEFAULT NULL,
  `Drawing_Number` varchar(128) DEFAULT NULL,
  `Surveyor` varchar(128) DEFAULT NULL,
  `Tag_Number` varchar(128) DEFAULT NULL,
  `Status` varchar(8) NOT NULL DEFAULT 'TRUE',
  `Created_By` varchar(32) NOT NULL DEFAULT 'SYSTEM',
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Chart_Number`),
  UNIQUE KEY `Chart_Number` (`Chart_Number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `heatchartmaster`
--

LOCK TABLES `heatchartmaster` WRITE;
/*!40000 ALTER TABLE `heatchartmaster` DISABLE KEYS */;
/*!40000 ALTER TABLE `heatchartmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resultmaster`
--

DROP TABLE IF EXISTS `resultmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resultmaster` (
  `Result_Code` int(11) NOT NULL DEFAULT '0',
  `Result_Name` varchar(64) DEFAULT NULL,
  `Remarks` varchar(128) DEFAULT NULL,
  `Status` varchar(8) NOT NULL DEFAULT 'TRUE',
  `Created_By` varchar(32) NOT NULL DEFAULT 'SYSTEM',
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Result_Code`),
  UNIQUE KEY `Result_Code_UNIQUE` (`Result_Code`),
  KEY `Result_Name` (`Result_Name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resultmaster`
--

LOCK TABLES `resultmaster` WRITE;
/*!40000 ALTER TABLE `resultmaster` DISABLE KEYS */;
INSERT INTO `resultmaster` VALUES (1001,'Confirm','Confirm','TRUE','SYSTEM','2012-10-27 16:34:41'),(1002,'Rejected','Rejected','TRUE','SYSTEM','2012-10-27 16:34:41'),(1003,'Re-Test','Re-Test','TRUE','SYSTEM','2012-10-27 16:34:41');
/*!40000 ALTER TABLE `resultmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `codemaster`
--

DROP TABLE IF EXISTS `codemaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `codemaster` (
  `Code_Number` int(11) NOT NULL,
  `Code_Name` varchar(64) DEFAULT NULL,
  `Code_Value` varchar(64) DEFAULT NULL,
  `Code_Datatype` varchar(32) DEFAULT NULL,
  `Code_Desc` varchar(64) DEFAULT NULL,
  `Status` varchar(8) NOT NULL DEFAULT 'TRUE',
  `Created_By` varchar(32) NOT NULL DEFAULT 'SYSTEM',
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Code_Number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `codemaster`
--

LOCK TABLES `codemaster` WRITE;
/*!40000 ALTER TABLE `codemaster` DISABLE KEYS */;
INSERT INTO `codemaster` VALUES (1001,'Default_Year','2012','Integer','Default entry year for data','TRUE','YSTEM','2012-11-06 14:24:31');
/*!40000 ALTER TABLE `codemaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `heatchartsheets`
--

DROP TABLE IF EXISTS `heatchartsheets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `heatchartsheets` (
  `Chart_Number` varchar(64) NOT NULL DEFAULT '',
  `Sheet_Number` int(10) NOT NULL,
  `Sequence_Number` int(10) DEFAULT NULL,
  `Part_Number` varchar(128) DEFAULT NULL,
  `Part_Name` varchar(128) DEFAULT NULL,
  `Specied_Size` varchar(128) DEFAULT NULL,
  `Specied_Grade` varchar(128) DEFAULT NULL,
  `Ct_Number` varchar(64) NOT NULL,
  `Status` varchar(8) NOT NULL DEFAULT 'TRUE',
  `Created_By` varchar(32) NOT NULL DEFAULT 'SYSTEM',
  `Created_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Chart_Number`,`Sheet_Number`,`Ct_Number`),
  KEY `Chart_Number` (`Chart_Number`) USING BTREE,
  KEY `Sheet_Number` (`Sheet_Number`) USING BTREE,
  KEY `Ct_Number` (`Ct_Number`) USING BTREE,
  CONSTRAINT `FK_HS_Chart_Number` FOREIGN KEY (`Chart_Number`) REFERENCES `heatchartmaster` (`Chart_Number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `heatchartsheets`
--

LOCK TABLES `heatchartsheets` WRITE;
/*!40000 ALTER TABLE `heatchartsheets` DISABLE KEYS */;
/*!40000 ALTER TABLE `heatchartsheets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'metalplants'
--

--
-- Dumping routines for database 'metalplants'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-11-06 19:59:28
