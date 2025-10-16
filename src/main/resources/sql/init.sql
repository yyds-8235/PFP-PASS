-- 创建客运司机表
CREATE TABLE IF NOT EXISTS `passenger_driver` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `username` varchar(50) NOT NULL COMMENT '用户名',
    `password` varchar(255) NOT NULL COMMENT '密码',
    `name` varchar(50) DEFAULT NULL COMMENT '姓名',
    `gender` tinyint DEFAULT 0 COMMENT '性别 0-未知 1-男 2-女',
    `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
    `car_number` varchar(20) DEFAULT NULL COMMENT '车牌号',
    `drive_experience` int DEFAULT 0 COMMENT '司机驾龄',
    `avatar` varchar(500) DEFAULT NULL COMMENT '头像',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '账户创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '信息最后更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客运司机表';

-- 创建站点表
CREATE TABLE IF NOT EXISTS `station` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `station_name` varchar(100) NOT NULL COMMENT '站点名字',
    `station_image` varchar(500) DEFAULT NULL COMMENT '站点图片',
    `station_phone` varchar(20) DEFAULT NULL COMMENT '站点电话',
    `station_address` varchar(200) DEFAULT NULL COMMENT '站点地址',
    `available_orders` int DEFAULT 0 COMMENT '站点可接订单数量',
    `status` tinyint DEFAULT 1 COMMENT '站点状态 0-关闭 1-开启',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站点信息表';

-- 创建专线表
CREATE TABLE IF NOT EXISTS `route` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `departure_point` varchar(100) NOT NULL COMMENT '出发点',
    `destination` varchar(100) NOT NULL COMMENT '终点',
    `departure_time` time NOT NULL COMMENT '出发时间',
    `estimated_arrival_time` time NOT NULL COMMENT '预计到达时间',
    `status` tinyint DEFAULT 1 COMMENT '专线状态 0-关闭 1-开启',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='专线信息表';

-- 插入客运司机测试数据
INSERT INTO `passenger_driver` (`username`, `password`, `name`, `gender`, `phone`, `car_number`, `drive_experience`, `avatar`) VALUES
('driver001', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', '张三', 1, '13800138001', '京A12345', 5, 'https://example.com/avatar1.jpg'),
('driver002', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', '李四', 1, '13800138002', '沪B67890', 8, 'https://example.com/avatar2.jpg'),
('driver003', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', '王五', 1, '13800138003', '粤C11111', 3, 'https://example.com/avatar3.jpg'),
('driver004', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', '赵六', 2, '13800138004', '深D22222', 6, 'https://example.com/avatar4.jpg'),
('driver005', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', '钱七', 1, '13800138005', '京E33333', 10, 'https://example.com/avatar5.jpg');

-- 插入站点测试数据
INSERT INTO `station` (`station_name`, `station_image`, `station_phone`, `station_address`, `available_orders`, `status`) VALUES
('北京站', 'https://example.com/beijing.jpg', '010-12345678', '北京市东城区', 15, 1),
('上海站', 'https://example.com/shanghai.jpg', '021-87654321', '上海市黄浦区', 20, 1),
('广州站', 'https://example.com/guangzhou.jpg', '020-11111111', '广州市越秀区', 12, 1),
('深圳站', 'https://example.com/shenzhen.jpg', '0755-22222222', '深圳市罗湖区', 18, 1);

INSERT INTO `route` (`departure_point`, `destination`, `departure_time`, `estimated_arrival_time`, `status`) VALUES
('北京', '上海', '08:00:00', '16:00:00', 1),
('上海', '北京', '09:00:00', '17:00:00', 1),
('广州', '深圳', '10:00:00', '12:00:00', 1),
('深圳', '广州', '14:00:00', '16:00:00', 1),
('北京', '广州', '07:00:00', '19:00:00', 1),
('广州', '北京', '08:00:00', '20:00:00', 1);
