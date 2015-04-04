-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.49-1ubuntu8.1


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema hotel
--

CREATE DATABASE IF NOT EXISTS hotel;
USE hotel;

--
-- Definition of table `hotel`.`BookingRooms`
--

DROP TABLE IF EXISTS `hotel`.`BookingRooms`;
CREATE TABLE  `hotel`.`BookingRooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `BookingId` int(11) NOT NULL,
  `RoomNo` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=119 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hotel`.`BookingRooms`
--

/*!40000 ALTER TABLE `BookingRooms` DISABLE KEYS */;
LOCK TABLES `BookingRooms` WRITE;
INSERT INTO `hotel`.`BookingRooms` VALUES  (116,99,212),
 (118,101,34),
 (115,99,1),
 (117,100,213);
UNLOCK TABLES;
/*!40000 ALTER TABLE `BookingRooms` ENABLE KEYS */;


--
-- Definition of table `hotel`.`Rates`
--

DROP TABLE IF EXISTS `hotel`.`Rates`;
CREATE TABLE  `hotel`.`Rates` (
  `rateid` int(11) NOT NULL AUTO_INCREMENT,
  `rcategoryid` int(11) NOT NULL,
  `fromdate` date NOT NULL,
  `todate` date NOT NULL,
  `tax` float DEFAULT NULL,
  `rate` float NOT NULL,
  PRIMARY KEY (`rateid`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hotel`.`Rates`
--

/*!40000 ALTER TABLE `Rates` DISABLE KEYS */;
LOCK TABLES `Rates` WRITE;
INSERT INTO `hotel`.`Rates` VALUES  (1,3,'2011-01-01','2011-03-31',210,2000),
 (6,3,'2011-04-01','2011-06-30',500,3000),
 (4,1,'2011-01-01','2011-03-31',220,2000),
 (5,7,'2011-04-01','2011-06-30',500,5500),
 (7,3,'2011-07-01','2011-09-30',500,2500),
 (8,3,'2011-10-01','2011-12-31',400,3200),
 (9,1,'2011-04-01','2011-06-30',500,4000),
 (10,1,'2011-07-01','2011-09-30',600,4500),
 (11,1,'2011-10-01','2011-12-31',600,4800),
 (12,7,'2011-01-01','2011-03-31',700,5000),
 (13,7,'2011-07-01','2011-09-30',700,6000),
 (14,7,'2011-10-01','2011-12-31',600,6100),
 (15,10,'2011-01-01','2011-03-31',200,9000),
 (16,10,'2011-04-01','2011-07-01',800,8000),
 (17,10,'2011-07-01','2011-10-31',3000,9080);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Rates` ENABLE KEYS */;


--
-- Definition of table `hotel`.`RoomCategories`
--

DROP TABLE IF EXISTS `hotel`.`RoomCategories`;
CREATE TABLE  `hotel`.`RoomCategories` (
  `rcategoryid` int(11) NOT NULL AUTO_INCREMENT,
  `rcategoryname` varchar(60) DEFAULT NULL,
  `rcategorydetails` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`rcategoryid`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hotel`.`RoomCategories`
--

/*!40000 ALTER TABLE `RoomCategories` DISABLE KEYS */;
LOCK TABLES `RoomCategories` WRITE;
INSERT INTO `hotel`.`RoomCategories` VALUES  (1,'luxury','luxury details'),
 (3,'deluxe','deluxe'),
 (10,'Suite','suite');
UNLOCK TABLES;
/*!40000 ALTER TABLE `RoomCategories` ENABLE KEYS */;


--
-- Definition of table `hotel`.`Rooms`
--

DROP TABLE IF EXISTS `hotel`.`Rooms`;
CREATE TABLE  `hotel`.`Rooms` (
  `roomno` int(11) NOT NULL,
  `floorno` int(11) NOT NULL,
  `rcategoryid` int(11) NOT NULL,
  PRIMARY KEY (`roomno`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hotel`.`Rooms`
--

/*!40000 ALTER TABLE `Rooms` DISABLE KEYS */;
LOCK TABLES `Rooms` WRITE;
INSERT INTO `hotel`.`Rooms` VALUES  (1,1,1),
 (212,3,1),
 (213,3,1),
 (214,3,1),
 (34,4,3),
 (12,3,3),
 (48,4,10),
 (45,4,10),
 (46,4,10),
 (51,5,3),
 (52,5,3);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Rooms` ENABLE KEYS */;


--
-- Definition of table `hotel`.`bookings`
--

DROP TABLE IF EXISTS `hotel`.`bookings`;
CREATE TABLE  `hotel`.`bookings` (
  `bookingid` int(11) NOT NULL AUTO_INCREMENT,
  `bookingdate` date NOT NULL,
  `checkindate` date NOT NULL,
  `checkoutdate` date NOT NULL,
  `roomno` int(11) NOT NULL,
  `guestid` int(11) NOT NULL,
  `amount` float NOT NULL,
  `advance` float NOT NULL,
  `tax` float NOT NULL,
  `status` varchar(60) NOT NULL,
  PRIMARY KEY (`bookingid`)
) ENGINE=MyISAM AUTO_INCREMENT=102 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hotel`.`bookings`
--

/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
LOCK TABLES `bookings` WRITE;
INSERT INTO `hotel`.`bookings` VALUES  (55,'2011-02-01','2011-02-02','2011-02-04',1,60,4420,300,210,'paid'),
 (54,'2011-03-03','2011-03-04','2011-03-06',1,59,10800,1000,600,'Paid'),
 (53,'2011-04-16','2011-04-17','2011-04-19',1,58,7200,1000,400,'paid'),
 (57,'2011-06-17','2011-06-18','2011-06-20',1,63,7200,1500,0,'paid'),
 (42,'2011-06-16','2011-06-12','2011-06-16',2,38,8880,2000,0,'paid'),
 (52,'2011-06-09','2011-06-10','2011-06-13',1,57,20100,1000,600,'paid'),
 (51,'2011-06-16','2011-06-12','2011-06-17',2,55,43200,3000,400,'Paid'),
 (58,'2011-06-17','2011-06-17','2011-06-19',1,64,7200,3000,400,'paid'),
 (56,'2011-01-21','2011-01-22','2011-01-24',2,61,21600,1000,600,'paid'),
 (85,'2011-06-23','2011-06-23','2011-06-24',1,99,5200,300,500,'paid'),
 (65,'2011-06-23','2011-06-22','2011-06-23',1,72,4000,1000,500,'paid'),
 (84,'2011-06-23','2011-06-23','2011-06-24',1,98,2600,400,500,'paid'),
 (86,'2011-06-23','2011-06-23','2011-06-24',1,103,4000,1000,500,'paid'),
 (88,'2011-06-24','2011-06-24','2011-06-24',1,114,3800,1000,800,'paid'),
 (89,'2011-07-10','2011-07-10','2011-07-12',1,116,13500,2000,500,'paid'),
 (92,'2011-07-28','2011-07-28','2011-07-28',2,121,12000,2000,500,'paid'),
 (90,'2011-06-24','2011-06-24','2011-06-24',1,118,3000,500,500,'paid'),
 (91,'2011-07-24','2011-07-24','2011-06-26',1,120,3800,1000,800,'paid'),
 (101,'2011-07-28','2011-07-28','2011-07-30',1,130,6000,1000,500,'Not Paid'),
 (100,'2011-07-28','2011-07-28','2011-07-31',1,129,15300,2000,600,'Not Paid'),
 (99,'2011-07-28','2011-07-29','2011-07-31',2,128,20400,1000,600,'Not Paid');
UNLOCK TABLES;
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;


--
-- Definition of table `hotel`.`documents`
--

DROP TABLE IF EXISTS `hotel`.`documents`;
CREATE TABLE  `hotel`.`documents` (
  `docid` int(11) NOT NULL AUTO_INCREMENT,
  `doctitle` varchar(60) DEFAULT NULL,
  `guestid` int(11) NOT NULL,
  `file` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`docid`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hotel`.`documents`
--

/*!40000 ALTER TABLE `documents` DISABLE KEYS */;
LOCK TABLES `documents` WRITE;
INSERT INTO `hotel`.`documents` VALUES  (5,'we',94,'latest.sql'),
 (3,'title',35,'Untitled 2.odt'),
 (6,'sds',94,'jquery-1.4.2.min.js'),
 (7,'sangeeta\'sdoc',95,'_20110622_1621.sql'),
 (8,'grtrr',97,'latest.sql'),
 (9,'chinta\'s docs',99,'new.sql'),
 (10,'pan card',103,'ServletProject.xls'),
 (11,'aa',106,'New Project 20110607 0931.sql'),
 (12,'voter\'s id',114,'New Project 20110607 0931.sql'),
 (13,'voter\'s id',116,'New Project 20110607 0931.sql'),
 (14,'Abc',118,'New Project 20110607 0931.sql'),
 (15,'pan card',120,'New Project 20110607 0931.sql'),
 (16,'pan card',121,'Spare_7may.sql'),
 (17,'qq',125,'Spare_7may.sql'),
 (18,'pan card',128,'admin_20110405_1555.sql');
UNLOCK TABLES;
/*!40000 ALTER TABLE `documents` ENABLE KEYS */;


--
-- Definition of table `hotel`.`guest`
--

DROP TABLE IF EXISTS `hotel`.`guest`;
CREATE TABLE  `hotel`.`guest` (
  `guestid` int(11) NOT NULL AUTO_INCREMENT,
  `guestname` varchar(60) NOT NULL,
  `age` int(11) NOT NULL,
  `gender` varchar(60) NOT NULL,
  `primary1` varchar(60) NOT NULL,
  `mobile` varchar(60) NOT NULL,
  `bookingid` int(11) NOT NULL,
  PRIMARY KEY (`guestid`)
) ENGINE=MyISAM AUTO_INCREMENT=131 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hotel`.`guest`
--

/*!40000 ALTER TABLE `guest` DISABLE KEYS */;
LOCK TABLES `guest` WRITE;
INSERT INTO `hotel`.`guest` VALUES  (56,'neetu rai',34,'female','NO','898768686',51),
 (40,'preeti',23,'female','NO','95848484',42),
 (38,'Ritwik Sinha',25,'male','yes','787525235',42),
 (63,'Raunak Singh ',32,'male','yes','987666232',57),
 (55,'mayank srivastava',45,'male','yes','98885633',51),
 (57,'Simarjeet',23,'female','yes','98585858',52),
 (58,'Mahendra',34,'male','yes','9966773344',53),
 (59,'sunaina mishra',56,'female','yes','957574774',54),
 (60,'Meenal',45,'female','yes','975535525',55),
 (61,'Lokesh',52,'male','yes','871234567',56),
 (64,'Navjeet Rathore',57,'male','yes','9664546744',58),
 (62,'janvi jain',23,'female','NO','9713921675',56),
 (118,'Rohit',32,'male','yes','98989898',90),
 (100,'chintamani',23,'female','NO','969569595',85),
 (99,'chintaman',45,'male','yes','9463636363',85),
 (72,'Soham ',23,'male','yes','9535355353',65),
 (103,'shraddha',34,'female','yes','9713945755',86),
 (104,'shlok',45,'male','NO','969569595',86),
 (98,'s ram',45,'male','yes','977636363',84),
 (117,'dinesh',27,'female','NO','9713943866',89),
 (116,'deepali',23,'female','yes','9446464664',89),
 (115,'suneeta ',29,'female','NO','97723545354',88),
 (114,'suresh pratap',34,'male','yes','9646466464',88),
 (119,'Abc',22,'female','NO','989898',90),
 (120,'Chaitanya',23,'male','yes','9546464646',91),
 (121,'Shivam Ahuja',25,'male','yes','9713956369',92),
 (122,'Charu Mehta',27,'female','yes','97632264454',93),
 (123,'rr',3,'male','yes','53678368368',94),
 (124,'www',23,'male','yes','91546164254',95),
 (125,'qq',23,'male','yes','1111211111',96),
 (126,'qqq',12,'male','yes','8368686',97),
 (127,'qqq',12,'male','yes','37367676',98),
 (128,'karan mehta',56,'male','yes','91546164254',99),
 (129,'deepak',34,'male','yes','9222224545',100),
 (130,'nehal jain',24,'female','yes','92424242252',101);
UNLOCK TABLES;
/*!40000 ALTER TABLE `guest` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
