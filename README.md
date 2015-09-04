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


--ongoing 
