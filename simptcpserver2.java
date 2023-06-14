// Simple TCP 
//Write the simple TCP Program for client and server to calculate length of a given word

import java.io.*;
import java.net.*;

class TCPServer4 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Listening on port 12345...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Handle client request
                handleClientRequest(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClientRequest(Socket clientSocket) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String clientMessage = reader.readLine();
            System.out.println("Received message from client: " + clientMessage);

            int wordLength = clientMessage.length();

            // Send response back to the client
            writer.println(wordLength);
            System.out.println("Sent response to client: " + wordLength);

            reader.close();
            writer.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Output:
// erver started. Listening on port 12345...
// Client connected: 127.0.0.1
// Received message from client: Aeroplane
// Sent response to client: 9
