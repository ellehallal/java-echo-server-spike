package EchoServer;

import java.io.IOException;
import java.net.ServerSocket;

public class EchoServer {

    public static void startServer(int portNumber) {
        try(var serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server started");
            while(true) {
                var socket = serverSocket.accept();
                var newThread = new EchoServerThread(socket);
                newThread.start();
            }

        } catch(IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
