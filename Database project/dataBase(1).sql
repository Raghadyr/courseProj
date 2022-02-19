-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema theme_park
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema theme_park
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `theme_park` DEFAULT CHARACTER SET utf8 ;
USE `theme_park` ;

-- -----------------------------------------------------
-- Table `theme_park`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`payment` (
  `Receipt_num` INT NOT NULL,
  `Total_price` DOUBLE NULL DEFAULT '0',
  `Payment_date` DATE NULL DEFAULT NULL,
  `Payment_method` VARCHAR(15) NULL DEFAULT NULL,
  PRIMARY KEY (`Receipt_num`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `theme_park`.`card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`card` (
  `Card_sn` VARCHAR(13) NOT NULL,
  `Pay_Receipt_num` INT NOT NULL,
  `Expiry_date` DATE NULL DEFAULT NULL,
  `Points` INT NULL DEFAULT NULL,
  PRIMARY KEY (`Card_sn`),
  INDEX `Pay_Receipt_num_idx` (`Pay_Receipt_num` ASC) VISIBLE,
  CONSTRAINT `fk_Pay_Receipt_num`
    FOREIGN KEY (`Pay_Receipt_num`)
    REFERENCES `theme_park`.`payment` (`Receipt_num`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `theme_park`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`employee` (
  `Emp_id` VARCHAR(9) NOT NULL,
  `DoB` DATE NULL DEFAULT NULL,
  `Name_` VARCHAR(45) NOT NULL,
  `Gender` VARCHAR(1) NULL DEFAULT NULL,
  `Phone` VARCHAR(10) NULL DEFAULT NULL,
  `Salary` DOUBLE NULL DEFAULT NULL,
  `Post_code` VARCHAR(6) NULL DEFAULT NULL,
  `Street_num` VARCHAR(8) NULL DEFAULT NULL,
  PRIMARY KEY (`Emp_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `theme_park`.`ride`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`ride` (
  `Ride_name` VARCHAR(15) NOT NULL,
  `E_id` VARCHAR(9) NOT NULL,
  `Duration` VARCHAR(10) NULL DEFAULT NULL,
  `Points` INT NULL DEFAULT NULL,
  `Capacity` INT NULL DEFAULT NULL,
  `Age` VARCHAR(2) NULL DEFAULT NULL,
  PRIMARY KEY (`Ride_name`),
  INDEX `E_id_idx` (`E_id` ASC) VISIBLE,
  CONSTRAINT `fkkk_E_id`
    FOREIGN KEY (`E_id`)
    REFERENCES `theme_park`.`employee` (`Emp_id`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `theme_park`.`check_`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`check_` (
  `E_id` VARCHAR(9) NOT NULL,
  `Ride_name` VARCHAR(15) NOT NULL,
  `M_Cost` DOUBLE NULL DEFAULT NULL,
  `Type_of_Damage` VARCHAR(20) NULL DEFAULT NULL,
  `Date_` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`E_id`, `Ride_name`),
  INDEX `Ride_name_idx` (`Ride_name` ASC) VISIBLE,
  CONSTRAINT `fkss_E_id`
    FOREIGN KEY (`E_id`)
    REFERENCES `theme_park`.`employee` (`Emp_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fkss_Ride_name`
    FOREIGN KEY (`Ride_name`)
    REFERENCES `theme_park`.`ride` (`Ride_name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `theme_park`.`resturant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`resturant` (
  `Res_name` VARCHAR(25) NOT NULL,
  `Capacity` INT NULL DEFAULT NULL,
  `Food_type` VARCHAR(15) NULL DEFAULT NULL,
  `Location` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`Res_name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `theme_park`.`visitors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`visitors` (
  `Vis_id` VARCHAR(9) NOT NULL,
  `Gender` VARCHAR(1) NULL DEFAULT NULL,
  `DoB` DATE NULL DEFAULT NULL,
  `Phone` CHAR(10) NULL DEFAULT NULL,
  `Name_` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`Vis_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `theme_park`.`eat_in`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`eat_in` (
  `Vis_id` VARCHAR(9) NOT NULL,
  `Res_name` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`Vis_id`, `Res_name`),
  INDEX `Res_name_idx` (`Res_name` ASC) VISIBLE,
  CONSTRAINT `fkh_Res_name`
    FOREIGN KEY (`Res_name`)
    REFERENCES `theme_park`.`resturant` (`Res_name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fkh_Vis_id`
    FOREIGN KEY (`Vis_id`)
    REFERENCES `theme_park`.`visitors` (`Vis_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `theme_park`.`event_`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`event_` (
  `Event_No` INT NOT NULL,
  `E_id` VARCHAR(9) NOT NULL,
  `Theme` VARCHAR(15) NULL DEFAULT NULL,
  `Date_` DATE NULL DEFAULT NULL,
  `Special_effect` VARCHAR(15) NULL DEFAULT NULL,
  `Act_Role` VARCHAR(15) NULL DEFAULT NULL,
  `Act_Costume` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`Event_No`),
  INDEX `E_id_idx` (`E_id` ASC) VISIBLE,
  CONSTRAINT `fkk_E_id`
    FOREIGN KEY (`E_id`)
    REFERENCES `theme_park`.`employee` (`Emp_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `theme_park`.`shops`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`shops` (
  `Shop_sn` CHAR(13) NOT NULL,
  `Open_time` TIME NULL DEFAULT NULL,
  `Close_time` TIME NULL DEFAULT NULL,
  `Catogery` VARCHAR(15) NULL DEFAULT NULL,
  `Name` VARCHAR(25) NULL DEFAULT NULL,
  `Location` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`Shop_sn`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `theme_park`.`goes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`goes` (
  `Vis_id` VARCHAR(9) NOT NULL,
  `Shop_sn` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`Vis_id`, `Shop_sn`),
  INDEX `Shop_sn_idx` (`Shop_sn` ASC) VISIBLE,
  CONSTRAINT `fkhh_Shop_sn`
    FOREIGN KEY (`Shop_sn`)
    REFERENCES `theme_park`.`shops` (`Shop_sn`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fkhh_Vis_id`
    FOREIGN KEY (`Vis_id`)
    REFERENCES `theme_park`.`visitors` (`Vis_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `theme_park`.`picnic_spots`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`picnic_spots` (
  `Name` VARCHAR(15) NOT NULL,
  `Close_time` TIME NULL DEFAULT NULL,
  `Open_time` TIME NULL DEFAULT NULL,
  `Location` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`Name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `theme_park`.`goes_to`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`goes_to` (
  `Vis_id` VARCHAR(9) NOT NULL,
  `P_S_name` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`Vis_id`, `P_S_name`),
  INDEX `P_S_name_idx` (`P_S_name` ASC) VISIBLE,
  CONSTRAINT `fkg_P_S_name`
    FOREIGN KEY (`P_S_name`)
    REFERENCES `theme_park`.`picnic_spots` (`Name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fkg_Vis_id`
    FOREIGN KEY (`Vis_id`)
    REFERENCES `theme_park`.`visitors` (`Vis_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `theme_park`.`medical_case`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`medical_case` (
  `Med_id` VARCHAR(9) NOT NULL,
  `Vis_id` VARCHAR(9) NULL DEFAULT NULL,
  `Date_` DATE NULL DEFAULT NULL,
  `Entrance_time` TIME NULL DEFAULT NULL,
  `Discharge_time` TIME NULL DEFAULT NULL,
  `Case_` VARCHAR(15) NULL DEFAULT NULL,
  `Treatment` VARCHAR(25) NULL DEFAULT NULL,
  PRIMARY KEY (`Med_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `theme_park`.`helps`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`helps` (
  `E_id` VARCHAR(9) NOT NULL,
  `Med_id` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`E_id`, `Med_id`),
  INDEX `Med_id_idx` (`Med_id` ASC) VISIBLE,
  CONSTRAINT `fk_E_id`
    FOREIGN KEY (`E_id`)
    REFERENCES `theme_park`.`employee` (`Emp_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Med_id`
    FOREIGN KEY (`Med_id`)
    REFERENCES `theme_park`.`medical_case` (`Med_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `theme_park`.`organizes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`organizes` (
  `E_id` VARCHAR(9) NOT NULL,
  `Event_No` INT NOT NULL,
  PRIMARY KEY (`E_id`, `Event_No`),
  INDEX `Event_No_idx` (`Event_No` ASC) VISIBLE,
  CONSTRAINT `fks_E_id`
    FOREIGN KEY (`E_id`)
    REFERENCES `theme_park`.`employee` (`Emp_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fks_Event_No`
    FOREIGN KEY (`Event_No`)
    REFERENCES `theme_park`.`event_` (`Event_No`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `theme_park`.`round`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`round` (
  `R_num` INT NOT NULL,
  `Ride_name` VARCHAR(15) NOT NULL,
  `Time` TIME NULL DEFAULT NULL,
  `Date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`R_num`, `Ride_name`),
  INDEX `Ride_name_idx` (`Ride_name` ASC) VISIBLE,
  CONSTRAINT `fkm_Ride_name`
    FOREIGN KEY (`Ride_name`)
    REFERENCES `theme_park`.`ride` (`Ride_name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `theme_park`.`plays`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`plays` (
  `Vis_id` VARCHAR(9) NOT NULL,
  `Card_sn` VARCHAR(13) NOT NULL,
  `R_num` INT NOT NULL,
  `Ride_name` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`Vis_id`, `Card_sn`, `R_num`, `Ride_name`),
  INDEX `R_num_idx` (`R_num` ASC) VISIBLE,
  INDEX `Ride_name_idx` (`Ride_name` ASC) VISIBLE,
  INDEX `Card_sn_idx` (`Card_sn` ASC) VISIBLE,
  CONSTRAINT `fkb_Card_sn`
    FOREIGN KEY (`Card_sn`)
    REFERENCES `theme_park`.`card` (`Card_sn`),
  CONSTRAINT `fkb_R_num`
    FOREIGN KEY (`R_num`)
    REFERENCES `theme_park`.`round` (`R_num`),
  CONSTRAINT `fkb_Ride_name`
    FOREIGN KEY (`Ride_name`)
    REFERENCES `theme_park`.`ride` (`Ride_name`),
  CONSTRAINT `fkb_Vis_id`
    FOREIGN KEY (`Vis_id`)
    REFERENCES `theme_park`.`visitors` (`Vis_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `theme_park`.`reduse_points`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`reduse_points` (
  `Card_sn` VARCHAR(13) NOT NULL,
  `Ride_Name` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`Card_sn`, `Ride_Name`),
  INDEX `Ride_name_idx` (`Ride_Name` ASC) VISIBLE,
  CONSTRAINT `fkk_Card_sn`
    FOREIGN KEY (`Card_sn`)
    REFERENCES `theme_park`.`card` (`Card_sn`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fkk_Ride_name`
    FOREIGN KEY (`Ride_Name`)
    REFERENCES `theme_park`.`ride` (`Ride_name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `theme_park`.`top_up`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`top_up` (
  `E_id` VARCHAR(9) NOT NULL,
  `Card_sn` VARCHAR(13) NOT NULL,
  `Vis_id` VARCHAR(9) NOT NULL,
  `Receipt_num` INT NOT NULL,
  PRIMARY KEY (`E_id`, `Card_sn`, `Vis_id`, `Receipt_num`),
  INDEX `Card_sn_idx` (`Card_sn` ASC) VISIBLE,
  INDEX `Vis_id_idx` (`Vis_id` ASC) VISIBLE,
  INDEX `Receipt_num_idx` (`Receipt_num` ASC) VISIBLE,
  CONSTRAINT `fkz_Card_sn`
    FOREIGN KEY (`Card_sn`)
    REFERENCES `theme_park`.`card` (`Card_sn`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fkz_E_id`
    FOREIGN KEY (`E_id`)
    REFERENCES `theme_park`.`employee` (`Emp_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fkz_Receipt_num`
    FOREIGN KEY (`Receipt_num`)
    REFERENCES `theme_park`.`payment` (`Receipt_num`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fkz_Vis_id`
    FOREIGN KEY (`Vis_id`)
    REFERENCES `theme_park`.`visitors` (`Vis_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `theme_park`.`visit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theme_park`.`visit` (
  `Event_No` INT NOT NULL,
  `Vis_id` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`Event_No`, `Vis_id`),
  INDEX `Vis_id_idx` (`Vis_id` ASC) VISIBLE,
  CONSTRAINT `fk_Event_No`
    FOREIGN KEY (`Event_No`)
    REFERENCES `theme_park`.`event_` (`Event_No`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Vis_id`
    FOREIGN KEY (`Vis_id`)
    REFERENCES `theme_park`.`visitors` (`Vis_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
