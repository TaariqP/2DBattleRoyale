package Server.TCPServer;

import Entity.Player;
import Server.Packet.PacketOtherPlayer;
import States.Game;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Map.Coordinate;


public class TCPServer extends NetworkController{

  public static void main(String[] args) {
    TCPServer server = new TCPServer(null, null);
  }


 private Map<InetAddress, Connection> ipsToConnection;
 private Map<String, Player> idToPlayer;
 private Map<String, Connection> idToConnection;
 private Game game;
 private ConnectionCreator cCreator;
 private int currentID = 0;


  public TCPServer(Connection connection, Game game) {
    System.out.println("Server created");
    cCreator = new ConnectionCreator(this, 6787);
    ipsToConnection = new HashMap<>();
    idToPlayer = new HashMap<>();
    idToConnection = new HashMap<>();
    this.game = game;
  }

  public String newConnection(String input, InetAddress inetAddress,
      int port) {
    Connection connect;
    if(ipsToConnection.containsKey(inetAddress)){
      connect = ipsToConnection.get(inetAddress);
    }else{
      connect = new Connection(inetAddress, port);
      ipsToConnection.put(inetAddress, connect);
      System.out.println("Got connection from " + connect.getIp());
    }
    String[] parts = input.split(",");
    switch (input.substring(0,2)){
      case "01":
        int id = currentID;
        currentID++;
        System.out.println("A player has joined and given id " + id);
        Player play = new Player(input.substring(2), id,
            new Coordinate(64*64, 64*64)
            , null, null,0, 0, false);
        PacketOtherPlayer other = new PacketOtherPlayer(input.substring(2), id, 64*64, 64*64);
        for(int oldID = 0; oldID < currentID - 1; oldID++){
          idToConnection.get(oldID).sendData(other.toString());
          Player player = idToPlayer.get(oldID);
          other = new PacketOtherPlayer(player.getName(),player.getID(),player.getPlayerPosition().getX(),
              play.getPlayerPosition().getY());
          connect.sendData(other.toString());
        }
      case "02":
        System.out.println("Move is disabled");
        /*
        for(int i = 0; i < currentID; i++){
          idToConnection.get(i).sendData(input);
        }
        */
    }

    return "thanks";
  }
}