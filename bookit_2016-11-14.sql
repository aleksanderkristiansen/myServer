# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.31)
# Database: bookit
# Generation Time: 2016-11-14 08:21:48 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table author
# ------------------------------------------------------------

DROP TABLE IF EXISTS `author`;

CREATE TABLE `author` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `author_name` varchar(255) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;

INSERT INTO `author` (`id`, `author_name`, `created`, `deleted`, `updated`)
VALUES
	(1,'Aleksander','2016-11-10 17:47:31',0,'2016-11-10 17:47:31'),
	(2,'Peter','2016-11-10 17:48:13',0,'2016-11-10 17:48:13');

/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table author_book
# ------------------------------------------------------------

DROP TABLE IF EXISTS `author_book`;

CREATE TABLE `author_book` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `book_id` int(11) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `author_book` WRITE;
/*!40000 ALTER TABLE `author_book` DISABLE KEYS */;

INSERT INTO `author_book` (`id`, `book_id`, `author_id`, `created`, `deleted`, `updated`)
VALUES
	(1,1,1,'2016-11-10 17:48:04',0,'2016-11-10 17:48:04'),
	(2,1,2,'2016-11-10 17:48:20',0,'2016-11-10 17:48:20');

/*!40000 ALTER TABLE `author_book` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table book
# ------------------------------------------------------------

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `isbn` double DEFAULT NULL,
  `book_price_id` int(11) DEFAULT NULL,
  `publisher_id` int(11) DEFAULT NULL,
  `author_book_id` int(11) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;

INSERT INTO `book` (`id`, `title`, `version`, `isbn`, `book_price_id`, `publisher_id`, `author_book_id`, `created`, `deleted`, `updated`)
VALUES
	(1,'bog',1,123456789,1,1,1,'2016-11-10 17:47:13',0,'2016-11-10 17:47:13');

/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table book_curriculum
# ------------------------------------------------------------

DROP TABLE IF EXISTS `book_curriculum`;

CREATE TABLE `book_curriculum` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `book_id` int(11) DEFAULT NULL,
  `curriculum_id` int(11) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `book_curriculum` WRITE;
/*!40000 ALTER TABLE `book_curriculum` DISABLE KEYS */;

INSERT INTO `book_curriculum` (`id`, `book_id`, `curriculum_id`, `created`, `deleted`, `updated`)
VALUES
	(1,1,1,'2016-11-10 17:53:49',0,'2016-11-10 17:53:49');

/*!40000 ALTER TABLE `book_curriculum` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table book_price
# ------------------------------------------------------------

DROP TABLE IF EXISTS `book_price`;

CREATE TABLE `book_price` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `book_id` int(11) DEFAULT NULL,
  `bookstore_id` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `book_price` WRITE;
/*!40000 ALTER TABLE `book_price` DISABLE KEYS */;

INSERT INTO `book_price` (`id`, `book_id`, `bookstore_id`, `price`, `created`, `deleted`, `updated`)
VALUES
	(1,1,1,100,'2016-11-10 17:48:43',0,'2016-11-10 17:48:47'),
	(2,1,2,120,'2016-11-10 17:51:32',0,'2016-11-10 17:51:32');

/*!40000 ALTER TABLE `book_price` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bookstore
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bookstore`;

CREATE TABLE `bookstore` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `bookstore_name` varchar(255) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `bookstore` WRITE;
/*!40000 ALTER TABLE `bookstore` DISABLE KEYS */;

INSERT INTO `bookstore` (`id`, `bookstore_name`, `created`, `updated`, `deleted`)
VALUES
	(1,'SAXO','2016-11-10 17:51:45','2016-11-11 16:23:38',0),
	(2,'Amazon','2016-11-10 17:52:01','2016-11-11 16:23:39',0);

/*!40000 ALTER TABLE `bookstore` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table curriculum
# ------------------------------------------------------------

DROP TABLE IF EXISTS `curriculum`;

CREATE TABLE `curriculum` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `education_id` int(11) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `curriculum` WRITE;
/*!40000 ALTER TABLE `curriculum` DISABLE KEYS */;

INSERT INTO `curriculum` (`id`, `education_id`, `semester`, `created`, `deleted`, `updated`)
VALUES
	(1,1,1,'2016-11-10 17:26:30',0,'2016-11-10 17:26:30');

/*!40000 ALTER TABLE `curriculum` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table education
# ------------------------------------------------------------

DROP TABLE IF EXISTS `education`;

CREATE TABLE `education` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `education_name` varchar(255) DEFAULT NULL,
  `school_id` int(11) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `education` WRITE;
/*!40000 ALTER TABLE `education` DISABLE KEYS */;

INSERT INTO `education` (`id`, `education_name`, `school_id`, `created`, `deleted`, `updated`)
VALUES
	(1,'HA(it)',1,'2016-11-10 17:27:47',0,'2016-11-10 17:27:47');

/*!40000 ALTER TABLE `education` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table publisher
# ------------------------------------------------------------

DROP TABLE IF EXISTS `publisher`;

CREATE TABLE `publisher` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `publisher_name` varchar(255) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;

INSERT INTO `publisher` (`id`, `publisher_name`, `created`, `deleted`, `updated`)
VALUES
	(1,'Gyldendal','2016-11-10 17:52:27',0,'2016-11-10 17:52:27');

/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table school
# ------------------------------------------------------------

DROP TABLE IF EXISTS `school`;

CREATE TABLE `school` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `school_name` varchar(255) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `school` WRITE;
/*!40000 ALTER TABLE `school` DISABLE KEYS */;

INSERT INTO `school` (`id`, `school_name`, `created`, `deleted`, `updated`)
VALUES
	(1,'CBS','2016-11-10 17:27:27',0,'2016-11-10 17:27:27');

/*!40000 ALTER TABLE `school` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table token
# ------------------------------------------------------------

DROP TABLE IF EXISTS `token`;

CREATE TABLE `token` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `token` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;

INSERT INTO `token` (`id`, `token`, `user_id`, `created`, `deleted`)
VALUES
	(2,'2acuacmin%zc3tgtzujh62lr5',1,'2016-11-11 16:23:02',0);

/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_type` tinyint(1) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT '0',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `user_type`, `created`, `deleted`, `updated`)
VALUES
	(1,'Aleksander','Kristiansen','ark@mail.dk','123',0,'2016-11-11 15:53:19',0,'2016-11-11 15:53:47');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
