import time
from kafka import KafkaProducer

producer = KafkaProducer(bootstrap_servers='localhost:9092')
filename = "item.txt"
for _ in range(10000):
    with open(filename) as f:
        content = f.readlines()

    for line in content:
        producer.send('mytopic', line)
        producer.flush
        time.sleep(.100)

producer.close