/*
 Navicat Premium Data Transfer

 Source Server         : TGY
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : logistics

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 23/02/2023 16:37:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for package
-- ----------------------------
DROP TABLE IF EXISTS `package`;
CREATE TABLE `package`  (
  `id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_es_0900_ai_ci NOT NULL,
  `sender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_es_0900_ai_ci NULL DEFAULT NULL,
  `sender_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_es_0900_ai_ci NULL DEFAULT '',
  `send_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_es_0900_ai_ci NULL DEFAULT NULL,
  `send_date` datetime(0) NULL DEFAULT NULL,
  `receiver` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_es_0900_ai_ci NULL DEFAULT NULL,
  `receiver_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_es_0900_ai_ci NULL DEFAULT NULL,
  `receive_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_es_0900_ai_ci NULL DEFAULT NULL,
  `receive_date` datetime(0) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_es_0900_ai_ci NULL DEFAULT NULL,
  `weight` double NULL DEFAULT NULL,
  `logistics` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_es_0900_ai_ci NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_es_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_es_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of package
-- ----------------------------
INSERT INTO `package` VALUES ('123', '张三', '12312333', '江西南昌', '2023-02-16 19:48:02', '小明', '1231231333', '湖南长沙', '2023-02-23 19:48:34', '飞扬洗发水', 0.2, '京东', '洗发水');
INSERT INTO `package` VALUES ('124', '小明', '12312333', '深圳', '2023-02-15 09:36:40', '张三', '1231333', '江西南昌', '2023-02-21 09:36:55', '笔记本电脑', 0.2, '京东', '');

-- ----------------------------
-- Table structure for transport
-- ----------------------------
DROP TABLE IF EXISTS `transport`;
CREATE TABLE `transport`  (
  `id` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_es_0900_ai_ci NOT NULL,
  `pid` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_es_0900_ai_ci NULL DEFAULT NULL,
  `date` datetime(0) NULL DEFAULT NULL,
  `progress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_es_0900_ai_ci NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_es_0900_ai_ci NULL DEFAULT '包裹已签收，感谢您使用顺丰快递服务，期待下次继续为您服务！',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `a`(`pid`) USING BTREE,
  CONSTRAINT `a` FOREIGN KEY (`pid`) REFERENCES `package` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_es_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of transport
-- ----------------------------
INSERT INTO `transport` VALUES ('11', '123', '2023-02-08 22:12:02', '湖南株洲', '包裹已签收，感谢您使用顺丰快递服务，期待下次继续为您服务！');
INSERT INTO `transport` VALUES ('2', '123', '2023-02-15 09:28:43', '快件已发车', '运输中');
INSERT INTO `transport` VALUES ('3', '124', '2023-02-21 09:40:00', '深圳市', '快件已发车');
INSERT INTO `transport` VALUES ('4', '124', '2023-02-22 09:40:04', '广州市', '已揽件');
INSERT INTO `transport` VALUES ('5', '124', '2023-02-24 09:40:08', '长沙市', '已揽件，正在运输中');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_es_0900_ai_ci NOT NULL DEFAULT '1123456789',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_es_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_es_0900_ai_ci NULL DEFAULT NULL,
  `permission` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_es_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('123', '张三', '123', 0);

SET FOREIGN_KEY_CHECKS = 1;
