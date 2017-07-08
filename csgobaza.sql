/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.7.14 : Database - csgobaza
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`csgobaza` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `csgobaza`;

/*Table structure for table `dostignuca` */

DROP TABLE IF EXISTS `dostignuca`;

CREATE TABLE `dostignuca` (
  `idDostignuca` int(11) NOT NULL AUTO_INCREMENT,
  `idTima` int(11) NOT NULL,
  `mesto` varchar(100) DEFAULT NULL,
  `nagrada` double DEFAULT '0',
  `pozicija` varchar(20) DEFAULT NULL,
  `idTurnira` int(11) NOT NULL,
  PRIMARY KEY (`idDostignuca`,`idTima`),
  KEY `fk_idTimaDostignuca` (`idTima`),
  KEY `fk_idTurniraDostignuca` (`idTurnira`),
  CONSTRAINT `fk_idTimaDostignuca` FOREIGN KEY (`idTima`) REFERENCES `tim` (`idTima`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_idTurniraDostignuca` FOREIGN KEY (`idTurnira`) REFERENCES `turnir` (`idTurnira`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `dostignuca` */

insert  into `dostignuca`(`idDostignuca`,`idTima`,`mesto`,`nagrada`,`pozicija`,`idTurnira`) values (1,1,'400',0,'1st - 4th',8),(2,1,'463',400,'2nd',7),(3,1,'480',8450,'1st',5),(4,2,'22',30000,'7 - 8th',10),(5,2,'22',10000,'3 - 4th',7),(6,2,'21',150000,'2nd',10),(7,3,'12',35000,'1st',15),(8,3,'12',300000,'2st',9),(9,3,'11',40000,'3rd',11),(10,5,'1',10000,'1st',12),(11,5,'1',400000,'1st',9),(12,5,'1',18000,'1st',6),(13,4,'8',150000,'3rd',9),(14,4,'8',12500,'1st',4),(15,4,'7',30000,'1st',14);

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `idKorisnika` int(11) NOT NULL AUTO_INCREMENT,
  `imeKorisnika` varchar(50) DEFAULT NULL,
  `prezimeKorisnika` varchar(50) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`idKorisnika`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `korisnik` */

insert  into `korisnik`(`idKorisnika`,`imeKorisnika`,`prezimeKorisnika`,`username`,`password`) values (1,'Filip','Ivanovic','filip','filip94'),(2,'Ilija','Ivanovic','ilija','ilija91'),(3,'Milos','Srejic','sreja','sreja94');

/*Table structure for table `lokacija` */

DROP TABLE IF EXISTS `lokacija`;

CREATE TABLE `lokacija` (
  `idLokacije` int(11) NOT NULL AUTO_INCREMENT,
  `idRegiona` int(11) NOT NULL,
  `nazivLokacije` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idLokacije`,`idRegiona`),
  KEY `fk_idRegionaL` (`idRegiona`),
  CONSTRAINT `fk_idRegionaL` FOREIGN KEY (`idRegiona`) REFERENCES `region` (`idRegiona`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `lokacija` */

insert  into `lokacija`(`idLokacije`,`idRegiona`,`nazivLokacije`) values (1,2,'Jönköping'),(2,1,'Denver'),(3,1,'Atlanta'),(4,2,'Valencia'),(5,2,'Bucharest'),(6,7,'Sangaj'),(7,2,'Leicester'),(8,5,'Johannesburg'),(9,3,'Mumbai'),(10,8,'Moskva'),(11,1,'Los Angeles'),(12,2,'Copenhagen'),(13,2,'Duisburg'),(14,4,'Sydney'),(15,7,'Shijiazhuang'),(16,6,'São Paulo'),(17,8,'Saint Petersburg');

/*Table structure for table `region` */

DROP TABLE IF EXISTS `region`;

CREATE TABLE `region` (
  `idRegiona` int(11) NOT NULL AUTO_INCREMENT,
  `nazivRegiona` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idRegiona`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `region` */

insert  into `region`(`idRegiona`,`nazivRegiona`) values (1,'USA'),(2,'Evropa'),(3,'Indija'),(4,'Australija'),(5,'Juzna Afrika'),(6,'Juzna Amerika'),(7,'Kina'),(8,'Rusija');

/*Table structure for table `rezultat` */

DROP TABLE IF EXISTS `rezultat`;

CREATE TABLE `rezultat` (
  `idTima` int(11) NOT NULL,
  `idTurnira` int(11) NOT NULL,
  `idRezultat` int(11) NOT NULL,
  `idKorisnika` int(11) NOT NULL,
  `Rezultat` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idTima`,`idTurnira`,`idRezultat`),
  KEY `fk_idKorisnikaR` (`idKorisnika`),
  KEY `fk_idTurniraR` (`idTurnira`),
  CONSTRAINT `fk_idKorisnikaR` FOREIGN KEY (`idKorisnika`) REFERENCES `korisnik` (`idKorisnika`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_idTimaR` FOREIGN KEY (`idTima`) REFERENCES `tim` (`idTima`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_idTurniraR` FOREIGN KEY (`idTurnira`) REFERENCES `turnir` (`idTurnira`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `rezultat` */

/*Table structure for table `serija` */

DROP TABLE IF EXISTS `serija`;

CREATE TABLE `serija` (
  `idSerije` int(11) NOT NULL AUTO_INCREMENT,
  `nazivSerije` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idSerije`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `serija` */

insert  into `serija`(`idSerije`,`nazivSerije`) values (1,'Intel Extreme Masters'),(2,'DreamHack '),(3,'ESEA'),(4,'ESEA S24 Global Challenge'),(5,'HTC 2vs2 Invitational'),(6,'NVIDIA GeForce CUP'),(7,'ESL Pro League'),(8,'Minor Championship'),(9,'G-League'),(10,'ZEN Esports Network League');

/*Table structure for table `tim` */

DROP TABLE IF EXISTS `tim`;

CREATE TABLE `tim` (
  `idTima` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) DEFAULT NULL,
  `trener` varchar(50) DEFAULT NULL,
  `menadzer` varchar(50) DEFAULT NULL,
  `sponzor` varchar(50) DEFAULT NULL,
  `igre` varchar(30) DEFAULT NULL,
  `zaradjenNovac` double DEFAULT '0',
  `idRegiona` int(11) NOT NULL,
  `idLokacije` int(11) NOT NULL,
  PRIMARY KEY (`idTima`),
  KEY `fk_idRegiona` (`idRegiona`),
  KEY `fk_idLokacije` (`idLokacije`),
  CONSTRAINT `fk_idLokacije` FOREIGN KEY (`idLokacije`) REFERENCES `lokacija` (`idLokacije`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_idRegiona` FOREIGN KEY (`idRegiona`) REFERENCES `region` (`idRegiona`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `tim` */

insert  into `tim`(`idTima`,`naziv`,`trener`,`menadzer`,`sponzor`,`igre`,`zaradjenNovac`,`idRegiona`,`idLokacije`) values (1,'eNergy eSports','/','Kas \"CAWA\" Ahmad','SteelSeries','CSGO',8329,5,8),(2,'Team Liquid','Wilton \"zews\" Prado','Steve \"jokasteve\" Perino','HTC','CSGO, Dota2',626900,2,12),(3,'Virtus.pro','Jakub \"kuben\" Gurczynski','Roman \"dvoryrom\" Dvoryankin','Twitch.tv','CSGO, Dota2',2388361,8,10),(4,'Natus Vincere','Andrey \"Andi\" Prokhorov','Evgeniy \"Ugin\" Erofeev','HyperX','CSGO',1654917,2,5),(5,'FaZe Clan','Robert \"RobbaN\" Dahlström','/','Scuf Gaming ','CSGO',519806,1,3);

/*Table structure for table `turnir` */

DROP TABLE IF EXISTS `turnir`;

CREATE TABLE `turnir` (
  `idTurnira` int(11) NOT NULL AUTO_INCREMENT,
  `pocetak` date NOT NULL,
  `kraj` date NOT NULL,
  `naziv` varchar(50) DEFAULT NULL,
  `nagradniFond` double DEFAULT '0',
  `idSerije` int(11) NOT NULL,
  `idLokacije` int(11) NOT NULL,
  `idRegiona` int(11) NOT NULL,
  PRIMARY KEY (`idTurnira`),
  KEY `fk_idRegionaT` (`idRegiona`),
  KEY `fk_idLokacijeT` (`idLokacije`),
  KEY `fk_idSerijeT` (`idSerije`),
  CONSTRAINT `fk_idLokacijeT` FOREIGN KEY (`idLokacije`) REFERENCES `lokacija` (`idLokacije`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_idSerijeT` FOREIGN KEY (`idSerije`) REFERENCES `serija` (`idSerije`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `turnir` */

insert  into `turnir`(`idTurnira`,`pocetak`,`kraj`,`naziv`,`nagradniFond`,`idSerije`,`idLokacije`,`idRegiona`) values (1,'2017-07-21','2017-07-23','	DreamHack Atlanta 2017',100000,2,3,1),(2,'2017-06-01','2017-06-04','Asia Minor Championship',50000,8,6,7),(3,'2017-05-30','2017-05-31','G-League',25000,9,15,7),(4,'2017-04-03','2017-05-06','ESL Meisterschaft: Spring 2017',12500,7,13,2),(5,'2017-05-26','2017-05-28','ESL India Premiership 2017',15000,7,9,3),(6,'2017-05-29','2017-06-01','Intel Challenge Russia- 2017',30000,1,17,8),(7,'2017-01-06','2017-01-08','ESEA Season 23: Global Premier Challenge',60000,3,2,1),(8,'2017-03-17','2017-03-19','China Cup I',98500,10,15,7),(9,'2017-07-16','2017-05-23','PGL Major Kraków 2017',1000000,4,11,1),(10,'2017-07-01','2017-07-03','ESL One: Cologne 2017',250000,7,7,2),(11,'2017-05-03','2017-05-07','	Intel Extreme Masters XII - Sydney',200000,1,14,4),(12,'2017-04-13','2017-04-14','	HTC Reborn Invitational',10000,5,12,2),(13,'2016-03-17','2016-03-20','World Cyber Arena 2016 - Chinese Qualifier #1',34600,6,15,7),(14,'2017-01-20','2017-01-24','Intel Extreme Masters X - Taipei',50000,1,16,6),(15,'2017-02-16','2017-02-20','DreamHack Tours 2016',55000,2,5,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
