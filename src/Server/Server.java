package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server extends Thread{

  private DatagramSocket socket;

  public static void main(String[] args) {
    new Server();
  }

  public Server(){
    try {
      this.socket = new DatagramSocket(1331);
    } catch (SocketException e) {
      e.printStackTrace();
    }
    start();
    System.out.println("SERVER: started...");
  }

  @Override
  public void run() {
    while (true){
      byte[] data = new byte[1024];
      DatagramPacket packet = new DatagramPacket(data,data.length);
      try {
        socket.receive(packet);
      } catch (IOException e) {
        e.printStackTrace();
      }
      HandlePacket(packet.getData(),packet.getAddress(),packet.getPort());
    }
  }

  private void HandlePacket(byte[] data, InetAddress address, int port) {
    System.out.println("Recieved packet " + new String(data).trim());
  }
}
