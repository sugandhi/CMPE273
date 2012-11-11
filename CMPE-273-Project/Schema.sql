SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
DROP SCHEMA IF EXISTS `videolibrary` ;
CREATE SCHEMA IF NOT EXISTS `videolibrary` DEFAULT CHARACTER SET utf8 ;
DROP SCHEMA IF EXISTS `VideoLibrary` ;
CREATE SCHEMA IF NOT EXISTS `VideoLibrary` ;
USE `mydb` ;
USE `videolibrary` ;
USE `VideoLibrary` ;

-- -----------------------------------------------------
-- Table `VideoLibrary`.`Invoices`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `VideoLibrary`.`Invoices` ;

CREATE  TABLE IF NOT EXISTS `VideoLibrary`.`Invoices` (
  `InvoiceId` INT(11) NOT NULL AUTO_INCREMENT ,
  `InvoiceDate` DATETIME NOT NULL ,
  `InvoiceAmount` DECIMAL(10,2) NOT NULL ,
  `MemberId` INT(11) NOT NULL ,
  `CreditCardNum` VARCHAR(16) NULL DEFAULT NULL ,
  `CreditCardName` VARCHAR(128) NULL DEFAULT NULL ,
  `PaymentDate` DATETIME NULL DEFAULT NULL ,
  `InvoiceStatus` INT(11) NULL DEFAULT '0' ,
  PRIMARY KEY (`InvoiceId`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `VideoLibrary`.`Members`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `VideoLibrary`.`Members` ;

CREATE  TABLE IF NOT EXISTS `VideoLibrary`.`Members` (
  `MemberId` INT(11) NOT NULL AUTO_INCREMENT ,
  `FirstName` VARCHAR(64) NOT NULL ,
  `LastName` VARCHAR(64) NOT NULL ,
  `Street1` VARCHAR(255) NOT NULL ,
  `Street2` VARCHAR(255) NULL DEFAULT NULL ,
  `City` VARCHAR(128) NOT NULL ,
  `State` VARCHAR(128) NOT NULL ,
  `ZipCode` VARCHAR(10) NOT NULL ,
  `Email` VARCHAR(255) NOT NULL ,
  `Password` VARCHAR(64) NOT NULL ,
  `MemberType` INT(11) NOT NULL ,
  `PhoneNo` VARCHAR(10) NOT NULL ,
  `MembershipNo` VARCHAR(64) NOT NULL ,
  `LoginTime` DATETIME NULL DEFAULT NULL ,
  `Status` INT(11) NOT NULL DEFAULT '0' ,
  `Balance` DECIMAL(10,2) NOT NULL DEFAULT '0.00' ,
  `createTime` DATETIME NOT NULL ,
  PRIMARY KEY (`MemberId`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Members Table';

CREATE UNIQUE INDEX `Email_UNIQUE` ON `VideoLibrary`.`Members` (`Email` ASC) ;

CREATE UNIQUE INDEX `MemberShipNo_UNIQUE` ON `VideoLibrary`.`Members` (`MembershipNo` ASC) ;


-- -----------------------------------------------------
-- Table `VideoLibrary`.`Movies`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `VideoLibrary`.`Movies` ;

CREATE  TABLE IF NOT EXISTS `VideoLibrary`.`Movies` (
  `MovieId` INT(11) NOT NULL AUTO_INCREMENT ,
  `MovieName` VARCHAR(255) NOT NULL ,
  `MovieBanner` VARCHAR(255) NOT NULL ,
  `ReleaseDate` DATETIME NOT NULL ,
  `RentAmount` DECIMAL(10,2) NOT NULL ,
  `CategoryId` INT(11) NOT NULL ,
  `AvailableCopies` INT(11) NOT NULL DEFAULT '0' ,
  `MovieSummary` VARCHAR(1024) NULL DEFAULT NULL ,
  PRIMARY KEY (`MovieId`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `VideoLibrary`.`RentedMovies`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `VideoLibrary`.`RentedMovies` ;

CREATE  TABLE IF NOT EXISTS `VideoLibrary`.`RentedMovies` (
  `RentedMovieId` INT(11) NOT NULL AUTO_INCREMENT ,
  `MovieId` INT(11) NOT NULL ,
  `MemberId` INT(11) NOT NULL ,
  `RentedDate` DATETIME NOT NULL ,
  `RentStatus` INT(11) NULL DEFAULT '0' ,
  `ReturnedDate` DATETIME NULL DEFAULT NULL ,
  `RentAmount` DECIMAL(10,2) NULL DEFAULT '0.00' ,
  PRIMARY KEY (`RentedMovieId`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
