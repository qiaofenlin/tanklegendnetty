/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50719
Source Host           : 127.0.0.1:3306
Source Database       : tanklegend

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-10-02 22:48:26
*/

SET FOREIGN_KEY_CHECKS=0;
-- --------------------------------------------------COMMON--------------------------------------------------------------------

-- ----------------------------
-- 创建common_repertory_equpment_whell表  -->履带 
-- ----------------------------
DROP TABLE IF EXISTS `common_repertory_equpment_whell`;
CREATE TABLE `common_repertory_equpment_whell` (
  `equpment_whell_id` INT(20) NOT NULL,
  `equpment_whell_name` VARCHAR(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HP` INT(20) DEFAULT NULL,
  `fire` INT(20) DEFAULT NULL,
  `shotspeed` INT(20) DEFAULT NULL,
  `tankspeed` INT(20) DEFAULT NULL,
  PRIMARY KEY (`equpment_whell_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of common_repertory_equpment_whell
-- ----------------------------
INSERT INTO `common_repertory_equpment_whell` VALUES ('1', '履带1', '20', '20', '40', '50');
INSERT INTO `common_repertory_equpment_whell` VALUES ('2', '履带2', '20', '25', '40', '60');
INSERT INTO `common_repertory_equpment_whell` VALUES ('3', '履带3', '20', '30', '40', '70');
INSERT INTO `common_repertory_equpment_whell` VALUES ('4', '履带4', '20', '35', '40', '75');
INSERT INTO `common_repertory_equpment_whell` VALUES ('5', '履带5', '20', '40', '40', '80');

-- ----------------------------
-- 创建common_repertory_equpment_engine表  -->发动机 
-- ----------------------------
DROP TABLE IF EXISTS `common_repertory_equpment_engine`;
CREATE TABLE `common_repertory_equpment_engine` (
  `equpment_engine_id` INT(20) NOT NULL,
  `equpment_engine_name` VARCHAR(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HP` INT(20) DEFAULT NULL,
  `fire` INT(20) DEFAULT NULL,
  `shotspeed` INT(20) DEFAULT NULL,
  `tankspeed` INT(20) DEFAULT NULL,
  PRIMARY KEY (`equpment_engine_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of common_repertory_equpment_engine
-- ----------------------------
INSERT INTO `common_repertory_equpment_engine` VALUES ('1', '发动机1', '20', '25', '40', '50');
INSERT INTO `common_repertory_equpment_engine` VALUES ('2', '发动机2', '20', '30', '40', '60');
INSERT INTO `common_repertory_equpment_engine` VALUES ('3', '发动机3', '20', '35', '40', '70');
INSERT INTO `common_repertory_equpment_engine` VALUES ('4', '发动机4', '20', '40', '40', '75');
INSERT INTO `common_repertory_equpment_engine` VALUES ('5', '发动机5', '20', '45', '40', '80');
-- ----------------------------
-- 创建common_repertory_equpment_turret表 -->炮塔
-- ----------------------------
DROP TABLE IF EXISTS `common_repertory_equpment_turret`;
CREATE TABLE `common_repertory_equpment_turret` (
  `equpment_turret_id` INT(20) NOT NULL,
  `equpment_turret_name` VARCHAR(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HP` INT(20) DEFAULT NULL,
  `fire` INT(20) DEFAULT NULL,
  `shotspeed` INT(20) DEFAULT NULL,
  `tankspeed` INT(20) DEFAULT NULL,
  PRIMARY KEY (`equpment_turret_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of common_repertory_equpment_turret	
-- ----------------------------
INSERT INTO `common_repertory_equpment_turret` VALUES ('1', '炮塔1','20', '35', '40', '20');
INSERT INTO `common_repertory_equpment_turret` VALUES ('2', '炮塔2','20', '40', '40', '20');
INSERT INTO `common_repertory_equpment_turret` VALUES ('3', '炮塔3','20', '45', '40', '20');
INSERT INTO `common_repertory_equpment_turret` VALUES ('4', '炮塔4','20', '50', '40', '20');
INSERT INTO `common_repertory_equpment_turret` VALUES ('5', '炮塔5','20', '55', '40', '20');


-- ----------------------------
-- 创建common_repertory_equpment_armour表 -->装甲
-- ----------------------------
DROP TABLE IF EXISTS `common_repertory_equpment_armour`;
CREATE TABLE `common_repertory_equpment_armour` (
  `equpment_armour_id` INT(20) NOT NULL,
  `equpment_armour_name` VARCHAR(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HP` INT(20) DEFAULT NULL,
  `fire` INT(20) DEFAULT NULL,
  `shotspeed` INT(20) DEFAULT NULL,
  `tankspeed` INT(20) DEFAULT NULL,
  PRIMARY KEY (`equpment_armour_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of common_repertory_equpment_turret	
-- ----------------------------
INSERT INTO `common_repertory_equpment_armour` VALUES ('1', '装甲1','30', '20', '40', '20');
INSERT INTO `common_repertory_equpment_armour` VALUES ('2', '装甲2','35', '20', '40', '20');
INSERT INTO `common_repertory_equpment_armour` VALUES ('3', '装甲3','40', '20', '40', '20');
INSERT INTO `common_repertory_equpment_armour` VALUES ('4', '装甲4','45', '20', '40', '20');
INSERT INTO `common_repertory_equpment_armour` VALUES ('5', '装甲5','50', '20', '40', '20');


-- ----------------------------
-- 创建common_game_model_info表
-- ----------------------------
DROP TABLE IF EXISTS `common_game_model_info`;
CREATE TABLE `common_game_model_info` (
  `game_model_id` INT(20) NOT NULL,
  `game_model_name` VARCHAR(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`game_model_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of common_game_model_info
-- ----------------------------
INSERT INTO `common_game_model_info` VALUES ('1', '个人模式');
INSERT INTO `common_game_model_info` VALUES ('2', '对战模式');
INSERT INTO `common_game_model_info` VALUES ('3', '训练模式');

-- ----------------------------
-- 创建common_game_model_info表
-- ----------------------------
DROP TABLE IF EXISTS `common_repertory_equpment_list`;
CREATE TABLE `common_repertory_equpment_list` (
  `common_equpment_list_id` INT(32) NOT NULL,
  `common_equpment_list_name` VARCHAR(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`common_equpment_list_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of common_game_model_info
-- ----------------------------
INSERT INTO `common_repertory_equpment_list` VALUES ('1', 'equpment_turret_id');
INSERT INTO `common_repertory_equpment_list` VALUES ('2', 'equpment_whell_id');
INSERT INTO `common_repertory_equpment_list` VALUES ('3', 'equpment_engine_id');
INSERT INTO `common_repertory_equpment_list` VALUES ('4', 'equpment_armour_id');


-- --------------------------------------------------USER--------------------------------------------------------------------
-- ----------------------------
-- 创建user_repertory_equpment表
-- ----------------------------
DROP TABLE IF EXISTS `user_repertory_equpment`;
CREATE TABLE `user_repertory_equpment` (
  `user_repertory_equpmen_id` INT(20) NOT NULL,
  `user_repertory_equpmen_type` INT(20) NOT NULL,
  `user_repertory_equpmen_type_id` INT(20) NOT NULL,
  PRIMARY KEY (`user_repertory_equpmen_id`,`user_repertory_equpmen_type`, `user_repertory_equpmen_type_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_repertory_equpment
-- ----------------------------
INSERT INTO `user_repertory_equpment` VALUES ('1', '1','1');
INSERT INTO `user_repertory_equpment` VALUES ('2', '2','1');
INSERT INTO `user_repertory_equpment` VALUES ('3', '3','1');
INSERT INTO `user_repertory_equpment` VALUES ('4', '4','1');



-- ----------------------------
-- 创建user_repertory_collector表
-- ----------------------------
DROP TABLE IF EXISTS `user_repertory_collector`;
CREATE TABLE `user_repertory_collector` (
  `user_id` INT(20) NOT NULL,
  `user_repertory_collector_id` INT(20) NOT NULL,
  `user_repertory_collector_type` INT(20) NOT NULL,
  `user_repertory_collector_type_id` INT(20) NOT NULL,
  PRIMARY KEY (`user_repertory_collector_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `user_repertory_collector` VALUES ('1', '1', '1','1');

-- ----------------------------
-- 创建user_repertory_info表
-- ----------------------------
DROP TABLE IF EXISTS `user_repertory_info`;
CREATE TABLE `user_repertory_info` (
  `user_id` INT(20) NOT NULL,
  `user_repertory_id` INT(20) NOT NULL,
  `user_repertory_equpment_id` INT(20) NOT NULL,
  `user_repertory_collector_id` INT(20) NOT NULL,
  PRIMARY KEY (`user_repertory_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `user_repertory_info` VALUES ('1', '1', '1','1');


-- ----------------------------
-- 创建user_tank_info表
-- ----------------------------
DROP TABLE IF EXISTS `user_tank_info`;
CREATE TABLE `user_tank_info` (
  `user_id` INT(20) NOT NULL,
  `user_tank_info_id` INT(20) NOT NULL,
  `equpment_turret_id` INT(20) NOT NULL,
  `equpment_whell_id` INT(20) NOT NULL,
  `equpment_engine_id` INT(20) NOT NULL,
  `equpment_armour_id` INT(20) NOT NULL,
  `HP` INT(20) DEFAULT NULL,
  `fire` INT(20) DEFAULT NULL,
  `shotspeed` INT(20) DEFAULT NULL,
  `tankspeed` INT(20) DEFAULT NULL,
  PRIMARY KEY (`user_tank_info_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `user_tank_info` VALUES ('1', '1', '1','1', '1','1', '20','40', '90','60');

-- ----------------------------
-- 创建user_map_info表
-- ----------------------------
DROP TABLE IF EXISTS `user_map_info`;
CREATE TABLE `user_map_info` (
  `user_id` INT(20) NOT NULL,
  `user_map_id` INT(20) NOT NULL,
  `HP` INT(20) DEFAULT NULL,
  `fire` INT(20) DEFAULT NULL,
  `shotspeed` INT(20) DEFAULT NULL,
  `tankspeed` INT(20) DEFAULT NULL,
  PRIMARY KEY (`user_map_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `user_map_info` VALUES ('1', '1',  '20','40', '90','60');


-- ----------------------------
-- 创建uesr_game_info表
-- ----------------------------
DROP TABLE IF EXISTS `user_game_info`;
CREATE TABLE `user_game_info` (
  `user_game_info_id` INT(20) NOT NULL,
  `user_id` INT(20) NOT NULL,
  `user_game_grade` INT(20) DEFAULT NULL,
  `user_game_money` INT(20) DEFAULT NULL,
  `user_game_diamond` INT(20) DEFAULT NULL,
  `user_game_oil` INT(20) DEFAULT NULL,
  `user_game_model` INT(20) DEFAULT NULL,
  `user_game_status` INT(20) DEFAULT NULL,
  `user_repertory_id` INT(20) DEFAULT NULL,
  `user_tank_id` INT(20) DEFAULT NULL,
  PRIMARY KEY (`user_game_info_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `user_game_info` VALUES ('1', '1',  '1', '1', '1', '1', '1', '1', '1', '1');

-- ----------------------------
-- 创建user_login_info表
-- ----------------------------
DROP TABLE IF EXISTS `user_login_info`;
CREATE TABLE `user_login_info` (
  `user_id` INT(20) NOT NULL,
  `user_game_info_id` INT(20) NOT NULL,
  `user_login_name` VARCHAR(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_login_password` VARCHAR(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_login_tel` VARCHAR(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_login_email` VARCHAR(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `uesr_login_game_info` VARCHAR(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_game_info_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `user_login_info` VALUES ('1', '1',  'qiao', '123456', '18322693235', '790872612@qq.com', '1');

-- ----------------------------
-- 创建tbl_user表  用于测试
-- ----------------------------

DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `uid` INT(20) NOT NULL,
  `uname` VARCHAR(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `upassward` VARCHAR(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `tbl_user` VALUES ('1','qiao', '123456');

