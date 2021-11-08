SET ECHO ON
SET TAB OFF
SET SERVEROUTPUT ON

REM 프로시저 예제
CREATE OR REPLACE PROCEDURE compute_power(a_num      IN     PLS_INTEGER, 
                                          a_exponent IN OUT PLS_INTEGER, --
                                          a_power    OUT    PLS_INTEGER)
IS
BEGIN
  -- 숫자 a_num의 a_exponent 거듭제곱 값을 구하여 a_power로 반환한다.
  -- 단, a_exponent가 음수이면 값을 0으로 변경하고 1을 반환한다.
  IF a_exponent < 0 THEN
    a_exponent := 0 ;-- 왼쪽항이므로  Out변수
  END IF ;
  a_power := 1 ;--왼쪽에 있으므로 Out 출력변수임
  FOR I IN 1 .. a_exponent
  LOOP
    a_power := a_power * a_num ;-- a_num은 오른쪽 항에 있으므로 In 변수
  END LOOP ;
END ;
/

