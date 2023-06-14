// Simple TCP 
//Write the simple TCP Program for client and server to calculate length of a given word

import java.io.*;
import java.net.*;

class TCPClient4 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String serverResponse;
            String clientMessage;
            while (true) {
                System.out.print("Enter a word to calculate its length (or 'exit' to quit): ");
                clientMessage = reader.readLine();

                if ("exit".equalsIgnoreCase(clientMessage)) {
                    writer.println(clientMessage);
                    break;
                }

                writer.println(clientMessage);

                serverResponse = serverReader.readLine();
                System.out.println("Length received from server: " + serverResponse);
            }

            reader.close();
            writer.close();
            serverReader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Output:
// Enter a word to calculate its length (or 'exit' to quit): Aeroplane
// Length received from server: 9
