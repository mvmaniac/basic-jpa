insert into tb_member(email, username, password, birth_day, alarm_date, grade, use_yn, created_by, updated_by)
values('dev1@gmail.com', '개발자1', '1234', '1985-01-15', '1985-01-15T23:38:20.268972', 'N', 'Y', currval('seq_member'), currval('seq_member'))
;
