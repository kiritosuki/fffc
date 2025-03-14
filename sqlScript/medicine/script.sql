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

create table history
(
    id           int unsigned auto_increment comment 'id'
        primary key,
    patient_id   int unsigned     not null comment '病人id',
    name         varchar(20)      not null comment '病人姓名',
    age          tinyint unsigned not null comment '病人年龄',
    gender       tinyint unsigned not null comment '1男 0女',
    phone        char(11)         not null comment '病人手机号',
    id_card      char(18)         not null comment '病人身份证号',
    doctor_name  varchar(20)      not null comment '诊断医生姓名',
    left_img     varchar(300)     null comment '左眼图片',
    right_img    varchar(300)     null comment '右眼图片',
    left_diag    varchar(500)     null comment '左眼诊断关键字',
    right_diag   varchar(500)     null comment '右眼诊断关键字',
    res_info     varchar(500)     null comment '诊断结果',
    diag_time    date             null comment '诊断日期',
    create_time  datetime         null comment '创建时间',
    allergy      varchar(200)     null comment '过敏药物',
    complaint    varchar(200)     null comment '主诉',
    visit        tinyint unsigned null comment '初诊/复诊 1初诊 2复诊',
    pres_history varchar(200)     null comment ' 现病史',
    past_history varchar(200)     null comment '既往史',
    pos_feature  varchar(200)     null comment '阳体特征',
    neg_feature  varchar(200)     null comment '阴体特征'
)
    comment '病人历史诊断记录';

create table past_status
(
    id         int unsigned auto_increment comment 'id'
        primary key,
    ill        int unsigned     null comment '疾病代号',
    history_id int unsigned     null comment '历史记录id',
    info       varchar(50)      null comment '其他异常信息',
    position   tinyint unsigned null comment '1左眼 2右眼'
)
    comment '历史记录中病人疾病';

create table patient
(
    id           int unsigned auto_increment comment 'id'
        primary key,
    name         varchar(20)      not null comment '姓名',
    age          int              not null comment '年龄',
    gender       tinyint unsigned not null comment '性别 1男 0女',
    phone        char(11)         not null comment '手机号码',
    id_card      char(18)         not null comment '身份证号',
    doctor_id    int unsigned     null comment '诊断医生id',
    left_img     varchar(300)     null comment '左眼图片',
    right_img    varchar(300)     null comment '右眼图片',
    left_diag    varchar(500)     null comment '左眼诊断结果关键字',
    right_diag   varchar(500)     null comment '右眼诊断结果关键字',
    res_info     varchar(500)     null comment '诊断结果信息',
    diag_time    date             null comment '诊断日期',
    create_time  datetime         null comment '创建时间',
    update_time  datetime         null comment '最后修改时间',
    allergy      varchar(200)     null comment '过敏药物',
    complaint    varchar(200)     null comment '主诉',
    visit        tinyint unsigned not null comment '初诊/复诊 1初诊 2复诊',
    pres_history varchar(200)     null comment '现病史',
    past_history varchar(200)     null comment '既往史',
    pos_feature  varchar(200)     null comment '阳体特征',
    neg_feature  varchar(200)     null comment '阴体特征',
    constraint id_card
        unique (id_card)
)
    comment '病人信息';

create table status
(
    id         int unsigned auto_increment comment 'id'
        primary key,
    ill        int unsigned     null comment '疾病名称 1正常 2糖尿病 3青光眼 4白内障 5AMD 6高血压 7近视 8其他异常',
    patient_id int unsigned     null comment 'id',
    info       varchar(50)      null comment '疾病信息描述',
    position   tinyint unsigned null comment '1左眼 2右眼'
)
    comment '病例状态表';


