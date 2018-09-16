#!/bin/bash
mkdir target -p
java -jar country-ws/target/country-ws-0.0.1-SNAPSHOT.jar --server.port=7070 &>./target/7070.log &
echo $! > target/pid1
java -jar country-ws/target/country-ws-0.0.1-SNAPSHOT.jar --server.port=7071 &>./target/7071.log &
echo $! > target/pid2



