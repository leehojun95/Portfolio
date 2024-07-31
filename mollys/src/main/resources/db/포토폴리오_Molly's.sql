/*
작성자 : 이호준
프로젝트 시작일 : 2024-06-17
*/


-- 1.회원테이블   
CREATE TABLE MBL_TBL ( 
    MBL_ID             VARCHAR2(20)     NOT NULL,
    MBL_PASSWORD       CHAR(60)         NOT NULL,
    MBL_EMAIL          VARCHAR2(100)    NOT NULL,
    MBL_NAME           VARCHAR2(20)     NOT NULL,
    Mbl_Brith          VARCHAR2(10)     NOT NULL,
    MBL_ZIP_CODE       CHAR(5)          NOT NULL,
    MBL_ADDR           VARCHAR2(100)    NOT NULL,
    MBL_ADDR_DETAIL    VARCHAR2(100)    NOT NULL,
    MBL_PHONE          VARCHAR2(30)     NOT NULL,
    MBL_GENDER         CHAR(1)          NOT NULL,
    MBL_POINT          NUMBER           DEFAULT 0 NOT NULL,
    MBL_REGDATE        DATE DEFAULT SYSDATE,
    MBL_UPDATEDATE     DATE DEFAULT SYSDATE
);
-- Premiere key 추가(MBL_ID)
ALTER TABLE MBL_TBL ADD CONSTRAINT pk_MBL_TBL PRIMARY KEY(MBL_ID);

-- SNS로그인(카카오, 네이버) 컬럼 추가
ALTER TABLE MBL_TBL ADD SNS_LOGIN_TYPE CHAR(1) DEFAULT 'N' NOT NULL;

COMMIT;

/*
-- 카카오로그인
CREATE TABLE KAKAO_TBL(
    ID          NUMBER          NOT NULL,
    NICKNAME    VARCHAR2(50)    NOT NULL,
    EMAIL       VARCHAR2(100)   NOT NULL
);

-- 네이버 로그인
CREATE TABLE NAVER_TBL(
    ID          NUMBER          NOT NULL,
    NICKNAME    VARCHAR2(50)    NOT NULL,
    EMAIL       VARCHAR2(100)   NOT NULL
);

-- SNS 로그인으로 통합
*/


-- SNS 로그인
CREATE TABLE SNS_TBL(
    SNS_ID          VARCHAR2(50)    NOT NULL,
    SNS_NICKNAME    VARCHAR2(50)    NOT NULL,
    SNS_EMAIL       VARCHAR2(100)   NOT NULL,
    SNS_TYPE    CHAR(5)         NOT NULL
);


-- 2. 관리자 테이블
CREATE TABLE ADMIN_TBL (
    ADMIN_ID    VARCHAR2(15) PRIMARY KEY,
    ADMIN_PW    CHAR(60)    NOT NULL,
    ADMIN_VISIT_DATE    DATE
);

-- Premiere key 추가(ADMIN_ID)
ALTER TABLE ADMIN_TBL ADD CONSTRAINT pk_ADMIN_TBL PRIMARY KEY(ADMIN_ID);

-- 관리자 아이디 및 비밀번호(암호화 : 1234) 
INSERT INTO ADMIN_TBL(ADMIN_ID, ADMIN_PW) VALUES('admin', '$2a$10$EMlOnefqIZm3c6UxvTVc5eJIRaM41HQh.tj/vdks9t0LfRoHQ5Y0C');

COMMIT;


-- 3. 상품정보 테이블
CREATE TABLE PRODUCT_TBL(
        PRO_NUM             NUMBER,
        CTG_H_CODE          NUMBER                 NULL,
        PRO_NAME            VARCHAR2(50)            NOT NULL,
        PRO_PRICE           NUMBER                  NOT NULL,
        PRO_DISCOUNT        NUMBER                  NOT NULL,
        PRO_PUBLISHER       VARCHAR2(50)            NOT NULL,
        PRO_CONTENT         VARCHAR2(4000)          NOT NULL,    
        PRO_UP_FOLDER       VARCHAR(50)             NOT NULL, 
        PRO_IMG             VARCHAR(100)             NOT NULL, 
        PRO_AMOUNT          NUMBER                  NOT NULL,
        PRO_BUY             CHAR(1)                 NOT NULL,  
        PRO_DATE            DATE DEFAULT SYSDATE    NOT NULL,
        PRO_UPDATEDATE      DATE DEFAULT SYSDATE    NOT NULL
);

-- Premiere key 추가(PRO_NUM)
ALTER TABLE PRODUCT_TBL ADD CONSTRAINT pk_PRODUCT_TBL PRIMARY KEY(PRO_NUM);

