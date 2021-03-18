# Auction-Server

CO225 - Software Construction <br/>
Project 02 (Group Project) - Auction Server  <br/>
Group Number - 04  <br/>
Members : 
  - M.D.S. De Silva (E/16/069)
  - K.R. De Silva (E/16/068)
  - N.C.S.K. De Silva (E/16/070)

### Introduction

This is a Multi-threaded Socket Server for Stock Auctions developed using JAVA.<br/>
Clients can connect to the server and bid for items in the stock exchange


### Compile and Run the server:

	javac MainClass.java
	java MainClass


### Connect as a client:

	$ nc localhost 2000	

### After connecting as a client

  - Client should gives a user name
  - Then client is required to provide a symbol of a item that he wants to bid
  - If client enter a invalid symbol he gets another another chance to enter a valid symbol
  - The bid value entered by client is accepted only if it is larger than current price
  - Client can logout using 'quit' word


### Server GUI details :

  - The GUI displays the stock prices of FB, VRTU, MSFT, GOOGL, YHOO, XLNX, TSLA and TXN 
  - It shows all previous bid details under the topic Bid history
  - Connection Details of each client is showed under the topic, client history
  - Can view all stock items by clicking on the view all items button

### Note
  
  - In this server many number of clients can connect at the same time (Handled using threads) but each client should have unique user names
  - A client can logout from the server and he can connect again using **nc command** in the terminal and bid for previous stock or another stock.
	


