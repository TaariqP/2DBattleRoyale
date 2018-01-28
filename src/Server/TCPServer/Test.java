package Server.TCPServer;

import java.net.InetAddress;

public class Test {

  public static void main(String[] args) {
    TCPServer server = new TCPServer(null,null);
    Connection conect = new Connection("129.31.184.75", 6789);
    conect.sendData("Hello");
  }

}
