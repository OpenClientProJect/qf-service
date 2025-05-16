/*
 Navicat Premium Dump SQL

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80040 (8.0.40)
 Source Host           : localhost:3306
 Source Schema         : qf

 Target Server Type    : MySQL
 Target Server Version : 80040 (8.0.40)
 File Encoding         : 65001

 Date: 16/05/2025 10:59:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for anime
-- ----------------------------
DROP TABLE IF EXISTS `anime`;
CREATE TABLE `anime`  (
  `anime_id` bigint NOT NULL AUTO_INCREMENT COMMENT '番剧的唯一标识',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '番剧标题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '番剧简介',
  `cover_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面图片的URL',
  `release_date` date NULL DEFAULT NULL COMMENT '首播日期',
  `status` enum('ongoing','completed','upcoming','hiatus') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '番剧状态：ongoing（连载中），completed（已完结）,upcoming(即将上映)，hiatus（暂停）',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `recommend` int NULL DEFAULT 0 COMMENT '推荐',
  `user_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`anime_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '存储番剧的基本信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for anime_episode
-- ----------------------------
DROP TABLE IF EXISTS `anime_episode`;
CREATE TABLE `anime_episode`  (
  `episode_id` bigint NOT NULL AUTO_INCREMENT,
  `anime_id` bigint NOT NULL,
  `episode_title` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '集数标题',
  `air_date` date NULL DEFAULT NULL COMMENT '播放日期',
  `duration` int NULL DEFAULT NULL COMMENT '时长（分钟）',
  `episode_number` int NULL DEFAULT NULL COMMENT '集数编号',
  `episode_image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `episode_video` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`episode_id`) USING BTREE,
  INDEX `episode_anime_anime_id_fk`(`anime_id` ASC) USING BTREE,
  CONSTRAINT `episode_anime_anime_id_fk` FOREIGN KEY (`anime_id`) REFERENCES `anime` (`anime_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
  `announcement_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_estonian_ci NOT NULL COMMENT '标题',
  `image_url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_estonian_ci NULL DEFAULT NULL,
  `video_url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_estonian_ci NULL DEFAULT NULL,
  `text` text CHARACTER SET utf8mb3 COLLATE utf8mb3_estonian_ci NOT NULL COMMENT '内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`announcement_id`) USING BTREE,
  INDEX `announcement_announcement_id_index`(`announcement_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_estonian_ci COMMENT = '公告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for background
-- ----------------------------
DROP TABLE IF EXISTS `background`;
CREATE TABLE `background`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `img` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_estonian_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `background_id_index`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_estonian_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for barrage
-- ----------------------------
DROP TABLE IF EXISTS `barrage`;
CREATE TABLE `barrage`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `video_id` int NOT NULL COMMENT '视频ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '弹幕内容',
  `color` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '弹幕颜色',
  `time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '视频时间点',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_video_time`(`video_id` ASC, `time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 241 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '弹幕表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for chat_message
-- ----------------------------
DROP TABLE IF EXISTS `chat_message`;
CREATE TABLE `chat_message`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `from_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发送者',
  `to_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接收者',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `send_time` datetime NOT NULL,
  `is_read` tinyint(1) NULL DEFAULT 0,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 177 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `video_id` int UNSIGNED NOT NULL COMMENT '外键，关联视频表（指向视频ID）',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '外键，关联用户表（指向用户ID）',
  `content` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论内容',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '评论更新时间',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父评论ID（如果是回复评论，指向父评论）',
  `status` int NULL DEFAULT 0 COMMENT '评论状态，标记评论是否已删除（0:正常，1:已删除）',
  `like_count` int NULL DEFAULT 0 COMMENT '点赞数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `comments_user_id_fk`(`user_id` ASC) USING BTREE,
  INDEX `comments_user_video_id_fk`(`video_id` ASC) USING BTREE,
  CONSTRAINT `comments_user_video_id_fk` FOREIGN KEY (`video_id`) REFERENCES `video` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 108 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for home_image
-- ----------------------------
DROP TABLE IF EXISTS `home_image`;
CREATE TABLE `home_image`  (
  `home_img_id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NOT NULL COMMENT '上传时间',
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `video` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`home_img_id`) USING BTREE,
  INDEX `home_image_home_img_id_index`(`home_img_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '首页图片' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for operate_log
-- ----------------------------
DROP TABLE IF EXISTS `operate_log`;
CREATE TABLE `operate_log`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `operate_user_id` bigint NULL DEFAULT NULL COMMENT '操作人id',
  `operate_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类名',
  `method_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作方法名',
  `method_params` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作方法参数',
  `cost_time` bigint NULL DEFAULT NULL COMMENT '操作耗时',
  `return_value` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法返回值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 305 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '昵称',
  `sex` int NOT NULL DEFAULT 0 COMMENT '性别',
  `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `user_pic` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像',
  `introduction` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '介绍',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `role` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'user' COMMENT '角色',
  `status` int NOT NULL DEFAULT 1 COMMENT '1=正常  0=停用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2450284794 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for user_follow
-- ----------------------------
DROP TABLE IF EXISTS `user_follow`;
CREATE TABLE `user_follow`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '关注者',
  `follow_user_id` bigint NOT NULL COMMENT '被关注者',
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 510 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '关注表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '视频封面',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视屏简介',
  `video_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视频链接',
  `user_id` bigint UNSIGNED NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL COMMENT '发布时间\r\n',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `likes_count` int NULL DEFAULT 0 COMMENT '点赞数',
  `favorite_count` int NULL DEFAULT 0,
  `category_id` int NOT NULL COMMENT '分类',
  `status` int NOT NULL DEFAULT 1 COMMENT '视频状态 1=待审核 2=已审核 3=通过',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_video_user_id_fk`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for video_draft
-- ----------------------------
DROP TABLE IF EXISTS `video_draft`;
CREATE TABLE `video_draft`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '视频封面',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视屏简介',
  `video_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视频链接',
  `user_id` bigint UNSIGNED NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL COMMENT '发布时间\r\n',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `likes_count` int NULL DEFAULT 0 COMMENT '点赞数',
  `favorite_count` int NULL DEFAULT 0,
  `subCategory_id` int NOT NULL DEFAULT 0 COMMENT '副分类',
  `mainCategory_id` int NULL DEFAULT NULL COMMENT '主分类',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_video_user_id_fk`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '草稿视频' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for video_episode
-- ----------------------------
DROP TABLE IF EXISTS `video_episode`;
CREATE TABLE `video_episode`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `video_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `video_episode_id_index`(`id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_as_ci COMMENT = '视频剧集' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for video_favorite
-- ----------------------------
DROP TABLE IF EXISTS `video_favorite`;
CREATE TABLE `video_favorite`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` bigint NULL DEFAULT NULL,
  `video_id` int NULL DEFAULT NULL,
  `Favorite_at` timestamp NULL DEFAULT NULL,
  INDEX `video_favorite_id_index`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '视频收藏' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for video_likes
-- ----------------------------
DROP TABLE IF EXISTS `video_likes`;
CREATE TABLE `video_likes`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '点赞记录ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `video_id` bigint NOT NULL COMMENT '视频ID',
  `liked_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '点赞时间',
  `favorite_at` timestamp NULL DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id` ASC, `video_id` ASC) USING BTREE COMMENT '确保同一用户对同一视频只能点赞一次'
) ENGINE = InnoDB AUTO_INCREMENT = 129 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '视频点赞表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for video_record
-- ----------------------------
DROP TABLE IF EXISTS `video_record`;
CREATE TABLE `video_record`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL,
  `video_id` int NOT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `category_id` int NULL DEFAULT NULL,
  INDEX `video_record_id_index`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_as_ci COMMENT = '视频播放记录' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
