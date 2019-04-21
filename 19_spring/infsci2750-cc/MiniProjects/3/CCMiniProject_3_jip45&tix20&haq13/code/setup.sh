# setup cassandra
echo "deb http://www.apache.org/dist/cassandra/debian 311x main" | sudo tee -a /etc/apt/sources.list.d/cassandra.sources.list
curl https://www.apache.org/dist/cassandra/KEYS | sudo apt-key add -
# update and install
sudo apt-get update
sudo apt-get install cassandra

# start services
service cassandra stop
cassandra -Rf

# CQL test
# into cassandra shell
cqlsh

# create table
CREATE KEYSPACE patient WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 3};
CREATE TABLE patient.exam (patient_id int, id int, date timeuuid, details text, PRIMARY KEY (patient_id, id));
# insert data
USE patient;
INSERT INTO exam (patient_id,id,date,details) values (1,1,now(),'first exam patient 1');
INSERT INTO exam (patient_id,id,date,details) values (1,2,now(),'second exam patient 1');
INSERT INTO exam (patient_id,id,date,details) values (2,1,now(),'first exam patient 2');
INSERT INTO exam (patient_id,id,date,details) values (3,1,now(),'first exam patient 3');
# query
SELECT * FROM exam WHERE patient_id=1;