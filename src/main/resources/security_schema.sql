/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : webapp

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2014-03-27 13:28:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `security_authority`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `security_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qh5t4dhrghyroc0clumyg8gx0` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `security_group`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `security_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8arojwrxmlyr0v6jfjnvnecaq` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `security_group_authorities`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `security_group_authorities` (
  `group_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL,
  PRIMARY KEY (`group_id`,`authority_id`),
  KEY `FK_fy9k4hqdugtmkjqigvvaihy7e` (`authority_id`),
  CONSTRAINT `FK_35wyylpj71cognopvgcmboqog` FOREIGN KEY (`group_id`) REFERENCES `security_group` (`id`),
  CONSTRAINT `FK_fy9k4hqdugtmkjqigvvaihy7e` FOREIGN KEY (`authority_id`) REFERENCES `security_authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `security_group_users`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `security_group_users` (
  `group_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`group_id`,`user_id`),
  KEY `FK_p76w9pk9q61a2kptvwro3x7ap` (`user_id`),
  CONSTRAINT `FK_p76w9pk9q61a2kptvwro3x7ap` FOREIGN KEY (`user_id`) REFERENCES `security_user` (`id`),
  CONSTRAINT `FK_s0phvml2lq332st6ynop8y36b` FOREIGN KEY (`group_id`) REFERENCES `security_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `security_user`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `security_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o716pk0dv59lgrnslah3ngqtd` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `security_user_authorities`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `security_user_authorities` (
  `user_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_id`),
  KEY `FK_ml8yxa6c1le9uv1cpxtug0uls` (`authority_id`),
  CONSTRAINT `FK_ml8yxa6c1le9uv1cpxtug0uls` FOREIGN KEY (`authority_id`) REFERENCES `security_authority` (`id`),
  CONSTRAINT `FK_sw2rjcgtfwe6f09ino27o9jkt` FOREIGN KEY (`user_id`) REFERENCES `security_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
