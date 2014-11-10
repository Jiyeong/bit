/* DISTINCT 
    중복 데이터 제거
*/

/* 1) 모든 제품 번호를 출력하라. */
select /* all */ PNO from PRODUCTS;

/* 2) 모든 주문 제품의 번호를 출력하라. */
select /* all */ PNO from ORDERS;

/* 3) 주문한 제품이 무엇무엇이 있는지 목록을 출력하라. */
select distinct PNO from ORDERS;

select all PNO from ORDERS; /* all;중복 허용, 생략가능 */

/* ORDER BY 절
    출력 결과를 정렬
    문법 : order by /* ASC */컬럼명, 컬럼명, ...
          order by DESC 컬럼명, ASC 컬럼명, ...
    
    => 나열된 컬럼 순서대로 정렬한다. 기본은 상향 정렬(오름차순,ㄱ ~ ㅎ)이다.
    
    정렬조건 : ASE(ENDING) => 오름차순
              DESC(ENDING) => 내림차순
              
    ORDER BY 절 수행 후 SELECT 수행 (select는 늘 마지막.)
*/
/* 1) 기본 출력 */
select UID, UNAME, EMAIL from MEMBERS;

/* 2) 이름을 오름차순으로 정렬하라 */
select UID, UNAME, EMAIL from MEMBERS order by UNAME/*ASC*/;

/* 3) 이름을 내림차순으로 정렬하라 */
select UID, UNAME, EMAIL from MEMBERS order by UNAME desc;

/* 4) 주문 정보를 제품 번호의 오름차순으로 정렬하라 */
select * from ORDERS order by PNO;

/* 5) 주문 정보를 제품 번호의 오름차순으로 정렬하고,
      사용자 아이디로 오름차순으로 정렬하라.
 */
select * from ORDERS order by PNO, UID;

/* 6) 주문 정보를 제품 번호의 오름차순으로 정렬하고,
      사용자 아이디로 내림차순으로 정렬하라.
 */
select * from ORDERS order by PNO, UID desc;

/* 7) 주문 정보를 제품 번호의 오름차순으로 정렬하고,
      사용자 아이디로 내림차순으로 정렬하라.
      
   => 정렬을 먼저 한 다음 select를 실행한다.**   
 */
select ONO, ODATE from ORDERS order by PNO, UID desc;

/* 별명(alias) 붙이기
    문법 : select 컬럼렴 [as] 별명, ...
 */
select ONO as no, ODATE as 'Order Date', PNO 'Product No', UID id from ORDERS;
/* 공백이 들어갈 땐 '' 사용*.

/* WHERE 절
    문법 : where 조건1 (and | or) 조건2... 
*/

/**/
/* 연산자 사용 */
/* 1) 더하기 연산자 */
select ono, qty, qty * 500000 as total from ORDERS;

/* 2) 비교 연산자 */
select ono, qty from ORDERS
where qty > 2;

select ono, qty from ORDERS
where qty = 1;

select ono, qty from ORDERS
where qty > 1 and qty <= 5;

/* 3) 문자열 비교 */
select UID, UNAME, EMAIL from MEMBERS where UNAME = '홍길동';

/* '%'는 0개 이상의 글자 */
select UID, UNAME, EMAIL from MEMBERS where UNAME like '김%';

/* '_'는 1개의 글자 */
select UID, UNAME, EMAIL from MEMBERS where UNAME like '김_진';

/* 제품명에 '럭시'라는 글자를 포함한 모든 제품 선택하기
    => 주의! 검색 속도가 매우 느리다.
 */
select PNO, PNAME 
from PRODUCTS 
where PNAME like '%럭시%';

/* IN
    표현식 IN (값, 값, 값, ...)
    => 표현식이 IN에 들어있는 값과 일치하면 TRUE
*/
/* 삼성과 애플 제품을 출력하시오 */
select PNO, PNAME, MKNO
from PRODUCTS
where MKNO=1 or MKNO=2;

select PNO, PNAME, MKNO
from PRODUCTS
where MKNO in (1,2);

/* NULL 여부 검사 */

select * from PROD_PHOTS where PNO IS NULL;

select * from PROD_PHOTS where PNO IS NOT NULL;

/*컨텐츠 빈 것 찾을 때 content is null 노노! content ='' 예쓰!*/

/* BETWEEN A AND B
    
 */
select * from ORDERS
where QTY >= 1 and QTY <=3;

select * from ORDERS
where QTY between 1 and 3;

/* UNION => 결과의 결합 (합집합)*/
/* 두 개의 결과를 합쳐서 하나로 다루고 싶을 때*/
/* 예) 제품 이름과 제조사이름을 알고 싶다.*/
select PNAME from PRODUCTS
UNION
select MKNAME from MAKERS;

/* 예) 2014년 7월 이후의 주문 정보와 패블릿 및 패드 제품의 주문 정보 */

/* UNION => 두 결과 데이터를 합칠 때 중복 데이터 제거 */
select * from ORDERS where ODATE >='2014-7-1'
union 
select * from ORDERS where PNO in(1,2,3);

/* UNION ALL => 두 결과 데이터를 중복에 상관없이 합친다. */
select * from ORDERS where ODATE >='2014-7-1'
union all
select * from ORDERS where PNO in(1,2,3);

/* 7월 이후 주문 정보 중에서 애플 제품을 제외한 주문 정보 */
/* MySQL은 minus 없다. 다른 문법으로 대체해야 한다. */
/* select * from ORDERS where ODATE >= '2014-7-1'
minus
select * from ORDERS where PNO in(1,2,3);
 */

select * from ORDERS where ODATE >= '2014-7-1'
and PNO not in (1,2,3);

/* 서브 쿼리 */
/* 1) 주문 제품의 주문 번호화 제품명을 출력하라
 * --> select 절에 서브 쿼리 넣는 경우*/
select 
  ONO, 
  PNO,
  (select PNAME from PRODUCTS where PNO=T1.PNO) as NAME,
  QTY
from ORDERS T1;

/* 2) 검색어와 일치하는 회사 제품의 주문 정보를 출력하시오. 
 * --> where 절에 서브 쿼리 넣는 경우*/
select * from ORDERS
where PNO in (select PNO from PRODUCTS where MKNO=1);

/* 3) '2014-7-1' 이후에 주문한 정보 중에서 u01, u05가 주문한 것 
 * --> from 절에 서브 쿼리 넣는 경우*/
select * 
from (select * from ORDERS where ODATE >= '2014-7-1') as T1
where UID in ('u01','u05');













