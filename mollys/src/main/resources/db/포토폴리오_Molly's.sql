/*
�ۼ��� : ��ȣ��
������Ʈ ������ : 2024-06-17
*/


-- 1.ȸ�����̺�   
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
-- Premiere key �߰�(MBL_ID)
ALTER TABLE MBL_TBL ADD CONSTRAINT pk_MBL_TBL PRIMARY KEY(MBL_ID);

-- SNS�α���(īī��, ���̹�) �÷� �߰�
ALTER TABLE MBL_TBL ADD SNS_LOGIN_TYPE CHAR(1) DEFAULT 'N' NOT NULL;

COMMIT;

/*
-- īī���α���
CREATE TABLE KAKAO_TBL(
    ID          NUMBER          NOT NULL,
    NICKNAME    VARCHAR2(50)    NOT NULL,
    EMAIL       VARCHAR2(100)   NOT NULL
);

-- ���̹� �α���
CREATE TABLE NAVER_TBL(
    ID          NUMBER          NOT NULL,
    NICKNAME    VARCHAR2(50)    NOT NULL,
    EMAIL       VARCHAR2(100)   NOT NULL
);

-- SNS �α������� ����
*/


-- SNS �α���
CREATE TABLE SNS_TBL(
    SNS_ID          VARCHAR2(50)    NOT NULL,
    SNS_NICKNAME    VARCHAR2(50)    NOT NULL,
    SNS_EMAIL       VARCHAR2(100)   NOT NULL,
    SNS_TYPE    CHAR(5)         NOT NULL
);


-- 2. ������ ���̺�
CREATE TABLE ADMIN_TBL (
    ADMIN_ID    VARCHAR2(15) PRIMARY KEY,
    ADMIN_PW    CHAR(60)    NOT NULL,
    ADMIN_VISIT_DATE    DATE
);

-- Premiere key �߰�(ADMIN_ID)
ALTER TABLE ADMIN_TBL ADD CONSTRAINT pk_ADMIN_TBL PRIMARY KEY(ADMIN_ID);

-- ������ ���̵� �� ��й�ȣ(��ȣȭ : 1234) 
INSERT INTO ADMIN_TBL(ADMIN_ID, ADMIN_PW) VALUES('admin', '$2a$10$EMlOnefqIZm3c6UxvTVc5eJIRaM41HQh.tj/vdks9t0LfRoHQ5Y0C');

COMMIT;


-- 3. ��ǰ���� ���̺�
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

-- Premiere key �߰�(PRO_NUM)
ALTER TABLE PRODUCT_TBL ADD CONSTRAINT pk_PRODUCT_TBL PRIMARY KEY(PRO_NUM);

-- ������ �߰�(PRO_NUM)
CREATE SEQUENCE SEQ_PRO_NUM;

COMMIT;


-- 4. ī�װ�
CREATE TABLE CATEGORY_TBL(
    CTG_H_CODE      NUMBER       PRIMARY KEY,
    CTG_L_CODE      NUMBER      NULL,
    CTG_NAME        VARCHAR2(30)    NOT NULL
);

-- Premiere key �߰�(CTG_H_CODE)
ALTER TABLE CATEGORY_TBL ADD CONSTRAINT pk_CATEGORY_TBL PRIMARY KEY(CTG_H_CODE);

-- ������ �߰�(CTG_H_CODE)
CREATE SEQUENCE SEQ_CTG_H_CODE;

-- 1�� ī�װ�
INSERT INTO category_tbl (ctg_h_code, ctg_l_code, ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, NULL, '������');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, NULL,'�����');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, NULL,'�����');    
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, NULL,'�ҵ���');        
    

-- 2�� ī�װ�

-- 1�� ī�װ� ������ : 2�� ī�װ�(���, ����, ������, �峭��, ��ǰ)
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 1, '������ ���');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 1, '������ ����');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 1, '������ ������');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 1, '������ �峭��');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 1, '������ ��ǰ');
    
    
-- 1�� ī�װ� ����� : 2�� ī�װ�(���, ����, ������, �峭��, ��ǰ)
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 2, '����� ���');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 2, '����� ����');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 2, '����� ������');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 2, '����� �峭��');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 2, '����� ��ǰ');
    
-- 1�� ī�װ� ����� : 2�� ī�װ�(���, ����, ����, û�ҿ�ǰ, ��Ŀ�ǰ, ������, ��Ÿ��ǰ)
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 3,'����� ���');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 3,'����� ����, ����');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 3,'����� ���� û�ҿ�ǰ');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 3,'����� ���� ��Ŀ�ǰ');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 3,'����� ������, ������');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 3,'����� ���� ��Ÿ��ǰ');


-- 1�� ī�װ� �ҵ��� : 2�� ī�װ�(���, ����, �ܚ�, ����, ����, �����)
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 4,'�ҵ��� ���, ����');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 4,'�ҵ��� �ܽ��� ��ǰ');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 4,'�ҵ��� ���� ��ǰ');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 4,'�ҵ��� ���� ��ǰ');
INSERT INTO category_tbl (ctg_h_code,ctg_l_code,ctg_name) 
    VALUES (SEQ_CTG_H_CODE.NEXTVAL, 4,'�ҵ��� ����� ��ǰ');
    
    
