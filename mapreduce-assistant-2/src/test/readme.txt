<<Pseudo-Distributed>>
# etc/hadoop/core-site.xml
<configuration>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://localhost:9000</value>
    </property>
</configuration>

# etc/hadoop/hdfs-site.xml
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>
</configuration>

# Setup passphraseless ssh
ssh-keygen -t dsa -P '' -f ~/.ssh/id_dsa
cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys

# Format the filesystem
hdfs namenode -format

# Start NameNode daemon and DataNode daemon
start-dfs.sh

# NameNode
http://localhost:50070/

# Make the HDFS directories required to execute MapReduce jobs
hdfs dfs -mkdir -p /user/hsiehpinghan
hdfs dfs -mkdir -p /tmp
hdfs dfs -chmod 777 /tmp

# Copy the input files into the distributed filesystem
hdfs dfs -put /user/<username> <input file>

# etc/hadoop/mapred-site.xml
<configuration>
    <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
</configuration>

# etc/hadoop/yarn-site.xml
<configuration>
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
</configuration>

# ResourceManager
http://localhost:8088/

# put files to hdfs
hdfs dfs -put /home/hsiehpinghan/git/assistant/mapreduce-assistant-2/src/test/file/wordCount_*.txt /home/hsiehpinghan/git/assistant/mapreduce-assistant-2/src/test/file/patterns.txt /tmp


