create table if not exists OperatorFact(
TIME_ID date,
DEPT_ID int,
OPR_ID varchar(200),
TOT_OP_NUM int,
TOT_AM int,
primary key (TIME_ID, DEPT_ID, OPR_ID)
);

create table if not exists deptDim(
DEPT_ID int,
level int,
primary key (DEPT_ID)
);

create table if not exists operatorDim(
OPR_ID varchar(200),
GENDER varchar(10),
primary key (OPR_ID)
);

create table if not exists timeDim(
TIME_ID date,
day int,
month int,
year int,
primary key (TIME_ID)
);


INSERT INTO OperatorFact
SELECT t.TR_TM AS TIME_ID, te.YNGYJIGO AS DEPT_ID, t.OPR_ID, count(TR_NO) AS TOT_OP_NUM, sum(t.TR_AM) AS TOT_AM
FROM transaction t, teller te 
WHERE t.OPR_ID = te.GUIYDAIH
AND NOT EXISTS(
SELECT *
FROM OperatorFact
WHERE t.TR_TM = OperatorFact.TIME_ID
    and te.YNGYJIGO = OperatorFact.DEPT_ID
    and t.OPR_ID = OperatorFact.OPR_ID
    )
GROUP BY t.TR_TM, te.YNGYJIGO, t.OPR_ID
;

INSERT INTO deptDim 
SELECT YNGYJIGO AS DEPT_ID, level
FROM teller
WHERE NOT EXISTS
(
SELECT
    *
FROM
    deptDim
WHERE teller.YNGYJIGO = deptDim.DEPT_ID
    )
;

INSERT INTO operatorDim
SELECT GUIYDAIH AS OPR_ID, GENDER
FROM teller
WHERE NOT EXISTS
(
SELECT
    *
FROM
    operatorDim
WHERE teller.GUIYDAIH = operatorDim.OPR_ID
    )
GROUP BY GUIYDAIH, GENDER
;



