package Server.ServerTest;

import Entity.Player;
import Server.Packet.Connects;
import States.Game;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class Server {

  private ObjectOutputStream output;
  private ObjectInputStream input;
  private ServerSocket server;
  private Socket connection;
  private Map<Player, InetAddress> players;
  private Map<String,Player> playersIdMap;
  private Game game;
  private List<Connects> connects;
  private int currentPlayerId = 0;

  public Server(Game game) {
    connects = new ArrayList<>();
    players = new HashMap<>();
    playersIdMap = new HashMap<>();
    this.game = game;
  }

  public void startRunning() {
    try {
      /*
       * The program tries to create a server socket and binds it to the
			 * local Port 5555, with the maximum length of the queue of incoming
			 * connections
			 */
      server = new ServerSocket(1337, 100);
      while (true) {
        try {
          waitForConnection();
          setupStreams();
          whileRunning();
        } catch (EOFException eofException) {
          System.out.println("\n Server ended the connection!");
        } finally {
          closeConnections();
        }
      }
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

  public void whileRunning(){
    while (true){

    }
  }

  // wait for connection, then display connection info
  public void waitForConnection() throws IOException {
    System.out.println("waiting for someone to connect...\n");
    connection = server.accept();
    System.out.println(
        "Now connected to " + connection.getInetAddress().getHostName());
  }

  // get stream to send and receive data
  public void setupStreams() throws IOException {
    output = new ObjectOutputStream(connection.getOutputStream());
    output.flush();
    input = new ObjectInputStream(connection.getInputStream());
    System.out.println("\n Streams are now setup! \n");
  }


  public void closeConnections() {
    System.out.println("\n Closing connections ...");
    try {
      output.close();
      input.close();
      connection.close();
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

}
