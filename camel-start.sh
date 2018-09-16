#!/bin/bash
mkdir -p target
java -jar passtrough-demo/target/passtrough-demo-0.0.1-SNAPSHOT.jar --server.port 8080 &>./target/camel.log &
echo $! > target/camelpid
