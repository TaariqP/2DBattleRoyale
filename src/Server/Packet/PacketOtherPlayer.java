package Server.Packet;

import Entity.Player;

public class PacketOtherPlayer extends Packet {
    private String name;
    private int id;
    private int x;
    private int y;
  public PacketOtherPlayer(String name, int id, int x, int y) {
    super(PacketType.OtherPlayer);

  }

  public byte[] getData(){
    return ("04,"+name + "," + Integer.toString(id) + "," + Integer.toString(x) +
    "," + Integer.toString(y)).getBytes();
  }

}
