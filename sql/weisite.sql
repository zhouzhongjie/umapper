/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50610
 Source Host           : 127.0.0.1
 Source Database       : test

 Target Server Type    : MySQL
 Target Server Version : 50610
 File Encoding         : utf-8

 Date: 11/12/2013 21:57:56 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `weisite`
-- ----------------------------
DROP TABLE IF EXISTS `weisite`;
CREATE TABLE `weisite` (
  `id` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `type` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `picture` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `weisite`
-- ----------------------------
BEGIN;
INSERT INTO `weisite` VALUES ('201311092321000', '首页', '十宫格', 'http://localhost:8080/index.jsp', null, null), ('201311092321001', '红烧肉', '单图文', 'http://localhost:8080/weisite.jsp', null, null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
