package Server.Packet;

public class PacketMove {
  private int x, y;
  private String id;
  private double rot;
  public PacketMove(String id, int x, int y, double rot){
    this.x = x;
    this.y = y;
    this.id = id;
    this.rot = rot;
  }


  public byte[] getData(){
    return ("02," + id + "," + Integer.toString(x) + "," + Integer.toString(y) + "," + Double.toString(rot)).getBytes();
  }

  public String toString(){
    return ("02," + id + "," + Integer.toString(x) + "," + Integer.toString(y) + "," + Double.toString(rot));
  }

}
