drop table if exists tb_board cascade;
drop table if exists tb_member cascade;

drop sequence if exists seq_member cascade;
drop sequence if exists seq_board cascade;

create sequence seq_member;
create sequence seq_board;

create table tb_member
(
    id              bigint not null default nextval('seq_member') constraint pk_member_id primary key,
    email           varchar(255),
    username        varchar(255),
    password        varchar(255),
    alarm_date      timestamp,
    birth_day       date,
    grade           varchar(1),
    use_yn          varchar(1),
    created_by      bigint,
    created_date    timestamp default current_timestamp,
    updated_by      bigint,
    updated_date    timestamp default current_timestamp
);

create table tb_board
(
    id              bigint not null default nextval('seq_board') constraint pk_board_id primary key,
    board_type      varchar(255),
    contents        varchar(255),
    title           varchar(255),
    use_yn          varchar(1),
    created_by      bigint constraint fk_board_created_by references tb_member (id),
    created_date    timestamp default current_timestamp,
    updated_by      bigint constraint fk_board_updated_by references tb_member (id),
    updated_date    timestamp default current_timestamp
);
