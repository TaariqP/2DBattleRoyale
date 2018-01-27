package Server;

import Entity.Player;
import Server.Packet.Connects;
import States.Game;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
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
    players = new ArrayList<>();
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
        players.put(address, new Player());
    }
  }
}