-- 시퀀스 추가(PRO_NUM)
CREATE SEQUENCE SEQ_PRO_NUM;

COMMIT;


-- 4. 카테고리
CREATE TABLE CATEGORY_TBL(
    CTG_H_CODE      NUMBER       PRIMARY KEY,
    CTG_L_CODE      NUMBER      NULL,
    CTG_NAME        VARCHAR2(30)    NOT NULL
);

-- Premiere key 추가(CTG_H_CODE)
ALTER TABLE CATEGORY_TBL ADD CONSTRAINT pk_CATEGORY_TBL PRIMARY KEY(CTG_H_CODE);

-- 시퀀스 추가(CTG_H_CODE)
CREATE SEQUENCE SEQ_CTG_H_CODE;

-- 1차 카테고리
INSERT INTO category_tbl (ctg_h_code, ctg_l_code, ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, NULL, '강아지');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, NULL,'고양이');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, NULL,'관상어');    
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, NULL,'소동물');        
    

-- 2차 카테고리

-- 1차 카테고리 강이지 : 2차 카테고리(사료, 간식, 영양제, 장난감, 용품)
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 1, '강아지 사료');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 1, '강아지 간식');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 1, '강아지 영양제');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 1, '강아지 장난감');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 1, '강아지 용품');
    
    
-- 1차 카테고리 고양이 : 2차 카테고리(사료, 간식, 영양제, 장난감, 용품)
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 2, '고양이 사료');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 2, '고양이 간식');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 2, '고양이 영양제');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 2, '고양이 장난감');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 2, '고양이 용품');
    
-- 1차 카테고리 관상어 : 2차 카테고리(사료, 수조, 어항, 청소용품, 장식용품, 여과제, 기타용품)
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 3,'관상어 사료');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 3,'관상어 수조, 어항');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 3,'관상어 수조 청소용품');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 3,'관상어 수조 장식용품');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 3,'관상어 여과기, 여과제');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 3,'관상어 수조 기타용품');


-- 1차 카테고리 소동물 : 2차 카테고리(사료, 간식, 햄슽, 조류, 곤충, 파충류)
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 4,'소동물 사료, 간식');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 4,'소동물 햄스터 용품');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 4,'소동물 조류 용품');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 4,'소동물 곤충 용품');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 4,'소동물 파충류 용품');
    
    
COMMIT;
    

-- 장바구니 테이블
CREATE TABLE CART_TBL(
    CART_CODE             NUMBER          NOT NULL,
    PRO_NUM             NUMBER          NOT NULL,
    MBL_ID              VARCHAR2(20)    NOT NULL,
    CART_AMOUNT          NUMBER          NOT NULL,
    CART_DATE           DATE    DEFAULT SYSDATE
);

-- Premiere key 추가(CART_CODE)
ALTER TABLE CART_TBL ADD CONSTRAINT pk_CART_TBL PRIMARY KEY(CART_CODE);

-- 시퀀스 추가(CART_CODE)
CREATE SEQUENCE SEQ_CART_CODE;

COMMIT;


-- 리뷰테이블
CREATE TABLE REVIEW_TBL (
        REV_CODE        NUMBER      NOT NULL,
        MBl_ID        VARCHAR2(20) NOT NULL,
        PRO_NUM        NUMBER      NOT NULL,
        REV_TITLE       VARCHAR2(50) NOT NULL,
        REV_CONTENT     VARCHAR2(200) NOT NULL,
        REV_RATE        NUMBER   NOT NULL,
        REV_DATE        DATE DEFAULT SYSDATE
);

-- Premiere key 추가(REV_CODE)
ALTER TABLE REVIEW_TBL ADD CONSTRAINT PK_REVIEW_CODE PRIMARY KEY (REV_CODE);

-- 시퀀스 추가(CTG_H_CODE)
CREATE SEQUENCE SEQ_REV_CODE;

COMMIT;


-- QnA테이블
CREATE TABLE QNA_TBL (
        QNA_CODE        NUMBER      NOT NULL,
        MBl_ID        VARCHAR2(20) NOT NULL,
        PRO_NUM        NUMBER      NOT NULL,
        QNA_TITLE       VARCHAR2(50) NOT NULL,
        QNA_CONTENT     VARCHAR2(200) NOT NULL,
        QNA_DATE        DATE DEFAULT SYSDATE,
        ANS_CONTENT     VARCHAR2(200) NULL,
        ANS_CHECK       VARCHAR2(20)  NOT NULL,
        ANS_DATE        DATE DEFAULT SYSDATE,
        ADMIN_ID    VARCHAR2(15)   NULL,
        PRO_UP_FOLDER       VARCHAR(50)             NULL,
        PRO_IMG             VARCHAR(100)             NULL
);

