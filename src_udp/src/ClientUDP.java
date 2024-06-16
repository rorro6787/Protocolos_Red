import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author <yournamehere>
 */

public class ClientUDP {
    public static void main(String[] args) throws IOException {
        // SERVER DATA:
        //* FIXED: as comments if they are provided as command prompt input
        // String serverName = "127.0.0.1"; //localhost
        // int serverPort = 54322;
        //* VARIABLES: uncomment them if they are provided as command prompt input
        String serverName = args[0];
        int serverPort = Integer.parseInt(args[1]);

        DatagramSocket serviceSocket = null;

        //* TODO: create socket
        serviceSocket = new DatagramSocket();

        // INITIALISE KEYBOARD INPUT
        BufferedReader stdIn = new BufferedReader( new InputStreamReader(System.in) );
        String userInput;
        System.out.println("Type a text to send that starts with a digit (no initial digit means TERMINATE): ");
        userInput = stdIn.readLine(); /*STRING STORED IN userInput*/

        //* TODO: Check if the user wants to TERMINATE the service
        while (Character.isDigit(userInput.charAt(0)))
        {
            //* TODO: Create a datagram with the given String
            DatagramPacket myDatagram = new DatagramPacket(userInput.getBytes(),
                    userInput.getBytes().length, InetAddress.getByName(serverName), serverPort);
            //* TODO: Send the datagram through the socket
            serviceSocket.send(myDatagram);
            System.out.println("STATUS: Waiting for the reply");

            //* TODO: Create and initialise an EMPTY datagram to recieve the reply (max 800 bytes)
            int bufferSize = 800;
            byte [] buffer = new byte[bufferSize];
            DatagramPacket receivedDatagram = new DatagramPacket(buffer, bufferSize);
            //* TODO: Recieve the replied datagram
            serviceSocket.receive(receivedDatagram);
             //* TODO: Extract the content of the datagram body in variable line
            String line = null;
            line = new String(receivedDatagram.getData(), receivedDatagram.getOffset(),
                    receivedDatagram.getLength(), StandardCharsets.UTF_8);

            System.out.println("echo: " + line);
            System.out.println("Type a text to send that starts with a digit (no initial digit means TERMINATE): ");
            userInput = stdIn.readLine();
        }

        System.out.println("STATUS: Closing client");

        //* TODO Close the client socket
        serviceSocket.close();

        System.out.println("STATUS: closed");
    }
}
