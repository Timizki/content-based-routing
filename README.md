# Simple content based routing with camel

This project shows how to do content based routing with Apache Camel for SOAP WS services.
The camel acts as HTTP proxy between SOAP client and SOAP services and use processor to read XML content stream and does the routing based on Name element of the content

# What is what?
* passtrough-demo: Apache camel module
  * There is one route that listens http://localhost:8080/in address
* country-ws: Simple SOAP WS project 
  * Contains one endpoint that returns imaginary coutry that has service port as population as response
  * The endpoint is published to http://localhost:port/ws/out address

# Implementation

The implementation is based on Spring boot. 

# Tools

To compile project you need Apache Maven

# How to run example
* Package projects with command mvn clean package
* camel-start.sh starts Apache camel and writes process pid to target/camelpid file
* camel-stop.sh reads target/camelpid file and use kill command to kill process
* start-backend-services.sh starts two instances of country-ws project. One to port 7070 and another to port 7071. Script writes service pids to target/pid1 and target/pid2 files
* stop-backend-services.sh reads target/pid1 and target/pid2 files and use kill command to kill country-ws process based on those pids


