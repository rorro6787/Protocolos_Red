import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author <Ivan Iroslavov Petkov>
 */
public class ServerUDP {
    public static String extractText(String text) {
        String result = "";
        int step = Character.getNumericValue(text.charAt(0));
    
        for (int i = 1; i < text.length(); i += step + 1) {
            result += text.charAt(i);
        }
    
        return result;
    }

    public static void main(String[] args)
    {
		// SERVER DATA:
		//* FIXED: as comments if they are provided as command prompt input
        // int port = 54322; // server port
		//* VARIABLES: uncomment them if they are provided as command prompt input
        int port = Integer.parseInt(args[0]); // server port

        // SOCKET
        DatagramSocket server = null;
        Socket a;
        //* TODO Create and initialise the server socket
        try { server = new DatagramSocket(port); }
        catch(IOException e){
            System.out.println("ERROR: Could not listen on port " +  port);
            System.exit(-1);
        }
        System.out.println("WAITING FOR THE CLIENT");
        // MAIN server function
        while (true)
        {
			//* TODO: Create and initialise the EMPTY datagram to recieve the reply (max 400 bytes)
            int buffSize = 800;
            byte [] buffer = new byte [buffSize];
            DatagramPacket receivedDatagram = new DatagramPacket(buffer, buffSize);
			//* TODO: Recieve the replied datagram
            System.out.println("Server trying to receive the datacram");
            try{ server.receive(receivedDatagram); }
            catch(IOException e){
                System.err.println("ERROR: Could not receive datagram with address: " + receivedDatagram.getAddress());
                System.exit(-1);
            }
			//* TODO: Get the recieved text
            String line = null;
            line = new String(receivedDatagram.getData(), receivedDatagram.getOffset(),
                    receivedDatagram.getLength(), StandardCharsets.UTF_8);

			//* TODO: Print on screen the client's socket address (IP and port) and text
           System.out.println("Message received from " + receivedDatagram.getAddress() + " with port " +
                    receivedDatagram.getPort() + " and the message "+ line);
            // We analyse the line
            line = extractText(line);

			//* TODO: Create reply datagram
            //DatagramPacket reply = new DatagramPacket(line.getBytes(), line.getBytes().length);
            DatagramPacket reply = new DatagramPacket(line.getBytes(), line.getBytes().length,
                    receivedDatagram.getAddress(), receivedDatagram.getPort());

			//* TODO: Send reply datagram
            try { server.send(reply); }
            catch(IOException e) {
                System.err.println("ERROR sending the reply");
                System.exit(-1);
            }
        } // Service Loop End
    }

}
