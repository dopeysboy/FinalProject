-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema rotahudb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `rotahudb` ;

-- -----------------------------------------------------
-- Schema rotahudb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rotahudb` DEFAULT CHARACTER SET utf8 ;
USE `rotahudb` ;

-- -----------------------------------------------------
-- Table `debt_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `debt_type` ;

CREATE TABLE IF NOT EXISTS `debt_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(250) NULL,
  `default_priority` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `debt_lender`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `debt_lender` ;

CREATE TABLE IF NOT EXISTS `debt_lender` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `image_url` VARCHAR(250) NULL,
  `site_url` VARCHAR(250) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NULL,
  `enabled` TINYINT NULL,
  `image_url` VARCHAR(250) NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `debt`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `debt` ;

CREATE TABLE IF NOT EXISTS `debt` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `annual_percentage_rate` DOUBLE NOT NULL,
  `minimum_monthly_payment` DOUBLE NOT NULL,
  `initial_balance` DOUBLE NOT NULL,
  `current_balance` DOUBLE NULL,
  `priority` INT NULL,
  `debt_type_id` INT NOT NULL,
  `debt_lender_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_debt_debt_type1_idx` (`debt_type_id` ASC),
  INDEX `fk_debt_debt_lender1_idx` (`debt_lender_id` ASC),
  INDEX `fk_debt_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_debt_debt_type1`
    FOREIGN KEY (`debt_type_id`)
    REFERENCES `debt_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_debt_debt_lender1`
    FOREIGN KEY (`debt_lender_id`)
    REFERENCES `debt_lender` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_debt_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `payment` ;

CREATE TABLE IF NOT EXISTS `payment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE NOT NULL,
  `debt_id` INT NOT NULL,
  `payment_date` DATETIME NOT NULL,
  `comment` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_payment_debt1_idx` (`debt_id` ASC),
  CONSTRAINT `fk_payment_debt1`
    FOREIGN KEY (`debt_id`)
    REFERENCES `debt` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `frequency`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `frequency` ;

CREATE TABLE IF NOT EXISTS `frequency` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `expense`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `expense` ;

CREATE TABLE IF NOT EXISTS `expense` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45) NULL,
  `amount` DOUBLE NULL,
  `category_id` INT NOT NULL,
  `frequency_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_expense_category1_idx` (`category_id` ASC),
  INDEX `fk_expense_frequency1_idx` (`frequency_id` ASC),
  INDEX `fk_expense_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_expense_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_expense_frequency1`
    FOREIGN KEY (`frequency_id`)
    REFERENCES `frequency` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_expense_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `income`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `income` ;

CREATE TABLE IF NOT EXISTS `income` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE NULL,
  `description` VARCHAR(500) NULL,
  `category_id` INT NOT NULL,
  `frequency_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_income_category1_idx` (`category_id` ASC),
  INDEX `fk_income_frequency1_idx` (`frequency_id` ASC),
  INDEX `fk_income_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_income_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_income_frequency1`
    FOREIGN KEY (`frequency_id`)
    REFERENCES `frequency` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_income_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rating` ;

