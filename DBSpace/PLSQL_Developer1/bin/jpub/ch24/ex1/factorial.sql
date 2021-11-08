SET ECHO ON
SET TAB OFF
SET SERVEROUTPUT ON

REM 재귀호출을 사용한 팩토리얼 계산 함수
CREATE OR REPLACE FUNCTION factorial(a_num PLS_INTEGER)
RETURN NUMBER
IS
BEGIN
  IF a_num <= 1 THEN                      -- 종료 조건
    RETURN 1 ;                            -- 종료
  ELSE                              
    RETURN a_num * factorial(a_num - 1) ; -- 재귀 호출
  END IF ;
END ;
/

PAUSE

REM SELECT문에서 factorial 함수 호출
SELECT LEVEL 숫자, factorial(LEVEL)
FROM DUAL
 CONNECT BY LEVEL <= 10 ;
 
 
 -- BINARY_INTEGER와 NUMBER 타입이 "라이브러리를 이용" 하여 수치 연산을 하는 반면, 
 -- PLS_INTEGER는 실제 기계적인 연산(Machine arithmetic)을 수행하기 때문이라고 하네요.
-- -2147483647과 2147483647 사이의 signed 정수에 대한 기본형으로 정의 할 수 있습니다.



