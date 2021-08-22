DROP SEQUENCE SEQ_JOB_CODE;
DROP SEQUENCE SEQ_EMP_CODE;
DROP SEQUENCE SEQ_MENU_CODE;
DROP SEQUENCE SEQ_PAY_CODE;
DROP SEQUENCE SEQ_ORDER_CODE;
DROP SEQUENCE SEQ_CATEGORY_CODE;

CREATE SEQUENCE SEQ_JOB_CODE;
CREATE SEQUENCE SEQ_EMP_CODE;
CREATE SEQUENCE SEQ_MENU_CODE;
CREATE SEQUENCE SEQ_PAY_CODE;
CREATE SEQUENCE SEQ_ORDER_CODE;
CREATE SEQUENCE SEQ_CATEGORY_CODE;

DROP TABLE DAILY_SALES;
DROP TABLE MENU_ORDER;
DROP TABLE POS_ORDER;
DROP TABLE MENU;
DROP TABLE MENU_SIZE;
DROP TABLE CATEGORY;
DROP TABLE HR;
DROP TABLE MEMBERSHIP;
DROP TABLE PAYMENT;
DROP TABLE EMPLOYEE;
DROP TABLE JOB;

-------------------- JOB TABLE -------------------------------------------------
CREATE TABLE JOB (
  -- COLUMN LEVEL CONSTRAINT
  JOB_CODE NUMBER,
  JOB_NAME VARCHAR2(100) CONSTRAINT NN_JOB_NAME NOT NULL,
  -- TABLE LEVEL CONSTRAINT
  CONSTRAINT PK_JOB_CODE PRIMARY KEY(JOB_CODE),
  CONSTRAINT UI_JOB_NAME UNIQUE(JOB_NAME)
);

COMMENT ON COLUMN JOB.JOB_CODE IS '직급코드';
COMMENT ON COLUMN JOB.JOB_NAME IS '직급명';

INSERT
  INTO JOB
VALUES 
(
SEQ_JOB_CODE.NEXTVAL, '관리자'
);

INSERT
  INTO JOB
VALUES 
(
SEQ_JOB_CODE.NEXTVAL, '직원'
);

INSERT 
  INTO JOB
VALUES 
(
SEQ_JOB_CODE.NEXTVAL, '수습'
);

------------------ EMPLOYEE TABLE ----------------------------------------------
CREATE TABLE EMPLOYEE (
  -- COLUMN LEVEL CONSTRAINT
  EMP_CODE NUMBER,
  EMP_NAME VARCHAR(100) CONSTRAINT NN_EMP_NAME NOT NULL,
  EMP_PHONE VARCHAR(100),
  JOB_CODE NUMBER,
  EMP_ID VARCHAR(100) CONSTRAINT NN_EMP_ID NOT NULL,
  EMP_PW VARCHAR(100) CONSTRAINT NN_EMP_PW NOT NULL,
  -- TABLE LEVEL CONSTRAINT
  CONSTRAINT PK_EMP_CODE PRIMARY KEY(EMP_CODE),
  CONSTRAINT UK_EMP_PHONE UNIQUE(EMP_PHONE),
  CONSTRAINT UK_EMP_ID UNIQUE(EMP_ID),
  CONSTRAINT FK_CATEGORY_CODE FOREIGN KEY(JOB_CODE) REFERENCES JOB(JOB_CODE)
);

COMMENT ON COLUMN EMPLOYEE.EMP_CODE IS '직원코드';
COMMENT ON COLUMN EMPLOYEE.EMP_NAME IS '직원이름';
COMMENT ON COLUMN EMPLOYEE.EMP_PHONE IS '전화번호';
COMMENT ON COLUMN EMPLOYEE.JOB_CODE IS '직급코드';
COMMENT ON COLUMN EMPLOYEE.EMP_ID IS '아이디';
COMMENT ON COLUMN EMPLOYEE.EMP_PW IS '비밀번호';

INSERT 
  INTO EMPLOYEE 
VALUES 
(
SEQ_EMP_CODE.NEXTVAL, '한정욱', '010-5683-6224', 1, 'boss', 'hellyeah'
);

