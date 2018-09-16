#!/bin/bash
PAYLOAD=@$1

curl -XPOST -H "Content-Type: text/xml" -H "SOAPAction: \"http://localhost:8080/in\""  --data-binary $PAYLOAD http://localhost:8080/in|xmllint --format -
