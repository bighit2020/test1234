-- Trigger 연습

:OLD   : 참조 전의 열의 값(기존에 들어 있는 값)
         (insert: 입력전 자료, update: 수정전 자료,delete:삭제할 자료)
 :NEW   : 참조 후의 열의 값(Insert,Update,Delete가 이루어 질때 넘어 가는 값
              (insert: 입력할 자료, update: 수정후 자료)

[바인드 입력창이 나오면 => 데이터 없이 "적용"]해서 넘긴다.

[상품-입고] 트리거 작성하기
 : 상품(Parent)에 대한 입고(Child) 처리를 위한 트리거 만들기
 : 입고 테이블에 상품이 입력되면, 입고 수량을 상품 테이블의 재고 수량에 추가하는 트리거 작성
   
   drop table 상품;
   drop table 입고; 
   
1. 입고 트리거 작성하기
 (1) 상품 테이블 생성
 Create table 상품(
 상품코드 char(6) primary key,
 상품명 varchar2(12) not null,
 제조사 varchar2(12),
 소비자가격 number(8),
 재고수량 number DEfault 0
 );
  
 (2) 입고 테이블 생성
 Create table 입고(
 입고번호 number(6) primary key,
 상품코드 char(6) References 상품(상품코드),
 입고일자 Date Default SYSDATE,
 입고수량 number(6),
 입고단가 number(8),
 입고금액 number(8)
 );
 
 (3) 상품 테이블에 데이터 입력(3개행)
 Insert Into 상품(상품코드,상품명,제조사,소비자가격) values('A001','세탁기','LG',500);
 Insert Into 상품(상품코드,상품명,제조사,소비자가격) values('A002','컴퓨터','LG',700);
 Insert Into 상품(상품코드,상품명,제조사,소비자가격) values('A003','냉장고','삼성',600);
 commit;
 select * from 상품;
 select * from 입고;
 
 (4) 입고 트리거 작성(trigger_enter.sql)
 Create Or replace Trigger trg_11
 After Insert On 입고 For Each Row
 Begin
  Update 상품  Set 재고수량 = 재고수량+:new.입고수량
  where 상품코드 = :new.상품코드;
 END;
 [참고] 위에서 :new.입고수량  의미는 insert시에 날라가는 입고수량 값
                    :new.상품코드 도 마찬가지로 insert시에 날라가는 상품코드 값
 
 (5) 입고 테이블 데이터 추가(2개행)
Insert Into 입고(입고번호,상품코드,입고수량,입고단가,입고금액) values(1,'A001',5,320,1600);

select * from 입고;
select * from 상품;
Insert Into 입고(입고번호,상품코드,입고수량,입고단가,입고금액) values(2,'A002',10,680,6800);
Insert Into 입고(입고번호,상품코드,입고수량,입고단가,입고금액) values(3,'A003',3,220,6600);
Insert Into 입고(입고번호,상품코드,입고수량,입고단가,입고금액) values(4,'A003',5,220,1100);
commit;

2. 갱신 트리거 작성하기
 (1) 갱신 트리거(trigger_update.sql)
 create or replace trigger trg_22
 After update On 입고 For Each Row
 Begin
  Update 상품  Set 재고수량 = 재고수량+(- :old.입고수량 + :new.입고수량)
  where 상품코드 = :new.상품코드;
 END;
 [참고] :old.입고수량 은 기존에 들어 있는 값
          :new.입고수량 은 update 시에 넘어가는 새로운 입고수량 값
 
 
 (2) 갱신 (데이터)
 Update 입고 set 입고수량=10,입고금액=2200 where 입고번호=3;
 
 select * from 입고 order by 입고번호;
 select * from 상품;

3. 삭제 트리거 작성하기
  (1) 삭제 트리거(trriger_del.sql)
 create or replace trigger trg_33
 After delete On 입고 For Each Row
 Begin
  Update 상품  Set 재고수량 = 재고수량- :old.입고수량
  where 상품코드 = :old.상품코드;
 END;
  
  (2) 삭제( 데이터)
 delete 입고 where 입고번호=3;
 select * from 입고 order by 입고번호;
 select * from 상품;