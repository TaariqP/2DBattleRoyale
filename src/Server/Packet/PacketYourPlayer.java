package Server.Packet;

public class PacketYourPlayer extends Packet{

  private int id;

  public PacketYourPlayer(int id){
    super(PacketType.YourPlayer);
    this.id = id;
  }

  @Override
  public byte[] getData() {
    return ("03" + Integer.toString(id)).getBytes();
  }
}
