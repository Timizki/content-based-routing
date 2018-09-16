#!/bin/bash
if [ -f "target/camelpid" ]; then
	PID=$(cat target/camelpid)
	kill $PID
else
	echo "PID unknown, cannot shutdown"
fi

