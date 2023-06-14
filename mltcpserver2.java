// Multithreaded TCP 2
// Write the Multithreaded TCP Program for client and server in java to reverse a string.

import java.io.*;
import java.net.*;

class TCPServer2 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Listening on port 12345...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Create a new thread to handle client requests
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String clientMessage;
            while ((clientMessage = reader.readLine()) != null) {
                System.out.println("Received message from client: " + clientMessage);

                // Reverse the client message
                String reversed = reverseString(clientMessage);

                // Send response back to the client
                writer.println(reversed);
                System.out.println("Sent response to client: " + reversed);
            }

            reader.close();
            writer.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String reverseString(String message) {
        return new StringBuilder(message).reverse().toString();
    }
}

// Output:
// Server started. Listening on port 12345...
// Client connected: 127.0.0.1
// Received message from client: Kervi
// Sent response to client: ivreK
