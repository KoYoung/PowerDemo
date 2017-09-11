/*
MySQL Data Transfer
Source Host: localhost
Source Database: test
Target Host: localhost
Target Database: test
Date: 2014-6-21 11:55:35
*/


SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for mem_activity
-- ----------------------------
DROP TABLE IF EXISTS `mem_activity`;
CREATE TABLE `mem_activity` (
  `act_id` int(11) NOT NULL AUTO_INCREMENT,
  `act_beginTime` datetime DEFAULT NULL,
  `act_endTime` datetime DEFAULT NULL,
  `act_name` varchar(50) DEFAULT NULL,
  `act_remark` longtext,
  PRIMARY KEY (`act_id`),
  UNIQUE KEY `act_id` (`act_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mem_activitydetail
-- ----------------------------
DROP TABLE IF EXISTS `mem_activitydetail`;
CREATE TABLE `mem_activitydetail` (
  `act_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `act_id` int(11) DEFAULT NULL,
  `discount` float DEFAULT NULL,
  `goods_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`act_detail_id`),
  UNIQUE KEY `act_detail_id` (`act_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mem_card
-- ----------------------------
DROP TABLE IF EXISTS `mem_card`;
CREATE TABLE `mem_card` (
  `card_no` varchar(9) NOT NULL,
  `card_indate` datetime DEFAULT NULL,
  `card_Level` int(11) DEFAULT NULL,
  `card_Money` float DEFAULT NULL,
  `card_pass` varchar(20) DEFAULT NULL,
  `card_Remark` int(11) DEFAULT NULL,
  `card_score` int(11) DEFAULT NULL,
  `card_Status` int(11) DEFAULT NULL,
  `card_Type` int(11) DEFAULT NULL,
  `create_Time` datetime DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `mem_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`card_no`),
  UNIQUE KEY `card_no` (`card_no`),
  KEY `FKD91EEB3A331768B4` (`mem_id`),
  CONSTRAINT `FKD91EEB3A331768B4` FOREIGN KEY (`mem_id`) REFERENCES `mem_member` (`mem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mem_gift
-- ----------------------------
DROP TABLE IF EXISTS `mem_gift`;
CREATE TABLE `mem_gift` (
  `gift_id` int(11) NOT NULL AUTO_INCREMENT,
  `deal_mark` int(11) DEFAULT NULL,
  `gift_Name` varchar(50) DEFAULT NULL,
  `gift_pic` varchar(50) DEFAULT NULL,
  `gift_integral` int(11) DEFAULT NULL,
  PRIMARY KEY (`gift_id`),
  UNIQUE KEY `gift_id` (`gift_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mem_member
-- ----------------------------
DROP TABLE IF EXISTS `mem_member`;
CREATE TABLE `mem_member` (
  `mem_id` int(11) NOT NULL AUTO_INCREMENT,
  `handlerName` varchar(20) DEFAULT NULL,
  `mem_Address` varchar(50) DEFAULT NULL,
  `mem_Age` int(11) DEFAULT NULL,
  `mem_Birthday` datetime DEFAULT NULL,
  `mem_Email` varchar(50) DEFAULT NULL,
  `mem_name` varchar(50) DEFAULT NULL,
  `mem_pic` varchar(50) DEFAULT NULL,
  `mem_Qq` varchar(50) DEFAULT NULL,
  `mem_sex` int(11) DEFAULT NULL,
  `mem_Status` int(11) DEFAULT NULL,
  `mem_Tel` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`mem_id`),
  UNIQUE KEY `mem_id` (`mem_id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mem_order
-- ----------------------------
DROP TABLE IF EXISTS `mem_order`;
CREATE TABLE `mem_order` (
  `order_no` varchar(20) NOT NULL,
  `card_no` varchar(20) DEFAULT NULL,
  `order_Allprice` float DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  `order_integral` int(11) DEFAULT NULL,
  `order_remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`order_no`),
  UNIQUE KEY `order_no` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mem_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `mem_order_detail`;
CREATE TABLE `mem_order_detail` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `detail_num` int(11) DEFAULT NULL,
  `detail_price` float DEFAULT NULL,
  `detail_rebate` float DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  `order_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`detail_id`),
  UNIQUE KEY `detail_id` (`detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3605 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mem_payrecord
-- ----------------------------
DROP TABLE IF EXISTS `mem_payrecord`;
CREATE TABLE `mem_payrecord` (
  `pay_Id` int(11) NOT NULL AUTO_INCREMENT,
  `card_no` varchar(9) DEFAULT NULL,
  `pay_money` float DEFAULT NULL,
  `pay_time` datetime DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`pay_Id`),
  UNIQUE KEY `pay_Id` (`pay_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3604 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mem_rank
-- ----------------------------
DROP TABLE IF EXISTS `mem_rank`;
CREATE TABLE `mem_rank` (
  `rank_Id` int(11) NOT NULL AUTO_INCREMENT,
  `discount` float DEFAULT NULL,
  `rank_Name` varchar(20) DEFAULT NULL,
  `scoreLimit` int(11) DEFAULT NULL,
  PRIMARY KEY (`rank_Id`),
  UNIQUE KEY `rank_Id` (`rank_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mem_score_rule
-- ----------------------------
DROP TABLE IF EXISTS `mem_score_rule`;
CREATE TABLE `mem_score_rule` (
  `rule_Id` int(11) NOT NULL AUTO_INCREMENT,
  `money` float DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `type_Id`varchar(20) DEFAULT NULL,
  PRIMARY KEY (`rule_Id`),
  UNIQUE KEY `rule_Id` (`rule_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for share_actioninfo
-- ----------------------------
DROP TABLE IF EXISTS `share_actioninfo`;
CREATE TABLE `share_actioninfo` (
  `action_id` int(11) NOT NULL AUTO_INCREMENT,
  `action_parent` int(11) DEFAULT NULL,
  `action_pic` varchar(50) DEFAULT NULL,
  `action_sort` int(11) DEFAULT NULL,
  `action_url` varchar(50) DEFAULT NULL,
  `action_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`action_id`),
  UNIQUE KEY `action_id` (`action_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for share_admin
-- ----------------------------
DROP TABLE IF EXISTS `share_admin`;
CREATE TABLE `share_admin` (
  `admin_name` varchar(30) NOT NULL,
  `admin_date` datetime DEFAULT NULL,
  `admin_password` varchar(20) DEFAULT NULL,
  `admin_remark` varchar(50) DEFAULT NULL,
  `admin_status` int(11) DEFAULT NULL,
  `staff_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`admin_name`),
  UNIQUE KEY `admin_name` (`admin_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for share_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `share_admin_role`;
CREATE TABLE `share_admin_role` (
  `role_id` int(11) NOT NULL,
  `admin_name` varchar(30) NOT NULL,
  PRIMARY KEY (`role_id`,`admin_name`),
  KEY `FK1403A3C66F42ADD2` (`admin_name`),
  KEY `FK1403A3C6A0B13F2` (`role_id`),
  CONSTRAINT `FK1403A3C66F42ADD2` FOREIGN KEY (`admin_name`) REFERENCES `share_admin` (`admin_name`),
  CONSTRAINT `FK1403A3C6A0B13F2` FOREIGN KEY (`role_id`) REFERENCES `share_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for share_data_base
-- ----------------------------
DROP TABLE IF EXISTS `share_data_base`;
CREATE TABLE `share_data_base` (
  `base_id` int(11) NOT NULL AUTO_INCREMENT,
  `base_no` int(11) NOT NULL,
  `base_name` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `base_parent` int(11) DEFAULT NULL,
  PRIMARY KEY (`base_id`),
  UNIQUE KEY `base_id` (`base_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for share_deptement
-- ----------------------------
DROP TABLE IF EXISTS `share_deptement`;
CREATE TABLE `share_deptement` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(20) DEFAULT NULL,
  `dept_parent` int(11) DEFAULT NULL,
  `dept_sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`dept_id`),
  UNIQUE KEY `dept_id` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for share_per_action
-- ----------------------------
DROP TABLE IF EXISTS `share_per_action`;
CREATE TABLE `share_per_action` (
  `per_id` int(11) NOT NULL,
  `action_id` int(11) NOT NULL,
  PRIMARY KEY (`per_id`,`action_id`),
  KEY `FKD90773F872E56BE` (`per_id`),
  KEY `FKD90773F8DB583BA0` (`action_id`),
  CONSTRAINT `FKD90773F872E56BE` FOREIGN KEY (`per_id`) REFERENCES `share_permitinfo` (`per_id`),
  CONSTRAINT `FKD90773F8DB583BA0` FOREIGN KEY (`action_id`) REFERENCES `share_actioninfo` (`action_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for share_permitinfo
-- ----------------------------
DROP TABLE IF EXISTS `share_permitinfo`;
CREATE TABLE `share_permitinfo` (
  `per_id` int(11) NOT NULL AUTO_INCREMENT,
  `per_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`per_id`),
  UNIQUE KEY `per_id` (`per_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for share_role
-- ----------------------------
DROP TABLE IF EXISTS `share_role`;
CREATE TABLE `share_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for share_role_per
-- ----------------------------
DROP TABLE IF EXISTS `share_role_per`;
CREATE TABLE `share_role_per` (
  `role_id` int(11) NOT NULL,
  `per_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`per_id`),
  KEY `FKEA83823472E56BE` (`per_id`),
  KEY `FKEA838234A0B13F2` (`role_id`),
  CONSTRAINT `FKEA83823472E56BE` FOREIGN KEY (`per_id`) REFERENCES `share_permitinfo` (`per_id`),
  CONSTRAINT `FKEA838234A0B13F2` FOREIGN KEY (`role_id`) REFERENCES `share_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for share_staff
-- ----------------------------
DROP TABLE IF EXISTS `share_staff`;
CREATE TABLE `share_staff` (
  `staff_Id` int(11) NOT NULL AUTO_INCREMENT,
  `remark` varchar(100) DEFAULT NULL,
  `staff_address` varchar(50) DEFAULT NULL,
  `staff_age` int(11) DEFAULT NULL,
  `staff_birthday` datetime DEFAULT NULL,
  `staff_level` int(11) DEFAULT NULL,
  `staff_name` varchar(50) DEFAULT NULL,
  `staff_pic` varchar(50) DEFAULT NULL,
  `staff_sex` int(11) DEFAULT NULL,
  `staff_tel` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `dept_Id` int(11) DEFAULT NULL,
  `adminName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`staff_Id`),
  UNIQUE KEY `staff_Id` (`staff_Id`),
  KEY `FK1842D2803F0D8E7B` (`dept_Id`),
  CONSTRAINT `FK1842D2803F0D8E7B` FOREIGN KEY (`dept_Id`) REFERENCES `share_deptement` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Procedure structure for proc_statisties
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_statisties`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_statisties`(in parameter integer)
begin
if parameter=0 then
select * from sail_order ;
end if;
end;;
DELIMITER ;


-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `mem_activity` VALUES ('1', '2014-05-04 15:34:40', '2014-04-08 15:34:48', '欢庆三天乐', '全场打折');
INSERT INTO `mem_activity` VALUES ('2', '2014-05-14 15:36:00', '2014-05-15 15:36:15', '跳楼大甩卖', '低到无法想象');
INSERT INTO `mem_activity` VALUES ('3', '2014-03-04 15:37:59', '2014-03-06 15:38:07', '清仓两天', '低价');
INSERT INTO `mem_activitydetail` VALUES ('1', '1', '9', '1');
INSERT INTO `mem_activitydetail` VALUES ('2', '2', '9.5', '2');
INSERT INTO `mem_activitydetail` VALUES ('3', '3', '9.8', '3');
INSERT INTO `mem_card` VALUES ('10001', '2013-05-13 15:15:39', '100201', '6000', '11111111', '1', '600', '100301', '100401', '2015-05-14 15:17:26', null, '101');
INSERT INTO `mem_card` VALUES ('10002', '2013-09-09 15:19:01', '100202', '5000', '22222222', '1', '500', '100302', '100402', '2014-08-20 15:21:04', null, '102');
INSERT INTO `mem_card` VALUES ('10003', '2014-01-20 15:21:33', '100203', '5500', '33333333', '1', '550', '100303', '100403', '2014-11-06 15:22:09', null, '103');
INSERT INTO `mem_gift` VALUES ('1', '1', '公仔', null,'500');
INSERT INTO `mem_gift` VALUES ('2', '1', '熊仔', null,'500');
INSERT INTO `mem_member` VALUES ('101', '张三', '河南郑州', '20', '1994-05-05 00:00:00', '@qq123231234.com', '孙魁', null, '123231234', '100101', '1', '12323456789');
INSERT INTO `mem_member` VALUES ('102', '李四', '湖北宜昌', '20', '1994-10-10 00:00:00', '@qq1234567897.com', '任天亮', null, '1234567897', '100101', '1', '15029823456');
INSERT INTO `mem_member` VALUES ('103', '王五', '河南许昌', '21', '1993-02-23 00:00:00', '@qq123333297.com', '孙飞虎', null, '123333297', '100101', '2', '23567456783');
INSERT INTO `mem_member` VALUES ('104', '赵六', '河北邢台', '22', '1992-12-25 00:00:00', '@qq123456975.com', '李岩', null, '123456975', '100101', '2', '3568908765');
INSERT INTO `mem_order` VALUES ('111', null, '600', '2014-06-09 00:00:00', '5485564', '消费600元');
INSERT INTO `mem_order` VALUES ('3601', '10001', '5000', '2014-06-17 14:43:29', '500', '消费5000元');
INSERT INTO `mem_order` VALUES ('3602', '10002', '6000', '2013-12-01 14:44:08', '600', '消费6000元');
INSERT INTO `mem_order` VALUES ('3603', '10003', '4000', '2013-03-04 14:45:08', '400', '消费4000元');
INSERT INTO `mem_order` VALUES ('3604', '10004', '2000', '2013-11-10 14:45:56', '200', '消费2000元');
INSERT INTO `mem_order_detail` VALUES ('3601', '44', '200', '9.5', '4001', '3601');
INSERT INTO `mem_order_detail` VALUES ('3602', '65', '60', '9.9', '4002', '3602');
INSERT INTO `mem_order_detail` VALUES ('3603', '70', '70', '9.8', '4003', '3603');
INSERT INTO `mem_order_detail` VALUES ('3604', '80', '80', '9.6', '4004', '3604');
INSERT INTO `mem_payrecord` VALUES ('3601', '10001', '500', '2014-04-15 15:53:19', '50');
INSERT INTO `mem_payrecord` VALUES ('3602', '10002', '400', '2014-04-14 15:54:41', '40');
INSERT INTO `mem_payrecord` VALUES ('3603', '1003', '300', '2014-06-09 15:55:07', '30');
INSERT INTO `mem_rank` VALUES ('100203', '8.5', '金卡', null);
INSERT INTO `mem_rank` VALUES ('100202', '9', '银卡', null);
INSERT INTO `mem_rank` VALUES ('100201', '9.5', '铜卡', null);
INSERT INTO `mem_score_rule` VALUES ('1', '8', '1', '100203');
INSERT INTO `mem_score_rule` VALUES ('2', '9', '1', '100202');
INSERT INTO `mem_score_rule` VALUES ('3', '10', '1', '100201');
INSERT INTO `share_actioninfo` VALUES ('1', '0', 'icon-reload', '6', '', '系统管理');
INSERT INTO `share_actioninfo` VALUES ('3', '1', 'icon-reload', '7', 'role/permitinfo.jsp', '权限管理');
INSERT INTO `share_actioninfo` VALUES ('5', '1', 'icon-reload', '2', 'role/roleinfo.jsp', '角色管理');
INSERT INTO `share_actioninfo` VALUES ('6', '1', 'icon-reload', '5', 'role/actioninfo.jsp', '菜单管理');
INSERT INTO `share_actioninfo` VALUES ('7', '1', 'icon-reload', '4', 'role/actioninfo.jsp', '测试菜单');
INSERT INTO `share_actioninfo` VALUES ('8', '7', 'icon-reload', '5', 'role/abc.jsp', '测试');
INSERT INTO `share_actioninfo` VALUES ('10', '0', 'icon-no', '9', '', '会员管理');
INSERT INTO `share_actioninfo` VALUES ('12', '1', 'icon-ok', '1', 'dept/staffinfo.jsp', '员工管理');
INSERT INTO `share_actioninfo` VALUES ('20', '1', 'icon-ok', '1', 'role/adminsinfo.jsp','员工角色配置');
INSERT INTO `share_actioninfo` VALUES ('13', '10', 'icon-cancel', '2', 'mem/MemberInfo.jsp', '会员查看');
INSERT INTO `share_actioninfo` VALUES ('14', '0', 'icon-reload', '1', '', '订单管理');
INSERT INTO `share_actioninfo` VALUES ('15', '14', 'icon-ok', '1', 'order/MemOrderInfo.jsp', '订单查看');
INSERT INTO `share_actioninfo` VALUES ('16', '1', 'icon-reload', '1', '', '规则管理');
INSERT INTO `share_actioninfo` VALUES ('17', '16', 'icon-reload', '1', 'mem/queryMemScoreRank.jsp', '积分规则管理');
INSERT INTO `share_actioninfo` VALUES ('18', '16', 'icon-reload', '1', 'mem/queryMemRank.jsp', '折扣规则管理');
INSERT INTO `share_admin` VALUES ('admin', '2014-06-17 14:25:09', 'admin', null, null, null);
INSERT INTO `share_admin` VALUES ('zhangsan', '2014-06-25 18:55:13', 'zhangsan', null, null, null);
INSERT INTO `share_admin_role` VALUES ('1', 'admin');
INSERT INTO `share_admin_role` VALUES ('2', 'zhangsan');
INSERT INTO `share_data_base` VALUES ('1', '1001', '性别', null);
INSERT INTO `share_data_base` VALUES ('2', '100101', '男', '1001');
INSERT INTO `share_data_base` VALUES ('5', '100102', '女', '1001');
INSERT INTO `share_data_base` VALUES ('7', '1002', '卡等级', null);
INSERT INTO `share_data_base` VALUES ('8', '100201', '普通卡', '1002');
INSERT INTO `share_data_base` VALUES ('9', '100202', '金卡', '1002');
INSERT INTO `share_data_base` VALUES ('10', '100203', '银卡', '1002');
INSERT INTO `share_data_base` VALUES ('11', '1003', '卡状态', null);
INSERT INTO `share_data_base` VALUES ('12', '100301', '正常', '1003');
INSERT INTO `share_data_base` VALUES ('13', '100302', '挂失', '1003');
INSERT INTO `share_data_base` VALUES ('14', '100303', '失效', '1003');
INSERT INTO `share_data_base` VALUES ('15', '1004', '卡类型', null);
INSERT INTO `share_data_base` VALUES ('16', '100401', '充值积分卡', '1004');
INSERT INTO `share_data_base` VALUES ('17', '100402', '积分卡', '1004');
INSERT INTO `share_data_base` VALUES ('18', '100403', '充值卡', '1004');
INSERT INTO `share_data_base` VALUES ('19', '1005', '积分规则类型', null);
INSERT INTO `share_data_base` VALUES ('20', '100501', '会员充值', '1005');
INSERT INTO `share_data_base` VALUES ('21', '100502', '正常消费', '1005');
INSERT INTO `share_data_base` VALUES ('24', '1006', '出入库状态', null);
INSERT INTO `share_data_base` VALUES ('25', '100601', '未审批', '1006');
INSERT INTO `share_data_base` VALUES ('26', '100602', '已完成', '1006');
INSERT INTO `share_data_base` VALUES ('27', '100603', '已审批', '1006');
INSERT INTO `share_deptement` VALUES ('1', '科特销售管理公司', null, null);
INSERT INTO `share_deptement` VALUES ('2', 'GS334大酒店', null, null);
INSERT INTO `share_deptement` VALUES ('3', '市场营销公司', null, null);
INSERT INTO `share_deptement` VALUES ('4', '销售一部00', null, null);
INSERT INTO `share_deptement` VALUES ('5', '销售二部', '1', null);
INSERT INTO `share_deptement` VALUES ('6', '人事部', '1', null);
INSERT INTO `share_deptement` VALUES ('7', '人事部', '2', null);
INSERT INTO `share_deptement` VALUES ('8', '销售部', '2', null);
INSERT INTO `share_deptement` VALUES ('9', '公关部', '2', null);
INSERT INTO `share_deptement` VALUES ('10', '市场部', '3', null);
INSERT INTO `share_deptement` VALUES ('11', '客户挖掘部', '3', null);
INSERT INTO `share_deptement` VALUES ('12', '客户评估组', '3', null);
INSERT INTO `share_deptement` VALUES ('13', '新增部门', null, null);
INSERT INTO `share_deptement` VALUES ('14', '新增部门', '1', null);
INSERT INTO `share_deptement` VALUES ('15', '新增部门', '1', null);
INSERT INTO `share_deptement` VALUES ('16', '新增部门555', '5', null);
INSERT INTO `share_deptement` VALUES ('18', '新增部门123', null, null);
INSERT INTO `share_per_action` VALUES ('2', '10');
INSERT INTO `share_per_action` VALUES ('2', '13');
INSERT INTO `share_permitinfo` VALUES ('1', '系统权限');
INSERT INTO `share_permitinfo` VALUES ('2', '普通权限');
INSERT INTO `share_permitinfo` VALUES ('7', '新增权限');
INSERT INTO `share_permitinfo` VALUES ('8', '新增权限666');
INSERT INTO `share_role` VALUES ('1', '超级管理员');
INSERT INTO `share_role` VALUES ('2', '普通用户');
INSERT INTO `share_role_per` VALUES ('1', '1');
INSERT INTO `share_role_per` VALUES ('2', '2');
