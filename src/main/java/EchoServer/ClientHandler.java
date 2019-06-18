package EchoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread{
    private final Socket socket;
    private final BufferedReader input;
    private final PrintWriter output;

    public ClientHandler(Socket socket, BufferedReader input, PrintWriter output) {
        this.socket = socket;
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        try {
            System.out.println("Client connected\n");
            handleClientMessage();

        } catch(IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    private void handleClientMessage() throws IOException {

        while(true) {
            var clientMessage = input.readLine();
            if (clientMessage.equals("exit")) {
                System.out.println("Client disconnected");
                input.close();
                output.close();
                socket.close();
                break;
            }

            displayReceivedClientMessage(clientMessage);
            echoToClient(clientMessage);
        }
    }

    private void displayReceivedClientMessage(String clientMessage) {
        System.out.println("Message received from client: " + clientMessage);
        System.out.println("Sending message back to client...\n");
    }

    private void echoToClient(String clientMessage) {
        output.println("Echo from server: " + clientMessage);
    }
}