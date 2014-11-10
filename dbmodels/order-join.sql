/* 1) CROSS 조인
      두 개의 테이블 데이터를 M:N 조인한다.
 */
select ONO, PNO, QTY from ORDERS;
select PNO, PNAME from PRODUCTS;

select ONO, T1.PNO, T1.QTY, PNAME 
from ORDERS T1, PRODUCTS T2;
/* ORDERS 7 * PRODUCTS 8 = 56 */

/* 2) NATURAL 조인
      두 개 테이블의 공통 컬럼을 기준으로 조인한다.
      외부키를 기준으로 조인을 수행한다.
 */
select ONO, T1.PNO, T1.QTY, PNAME 
from ORDERS T1, PRODUCTS T2
where T1.PNO = T2.PNO;

/* 2) NATURAL 조인 => T1 JOIN T2 using(컬럼명, 컬럼명, ...)
      단, 조인 할 때 기준이 되는 컬럼명이 같아야 한다.
 */
select ONO, T1.PNO, T1.QTY, PNAME 
from ORDERS T1 join PRODUCTS T2 using(PNO);

/* 2) NATURAL 조인 => T1 JOIN T2 on 조인조건1...
      조인의 기준이 되는 컬럼명이 다를 때 사용
 */
select ONO, T1.PNO, T1.QTY, PNAME 
from ORDERS T1 join PRODUCTS T2 on T1.PNO = T2.PNO;

/* O3) OUTER 조인

 */
/* 모든 제품의 정보를 출력하되, 사진 정보도 함께 출력하라. */
/*다음질의문은 조인 가능한 결과만 출력한다.*/
select T1.PNO, T1.PNAME, T2.URL
from PRODUCTS T1 join PROD_PHOTS T2 on T1.PNO = T2.PNO;

/* 조인이 불가능하더라도, 즉 조인할 데이터가 상대 테이블에 없더라도 
   반드시 기준이 되는 테이블의 데이터를 모두 출력하고 싶다면 
   OUTER 조인을 사용하라.!
   문법 : T1 left outer join T2 on 조인조건1...
        ; 왼쪽 T1 테이블을 기준으로 T2 테이블과 조인하라.
          기준 테이블인 T1의 데이터는 모두 출력될 것이다.
 */
select T1.PNO, T1.PNAME, T2.URL
from PRODUCTS T1 left outer join PROD_PHOTS T2 on T1.PNO = T2.PNO;

/* 사진 테이블을 기준으로 조인을 하고 싶다면 */
select T1.PNO, T1.PNAME, T2.URL
from PRODUCTS T1 right outer join PROD_PHOTS T2 on T1.PNO = T2.PNO;

/* 문제 : 다음 결과를 출력하시오
 * 주문 번호, 제품명, 제조사명, 주문수량, 잔여수량, 고객명, 고객이메일
 * o_ONO, p_PNAME, MA_MKNAME, o_QTY, p_QTY, m_UNAME, m_EMAIL 
 * orders3 products2 makers1 members4 table join!
 */
select T3.ONO, T2.PNAME, T1.MKNAME, T3.QTY, T2.QTY, T4.UNAME, T4.EMAIL
from MAKERS T1 left outer join PRODUCTS T2 on T1.MKNO = T2.MKNO
left outer join ORDERS T3 on T2.PNO = T3.PNO
left outer join MEMBERS T4 on T3.UID = T4.UID;

