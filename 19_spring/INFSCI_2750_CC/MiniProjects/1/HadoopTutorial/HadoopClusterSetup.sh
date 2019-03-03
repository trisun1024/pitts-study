

# test none-password SSH login
ssh CC-demo-02

# back to the master node
exit

#Configure cluster
# go to your account home directory
cd ~/hadoop

#assign slaves on master node
#master and slave nodes both act as slaves
# put one hostname for each line
# it should looks like:
############
# CC-demo-01
# CC-demo-02
# CC-demo-03
###########
nano etc/hadoop/workers


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
scp -r * student@CC-demo-02:~/hadoop/etc/hadoop/

#Formate the namenode
hadoop namenode -format
