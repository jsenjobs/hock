-- MySQL dump 10.16  Distrib 10.2.13-MariaDB, for osx10.13 (x86_64)
--
-- Host: localhost    Database: jtest001
-- ------------------------------------------------------
-- Server version	10.2.13-MariaDB

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `sex` varchar(16) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `lastlogin` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'jsen','nan','368b4820fb9d190a2a42dc20d37067c76e39024d7f630100','2018-03-24'),(2,'jack','nan','368b4820fb9d190a2a42dc20d37067c76e39024d7f630100','2018-03-29'),(3,'lucy','nv','368b4820fb9d190a2a42dc20d37067c76e39024d7f630100','2018-03-29'),(4,'echo','nv','368b4820fb9d190a2a42dc20d37067c76e39024d7f630100','2018-03-29'),(5,'fare','nv','368b4820fb9d190a2a42dc20d37067c76e39024d7f630100','2018-03-29'),(6,'luker','nan','368b4820fb9d190a2a42dc20d37067c76e39024d7f630100','2018-03-29'),(7,'eler','nv','368b4820fb9d190a2a42dc20d37067c76e39024d7f630100','2018-03-29'),(8,'luyer','nan','368b4820fb9d190a2a42dc20d37067c76e39024d7f630100','2018-03-29'),(9,'cuyt','nv','368b4820fb9d190a2a42dc20d37067c76e39024d7f630100','2018-03-29'),(11,'beer','nan','368b4820fb9d190a2a42dc20d37067c76e39024d7f630100','2018-03-29'),(13,'moker','nan','368b4820fb9d190a2a42dc20d37067c76e39024d7f630100','2018-03-29'),(14,'cute','nv','368b4820fb9d190a2a42dc20d37067c76e39024d7f630100','2018-03-29'),(15,'out','nan','368b4820fb9d190a2a42dc20d37067c76e39024d7f630100','2018-03-29');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datacontainer`
--

DROP TABLE IF EXISTS `datacontainer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `datacontainer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` varchar(128) DEFAULT NULL,
  `tip` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5485 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datacontainer`
--

LOCK TABLES `datacontainer` WRITE;
/*!40000 ALTER TABLE `datacontainer` DISABLE KEYS */;
INSERT INTO `datacontainer` VALUES (5475,'0','0'),(5476,'1','1'),(5477,'0','0'),(5478,'1','1'),(5481,'0','0'),(5482,'1','1'),(5483,'0','0'),(5484,'1','1');
/*!40000 ALTER TABLE `datacontainer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distest`
--

DROP TABLE IF EXISTS `distest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `distest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `c1` int(11) DEFAULT NULL,
  `c2` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `distest_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distest`
--

LOCK TABLES `distest` WRITE;
/*!40000 ALTER TABLE `distest` DISABLE KEYS */;
INSERT INTO `distest` VALUES (10,1,2),(11,6,4),(12,5,3),(13,3,5),(14,2,6),(15,7,4),(16,4,3),(17,3,7);
/*!40000 ALTER TABLE `distest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `fullinfo`
--

