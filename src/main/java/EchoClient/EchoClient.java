package EchoClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

    public static void startClient(String hostAddress, int portNumber)  throws IOException {
        try(Socket socket = new Socket(hostAddress, portNumber)) {

            var inputStreamReader = new InputStreamReader(socket.getInputStream());
            var echoes = new BufferedReader(inputStreamReader);
            var stringToEcho = new PrintWriter(socket.getOutputStream(), true);
            var scanner = new Scanner(System.in);

            var echoString = "";
            var responseFromServer = "";

            while(!echoString.equals("exit")) {
                System.out.println("Please enter the string to be echoed: ");
                echoString = scanner.nextLine();

                stringToEcho.println(echoString);

                if(!echoString.equals("exit")) {
                    responseFromServer = echoes.readLine();
                    System.out.println(responseFromServer);
                }
            }

        } catch(IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }

    }
}
