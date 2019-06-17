package EchoServer;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) throws IOException {
        var serverSocket = new ServerSocket(5000);
        var echoServer = new EchoServer(serverSocket);
        echoServer.startServer();
    }
}
