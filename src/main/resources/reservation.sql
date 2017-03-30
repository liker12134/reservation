/*
Navicat MySQL Data Transfer

Source Server         : 脚边的服务器
Source Server Version : 50716
Source Host           : 10.82.60.128:3306
Source Database       : reservation

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2016-12-03 17:22:39
CREATE USER 'reservation'@'%' IDENTIFIED BY 'reservation';
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for res_class
-- ----------------------------
DROP TABLE IF EXISTS `res_class`;
CREATE TABLE `res_class` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(40) NOT NULL,
  `cmax` int(11) NOT NULL,
  `cremark` varchar(16) NOT NULL,
  `cstatus` varchar(1) NOT NULL DEFAULT '1',
  `coperatorid` int(11) DEFAULT NULL,
  `cmodifiedtime` datetime DEFAULT NULL,
  PRIMARY KEY (`cid`),
  KEY `FK6D7ECD1913A5C516` (`coperatorid`),
  CONSTRAINT `FK6D7ECD1913A5C516` FOREIGN KEY (`coperatorid`) REFERENCES `res_manager` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_class
-- ----------------------------
INSERT INTO `res_class` VALUES ('1', 'E506', '50', '2', '1', '1', '2016-12-03 13:52:41');
INSERT INTO `res_class` VALUES ('2', 'E203', '49', '1', '1', '1', '2016-12-03 17:00:22');
INSERT INTO `res_class` VALUES ('3', 'E210', '50', '1', '1', '1', '2016-12-03 13:57:06');
INSERT INTO `res_class` VALUES ('4', 'E412', '50', '1', '1', '1', '2016-12-03 13:57:06');

-- ----------------------------
-- Table structure for res_config
-- ----------------------------
DROP TABLE IF EXISTS `res_config`;
CREATE TABLE `res_config` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `ckey` varchar(20) DEFAULT NULL,
  `cvalue` text,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_config
-- ----------------------------
INSERT INTO `res_config` VALUES ('1', 'notice', '暂无');
INSERT INTO `res_config` VALUES ('2', 'totalDays', '12');

-- ----------------------------
-- Table structure for res_manager
-- ----------------------------
DROP TABLE IF EXISTS `res_manager`;
CREATE TABLE `res_manager` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `maccount` varchar(40) NOT NULL,
  `mpassword` varchar(40) NOT NULL,
  `mremark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_manager
-- ----------------------------
INSERT INTO `res_manager` VALUES ('1', 'admin', '319485E9B5447852E46A6F2DD02F9DAE', '活动室预约系统默认管理员');

-- ----------------------------
-- Table structure for res_reservation
-- ----------------------------
DROP TABLE IF EXISTS `res_reservation`;
CREATE TABLE `res_reservation` (
  `rid` bigint(20) NOT NULL AUTO_INCREMENT,
  `sid` bigint(20) DEFAULT NULL,
  `tid` int(11) NOT NULL,
  `cid` int(11) DEFAULT NULL,
  `cname` varchar(40) NOT NULL COMMENT 'ÈßÓà×Ö¶Î',
  `rtargetdate` datetime NOT NULL,
  `rweeknum` int(11) DEFAULT NULL,
  `tbegintime` datetime NOT NULL COMMENT 'ÈßÓà×Ö¶Î',
  `tendtime` datetime NOT NULL COMMENT 'ÈßÓà×Ö¶Î',
  `rspecificbegintime` datetime DEFAULT NULL,
  `rspecificendtime` datetime DEFAULT NULL,
  `rrecordcreatetime` datetime DEFAULT NULL,
  `rapplyunit` varchar(40) DEFAULT NULL,
  `rchargeperson` varchar(20) NOT NULL,
  `rchargetelephone` varchar(20) NOT NULL,
  `rcontactsperson` varchar(20) DEFAULT NULL,
  `rcontacttelephone` varchar(20) DEFAULT NULL,
  `rspecificcontent` varchar(1000) DEFAULT NULL,
  `rstatus` varchar(1) NOT NULL,
  PRIMARY KEY (`rid`),
  KEY `FK_Reference_1` (`sid`),
  KEY `FKC2D36AADDDD8CC4` (`tid`),
  KEY `FKC2D36AAD8EEC77FD` (`cid`),
  CONSTRAINT `FKC2D36AAD8EEC77FD` FOREIGN KEY (`cid`) REFERENCES `res_class` (`cid`),
  CONSTRAINT `FKC2D36AADDDD8CC4` FOREIGN KEY (`tid`) REFERENCES `res_timequantum` (`tid`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`sid`) REFERENCES `res_student` (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_reservation
-- ----------------------------
INSERT INTO `res_reservation` VALUES ('1', '1', '2', '2', 'E203', '2016-12-06 00:00:01', '7', '2016-12-06 13:00:00', '2016-12-06 16:30:00', '2016-12-06 13:00:00', '2016-12-06 16:30:00', '2016-12-03 17:07:43', '1603', '15313926025', '15313926025', '15313926025', '15313926025', '', '1');

-- ----------------------------
-- Table structure for res_student
-- ----------------------------
DROP TABLE IF EXISTS `res_student`;
CREATE TABLE `res_student` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT,
  `sno` varchar(40) NOT NULL,
  `spassword` varchar(40) NOT NULL,
  `sname` varchar(20) NOT NULL,
  `sremark` varchar(200) DEFAULT NULL,
  `slastlogin` datetime DEFAULT NULL,
  `slogincount` int(11) DEFAULT NULL,
  `status` varchar(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_student
-- ----------------------------
INSERT INTO `res_student` VALUES ('1', '1603', '1603', '1603', '', '2016-12-03 17:06:49', '1', '1');

-- ----------------------------
-- Table structure for res_timequantum
-- ----------------------------
DROP TABLE IF EXISTS `res_timequantum`;
CREATE TABLE `res_timequantum` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `tbegintime` datetime NOT NULL,
  `tendtime` datetime NOT NULL,
  `teffectivebegintime` datetime DEFAULT NULL,
  `teffectiveendtime` datetime DEFAULT NULL,
  `toperatorid` int(11) DEFAULT NULL,
  `tmodifiedtime` datetime DEFAULT NULL,
  PRIMARY KEY (`tid`),
  KEY `FKD8A568FF6D5A3E7` (`toperatorid`),
  CONSTRAINT `FKD8A568FF6D5A3E7` FOREIGN KEY (`toperatorid`) REFERENCES `res_manager` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_timequantum
-- ----------------------------
INSERT INTO `res_timequantum` VALUES ('1', '1970-01-01 08:30:00', '1970-01-01 11:30:00', '1970-01-01 00:00:00', '2999-12-31 23:59:59', '1', '2016-12-03 13:55:41');
INSERT INTO `res_timequantum` VALUES ('2', '1970-01-01 13:00:00', '1970-01-01 16:30:00', '1970-01-01 00:00:00', '2999-12-31 23:59:59', '1', '2016-12-03 13:55:41');
INSERT INTO `res_timequantum` VALUES ('3', '1970-01-01 18:00:00', '1970-01-01 21:00:00', '1970-01-01 00:00:00', '2999-12-31 23:59:59', '1', '2016-12-03 13:55:41');
