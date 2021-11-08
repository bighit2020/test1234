create table member_proc(
    id varchar2(12),
    passwd varchar2(12),
    name varchar2(12),
    age number,
    addr varchar2(50),
    email varchar2(30)
);

select * from member_proc;

-- 저장 프로시저
create or replace procedure insertMember(
    myId varchar2,
    myPasswd varchar2,
    myName varchar2,
    myAge number,
    myAddr varchar2,
    myEmail varchar2
)
is
begin
    insert into member_proc values(myId, myPasswd, myName, myAge, myAddr, myEmail);
end;

