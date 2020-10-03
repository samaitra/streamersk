# streamersk

A realtime data streaming application to access, aggregate and format events

### Architecture

![Stream processing topology](https://github.com/samaitra/streamersk/raw/master/resources/streamersk.png) 

### Setup: Download and Start Flink

Download a binary from the downloads page. You can pick any Hadoop/Scala combination you like. If you plan to just use the local file system, any Hadoop version will work fine.
Go to the download directory.

### Unpack the downloaded archive.
```
$ cd ~/Downloads        # Go to download directory
$ tar xzf flink-*.tgz   # Unpack the downloaded archive
$ mv flink-1.9.1 ~/     # copy flink to home dir 
$ cd flink-1.9.1
```

### Start a Local Flink Cluster
```
$ ./bin/start-cluster.sh  # Start Flink
```
Check the Dispatcher’s web frontend at http://localhost:8081 and make sure everything is up and running. The web frontend should report a single available TaskManager instance.

![Flink Job Execution](https://github.com/samaitra/streamersk/raw/master/resources/flink_job.png) 

Dispatcher: Overview

You can also verify that the system is running by checking the log files in the logs directory:
```
$ tail log/flink-*-standalonesession-*.log
```

### Download kafka

```
https://kafka.apache.org/downloads
``` 

### start zookeeper server
```
$./bin/zookeeper-server-start.sh ./config/zookeeper.properties
```

### start broker
```
$./bin/kafka-server-start.sh ./config/server.properties 
```

### create topic “mytopic”
```
$ ./bin/kafka-topics.sh --create --topic mytopic --zookeeper localhost:2181 --partitions 1 --replication-factor 1
```

### Describe topic "mytopic"

```
$ ./bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic mytopic
```

### produce something into the topic (write something and hit enter)
```
$ ./bin/kafka-console-producer.sh --topic mytopic --broker-list localhost:9092
```

### consume from the topic using the console producer
```
$ ./bin/kafka-console-consumer.sh --topic mytopic --zookeeper localhost:2181
```

### Clone Apache Ignite 

```
$ git clone https://github.com/apache/ignite
```

### Build Apache Ignite 

```
$ mvn clean package install -DskipTests
```

### Build the Flink program :
```
$ mvn clean package
```

### Submit the Flink program :
```
$ ~/flink-1.9.1/bin/flink run ./target/streamersk-1.0-SNAPSHOT.jar src/main/resources/application.properties
```

### produce something into the topic (write something and hit enter)
```
$ ./bin/kafka-console-producer.sh --topic mytopic --broker-list localhost:9092
```

If you are using `print()` The .out file will print the counts at the end of each time window as long as words are floating in, e.g.:
```
$ tail -f log/flink-*-taskexecutor-*.out
12314213 : 1
12314214 : 3
```

### Install pip & kafka-python

```
$ curl https://bootstrap.pypa.io/get-pip.py -o get-pip.py
$ python get-pip.py --user
$ pip install kafka-python --user
```

### Use data_publisher.py
To run a sample data publisher you can use the data_publisher.py which will read the data from 
item.txt and publish data to kafka topic

```
cd data
python data_publisher.py
```

### Ignite rest service
To check the cache key values you can use the Ignite rest service 
```
$ curl -X GET http://localhost:8080/ignite\?cmd\=getall\&k1\=12314213\&cacheName\=testCache
```

### Scan cache 
To check all the keys from an Ignite cache the following rest service can be used
```
$ curl -X GET http://localhost:8080/ignite?cmd=qryscanexe&pageSize=10&cacheName=testCache
```

### Ignite Web Console

#### Ignite Web Console Build Instructions

1. Install MongoDB (version >=3.2.0 <=3.4.15) using instructions from http://docs.mongodb.org/manual/installation.
2. Install Node.js (version >=8.0.0) using installer from https://nodejs.org/en/download/current for your OS.
3. Change directory to 'modules/web-console/backend' and
 run "npm install --no-optional" for download backend dependencies.
4. Change directory to 'modules/web-console/frontend' and
 run "npm install --no-optional" for download frontend dependencies.
5. Build ignite-web-agent module follow instructions from 'modules/web-console/web-agent/README.txt'.
6. Copy ignite-web-agent-<version>.zip from 'modules/web-console/web-agent/target'
 to 'modules/web-console/backend/agent_dists' folder.
7. Unzip ignite-web-agent-<version>.zip in 'modules/web-console/backend/agent_dists'
8. run './ignite-web-agent.sh' inside ignite-web-agent-<version> folder 

Steps 1 - 4 should be executed once.

#### Ignite Web Console Run In Development Mode

1. Configure MongoDB to run as service or in terminal change dir to $MONGO_INSTALL_DIR/server/3.2/bin
  and start MongoDB by executing "mongod".

2. In new terminal change directory to 'modules/web-console/backend'.
   If needed run "npm install --no-optional" (if dependencies changed) and run "npm start" to start backend.

3. In new terminal change directory to 'modules/web-console/frontend'.
  If needed run "npm install --no-optional" (if dependencies changed) and start webpack in development mode "npm run dev".

4. In browser open: http://localhost:9000

![Ignite Web Console](https://github.com/samaitra/streamersk/raw/master/resources/ignite_web_console.png) 


### To stop Flink when you’re done type:
```
$ ./bin/stop-cluster.sh
