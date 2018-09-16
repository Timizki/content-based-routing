#!/bin/bash
PID1=""
PID2=""
if [ -f "target/pid1" ]; then
	PID1=$(cat target/pid1)
fi
if [ -f "target/pid2" ]; then
	PID2=$(cat target/pid2)
fi
kill $PID1 $PID2
rm target/pid1
rm target/pid2


