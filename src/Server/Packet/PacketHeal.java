package Server.Packet;

public class PacketHeal extends Packet{

  String id;
  public PacketHeal(String id) {
    super(PacketType.Heal);
    this.id = id;
  }

  public byte[] getData(){
    return ("07," + id).getBytes();
  }
}
