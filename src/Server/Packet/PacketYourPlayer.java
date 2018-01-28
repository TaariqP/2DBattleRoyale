package Server.Packet;

public class PacketYourPlayer extends Packet{

  private int id;
  private String name;

  public PacketYourPlayer(String name, int id){
    super(PacketType.YourPlayer);
    this.id = id;
    this.name = name;
  }

  @Override
  public byte[] getData() {
    return ("03," + name + "," + Integer.toString(id)).getBytes();
  }

  public String toString(){
    return ("03," + name + "," + Integer.toString(id));
  }
}
