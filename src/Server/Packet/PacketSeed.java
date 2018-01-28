package Server.Packet;

public class PacketSeed extends Packet{
  int seed = 1;


  public PacketSeed(int seed) {
    super(PacketType.Seed);
    this.seed = seed;
  }

  public byte[] getData(){
    return ("06," + seed).getBytes();
  }

}
