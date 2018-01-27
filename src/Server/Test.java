package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Test {
  private static InetAddress ipAddress;
  private static DatagramSocket socket;
  public static void main(String[] args) {
    try {
      socket = new DatagramSocket();
      ipAddress = InetAddress.getByName("129.31.184.75");
    } catch (SocketException e) {
      e.printStackTrace();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }

    sendData("Hello".getBytes());

  }

  public static void sendData(byte[] data){
    DatagramPacket packet = new DatagramPacket(data,data.length,ipAddress,1331);
    try {
      socket.send(packet);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
