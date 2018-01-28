package Server.Packet;

import Entity.Player;

public class PacketOtherPlayer extends Packet {
    private String name;
    private int id;
    private int x;
    private int y;
  public PacketOtherPlayer(String name, int id, int x, int y) {
    super(PacketType.OtherPlayer);
    this.id = id;
    this.x = x;
    this.y = y;
    System.out.println("constructor: id " + id + " x " + x + " y " + y);

  }

  public byte[] getData(){
    System.out.println("id " + id + " x " + x + " y " + y);
    System.out.println("04,"+name + "," + Integer.toString(id) + "," + Integer.toString(x) +
        "," + Integer.toString(y));
    return ("04,"+name + "," + Integer.toString(id) + "," + Integer.toString(x) +
    "," + Integer.toString(y)).getBytes();
  }

}
