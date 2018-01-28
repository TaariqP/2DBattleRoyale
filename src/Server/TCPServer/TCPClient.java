package Server.TCPServer;

import Entity.Player;
import Server.Client;
import Server.Packet.PacketJoin;
import Server.Packet.PacketMove;
import States.Game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TCPClient extends NetworkController{
  private static Connection connection = new Connection("129.31.184.75", 6788);
  private Map<String, Player> playerMap;
  private Game game;

  public TCPClient(Game game){
    super(connection);
    this.game = game;
    playerMap = new HashMap<>();
  }



  public String writeData(String data){
    return connection.sendData(data);
  }

  public String response(String clientSentence) {
    String[] parts = clientSentence.split(",");
    switch (clientSentence.substring(0,2)){
      case "02":
        Player p = playerMap.get(parts[1]);
        p.move(Integer.valueOf(parts[2]),Integer.valueOf(parts[3]), Double.valueOf(parts[4]));
        break;
      case "03":
        System.out.println("Server sent a player back");
        //System.out.println(Arrays.toString(parts));
        Player p2 = game.addPlayableplayer(parts[1], parts[2], 64*64, 64*64);
        playerMap.put(parts[2],p2);
        break;
      case "04":
        System.out.println("Server sent back another player");
        Player p3 = game.addPlayer(parts[1], parts[2], Integer.valueOf(parts[3]), Integer.valueOf(parts[4]));
        playerMap.put(parts[2],p3);

    }
    return null;
  }


  public void requestPlayer() {
    PacketJoin join = new PacketJoin();
    connection.sendData(join.toString());
  }

  public void move(String id, int x, int y, double rot) {
    PacketMove move = new PacketMove(id, x, y, rot);
    connection.sendData(move.toString());
  }
}
