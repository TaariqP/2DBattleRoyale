package Server.Packet;

public abstract class Packet {
  private PacketType type;
  private String data;

  public Packet(PacketType type){
    this.type = type;
  }

}
