package Server.TCPServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ConnectionCreator extends PortListener{

  TCPServer server;
  Map<InetAddress, Connection> ipsToConnection;
  public ConnectionCreator(TCPServer server) {
    super(server);
    this.server = server;
  }

  public ConnectionCreator(TCPServer tcpServer, int port) {
    super(tcpServer, port);
  }

  public void run(){
    try {
      while (true) {
        Socket connectionSocket = welcomeSocket.accept();
        BufferedReader inFromClient =
            new BufferedReader(
                new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(
            connectionSocket.getOutputStream());
        String clientSentence = inFromClient.readLine();
        String giveBack = server.newConnection(clientSentence, connectionSocket.getInetAddress(), connectionSocket.getPort());
        System.out.println("Received: " + clientSentence);
        String capitalizedSentence = clientSentence.toUpperCase() + '\n';
        outToClient.writeBytes(giveBack);
      }
    }catch(IOException e){
      e.printStackTrace();
    }
  }


}


