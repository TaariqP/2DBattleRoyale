package Server.ServerTest;

public class ClientTest {

  public static void main(String[] args) {
    // TODO Auto-generated method stub

    // 127.0.0.1 means localhost
    Client testClient = new Client("129.31.224.147");

    testClient.run();
    testClient.startRunning();
  }


}
