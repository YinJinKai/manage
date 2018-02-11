-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: manage
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `base_data`
--

DROP TABLE IF EXISTS `base_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `state_name` varchar(45) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='字典表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_data`
--

LOCK TABLES `base_data` WRITE;
/*!40000 ALTER TABLE `base_data` DISABLE KEYS */;
INSERT INTO `base_data` VALUES (1,1,'正常出勤','1'),(2,1,'缺勤','0'),(3,2,'老师审批中','1'),(6,2,'教师未通过','t1'),(7,2,'教务审批中','t2'),(8,2,'教务未通过','a1'),(9,2,'教务通过','a2'),(10,3,'签到得分','10'),(11,4,'客观题','1'),(12,4,'主观题','2'),(13,1,'请假','2');
/*!40000 ALTER TABLE `base_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` varchar(45) DEFAULT NULL,
  `update_time` varchar(45) DEFAULT NULL,
  `create_by` varchar(45) DEFAULT NULL,
  `update_by` varchar(45) DEFAULT NULL,
  `isdelete` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  `descr` varchar(100) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `class_name` varchar(45) DEFAULT NULL,
  `class_code` varchar(45) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='班级';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1708java','1','888'),(2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1708ui','2','000'),(3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1708h5','3','4444');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creat_question`
--

DROP TABLE IF EXISTS `creat_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `creat_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_name` varchar(45) DEFAULT NULL,
  `create_question_code` varchar(45) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='创建问卷表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creat_question`
--

LOCK TABLES `creat_question` WRITE;
/*!40000 ALTER TABLE `creat_question` DISABLE KEYS */;
INSERT INTO `creat_question` VALUES (26,'讲师教学反馈','cq20180130131721','666'),(30,'学生课堂反馈','cq20180130142010','666'),(31,'讲师同学反馈','cq20180130142205',NULL);
/*!40000 ALTER TABLE `creat_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creat_question_question_qa_rel`
--

DROP TABLE IF EXISTS `creat_question_question_qa_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `creat_question_question_qa_rel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_question_code` varchar(45) DEFAULT NULL,
  `question_qa_code` varchar(45) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8 COMMENT='答卷题目与哪套答卷的关联';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creat_question_question_qa_rel`
--

LOCK TABLES `creat_question_question_qa_rel` WRITE;
/*!40000 ALTER TABLE `creat_question_question_qa_rel` DISABLE KEYS */;
INSERT INTO `creat_question_question_qa_rel` VALUES (91,'cq20180130131721','tm20180130105553',3),(92,'cq20180130131721','tm20180130105644',2),(93,'cq20180130131721','tm20180130105759',1),(98,'cq20180130142010','tm20180130105553',1),(99,'cq20180130142010','tm20180130105759',2),(100,'cq20180130142010','tm20180130141020',3),(101,'cq20180130142205','tm20180130140851',1),(102,'cq20180130142205','tm20180130141020',2),(103,'cq20180130142205','tm20180130141128',3),(104,'cq20180130142010','tm20180130141102',4),(105,'cq20180130142010','tm20180130141737',5);
/*!40000 ALTER TABLE `creat_question_question_qa_rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` varchar(45) DEFAULT NULL,
  `update_time` varchar(45) DEFAULT NULL,
  `create_by` varchar(45) DEFAULT NULL,
  `update_by` varchar(45) DEFAULT NULL,
  `isdelete` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  `descr` varchar(100) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `permission_name` varchar(45) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  `pccode` varchar(45) DEFAULT NULL,
  `menu_code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='权限表(菜单表)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'考勤情况','attendance/select.shtml','0','student1'),(2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'作业',NULL,'0','student2'),(4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'发布作业',NULL,'0','teach1'),(5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'作业打分',NULL,'0','teach2'),(6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'请假管理','leave/teacherselect.shtml','0','teach3'),(7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'请假管理','leave/adminselect.shtml','0','jiao1'),(8,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'考勤管理','attendance/teachselect.shtml','0','jiao2'),(9,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'请假','leave/select.shtml','0','student4'),(10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'问卷管理','question/select.shtml','0','jiao3'),(11,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'学生答卷','stcheck/select.shtml','0','student5'),(12,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'人员录入','addUser/select.shtml','0','jiao4'),(13,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'人员信息修改','user/select.shtml','0','jiao5'),(14,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'菜单管理','role/select.shtml','0','jiao6'),(15,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'班级管理','userclass/select.shtml','0','per20180202085707');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `create_question_code` varchar(45) DEFAULT NULL,
  `titleid` int(11) DEFAULT NULL,
  `level` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='问卷学生回答表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (29,'411517453','cq20180130131721',6,'1'),(30,'5555','cq20180130131721',6,'2'),(31,'411517453','cq20180130131721',8,'3'),(32,'411517453','cq20180130142010',11,NULL),(33,'411517453','cq20180130142010',7,'2');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_da`
--

DROP TABLE IF EXISTS `question_da`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_da` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `da_name` varchar(1500) DEFAULT NULL,
  `question_qa_code` varchar(45) DEFAULT NULL,
  `witch_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COMMENT='答卷评分表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_da`
--

LOCK TABLES `question_da` WRITE;
/*!40000 ALTER TABLE `question_da` DISABLE KEYS */;
INSERT INTO `question_da` VALUES (56,'A','tm20180130105553',29),(57,'B','tm20180130105644',29),(58,'好','tm20180130105759',29),(59,'B','tm20180130105553',30),(60,'D','tm20180130105644',30),(61,'很好','tm20180130105759',30),(62,'B','tm20180130105553',31),(63,'C','tm20180130105644',31),(64,'还行把','tm20180130105759',31),(70,'A','tm20180130105553',33),(71,'还好','tm20180130105759',33),(72,'A','tm20180130141020',33),(73,'B','tm20180130141102',33),(74,'C','tm20180130141737',33);
/*!40000 ALTER TABLE `question_da` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_qa`
--

DROP TABLE IF EXISTS `question_qa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_qa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_qa_code` varchar(45) DEFAULT NULL,
  `tm` varchar(100) DEFAULT NULL,
  `A` varchar(100) DEFAULT NULL,
  `B` varchar(100) DEFAULT NULL,
  `C` varchar(100) DEFAULT NULL,
  `D` varchar(100) DEFAULT NULL,
  `daan` varchar(45) DEFAULT NULL,
  `tmtype` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='题库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_qa`
--

LOCK TABLES `question_qa` WRITE;
/*!40000 ALTER TABLE `question_qa` DISABLE KEYS */;
INSERT INTO `question_qa` VALUES (13,'tm20180130105553','在本周学习过程中，你的学习状态如何？','比较好','一般','较差','差','A','1'),(14,'tm20180130105644','在本周学习过程中，是否能跟上讲师的进度？',' 完全跟上 ','能跟上',' 差距较大 ','一点儿不会','A','1'),(16,'tm20180130105759','你认为同学们的学习氛围如何！',NULL,NULL,NULL,NULL,NULL,'2'),(17,'tm20180130140851','你对当班讲师的综合满意度。','较为满意 ','  一般 ','感觉不太好','差','A','1'),(18,'tm20180130140939','讲师与学生除了上课外，与大家的交流多吗？',' 挺多的 ','还行 ','不怎么交流 ','没有','A','1'),(19,'tm20180130141020','讲师讲课时有激情吗？','较 激情 ','一般 ','较闷 ','没有','A','1'),(20,'tm20180130141102','你认为当班讲师的班级学生管理方式？','比较严厉','还好',' 比较温柔 ','很严厉','A','1'),(21,'tm20180130141118','讲师讲解难点或辅导学生有耐心吗？',NULL,NULL,NULL,NULL,NULL,'2'),(22,'tm20180130141128','讲师讲课有意思有乐趣吗',NULL,NULL,NULL,NULL,NULL,'2'),(25,'tm20180130141737','讲师备课准备充分，明细吗？','较好 ','一般 ','较差 ','没有','A','1'),(26,'tm20180130141752','讲师上课之前是否经常告知学生今日本周讲课计划？',NULL,NULL,NULL,NULL,NULL,'2');
/*!40000 ALTER TABLE `question_qa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` varchar(45) DEFAULT NULL,
  `update_time` varchar(45) DEFAULT NULL,
  `create_by` varchar(45) DEFAULT NULL,
  `update_by` varchar(45) DEFAULT NULL,
  `isdelete` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  `descr` varchar(100) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `role_name` varchar(45) DEFAULT NULL,
  `role_code` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'学生',NULL),(2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'讲师',NULL),(3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'教务',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission_rel`
--

DROP TABLE IF EXISTS `role_permission_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission_rel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` varchar(45) DEFAULT NULL,
  `update_time` varchar(45) DEFAULT NULL,
  `create_by` varchar(45) DEFAULT NULL,
  `update_by` varchar(45) DEFAULT NULL,
  `isdelete` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  `descr` varchar(100) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `role_code` varchar(45) DEFAULT NULL,
  `menu_code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission_rel`
--

LOCK TABLES `role_permission_rel` WRITE;
/*!40000 ALTER TABLE `role_permission_rel` DISABLE KEYS */;
INSERT INTO `role_permission_rel` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','student1'),(2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','student2'),(3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','student3'),(4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2','teach1'),(5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2','teach2'),(6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2','teach3'),(7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'3','jiao1'),(8,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'3','jiao2'),(9,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','student4'),(10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'3','jiao3'),(11,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','student5'),(12,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'3','jiao4'),(13,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'3','jiao5'),(14,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'3','jiao6'),(16,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'3','per20180202085707');
/*!40000 ALTER TABLE `role_permission_rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stleave`
--

DROP TABLE IF EXISTS `stleave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stleave` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` varchar(45) DEFAULT NULL,
  `update_time` varchar(45) DEFAULT NULL,
  `create_by` varchar(45) DEFAULT NULL,
  `update_by` varchar(45) DEFAULT NULL,
  `isdelete` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  `descr` varchar(100) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `why` varchar(45) DEFAULT NULL,
  `leave_time` varchar(45) DEFAULT NULL,
  `start_time` varchar(45) DEFAULT NULL,
  `end_time` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='请假表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stleave`
--

LOCK TABLES `stleave` WRITE;
/*!40000 ALTER TABLE `stleave` DISABLE KEYS */;
INSERT INTO `stleave` VALUES (4,'2018-01-16 22:28:27',NULL,NULL,NULL,0,1,NULL,NULL,NULL,'411517453','11111',NULL,'2018-01-16 02:03','2018-01-26 02:03','a2'),(5,'2018-01-17 23:23:55',NULL,NULL,NULL,0,1,NULL,NULL,NULL,'411517453','dadad',NULL,'2018-01-17 02:03','2018-01-17 02:03','a1'),(6,'2018-01-18 09:36:57',NULL,NULL,NULL,0,1,NULL,NULL,NULL,'411517453','79878979',NULL,'2018-01-18 02:03','2018-01-18 02:03','a1'),(7,'2018-01-18 09:40:06',NULL,NULL,NULL,0,1,NULL,NULL,NULL,'411517453','4444',NULL,'2018-01-18 02:03','2018-01-18 02:03','a1'),(8,'2018-01-18 09:41:03',NULL,NULL,NULL,0,1,NULL,NULL,NULL,'11111111','oooo',NULL,'2018-01-17 02:03','2018-01-18 02:03','a2'),(9,'2018-01-18 09:41:51',NULL,NULL,NULL,0,1,NULL,NULL,NULL,'11111111','11111',NULL,'2018-01-18 02:03','2018-01-18 02:03','t1'),(10,'2018-01-18 09:44:13',NULL,NULL,NULL,0,1,NULL,NULL,NULL,'222222222','dddd',NULL,'2018-01-18 02:03','2018-01-18 02:03','a2'),(11,'2018-01-18 09:45:21',NULL,NULL,NULL,0,1,NULL,NULL,NULL,'222222222','4444',NULL,'2018-01-18 02:03','2018-01-18 02:03','a2'),(12,'2018-01-18 09:45:48',NULL,NULL,NULL,0,1,NULL,NULL,NULL,'222222222','88888',NULL,'2018-01-18 02:03','2018-01-18 02:03','a2');
/*!40000 ALTER TABLE `stleave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_check_question`
--

DROP TABLE IF EXISTS `student_check_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_check_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_question_code` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `time` varchar(100) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='学生的问卷';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_check_question`
--

LOCK TABLES `student_check_question` WRITE;
/*!40000 ALTER TABLE `student_check_question` DISABLE KEYS */;
INSERT INTO `student_check_question` VALUES (6,'cq20180130131721','周一','2018-01-17--2018-01-30','认真答题'),(7,'cq20180130142010','周一','2018-01-17--2018-01-30','认真答题'),(8,'cq20180130131721','周二','2018-01-12--2018-01-30','认真答题'),(9,'cq20180130131721','周三','2018-01-12--2018-01-30','认真答题'),(10,'cq20180130142205','周一','2018-01-17--2018-01-25','认真答题');
/*!40000 ALTER TABLE `student_check_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` varchar(45) DEFAULT NULL,
  `update_time` varchar(45) DEFAULT NULL,
  `create_by` varchar(45) DEFAULT NULL,
  `update_by` varchar(45) DEFAULT NULL,
  `isdelete` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  `descr` varchar(100) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `true_name` varchar(45) DEFAULT NULL,
  `role_code` int(20) DEFAULT NULL,
  `class_code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'411517453','123456','尹金凯',1,'1'),(2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'888','123456','杜鲜',2,'1'),(3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'666','123456','李威',3,NULL),(4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'11111111','123456','小一',1,'2'),(5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'222222222','123456','小二',1,'1'),(6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'5555','123456','大王',1,'1'),(7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'66666','123456','小王',1,'1'),(8,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1111','123456','小一',1,'1'),(9,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2222','123456','小二',1,'1'),(11,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'4444','123456','小四',2,'3'),(12,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'22223','123456','小二',1,'1'),(13,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'000','123456','老王',2,'2'),(14,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'111111','123456','大一',1,'3'),(15,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'111112','123456','大二',1,'2'),(16,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'111113','123456','大三',1,'2'),(17,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'111114','123456','大四',1,'2'),(18,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'111115','123456','大五',1,'3'),(19,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'111116','123456','大六',1,'3'),(20,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'111117','123456','大七',1,'3'),(21,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'111118','123456','大八',1,'3');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_attendance`
--

DROP TABLE IF EXISTS `work_attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `work_attendance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `one_check` varchar(45) DEFAULT NULL,
  `two_check` varchar(45) DEFAULT NULL,
  `three_check` varchar(45) DEFAULT NULL,
  `four_check` varchar(45) DEFAULT NULL,
  `five_check` varchar(45) DEFAULT NULL,
  `six_check` varchar(45) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `time` varchar(45) DEFAULT NULL,
  `create_time` varchar(45) DEFAULT NULL,
  `update_time` varchar(45) DEFAULT NULL,
  `create_by` varchar(45) DEFAULT NULL,
  `update_by` varchar(45) DEFAULT NULL,
  `isdelete` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  `descr` varchar(100) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8 COMMENT='考勤表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_attendance`
--

LOCK TABLES `work_attendance` WRITE;
/*!40000 ALTER TABLE `work_attendance` DISABLE KEYS */;
INSERT INTO `work_attendance` VALUES (65,'222222222','1','1','1','1','1','1',1,'2018-02-02',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(66,'5555','0','0','0','0','0','0',0,'2018-02-02',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(67,'66666','1','1','1','1','1','1',1,'2018-02-02',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(68,'1111','0','0','0','0','0','0',1,'2018-02-02',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(69,'2222','0','0','0','1','1','1',0,'2018-02-02',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(70,'22223','0','0','0','0','0','0',0,'2018-02-02',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(71,'888','2','2','2','2','2','2',0,'2018-02-02',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(72,'11111111','0','0','0','0','0','0',0,'2018-02-02',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(73,'111112','0','0','0','0','0','0',0,'2018-02-02',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(74,'111113','0','0','0','0','0','0',0,'2018-02-02',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(75,'111114','0','0','0','0','0','0',0,'2018-02-02',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(76,'000','0','0','0','0','0','0',0,'2018-02-02',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(77,'111111','0','0','0','0','0','0',0,'2018-02-02',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(78,'111115','0','0','0','0','0','0',0,'2018-02-02',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(79,'111116','0','0','0','0','0','0',0,'2018-02-02',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(80,'111117','0','0','0','0','0','0',0,'2018-02-02',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(81,'111118','0','0','0','0','0','0',0,'2018-02-02',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(82,'4444','0','0','0','0','0','0',0,'2018-02-02',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(83,'666','0','0','0','0','0','0',0,'2018-02-02',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(84,'411517453','2','2','2','2','2','1',10,'2018-02-02',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(85,'411517453','0','0','0','0','0','0',0,'2018-02-03',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(86,'222222222','2','2','2','2','2','2',0,'2018-02-03',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(87,'5555','0','0','0','0','0','0',0,'2018-02-03',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(88,'66666','0','0','0','0','0','0',0,'2018-02-03',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(89,'1111','0','0','0','0','0','0',0,'2018-02-03',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(90,'2222','0','0','0','0','0','0',0,'2018-02-03',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(91,'22223','0','0','0','0','0','0',0,'2018-02-03',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(92,'888','0','0','0','0','0','0',0,'2018-02-03',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(93,'11111111','1','1','1','1','1','1',1,'2018-02-03',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(94,'111112','1','1','1','1','1','1',1,'2018-02-03',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(95,'111113','0','0','0','0','0','0',0,'2018-02-03',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(96,'111114','1','1','1','1','1','1',1,'2018-02-03',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(97,'000','2','2','2','2','2','2',2,'2018-02-03',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(98,'111111','2','2','2','2','2','2',2,'2018-02-03',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(99,'111115','0','0','0','0','0','0',0,'2018-02-03',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(100,'111116','0','0','0','0','0','0',0,'2018-02-03',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(101,'111117','0','0','0','0','0','0',0,'2018-02-03',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(102,'111118','0','0','0','0','0','0',0,'2018-02-03',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(103,'4444','0','0','0','0','0','0',0,'2018-02-03',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(104,'666','0','0','0','0','0','0',0,'2018-02-03',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(105,'411517453','0','0','0','0','0','0',0,'2018-02-04',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(106,'222222222','0','0','0','0','0','0',0,'2018-02-04',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(107,'5555','0','0','0','0','0','0',0,'2018-02-04',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(108,'66666','0','0','0','0','0','0',0,'2018-02-04',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(109,'1111','0','0','0','0','0','0',0,'2018-02-04',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(110,'2222','0','0','0','0','0','0',0,'2018-02-04',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(111,'22223','2','2','2','2','2','2',0,'2018-02-04',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(112,'888','0','0','0','0','0','0',0,'2018-02-04',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(113,'11111111','2','2','2','2','2','2',0,'2018-02-04',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(114,'111112','0','0','0','0','0','0',0,'2018-02-04',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(115,'111113','0','0','0','0','0','0',0,'2018-02-04',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(116,'111114','1','1','1','1','1','1',1,'2018-02-04',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(117,'000','2','2','2','2','2','2',1,'2018-02-04',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(118,'111111','1','1','1','1','1','1',1,'2018-02-04',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(119,'111115','1','1','1','1','1','1',1,'2018-02-04',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(120,'111116','1','1','1','1','1','1',1,'2018-02-04',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(121,'111117','2','2','2','2','2','2',2,'2018-02-04',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(122,'111118','0','0','0','0','0','0',0,'2018-02-04',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(123,'4444','0','0','0','0','0','0',0,'2018-02-04',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(124,'666','0','0','0','0','0','0',0,'2018-02-04',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(125,'411517453','2','2','2','2','2','2',0,'2018-02-06',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(126,'222222222','0','0','0','0','0','0',0,'2018-02-06',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(127,'5555','0','0','0','0','0','0',0,'2018-02-06',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(128,'66666','0','0','0','0','0','0',0,'2018-02-06',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(129,'1111','0','0','0','0','0','0',0,'2018-02-06',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(130,'2222','0','0','0','0','0','0',0,'2018-02-06',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(131,'22223','0','0','0','0','0','0',0,'2018-02-06',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(132,'888','0','0','0','0','0','0',0,'2018-02-06',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(133,'11111111','0','0','0','0','0','0',0,'2018-02-06',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(134,'111112','0','0','0','0','0','0',0,'2018-02-06',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(135,'111113','0','0','0','0','0','0',0,'2018-02-06',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(136,'111114','0','0','0','0','0','0',0,'2018-02-06',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(137,'000','0','0','0','0','0','0',0,'2018-02-06',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(138,'111111','0','0','0','0','0','0',0,'2018-02-06',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(139,'111115','0','0','0','0','0','0',0,'2018-02-06',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(140,'111116','0','0','0','0','0','0',0,'2018-02-06',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(141,'111117','0','0','0','0','0','0',0,'2018-02-06',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(142,'111118','0','0','0','0','0','0',0,'2018-02-06',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(143,'4444','0','0','0','0','0','0',0,'2018-02-06',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1),(144,'666','0','0','0','0','0','0',0,'2018-02-06',NULL,NULL,NULL,NULL,0,1,NULL,NULL,1);
/*!40000 ALTER TABLE `work_attendance` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-11 11:20:09
