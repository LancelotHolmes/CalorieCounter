CREATE DATABASE  IF NOT EXISTS `id27315681` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `id27315681`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: id27315681
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `eat`
--

DROP TABLE IF EXISTS `eat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eat` (
  `food_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `eat_time` datetime NOT NULL,
  `quentity` double DEFAULT NULL,
  `measure` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`,`eat_time`),
  KEY `eat_food` (`food_id`),
  CONSTRAINT `eat_ibfk_1` FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`) ON UPDATE CASCADE,
  CONSTRAINT `eat_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eat`
--

LOCK TABLES `eat` WRITE;
/*!40000 ALTER TABLE `eat` DISABLE KEYS */;
INSERT INTO `eat` VALUES (30,1,'2016-03-10 17:45:22',1,'tbsp'),(25,2,'2016-03-08 07:35:02',1,'med'),(25,3,'2016-03-08 07:35:02',6,'med'),(30,3,'2016-03-10 17:45:22',2,'tbsp'),(21,4,'2016-03-10 17:45:22',3,'cup');
/*!40000 ALTER TABLE `eat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food` (
  `food_id` int(11) NOT NULL AUTO_INCREMENT,
  `food_name` varchar(25) NOT NULL,
  `calorie` double NOT NULL,
  `fat` double NOT NULL,
  `measure` varchar(45) NOT NULL,
  `serving` double NOT NULL,
  PRIMARY KEY (`food_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'Honey',64,0,'tbsp',1),(2,'Fish stick',65,3,'oz',1),(3,'Eggs,fried with fat',83,6,'med',1),(4,'Apple',81,0,'each',1),(5,'Bacon pork',36,3,'slice',1),(6,'Banana fresh',96,0,'each',1),(7,'Beef canned',166,10,'cup',0.5),(8,'Beer regular',148,0,'can',1),(9,'Biscuit from mix',125,7,'each',1),(10,'Bread stick',64,1,'each',1),(11,'Cabbage cooked',31,2,'cup',0.5),(12,'Cake angel food',212,0,'pc',1),(13,'Chocolate bar regular',221,13,'bar',1),(14,'Cream cheese regular',101,10,'tbsp',2),(15,'Chicken dark meat',56,2,'oz',1),(16,'Chicken tenders',232,12,'pcs',6),(17,'Chipped or dried beef',140,3,'oz',3),(18,'Coconut milk regular',473,51,'cup',1),(19,'Coffee regular',5,0,'cup',1),(20,'Corn dog',341,25,'each',1),(21,'Dirty rice',271,5,'cup',1),(22,'Duck domestic skin eaten',287,24,'oz',3),(23,'Fruit ice',124,0,'cup',0.5),(24,'Ham hocks',109,5,'pc',1),(25,'Kiwi',46,0,'med',1),(26,'Lard',116,13,'tbsp',1),(27,'Lemon juice',3,0,'tbsp',1),(28,'Lobster',83,0,'oz',3),(29,'Mango nectar',100,0,'cup',0.75),(30,'Miso',35,1,'tbsp',1),(31,'Parsnips',63,0,'cup',0.5),(32,'Rutabaga',33,0,'cup',0.5),(33,'Quinoa',79,1,'cup',0.5);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `user_id` int(11) NOT NULL,
  `record_date` date NOT NULL,
  `calorie_consumed` double DEFAULT NULL,
  `calorie_burned` double DEFAULT NULL,
  `calorie_goal` double DEFAULT NULL,
  `calorie_remaining` double DEFAULT NULL,
  `total_steps` double DEFAULT NULL,
  PRIMARY KEY (`user_id`,`record_date`),
  CONSTRAINT `report_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `pwd` varchar(45) NOT NULL,
  `age` tinyint(4) NOT NULL,
  `height` double NOT NULL,
  `weight` double NOT NULL,
  `gender` enum('M','F','U') NOT NULL,
  `level_of_activity` tinyint(4) NOT NULL,
  `steps_per_mile` double NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Lancelot','123',22,178,85,'M',3,2000),(2,'Kathy','123',21,158,45,'F',2,1800),(3,'Joe','123',24,170,65,'M',2,1900),(4,'Lydia','123',23,168,50,'F',3,2000);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-23 23:37:52
