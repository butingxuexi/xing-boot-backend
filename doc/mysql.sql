-- auto-generated definition
create table system_users
(
    id          bigint auto_increment comment '用户ID'
        primary key,
    username    varchar(30)                            not null comment '用户账号',
    password    varchar(100) default ''                not null comment '密码',
    nickname    varchar(30)                            not null comment '用户昵称',
    unionId     varchar(255) default ''                null comment '微信开放平台id',
    mpOpenId    varchar(255) default ''                null comment '公众号openId',
    email       varchar(50)  default ''                null comment '用户邮箱',
    mobile      varchar(11)  default ''                null comment '手机号码',
    sex         tinyint      default 0                 null comment '用户性别',
    avatar      varchar(100) default ''                null comment '头像地址',
    userRole    varchar(255) default 'user'            not null comment '用户角色:user/admin',
    status      tinyint      default 0                 not null comment '帐号状态（0正常 1停用）',
    creator     varchar(64)  default ''                null comment '创建者',
    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater     varchar(64)  default ''                null comment '更新者',
    update_time datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     bit          default b'0'              null comment '是否删除(0:未删除，1:删除)',
    constraint idx_username
        unique (username, unionId)
)
    comment '用户信息表' row_format = DYNAMIC;

