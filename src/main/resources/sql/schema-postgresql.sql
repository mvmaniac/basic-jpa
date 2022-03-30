drop table if exists tb_team cascade;
drop sequence if exists seq_team cascade;

create sequence seq_team;

create table tb_team
(
    id              bigint not null default nextval('seq_team') constraint pk_team_id primary key,
    title           varchar(255),
    description     varchar(1000),
    team_type       varchar(255),
    use_yn          varchar(1),
    created_by      varchar(20),
    created_date    timestamp default current_timestamp,
    updated_by      varchar(20),
    updated_date    timestamp default current_timestamp
);
