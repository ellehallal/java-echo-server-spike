package EchoClient;

import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException {
        EchoClient.startClient("localhost", 5000);
    }
}