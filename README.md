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

# How to
Steps -
```
1 . git clone https://github.com/stevesri/dsnyhackathon.git
2.  git clone https://github.com/sandeshdanwale/feprocessor.git
3.  git clone https://github.com/sandeshdanwale/feprocessor.git
4.  Go to /dsnyhackathon/source/smart-app
5.  Run the command `gradle bootRun` - this will start an app server at localhost:8080
6.  Check web server started properly by accessing http://localhost:8080/smartapp
7.  Go to /nodeproxy
8.  Start nodeproxy - node nodeproxy.js 4000 127.0.0.1 8080. Node proxy also acts as a web server (servers static      files at this point).
9.  Access the website using http://localhost:4000/index.html.
10. Drag sequence number cards and drop them to the other column. Once the columns is filled up, click on submit       button.
11. This will send the ajax requests to the node-proxy server in the sequence mentioned. Node proxy will further       send them to app server. App server will take care of sequencing.
12. Please see testing section below for further instructions

```

