package EchoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class EchoServer {

    public static void startServer(int portNumber) {
        try(var serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server started");
            var socket = serverSocket.accept();
            System.out.println("Client connected");

            var input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var output = new PrintWriter(socket.getOutputStream(), true);
            handleClientMessage(input, output);

        } catch(IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }


    private static void handleClientMessage
            (BufferedReader input, PrintWriter output) throws IOException {
        while(true) {
            var clientMessage = input.readLine();
            if (clientMessage.equals("exit")) {
                System.out.println("Client disconnected");
                break;
            }
            System.out.println("Message received from client: " + clientMessage);
            System.out.println("Sending message back to client...");
            output.println("Echo from server: " + clientMessage);
        }
    }

}