CREATE TABLE IF NOT EXISTS `rating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rate` INT NOT NULL,
  `description` VARCHAR(250) NULL,
  `debt_lender_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `rating_date` DATETIME NOT NULL,
  `enabled` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_rating_debt_lender1_idx` (`debt_lender_id` ASC),
  INDEX `fk_rating_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_rating_debt_lender1`
    FOREIGN KEY (`debt_lender_id`)
    REFERENCES `debt_lender` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rating_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `credit_resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `credit_resource` ;

CREATE TABLE IF NOT EXISTS `credit_resource` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(250) NULL,
  `video_url` VARCHAR(1500) NULL,
  `site_url` VARCHAR(250) NULL,
  `debt_intensity` INT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` VARCHAR(45) NOT NULL,
  `enabled` TINYINT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_credit_resource_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_credit_resource_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_credit_resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_credit_resource` ;

CREATE TABLE IF NOT EXISTS `user_has_credit_resource` (
  `user_id` INT NOT NULL,
  `credit_resource_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `credit_resource_id`),
  INDEX `fk_user_has_credit_resource_credit_resource1_idx` (`credit_resource_id` ASC),
  INDEX `fk_user_has_credit_resource_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_credit_resource_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_credit_resource_credit_resource1`
    FOREIGN KEY (`credit_resource_id`)
    REFERENCES `credit_resource` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS rotahu@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'rotahu'@'localhost' IDENTIFIED BY 'rotahu';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'rotahu'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `debt_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `rotahudb`;
INSERT INTO `debt_type` (`id`, `description`, `default_priority`) VALUES (1, 'Credit Card', 5);
INSERT INTO `debt_type` (`id`, `description`, `default_priority`) VALUES (2, 'Mortgage', 1);
INSERT INTO `debt_type` (`id`, `description`, `default_priority`) VALUES (3, 'Auto Loan', 2);
INSERT INTO `debt_type` (`id`, `description`, `default_priority`) VALUES (4, 'Personal Loan', 3);
INSERT INTO `debt_type` (`id`, `description`, `default_priority`) VALUES (5, 'Student Loan', 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `debt_lender`
-- -----------------------------------------------------
START TRANSACTION;
USE `rotahudb`;
INSERT INTO `debt_lender` (`id`, `name`, `image_url`, `site_url`) VALUES (1, 'Navy Federal', NULL, NULL);
INSERT INTO `debt_lender` (`id`, `name`, `image_url`, `site_url`) VALUES (2, 'Wells Fargo', NULL, NULL);
INSERT INTO `debt_lender` (`id`, `name`, `image_url`, `site_url`) VALUES (3, 'USAA', NULL, NULL);
INSERT INTO `debt_lender` (`id`, `name`, `image_url`, `site_url`) VALUES (4, 'Chase ', NULL, NULL);
INSERT INTO `debt_lender` (`id`, `name`, `image_url`, `site_url`) VALUES (5, 'Bank of America', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `rotahudb`;
INSERT INTO `user` (`id`, `username`, `password`, `email`, `role`, `enabled`, `image_url`, `first_name`, `last_name`, `created_at`, `updated_at`) VALUES (1, 'admin', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'admin@gmail.com', NULL, 1, NULL, 'john', 'doe', '2022-11-22 00:00:00', '2022-11-22 12:00:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `debt`
-- -----------------------------------------------------
START TRANSACTION;
USE `rotahudb`;
INSERT INTO `debt` (`id`, `name`, `annual_percentage_rate`, `minimum_monthly_payment`, `initial_balance`, `current_balance`, `priority`, `debt_type_id`, `debt_lender_id`, `user_id`, `created_at`) VALUES (1, 'Navy Federal Credit Card', 18.65, 29.00, 7500.00, 7500.00, 1, 1, 1, 1, '2022-11-22 12:00:00');
INSERT INTO `debt` (`id`, `name`, `annual_percentage_rate`, `minimum_monthly_payment`, `initial_balance`, `current_balance`, `priority`, `debt_type_id`, `debt_lender_id`, `user_id`, `created_at`) VALUES (2, 'Mortgage', 4.75, 2478.72, 489000.00, 489000.00, 10, 1, 2, 1, '2022-11-22 12:00:00');
INSERT INTO `debt` (`id`, `name`, `annual_percentage_rate`, `minimum_monthly_payment`, `initial_balance`, `current_balance`, `priority`, `debt_type_id`, `debt_lender_id`, `user_id`, `created_at`) VALUES (3, 'Auto Loan', 9.2, 457.50, 52000, 50750, 9, 2, 4, 1, '2022-11-22 12:00:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `payment`
-- -----------------------------------------------------
START TRANSACTION;
USE `rotahudb`;
INSERT INTO `payment` (`id`, `amount`, `debt_id`, `payment_date`, `comment`) VALUES (1, 750, 1, '2022-11-22 13:00:00', 'initial payment with Rutahu');
INSERT INTO `payment` (`id`, `amount`, `debt_id`, `payment_date`, `comment`) VALUES (2, 125, 1, '2022-11-23 00:00:00', 'new payment');

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `rotahudb`;
INSERT INTO `category` (`id`, `name`) VALUES (1, 'groceries');
INSERT INTO `category` (`id`, `name`) VALUES (2, 'developer salary');
INSERT INTO `category` (`id`, `name`) VALUES (3, 'Monthly Housing Allowance');
INSERT INTO `category` (`id`, `name`) VALUES (4, 'Dividends');
INSERT INTO `category` (`id`, `name`) VALUES (5, 'Rent');
INSERT INTO `category` (`id`, `name`) VALUES (6, 'Gas');

COMMIT;


-- -----------------------------------------------------
-- Data for table `frequency`
-- -----------------------------------------------------
START TRANSACTION;
USE `rotahudb`;
INSERT INTO `frequency` (`id`, `name`) VALUES (1, 'monthly');
INSERT INTO `frequency` (`id`, `name`) VALUES (2, 'weekly');
INSERT INTO `frequency` (`id`, `name`) VALUES (3, 'quarterly');

COMMIT;


-- -----------------------------------------------------
-- Data for table `expense`
-- -----------------------------------------------------
START TRANSACTION;
USE `rotahudb`;
INSERT INTO `expense` (`id`, `description`, `amount`, `category_id`, `frequency_id`, `user_id`, `created_at`, `updated_at`) VALUES (1, 'groceries', 250, 1, 2, 1, '2022-11-22 12:00:00', '2022-11-22 18:00:00');
INSERT INTO `expense` (`id`, `description`, `amount`, `category_id`, `frequency_id`, `user_id`, `created_at`, `updated_at`) VALUES (2, 'rent', 2200, 4, 1, 1, '2022-11-22 12:00:00', '2022-11-22 18:00:00');
INSERT INTO `expense` (`id`, `description`, `amount`, `category_id`, `frequency_id`, `user_id`, `created_at`, `updated_at`) VALUES (3, 'gas', 200, 5, 2, 1, '2022-11-22 12:00:00', '2022-11-22 18:00:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `income`
-- -----------------------------------------------------
START TRANSACTION;
USE `rotahudb`;
INSERT INTO `income` (`id`, `amount`, `description`, `category_id`, `frequency_id`, `user_id`, `created_at`, `updated_at`) VALUES (1, 7500, 'Software Engineer', 2, 1, 1, '2022-11-22 00:00:00', '2022-11-22 12:00:00');
INSERT INTO `income` (`id`, `amount`, `description`, `category_id`, `frequency_id`, `user_id`, `created_at`, `updated_at`) VALUES (2, 993, 'MHA', 3, 1, 1, '2022-11-22 00:00:00', '2022-11-22 12:00:00');
INSERT INTO `income` (`id`, `amount`, `description`, `category_id`, `frequency_id`, `user_id`, `created_at`, `updated_at`) VALUES (3, 55.00, 'Dividends', 4, 3, 1, '2022-11-22 00:00:00', '2022-11-22 12:00:00');
INSERT INTO `income` (`id`, `amount`, `description`, `category_id`, `frequency_id`, `user_id`, `created_at`, `updated_at`) VALUES (4, 250, 'Groceries', 1, 2, 1, '2022-11-22 00:00:00', '2022-11-22 00:00:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `rotahudb`;
INSERT INTO `rating` (`id`, `rate`, `description`, `debt_lender_id`, `user_id`, `rating_date`, `enabled`) VALUES (1, 5, '1 year 0% APR', 1, 1, '2022-11-22 12:00:00', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `credit_resource`
-- -----------------------------------------------------
START TRANSACTION;
USE `rotahudb`;
INSERT INTO `credit_resource` (`id`, `description`, `video_url`, `site_url`, `debt_intensity`, `created_at`, `updated_at`, `enabled`, `user_id`) VALUES (1, 'nice resource', NULL, NULL, NULL, '2022-11-22 12:00:00', '2022-11-22 12:00:00', NULL, 1);

COMMIT;

