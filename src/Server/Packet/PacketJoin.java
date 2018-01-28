package Server.Packet;

public class PacketJoin extends Packet{

  public PacketJoin(){
    super(PacketType.Join);
  }

  public byte[] getData(){
    return "01".getBytes();
  }

  public String toString(){
    return "01";
  }

}
