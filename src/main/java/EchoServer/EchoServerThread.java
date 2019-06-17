package EchoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoServerThread extends Thread{
    private final Socket socket;

    public EchoServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
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

            displayReceivedClientMessage(clientMessage);
            echoToClient(output, clientMessage);
        }
    }

    private static void displayReceivedClientMessage(String clientMessage) {
        System.out.println("Message received from client: " + clientMessage);
        System.out.println("Sending message back to client...");
    }

    private static void echoToClient(PrintWriter output, String clientMessage) {
        output.println("Echo from server: " + clientMessage);
    }

}