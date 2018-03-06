# TicTacToeUltimate

How to play? Order is important. First You have to run server (TicTacToeServer) and then you can run as many client (TicTacToeUltimate) as you want, server will handle it.

Tech details: 
  languages: Java, fxml, CSS, 
  graphic library: JavaFX. 
  by default clients connect to localhost

Internet protocol: 
In this app I used socket's to send data from client app (TicTacToeUltimate) to server app (TicTacToeServer) and fro via TCP/IP protocol. Why TCP/IP instead of UDP? Although UDP is faster than TCP/IP, I didn't use it, because only things that are sent is few string to confirm connection and field interpretation in String, more important is to always send whole message, without data loss.

Logic of server: 
Most time server just listen for clients. When two clients connect to server then it create separate thread that handle them. After it listen for another clients. Tech details: server doesn't implement any custom object for Players in order to use less computing power.

todo list:
  - handle game end (app check if game is over, but do nothing with it),
  - handle situation when during game one of player disconnect,
  - menu,
  - command for players, like: wait for opponent, your turn, wait for opponent move,
  - better frontend.
