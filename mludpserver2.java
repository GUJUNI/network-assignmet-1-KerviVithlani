// Multithreaded UDP 
// Write the Multithreaded UDP Program for client and server to do basic arithmetic operation on two numbers.

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

class UDPServer2 {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(12345);
            System.out.println("Server started. Listening on port 12345...");

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Receive client request
                serverSocket.receive(receivePacket);
                System.out.println("Client connected: " + receivePacket.getAddress().getHostAddress());

                // Create a new thread to handle client request
                ClientHandler clientHandler = new ClientHandler(serverSocket, receivePacket);
                clientHandler.start();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private DatagramSocket serverSocket;
    private DatagramPacket receivePacket;

    public ClientHandler(DatagramSocket serverSocket, DatagramPacket receivePacket) {
        this.serverSocket = serverSocket;
        this.receivePacket = receivePacket;
    }

    public void run() {
        try {
            byte[] sendData;
            String request = new String(receivePacket.getData(), 0, receivePacket.getLength());

            // Parse the received request
            String[] elements = request.split(",");
            int num1 = Integer.parseInt(elements[0]);
            int num2 = Integer.parseInt(elements[1]);

            // Perform arithmetic operation
            int sum = num1 + num2;
            int difference = num1 - num2;
            int product = num1 * num2;
            int quotient = num1 / num2;

            // Create the response string
            String response = "Sum: " + sum + ", Difference: " + difference + ", Product: " + product + ", Quotient: " + quotient;

            // Send response back to the client
            sendData = response.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
            serverSocket.send(sendPacket);

            System.out.println("Sent response to client: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Output:
// Server started. Listening on port 12345...
// Client connected: 127.0.0.1
// Sent response to client: Sum: 15, Difference: 5, Product: 50, Quotient: 2
