SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `VideoLibrary` ;
CREATE SCHEMA IF NOT EXISTS `VideoLibrary` DEFAULT CHARACTER SET utf8 ;
USE `VideoLibrary` ;

DROP TABLE IF EXISTS `VideoLibrary`.`Members` ;
CREATE  TABLE `VideoLibrary`.`Members` (
  `MemberId` INT NOT NULL AUTO_INCREMENT ,
  `FirstName` VARCHAR(64) NOT NULL ,
  `LastName` VARCHAR(64) NOT NULL ,
  `Street1` VARCHAR(255) NOT NULL ,
  `Street2` VARCHAR(255) NULL DEFAULT NULL ,
  `City` VARCHAR(128) NOT NULL ,
  `State` VARCHAR(128) NOT NULL ,
  `ZipCode` VARCHAR(10) NOT NULL ,
  `Email` VARCHAR(255) NOT NULL ,
  `Password` VARCHAR(64) NOT NULL ,
  `MemberType` INT NOT NULL ,
  `PhoneNo` VARCHAR(10) NOT NULL ,
  `MembershipNo` VARCHAR(64) NOT NULL,
  `LoginTime` DATETIME NULL DEFAULT NULL,
  `Status` INT NOT NULL ,
  PRIMARY KEY (`MemberId`) ,
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC),
  UNIQUE INDEX `PhoneNo_UNIQUE` (`PhoneNo` ASC),
  UNIQUE INDEX `MembershipNo_UNIQUE` (`MembershipNo` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Members Table';

DROP TABLE IF EXISTS `VideoLibrary`.`Movies` ;
CREATE  TABLE `VideoLibrary`.`Movies` (
  `MovieId` INT NOT NULL AUTO_INCREMENT ,
  `MovieName` VARCHAR(255) NOT NULL ,
  `MovieBanner` VARCHAR(255) NOT NULL ,
  `ReleaseDate` DATETIME NOT NULL ,
  `RentAmount` DECIMAL(10,2) NOT NULL ,
  `Category` VARCHAR(64) NOT NULL  ,
  `AvailableCopies` INT NOT NULL DEFAULT 0 ,
  `MovieSummary` VARCHAR(1024) NULL DEFAULT NULL ,
  PRIMARY KEY (`MovieId`),
  UNIQUE INDEX `MovieName_UNIQUE` (`MovieName` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS `VideoLibrary`.`Transactions` ;
CREATE  TABLE `VideoLibrary`.`Transactions` (
  `TransactionId` INT NOT NULL AUTO_INCREMENT ,
  `TransactionDate` DATETIME NOT NULL ,
  `TransactionAmount` DECIMAL(10,2) NOT NULL ,
  `MemberId` INT NOT NULL ,
  `CreditCardNum` VARCHAR(16) NOT NULL ,
  `CreditCardName` VARCHAR(128) NOT NULL ,
  PRIMARY KEY (`TransactionId`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS `VideoLibrary`.`RentedMovies` ;
CREATE  TABLE `VideoLibrary`.`RentedMovies` (
  `RentedMovieId` INT NOT NULL AUTO_INCREMENT ,
  `MovieId` INT NOT NULL ,
  `MemberId` INT NOT NULL ,
  `RentedDate` DATETIME NOT NULL ,
  `RentStatus` INT NOT NULL DEFAULT 0 ,
  `ReturnedDate` DATETIME NULL DEFAULT NULL ,
  `RentAmount` DECIMAL(10,2) NOT NULL ,
  PRIMARY KEY (`RentedMovieId`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE `VideoLibrary`.`MovieQueues` (
  `MovieQueueId` INT NOT NULL AUTO_INCREMENT ,
  `MovieId` INT NOT NULL ,
  `MemberId` INT NOT NULL ,
  PRIMARY KEY (`MovieQueueId`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;  