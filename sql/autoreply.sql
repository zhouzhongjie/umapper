/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50172
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50172
File Encoding         : 65001

Date: 2013-11-20 22:20:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `autoreply`
-- ----------------------------
DROP TABLE IF EXISTS `autoreply`;
CREATE TABLE `autoreply` (
  `user` varchar(100) NOT NULL COMMENT '商户的标识',
  `key` varchar(20) NOT NULL COMMENT '关键字',
  `matchrule` enum('approximate','accurate') NOT NULL COMMENT '匹配规则，0为精确匹配，1为模糊匹配',
  `type` enum('music','text','voice','news') NOT NULL COMMENT '回复消息类型，有几种：news,music,text',
  `content` mediumtext NOT NULL COMMENT '回复的内容，为json格式的字符串',
  PRIMARY KEY (`user`,`key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of autoreply
-- ----------------------------
