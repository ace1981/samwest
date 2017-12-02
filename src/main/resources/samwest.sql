/*
Navicat MySQL Data Transfer

Source Server Version : 50555
Source Database       : samwest

Target Server Type    : MYSQL
Target Server Version : 50555
File Encoding         : 65001

Date: 2017-12-03 01:18:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` varchar(36) DEFAULT NULL,
  `title` varchar(90) DEFAULT NULL,
  `content` varchar(8000) DEFAULT NULL,
  `articletime` timestamp NULL DEFAULT NULL,
  `tags` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of article
-- ----------------------------

-- ----------------------------
-- Table structure for `userbasicinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userbasicinfo`;
CREATE TABLE `userbasicinfo` (
  `id` varchar(36) DEFAULT NULL,
  `username` varchar(36) DEFAULT NULL,
  `userpassword` varchar(64) DEFAULT NULL,
  `usernick` varchar(20) DEFAULT NULL,
  `userbirthyear` int(11) DEFAULT NULL,
  `useremail` varchar(80) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of userbasicinfo
-- ----------------------------
