package Server.TCPServer;

import java.net.InetAddress;

public class NetworkController extends Thread{
  protected PortListener listener;
  protected Connection connection;


  public NetworkController(Connection connection){
    this.connection = connection;
    listener = new PortListener(this);
  }

  public NetworkController(Connection connection, int port){
    this.connection = connection;
    listener = new PortListener(this, port);
  }

  public NetworkController() {
  }

  public String response(String clientSentence) {
    return "Thanks";
  }


}
