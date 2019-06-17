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
    public void run(){
        try {
            System.out.println("Client connected\n");
            handleClientMessage(socket);

        } catch(IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    private static void handleClientMessage(Socket socket) throws IOException {
        var input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        var output = new PrintWriter(socket.getOutputStream(), true);

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
        System.out.println("Sending message back to client...\n");
    }

    private static void echoToClient(PrintWriter output, String clientMessage) {
        output.println("Echo from server: " + clientMessage);
    }

}