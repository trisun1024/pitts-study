
CREATE TABLE IF NOT EXISTS cust_acct(
    tb_id INT ,
    CUST_ID int, 
    ACCT_ID BIGINT, 
    VALIAD_PERIOD int,
    ACCT_LEVEL int,
    PRIMARY KEY (tb_id) ) ;

CREATE TABLE IF NOT EXISTS teller(
    tb_id INT ,
    GUIYDAIH VARCHAR(100), 
    YNGYJIGO int, 
    level int,
    GENDER VARCHAR(10),
    PRIMARY KEY (tb_id) ) ;

CREATE TABLE IF NOT EXISTS transaction(
    tb_id INT,
    TR_TM date, 
    TR_NO VARCHAR(100), 
    OPR_ID VARCHAR(100), 
    CUST_ID int, 
    ACCT_ID BIGINT, 
    TR_AM int, 
    OPP_ACCT_ID BIGINT,
    PRIMARY KEY (tb_id) ) ;

create table if not exists customer_tb(
tb_id int,
C_GENDER int,
C_AGE int,
C_FIRST_WORK_DATE date,
CUST_CODE int,
primary key (tb_id)
);

create table if not exists confidentical_info(
tb_id int,
ID VARCHAR(200),
passwd varchar (100),
primary key (tb_id)
);


















