create table pfp_role
(
  ID   decimal(16) not null
  comment '唯一标识'
    primary key,
  NAME varchar(32) not null
  comment '名称',
  NO   varchar(32) null
  comment '编号',
  constraint PFP_ROLE_UK_NO
  unique (NO)
)
  comment 'PFP_ROLE
��ɫ';

create table pfp_user
(
  ID           decimal(16)  not null
  comment '唯一标志'
    primary key,
  NO           varchar(32)  null
  comment '编号，一般为员工工号',
  NAME         varchar(128) not null
  comment '姓名或名称',
  LOGIN_NAME   varchar(128) not null
  comment '登陆账号，唯一。',
  LOGIN_PWD    varchar(128) not null
  comment '登陆密码，已加密后密文形式保存。',
  LOGIN_COUNT  decimal(6)   null,
  LOCK_FLAG    decimal(6)   null,
  LOCK_TIME    datetime     null
  comment '锁定账号的时间，如果有表示用户已经锁定，不能登陆。',
  LOCK_REASON  varchar(256) null
  comment '锁定账号的原因，和锁定时间配合使用。',
  DISABLE_FLAG decimal(6)   null,
  DISABLE_TIME datetime     null
  comment '停用时间',
  MOBILE_TEL   varchar(32)  null
  comment '移动电话',
  EMAIL        varchar(256) null
  comment '电子邮件',
  constraint PFP_USER_IDX_LOGIN_NAME
  unique (LOGIN_NAME),
  constraint PFP_USER_UK_NO
  unique (NO)
)
  comment 'PFP_USER
管理用户，使用系统的人员。';

create index PFP_USER_IDX_MOBILE_TEL
  on pfp_user (MOBILE_TEL);

create index PFP_USER_IDX_NAME
  on pfp_user (NAME);

create table pfp_user_role
(
  ID      decimal(16) not null
  comment '唯一标志'
    primary key,
  USER_ID decimal(16) not null
  comment '用户ID',
  ROLE_ID decimal(16) not null
  comment '角色ID',
  constraint PFP_USER_ROLE_IDX_USER_ROLE
  unique (USER_ID, ROLE_ID)
)
  comment 'PFP_USER_ROLE
用户角色关系';

create index PFP_USER_ROLE_IDX_ROLE_ID
  on pfp_user_role (ROLE_ID);
