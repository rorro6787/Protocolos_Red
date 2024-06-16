import java.io.*;
import java.net.*;
import java.util.Scanner;

class ServerTCP {
    public static String extract_text(String s){
        String res = "";
        int step = Character.getNumericValue(s.charAt(0));
        for(int i = 1; i < s.length(); i += step + 1)
            res += s.charAt(i);
        return res;
    }

    public static void main(String[] args) throws IOException {
    
        // SERVER DATA
        //* FIXED: If read from command line must be commented out
        //int port = 12345; // server port
        //* VARIABLE: If read from command line it must be uncommented
        // String serverName = args[0];
        int port = Integer.parseInt(args[0]);

        // SOCKETS
        ServerSocket server = null; // Passive (receiving requests)
        Socket client = null; // Active (client service)

        // FLOWS FOR SENDING AND RECEIVING
        BufferedReader in = null;
        PrintWriter out = null;

        //* TODO: Create and initialise server socket (passive socket)
        try{
            server = new ServerSocket(port, 1);
        }catch(IOException e){
            System.out.println("Error, could not listen on the port: "+server);
            System.exit(-1);
        }


        while (true) // Loop to receive incoming connections
        {
            //* TODO: Waiting for incoming connections
            System.out.println("Status: Waiting for clients");
            try {
                client = server.accept();
            }catch(IOException e){
                System.out.println("Accept failed: "+port);
                System.exit(-1);
            }

            //* TODO: Once a connection is accepted, initialise input/output streams of the connected socket

            try{
                out = new PrintWriter(client.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader((client.getInputStream())));
            }catch(IOException e){
                System.err.println("Could not get IO for "+client.getRemoteSocketAddress());
                System.exit(-1);
            }
            System.out.println("STATUS: Client connected from: "+client.getRemoteSocketAddress());



            boolean exit = false;
            while(!exit) // Start loop of a client service
            {
                try{
                    //* TODO: Receive inline text sent by the client via the input stream of the connected socket
                    String line;
                    line = in.readLine();

                    //* TODO: Check if it is the end of connection - REPLACE WITH 'END' STATEMENT
                    if (line.compareTo("FINISH") != 0){
                        line = extract_text(line);

                        out.println(line);
                        System.out.println("Status: Sending to client "+line);
                        //* TODO: Send text to the client via the outgoing stream of the connected socket
                    } else { // Client wants to close connection, has sent END
                        exit = true;
                        System.out.println("OK");
                    }
                } catch(IOException e) {
                    System.out.println("ERROR: Received/sent: "+e.getMessage());
                }

            } // end of service

            //* TODO: Close flows and socket
            in.close();
            out.close();
            client.close();

        } // end of loop
    } // end of method
}