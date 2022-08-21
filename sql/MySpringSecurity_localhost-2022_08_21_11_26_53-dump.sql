-- MariaDB dump 10.19  Distrib 10.8.3-MariaDB, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: MySpringSecurity
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `b_blog`
--

DROP TABLE IF EXISTS `b_blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `b_blog` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '用户id',
  `title` varchar(255) DEFAULT NULL COMMENT '博客标题',
  `content` text COMMENT '博客内容',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面图片路径',
  `view_count` int DEFAULT NULL COMMENT '浏览次数',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `publish_time` timestamp NULL DEFAULT NULL COMMENT '发布时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `is_top` tinyint(1) DEFAULT NULL COMMENT '是否置顶',
  `status` tinyint(1) DEFAULT NULL COMMENT '0: 未发表，1: 已发表，2: 仅个人可见',
  PRIMARY KEY (`id`),
  UNIQUE KEY `b_blog_id_uindex` (`id`),
  KEY `b_blog_user_index` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1537705748115505154 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b_blog`
--

LOCK TABLES `b_blog` WRITE;
/*!40000 ALTER TABLE `b_blog` DISABLE KEYS */;
INSERT INTO `b_blog` VALUES
(1,1,'123','321','123',11,'2022-06-11 13:19:44','2022-06-11 13:19:46','2022-06-11 13:19:47',0,0),
(2,1,'123456','safdsd爱的地方','321',22,'2022-06-11 13:20:04','2022-06-11 13:20:05','2022-06-11 13:20:06',1,1),
(3,1525017379245969409,'541435243','asdasd大师阿凡达说法都是发','321',333,'2022-06-11 13:23:43','2022-06-11 13:23:43','2022-06-11 13:23:44',0,2),
(1537705748115505153,1,'test66666','内容66666','https://......',0,'2022-06-17 07:56:34',NULL,'2022-06-17 08:01:16',NULL,0);
/*!40000 ALTER TABLE `b_blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `b_login_log`
--

DROP TABLE IF EXISTS `b_login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `b_login_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '用户id',
  `ip` varchar(20) DEFAULT NULL,
  `login_time` timestamp NOT NULL,
  `type` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `blog_login_log_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1539521286768709635 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b_login_log`
--

LOCK TABLES `b_login_log` WRITE;
/*!40000 ALTER TABLE `b_login_log` DISABLE KEYS */;
INSERT INTO `b_login_log` VALUES
(1527651847223922689,1,'127.0.0.1','2022-05-20 14:05:57',0),
(1527652034520596481,1,'127.0.0.1','2022-05-20 14:06:24',0),
(1527652292868706306,1,'127.0.0.1','2022-05-20 14:07:44',0),
(1527652344383148034,1,'127.0.0.1','2022-05-20 14:07:56',0),
(1527654119697813506,1,'127.0.0.1','2022-05-20 14:14:59',0),
(1527654125234294786,1,'127.0.0.1','2022-05-20 14:15:01',0),
(1527654133232832513,1,'127.0.0.1','2022-05-20 14:15:02',0),
(1527833567227293698,1,'127.0.0.1','2022-05-21 02:08:02',NULL),
(1527833808974417921,1,'127.0.0.1','2022-05-21 02:09:00',NULL),
(1527842072365744129,1,'127.0.0.1','2022-05-21 02:41:36',NULL),
(1527842099569999873,1,'127.0.0.1','2022-05-21 02:41:53',NULL),
(1535516437018341378,1,'127.0.0.1','2022-06-11 06:57:01',NULL),
(1535516538105262082,1,'127.0.0.1','2022-06-11 06:57:26',NULL),
(1535517040918425601,1,'127.0.0.1','2022-06-11 06:59:25',NULL),
(1535517988906627073,1,'127.0.0.1','2022-06-11 07:03:12',NULL),
(1535584954619109378,1,'127.0.0.1','2022-06-11 11:29:16',NULL),
(1535586989028515842,1,'127.0.0.1','2022-06-11 11:37:22',NULL),
(1535602339631702017,1,'127.0.0.1','2022-06-11 12:38:21',NULL),
(1535605000577851393,1,'127.0.0.1','2022-06-11 12:48:56',NULL),
(1535607844785725442,1,'127.0.0.1','2022-06-11 13:00:15',NULL),
(1537100993966129153,1,'127.0.0.1','2022-06-15 15:53:29',NULL),
(1537342668969877506,1,'127.0.0.1','2022-06-16 07:53:49',NULL),
(1537705036040110081,1,'127.0.0.1','2022-06-17 07:53:44',NULL),
(1538070349915119618,1,'127.0.0.1','2022-06-18 08:05:21',NULL),
(1538534903824642050,1,'127.0.0.1','2022-06-19 14:51:20',NULL),
(1539521286768709634,1,'127.0.0.1','2022-06-22 08:10:52',NULL);
/*!40000 ALTER TABLE `b_login_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `b_permission`
--

DROP TABLE IF EXISTS `b_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `b_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '权限名称',
  `value` varchar(50) NOT NULL COMMENT '权限值',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标url',
  `uri` varchar(50) NOT NULL COMMENT '权限的路径',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '是否启用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '项创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_permission_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b_permission`
--

LOCK TABLES `b_permission` WRITE;
/*!40000 ALTER TABLE `b_permission` DISABLE KEYS */;
INSERT INTO `b_permission` VALUES
(1,'测试hello','sys:hello:test',NULL,'/hello/test',1,'2022-05-05 21:53:23');
/*!40000 ALTER TABLE `b_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `b_role`
--

DROP TABLE IF EXISTS `b_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `b_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_role_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b_role`
--

LOCK TABLES `b_role` WRITE;
/*!40000 ALTER TABLE `b_role` DISABLE KEYS */;
INSERT INTO `b_role` VALUES
(1,'user','普通用户','2022-05-05 17:33:22');
/*!40000 ALTER TABLE `b_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `b_role_permission_relate`
--

DROP TABLE IF EXISTS `b_role_permission_relate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `b_role_permission_relate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_role_permission_relate_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b_role_permission_relate`
--

LOCK TABLES `b_role_permission_relate` WRITE;
/*!40000 ALTER TABLE `b_role_permission_relate` DISABLE KEYS */;
INSERT INTO `b_role_permission_relate` VALUES
(1,1,1);
/*!40000 ALTER TABLE `b_role_permission_relate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `b_tag`
--

DROP TABLE IF EXISTS `b_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `b_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `blog_id` bigint NOT NULL COMMENT '博客id',
  `tag` varchar(10) NOT NULL COMMENT '博客tag',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `b_tag_id_uindex` (`id`),
  KEY `b_tag_blogId_index` (`blog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1537706931131633667 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b_tag`
--

LOCK TABLES `b_tag` WRITE;
/*!40000 ALTER TABLE `b_tag` DISABLE KEYS */;
INSERT INTO `b_tag` VALUES
(1,1,'Java','2022-06-15 14:34:08','2022-06-15 14:34:10'),
(2,1,'PHP','2022-06-15 14:34:19','2022-06-15 14:34:20'),
(3,2,'Python','2022-06-15 15:08:41','2022-06-15 15:08:42'),
(4,2,'Go','2022-06-15 15:08:47','2022-06-15 15:08:48'),
(1537706931119050754,1537705748115505153,'GO','2022-06-17 08:01:16','2022-06-17 08:01:16'),
(1537706931131633665,1537705748115505153,'PHP','2022-06-17 08:01:16','2022-06-17 08:01:16'),
(1537706931131633666,1537705748115505153,'Java','2022-06-17 08:01:16','2022-06-17 08:01:16');
/*!40000 ALTER TABLE `b_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `b_user`
--

DROP TABLE IF EXISTS `b_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `b_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `nick_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '昵称',
  `password` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `status` tinyint DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phone_number` varchar(32) DEFAULT NULL COMMENT '手机号',
  `sex` char(1) DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  `avatar` varchar(128) DEFAULT NULL COMMENT '头像',
  `user_type` char(1) NOT NULL DEFAULT '1' COMMENT '用户类型（0管理员，1普通用户）',
  `create_by` bigint DEFAULT NULL COMMENT '创建人的用户id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_user_index` (`id`),
  UNIQUE KEY `sys_user_username` (`user_name`),
  UNIQUE KEY `sys_user_phone` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=1525305144571371523 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b_user`
--

LOCK TABLES `b_user` WRITE;
/*!40000 ALTER TABLE `b_user` DISABLE KEYS */;
INSERT INTO `b_user` VALUES
(1,'doper','dopaminer','$2a$10$ws3EaoGLIzuBR0uAAvHq8OgCQAVs4UQLh5H8q1e8i9oGeNqBusvOS',0,'888888888@qq.com','11111111111','1',NULL,'1',NULL,'2022-05-02 22:31:53',NULL,NULL,0),
(1525017379245969409,'doper2','NULL','$2a$10$3NtBE25F9tr54cKxMWEQ.eZinrOikBl.1iAvFIsZREShb.lQRMU/m',0,'doper@163.com','11111111112',NULL,NULL,'1',NULL,'2022-05-13 07:37:31',NULL,'2022-05-13 07:37:31',0);
/*!40000 ALTER TABLE `b_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `b_user_role_relate`
--

DROP TABLE IF EXISTS `b_user_role_relate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `b_user_role_relate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_user_role_relate_id_uindex` (`id`),
  UNIQUE KEY `user_role_index` (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b_user_role_relate`
--

LOCK TABLES `b_user_role_relate` WRITE;
/*!40000 ALTER TABLE `b_user_role_relate` DISABLE KEYS */;
INSERT INTO `b_user_role_relate` VALUES
(1,1,1);
/*!40000 ALTER TABLE `b_user_role_relate` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-21 11:26:53
