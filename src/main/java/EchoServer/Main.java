package EchoServer;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) throws IOException {
        EchoServer.startServer(5000);
    }
}
