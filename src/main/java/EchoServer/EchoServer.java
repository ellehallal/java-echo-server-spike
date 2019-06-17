package EchoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.sql.Connection;

public class EchoServer {
    private final ServerSocket serverSocket;
    private BufferedReader input;
    private PrintWriter output;

    public EchoServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try(var socket = serverSocket.accept()) {
            System.out.println("Client connected");

            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            handleClientInput();

        } catch(IOException exception) {
            System.out.println("Server exception: " + exception.getMessage());
        }
    }


    private void handleClientInput() throws IOException {
        while(true) {
            var clientInput = input.readLine();
            if (clientInput.equals("exit")) {
                System.out.println("Client disconnected");
                break;
            }
            output.println("Echo from server: " + clientInput);
        }
    }

}
