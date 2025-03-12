create table doctor
(
    id       int unsigned auto_increment comment 'id'
        primary key,
    username varchar(20)  not null comment '用户名',
    password varchar(20)  not null comment '密码',
    name     varchar(20)  not null comment '姓名',
    phone    char(11)     not null comment '手机号码',
    email    varchar(50)  null comment '邮箱',
    emp_id   varchar(200) null comment '员工编号',
    constraint phone
        unique (phone),
    constraint username
        unique (username)
)
    comment '医生用户表';

