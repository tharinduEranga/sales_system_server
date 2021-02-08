/*
 Navicat Premium Data Transfer

 Source Server         : Local
 Source Server Type    : MySQL
 Source Server Version : 50712
 Source Host           : localhost:3306
 Source Schema         : sales_db

 Target Server Type    : H2
 Target Server Version : 50712
 File Encoding         : 65001

 Date: 08/02/2021 12:26:57
*/
-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;

CREATE TABLE `product`
(
    `id`         varchar(255) NOT NULL PRIMARY KEY,
    `name`       varchar(255) NOT NULL UNIQUE,
    `status`     int(10)               DEFAULT NULL,
    `created_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product`
VALUES ('12cbc2ca-69d8-11eb-8f8a-a81e849e9ba1', 'Apple', 1, '2021-02-08 11:38:16');
INSERT INTO `product`
VALUES ('12cbc2ca-69d8-11eb-8f8a-a81e849e9ba3', 'Orange', 1, '2021-02-08 11:38:16');
INSERT INTO `product`
VALUES ('12cbc2ca-69d8-11eb-8f8a-a81e849e9ba4', 'Banana', 1, '2021-02-08 12:06:47');
INSERT INTO `product`
VALUES ('12cbc2ca-69d8-11eb-8f8a-a81e849e9ba2', 'Papaya', 1, '2021-02-08 12:16:47');

