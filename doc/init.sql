SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`
(
    `id`           bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
    `name`         varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '菜单名称',
    `url`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '菜单url',
    `notes`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `del_flag`     int(11)                                                       NOT NULL DEFAULT 0 COMMENT '0：未删除；1：已删除',
    `parent_id`    bigint(20)                                                    NOT NULL COMMENT '父级ID',
    `component`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '页面组件',
    `icon`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT 'icon',
    `create_time`  timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '创建者',
    `update_by`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '更新者',
    `create_by_id` bigint(20)                                                    NULL     DEFAULT NULL COMMENT '创建者ID',
    `update_by_id` bigint(20)                                                    NULL     DEFAULT NULL COMMENT '更新者ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '菜单表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission`
VALUES (1, '控制面板', 'system', NULL, 0, 0, 'RouteView', 'setting', '2022-01-28 10:18:56', '2022-01-29 10:54:20', NULL,
        NULL, NULL, NULL);
INSERT INTO `permission`
VALUES (2, '菜单管理', 'permission', NULL, 0, 1, 'PermissionList', NULL, '2022-01-28 10:18:56', '2022-01-28 10:18:56', NULL,
        NULL, NULL, NULL);
INSERT INTO `permission`
VALUES (3, '角色管理', 'role', NULL, 0, 1, 'RoleList', NULL, '2022-01-28 10:18:56', '2022-01-28 10:18:56', NULL, NULL, NULL,
        NULL);
INSERT INTO `permission`
VALUES (4, '用户管理', 'user', NULL, 0, 1, 'UserList', NULL, '2022-01-28 10:18:56', '2022-01-28 10:18:56', NULL, NULL, NULL,
        NULL);
INSERT INTO `permission`
VALUES (5, '发布公告', 'notice', NULL, 0, 1, 'UserList', NULL, '2022-01-29 11:10:28', '2022-01-29 11:10:28', NULL, NULL,
        NULL, NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`           bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
    `name`         varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '角色名称',
    `del_flag`     tinyint(4)                                                    NOT NULL DEFAULT 0 COMMENT '是否删除0：未删除；1：已删除',
    `notes`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `type`         varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '类型 PERM：默认角色；ITEM：接入项目绑定角色；ITEM_FLOW：接入项目流程绑定角色',
    `create_time`  timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '创建者',
    `update_by`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '更新者',
    `create_by_id` bigint(20)                                                    NULL     DEFAULT NULL COMMENT '创建者ID',
    `update_by_id` bigint(20)                                                    NULL     DEFAULT NULL COMMENT '更新者ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role`
VALUES (1, '系统管理员', 0, NULL, 'PERM', '2022-01-28 10:18:59', '2022-01-28 10:18:59', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`
(
    `id`            bigint(20)                                                   NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
    `role_id`       bigint(20)                                                   NOT NULL COMMENT '角色id',
    `permission_id` bigint(20)                                                   NOT NULL COMMENT '菜单id',
    `del_flag`      tinyint(4)                                                   NOT NULL DEFAULT 0 COMMENT '0：未删除；1：已删除',
    `create_time`   timestamp                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '创建者',
    `update_by`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '更新者',
    `create_by_id`  bigint(20)                                                   NULL     DEFAULT NULL COMMENT '创建者ID',
    `update_by_id`  bigint(20)                                                   NULL     DEFAULT NULL COMMENT '更新者ID',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `fk_role_id` (`role_id`) USING BTREE,
    INDEX `fk_permission_id` (`permission_id`) USING BTREE,
    CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
    CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色菜单表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission`
VALUES (1, 1, 1, 0, '2022-01-28 10:19:05', '2022-01-28 10:19:05', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission`
VALUES (2, 1, 2, 0, '2022-01-28 10:19:05', '2022-01-28 10:19:05', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission`
VALUES (3, 1, 3, 0, '2022-01-28 10:19:05', '2022-01-28 10:19:05', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission`
VALUES (4, 1, 4, 0, '2022-01-28 10:19:05', '2022-01-28 10:19:05', NULL, NULL, NULL, NULL);
INSERT INTO `role_permission`
VALUES (5, 1, 5, 0, '2022-01-29 11:10:41', '2022-01-29 11:10:41', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`                 bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
    `name`               varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户名称',
    `password`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户密码',
    `del_flag`           tinyint(4)                                                    NOT NULL DEFAULT 0 COMMENT '是否删除0：未删除；1：已删除',
    `notes`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '备注',
    `username`           varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户名',
    `email`              varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '邮箱',
    `create_time`        timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`        timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '创建者',
    `update_by`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '更新者',
    `create_by_id`       bigint(20)                                                    NULL     DEFAULT NULL COMMENT '创建者ID',
    `update_by_id`       bigint(20)                                                    NULL     DEFAULT NULL COMMENT '更新者ID',
    `profile`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '简介',
    `last_login_time`    timestamp                                                     NULL     DEFAULT NULL COMMENT '最后一次登陆时间',
    `current_login_time` timestamp                                                     NULL     DEFAULT NULL COMMENT '本次登陆时间',
    `avatar`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '头像',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (1, '管理员', 'e10adc3949ba59abbe56e057f20f883e', 0, '管理员', 'admin', '123@qq.com', '2022-01-28 09:38:30',
        '2022-01-29 10:18:45', NULL, NULL, NULL, NULL, '个人简介', '2022-01-28 14:34:06', '2022-01-28/1-1643361026562.png');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `id`           bigint(20)                                                   NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
    `user_id`      bigint(20)                                                   NOT NULL COMMENT '用户id',
    `role_id`      bigint(20)                                                   NOT NULL COMMENT '角色id',
    `del_flag`     tinyint(4)                                                   NOT NULL DEFAULT 0 COMMENT '0：未删除；1：已删除',
    `create_time`  timestamp                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  timestamp                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '创建者',
    `update_by`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '更新者',
    `create_by_id` bigint(20)                                                   NULL     DEFAULT NULL COMMENT '创建者ID',
    `update_by_id` bigint(20)                                                   NULL     DEFAULT NULL COMMENT '更新者ID',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `fk_user_id` (`user_id`) USING BTREE,
    INDEX `pk_role_id` (`role_id`) USING BTREE,
    CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
    CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户角色表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role`
VALUES (1, 1, 1, 0, '2022-01-28 10:19:11', '2022-01-29 10:44:49', NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
