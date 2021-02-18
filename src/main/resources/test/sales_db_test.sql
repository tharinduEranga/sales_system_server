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
SET MODE MySQL;
-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
DROP TABLE IF EXISTS `branch`;
DROP TABLE IF EXISTS `product`;

CREATE TABLE `product`
(
    `id`         varchar(255) NOT NULL PRIMARY KEY,
    `name`       varchar(255) NOT NULL,
    `status`     int(10)      NOT NULL DEFAULT 1,
    `created_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ----------------------------
-- Table structure for branch
-- ----------------------------
CREATE TABLE `branch`
(
    `id`         varchar(255) NOT NULL PRIMARY KEY,
    `name`       varchar(255) NOT NULL,
    `address`    varchar(255)          DEFAULT NULL,
    `tel`        varchar(255)          DEFAULT NULL,
    `type`       int(10)      NOT NULL DEFAULT 2,
    `status`     int(10)      NOT NULL DEFAULT 1,
    `created_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ----------------------------
-- Table structure for stock
-- ----------------------------
CREATE TABLE `stock`
(
    `id`          varchar(255)   NOT NULL PRIMARY KEY,
    `description` varchar(255)            DEFAULT NULL,
    `qty`         int(255)       NOT NULL DEFAULT 0,
    `price`       decimal(10, 2) NOT NULL DEFAULT 0.0,
    `branch_id`   varchar(255)   NOT NULL,
    `product_id`  varchar(255)   NOT NULL,
    `created_at`  timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX bra_ind (branch_id),
    FOREIGN KEY (branch_id)
        REFERENCES branch (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    INDEX pro_ind (product_id),
    FOREIGN KEY (product_id)
        REFERENCES product (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
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

-- ----------------------------
-- Records of branch
-- ----------------------------
INSERT INTO `branch` (`id`, `name`, `address`, `tel`, `type`, `status`, `created_at`)
VALUES ('323432', 'Colombo Branch', 'Colombo', '0774935895', 2, 1, '2021-02-17 11:38:44');
INSERT INTO `branch` (`id`, `name`, `address`, `tel`, `type`, `status`, `created_at`)
VALUES ('43242324', 'Galle Branch', 'Galle', '0776288969', 2, 1, '2021-02-17 11:53:30');

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO `stock` (`id`, `description`, `qty`, `price`, `branch_id`, `product_id`, `created_at`)
VALUES ('643344fregt4t', 'Test', 10, 0.00, '43242324',
        '12cbc2ca-69d8-11eb-8f8a-a81e849e9ba2', '2021-02-18 09:01:03');



