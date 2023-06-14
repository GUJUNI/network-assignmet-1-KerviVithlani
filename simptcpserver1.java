// Simple TCP 
// Write the simple TCP Program for client and server to calculate factorial in java

import java.io.*;
import java.net.*;

class TCPServer3 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Listening on port 12345...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                String clientMessage = reader.readLine();
                System.out.println("Received number from client: " + clientMessage);

                int number = Integer.parseInt(clientMessage);

                // Calculate the factorial
                long factorial = calculateFactorial(number);

                // Send response back to the client
                writer.println(factorial);
                System.out.println("Sent factorial to client: " + factorial);

                reader.close();
                writer.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long calculateFactorial(int number) {
        long factorial = 1;
        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }
        return factorial;
    }
}

// Output:
// Server started. Listening on port 12345...
// Client connected: 127.0.0.1
// Received number from client: 5
// Sent factorial to client: 120
