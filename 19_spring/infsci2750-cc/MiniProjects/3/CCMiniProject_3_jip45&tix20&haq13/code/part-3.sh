# CQL test

# upload data 
scp -i key_student /Users/pangjing/IdeaProjects/CCMini3/data/out/access_log.csv student@159.65.253.68:~/ccmini3/


scp -i key_student -r /Users/pangjing/IdeaProjects/CCMini3/ student@159.65.253.68:~/ccmini3/


# into cassandra shell
cqlsh master --request-timeout=600000



# create table
CREATE KEYSPACE ccmini3 WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 3};


# problem 3
DROP TABLE pathcount;
CREATE TABLE pathcount (path text, count int, PRIMARY KEY (path, count) ) ;
COPY pathcount(path,count) FROM '~/ccmini3/pathcount.csv' WITH DELIMITER = ' ' AND HEADER = TRUE;

SELECT * FROM ccmini3.pathcount limit 1;
SELECT max(count) FROM pathcount;
SELECT * FROM pathcount WHERE count = 117348 ALLOW FILTERING ;


# problem 4
DROP TABLE ipcount;
CREATE TABLE ipcount (ip text, count int, PRIMARY KEY (ip, count) ) ;
COPY ipcount(ip,count) FROM '~/ccmini3/ipcount.csv' WITH DELIMITER = ' ' AND HEADER = TRUE;

SELECT * FROM ccmini3.ipcount limit 1;
SELECT max(count) FROM ipcount;
SELECT * FROM ipcount WHERE count = 158614 ALLOW FILTERING ;


