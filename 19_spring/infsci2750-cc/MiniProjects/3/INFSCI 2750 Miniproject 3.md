# INFSCI 2750 Miniproject 3

By Jing Pang (jip45@pitt.edu), Tian Xue (tix20@pitt.edu), Haoyang Qian (haq13@pitt.edu)



### Part 1: Setting Up Cassandra

Setting up Cassandra on a single node on ubuntu linux with following instructions.

``` shell
# install Cassandra
echo "deb http://www.apache.org/dist/cassandra/debian 311x main" | sudo tee -a /etc/apt/sources.list.d/cassandra.sources.list
curl https://www.apache.org/dist/cassandra/KEYS | sudo apt-key add-
sudo apt-get update
sudo apt-get install cassandra
```

Change configure on all nodes

```shell
# read file
sudo nano /etc/cassandra/cassandra.yaml
# change file setting
- seeds: "master, slave-1, slave-2" (on all nodes)
listen_address: master (on master node)
listen_address: slave-1 (on slave-1 node)
listen_address: slave-2 (on slave-2 node)

rpc_address: master (on master node)
rpc_address: slave-1 (on slave-1 node)
rpc_address: slave-2 (on slave-2 node)
```

Then start the services on all nodes

```shell
# stop cassandra on all nodes
sudo service cassandra stop
# run cassandra 
sudo cassandra -Rf
# check status
```

![nodetool_status](/Users/pangjing/Desktop/nodetool_status.png)

### Part 2: Import Data into Cassandra

Test: Start CQL shell to see the correct setup 

```shell
cqlsh master --request-timeout=600000
```

Before we upload the access_log file, we preprocess the file with several steps. We splited file into 5 pieces, and transferred each piece into a csv format. Then, we combined this set of files into a single file. At last, we upload this file to master node.

```shell
# upload file from local
scp -i key_student /Users/pangjing/Desktop/ccmini3/accesslog5.csv student@159.65.253.68:~/CCMiniproject3/
```

Then, we login to the CQL shell, and use the COPY command to upload data into a table

```CQL
# login 
cqlsh master --request-timeout=600000
# create keyspace
create keyspace access_log2
with replication = {
'class' : 'NetworkTopologyStrategy',
'datacenter1' : 1
};

# create table
create table access_log2.log (
IPaddress text,
identity text,
username text,
time text,
timetail text,
requestline text,
statuscode text,
size text,
primary key (IPaddress, time, requestline)
);

# copy data to table
COPY access_log2.log (IPaddress, identity, username, time, timetail, requestline, statuscode, size) 
FROM '/home/student/CCMiniproject3/accesslog5.csv' WITH numprocesses=4;
```

![copy_log-1](/Users/pangjing/Desktop/copy_log-1.png)

![copy_log-2](/Users/pangjing/Desktop/copy_log-2.png)![select_log](/Users/pangjing/Desktop/select_log.png)

### Part 3: Operate Data in Cassandra 

Solve problems with Cassandra

1. How many hits were made to the website item "/assets/img/release-schedule-logo.png" ?

   ```CQL
   SELECT count(*) 
   FROM access_log2.log 
   WHERE requestline = '/assets/img/release-schedule-logo.png'
   ALLOW FILTERING;
   ```

   ![q1](/Users/pangjing/Desktop/q1.png)

2. How many hits were made from the IP: "10.207.188.188" ?

   ```CQL
   SELECT count(*) 
   FROM access_log2.log WHERE ipaddress = '10.207.188.188'
   ALLOW FILTERING;
   ```

   ![q2](/Users/pangjing/Desktop/q2.png)

3. Which path in the website has been hit most? How many hits were made to the path?

   Construct a new csv file containning the path and counts by using the Java program. Upload this new csv file to master node.

   ```shell
   # upload from local computer to master node
   scp -i key_student /Users/pangjing/IdeaProjects/CCMini3/out/pathcount.csv student@159.65.253.68:~/ccmini3/
   ```

   Import csv file to cassandra 

   ```CQL
   # create keyspace
   CREATE KEYSPACE ccmini3 WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 3};
   # create table 
   CREATE TABLE pathcount (path text, count int, PRIMARY KEY (path, count) ) ;
   # import data
   COPY pathcount(path,count) FROM '~/ccmini3/pathcount.csv' WITH DELIMITER = ' ' AND HEADER = TRUE;
   ```

   Run calculation to find match in CQL shell 

   ```CQL
   SELECT * FROM ccmini3.pathcount limit 1;
   SELECT max(count) FROM pathcount;
   SELECT * FROM pathcount WHERE count = 117348 ALLOW FILTERING ;
   ```

   ![q3](/Users/pangjing/Desktop/q3.png)

4. Which IP accesses the website most? How many accesses were made by it?

   Construct a new csv file containning the path and counts by using the Java program. Upload this new csv file to master node.

   ```shell
   # upload from local computer to master node
   scp -i key_student /Users/pangjing/IdeaProjects/CCMini3/out/ipcount.csv student@159.65.253.68:~/ccmini3/
   ```

   Import csv file to cassandra 

   ```CQL
   # create table 
   CREATE TABLE ipcount (ip text, count int, PRIMARY KEY (ip, count) ) ;
   # import data
   COPY ipcount(ip,count) FROM '~/ccmini3/ipcount.csv' WITH DELIMITER = ' ' AND HEADER = TRUE;
   ```

   Run calculation to find match in CQL shell 

   ```CQL
   SELECT * FROM ccmini3.ipcount limit 1;
   SELECT max(count) FROM ipcount;
   SELECT * FROM ipcount WHERE count = 158614 ALLOW FILTERING ;
   ```

   ![q4](/Users/pangjing/Desktop/q4.png)



