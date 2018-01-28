package Server;

import Entity.Player;
import Map.Coordinate;
import Server.Packet.Connects;
import Server.Packet.PacketMove;
import Server.Packet.PacketOtherPlayer;
import Server.Packet.PacketYourPlayer;
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
  private Map<Player, InetAddress> players;
  private Map<String,Player> playersIdMap;


  public static void main(String[] args) {
    new Server(null);
  }

  public Server(Game game){
    connects = new ArrayList<>();
    players = new HashMap<>();
    playersIdMap = new HashMap<>();
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
        int id = currentPlayerId;
        currentPlayerId++;
        System.out.println("A player has joined");
        connects.add(new Connects(address, port));

        Player play = new Player(datas.substring(2), id,
            new Coordinate(64*64, 64*64)
            , null, null,0, 0, false);
        players.put(play, address);
        System.out.println("Sending a player back");
        PacketYourPlayer p = new PacketYourPlayer("Dave", id);
        playersIdMap.put(Integer.toString(id), play);
        sendData(p.getData(), address, port);
        System.out.println("Current id " + currentPlayerId);
        for(int oldID = 0; oldID < currentPlayerId - 1; oldID++){
          System.out.println("oldID " + oldID);
          Player pl = playersIdMap.get(Integer.toString(oldID));
          Connects connect = getConnectFromIp(players.get(pl));
          PacketOtherPlayer other = new PacketOtherPlayer(pl.getName(),
              pl.getID(), pl.getPlayerPosition().getX(), pl.getPlayerPosition().getY());
          sendData(other.getData(), address, port);
        }
        for(Connects c : connects){
          if(c.getIp() != address){
            PacketOtherPlayer other = new PacketOtherPlayer("Dave", id, 64*64, 64*64);
            sendData(other.getData(), c.getIp(), c.getPort());
          }
        }
        break;
      case "02":
        String[] parts = datas.split(",");
        System.out.print("Player moved " + playersIdMap.get(parts[1]).getName());

        for(Connects cs: connects){
          sendData(data, cs.getIp(), cs.getPort());
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

  private Connects getConnectFromIp(InetAddress ip){
    for(Connects c : connects){
      if(c.getIp().toString().equals(ip.toString())){
        return c;
      }
    }
    return null;
  }
}
