/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80039
 Source Host           : localhost:3306
 Source Schema         : love

 Target Server Type    : MySQL
 Target Server Version : 80039
 File Encoding         : 65001

 Date: 15/05/2025 00:30:29
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
  PRIMARY KEY (`anime_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '存储番剧的基本信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of anime
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of anime_episode
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_estonian_ci COMMENT = '公告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of announcement
-- ----------------------------

-- ----------------------------
-- Table structure for background
-- ----------------------------
DROP TABLE IF EXISTS `background`;
CREATE TABLE `background`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `img` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_estonian_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `background_id_index`(`id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_estonian_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of background
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '弹幕表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of barrage
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of chat_message
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comments
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '首页图片' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of home_image
-- ----------------------------
INSERT INTO `home_image` VALUES (5, 'http://svs454s5u.hn-bkt.clouddn.com/9e9210f2-414c-4740-ba65-f3906b42d0aa.jpg', '2025-05-09 18:32:52', '123', 'http://svs454s5u.hn-bkt.clouddn.com/videos/3c0d3fcf-6da7-4557-b43d-4572fc54bbed.mp4', '123');

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
) ENGINE = InnoDB AUTO_INCREMENT = 297 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of operate_log
-- ----------------------------
INSERT INTO `operate_log` VALUES (290, 2450284793, '2025-05-09 15:11:48', 'com.love.controller.user.UserVideoController', 'add', '[Video(id=null, title=123, cover=http://svs454s5u.hn-bkt.clouddn.com/56931176-a425-46f0-81b0-e54c94359bca.jpg, userId=null, nickname=null, userPic=null, username=null, introduction=null, content=123, categoryId=6, videoUrl=http://svs454s5u.hn-bkt.clouddn.com/videos/ecfd8f69-f5c2-485e-9dfd-a563e231f527.mp4, likesCount=null, favoriteCount=null, createTime=null, updateTime=null)]', 5, '{\"code\":200,\"message\":\"操作成功\",\"data\":null}');
INSERT INTO `operate_log` VALUES (291, 2450281293, '2025-05-09 15:51:41', 'com.love.controller.user.UserController', 'update', '[User(id=null, username=null, password=null, nickname=昵称_de774a, sex=0, role=null, status=null, email=null, code=null, userPic=http://svs454s5u.hn-bkt.clouddn.com/44cbf52c-f38c-4ba9-bc74-8fcd07f8cc2a.jpg, introduction=12312313, createTime=null, updateTime=null)]', 8, '{\"code\":200,\"message\":\"操作成功\",\"data\":null}');
INSERT INTO `operate_log` VALUES (292, 2450281293, '2025-05-09 16:44:27', 'com.love.controller.user.UserVideoController', 'add', '[Video(id=null, title=123, cover=http://svs454s5u.hn-bkt.clouddn.com/3707f6cb-fdb6-405b-ad7d-8e833040a6cc.jpg, userId=null, nickname=null, userPic=null, username=null, introduction=null, content=123123, categoryId=201, videoUrl=http://svs454s5u.hn-bkt.clouddn.com/videos/23ca35d6-adba-4f18-885c-cdb1ffef72e6.mp4, likesCount=null, favoriteCount=null, createTime=null, updateTime=null)]', 12, '{\"code\":200,\"message\":\"操作成功\",\"data\":null}');
INSERT INTO `operate_log` VALUES (293, 2450281293, '2025-05-09 16:48:43', 'com.love.controller.user.UserVideoController', 'add', '[Video(id=null, title=312313, cover=http://svs454s5u.hn-bkt.clouddn.com/1cfe91dc-df84-4e55-a6cd-df3b0c5c19b6.jpg, userId=null, nickname=null, userPic=null, username=null, introduction=null, content=1232131, categoryId=106, videoUrl=http://svs454s5u.hn-bkt.clouddn.com/videos/b51ccb95-590a-4ea4-91e3-6f90d9633eab.mp4, likesCount=null, favoriteCount=null, createTime=null, updateTime=null)]', 4, '{\"code\":200,\"message\":\"操作成功\",\"data\":null}');
INSERT INTO `operate_log` VALUES (294, 2450281293, '2025-05-09 16:54:11', 'com.love.controller.user.UserVideoController', 'add', '[Video(id=null, title=1231321, cover=http://svs454s5u.hn-bkt.clouddn.com/a6251b51-cfb2-48ea-97f9-e75201973503.jpg, userId=null, nickname=null, userPic=null, username=null, introduction=null, content=123123, mainCategoryId=1, subCategoryId=105, videoUrl=http://svs454s5u.hn-bkt.clouddn.com/videos/01594975-8e01-458d-813d-aea7199a037d.mp4, likesCount=null, favoriteCount=null, createTime=null, updateTime=null)]', 43, '{\"code\":200,\"message\":\"操作成功\",\"data\":null}');
INSERT INTO `operate_log` VALUES (295, 2450281293, '2025-05-09 17:02:05', 'com.love.controller.user.UserVideoController', 'add', '[Video(id=null, title=123123, cover=http://svs454s5u.hn-bkt.clouddn.com/ddddfa0e-f8e7-4706-b3d8-94ac14784d22.jpg, userId=null, nickname=null, userPic=null, username=null, introduction=null, content=1312311, mainCategoryId=2, subCategoryId=202, videoUrl=http://svs454s5u.hn-bkt.clouddn.com/videos/744e1b71-37de-4048-81a9-d29e1980a389.mp4, likesCount=null, favoriteCount=null, createTime=null, updateTime=null)]', 5, '{\"code\":200,\"message\":\"操作成功\",\"data\":null}');
INSERT INTO `operate_log` VALUES (296, 2450281293, '2025-05-09 17:03:03', 'com.love.controller.user.UserVideoController', 'add', '[Video(id=null, title=1231, cover=http://svs454s5u.hn-bkt.clouddn.com/7f4dabec-9f6f-48da-ad51-ca8e7e1c9d05.jpg, userId=null, nickname=null, userPic=null, username=null, introduction=null, content=13212313, mainCategoryId=2, subCategoryId=203, videoUrl=http://svs454s5u.hn-bkt.clouddn.com/videos/de41e216-3da7-416a-b930-52cbdbf6f43d.mp4, likesCount=null, favoriteCount=null, createTime=null, updateTime=null)]', 39, '{\"code\":200,\"message\":\"操作成功\",\"data\":null}');
INSERT INTO `operate_log` VALUES (297, 2450281293, '2025-05-12 22:55:38', 'com.love.controller.user.UserFollowController', 'follow', '[2450284793, true]', 13, '{\"code\":200,\"message\":\"操作成功\",\"data\":null}');

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
) ENGINE = InnoDB AUTO_INCREMENT = 2450284793 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2450281293, '24895022133', 'e10adc3949ba59abbe56e057f20f883e', '昵称_de774a', 0, '2954494754@qq.com', 'http://svs454s5u.hn-bkt.clouddn.com/44cbf52c-f38c-4ba9-bc74-8fcd07f8cc2a.jpg', '12312313', '2025-05-09 14:09:14', '2025-05-09 14:09:14', 'admin', 1);
INSERT INTO `user` VALUES (2450284793, '24895020387', 'e10adc3949ba59abbe56e057f20f883e', '昵称_de774a', 0, '2954494754@qq.com', '', NULL, '2025-05-09 14:09:14', '2025-05-09 14:09:14', 'admin', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 509 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '关注表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_follow
-- ----------------------------
INSERT INTO `user_follow` VALUES (509, 2450281293, 2450284793, '2025-05-12 22:55:37');

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
  `sub_category_id` int NOT NULL COMMENT '副分类',
  `main_category_id` int NOT NULL COMMENT '主分类',
  `status` int NOT NULL DEFAULT 1 COMMENT '视频状态 1=待审核 2=已审核 3=通过',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_video_user_id_fk`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES (105, '123', 'http://svs454s5u.hn-bkt.clouddn.com/56931176-a425-46f0-81b0-e54c94359bca.jpg', '123', 'http://svs454s5u.hn-bkt.clouddn.com/videos/ecfd8f69-f5c2-485e-9dfd-a563e231f527.mp4', 2450284793, '2025-05-09 15:11:48', '2025-05-09 15:11:48', 0, 0, 203, 2, 2);
INSERT INTO `video` VALUES (106, '1231', 'http://svs454s5u.hn-bkt.clouddn.com/7f4dabec-9f6f-48da-ad51-ca8e7e1c9d05.jpg', '13212313', 'http://svs454s5u.hn-bkt.clouddn.com/videos/de41e216-3da7-416a-b930-52cbdbf6f43d.mp4', 2450281293, '2025-05-09 17:03:02', '2025-05-09 17:03:02', 0, 0, 203, 2, 3);
INSERT INTO `video` VALUES (107, '312313', 'http://svs454s5u.hn-bkt.clouddn.com/1cfe91dc-df84-4e55-a6cd-df3b0c5c19b6.jpg', '1232131', 'http://svs454s5u.hn-bkt.clouddn.com/videos/b51ccb95-590a-4ea4-91e3-6f90d9633eab.mp4', 2450281293, '2025-05-09 16:48:43', '2025-05-09 16:48:43', 0, 0, 106, 2, 2);
INSERT INTO `video` VALUES (108, '1231321', 'http://svs454s5u.hn-bkt.clouddn.com/a6251b51-cfb2-48ea-97f9-e75201973503.jpg', '123123', 'http://svs454s5u.hn-bkt.clouddn.com/videos/01594975-8e01-458d-813d-aea7199a037d.mp4', 2450281293, '2025-05-09 16:54:10', '2025-05-09 16:54:10', 0, 0, 105, 1, 2);
INSERT INTO `video` VALUES (109, '123123', 'http://svs454s5u.hn-bkt.clouddn.com/ddddfa0e-f8e7-4706-b3d8-94ac14784d22.jpg', '1312311', 'http://svs454s5u.hn-bkt.clouddn.com/videos/744e1b71-37de-4048-81a9-d29e1980a389.mp4', 2450281293, '2025-05-09 17:02:05', '2025-05-09 17:02:05', 0, 0, 202, 2, 3);

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
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '草稿视频' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of video_draft
-- ----------------------------
INSERT INTO `video_draft` VALUES (105, '123', 'http://svs454s5u.hn-bkt.clouddn.com/56931176-a425-46f0-81b0-e54c94359bca.jpg', '123', 'http://svs454s5u.hn-bkt.clouddn.com/videos/ecfd8f69-f5c2-485e-9dfd-a563e231f527.mp4', 2450284793, '2025-05-09 15:11:48', '2025-05-09 15:11:48', 0, 0, 6, NULL);
INSERT INTO `video_draft` VALUES (106, '123', 'http://svs454s5u.hn-bkt.clouddn.com/3707f6cb-fdb6-405b-ad7d-8e833040a6cc.jpg', '123123', 'http://svs454s5u.hn-bkt.clouddn.com/videos/23ca35d6-adba-4f18-885c-cdb1ffef72e6.mp4', 2450281293, '2025-05-09 16:44:26', '2025-05-09 16:44:26', 0, 0, 201, NULL);
INSERT INTO `video_draft` VALUES (107, '312313', 'http://svs454s5u.hn-bkt.clouddn.com/1cfe91dc-df84-4e55-a6cd-df3b0c5c19b6.jpg', '1232131', 'http://svs454s5u.hn-bkt.clouddn.com/videos/b51ccb95-590a-4ea4-91e3-6f90d9633eab.mp4', 2450281293, '2025-05-09 16:48:43', '2025-05-09 16:48:43', 0, 0, 106, NULL);
INSERT INTO `video_draft` VALUES (108, '1231321', 'http://svs454s5u.hn-bkt.clouddn.com/a6251b51-cfb2-48ea-97f9-e75201973503.jpg', '123123', 'http://svs454s5u.hn-bkt.clouddn.com/videos/01594975-8e01-458d-813d-aea7199a037d.mp4', 2450281293, '2025-05-09 16:54:10', '2025-05-09 16:54:10', 0, 0, 105, 1);
INSERT INTO `video_draft` VALUES (109, '123123', 'http://svs454s5u.hn-bkt.clouddn.com/ddddfa0e-f8e7-4706-b3d8-94ac14784d22.jpg', '1312311', 'http://svs454s5u.hn-bkt.clouddn.com/videos/744e1b71-37de-4048-81a9-d29e1980a389.mp4', 2450281293, '2025-05-09 17:02:05', '2025-05-09 17:02:05', 0, 0, 202, 2);

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
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '视频收藏' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of video_favorite
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '视频点赞表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of video_likes
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
