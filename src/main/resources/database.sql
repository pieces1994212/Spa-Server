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
角色';

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


create table pfp_function
(
  ID        decimal(16)  not null
  comment '唯一标志'
    primary key,
  NAME      varchar(256) not null
  comment '名称，显示在界面上的功能名称',
  NO        varchar(32)  not null
  comment '编码，一般唯一',
  SEQ       decimal(6)   not null
  comment '排序序号，一般用于界面显示',
  TYPE      decimal(6)   not null
  comment '类型，主要有菜单、菜单项、按钮、子页面、功能点',
  COMPONENT varchar(32)  null
  comment '路由对应组件名',
  PATH      varchar(512) null
  comment '功能的路由或接口的路径',
  PARENT_ID decimal(16)  null
  comment '上级ID',
  REMARK    varchar(512) null
  comment '详细说明',
  constraint PFP_FUNCTION_UK_NO
  unique (NO)
)
  comment 'PFP_FUNCTION
具体的功能，包括菜单、菜单项、按钮、子页面、功能点等，树形结构。';

create index PFP_FUNCTION_IDX_PARENT_ID
  on pfp_function (PARENT_ID);


create table pfp_role_func
(
  ID      decimal(16) not null
  comment '唯一标示'
    primary key,
  FUNC_ID decimal(16) not null
  comment '功能点ID',
  ROLE_ID decimal(16) not null
  comment '角色id',
  constraint PFP_ROLE_FUNC_IDX_ROLE_FUNC
  unique (FUNC_ID, ROLE_ID)
)
  comment 'PFP_ROLE_FUNCTION
功能点与角色关系';

create index PFP_ROLE_FUNC_IDX_ROLE_ID
  on pfp_role_func (ROLE_ID);