INSERT 
  INTO EMPLOYEE 
VALUES 
(
SEQ_EMP_CODE.NEXTVAL, '김준우', '010-8373-2349', 2, 'emp01', 'pass01'
);

INSERT 
  INTO EMPLOYEE 
VALUES 
(
SEQ_EMP_CODE.NEXTVAL, '김지원', '010-3532-7723', 2, 'emp02', 'pass02'
);

--------------------- HR TABLE -------------------------------------------------
CREATE TABLE HR (
  -- COLUMN LEVEL CONSTRAINT
  HR_DATE DATE DEFAULT SYSDATE,
  EMP_CODE NUMBER,
  CLOCK_IN DATE DEFAULT SYSDATE CONSTRAINT NN_CLOCK_IN NOT NULL,
  CLOCK_OUT DATE DEFAULT SYSDATE CONSTRAINT NN_CLOCK_OUT NOT NULL,
  -- TABLE LEVEL CONSTRAINT
  CONSTRAINT PK_HR_DATE_EMP_CODE PRIMARY KEY(HR_DATE, EMP_CODE),
  CONSTRAINT FK_EMP_CODE FOREIGN KEY(EMP_CODE) REFERENCES EMPLOYEE(EMP_CODE)
);

COMMENT ON COLUMN HR.HR_DATE IS '날짜';
COMMENT ON COLUMN HR.EMP_CODE IS '직원코드';
COMMENT ON COLUMN HR.CLOCK_IN IS '출근시간';
COMMENT ON COLUMN HR.CLOCK_OUT IS '퇴근시간';





-------------------- CATEGORY TABLE --------------------------------------------
CREATE TABLE CATEGORY (
  CATEGORY_CODE NUMBER
, CATEGORY_NAME VARCHAR2(100) CONSTRAINT NN_CATEGORY_NAME NOT NULL
, CONSTRAINT PK_CATEGORY_CODE PRIMARY KEY(CATEGORY_CODE)
);

COMMENT ON COLUMN CATEGORY.CATEGORY_CODE IS '카테고리코드';
COMMENT ON COLUMN CATEGORY.CATEGORY_NAME IS '카테고리명';

INSERT 
  INTO CATEGORY 
VALUES 
(
SEQ_CATEGORY_CODE.NEXTVAL, '커피'
);

INSERT 
  INTO CATEGORY 
VALUES 
(
SEQ_CATEGORY_CODE.NEXTVAL, '음료'
);

INSERT 
  INTO CATEGORY 
VALUES 
(
SEQ_CATEGORY_CODE.NEXTVAL, '빵'
);

--------------- MENU_SIZE TABLE ------------------------------------------------
CREATE TABLE MENU_SIZE(
  SIZE_CODE NUMBER
, SIZE_NAME VARCHAR2(100) CONSTRAINT NN_SIZE_NAME NOT NULL
, SIZE_PRICE NUMBER CONSTRAINT NN_SIZE_PRICE NOT NULL
, CONSTRAINT PK_SIZE_CODE PRIMARY KEY(SIZE_CODE)
);

COMMENT ON COLUMN MENU_SIZE.SIZE_CODE IS '사이즈코드';
COMMENT ON COLUMN MENU_SIZE.SIZE_NAME IS '사이즈명';
COMMENT ON COLUMN MENU_SIZE.SIZE_PRICE IS '사이즈별 가격';

INSERT
  INTO MENU_SIZE
VALUES
(
  1, 'Regular', 0
);

INSERT
  INTO MENU_SIZE
VALUES
(
  2, 'Large', 500
);

INSERT
  INTO MENU_SIZE
VALUES
(
  3, 'one size', 0
);

