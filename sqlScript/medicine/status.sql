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

