/*
Navicat MySQL Data Transfer

Source Server         : formcreator
Source Server Version : 50534
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50534
File Encoding         : 65001

Date: 2013-11-17 21:02:30
*/

SET FOREIGN_KEY_CHECKS=0;

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
