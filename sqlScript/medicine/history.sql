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
    neg_feature  varchar(200)     null comment '阴体特征',
    constraint id_card
        unique (id_card),
    constraint phone
        unique (phone)
)
    comment '病人历史诊断记录';