-- Premiere key 추가(QNA_CODE)
ALTER TABLE QNA_TBL ADD CONSTRAINT pk_QNA_TBL PRIMARY KEY(QNA_CODE);

-- 시퀀스 추가(QNA_CODE)
CREATE SEQUENCE SEQ_QNA_CODE;


--Foreign key 추가(PRODUCT_TBL(PRO_NUM))
ALTER TABLE QNA_TBL
ADD CONSTRAINT FK_QNA_PRO_NUM
FOREIGN KEY (PRO_NUM)
REFERENCES PRODUCT_TBL(PRO_NUM);

COMMIT;


-- 주문내용 테이블(주문자 정보테이블)
CREATE TABLE ORDER_TBL (
        ORD_CODE            NUMBER,
        MBL_ID             VARCHAR2(15)            NOT NULL,
        ORD_NAME            VARCHAR2(30)            NOT NULL,
        ORD_ADDR_ZIPCODE    CHAR(5)                 NOT NULL,
        ORD_ADDR_BASIC      VARCHAR2(50)            NOT NULL,
        ORD_ADDR_DETAIL     VARCHAR2(50)            NOT NULL,
        ORD_TEL             VARCHAR2(20)            NOT NULL,
        ORD_PRICE           NUMBER                  NOT NULL,
        ORD_DESC            VARCHAR2(300)           NULL,
        ORD_REGDATE         DATE DEFAULT SYSDATE    NOT NULL
);

-- Premiere key 추가(ORD_CODE)
ALTER TABLE ORDER_TBL ADD CONSTRAINT pk_ORDER_TBL PRIMARY KEY(ORD_CODE);

-- 시퀀스 추가(ORD_CODE)
CREATE SEQUENCE SEQ_ORD_CODE;

COMMIT;

-- 관리자 메모 컬럼추가
ALTER TABLE ORDER_TBL
ADD ORD_ADMIN_MEMO VARCHAR2(100);

-- 주문상세 테이블(주문 상품)
CREATE TABLE ORDETAIL_TBL (
        ORD_CODE        NUMBER      NOT NULL,
        PRO_NUM         NUMBER      NOT NULL,
        DT_AMOUNT       NUMBER      NOT NULL,
        DT_PRICE        NUMBER      NOT NULL
);

-- Premiere key 추가(ORD_CODE, PRO_NUM)
ALTER TABLE ORDETAIL_TBL
ADD CONSTRAINT PK_ORDETAIL_CODE_NUM PRIMARY KEY (ORD_CODE ,PRO_NUM);

--Foreign key 추가(PRODUCT_TBL(PRO_NUM))
ALTER TABLE ORDETAIL_TBL
ADD CONSTRAINT FK_ORDETAIL_NUM
FOREIGN KEY (PRO_NUM)
REFERENCES PRODUCT_TBL(PRO_NUM);

COMMIT;


-- 결제 테이블
CREATE TABLE PAYINFO_TBL(
    PAY_ID        NUMBER,
    ORD_CODE    NUMBER NOT NULL,
    MBL_ID             VARCHAR2(20)     NOT NULL,
    PAYMETHOD   VARCHAR2(50)    NOT NULL,
    PAY_PRICE     NUMBER NOT NULL,
    PAYINFO         VARCHAR2(100)  NULL,
    PAY_STATUS    VARCHAR2(10)    NOT NULL,
    PAY_DATE      DATE DEFAULT    SYSDATE
);

-- Premiere key 추가(PAY_ID)
ALTER TABLE PAYINFO_TBL ADD CONSTRAINT pk_PAYINFO_TBL PRIMARY KEY(PAY_ID);

-- 시퀀스 추가(PAYINFO_ID)
CREATE SEQUENCE SEQ_PAYINFO_ID;

COMMIT;

-- 회원메일 테이블
CREATE TABLE MBMG_MAIL_TBL(
    M_NUM        NUMBER,
    M_TITLE     VARCHAR2(200)    NOT NULL,
    M_CONTENT   VARCHAR2(4000)  NOT NULL,
    M_CHECK     VARCHAR(30)     NOT NULL,
    M_SEND_COUNT    NUMBER DEFAULT 0,
    M_REGDATE   DATE DEFAULT SYSDATE
);

-- Premiere key 추가(M_NUM)
ALTER TABLE MBMG_MAIL_TBL ADD CONSTRAINT pk_MBMG_MAIL_TBL PRIMARY KEY(M_NUM);

-- 시퀀스 추가(M_NUM)
CREATE SEQUENCE SEQ_M_NUM;

-- 받는사람(메일) 컬럼추가
ALTER TABLE MBMG_MAIL_TBL
ADD MBL_EMAIL VARCHAR2(100);

COMMIT;