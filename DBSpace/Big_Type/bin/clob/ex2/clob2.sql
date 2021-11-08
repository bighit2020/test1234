drop table test_tbl;

create table test_tbl(
	REC_KEY number(8) primary key,--키
	USER_ID varchar2(50) not null, --
	USER_PW varchar2(50) not null,
	PHOTO CLOB                    -- 사용자 사진
);