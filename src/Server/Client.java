package Server;

import Entity.Bullet;
import Entity.Player;
import Entity.PlayerState;
import Map.Coordinate;
import Server.Packet.PacketJoin;
import Server.Packet.PacketMove;
import Server.Packet.PacketShot;
import States.Camera;
import States.Clickable;
import States.Game;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Client extends Thread{
  private static InetAddress ipAddress;
  private static DatagramSocket socket;
  private Map<String, Player> playerMap;
  private Game game;


  public Client(Game game){
    this.game = game;
    playerMap = new HashMap<>();
    try {
      socket = new DatagramSocket();
      ipAddress = InetAddress.getByName("129.31.184.75");
    } catch (SocketException e) {
      e.printStackTrace();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }

    try {
      this.socket = new DatagramSocket(1331);
    } catch (SocketException e) {
      e.printStackTrace();
    }

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
    String dataString = new String(data).trim();
    String[] parts = dataString.split(",");
    switch (dataString.substring(0,2)){
      case "02":
        Player p = playerMap.get(parts[1]);
        
        p.move(Integer.valueOf(parts[2]),Integer.valueOf(parts[3]), Double.valueOf(parts[4]), parts[5]);

        break;
      case "03":
        System.out.println("Server sent a player back");
        System.out.println(Arrays.toString(parts));
        Player p2 = game.addPlayableplayer(parts[1], parts[2], 64*64, 64*64);
        playerMap.put(parts[2],p2);
        break;
      case "04":
        System.out.println("Server sent back another player");
        Player p3 = game.addPlayer(parts[1], parts[2], Integer.valueOf(parts[3]), Integer.valueOf(parts[4]));
        playerMap.put(parts[2],p3);
        break;
      case "05":
        System.out.println("Receiving bullet");
        game.addBullet(new Bullet(Integer.valueOf(parts[4]),Double.valueOf(parts[3]),new Coordinate(Integer.valueOf(parts[1]), Integer.valueOf(parts[2])), null, Integer.valueOf(parts[5])));
        break;
      case "06":
        game.setSeed(Integer.valueOf(parts[1]));
    }

  }

  public static void sendData(byte[] data){
    DatagramPacket packet = new DatagramPacket(data,data.length,ipAddress,1337);
    try {
      socket.send(packet);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void requestPlayer() {
    PacketJoin join = new PacketJoin();
    sendData(join.getData());
    System.out.println("Requested player");
  }

  public void move(String id, int x, int y, double rot, PlayerState state){
    PacketMove move = new PacketMove(id, x, y, rot, state);
    sendData(move.getData());
  }

  public void shoot(Bullet b) {
    PacketShot shot = new PacketShot(b.getX(), b.getY(), b.getRotation(),
        b.getDamage(), Integer.toString(b.getShooterID()));
    sendData(shot.getData());
  }
}
