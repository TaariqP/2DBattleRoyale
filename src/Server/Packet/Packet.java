package Server.Packet;

public abstract class Packet {
  private PacketType type;
  private String data;

  public Packet(PacketType type){
    this.type = type;
  }

  public byte[] getData(){
    System.out.println("You need to implement this");
    return null;
  }

  public String toString(){
    return "Help :(";
  }

}
