package Server.Packet;

import Entity.PlayerState;

public class PacketMove {
  private int x, y;
  private String id;
  private double rot;
  private PlayerState state;
  public PacketMove(String id, int x, int y, double rot, PlayerState state){
    this.x = x;
    this.y = y;
    this.id = id;
    this.rot = rot;
    this.state = state;
  }


  public byte[] getData(){
    return ("02," + id + "," + Integer.toString(x) + "," + Integer.toString(y) + "," + Double.toString(rot) + "," + state.toString()).getBytes();
  }

  public String toString(){
    return ("02," + id + "," + Integer.toString(x) + "," + Integer.toString(y) + "," + Double.toString(rot));
  }

}
