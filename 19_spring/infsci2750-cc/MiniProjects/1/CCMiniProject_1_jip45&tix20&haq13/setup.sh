export MASTER_IP=159.65.253.68
export SLAVE1_IP=68.183.59.111
export SLAVE2_IP=68.183.154.239

#ssh login
ssh -i "key_student" root@68.183.154.239

# Commands on every node
#setup JAVA 8
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer
sudo update-alternatives --config java

#test java setup
java -version

# Setup Hadoop
cd ~
wget http://us.mirrors.quenda.co/apache/hadoop/common/hadoop-3.2.0/hadoop-3.2.0.tar.gz
tar -zxf hadoop-3.2.0.tar.gz
ln -s hadoop-3.2.0 hadoop
cd hadoop

# set to the root of your Java installation
# export JAVA_HOME=/usr/lib/jvm/java-8-oracle
nano etc/hadoop/hadoop-env.sh

# Test with local tasks
bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.2.0.jar pi 2 5

mkdir input
cp etc/hadoop/*.xml input
bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.2.0.jar grep input output 'dfs[a-z.]+'
cat output/*

# setup /etc/hosts with IP and Node Name on every node
159.65.253.68 master
68.183.59.111 slave-1
68.183.154.239 slave-2

# test non-password SSH login
ssh slave-1

# check rsa
eval $(ssh-agent)
ssh-add ~/.ssh/id_rsa

# assign masters and workers
nano etc/hadoop/workers
master
slave-1
slave-2

# allow the master to listen to 9000
sudo ufw allow 9000

#configure the setting
cd etc/hadoop/

#See the example in the HadoopTutorial package
nano core-site.xml
nano hdfs-site.xml
nano yarn-site.xml
nano mapred-site.xml
#copy the setting to slave
scp -r * student@slave-1:~/hadoop/etc/hadoop/
scp -r * student@slave-2:~/hadoop/etc/hadoop/

#Formate the namenode
# make sure clean slaves datanode files /tmp/hadoop-student/dfs/data/
bin/hadoop namenode -format

# Start Cluster
cd ~/hadoop

#start cluster
sbin/start-dfs.sh
sbin/start-yarn.sh
#start job history server
sbin/mr-jobhistory-daemon.sh --config etc/hadoop/ start historyserver

#test with jps
jps
ssh CC-demo-02
jps
exit

# test with create an directory on HDFS
export PATH=$PATH:~/hadoop/bin
hdfs dfs -mkdir /input
hdfs dfs -ls /
hdfs dfs -mkdir /user
hdfs dfs -mkdir /user/student
hdfs dfs -put etc/hadoop/input
hdfs dfs -ls /user/student/input

# ex1
bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.2.0.jar pi 2 5
# ex2
hdfs dfs -rmr output
bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.2.0.jar wordcount input/ output/wordcount
bin/hdfs dfs -cat output/wordcount/*

# upload from local to master
scp -i key_student /Users/pangjing/Desktop/ngram.jar student@159.65.253.68:~/hadoop/miniproject1/
# run ngram.jar
bin/hdfs dfs -rmr /user/student/output/ngram
bin/hadoop jar miniproject1/ngram.jar ngram 3 access_log output/ngram
bin/hdfs dfs -cat output/ngram/*

# upload from local to master
scp -i key_student /Users/pangjing/Desktop/logQ1.jar student@159.65.253.68:~/hadoop/miniproject1/
# run logQ1.jar
bin/hdfs dfs -rmr /user/student/output/resQ1
bin/hadoop jar miniproject1/logQ1.jar logQ1 access_log output/resQ1
bin/hdfs dfs -cat output/resQ1/*

# upload from local to master
scp -i key_student /Users/pangjing/Desktop/logQ2.jar student@159.65.253.68:~/hadoop/miniproject1/
# run logQ2.jar
bin/hdfs dfs -rmr /user/student/output/resQ2
bin/hadoop jar miniproject1/logQ2.jar logQ2 access_log output/resQ2
bin/hdfs dfs -cat output/resQ2/*

# upload from local to master
scp -i key_student /Users/pangjing/Desktop/logQ3.jar student@159.65.253.68:~/hadoop/miniproject1/
# run logQ3.jar
bin/hdfs dfs -rmr /user/student/output/resQ3
bin/hadoop jar miniproject1/logQ3.jar logQ3.logQ3 access_log output/resQ3/count output/resQ3/top1
bin/hdfs dfs -cat output/resQ3/top1/*

# upload from local to master
scp -i key_student /Users/pangjing/Desktop/logQ4.jar student@159.65.253.68:~/hadoop/miniproject1/
# run logQ4.jar
bin/hdfs dfs -rmr /user/student/output/resQ4
bin/hadoop jar miniproject1/logQ4.jar logQ4.logQ4 access_log output/resQ4/count output/resQ4/top1
bin/hdfs dfs -cat output/resQ4/top1/*

# server
bin/yarn application -list
bin/yarn application -kill



