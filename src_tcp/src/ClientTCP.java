/**
 * @author <YOUR NAME HERE>
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTCP {

    public static void main(String[] args) throws IOException {
        // SERVER DATA:
        //* FIXED: comment them out if you read them from the command line.
        // String serverName = "127.0.0.1"; // local address
        // int serverPort = 12345;
        //* VARIABLES: uncomment them if you read them from the command line
        String serverName = args[0];
        int serverPort = Integer.parseInt(args[1]);

        // SOCKET
        Socket serviceSocket = null;

        // FLOWS FOR SENDING AND RECEIVING
        PrintWriter out = null;
        BufferedReader in = null;

        //* TODO: Create socket and connect to server

        try {
            serviceSocket = new Socket(serverName, serverPort);
        } catch (IOException e) {
            System.err.println("ERROR: cannot connect to " + serverName + " : " + serverPort);
            System.exit(1);
        }

        //* TODO: Initialise input/output streams of connected socket in PrintWriter and BufferedReader variables

        try {
            out = new PrintWriter(serviceSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(serviceSocket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Could not get I/O for " + serverName);
            System.exit(1);
        }

        System.out.println("STATUS: Client is connected to the server");
        //* TODO: Receive welcome message from server and display it

        // Get text from keyboard
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        System.out.println("Enter a text to be sent (to finish enter a text without leading digit): ");
        userInput = stdIn.readLine();

        //* TODO: Check if the user has started the end of the interaction
        while(Character.isDigit(userInput.charAt(0))) { // service loop

            //* TODO: Send text in userInput to the server via the output stream of the connected socket
            out.println(userInput);
            System.out.println("STATUS: Message sent: " + userInput);
            System.out.println("STATUS: waiting for an answer.");

            //* TODO: Receive text sent by the server via the input stream of the connected socket
            String line = null;
            line = in.readLine();
            System.out.println("STATUS: Reply received: " + line);

            // Read user text by keyboard
            System.out.println("Enter a text to be sent (to finish enter a text without leading digit)");
            userInput = stdIn.readLine();
        } // End of client service loop

        // We exit because the client wants to end the interaction, it has entered a String without a leading digit.
        //* TODO: Send END to the server to indicate the end of the service.
        System.out.println("STATUS: Client is disconnecting from the server");
        out.println("FINISH");

        //* TODO: Receive OK from Server
        String ok = in.readLine();
        System.out.println("STATUS: Ok from the server");


        //* TODO Close flows and socket
        out.close();
        in.close();
        stdIn.close();
        serviceSocket.close();

        System.out.println("Everything is closed");
    }
}
