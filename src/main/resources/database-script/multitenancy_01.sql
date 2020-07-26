/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.19 : Database - multitenancy_tenant_01
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`multitenancy_tenant_01` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `multitenancy_tenant_01`;

/*Table structure for table `tenant_user` */

DROP TABLE IF EXISTS `tenant_user`;

CREATE TABLE `tenant_user` (
  `id` varchar(255) NOT NULL,
  `description` varchar(512) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  `note` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `tenant_user` */

insert  into `tenant_user`(`id`,`description`,`name`,`note`) values ('19458a72-97d5-42e9-a9ce-988eed682956','description','tenant_01_user_01','note');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
