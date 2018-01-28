package Server.ServerTest;

import Entity.Player;
import Server.Packet.Connects;
import States.Game;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerTest {



  public static void main(String[] args) {


    Server server1 = new Server(null);
    server1.startRunning();
  }

}
