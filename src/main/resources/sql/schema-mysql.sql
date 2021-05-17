drop table if exists tb_board cascade;
drop table if exists tb_member cascade;

create table tb_member
(
    id           bigint auto_increment primary key,
    email        varchar(255) null,
    username     varchar(255) null,
    password     varchar(255) null,
    grade        varchar(255) null,
    alarm_date   datetime,
    birth_day    date,
    use_yn       varchar(1) null,
    created_by   bigint null,
    created_date datetime default current_timestamp,
    updated_by   bigint null,
    updated_date datetime default current_timestamp
);

create table tb_board
(
    id           bigint auto_increment primary key,
    board_type   varchar(255) null,
    contents     varchar(255) null,
    title        varchar(255) null,
    use_yn       varchar(1) null,
    created_by   bigint null,
    created_date datetime default current_timestamp,
    updated_by   bigint null,
    updated_date datetime default current_timestamp,
    constraint fk_board_created_by foreign key (created_by) references tb_member (id),
    constraint fk_board_updated_by foreign key (updated_by) references tb_member (id)
);