DROP TABLE IF EXISTS `fullinfo`;
/*!50001 DROP VIEW IF EXISTS `fullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `fullinfo` (
  `a_id` tinyint NOT NULL,
  `name` tinyint NOT NULL,
  `sex` tinyint NOT NULL,
  `password` tinyint NOT NULL,
  `lastlogin` tinyint NOT NULL,
  `w_id` tinyint NOT NULL,
  `create_time` tinyint NOT NULL,
  `content` tinyint NOT NULL,
  `l_id` tinyint NOT NULL,
  `link` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `link`
--

DROP TABLE IF EXISTS `link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `w_id` int(11) DEFAULT NULL,
  `link` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `links_id_uindex` (`id`),
  KEY `links_weibo_id_fk` (`w_id`),
  CONSTRAINT `links_weibo_id_fk` FOREIGN KEY (`w_id`) REFERENCES `weibo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `link`
--

LOCK TABLES `link` WRITE;
/*!40000 ALTER TABLE `link` DISABLE KEYS */;
INSERT INTO `link` VALUES (1,1,'www.baidu.com'),(2,1,'www.facebook.com'),(3,1,'www.google.com'),(4,2,'www.baidu.com'),(5,2,'www.github.com'),(6,3,'www.taobao.com'),(7,4,'https://blog.csdn.net'),(8,4,'https://www.cnblogs.com'),(9,5,'https://blog.csdn.net');
/*!40000 ALTER TABLE `link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quartzjob`
--

DROP TABLE IF EXISTS `quartzjob`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quartzjob` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(128) DEFAULT NULL,
  `job_group` varchar(128) DEFAULT NULL,
  `trigger_name` varchar(128) DEFAULT NULL,
  `trigger_group` varchar(128) DEFAULT NULL,
  `job_clazz` varchar(256) DEFAULT NULL,
  `cron` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `quartzjob_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quartzjob`
--

LOCK TABLES `quartzjob` WRITE;
/*!40000 ALTER TABLE `quartzjob` DISABLE KEYS */;
/*!40000 ALTER TABLE `quartzjob` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `queryp`
--

DROP TABLE IF EXISTS `queryp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `queryp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qp` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `queryp_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `queryp`
--

LOCK TABLES `queryp` WRITE;
/*!40000 ALTER TABLE `queryp` DISABLE KEYS */;
INSERT INTO `queryp` VALUES (1,'jsen');
/*!40000 ALTER TABLE `queryp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(64) DEFAULT 'NULL',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin'),(2,'customer');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_join_role001`
--

DROP TABLE IF EXISTS `test_join_role001`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_join_role001` (
  `name` varchar(64) DEFAULT NULL,
  `role` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_join_role001`
--

LOCK TABLES `test_join_role001` WRITE;
/*!40000 ALTER TABLE `test_join_role001` DISABLE KEYS */;
INSERT INTO `test_join_role001` VALUES ('jack','admin'),('tooth','customer'),('jsen','customer'),('jj','customer'),('lucy','admin'),('jsen','customer'),('Liner\n','customer'),('Tick','super');
/*!40000 ALTER TABLE `test_join_role001` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_union001`
--

DROP TABLE IF EXISTS `test_union001`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_union001` (
  `name` varchar(64) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_union001`
--

LOCK TABLES `test_union001` WRITE;
/*!40000 ALTER TABLE `test_union001` DISABLE KEYS */;
INSERT INTO `test_union001` VALUES ('jsen',0,23),('jsen',0,23),('jack',0,24),('lucy',1,22),('echo',1,22);
/*!40000 ALTER TABLE `test_union001` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_union002`
--

DROP TABLE IF EXISTS `test_union002`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_union002` (
  `name` varchar(64) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_union002`
--

LOCK TABLES `test_union002` WRITE;
/*!40000 ALTER TABLE `test_union002` DISABLE KEYS */;
INSERT INTO `test_union002` VALUES ('jsen001',0,23),('jack001',0,24),('lucy001',1,22),('echo',1,22);
/*!40000 ALTER TABLE `test_union002` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testtable`
--

DROP TABLE IF EXISTS `testtable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testtable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `sex` varchar(16) DEFAULT NULL,
  `testint` int(11) DEFAULT NULL,
  `testnumber` float DEFAULT NULL,
  `time` date DEFAULT NULL,
  `logical` int(11) DEFAULT 1,
  `testchar` varchar(64) DEFAULT NULL,
  `tenantid` int(11) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `testtable_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testtable`
--

LOCK TABLES `testtable` WRITE;
/*!40000 ALTER TABLE `testtable` DISABLE KEYS */;
/*!40000 ALTER TABLE `testtable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `name` varchar(64) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('jsen',1,23),('jack',2,18),('echo',3,24);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weibo`
--

DROP TABLE IF EXISTS `weibo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weibo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `a_id` int(11) DEFAULT NULL,
  `create_time` date DEFAULT current_timestamp(),
  `content` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `weibo_id_uindex` (`id`),
  KEY `weibo_account_id_fk` (`a_id`),
  CONSTRAINT `weibo_account_id_fk` FOREIGN KEY (`a_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weibo`
--

LOCK TABLES `weibo` WRITE;
/*!40000 ALTER TABLE `weibo` DISABLE KEYS */;
INSERT INTO `weibo` VALUES (1,1,'2018-03-29','jsen create weibo content001'),(2,1,'2018-03-29','jsen create weibo content002'),(3,2,'2018-03-29','jack create 001'),(4,3,'2018-03-29','lucy create 001'),(5,4,'2018-03-29','echo create 001'),(7,15,'2018-03-29','test re');
/*!40000 ALTER TABLE `weibo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `fullinfo`
--

/*!50001 DROP TABLE IF EXISTS `fullinfo`*/;
/*!50001 DROP VIEW IF EXISTS `fullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `fullinfo` AS select `a`.`id` AS `a_id`,`a`.`name` AS `name`,`a`.`sex` AS `sex`,`a`.`password` AS `password`,`a`.`lastlogin` AS `lastlogin`,`w`.`id` AS `w_id`,`w`.`create_time` AS `create_time`,`w`.`content` AS `content`,`l`.`id` AS `l_id`,`l`.`link` AS `link` from ((`account` `a` left join `weibo` `w` on(`a`.`id` = `w`.`a_id`)) left join `link` `l` on(`w`.`id` = `l`.`w_id`)) */;
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

-- Dump completed on 2018-04-03  9:22:10
