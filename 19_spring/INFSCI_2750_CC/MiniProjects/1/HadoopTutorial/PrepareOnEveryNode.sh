export MASTER_IP=157.230.190.194
export SLAVE_IP=157.230.181.243
export SLAVE02_IP=157.230.213.251

#ssh login
ssh -i "key_student" student@<VM_IP_ADDRESS>

######WARNING: 
#change the password to a strong enough password if you do not want to lose what you did in the VM
##############

# Commands on every node
#setup JAVA 8
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer
sudo update-alternatives --config java


#test java setup
java -version


#download Hadoop and test locally
# suppose to use the home directory to place the hadoop files
cd ~
wget http://www-us.apache.org/dist/hadoop/common/hadoop-3.2.0/hadoop-3.2.0.tar.gz
tar -zxf hadoop-3.2.0.tar.gz
ln -s hadoop-3.2.0 hadoop
cd hadoop

# set to the root of your Java installation
# export JAVA_HOME=/usr/lib/jvm/java-8-oracle
nano etc/hadoop/hadoop-env.sh

#test single node hadoop
mkdir input
cp etc/hadoop/*.xml input
bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.2.0.jar grep input output 'dfs[a-z.]+'
cat output/*

# setup etc/hosts with the IP and Node Name on every node
# 159.203.71.174 CC-demo-01
# 159.203.167.80 CC-demo-02
# ......
# Warning: delete every IP setting with 127.0.1.1 and 127.0.0.1 with your hostname
# for example:
# 127.0.0.1 CC-demo-01 CC-demo-01
# please delete the above line in /etc/hosts
sudo nano /etc/hosts

