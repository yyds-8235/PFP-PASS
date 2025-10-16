# 客运司机APP端功能说明

## 功能概述

客运司机APP端是基于微信小程序的客运司机管理平台，主要功能包括个人中心、途顺接单和今日专线三个模块。

## 技术栈

- Spring Boot 3.5.6
- MyBatis Plus 3.5.10.1
- MySQL 8.0
- Sa-Token 1.37.0 (权限认证)
- SpringDoc OpenAPI 3 (API文档)
- 七牛云OSS (文件存储)

## 数据库表结构

### 1. 客运司机表 (passenger_driver)
使用现有的User实体类，对应passenger_driver表，包含以下字段：
- id: 主键ID (自增)
- username: 用户名 (唯一)
- password: 密码 (加密存储)
- name: 姓名
- gender: 性别 (0-未知 1-男 2-女)
- phone: 手机号
- car_number: 车牌号
- drive_experience: 司机驾龄
- avatar: 头像URL
- create_time: 账户创建时间
- update_time: 信息最后更新时间

**测试数据包含5个客运司机：**
- 张三 (driver001) - 京A12345, 5年驾龄
- 李四 (driver002) - 沪B67890, 8年驾龄  
- 王五 (driver003) - 粤C11111, 3年驾龄
- 赵六 (driver004) - 深D22222, 6年驾龄
- 钱七 (driver005) - 京E33333, 10年驾龄

### 2. 站点表 (station)
- id: 主键ID
- station_name: 站点名字
- station_image: 站点图片
- station_phone: 站点电话
- station_address: 站点地址
- available_orders: 站点可接订单数量
- status: 站点状态 (0-关闭 1-开启)
- create_time: 创建时间
- update_time: 更新时间

### 3. 专线表 (route)
- id: 主键ID
- departure_point: 出发点
- destination: 终点
- departure_time: 出发时间
- estimated_arrival_time: 预计到达时间
- status: 专线状态 (0-关闭 1-开启)
- create_time: 创建时间
- update_time: 更新时间

## API接口说明

### 认证模块 (AuthController)
- **登录**: `POST /api/auth/login` - 用户登录
- **注册**: `POST /api/auth/register` - 用户注册  
- **注销**: `POST /api/auth/logout` - 用户注销

### 1. 司机个人信息模块 (DriverController)
**需要登录认证**

#### 获取司机个人信息
- **接口**: `GET /api/driver/info`
- **功能**: 获取当前登录司机的个人信息
- **认证**: 需要登录
- **返回**: DriverInfoVO对象

#### 更新司机个人信息
- **接口**: `PUT /api/driver/info`
- **功能**: 更新当前登录司机的个人信息
- **认证**: 需要登录
- **参数**: DriverInfoUpdateDTO对象
- **返回**: 操作结果

### 2. 站点信息模块 (StationController)
**需要登录认证**

#### 获取站点列表
- **接口**: `GET /api/station/list`
- **功能**: 分页获取可接单的站点列表
- **认证**: 需要登录
- **参数**: 
  - current: 当前页 (默认1)
  - size: 每页大小 (默认10)
- **返回**: PageDTO<StationDTO>对象

#### 获取站点详细信息
- **接口**: `GET /api/station/{id}`
- **功能**: 根据站点ID获取站点详细信息
- **认证**: 需要登录
- **参数**: id - 站点ID
- **返回**: StationDetailVO对象

### 3. 专线信息模块 (RouteController)
**需要登录认证**

#### 获取今日专线列表（分页）
- **接口**: `GET /api/route/list`
- **功能**: 分页获取今日专线列表
- **认证**: 需要登录
- **参数**: 
  - current: 当前页 (默认1)
  - size: 每页大小 (默认10)
- **返回**: PageDTO<RouteDTO>对象

#### 获取今日专线列表（不分页）
- **接口**: `GET /api/route/today`
- **功能**: 获取今日专线列表（不分页）
- **认证**: 需要登录
- **返回**: List<TodayRouteVO>对象

## 核心类说明

### DTO类
- **PageDTO**: 分页查询结果封装类
- **DriverInfoUpdateDTO**: 司机信息更新DTO
- **StationDTO**: 站点信息DTO
- **RouteDTO**: 专线信息DTO

### VO类
- **DriverInfoVO**: 司机信息展示VO
- **StationDetailVO**: 站点详细信息VO
- **TodayRouteVO**: 今日专线VO

### 实体类
- **User**: 客运司机实体类（对应passenger_driver表）
- **Station**: 站点信息实体类
- **Route**: 专线信息实体类

### 服务类
- **UserService**: 用户服务接口
- **StationService**: 站点信息服务接口
- **RouteService**: 专线信息服务接口

### 控制器
- **AuthController**: 认证控制器（登录、注册、注销）
- **DriverController**: 客运司机个人信息控制器
- **StationController**: 站点信息控制器
- **RouteController**: 专线信息控制器

## 部署说明

1. 确保MySQL数据库已启动
2. 执行 `src/main/resources/sql/init.sql` 初始化数据库表和数据
3. 修改 `application.yaml` 中的数据库连接配置
4. 启动Spring Boot应用
5. 访问 `http://localhost:8085/swagger-ui.html` 查看API文档

## 注意事项

1. 当前用户ID获取逻辑需要根据实际的认证机制进行调整
2. 站点可接订单数量需要根据实际业务逻辑进行计算
3. 专线信息的时间字段使用LocalTime类型，需要确保数据库支持
4. 所有接口都返回统一的Result格式
5. 分页查询支持自定义页码和每页大小

## 扩展功能

后续可以扩展的功能：
1. 订单管理功能
2. 司机位置跟踪
3. 消息通知功能
4. 数据统计和报表
5. 司机评价系统
