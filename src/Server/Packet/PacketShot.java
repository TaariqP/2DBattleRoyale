package Server.Packet;

public class PacketShot extends Packet{
  private int x, y;
  private double rot;

  public PacketShot(int x, int y, double rot) {
    super(PacketType.Shot);
    this.x = x;
    this.y = y;
    this.rot = rot;
  }

  public byte[] getData(){
    return ("05," + Integer.toString(x) + "," + Integer.toString(y) + "," +
    Double.toString(rot)).getBytes();
  }
}
