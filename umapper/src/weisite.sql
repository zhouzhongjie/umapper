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
-- Table structure for `template`
-- ----------------------------
DROP TABLE IF EXISTS `template`;
CREATE TABLE `template` (
  `tid` int(11) NOT NULL,
  `tname` varchar(255) NOT NULL,
  `tdescrption` varchar(10240) DEFAULT NULL,
  `tpath` varchar(255) NOT NULL,
  `createUser` varchar(255) DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of template
-- ----------------------------
INSERT INTO `template` VALUES ('1', '单图文', '一张图，简单明了', 'singlepicture.ftl', 'dqq', '2013-11-15 23:12:29');
INSERT INTO `template` VALUES ('2', '九宫格', '内容丰富', '9grid.ftl', 'dqq', '2013-11-15 23:13:05');

-- ----------------------------
--  Records of `weisite`
-- ----------------------------
BEGIN;
INSERT INTO `weisite` VALUES ('201311092321000', '首页', '十宫格', 'http://localhost:8080/index.jsp', null, null), ('201311092321001', '红烧肉', '单图文', 'http://localhost:8080/weisite.jsp', null, null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