--------------- MENU TABLE -----------------------------------------------------
CREATE TABLE MENU(
  MENU_CODE NUMBER CONSTRAINT PK_MENU_CODE PRIMARY KEY
, MENU_NAME VARCHAR2(100) CONSTRAINT NN_MENU_NAME NOT NULL
, UNIT_PRICE NUMBER CONSTRAINT NN_UNIT_PRICE NOT NULL
, CATEGORY_CODE NUMBER
, CONSTRAINT FK_CATEGORY_CODE2 FOREIGN KEY (CATEGORY_CODE) REFERENCES CATEGORY (CATEGORY_CODE)
, CONSTRAINT UK_MENU_NAME UNIQUE(MENU_NAME)
);

COMMENT ON COLUMN MENU.MENU_CODE IS '메뉴코드';
COMMENT ON COLUMN MENU.MENU_NAME IS '메뉴명';
COMMENT ON COLUMN MENU.UNIT_PRICE IS '단가';
COMMENT ON COLUMN MENU.CATEGORY_CODE IS '카테고리코드';

INSERT 
  INTO MENU
(
MENU_CODE, MENU_NAME, UNIT_PRICE, CATEGORY_CODE
) 
VALUES
(
SEQ_MENU_CODE.NEXTVAL, '아메리카노', 3500, 1
);

INSERT 
  INTO MENU
(
MENU_CODE, MENU_NAME, UNIT_PRICE, CATEGORY_CODE
) 
VALUES
(
SEQ_MENU_CODE.NEXTVAL, '바닐라라떼', 4500, 1
);




INSERT
  INTO MENU
(
MENU_CODE, MENU_NAME, UNIT_PRICE, CATEGORY_CODE
) 
VALUES
(
SEQ_MENU_CODE.NEXTVAL, '레몬에이드', 4500, 2
);

INSERT
  INTO MENU
(
MENU_CODE, MENU_NAME, UNIT_PRICE, CATEGORY_CODE
) 
VALUES
(
SEQ_MENU_CODE.NEXTVAL, '플레인요거트스무디', 4500, 2
);



INSERT
  INTO MENU
(
MENU_CODE, MENU_NAME, UNIT_PRICE, CATEGORY_CODE
) 
VALUES
(
SEQ_MENU_CODE.NEXTVAL, '초코크로플', 2000, 3
);

INSERT
  INTO MENU
(
MENU_CODE, MENU_NAME, UNIT_PRICE, CATEGORY_CODE
) 
VALUES
(
SEQ_MENU_CODE.NEXTVAL, '크로아상', 3000, 3
);

INSERT
  INTO MENU
(
MENU_CODE, MENU_NAME, UNIT_PRICE, CATEGORY_CODE
) 
VALUES
(
SEQ_MENU_CODE.NEXTVAL, '베이글', 2500, 3
);

----------------------- PAYMENT TABLE ------------------------------------------
CREATE TABLE PAYMENT (
  PAY_CODE NUMBER
, PAY_METHOD VARCHAR2(100) CONSTRAINT NN_PAY_METHOD NOT NULL
, CONSTRAINT PK_PAY_CODE PRIMARY KEY(PAY_CODE)
);
COMMENT ON COLUMN PAYMENT.PAY_CODE IS '결제수단코드';
COMMENT ON COLUMN PAYMENT.PAY_METHOD IS '결제방법';

INSERT 
  INTO PAYMENT 
VALUES 
(
SEQ_PAY_CODE.NEXTVAL, '현금'
);

INSERT 
  INTO PAYMENT 
VALUES 
(
SEQ_PAY_CODE.NEXTVAL, '포인트'
);

-------------------- MEMBERSHIP TABLE ------------------------------------------
CREATE TABLE MEMBERSHIP(
  MEM_CODE NUMBER CONSTRAINT PK_MEM_CODE PRIMARY KEY
, MEM_NAME VARCHAR2(100) CONSTRAINT NN_MEM_NAME NOT NULL
, MEM_PHONE VARCHAR2(100) CONSTRAINT NN_MEM_PHONE NOT NULL
, MEM_POINT VARCHAR2(100) CONSTRAINT NN_MEM_POINT NOT NULL
, MEM_YN VARCHAR2(100) CONSTRAINT NN_MEM_YN NOT NULL
, CONSTRAINT UK_MEM_PHONE UNIQUE(MEM_PHONE)
, CONSTRAINT CK_MEM_YN CHECK(MEM_YN IN ('Y', 'N'))
);