COMMIT;
    

-- ��ٱ��� ���̺�
CREATE TABLE CART_TBL(
    CART_CODE             NUMBER          NOT NULL,
    PRO_NUM             NUMBER          NOT NULL,
    MBL_ID              VARCHAR2(20)    NOT NULL,
    CART_AMOUNT          NUMBER          NOT NULL,
    CART_DATE           DATE    DEFAULT SYSDATE
);

-- Premiere key �߰�(CART_CODE)
ALTER TABLE CART_TBL ADD CONSTRAINT pk_CART_TBL PRIMARY KEY(CART_CODE);

-- ������ �߰�(CART_CODE)
CREATE SEQUENCE SEQ_CART_CODE;

COMMIT;


-- �������̺�
CREATE TABLE REVIEW_TBL (
        REV_CODE        NUMBER      NOT NULL,
        MBl_ID        VARCHAR2(20) NOT NULL,
        PRO_NUM        NUMBER      NOT NULL,
        REV_TITLE       VARCHAR2(50) NOT NULL,
        REV_CONTENT     VARCHAR2(200) NOT NULL,
        REV_RATE        NUMBER   NOT NULL,
        REV_DATE        DATE DEFAULT SYSDATE
);

-- Premiere key �߰�(REV_CODE)
ALTER TABLE REVIEW_TBL ADD CONSTRAINT PK_REVIEW_CODE PRIMARY KEY (REV_CODE);

-- ������ �߰�(CTG_H_CODE)
CREATE SEQUENCE SEQ_REV_CODE;

COMMIT;


-- QnA���̺�
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

-- Premiere key �߰�(QNA_CODE)
ALTER TABLE QNA_TBL ADD CONSTRAINT pk_QNA_TBL PRIMARY KEY(QNA_CODE);

-- ������ �߰�(QNA_CODE)
CREATE SEQUENCE SEQ_QNA_CODE;


--Foreign key �߰�(PRODUCT_TBL(PRO_NUM))
ALTER TABLE QNA_TBL
ADD CONSTRAINT FK_QNA_PRO_NUM
FOREIGN KEY (PRO_NUM)
REFERENCES PRODUCT_TBL(PRO_NUM);

COMMIT;


-- �ֹ����� ���̺�(�ֹ��� �������̺�)
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

-- Premiere key �߰�(ORD_CODE)
ALTER TABLE ORDER_TBL ADD CONSTRAINT pk_ORDER_TBL PRIMARY KEY(ORD_CODE);

-- ������ �߰�(ORD_CODE)
CREATE SEQUENCE SEQ_ORD_CODE;

COMMIT;

-- ������ �޸� �÷��߰�
ALTER TABLE ORDER_TBL
ADD ORD_ADMIN_MEMO VARCHAR2(100);

-- �ֹ��� ���̺�(�ֹ� ��ǰ)
CREATE TABLE ORDETAIL_TBL (
        ORD_CODE        NUMBER      NOT NULL,
        PRO_NUM         NUMBER      NOT NULL,
        DT_AMOUNT       NUMBER      NOT NULL,
        DT_PRICE        NUMBER      NOT NULL
);

-- Premiere key �߰�(ORD_CODE, PRO_NUM)
ALTER TABLE ORDETAIL_TBL
ADD CONSTRAINT PK_ORDETAIL_CODE_NUM PRIMARY KEY (ORD_CODE ,PRO_NUM);

--Foreign key �߰�(PRODUCT_TBL(PRO_NUM))
ALTER TABLE ORDETAIL_TBL
ADD CONSTRAINT FK_ORDETAIL_NUM
FOREIGN KEY (PRO_NUM)
REFERENCES PRODUCT_TBL(PRO_NUM);

COMMIT;


-- ���� ���̺�
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

-- Premiere key �߰�(PAY_ID)
ALTER TABLE PAYINFO_TBL ADD CONSTRAINT pk_PAYINFO_TBL PRIMARY KEY(PAY_ID);

-- ������ �߰�(PAYINFO_ID)
CREATE SEQUENCE SEQ_PAYINFO_ID;

COMMIT;

-- ȸ������ ���̺�
CREATE TABLE MBMG_MAIL_TBL(
    M_NUM        NUMBER,
    M_TITLE     VARCHAR2(200)    NOT NULL,
    M_CONTENT   VARCHAR2(4000)  NOT NULL,
    M_CHECK     VARCHAR(30)     NOT NULL,
    M_SEND_COUNT    NUMBER DEFAULT 0,
    M_REGDATE   DATE DEFAULT SYSDATE
);

-- Premiere key �߰�(M_NUM)
ALTER TABLE MBMG_MAIL_TBL ADD CONSTRAINT pk_MBMG_MAIL_TBL PRIMARY KEY(M_NUM);

-- ������ �߰�(M_NUM)
CREATE SEQUENCE SEQ_M_NUM;

-- �޴»��(����) �÷��߰�
ALTER TABLE MBMG_MAIL_TBL
ADD MBL_EMAIL VARCHAR2(100);

COMMIT;