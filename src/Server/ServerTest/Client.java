package Server.ServerTest;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

  private ObjectOutputStream output;
  private ObjectInputStream input;
  private String message = "";
  private String serverIP;
  private Socket connection;
  // ip address of the server
  private String serverIp;

  public Client(String host) {
    serverIp = host;
  }


  public void run2() {
    while (true) {
      System.out.println("attempting connection ...... ");
      try {
        connection = new Socket("127.0.0.1", 1337);
        if (connection.isConnected()) {
          break;
        }
      } catch (IOException e) {
        // TODO Auto-generated catch block

      }
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block

      }

    }
    System.out.println(
        "connected ..... \n " + connection.getInetAddress().getHostName());
  }

  public void run() {
    while (true) {
      System.out.println("attempting connection ...... ");
      try {
        connection = new Socket("127.0.0.1", 1337);
        if (connection.isConnected()) {
          break;
        }
      } catch (IOException e) {
        // TODO Auto-generated catch block

      }
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block

      }

    }
    System.out.println(
        "connected ..... \n " + connection.getInetAddress().getHostName());
  }

  private void setupStreams() throws IOException {
    output = new ObjectOutputStream(connection.getOutputStream());
    output.flush();
    input = new ObjectInputStream(connection.getInputStream());
    System.out.println("streams are now setup");
  }

  public void startRunning() {

    try {
      // connect to one specific server
      // connectToServer();
      setupStreams();
      whileRunning();
    } catch (EOFException eofException) {
      System.out.println("Client terminate connection \n");
    } catch (IOException ioException) {
      System.out.println("error");
    } finally {
      closeStream();
    }

  }

  public void whileRunning() {

    while (true) {

    }

  }


  private void closeStream() {
    System.out.println("\n closing Stream down .....");
    try {
      output.close();
      input.close();
      connection.close();
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

}
