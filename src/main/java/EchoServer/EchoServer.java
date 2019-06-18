package EchoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class EchoServer {

    public static void startServer(int portNumber) {
        try (var serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server started");


            while (true) {
                var socket = serverSocket.accept();
                var inputStreamReader = new InputStreamReader(socket.getInputStream());
                var input = new BufferedReader(inputStreamReader);
                var output = new PrintWriter(socket.getOutputStream(), true);

                var newThread = new ClientHandler(socket, input, output);
                newThread.start();
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}