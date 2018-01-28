package Server.Packet;

public class PacketShot extends Packet{

  private final int dmg;
  private int x, y;
  private double rot;
  private String id;


  public PacketShot(int x, int y, double rot, int dmg, String id) {
    super(PacketType.Shot);
    this.x = x;
    this.y = y;
    this.rot = rot;
    this.id = id;
    this.dmg = dmg;
  }

  public byte[] getData(){
    return ("05," + Integer.toString(x) + "," + Integer.toString(y) + "," +
    Double.toString(rot) + "," + dmg + "," + id).getBytes();
  }
}
