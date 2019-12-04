-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: mysql3.cs.stonybrook.edu    Database: mwcoulter
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `Account`
--

DROP TABLE IF EXISTS `Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Account` (
  `Id` int(11) NOT NULL,
  `DateOpened` date DEFAULT NULL,
  `AcctType` char(20) DEFAULT NULL,
  `Customer` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Customer` (`Customer`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`Customer`) REFERENCES `Customer` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
INSERT INTO `Account` VALUES (1,'2006-10-01','unlimited-2',444444444),(2,'2006-10-15','Unlimited-2',222222222),(116789,'2019-12-03','limited',555667777);
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Actor`
--

DROP TABLE IF EXISTS `Actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Actor` (
  `Id` int(11) NOT NULL,
  `Name` char(20) NOT NULL,
  `Age` int(11) NOT NULL,
  `Gender` char(1) NOT NULL,
  `Rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Actor`
--

LOCK TABLES `Actor` WRITE;
/*!40000 ALTER TABLE `Actor` DISABLE KEYS */;
INSERT INTO `Actor` VALUES (1,'Al Pacino',63,'M',5),(2,'Tim Robbins',53,'M',2);
/*!40000 ALTER TABLE `Actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AppearedIn`
--

DROP TABLE IF EXISTS `AppearedIn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `AppearedIn` (
  `ActorId` int(11) NOT NULL,
  `MovieId` int(11) NOT NULL,
  PRIMARY KEY (`ActorId`,`MovieId`),
  KEY `MovieId` (`MovieId`),
  CONSTRAINT `appearedin_ibfk_1` FOREIGN KEY (`ActorId`) REFERENCES `Actor` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `appearedin_ibfk_2` FOREIGN KEY (`MovieId`) REFERENCES `Movie` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AppearedIn`
--

LOCK TABLES `AppearedIn` WRITE;
/*!40000 ALTER TABLE `AppearedIn` DISABLE KEYS */;
INSERT INTO `AppearedIn` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `AppearedIn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer` (
  `Id` int(11) NOT NULL,
  `Email` char(32) DEFAULT NULL,
  `Rating` int(11) DEFAULT NULL,
  `CreditCardNumber` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`Id`) REFERENCES `Person` (`SSN`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (111111111,'syang@cs.sunysb.edu',2,1234567810000000),(222222222,'vicdu@cs.sunysb.edu',5,5678123400000000),(333333333,'jsmith@ic.sunysb.edu',1,2345678923456789),(444444444,'pml@cs.sunysb.edu',1,6789234567892345),(555667777,'testUser@aol.com',3,9876098777771234),(888888888,'123@123.com',3,1234123420000000);
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Employee` (
  `ID` int(11) NOT NULL,
  `SSN` int(11) DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `HourlyRate` int(11) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SSN` (`SSN`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`SSN`) REFERENCES `Person` (`SSN`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (123456789,123456789,'2005-11-01',10,'dsmith@cs.stonybrook.edu'),(789123456,789123456,'2006-02-02',50,NULL),(888991010,888991010,'2019-05-10',15,'hdannon@cs.stonybrook.edu');
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LivesAt`
--

DROP TABLE IF EXISTS `LivesAt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LivesAt` (
  `ZipCode` int(11) NOT NULL,
  `SSN` int(11) NOT NULL,
  PRIMARY KEY (`ZipCode`,`SSN`),
  KEY `_idx` (`SSN`),
  CONSTRAINT `SSN` FOREIGN KEY (`SSN`) REFERENCES `Person` (`SSN`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `ZipCode` FOREIGN KEY (`ZipCode`) REFERENCES `Location` (`ZipCode`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LivesAt`
--

LOCK TABLES `LivesAt` WRITE;
/*!40000 ALTER TABLE `LivesAt` DISABLE KEYS */;
INSERT INTO `LivesAt` VALUES (11514,56551234),(11790,111111111),(11790,123456789),(11790,222222222),(93536,333333333),(11794,444444444),(12640,555667777),(11794,789123456),(9090,888888888),(11790,888991010),(12345,999999999);
/*!40000 ALTER TABLE `LivesAt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Location`
--

DROP TABLE IF EXISTS `Location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Location` (
  `ZipCode` int(11) NOT NULL,
  `City` char(20) NOT NULL,
  `State` char(20) NOT NULL,
  PRIMARY KEY (`ZipCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Location`
--

LOCK TABLES `Location` WRITE;
/*!40000 ALTER TABLE `Location` DISABLE KEYS */;
INSERT INTO `Location` VALUES (9090,'Hawaii','HI'),(11514,'Carle Place','NY'),(11790,'Stony','NY'),(11794,'Stony Brook','NY'),(11795,'Stony Brook','NY'),(12345,'Hell','PA'),(12640,'Rochester','NY'),(93536,'Los Angeles','CA');
/*!40000 ALTER TABLE `Location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Login`
--

DROP TABLE IF EXISTS `Login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Login` (
  `Username` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Login`
--

LOCK TABLES `Login` WRITE;
/*!40000 ALTER TABLE `Login` DISABLE KEYS */;
INSERT INTO `Login` VALUES ('123@123.com','1234','customer'),('dsmith@cs.stonybrook.edu','d','customerRepresentative'),('estark@cs.stonybrook.edu','320','customerRepresentative'),('hdannon@cs.stonybrook.edu','heymi','customerRepresentative'),('Manager@manager.com','manager','manager'),('testUser@aol.com','admin','customer'),('vicdu@cs.sunysb.edu','vic','customer');
/*!40000 ALTER TABLE `Login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Movie`
--

DROP TABLE IF EXISTS `Movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Movie` (
  `Id` int(11) NOT NULL,
  `Name` char(20) NOT NULL,
  `MType` char(20) NOT NULL,
  `Rating` int(11) DEFAULT NULL,
  `DistrFee` float DEFAULT NULL,
  `NumCopies` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Movie`
--

LOCK TABLES `Movie` WRITE;
/*!40000 ALTER TABLE `Movie` DISABLE KEYS */;
INSERT INTO `Movie` VALUES (1,'The Godfather','Drama',5,10000,2),(2,'Shawshank Redemption','Drama',4,1000,2),(3,'Borat','Comedy',3,500,1);
/*!40000 ALTER TABLE `Movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MovieQ`
--

DROP TABLE IF EXISTS `MovieQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `MovieQ` (
  `AccountId` int(11) NOT NULL,
  `MovieId` int(11) NOT NULL,
  PRIMARY KEY (`AccountId`,`MovieId`),
  KEY `MovieId` (`MovieId`),
  CONSTRAINT `movieq_ibfk_1` FOREIGN KEY (`AccountId`) REFERENCES `Account` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `movieq_ibfk_2` FOREIGN KEY (`MovieId`) REFERENCES `Movie` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MovieQ`
--

LOCK TABLES `MovieQ` WRITE;
/*!40000 ALTER TABLE `MovieQ` DISABLE KEYS */;
/*!40000 ALTER TABLE `MovieQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Orders` (
  `Id` int(11) NOT NULL,
  `RDate` datetime DEFAULT NULL,
  `ReturnDate` date DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
INSERT INTO `Orders` VALUES (1,'2009-11-11 10:00:00','2009-11-14'),(2,'2009-11-11 18:15:00','2019-12-03'),(3,'2009-11-12 09:30:00',NULL),(4,'2009-11-21 22:22:00',NULL);
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Person`
--

DROP TABLE IF EXISTS `Person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Person` (
  `SSN` int(11) NOT NULL,
  `LastName` char(20) NOT NULL,
  `FirstName` char(20) NOT NULL,
  `Address` char(20) DEFAULT NULL,
  `ZipCode` int(11) DEFAULT NULL,
  `Telephone` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`SSN`),
  KEY `ZipCode` (`ZipCode`),
  CONSTRAINT `person_ibfk_1` FOREIGN KEY (`ZipCode`) REFERENCES `Location` (`ZipCode`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Person`
--

LOCK TABLES `Person` WRITE;
/*!40000 ALTER TABLE `Person` DISABLE KEYS */;
INSERT INTO `Person` VALUES (56551234,'Aron','Arty','123 Cherry Ln.',11514,5167791234),(111111111,'Yang','Shang','123',11790,5166328959),(123456789,'Smith','David','123',11790,5162152345),(222222222,'Du','Victor','456 Fortune Road',11790,5166324360),(333333333,'Smith','John','789 Peace Blvd.',93536,3154434321),(444444444,'Philip','Lewis','135 Knowledge Lane',11794,5166668888),(555667777,'User','Test','123',12640,3612540978),(789123456,'Warren','David','456 Sunken Street',11794,6316329997),(888888888,'b','a','123 abc ln',9090,1231231234),(888991010,'Dannon','Heymi','100 Circle Road',11790,6318888888),(999999999,'b','a','123 abc ln',12345,1231231234);
/*!40000 ALTER TABLE `Person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Rental`
--

DROP TABLE IF EXISTS `Rental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Rental` (
  `AccountId` int(11) NOT NULL,
  `CustRepId` int(11) NOT NULL,
  `OrderId` int(11) NOT NULL,
  `MovieId` int(11) NOT NULL,
  PRIMARY KEY (`AccountId`,`CustRepId`,`OrderId`,`MovieId`),
  KEY `CustRepId` (`CustRepId`),
  KEY `OrderId` (`OrderId`),
  KEY `MovieId` (`MovieId`),
  CONSTRAINT `rental_ibfk_1` FOREIGN KEY (`AccountId`) REFERENCES `Account` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `rental_ibfk_2` FOREIGN KEY (`CustRepId`) REFERENCES `Employee` (`ID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `rental_ibfk_3` FOREIGN KEY (`OrderId`) REFERENCES `Orders` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `rental_ibfk_4` FOREIGN KEY (`MovieId`) REFERENCES `Movie` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Rental`
--

LOCK TABLES `Rental` WRITE;
/*!40000 ALTER TABLE `Rental` DISABLE KEYS */;
INSERT INTO `Rental` VALUES (1,123456789,1,1),(1,123456789,3,3),(2,123456789,2,3),(2,789123456,4,2);
/*!40000 ALTER TABLE `Rental` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `feetable`
--

DROP TABLE IF EXISTS `feetable`;
/*!50001 DROP VIEW IF EXISTS `feetable`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `feetable` AS SELECT 
 1 AS `Date`,
 1 AS `fee`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `feetable`
--

/*!50001 DROP VIEW IF EXISTS `feetable`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`mwcoulter`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `feetable` AS select extract(year_month from `O`.`RDate`) AS `Date`,(case when (`A`.`AcctType` = 'limited') then 10 when (`A`.`AcctType` = 'unlimited-1') then 15 when (`A`.`AcctType` = 'unlimited-2') then 20 else 25 end) AS `fee` from ((`orders` `O` join `rental` `R`) join `account` `A`) where ((`O`.`Id` = `R`.`OrderId`) and (`R`.`AccountId` = `A`.`Id`)) group by extract(year_month from `O`.`RDate`),`A`.`Id` */;
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

-- Dump completed on 2019-12-03 23:53:14
