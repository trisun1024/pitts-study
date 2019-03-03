
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

# upload ngram from local to master
scp -i key_student /Users/pangjing/Desktop/ngram.jar student@159.65.253.68:~/hadoop/miniproject1/
# run ngram.jar
bin/hdfs dfs -rmr /user/student/output/ngram
bin/hadoop jar miniproject1/ngram.jar ngram 3 access_log output/ngram
bin/hdfs dfs -cat output/ngram/*

# upload access_log file from local to master
scp -i key_student /Users/pangjing/Desktop/access_log student@159.65.253.68:~/hadoop/bin/user/student/

# upload logQ1 from local to master
scp -i key_student /Users/pangjing/Desktop/logQ1.jar student@159.65.253.68:~/hadoop/miniproject1/
# run logQ1.jar
bin/hdfs dfs -rmr /user/student/output/resQ1
bin/hadoop jar miniproject1/logQ1.jar logQ1 access_log output/resQ1
bin/hdfs dfs -cat output/resQ1/*

# upload logQ2 from local to master
scp -i key_student /Users/pangjing/Desktop/logQ2.jar student@159.65.253.68:~/hadoop/miniproject1/
# run logQ2.jar
bin/hdfs dfs -rmr /user/student/output/resQ2
bin/hadoop jar miniproject1/logQ2.jar logQ2 access_log output/resQ2
bin/hdfs dfs -cat output/resQ2/*

# upload logQ3 from local to master
scp -i key_student /Users/pangjing/Desktop/logQ3.jar student@159.65.253.68:~/hadoop/miniproject1/
# run logQ3.jar
bin/hdfs dfs -rmr /user/student/output/resQ3
bin/hadoop jar miniproject1/logQ3.jar logQ3.logQ3 access_log output/resQ3/count output/resQ3/top1
bin/hdfs dfs -cat output/resQ3/top1/*

# upload logQ4 from local to master
scp -i key_student /Users/pangjing/Desktop/logQ4.jar student@159.65.253.68:~/hadoop/miniproject1/
# run logQ4.jar
bin/hdfs dfs -rmr /user/student/output/resQ4
bin/hadoop jar miniproject1/logQ4.jar logQ4.logQ4 access_log output/resQ4/count output/resQ4/top1
bin/hdfs dfs -cat output/resQ4/top1/*

# server
bin/yarn application -list
bin/yarn application -kill



