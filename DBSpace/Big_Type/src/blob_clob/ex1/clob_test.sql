CREATE TABLE CAR (
        id NUMBER(6) PRIMARY KEY NOT NULL,
        name VARCHAR2(20),
        price NUMBER(10),
        review  CLOB);
        
INSERT INTO CAR VALUES (100, 'SM7', 25000000, '삼성에서 나온 SM7을 타보니 정말 편안했다.');
INSERT INTO CAR VALUES (200, 'PORTER', 13000000, '역시 1톤트럭의 대표자!!');
INSERT INTO CAR VALUES (300, 'VERNA', 9000000, '싸고 좋은 거 같아용~^^*');
INSERT INTO CAR VALUES (400, 'SANTAFE', 33000000, EMPTY_CLOB());
