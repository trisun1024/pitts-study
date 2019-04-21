Note: 
Jar file is located in the zip folder
Location: sourcecodes/CCMini2/target/ccmini2-out-1.0.jar

# upload dataset from local to cloud
scp -i key_student /Users/pangjing/Dropbox/19\ Spring/INFSCI_2750_CC/MiniProjects/2/datasets/Q2/hetrec2011-lastfm-2k/user_artists.dat student@159.65.253.68:~/spark/hw2/dataset/

# put dataset to hdfs
cd ~/spark/hw2/dataset/
~/hadoop/bin/hdfs dfs -put user_artists.dat

# upload java file from local
scp -i key_student -r /Users/pangjing/IdeaProjects/CCMini2 student@159.65.253.68:~/spark/hw2/

# package jar
cd ~/spark/hw2/CCMini2
mvn package

# run jar file
# Part-2
~/spark/bin/spark-submit --class Part2.Q2Task \
--master yarn \
--deploy-mode cluster \
--driver-memory 1g \
--executor-memory 1g \
--executor-cores 3 \
--queue default \
~/spark/hw2/CCMini2/target/ccmini2-out*.jar 

# Part-3 Question 1
# Case 1 normal
~/spark/bin/spark-submit --class Part3.Q1Task \
--master yarn \
--deploy-mode cluster \
--driver-memory 1g \
--executor-memory 1g \
--executor-cores 3 \
--queue default \
~/spark/hw2/CCMini2/target/ccmini2-out*.jar 

# Part-3 Question 2
# Case 1 normal
~/spark/bin/spark-submit --class Part3.Q2Task \
--master yarn \
--deploy-mode cluster \
--driver-memory 1g \
--executor-memory 1g \
--executor-cores 3 \
--queue default \
~/spark/hw2/CCMini2/target/ccmini2-out*.jar 

# Case 2 cache
~/spark/bin/spark-submit --class Part3.Q2TaskCache \
--master yarn \
--deploy-mode cluster \
--driver-memory 1g \
--executor-memory 1g \
--executor-cores 3 \
--queue default \
~/spark/hw2/CCMini2/target/ccmini2-out*.jar 

# Part-3 Question 3
~/spark/bin/spark-submit --class Part3.Q3Task \
--master yarn \
--deploy-mode cluster \
--driver-memory 1g \
--executor-memory 1g \
--executor-cores 3 \
--queue default \
~/spark/hw2/CCMini2/target/ccmini2-out*.jar 

# Part-3 Question 4
~/spark/bin/spark-submit --class Part3.Q4Task \
--master yarn \
--deploy-mode cluster \
--driver-memory 1g \
--executor-memory 1g \
--executor-cores 3 \
--queue default \
~/spark/hw2/CCMini2/target/ccmini2-out*.jar 

# Part-3 Question 1&2
~/spark/bin/spark-submit --class Part3.Q1Q2Combination \
--master yarn \
--deploy-mode cluster \
--driver-memory 1g \
--executor-memory 1g \
--executor-cores 3 \
--queue default \
~/spark/hw2/CCMini2/target/ccmini2-out*.jar 

# download mvn package from server
scp -i key_student -r student@159.65.253.68:~/spark/hw2/CCMini2 /Users/pangjing/Desktop/CCMini2/