-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema task_manager
-- -----------------------------------------------------
-- Demo db for task manager app

-- -----------------------------------------------------
-- Schema task_manager
--
-- Demo db for task manager app
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `task_manager` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `task_manager` ;

-- -----------------------------------------------------
-- Table `task_manager`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `task_manager`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `task_manager`.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `task_manager`.`task` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` TINYTEXT NOT NULL,
  `due_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `completed` TINYINT(1) NOT NULL DEFAULT 0,
  `user_id` INT NOT NULL,
  `priority` INT NOT NULL DEFAULT 1 COMMENT '1=low, 2=normal, 3=high',
  INDEX `fk_task_user_idx` (`user_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_task_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `task_manager`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
