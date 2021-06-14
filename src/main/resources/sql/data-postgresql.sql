-- 회원
insert into tb_member(email, username, password, birth_day, alarm_date, grade, use_yn, created_by, updated_by)
values('dev1@gmail.com', '개발자1', '1234', '1985-01-15', '1985-01-15T23:38:20.268972', 'N', 'Y', currval('seq_member'), currval('seq_member'))
;

insert into tb_member(email, username, password, birth_day, alarm_date, grade, use_yn, created_by, updated_by)
values('dev2@gmail.com', '개발자2', '1234', '1985-02-15', '1985-02-15T23:38:20.268972', 'N', 'Y', currval('seq_member'), currval('seq_member'))
;

-- 게시판
insert into tb_board(board_type, title, contents, use_yn, created_by, updated_by)
values('FREE', '자유게시판 제목 1', '자유게시판 내용 1', 'Y', 1, 1)
;

insert into tb_board(board_type, title, contents, use_yn, created_by, updated_by)
values('FREE', '자유게시판 제목 2', '자유게시판 내용 2', 'Y', 2, 2)
;

insert into tb_board(board_type, title, contents, use_yn, created_by, updated_by)
values('FREE', '자유게시판 제목 3', '자유게시판 내용 3', 'Y', 1, 1)
;

insert into tb_board(board_type, title, contents, use_yn, created_by, updated_by)
values('FREE', '자유게시판 제목 4', '자유게시판 내용 4', 'Y', 2, 2)
;

insert into tb_board(board_type, title, contents, use_yn, created_by, updated_by)
values('FREE', '자유게시판 제목 5', '자유게시판 내용 5', 'Y', 1, 1)
;

insert into tb_board(board_type, title, contents, use_yn, created_by, updated_by)
values('FREE', '자유게시판 제목 6', '자유게시판 내용 6', 'Y', 2, 2)
;

insert into tb_board(board_type, title, contents, use_yn, created_by, updated_by)
values('FREE', '자유게시판 제목 7', '자유게시판 내용 7', 'Y', 1, 1)
;

insert into tb_board(board_type, title, contents, use_yn, created_by, updated_by)
values('FREE', '자유게시판 제목 8', '자유게시판 내용 8', 'Y', 2, 2)
;

insert into tb_board(board_type, title, contents, use_yn, created_by, updated_by)
values('FREE', '자유게시판 제목 9', '자유게시판 내용 9', 'Y', 1, 1)
;

insert into tb_board(board_type, title, contents, use_yn, created_by, updated_by)
values('FREE', '자유게시판 제목 10', '자유게시판 내용 10', 'Y', 2, 2)
;

insert into tb_board(board_type, title, contents, use_yn, created_by, updated_by)
values('FREE', '자유게시판 제목 11', '자유게시판 내용 11', 'Y', 1, 1)
;

insert into tb_board(board_type, title, contents, use_yn, created_by, updated_by)
values('FREE', '자유게시판 제목 12', '자유게시판 내용 12', 'Y', 2, 2)
;
