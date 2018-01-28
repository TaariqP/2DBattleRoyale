package Server.TCPServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class PortListener extends Thread{
  protected final int port = 6788;
  protected ServerSocket welcomeSocket;
  NetworkController controller;


  public PortListener(NetworkController controller){
    this.controller = controller;
    try {
      System.out.println("Producing on port " + port);
      welcomeSocket = new ServerSocket(port);
      start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public PortListener(NetworkController controller, int port){
    this.controller = controller;
    try {
      System.out.printf("");
      welcomeSocket = new ServerSocket(port);
      start();
    } catch (IOException e) {
      e.printStackTrace();
    }
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
        String giveBack = controller.response(clientSentence);
        System.out.println("Received: " + clientSentence);
        String capitalizedSentence = clientSentence.toUpperCase() + '\n';
//        outToClient.writeBytes(giveBack);
      }
    }catch(IOException e){
      e.printStackTrace();
    }
  }

}
