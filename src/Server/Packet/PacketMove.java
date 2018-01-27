package Server.Packet;

public class PacketMove {
  private int x, y;
  private String id;
  public PacketMove(int x, int y, String id){
    this.x = x;
    this.y = y;
    this.id = id;
  }


  public byte[] getData(){
    return ("02," + id + "," + Integer.toString(x) + "," + Integer.toString(y)).getBytes();
  }



}
