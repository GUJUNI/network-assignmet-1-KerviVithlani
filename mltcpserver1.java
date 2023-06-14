// Multithreaded TCP 1 :
// Write the Multithreaded TCP Program for client and serve to print prime numbers.

import java.io.*;
import java.net.*;

class TCPServer {
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

                int number = Integer.parseInt(clientMessage);
                String primes = findPrimes(number);

                // Send response back to the client
                writer.println(primes);
                System.out.println("Sent response to client: " + primes);
            }

            reader.close();
            writer.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String findPrimes(int number) {
        StringBuilder primesBuilder = new StringBuilder();

        for (int i = 2; i <= number; i++) {
            if (isPrime(i)) {
                primesBuilder.append(i).append(" ");
            }
        }

        return primesBuilder.toString().trim();
    }

    private boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}

// Output:
// Server started. Listening on port 12345...
// Client connected: 127.0.0.1
// Received message from client: 20
// Sent response to client: 2 3 5 7 11 13 17 19
// Received message from client: 30
// Sent response to client: 2 3 5 7 11 13 17 19 23 29
