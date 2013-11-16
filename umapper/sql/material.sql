/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50172
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50172
File Encoding         : 65001

Date: 2013-11-16 00:05:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `material`
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `url` varchar(255) DEFAULT NULL COMMENT '素材的URL',
  `bucket` varchar(255) DEFAULT NULL COMMENT '素材在BCS上的路径',
  `filename` varchar(255) DEFAULT NULL COMMENT ' 素材的BCS中的名字',
  `time` int(11) DEFAULT NULL COMMENT '素材创建的时间'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of material
-- ----------------------------
