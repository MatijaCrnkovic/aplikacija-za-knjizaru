-- MySQL Script generated by MySQL Workbench
-- Tue Dec  3 23:52:48 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema knjizara
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema knjizara
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `knjizara` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `knjizara` ;

-- -----------------------------------------------------
-- Table `knjizara`.`brporudzbina`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `knjizara`.`brporudzbina` ;

CREATE TABLE IF NOT EXISTS `knjizara`.`brporudzbina` (
  `brojPorudzbine` INT(15) NOT NULL AUTO_INCREMENT,
  `datumPorudzbine` VARCHAR(45) NOT NULL,
  `datumIsporuke` VARCHAR(45) NOT NULL,
  `ukupnaCena` VARCHAR(45) NOT NULL,
  `ukljucenPopust` VARCHAR(45) NOT NULL,
  `brojClanskeKarte` INT(11) NOT NULL,
  `IDZaposleni` INT(11) NOT NULL,
  `ISBN` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`brojPorudzbine`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `knjizara`.`knjiga`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `knjizara`.`knjiga` ;

CREATE TABLE IF NOT EXISTS `knjizara`.`knjiga` (
  `ISBN` VARCHAR(13) NOT NULL,
  `imeKnjige` VARCHAR(100) NOT NULL,
  `pisac` VARCHAR(45) NOT NULL,
  `kategorija` VARCHAR(45) NOT NULL,
  `godinaIzdavanja` INT(4) NOT NULL,
  `cena` DOUBLE NOT NULL,
  PRIMARY KEY (`ISBN`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `knjizara`.`korisnici`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `knjizara`.`korisnici` ;

CREATE TABLE IF NOT EXISTS `knjizara`.`korisnici` (
  `brojClanskeKarte` INT(7) NOT NULL AUTO_INCREMENT,
  `JMBG` VARCHAR(13) NOT NULL,
  `ime` VARCHAR(15) NOT NULL,
  `prezime` VARCHAR(20) NOT NULL,
  `adresa` VARCHAR(50) NOT NULL,
  `grad` VARCHAR(20) NOT NULL,
  `brTelefona` VARCHAR(20) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`brojClanskeKarte`),
  UNIQUE INDEX `JMBG_UNIQUE` (`JMBG` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 32
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `knjizara`.`zaposleni`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `knjizara`.`zaposleni` ;

CREATE TABLE IF NOT EXISTS `knjizara`.`zaposleni` (
  `IDzaposleni` INT(11) NOT NULL AUTO_INCREMENT,
  `JMBG` VARCHAR(45) NOT NULL,
  `ime` VARCHAR(15) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `prezime` VARCHAR(20) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `adresa` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `grad` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `brTelefona` VARCHAR(10) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `email` VARCHAR(50) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `datumZaposlenja` DATE NULL DEFAULT NULL,
  `pozicija` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `plata` INT(11) NOT NULL,
  `username` VARCHAR(15) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `password` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  PRIMARY KEY (`IDzaposleni`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `JMBG_UNIQUE` (`JMBG` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
