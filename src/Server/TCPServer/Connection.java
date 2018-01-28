package Server.TCPServer;

import Server.Packet.Connects;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {
  private InetAddress ip;

  public InetAddress getIp() {
    return ip;
  }

  public int getPort() {
    return port;
  }

  private int port;


  public Connection(InetAddress ip, int port){
    this.ip = ip;
    this.port = port;
  }

  public Connection(String ip, int port){
    this.port = port;
    try {
      this.ip = InetAddress.getByName(ip);
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
  }

  public String sendData(String data){
    try {
      Socket clientSocket = new Socket(ip, port);
      DataOutputStream outToServer = new DataOutputStream(
          clientSocket.getOutputStream());
      BufferedReader inFromServer = new BufferedReader(
          new InputStreamReader(clientSocket.getInputStream()));
      outToServer.writeBytes(data + '\n');
      String in =  inFromServer.readLine();
      clientSocket.close();
      return in;


    }catch(IOException e){
      e.printStackTrace();
    }
    return null;
  }

  public boolean equals(Connection c){
    return c.getIp() == ip && c.getPort() == port;
  }




}
