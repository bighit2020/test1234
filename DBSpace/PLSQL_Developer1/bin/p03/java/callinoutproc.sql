--scott계정에 생성하기



create or replace procedure javatest(
  p1 in varchar2(20),
  p2 in out varchar2(20),
  p3 out varchar2(20)
  ) as
begin
  p2:=p1 || p2;
  p3:=p1;
end;
/

 -- MYSQL 
  CREATE TABLE member (
    id INT(6)  auto_increment primary key,
    name VARCHAR(20)
  );
  INSERT INTO member(name) VALUES('hong');
    
  -- ORACLE
  CREATE TABLE member (
    id NUMBER(6)  primary key,
    name VARCHAR2(20)
  );
  
 CREATE SEQUENCE m_seq
      start with 0
      increment by 1
      minvalue 0;
       
 INSERT INTO member  VALUES(m_seq.nextval,'hong');