COMMENT ON COLUMN MEMBERSHIP.MEM_CODE IS '회원코드';
COMMENT ON COLUMN MEMBERSHIP.MEM_NAME IS '이름';
COMMENT ON COLUMN MEMBERSHIP.MEM_PHONE IS '전화번호';
COMMENT ON COLUMN MEMBERSHIP.MEM_POINT IS '포인트';
COMMENT ON COLUMN MEMBERSHIP.MEM_YN IS '탈퇴여부';

----------------------- POS_ORDER TABLE ------------------------------
CREATE TABLE POS_ORDER(
  ORDER_CODE NUMBER CONSTRAINT PK_ORDER_CODE PRIMARY KEY
, ORDER_DATE DATE CONSTRAINT NN_ORDER_DATE NOT NULL
, PAY_METHOD_CODE NUMBER
, MEM_CODE NUMBER 
, CONSTRAINT FK_PAY_METHOD_CODE FOREIGN KEY (PAY_METHOD_CODE) REFERENCES PAYMENT (PAY_CODE)
, CONSTRAINT FK_MEM_CODE FOREIGN KEY (MEM_CODE) REFERENCES MEMBERSHIP (MEM_CODE)
);

COMMENT ON COLUMN POS_ORDER.ORDER_CODE IS '결제수단코드';
COMMENT ON COLUMN POS_ORDER.OREDER_DATE IS '결제날짜';
COMMENT ON COLUMN POS_ORDER.PAY_METHOD_CODE IS '결제수단코드';
COMMENT ON COLUMN POS_ORDER.MEM_CODE IS '회원번호';

------------------------- MENU_ORDER TABLE -------------------------------------
CREATE TABLE MENU_ORDER(
  ORDER_CODE NUMBER
, MENU_CODE NUMBER
, QUAN NUMBER CONSTRAINT NN_QUAN NOT NULL
, SIZE_CODE NUMBER CONSTRAINT NN_SIZE_CODE NOT NULL
, CONSTRAINT PK_MENU_ORDER_CODE PRIMARY KEY(MENU_CODE, ORDER_CODE)
, CONSTRAINT FK_SIZE_CODE2 FOREIGN KEY (SIZE_CODE) REFERENCES MENU_SIZE (SIZE_CODE)
, CONSTRAINT FK_MENU_CODE FOREIGN KEY (MENU_CODE) REFERENCES MENU (MENU_CODE)
, CONSTRAINT FK_ORDER_CODE FOREIGN KEY (ORDER_CODE) REFERENCES POS_ORDER (ORDER_CODE)
);

COMMENT ON COLUMN MENU_ORDER.ORDER_CODE IS '주문코드';
COMMENT ON COLUMN MENU_ORDER.MENU_CODE IS '메뉴코드';
COMMENT ON COLUMN MENU_ORDER.QUAN IS '수량';
COMMENT ON COLUMN MENU_ORDER.SIZE_CODE IS '사이즈코드';

------------------------- DAILY_SALES ------------------------------------------ 
CREATE TABLE DAILY_SALES(
  SALES_DATE DATE
, SALES NUMBER DEFAULT 0 CONSTRAINT NN_SALES NOT NULL
, REFUND NUMBER DEFAULT 0 CONSTRAINT NN_REFUND NOT NULL
, TOTAL_SALES NUMBER DEFAULT 0 CONSTRAINT NN_TOTAL_SALES NOT NULL
, CONSTRAINT PK_SALES_DATE PRIMARY KEY(SALES_DATE)
);

COMMENT ON COLUMN DAILY_SALES.SALES_DATE IS '날짜';
COMMENT ON COLUMN DAILY_SALES.SALES IS '판매금액';
COMMENT ON COLUMN DAILY_SALES.REFUND IS '환불금액';
COMMENT ON COLUMN DAILY_SALES.TOTAL_SALES IS '총매출금액';

INSERT 
  INTO DAILY_SALES 
VALUES 
(
SYSDATE, 100000, 10000, 90000
);


