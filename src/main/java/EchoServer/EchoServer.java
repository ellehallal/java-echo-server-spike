package EchoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class EchoServer {

    public static void startServer(int portNumber) {
        try(var serverSocket = new ServerSocket(portNumber)) {
            var socket = serverSocket.accept();
            System.out.println("Client connected");

            var input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var output = new PrintWriter(socket.getOutputStream(), true);
            handleClientInput(input, output);

        } catch(IOException exception) {
            System.out.println("Server exception: " + exception.getMessage());
        }
    }


    private static void handleClientInput
            (BufferedReader input, PrintWriter output) throws IOException {
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
