---
 docker container used
---

####grafana
```shell
docker run --name=grafana -d --restart=always \
  -p 3000:3000 \ 
  -e "GF_SERVER_ROOT_URL=http://grafana.server.name" \
  -e "GF_SECURITY_ADMIN_PASSWORD=secret" \
  grafana/grafana
```
  
####influxdb
```shell
docker run --name influxdb -d --restart=always \
  -p 8083:8083 -p8086:8086 --expose 8090 --expose 8099 \
  tutum/influxdb
```

####activemq
```shell
docker run --name activemq -d --restart=always \
  -p 8161:8161 -p 61616:61616  \
  -e ACTIVEMQ_ADMIN_LOGIN=admin \
  -e ACTIVEMQ_ADMIN_PASSWORD=admin \   
  webcenter/activemq
```

####elasticsearch
```Dockerfile
FROM elasticsearch:2.3
RUN /usr/share/elasticsearch/bin/plugin install mobz/elasticsearch-head
EXPOSE 9200
EXPOSE 9300
```
```
docker build -t ace/elasticsearch .
```
```
docker run  --name elasticsearch -d --restart=always \
  -p 9200:9200 -p 9300:9300 \
  ace/elasticsearch
```

####kibana
```shell
docker run --name kibana -d --restart=always \
  -p 5601:5601 \
  -e ELASTICSEARCH_URL=http://118.190.77.10:9200 \ 
  kibana:4.5
```

####activemq
* redis.conf modify->requirepass
```
port 6379
tcp-backlog 511
timeout 0
tcp-keepalive 0
loglevel notice
logfile ""
databases 16
save 900 1
save 300 10
save 60 10000
stop-writes-on-bgsave-error yes
rdbcompression yes
rdbchecksum yes
dbfilename dump.rdb
dir ./
slave-serve-stale-data yes
slave-read-only yes
repl-diskless-sync no
repl-diskless-sync-delay 5
repl-disable-tcp-nodelay no
slave-priority 100
requirepass yourpassword
appendonly no
appendfilename "appendonly.aof"
appendfsync everysec
no-appendfsync-on-rewrite no
auto-aof-rewrite-percentage 100
auto-aof-rewrite-min-size 64mb
aof-load-truncated yes
lua-time-limit 5000
slowlog-log-slower-than 10000
slowlog-max-len 128
latency-monitor-threshold 0
notify-keyspace-events ""
hash-max-ziplist-entries 512
hash-max-ziplist-value 64
list-max-ziplist-entries 512
list-max-ziplist-value 64
set-max-intset-entries 512
zset-max-ziplist-entries 128
zset-max-ziplist-value 64
hll-sparse-max-bytes 3000
activerehashing yes
client-output-buffer-limit normal 0 0 0
client-output-buffer-limit slave 256mb 64mb 60
client-output-buffer-limit pubsub 32mb 8mb 60
hz 10
aof-rewrite-incremental-fsync yes
```
* Dockerfile
```Dockerfile
FROM redis
COPY redis.conf /usr/local/etc/redis/redis.conf
CMD ["redis-server","/usr/local/etc/redis/redis.conf"]
```
```
docker build -t ace/redis .
```
```shell
docker run --name redis -d --restart=always \
  -p 6379:6379 \
  ace/redis
```
