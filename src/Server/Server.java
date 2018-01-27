package Server;

import Entity.Player;
import Map.Coordinate;
import Server.Packet.Connects;
import Server.Packet.PacketMove;
import States.Game;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server extends Thread{

  private DatagramSocket socket;
  private Game game;
  private List<Connects> connects;
  private int currentPlayerId = 0;
  private Map<InetAddress,Player> players;


  public static void main(String[] args) {
    new Server(null);
  }

  public Server(Game game){
    connects = new ArrayList<>();
    players = new HashMap<>();
    this.game = game;
    try {
      this.socket = new DatagramSocket(1337);
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
    String datas = new String(data);
    switch (datas.substring(0,2)){
      case  "01" :
        System.out.println("A player has joined");
        connects.add(new Connects(address, port));
        players.put(address, new Player(datas.substring(2), currentPlayerId++,
            new Coordinate(3000,3000), null, null,0, 0));
        break;
      case "02":
        System.out.print("Player moved " + players.get(address).getName());
        String[] parts = datas.split(",");
        PacketMove move = new PacketMove(Integer.valueOf(parts[2]), Integer.valueOf(parts[3]), parts[1]);
        for(Connects cs: connects){
          sendData(move.getData(), cs.getIp(), cs.getPort());
        }
    }
  }

  private void sendData(byte[] data ,InetAddress ip, int port){
    DatagramPacket packet = new DatagramPacket(data,data.length,ip,port);
    try {
      socket.send(packet);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
