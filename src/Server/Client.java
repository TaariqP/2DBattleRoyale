package Server;

import Entity.Player;
import Server.Packet.PacketJoin;
import Server.Packet.PacketMove;
import States.Camera;
import States.Clickable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Map;

public class Client extends Thread{
  private static InetAddress ipAddress;
  private static DatagramSocket socket;
  private Map<String, Player> playerMap;


  public static void main(String[] args) {
    Client c = new Client();

  }

  public Client(){
    try {
      socket = new DatagramSocket();
      ipAddress = InetAddress.getByName("129.31.184.75");
    } catch (SocketException e) {
      e.printStackTrace();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }

    PacketJoin join = new PacketJoin();
    sendData(join.getData());
    PacketMove move = new PacketMove(3000,3010, "1");
    sendData(move.getData());

    start();

  }

  @SuppressWarnings("Duplicates")
  public void run(){
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
    switch (data.toString().substring(0,2)){
      case "02":
        //playerMap.get()
      case "03":
       break;
    }

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
