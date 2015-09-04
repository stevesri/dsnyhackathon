# dsnyhackathon

Problem Statement
-----------------
 Command/Event Sequencing is a very commmon Enterprise Integration Problem/Pattern. While command sequencing is an issue on
the dispatching side, on a multi-threaded envrionment ( or a multi-node) environment, it gets even more complicated if commands
have to be executed in a serial fashion. There are some well-known tools/frameworks that guarantee sequencing like Message Queue( sequencing),
Integration frameworks like Spring/Camel/Mule etc.
Another issue with sequencing is when they are limited to a certain scope ( like system-wide/client/module/transaction) etc.
With SMART integration, we have all of the above challenges.

•	PS/Scan <--> App Server
•	App Server --> App Server
•	App Server --> Browser
•	Browser --> App Server

We need to come up a solution that is end-point agnostic ( isomorphic if possible, but at the very least the pattern should be reproducible)
It does not matter how these messages/events come in ( REST/SOAP over HTTP or JMS/AMQP/STOMP over AMQP), the solution should be generic
enough to process the message in a sequential/linear/serial fashion. The solution should also be able to specify scopes around what needs
to be sequenced.


Solution
---------

We took a cursory look at the various open-source integration frameworks in depth to see if they are able to resolve
these issues. Given the time-frame, we have chosen to come up with a simple home-grown framework to handle this issue.
By doing this excercise, we will be able to understand the challenges in a greater depth in an isolated piece of code/environment.

Please look at the architecture folder for the overall design

# How to
Steps -
```
1 . git clone https://github.com/stevesri/dsnyhackathon.git
2.  git clone https://github.com/sandeshdanwale/feprocessor.git
3.  git clone https://github.com/sandeshdanwale/nodeproxy.git
4.  Go to /dsnyhackathon/source/smart-app
5.  Run the command `gradle bootRun` - this will start an app server at localhost:8080
6.  Check web server started properly by accessing http://localhost:8080/smartapp
7.  Additional but optional command to test 
curl --noproxy localhost -v -H "Content-Type: application/json" --request POST  http://localhost:8080/smartapp/processCommand -d '{ "name":"AddTask", "sequenceScope": "clientid-domain1", "sequenceId": 1, "message": "This would be  json message"}'
8.  Go to /nodeproxy
9.  Start nodeproxy - node nodeproxy.js 4000 127.0.0.1 8080. Node proxy also acts as a web server (servers static      files at this point).
10.  Access the website using http://localhost:4000/index.html.
11. Drag sequence number cards and drop them to the other column. Once the columns is filled up, click on submit       button.
12. This will send the ajax requests to the node-proxy server in the sequence mentioned. Node proxy will further       send them to app server. App server will take care of sequencing.
13. Please see testing section below for further instructions

```
## Directory Layout

```
App/                    --> all of the source files for the application
  dsnyhackathon
   source/
    smart-app
  fepreocessor
  nodeproxy
   nodeproxy.js

```

Testing
--------
Currently the testing capabilities are limited to reviewing the log files to determine the order in which the commands were executed. However automated end-to-end tests could be built if the server sents back a message confirmation after the message is processed. Due to lack of time, we did not build that. Unit Tests could be built if stub dependencies could be injected.

Manual Testing
---------------
Use the UI to stack up commands in an incorrect sequence and send. The server logs will show that they get processed according to the sequence numbers.

We think it is a  wow factor UI, check it out.

If you want to use curl ( send the following message with different sequence id from 4 to 1)

curl --noproxy localhost -v -H "Content-Type: application/json" --request POST  http://localhost:8080/smartapp/processCommand -d '{ "name":"AddTask", "sequenceScope": "clientid-domain1", "sequenceId": 1, "message": "This would be  json message"}'

Current Drawbacks
-----------------
This solution does not use a centeralized store ( DB) for Sequence Registry. The current implementation will work if it is tested on a single JVM. Spring Integration will be a perfectly suited candidate for receiving messages on different end-points ( REST/SOAP/AMQP - Gatways) and processing messages. We could write custom Message Processors.

