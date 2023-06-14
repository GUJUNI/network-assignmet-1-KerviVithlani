// Simple TCP 
// Write the simple TCP Program for client and server to calculate factorial in java

import java.io.*;
import java.net.*;

class TCPClient3 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                System.out.print("Enter a number to calculate factorial (or 'exit' to quit): ");
                String clientMessage = reader.readLine();

                if ("exit".equalsIgnoreCase(clientMessage)) {
                    writer.println(clientMessage);
                    break;
                }

                writer.println(clientMessage);

                String serverResponse = serverReader.readLine();
                System.out.println("Factorial received from server: " + serverResponse);
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
// Enter a number to calculate factorial (or 'exit' to quit): 5
// Factorial received from server: 120
