/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50615
 Source Host           : localhost
 Source Database       : stm

 Target Server Type    : MySQL
 Target Server Version : 50615
 File Encoding         : utf-8

 Date: 05/27/2014 13:24:47 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `auth_t`
-- ----------------------------
DROP TABLE IF EXISTS `auth_t`;
CREATE TABLE `auth_t` (
  `auth_id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_code` varchar(30) DEFAULT NULL,
  `auth_name` varchar(50) DEFAULT NULL,
  `auth_type` varchar(5) DEFAULT NULL,
  `auth_parent_code` varchar(50) NOT NULL,
  `menu_addr` varchar(255) DEFAULT NULL,
  `sort_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`auth_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `auth_t`
-- ----------------------------
BEGIN;
INSERT INTO `auth_t` VALUES ('5', 'system', '系统管理', 'A', '', '', '1'), ('6', 'user', '用户管理', 'A', 'system', '/user/user', '3'), ('7', 'auth', '权限管理', 'A', 'system', '/user/auth', '4'), ('8', 'dept', '角色管理', 'A', 'system', '/user/dept', '2'), ('9', 'dept:view', '角色查看', 'B', 'dept', '', '5'), ('10', 'dept:edit', '角色编辑', 'B', 'dept', '', '6'), ('11', 'user:view', '用户查看', 'B', 'user', '', '7'), ('12', 'user:edit', '用户编辑', 'B', 'user', '', '8'), ('13', 'auth:view', '权限查看', 'B', 'auth', '', '9'), ('14', 'auth:edit', '权限编辑', 'B', 'auth', '', '10');
COMMIT;

-- ----------------------------
--  Table structure for `dept_auth`
-- ----------------------------
DROP TABLE IF EXISTS `dept_auth`;
CREATE TABLE `dept_auth` (
  `dept_id` int(11) DEFAULT NULL,
  `auth_code` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `dept_auth`
-- ----------------------------
BEGIN;
INSERT INTO `dept_auth` VALUES ('4', 'system'), ('4', 'user'), ('4', 'user:view'), ('4', 'user:edit'), ('4', 'auth'), ('4', 'auth:view'), ('4', 'auth:edit'), ('4', 'dept'), ('4', 'dept:view'), ('4', 'dept:edit'), ('5', 'system'), ('5', 'auth'), ('5', 'auth:view'), ('5', 'auth:edit'), ('6', 'system'), ('6', 'user'), ('6', 'user:view'), ('6', 'user:edit'), ('6', 'auth'), ('6', 'auth:view'), ('6', 'auth:edit'), ('6', 'dept'), ('6', 'dept:view'), ('6', 'dept:edit');
COMMIT;

-- ----------------------------
--  Table structure for `dept_t`
-- ----------------------------
DROP TABLE IF EXISTS `dept_t`;
CREATE TABLE `dept_t` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(45) DEFAULT NULL,
  `parent_dept_id` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `dept_t`
-- ----------------------------
BEGIN;
INSERT INTO `dept_t` VALUES ('4', '超级管理员', null, null, null, null), ('5', '普通用户', null, null, null, null), ('6', '角色管理', null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `user_dept`
-- ----------------------------
DROP TABLE IF EXISTS `user_dept`;
CREATE TABLE `user_dept` (
  `user_id` int(11) NOT NULL,
  `dept_id` int(11) NOT NULL,
  KEY `fk_user_dept_user_t_idx` (`user_id`),
  KEY `fk_user_dept_dept_t1` (`dept_id`),
  CONSTRAINT `fk_user_dept_dept_t1` FOREIGN KEY (`dept_id`) REFERENCES `dept_t` (`dept_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_dept_user_t` FOREIGN KEY (`user_id`) REFERENCES `user_t` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_dept`
-- ----------------------------
BEGIN;
INSERT INTO `user_dept` VALUES ('11', '4'), ('11', '5'), ('11', '6');
COMMIT;

-- ----------------------------
--  Table structure for `user_t`
-- ----------------------------
DROP TABLE IF EXISTS `user_t`;
CREATE TABLE `user_t` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `login_name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_t`
-- ----------------------------
BEGIN;
INSERT INTO `user_t` VALUES ('11', 'admin', 'admin', 'admin', '');
COMMIT;

