-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: springbootc4z801c7
-- ------------------------------------------------------
-- Server version	5.7.31

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
-- Current Database: `springbootc4z801c7`
--

/*!40000 DROP DATABASE IF EXISTS `springbootc4z801c7`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `springbootc4z801c7` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `springbootc4z801c7`;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `address` varchar(200) NOT NULL COMMENT '地址',
  `name` varchar(200) NOT NULL COMMENT '收货人',
  `phone` varchar(200) NOT NULL COMMENT '电话',
  `isdefault` varchar(200) NOT NULL COMMENT '是否默认地址[是/否]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1699512167097 DEFAULT CHARSET=utf8 COMMENT='地址';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'2030-11-09 06:38:10',11,'宇宙银河系金星1号','金某','13823888881','是'),(2,'2030-11-09 06:38:10',12,'宇宙银河系木星1号','木某','13823888882','是'),(3,'2030-11-09 06:38:10',13,'宇宙银河系水星1号','水某','13823888883','是'),(4,'2030-11-09 06:38:10',14,'宇宙银河系火星1号','火某','13823888884','是'),(5,'2030-11-09 06:38:10',15,'宇宙银河系土星1号','土某','13823888885','是'),(6,'2030-11-09 06:38:10',16,'宇宙银河系月球1号','月某','13823888886','是'),(7,'2030-11-09 06:38:10',17,'宇宙银河系黑洞1号','黑某','13823888887','是'),(8,'2030-11-09 06:38:10',18,'宇宙银河系地球1号','地某','13823888888','是'),(1699512167096,'2030-11-09 06:42:46',1699512063431,'AAx小区','11','15111111111','是');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caizhaijihua`
--

DROP TABLE IF EXISTS `caizhaijihua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caizhaijihua` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `chayemingcheng` varchar(200) NOT NULL COMMENT '茶叶名称',
  `caizhaishuliang` int(11) DEFAULT NULL COMMENT '采摘数量',
  `jihuazhuangtai` varchar(200) DEFAULT NULL COMMENT '计划状态',
  `caizhairiqi` date DEFAULT NULL COMMENT '采摘日期',
  `jihuashuoming` longtext COMMENT '计划说明',
  `zhuguanzhanghao` varchar(200) DEFAULT NULL COMMENT '主管账号',
  `zhuguanxingming` varchar(200) DEFAULT NULL COMMENT '主管姓名',
  `gonghao` varchar(200) DEFAULT NULL COMMENT '工号',
  `xingming` varchar(200) DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1699512501653 DEFAULT CHARSET=utf8 COMMENT='采摘计划';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caizhaijihua`
--

LOCK TABLES `caizhaijihua` WRITE;
/*!40000 ALTER TABLE `caizhaijihua` DISABLE KEYS */;
INSERT INTO `caizhaijihua` VALUES (111,'2030-11-09 06:38:10','茶叶名称1',1,'已执行','2030-11-09','计划说明1','主管账号1','主管姓名1','工号1','姓名1'),(112,'2030-11-09 06:38:10','茶叶名称2',2,'已执行','2030-11-09','计划说明2','主管账号2','主管姓名2','工号2','姓名2'),(113,'2030-11-09 06:38:10','茶叶名称3',3,'已执行','2030-11-09','计划说明3','主管账号3','主管姓名3','工号3','姓名3'),(114,'2030-11-09 06:38:10','茶叶名称4',4,'已执行','2030-11-09','计划说明4','主管账号4','主管姓名4','工号4','姓名4'),(115,'2030-11-09 06:38:10','茶叶名称5',5,'已执行','2030-11-09','计划说明5','主管账号5','主管姓名5','工号5','姓名5'),(116,'2030-11-09 06:38:10','茶叶名称6',6,'已执行','2030-11-09','计划说明6','主管账号6','主管姓名6','工号6','姓名6'),(117,'2030-11-09 06:38:10','茶叶名称7',7,'已执行','2030-11-09','计划说明7','主管账号7','主管姓名7','工号7','姓名7'),(118,'2030-11-09 06:38:10','茶叶名称8',8,'已执行','2030-11-09','计划说明8','主管账号8','主管姓名8','工号8','姓名8'),(1699512501652,'2030-11-09 06:48:21','黑茶',20,'已执行','2030-11-09','<p>发给33的采摘计划3</p>','22','李四','33','李四似');
/*!40000 ALTER TABLE `caizhaijihua` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caizhaixinxi`
--

DROP TABLE IF EXISTS `caizhaixinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caizhaixinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `chayemingcheng` varchar(200) NOT NULL COMMENT '茶叶名称',
  `caizhaishuliang` int(11) DEFAULT NULL COMMENT '采摘数量',
  `dengjishijian` date DEFAULT NULL COMMENT '登记时间',
  `caizhaishuoming` longtext COMMENT '采摘说明',
  `zhuguanzhanghao` varchar(200) DEFAULT NULL COMMENT '主管账号',
  `zhuguanxingming` varchar(200) DEFAULT NULL COMMENT '主管姓名',
  `gonghao` varchar(200) DEFAULT NULL COMMENT '工号',
  `xingming` varchar(200) DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1699512535773 DEFAULT CHARSET=utf8 COMMENT='采摘信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caizhaixinxi`
--

LOCK TABLES `caizhaixinxi` WRITE;
/*!40000 ALTER TABLE `caizhaixinxi` DISABLE KEYS */;
INSERT INTO `caizhaixinxi` VALUES (121,'2030-11-09 06:38:10','茶叶名称1',1,'2030-11-09','采摘说明1','主管账号1','主管姓名1','工号1','姓名1'),(122,'2030-11-09 06:38:10','茶叶名称2',2,'2030-11-09','采摘说明2','主管账号2','主管姓名2','工号2','姓名2'),(123,'2030-11-09 06:38:10','茶叶名称3',3,'2030-11-09','采摘说明3','主管账号3','主管姓名3','工号3','姓名3'),(124,'2030-11-09 06:38:10','茶叶名称4',4,'2030-11-09','采摘说明4','主管账号4','主管姓名4','工号4','姓名4'),(125,'2030-11-09 06:38:10','茶叶名称5',5,'2030-11-09','采摘说明5','主管账号5','主管姓名5','工号5','姓名5'),(126,'2030-11-09 06:38:10','茶叶名称6',6,'2030-11-09','采摘说明6','主管账号6','主管姓名6','工号6','姓名6'),(127,'2030-11-09 06:38:10','茶叶名称7',7,'2030-11-09','采摘说明7','主管账号7','主管姓名7','工号7','姓名7'),(128,'2030-11-09 06:38:10','茶叶名称8',8,'2030-11-09','采摘说明8','主管账号8','主管姓名8','工号8','姓名8'),(1699512535772,'2030-11-09 06:48:55','黑茶',20,'2030-11-09','<p>输入采摘说明444</p>','22','李四','33','李四似');
/*!40000 ALTER TABLE `caizhaixinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `tablename` varchar(200) DEFAULT 'chayexinxi' COMMENT '商品表名',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `goodid` bigint(20) NOT NULL COMMENT '商品id',
  `goodname` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `picture` longtext COMMENT '图片',
  `buynumber` int(11) NOT NULL COMMENT '购买数量',
  `price` double DEFAULT NULL COMMENT '单价',
  `discountprice` double DEFAULT NULL COMMENT '会员价',
  `xiaoshouzhanghao` varchar(200) DEFAULT NULL COMMENT '商户名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1699512925393 DEFAULT CHARSET=utf8 COMMENT='购物车表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `adminid` bigint(20) DEFAULT NULL COMMENT '管理员id',
  `ask` longtext COMMENT '提问',
  `reply` longtext COMMENT '回复',
  `isreply` int(11) DEFAULT NULL COMMENT '是否回复',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1699512388696 DEFAULT CHARSET=utf8 COMMENT='在线留言';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (141,'2030-11-09 06:38:10',1,1,'提问1','回复1',1),(142,'2030-11-09 06:38:10',2,2,'提问2','回复2',2),(143,'2030-11-09 06:38:10',3,3,'提问3','回复3',3),(144,'2030-11-09 06:38:10',4,4,'提问4','回复4',4),(145,'2030-11-09 06:38:10',5,5,'提问5','回复5',5),(146,'2030-11-09 06:38:10',6,6,'提问6','回复6',6),(147,'2030-11-09 06:38:10',7,7,'提问7','回复7',7),(148,'2030-11-09 06:38:10',8,8,'提问8','回复8',8),(1699512099110,'2030-11-09 06:41:38',1699512063431,NULL,'留言AAA',NULL,0),(1699512388695,'2030-11-09 06:46:28',1699512063431,1,NULL,'管理员回复药3',NULL);
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chayexinxi`
--

DROP TABLE IF EXISTS `chayexinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chayexinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `chayemingcheng` varchar(200) NOT NULL COMMENT '茶叶名称',
  `chayefenlei` varchar(200) NOT NULL COMMENT '茶叶分类',
  `chandi` varchar(200) NOT NULL COMMENT '产地',
  `shangjiariqi` date DEFAULT NULL COMMENT '上架日期',
  `fengmian` longtext COMMENT '封面',
  `shangpinjieshao` longtext COMMENT '商品介绍',
  `onelimittimes` int(11) DEFAULT NULL COMMENT '单限',
  `alllimittimes` int(11) DEFAULT NULL COMMENT '库存',
  `xiaoshouzhanghao` varchar(200) DEFAULT NULL COMMENT '销售账号',
  `xiaoshouxingming` varchar(200) DEFAULT NULL COMMENT '销售姓名',
  `clicktime` datetime DEFAULT NULL COMMENT '最近点击时间',
  `clicknum` int(11) DEFAULT '0' COMMENT '点击次数',
  `discussnum` int(11) DEFAULT '0' COMMENT '评论数',
  `price` double NOT NULL COMMENT '价格',
  `storeupnum` int(11) DEFAULT '0' COMMENT '收藏数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1699512578519 DEFAULT CHARSET=utf8 COMMENT='茶叶信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chayexinxi`
--

LOCK TABLES `chayexinxi` WRITE;
/*!40000 ALTER TABLE `chayexinxi` DISABLE KEYS */;
INSERT INTO `chayexinxi` VALUES (131,'2030-11-09 06:38:10','茶叶名称1','茶叶分类1','产地1','2030-11-09','upload/chayexinxi_fengmian1.jpg,upload/chayexinxi_fengmian2.jpg,upload/chayexinxi_fengmian3.jpg','商品介绍1',1,99,'销售账号1','销售姓名1','2030-11-09 14:42:26',2,0,99.9,1),(132,'2030-11-09 06:38:10','茶叶名称2','茶叶分类2','产地2','2030-11-09','upload/chayexinxi_fengmian2.jpg,upload/chayexinxi_fengmian3.jpg,upload/chayexinxi_fengmian4.jpg','商品介绍2',2,99,'销售账号2','销售姓名2','2030-11-09 14:38:10',2,0,99.9,2),(133,'2030-11-09 06:38:10','茶叶名称3','茶叶分类3','产地3','2030-11-09','upload/chayexinxi_fengmian3.jpg,upload/chayexinxi_fengmian4.jpg,upload/chayexinxi_fengmian5.jpg','商品介绍3',3,99,'销售账号3','销售姓名3','2030-11-09 14:38:10',3,0,99.9,3),(134,'2030-11-09 06:38:10','茶叶名称4','茶叶分类4','产地4','2030-11-09','upload/chayexinxi_fengmian4.jpg,upload/chayexinxi_fengmian5.jpg,upload/chayexinxi_fengmian6.jpg','商品介绍4',4,99,'销售账号4','销售姓名4','2030-11-09 14:38:10',4,0,99.9,4),(135,'2030-11-09 06:38:10','茶叶名称5','茶叶分类5','产地5','2030-11-09','upload/chayexinxi_fengmian5.jpg,upload/chayexinxi_fengmian6.jpg,upload/chayexinxi_fengmian7.jpg','商品介绍5',5,99,'销售账号5','销售姓名5','2030-11-09 14:38:10',5,0,99.9,5),(136,'2030-11-09 06:38:10','茶叶名称6','茶叶分类6','产地6','2030-11-09','upload/chayexinxi_fengmian6.jpg,upload/chayexinxi_fengmian7.jpg,upload/chayexinxi_fengmian8.jpg','商品介绍6',6,99,'销售账号6','销售姓名6','2030-11-09 14:38:10',6,0,99.9,6),(137,'2030-11-09 06:38:10','茶叶名称7','茶叶分类7','产地7','2030-11-09','upload/chayexinxi_fengmian7.jpg,upload/chayexinxi_fengmian8.jpg,upload/chayexinxi_fengmian9.jpg','商品介绍7',7,99,'销售账号7','销售姓名7','2030-11-09 14:38:10',7,0,99.9,7),(138,'2030-11-09 06:38:10','茶叶名称8','茶叶分类8','产地8','2030-11-09','upload/chayexinxi_fengmian8.jpg,upload/chayexinxi_fengmian9.jpg,upload/chayexinxi_fengmian10.jpg','<p>商品介绍8</p>',8,96,'销售账号8','销售姓名8','2030-11-09 14:58:43',24,0,99.9,8),(1699512578518,'2030-11-09 06:49:38','黑茶','分类1','上海','2030-11-09','upload/1699512570755.jpg','<p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p><br></p>',2,20,'44','王五','2030-11-09 14:58:49',8,1,220,0);
/*!40000 ALTER TABLE `chayexinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chayezhongzhi`
--

DROP TABLE IF EXISTS `chayezhongzhi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chayezhongzhi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `chayemingcheng` varchar(200) DEFAULT NULL COMMENT '茶叶名称',
  `zhongzhishuliang` int(11) DEFAULT NULL COMMENT '种植数量',
  `zhongzhiriqi` date DEFAULT NULL COMMENT '种植日期',
  `zhongzhishuoming` longtext COMMENT '种植说明',
  `zhuguanzhanghao` varchar(200) DEFAULT NULL COMMENT '主管账号',
  `zhuguanxingming` varchar(200) DEFAULT NULL COMMENT '主管姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1699512433041 DEFAULT CHARSET=utf8 COMMENT='茶叶种植';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chayezhongzhi`
--

LOCK TABLES `chayezhongzhi` WRITE;
/*!40000 ALTER TABLE `chayezhongzhi` DISABLE KEYS */;
INSERT INTO `chayezhongzhi` VALUES (71,'2030-11-09 06:38:09','茶叶名称1',1,'2030-11-09','种植说明1','主管账号1','主管姓名1'),(72,'2030-11-09 06:38:09','茶叶名称2',2,'2030-11-09','种植说明2','主管账号2','主管姓名2'),(73,'2030-11-09 06:38:09','茶叶名称3',3,'2030-11-09','种植说明3','主管账号3','主管姓名3'),(74,'2030-11-09 06:38:09','茶叶名称4',4,'2030-11-09','种植说明4','主管账号4','主管姓名4'),(75,'2030-11-09 06:38:10','茶叶名称5',5,'2030-11-09','种植说明5','主管账号5','主管姓名5'),(76,'2030-11-09 06:38:10','茶叶名称6',6,'2030-11-09','种植说明6','主管账号6','主管姓名6'),(77,'2030-11-09 06:38:10','茶叶名称7',7,'2030-11-09','种植说明7','主管账号7','主管姓名7'),(78,'2030-11-09 06:38:10','茶叶名称8',8,'2030-11-09','种植说明8','主管账号8','主管姓名8'),(1699512433040,'2030-11-09 06:47:12','黑茶',20,'2030-11-09','<p>填写种植记录12</p>','22','李四');
/*!40000 ALTER TABLE `chayezhongzhi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chayuanzhuguan`
--

DROP TABLE IF EXISTS `chayuanzhuguan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chayuanzhuguan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zhuguanzhanghao` varchar(200) NOT NULL COMMENT '主管账号',
  `mima` varchar(200) NOT NULL COMMENT '密码',
  `zhuguanxingming` varchar(200) NOT NULL COMMENT '主管姓名',
  `nianling` int(11) DEFAULT NULL COMMENT '年龄',
  `xingbie` varchar(200) DEFAULT NULL COMMENT '性别',
  `shouji` varchar(200) DEFAULT NULL COMMENT '手机',
  `touxiang` longtext COMMENT '头像',
  `money` double DEFAULT '0' COMMENT '余额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `zhuguanzhanghao` (`zhuguanzhanghao`)
) ENGINE=InnoDB AUTO_INCREMENT=1699512193771 DEFAULT CHARSET=utf8 COMMENT='茶园主管';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chayuanzhuguan`
--

LOCK TABLES `chayuanzhuguan` WRITE;
/*!40000 ALTER TABLE `chayuanzhuguan` DISABLE KEYS */;
INSERT INTO `chayuanzhuguan` VALUES (21,'2030-11-09 06:38:09','主管账号1','123456','主管姓名1',1,'男','13823888881','upload/chayuanzhuguan_touxiang1.jpg',200),(22,'2030-11-09 06:38:09','主管账号2','123456','主管姓名2',2,'男','13823888882','upload/chayuanzhuguan_touxiang2.jpg',200),(23,'2030-11-09 06:38:09','主管账号3','123456','主管姓名3',3,'男','13823888883','upload/chayuanzhuguan_touxiang3.jpg',200),(24,'2030-11-09 06:38:09','主管账号4','123456','主管姓名4',4,'男','13823888884','upload/chayuanzhuguan_touxiang4.jpg',200),(25,'2030-11-09 06:38:09','主管账号5','123456','主管姓名5',5,'男','13823888885','upload/chayuanzhuguan_touxiang5.jpg',200),(26,'2030-11-09 06:38:09','主管账号6','123456','主管姓名6',6,'男','13823888886','upload/chayuanzhuguan_touxiang6.jpg',200),(27,'2030-11-09 06:38:09','主管账号7','123456','主管姓名7',7,'男','13823888887','upload/chayuanzhuguan_touxiang7.jpg',200),(28,'2030-11-09 06:38:09','主管账号8','123456','主管姓名8',8,'男','13823888888','upload/chayuanzhuguan_touxiang8.jpg',200),(1699512193770,'2030-11-09 06:43:13','22','22','李四',33,'男','15111122548','upload/1699512191617.jpg',0);
/*!40000 ALTER TABLE `chayuanzhuguan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '配置参数名称',
  `value` varchar(100) DEFAULT NULL COMMENT '配置参数值',
  `url` varchar(500) DEFAULT NULL COMMENT 'url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='配置文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES (1,'picture1','upload/1699512412907.jpg',NULL),(2,'picture2','upload/picture2.jpg',NULL),(3,'picture3','upload/1699512404584.jpg',NULL);
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discusschayexinxi`
--

DROP TABLE IF EXISTS `discusschayexinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discusschayexinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `avatarurl` longtext COMMENT '头像',
  `nickname` varchar(200) DEFAULT NULL COMMENT '用户名',
  `content` longtext NOT NULL COMMENT '评论内容',
  `reply` longtext COMMENT '回复内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1699513048932 DEFAULT CHARSET=utf8 COMMENT='茶叶信息评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discusschayexinxi`
--

LOCK TABLES `discusschayexinxi` WRITE;
/*!40000 ALTER TABLE `discusschayexinxi` DISABLE KEYS */;
INSERT INTO `discusschayexinxi` VALUES (1699513048931,'2030-11-09 06:57:28',1699512578518,1699512063431,'upload/1699512061644.jpg','11','<p>输入评价345</p>','<p>恢复评价345</p>');
/*!40000 ALTER TABLE `discusschayexinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forum`
--

DROP TABLE IF EXISTS `forum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(200) DEFAULT NULL COMMENT '帖子标题',
  `content` longtext NOT NULL COMMENT '帖子内容',
  `parentid` bigint(20) DEFAULT NULL COMMENT '父节点id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(200) DEFAULT NULL COMMENT '用户名',
  `avatarurl` longtext COMMENT '头像',
  `isdone` varchar(200) DEFAULT NULL COMMENT '状态',
  `istop` int(11) DEFAULT '0' COMMENT '是否置顶',
  `toptime` datetime DEFAULT NULL COMMENT '置顶时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1699512087024 DEFAULT CHARSET=utf8 COMMENT='交流论坛';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forum`
--

LOCK TABLES `forum` WRITE;
/*!40000 ALTER TABLE `forum` DISABLE KEYS */;
INSERT INTO `forum` VALUES (151,'2030-11-09 06:38:10','帖子标题1','帖子内容1',0,1,'用户名1','upload/forum_avatarurl1.jpg,upload/forum_avatarurl2.jpg,upload/forum_avatarurl3.jpg','开放',0,'2030-11-09 14:38:10'),(152,'2030-11-09 06:38:10','帖子标题2','帖子内容2',0,2,'用户名2','upload/forum_avatarurl2.jpg,upload/forum_avatarurl3.jpg,upload/forum_avatarurl4.jpg','开放',0,'2030-11-09 14:38:10'),(153,'2030-11-09 06:38:10','帖子标题3','帖子内容3',0,3,'用户名3','upload/forum_avatarurl3.jpg,upload/forum_avatarurl4.jpg,upload/forum_avatarurl5.jpg','开放',0,'2030-11-09 14:38:10'),(154,'2030-11-09 06:38:10','帖子标题4','帖子内容4',0,4,'用户名4','upload/forum_avatarurl4.jpg,upload/forum_avatarurl5.jpg,upload/forum_avatarurl6.jpg','开放',0,'2030-11-09 14:38:10'),(155,'2030-11-09 06:38:10','帖子标题5','帖子内容5',0,5,'用户名5','upload/forum_avatarurl5.jpg,upload/forum_avatarurl6.jpg,upload/forum_avatarurl7.jpg','开放',0,'2030-11-09 14:38:10'),(156,'2030-11-09 06:38:10','帖子标题6','帖子内容6',0,6,'用户名6','upload/forum_avatarurl6.jpg,upload/forum_avatarurl7.jpg,upload/forum_avatarurl8.jpg','开放',0,'2030-11-09 14:38:10'),(157,'2030-11-09 06:38:10','帖子标题7','帖子内容7',0,7,'用户名7','upload/forum_avatarurl7.jpg,upload/forum_avatarurl8.jpg,upload/forum_avatarurl9.jpg','开放',0,'2030-11-09 14:38:10'),(158,'2030-11-09 06:38:10','帖子标题8','帖子内容8',0,8,'用户名8','upload/forum_avatarurl8.jpg,upload/forum_avatarurl9.jpg,upload/forum_avatarurl10.jpg','开放',0,'2030-11-09 14:38:10'),(1699512087023,'2030-11-09 06:41:26','发布帖子','<p>发布帖子发布帖子发布帖子发布帖子发布帖子发布帖子发布帖子发布帖子发布帖子发布帖子发布帖子发布帖子发布帖子发布帖子</p>',0,1699512063431,'11',NULL,'开放',1,'2030-11-09 14:46:29');
/*!40000 ALTER TABLE `forum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friendlink`
--

DROP TABLE IF EXISTS `friendlink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friendlink` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `name` varchar(200) NOT NULL COMMENT '名称',
  `picture` longtext COMMENT '图片',
  `url` longtext COMMENT '链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='友情链接';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friendlink`
--

LOCK TABLES `friendlink` WRITE;
/*!40000 ALTER TABLE `friendlink` DISABLE KEYS */;
INSERT INTO `friendlink` VALUES (1,'2030-11-09 06:38:10','爱奇艺','upload/fl_aqy.png','https://www.iqiyi.com/'),(2,'2030-11-09 06:38:10','百度','upload/fl_bd.png','https://www.baidu.com/'),(3,'2030-11-09 06:38:10','京东','upload/fl_jd.png','https://www.jd.com/'),(4,'2030-11-09 06:38:10','搜狐','upload/fl_sh.png','https://www.sohu.com/'),(5,'2030-11-09 06:38:10','腾讯','upload/fl_tx.png','https://www.qq.com/'),(6,'2030-11-09 06:38:10','网易','upload/fl_wy.png','https://www.163.com/'),(7,'2030-11-09 06:38:10','新浪','upload/fl_xl.png','https://www.sina.com.cn/'),(8,'2030-11-09 06:38:10','知乎','upload/fl_zh.png','https://www.zhihu.com/');
/*!40000 ALTER TABLE `friendlink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gonggaoxinxi`
--

DROP TABLE IF EXISTS `gonggaoxinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gonggaoxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `biaoti` varchar(200) NOT NULL COMMENT '标题',
  `jianjie` longtext COMMENT '简介',
  `fabushijian` date DEFAULT NULL COMMENT '发布时间',
  `fengmian` longtext COMMENT '封面',
  `neirong` longtext COMMENT '内容',
  `clicktime` datetime DEFAULT NULL COMMENT '最近点击时间',
  `clicknum` int(11) DEFAULT '0' COMMENT '点击次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1699512273229 DEFAULT CHARSET=utf8 COMMENT='公告信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gonggaoxinxi`
--

LOCK TABLES `gonggaoxinxi` WRITE;
/*!40000 ALTER TABLE `gonggaoxinxi` DISABLE KEYS */;
INSERT INTO `gonggaoxinxi` VALUES (51,'2030-11-09 06:38:09','标题1','简介1','2030-11-09','upload/gonggaoxinxi_fengmian1.jpg,upload/gonggaoxinxi_fengmian2.jpg,upload/gonggaoxinxi_fengmian3.jpg','内容1','2030-11-09 14:42:23',3),(52,'2030-11-09 06:38:09','标题2','简介2','2030-11-09','upload/gonggaoxinxi_fengmian2.jpg,upload/gonggaoxinxi_fengmian3.jpg,upload/gonggaoxinxi_fengmian4.jpg','内容2','2030-11-09 14:38:09',2),(53,'2030-11-09 06:38:09','标题3','简介3','2030-11-09','upload/gonggaoxinxi_fengmian3.jpg,upload/gonggaoxinxi_fengmian4.jpg,upload/gonggaoxinxi_fengmian5.jpg','内容3','2030-11-09 14:38:09',3),(54,'2030-11-09 06:38:09','标题4','简介4','2030-11-09','upload/gonggaoxinxi_fengmian4.jpg,upload/gonggaoxinxi_fengmian5.jpg,upload/gonggaoxinxi_fengmian6.jpg','内容4','2030-11-09 14:38:09',4),(55,'2030-11-09 06:38:09','标题5','简介5','2030-11-09','upload/gonggaoxinxi_fengmian5.jpg,upload/gonggaoxinxi_fengmian6.jpg,upload/gonggaoxinxi_fengmian7.jpg','内容5','2030-11-09 14:38:09',5),(56,'2030-11-09 06:38:09','标题6','简介6','2030-11-09','upload/gonggaoxinxi_fengmian6.jpg,upload/gonggaoxinxi_fengmian7.jpg,upload/gonggaoxinxi_fengmian8.jpg','内容6','2030-11-09 14:38:09',6),(57,'2030-11-09 06:38:09','标题7','简介7','2030-11-09','upload/gonggaoxinxi_fengmian7.jpg,upload/gonggaoxinxi_fengmian8.jpg,upload/gonggaoxinxi_fengmian9.jpg','内容7','2030-11-09 14:38:09',7),(58,'2030-11-09 06:38:09','标题8','简介8','2030-11-09','upload/gonggaoxinxi_fengmian8.jpg,upload/gonggaoxinxi_fengmian9.jpg,upload/gonggaoxinxi_fengmian10.jpg','内容8','2030-11-09 14:42:10',9),(1699512273228,'2030-11-09 06:44:32','公告信息11','操作者可以在输入框输入   简介信息    等内容。','2030-11-09','upload/1699512264323.png','<p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p><br></p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p><br></p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p><img src=\"http://localhost:8080/springbootc4z801c7/upload/1699512271334.png\"></p>','2030-11-09 14:50:15',1);
/*!40000 ALTER TABLE `gonggaoxinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jiagongyuangong`
--

DROP TABLE IF EXISTS `jiagongyuangong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiagongyuangong` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gonghao` varchar(200) NOT NULL COMMENT '工号',
  `mima` varchar(200) NOT NULL COMMENT '密码',
  `xingming` varchar(200) NOT NULL COMMENT '姓名',
  `xingbie` varchar(200) DEFAULT NULL COMMENT '性别',
  `nianling` varchar(200) DEFAULT NULL COMMENT '年龄',
  `shouji` varchar(200) DEFAULT NULL COMMENT '手机',
  `touxiang` longtext COMMENT '头像',
  `money` double DEFAULT '0' COMMENT '余额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `gonghao` (`gonghao`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='加工员工';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jiagongyuangong`
--

LOCK TABLES `jiagongyuangong` WRITE;
/*!40000 ALTER TABLE `jiagongyuangong` DISABLE KEYS */;
INSERT INTO `jiagongyuangong` VALUES (31,'2030-11-09 06:38:09','工号1','123456','姓名1','男','年龄1','13823888881','upload/jiagongyuangong_touxiang1.jpg',200),(32,'2030-11-09 06:38:09','工号2','123456','姓名2','男','年龄2','13823888882','upload/jiagongyuangong_touxiang2.jpg',200),(33,'2030-11-09 06:38:09','工号3','123456','姓名3','男','年龄3','13823888883','upload/jiagongyuangong_touxiang3.jpg',200),(34,'2030-11-09 06:38:09','工号4','123456','姓名4','男','年龄4','13823888884','upload/jiagongyuangong_touxiang4.jpg',200),(35,'2030-11-09 06:38:09','工号5','123456','姓名5','男','年龄5','13823888885','upload/jiagongyuangong_touxiang5.jpg',200),(36,'2030-11-09 06:38:09','工号6','123456','姓名6','男','年龄6','13823888886','upload/jiagongyuangong_touxiang6.jpg',200),(37,'2030-11-09 06:38:09','工号7','123456','姓名7','男','年龄7','13823888887','upload/jiagongyuangong_touxiang7.jpg',200),(38,'2030-11-09 06:38:09','33','33','李四似','男','年龄8','13823888888','upload/jiagongyuangong_touxiang8.jpg',200);
/*!40000 ALTER TABLE `jiagongyuangong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kehu`
--

DROP TABLE IF EXISTS `kehu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kehu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zhanghao` varchar(200) NOT NULL COMMENT '账号',
  `mima` varchar(200) NOT NULL COMMENT '密码',
  `xingming` varchar(200) NOT NULL COMMENT '姓名',
  `nianling` int(11) DEFAULT NULL COMMENT '年龄',
  `xingbie` varchar(200) DEFAULT NULL COMMENT '性别',
  `shouji` varchar(200) DEFAULT NULL COMMENT '手机',
  `touxiang` longtext COMMENT '头像',
  `money` double DEFAULT '0' COMMENT '余额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `zhanghao` (`zhanghao`)
) ENGINE=InnoDB AUTO_INCREMENT=1699512063432 DEFAULT CHARSET=utf8 COMMENT='客户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kehu`
--

LOCK TABLES `kehu` WRITE;
/*!40000 ALTER TABLE `kehu` DISABLE KEYS */;
INSERT INTO `kehu` VALUES (11,'2030-11-09 06:38:09','账号1','123456','姓名1',1,'男','13823888881','upload/kehu_touxiang1.jpg',200),(12,'2030-11-09 06:38:09','账号2','123456','姓名2',2,'男','13823888882','upload/kehu_touxiang2.jpg',200),(13,'2030-11-09 06:38:09','账号3','123456','姓名3',3,'男','13823888883','upload/kehu_touxiang3.jpg',200),(14,'2030-11-09 06:38:09','账号4','123456','姓名4',4,'男','13823888884','upload/kehu_touxiang4.jpg',200),(15,'2030-11-09 06:38:09','账号5','123456','姓名5',5,'男','13823888885','upload/kehu_touxiang5.jpg',200),(16,'2030-11-09 06:38:09','账号6','123456','姓名6',6,'男','13823888886','upload/kehu_touxiang6.jpg',200),(1699512063431,'2030-11-09 06:41:03','11','11','张三',22,'女','15111111111','upload/1699512061644.jpg',10081.6);
/*!40000 ALTER TABLE `kehu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `orderid` varchar(200) NOT NULL COMMENT '订单编号',
  `tablename` varchar(200) DEFAULT 'chayexinxi' COMMENT '商品表名',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `goodid` bigint(20) NOT NULL COMMENT '商品id',
  `goodname` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `picture` longtext COMMENT '商品图片',
  `buynumber` int(11) NOT NULL COMMENT '购买数量',
  `price` double NOT NULL DEFAULT '0' COMMENT '价格',
  `discountprice` double DEFAULT '0' COMMENT '折扣价格',
  `total` double NOT NULL DEFAULT '0' COMMENT '总价格',
  `discounttotal` double DEFAULT '0' COMMENT '折扣总价格',
  `type` int(11) DEFAULT '1' COMMENT '支付类型',
  `status` varchar(200) DEFAULT NULL COMMENT '状态',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `tel` varchar(200) DEFAULT NULL COMMENT '电话',
  `consignee` varchar(200) DEFAULT NULL COMMENT '收货人',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `logistics` longtext COMMENT '物流',
  `xiaoshouzhanghao` varchar(200) DEFAULT NULL COMMENT '商户名称',
  `sfsh` varchar(200) DEFAULT '' COMMENT '是否审核',
  `shhf` longtext COMMENT '审核回复',
  `role` varchar(200) DEFAULT NULL COMMENT '用户角色',
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderid` (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=1699512936278 DEFAULT CHARSET=utf8 COMMENT='订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1699512936277,'2030-11-09 06:55:36','2023119145551373','chayexinxi',1699512063431,1699512578518,'黑茶','upload/1699512570755.jpg',2,220,220,440,440,1,'已完成','AAx小区','15111111111','11','备注33','<p>填写物流信息3334</p>','44','',NULL,'kehu');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storeup`
--

DROP TABLE IF EXISTS `storeup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storeup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `refid` bigint(20) DEFAULT NULL COMMENT '商品id',
  `tablename` varchar(200) DEFAULT NULL COMMENT '表名',
  `name` varchar(200) NOT NULL COMMENT '名称',
  `picture` longtext NOT NULL COMMENT '图片',
  `type` varchar(200) DEFAULT '1' COMMENT '类型(1:收藏,21:赞,22:踩,31:竞拍参与,41:关注)',
  `inteltype` varchar(200) DEFAULT NULL COMMENT '推荐类型',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1699512125655 DEFAULT CHARSET=utf8 COMMENT='收藏表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storeup`
--

LOCK TABLES `storeup` WRITE;
/*!40000 ALTER TABLE `storeup` DISABLE KEYS */;
/*!40000 ALTER TABLE `storeup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `tablename` varchar(100) DEFAULT NULL COMMENT '表名',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `token` varchar(200) NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='token表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,11,'账号1','kehu','客户','zzj9rpq09jy5lfzoi1v1ttuggy4viyme','2030-11-09 06:40:31','2030-11-09 07:40:31'),(2,1699512063431,'11','kehu','客户','0gje81hgvsnhg62jngvmnie3jtxunhsd','2030-11-09 06:41:06','2030-11-09 07:58:39'),(3,1699512193770,'22','chayuanzhuguan','茶园主管','hexqjtrpagafv85hoaax78bkxbzvy8lf','2030-11-09 06:43:17','2030-11-09 07:47:01'),(4,1,'admin','users','管理员','zy2ym2hiprx0v0ur1pmhd6thmmb4zt9l','2030-11-09 06:43:30','2030-11-09 07:58:06'),(5,38,'33','jiagongyuangong','加工员工','58q57cvc8hkttih0jbtqvuwr31ylc6jt','2030-11-09 06:48:44','2030-11-09 07:48:44'),(6,48,'44','xiaoshourenyuan','销售人员','g78y0yg2ma729k15xb2kj3ouz5q5lwun','2030-11-09 06:49:09','2030-11-09 07:57:50');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `image` varchar(200) DEFAULT NULL COMMENT '头像',
  `role` varchar(100) DEFAULT '管理员' COMMENT '角色',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin','upload/image1.jpg','管理员','2030-11-09 06:38:10');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wuzichuku`
--

DROP TABLE IF EXISTS `wuzichuku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wuzichuku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `chukubianhao` varchar(200) DEFAULT NULL COMMENT '出库编号',
  `wuzibianhao` varchar(200) NOT NULL COMMENT '物资编号',
  `wuzimingcheng` varchar(200) NOT NULL COMMENT '物资名称',
  `shuliang` int(11) NOT NULL COMMENT '数量',
  `chukuriqi` date DEFAULT NULL COMMENT '出库日期',
  `beizhu` varchar(200) DEFAULT NULL COMMENT '备注',
  `zhuguanzhanghao` varchar(200) DEFAULT NULL COMMENT '主管账号',
  `zhuguanxingming` varchar(200) DEFAULT NULL COMMENT '主管姓名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `chukubianhao` (`chukubianhao`)
) ENGINE=InnoDB AUTO_INCREMENT=1699512453214 DEFAULT CHARSET=utf8 COMMENT='物资出库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wuzichuku`
--

LOCK TABLES `wuzichuku` WRITE;
/*!40000 ALTER TABLE `wuzichuku` DISABLE KEYS */;
INSERT INTO `wuzichuku` VALUES (91,'2030-11-09 06:38:10','1111111111','物资编号1','物资名称1',1,'2030-11-09','备注1','主管账号1','主管姓名1'),(92,'2030-11-09 06:38:10','2222222222','物资编号2','物资名称2',2,'2030-11-09','备注2','主管账号2','主管姓名2'),(93,'2030-11-09 06:38:10','3333333333','物资编号3','物资名称3',3,'2030-11-09','备注3','主管账号3','主管姓名3'),(94,'2030-11-09 06:38:10','4444444444','物资编号4','物资名称4',4,'2030-11-09','备注4','主管账号4','主管姓名4'),(95,'2030-11-09 06:38:10','5555555555','物资编号5','物资名称5',5,'2030-11-09','备注5','主管账号5','主管姓名5'),(96,'2030-11-09 06:38:10','6666666666','物资编号6','物资名称6',6,'2030-11-09','备注6','主管账号6','主管姓名6'),(97,'2030-11-09 06:38:10','7777777777','物资编号7','物资名称7',7,'2030-11-09','备注7','主管账号7','主管姓名7'),(98,'2030-11-09 06:38:10','8888888888','物资编号8','物资名称8',8,'2030-11-09','备注8','主管账号8','主管姓名8'),(1699512453213,'2030-11-09 06:47:32','1699512461734','1699512355469','第一物资',15,'2030-11-09','种植需要34','22','李四');
/*!40000 ALTER TABLE `wuzichuku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wuzikucun`
--

DROP TABLE IF EXISTS `wuzikucun`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wuzikucun` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `wuzibianhao` varchar(200) DEFAULT NULL COMMENT '物资编号',
  `wuzimingcheng` varchar(200) NOT NULL COMMENT '物资名称',
  `wuzifenlei` varchar(200) NOT NULL COMMENT '物资分类',
  `shuliang` int(11) NOT NULL COMMENT '数量',
  `dengjiriqi` date DEFAULT NULL COMMENT '登记日期',
  `wuzixiangqing` longtext COMMENT '物资详情',
  PRIMARY KEY (`id`),
  UNIQUE KEY `wuzibianhao` (`wuzibianhao`)
) ENGINE=InnoDB AUTO_INCREMENT=1699512351938 DEFAULT CHARSET=utf8 COMMENT='物资库存';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wuzikucun`
--

LOCK TABLES `wuzikucun` WRITE;
/*!40000 ALTER TABLE `wuzikucun` DISABLE KEYS */;
INSERT INTO `wuzikucun` VALUES (81,'2030-11-09 06:38:10','1111111111','物资名称1','物资分类1',1,'2030-11-09','物资详情1'),(82,'2030-11-09 06:38:10','2222222222','物资名称2','物资分类2',2,'2030-11-09','物资详情2'),(83,'2030-11-09 06:38:10','3333333333','物资名称3','物资分类3',3,'2030-11-09','物资详情3'),(84,'2030-11-09 06:38:10','4444444444','物资名称4','物资分类4',4,'2030-11-09','物资详情4'),(85,'2030-11-09 06:38:10','5555555555','物资名称5','物资分类5',5,'2030-11-09','物资详情5'),(86,'2030-11-09 06:38:10','6666666666','物资名称6','物资分类6',6,'2030-11-09','物资详情6'),(87,'2030-11-09 06:38:10','7777777777','物资名称7','物资分类7',7,'2030-11-09','物资详情7'),(88,'2030-11-09 06:38:10','8888888888','物资名称8','物资分类8',8,'2030-11-09','物资详情8'),(1699512351937,'2030-11-09 06:45:51','1699512355469','第一物资','废料',51,'2030-11-09','<p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p>操作者可以在输入框输入&nbsp;&nbsp;详情信息&nbsp;&nbsp;等内容。</p><p><br></p>');
/*!40000 ALTER TABLE `wuzikucun` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wuziruku`
--

DROP TABLE IF EXISTS `wuziruku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wuziruku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `rukubianhao` varchar(200) DEFAULT NULL COMMENT '入库编号',
  `wuzibianhao` varchar(200) NOT NULL COMMENT '物资编号',
  `wuzimingcheng` varchar(200) DEFAULT NULL COMMENT '物资名称',
  `shuliang` int(11) NOT NULL COMMENT '数量',
  `beizhu` varchar(200) DEFAULT NULL COMMENT '备注',
  `rukuriqi` date DEFAULT NULL COMMENT '入库日期',
  `zhuguanzhanghao` varchar(200) DEFAULT NULL COMMENT '主管账号',
  `zhuguanxingming` varchar(200) DEFAULT NULL COMMENT '主管姓名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `rukubianhao` (`rukubianhao`)
) ENGINE=InnoDB AUTO_INCREMENT=1699512462102 DEFAULT CHARSET=utf8 COMMENT='物资入库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wuziruku`
--

LOCK TABLES `wuziruku` WRITE;
/*!40000 ALTER TABLE `wuziruku` DISABLE KEYS */;
INSERT INTO `wuziruku` VALUES (101,'2030-11-09 06:38:10','1111111111','物资编号1','物资名称1',1,'备注1','2030-11-09','主管账号1','主管姓名1'),(102,'2030-11-09 06:38:10','2222222222','物资编号2','物资名称2',2,'备注2','2030-11-09','主管账号2','主管姓名2'),(103,'2030-11-09 06:38:10','3333333333','物资编号3','物资名称3',3,'备注3','2030-11-09','主管账号3','主管姓名3'),(104,'2030-11-09 06:38:10','4444444444','物资编号4','物资名称4',4,'备注4','2030-11-09','主管账号4','主管姓名4'),(105,'2030-11-09 06:38:10','5555555555','物资编号5','物资名称5',5,'备注5','2030-11-09','主管账号5','主管姓名5'),(106,'2030-11-09 06:38:10','6666666666','物资编号6','物资名称6',6,'备注6','2030-11-09','主管账号6','主管姓名6'),(107,'2030-11-09 06:38:10','7777777777','物资编号7','物资名称7',7,'备注7','2030-11-09','主管账号7','主管姓名7'),(108,'2030-11-09 06:38:10','8888888888','物资编号8','物资名称8',8,'备注8','2030-11-09','主管账号8','主管姓名8'),(1699512462101,'2030-11-09 06:47:41','1699512470853','1699512355469','第一物资',33,'购入33','2030-11-09','22','李四');
/*!40000 ALTER TABLE `wuziruku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xiaoshourenyuan`
--

DROP TABLE IF EXISTS `xiaoshourenyuan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xiaoshourenyuan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `xiaoshouzhanghao` varchar(200) NOT NULL COMMENT '销售账号',
  `mima` varchar(200) NOT NULL COMMENT '密码',
  `xiaoshouxingming` varchar(200) NOT NULL COMMENT '销售姓名',
  `xingbie` varchar(200) DEFAULT NULL COMMENT '性别',
  `nianling` varchar(200) DEFAULT NULL COMMENT '年龄',
  `shouji` varchar(200) DEFAULT NULL COMMENT '手机',
  `touxiang` longtext COMMENT '头像',
  `money` double DEFAULT '0' COMMENT '余额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `xiaoshouzhanghao` (`xiaoshouzhanghao`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='销售人员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xiaoshourenyuan`
--

LOCK TABLES `xiaoshourenyuan` WRITE;
/*!40000 ALTER TABLE `xiaoshourenyuan` DISABLE KEYS */;
INSERT INTO `xiaoshourenyuan` VALUES (41,'2030-11-09 06:38:09','销售账号1','123456','销售姓名1','男','年龄1','13823888881','upload/xiaoshourenyuan_touxiang1.jpg',200),(42,'2030-11-09 06:38:09','销售账号2','123456','销售姓名2','男','年龄2','13823888882','upload/xiaoshourenyuan_touxiang2.jpg',200),(43,'2030-11-09 06:38:09','销售账号3','123456','销售姓名3','男','年龄3','13823888883','upload/xiaoshourenyuan_touxiang3.jpg',200),(44,'2030-11-09 06:38:09','销售账号4','123456','销售姓名4','男','年龄4','13823888884','upload/xiaoshourenyuan_touxiang4.jpg',200),(45,'2030-11-09 06:38:09','销售账号5','123456','销售姓名5','男','年龄5','13823888885','upload/xiaoshourenyuan_touxiang5.jpg',200),(46,'2030-11-09 06:38:09','销售账号6','123456','销售姓名6','男','年龄6','13823888886','upload/xiaoshourenyuan_touxiang6.jpg',200),(47,'2030-11-09 06:38:09','销售账号7','123456','销售姓名7','男','年龄7','13823888887','upload/xiaoshourenyuan_touxiang7.jpg',200),(48,'2030-11-09 06:38:09','销售账号8','123456','王五','女','年龄8','13823888888','upload/xiaoshourenyuan_touxiang8.jpg',200);
/*!40000 ALTER TABLE `xiaoshourenyuan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zhongzhijihua`
--

DROP TABLE IF EXISTS `zhongzhijihua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zhongzhijihua` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `chayemingcheng` varchar(200) DEFAULT NULL COMMENT '茶叶名称',
  `zhongzhishuliang` int(11) DEFAULT NULL COMMENT '种植数量',
  `zhongzhishijian` date DEFAULT NULL COMMENT '种植时间',
  `zhongzhiweizhi` varchar(200) DEFAULT NULL COMMENT '种植位置',
  `zhongzhizhuangtai` varchar(200) DEFAULT NULL COMMENT '种植状态',
  `zhongzhixiangqing` longtext COMMENT '种植详情',
  `zhuguanzhanghao` varchar(200) DEFAULT NULL COMMENT '主管账号',
  `zhuguanxingming` varchar(200) DEFAULT NULL COMMENT '主管姓名',
  `storeupnum` int(11) DEFAULT '0' COMMENT '收藏数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1699512301182 DEFAULT CHARSET=utf8 COMMENT='种植计划';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zhongzhijihua`
--

LOCK TABLES `zhongzhijihua` WRITE;
/*!40000 ALTER TABLE `zhongzhijihua` DISABLE KEYS */;
INSERT INTO `zhongzhijihua` VALUES (61,'2030-11-09 06:38:09','茶叶名称1',1,'2030-11-09','种植位置1','已种植','种植详情1','主管账号1','主管姓名1',1),(62,'2030-11-09 06:38:09','茶叶名称2',2,'2030-11-09','种植位置2','已种植','种植详情2','主管账号2','主管姓名2',2),(63,'2030-11-09 06:38:09','茶叶名称3',3,'2030-11-09','种植位置3','已种植','种植详情3','主管账号3','主管姓名3',3),(64,'2030-11-09 06:38:09','茶叶名称4',4,'2030-11-09','种植位置4','已种植','种植详情4','主管账号4','主管姓名4',4),(65,'2030-11-09 06:38:09','茶叶名称5',5,'2030-11-09','种植位置5','已种植','种植详情5','主管账号5','主管姓名5',5),(66,'2030-11-09 06:38:09','茶叶名称6',6,'2030-11-09','种植位置6','已种植','种植详情6','主管账号6','主管姓名6',6),(67,'2030-11-09 06:38:09','茶叶名称7',7,'2030-11-09','种植位置7','已种植','种植详情7','主管账号7','主管姓名7',7),(68,'2030-11-09 06:38:09','茶叶名称8',8,'2030-11-09','种植位置8','已种植','种植详情8','主管账号8','主管姓名8',8),(1699512301181,'2030-11-09 06:45:00','黑茶',20,'2030-11-09','发多少发生的','已种植','<p>发给主管的种植计划 </p>','22','李四',0);
/*!40000 ALTER TABLE `zhongzhijihua` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2030-11-28  8:01:28
