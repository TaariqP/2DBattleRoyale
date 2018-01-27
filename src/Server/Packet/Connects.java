package Server.Packet;

import java.net.InetAddress;

public class Connects {
  private InetAddress ip;

  public InetAddress getIp() {
    return ip;
  }

  public int getPort() {
    return port;
  }

  private int port;

  public Connects(InetAddress ip, int port){
    this.ip = ip;
    this.port = port;
  }

}
