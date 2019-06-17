package EchoClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

    public static void startClient(String hostAddress, int portNumber) throws IOException {
        try (Socket socket = new Socket(hostAddress, portNumber)) {
            var scanner = new Scanner(System.in);
            var stringToEcho = new PrintWriter(socket.getOutputStream(), true);
            var inputStreamReader = new InputStreamReader(socket.getInputStream());
            var echoes = new BufferedReader(inputStreamReader);
            var echoString = "";

            while (isEchoStringNotExit(echoString)) {

                echoString = getUserInput(scanner);
                inputToServer(stringToEcho, echoString);

                if (isEchoStringNotExit(echoString)) {
                    responseFromServer(echoes);
                }
            }

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }

    private static String getUserInput(Scanner scanner) {
        System.out.println("Please enter the string to be echoed: ");
        return scanner.nextLine();
    }

    private static void inputToServer(PrintWriter stringToEcho, String echoString) {
        stringToEcho.println(echoString);
    }

    private static boolean isEchoStringNotExit(String echoString) {
        return !echoString.equals("exit");
    }

    private static void responseFromServer(BufferedReader echoes) throws IOException {
        var responseFromServer = echoes.readLine();
        System.out.println(responseFromServer);
    }
}
