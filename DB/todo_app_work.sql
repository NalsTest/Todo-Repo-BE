-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: todo_app
-- ------------------------------------------------------
-- Server version	5.7.37-log

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
-- Table structure for table `work`
--

DROP TABLE IF EXISTS `work`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ending_date` date DEFAULT NULL,
  `flag_delete` tinyint(1) DEFAULT '0',
  `starting_date` date DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `work_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work`
--

LOCK TABLES `work` WRITE;
/*!40000 ALTER TABLE `work` DISABLE KEYS */;
INSERT INTO `work` VALUES (1,'2022-04-27',1,'2022-04-27',1,'Học Python'),(2,'2022-07-18',0,'2022-05-02',0,'Đi tập gym'),(3,'2022-04-18',0,'2022-04-18',1,'Nấu bữa tối'),(4,'2022-04-19',0,'2022-04-19',0,'Xem 5 video tuts về aws'),(5,'2022-04-26',0,'2022-04-20',1,'Hoàn thành đánh giá tuần'),(6,'2022-04-23',0,'2022-04-21',0,'Review code'),(7,'2022-04-23',0,'2022-04-21',1,'Coding feature add'),(8,'2022-04-18',0,'2022-04-17',0,'Coding feature edit'),(9,'2022-04-19',0,'2022-04-18',1,'Coding feature delete'),(10,'2022-04-21',0,'2022-04-19',0,'Coding feature search'),(11,'2022-04-21',0,'2022-04-19',0,'Coding feature paging'),(12,'2022-04-21',0,'2022-04-19',0,'Coding feature sort'),(13,'2022-04-22',0,'2022-04-22',1,'Coding unit test'),(14,'2022-04-27',0,'2022-04-22',0,'Ôn tập Java'),(15,'2022-04-27',0,'2022-04-22',0,'Làm quiz OCA'),(16,'2022-04-28',0,'2022-04-28',1,'Học 20 từ vựng tiếng Anh'),(17,'2022-04-24',0,'2022-04-22',0,'Quiz test angular'),(18,'2022-04-29',0,'2022-04-29',0,'Deloy project'),(19,'2022-04-27',0,'2022-04-27',0,'Report tuần'),(20,'2022-04-27',0,'2022-04-27',0,'Review bài tập'),(21,'2022-04-27',0,'2022-04-27',0,'Học Java');
/*!40000 ALTER TABLE `work` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-18  1:10:08
