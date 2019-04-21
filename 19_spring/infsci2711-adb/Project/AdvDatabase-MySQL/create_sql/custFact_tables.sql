create table if not exists CustOutFact(
TIME_ID date,
CUST_ID int,
ACCT_ID bigint,
TOT_OUT_AMOUNT int,
COUNT_OUT int,
primary key (TIME_ID, CUST_ID, ACCT_ID)
);

create table if not exists CustInFact(
TIME_ID date,
CUST_ID int,
ACCT_ID bigint,
TOT_IN_AMOUNT int,
COUNT_IN int,
primary key (TIME_ID, CUST_ID, ACCT_ID)
);

create table if not exists timeDim(
TIME_ID date,
day int,
month int,
year int,
primary key (TIME_ID)
);

create table if not exists custDim(
CUST_ID int,
C_GENDER int,
C_AGE int,
primary key (CUST_ID)
) ;

create table if not exists acctDim(
ACCT_ID bigint,
VALIAD_PERIOD int,
ACCT_LEVEL int,
primary key (ACCT_ID)
);


INSERT INTO CustOutFact
SELECT TR_TM AS TIME_ID, CUST_ID, ACCT_ID, sum(TR_AM) AS TOT_OUT_AMOUNT, count(TR_NO) COUNT_OUT
FROM transaction
WHERE NOT EXISTS
(
SELECT *
FROM CustOutFact
WHERE transaction.TR_TM = CustOutFact.TIME_ID
    and transaction.CUST_ID = CustOutFact.CUST_ID
    and transaction.ACCT_ID = CustOutFact.ACCT_ID
    )
GROUP BY TR_TM, CUST_ID, ACCT_ID
;

INSERT INTO CustInFact
SELECT TR_TM AS TIME_ID, CUST_ID , OPP_ACCT_ID AS ACCT_ID, sum(TR_AM) AS TOT_IN_AMOUNT, count(TR_NO) COUNT_IN
FROM transaction
WHERE NOT EXISTS
(
SELECT *
FROM CustInFact
WHERE transaction.TR_TM = CustInFact.TIME_ID
    and transaction.CUST_ID = CustInFact.CUST_ID
    and transaction.OPP_ACCT_ID = CustInFact.ACCT_ID
    )
GROUP BY TR_TM, CUST_ID, OPP_ACCT_ID
;

INSERT INTO timeDim
SELECT TR_TM AS TIME_ID, DAY(TR_TM) AS day, MONTH(TR_TM) AS month, YEAR(TR_TM) AS YEAR
FROM transaction
WHERE NOT EXISTS
(
SELECT TIME_ID
FROM timeDim
WHERE transaction.TR_TM = timeDim.TIME_ID
)
GROUP BY TR_TM
;

INSERT INTO custDim 
SELECT CUST_CODE AS CUST_ID, C_GENDER, C_AGE
FROM customer_tb
WHERE NOT EXISTS
(
SELECT *
FROM custDim
WHERE customer_tb.CUST_CODE = custDim.CUST_ID
    )
;

INSERT INTO acctDim 
SELECT ACCT_ID, VALIAD_PERIOD, ACCT_LEVEL
FROM cust_acct
WHERE NOT EXISTS
(
SELECT *
FROM acctDim
WHERE cust_acct.ACCT_ID = acctDim.ACCT_ID
    )
;