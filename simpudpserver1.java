// Simple UDP
// Write the simple UDP Program for client and server to find odd and even numbers 

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

class UDPServer3 {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(12345);
            System.out.println("Server started. Listening on port 12345...");

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Receive client request
            serverSocket.receive(receivePacket);
            System.out.println("Client connected: " + receivePacket.getAddress().getHostAddress());

            String request = new String(receivePacket.getData(), 0, receivePacket.getLength());

            // Parse the received request
            int start = Integer.parseInt(request);

            // Find odd and even numbers
            StringBuilder oddNumbers = new StringBuilder();
            StringBuilder evenNumbers = new StringBuilder();

            for (int i = start; i >= 0; i--) {
                if (i % 2 == 0) {
                    evenNumbers.append(i).append(" ");
                } else {
                    oddNumbers.append(i).append(" ");
                }
            }

            String response = "Odd Numbers: " + oddNumbers.toString() + "\nEven Numbers: " + evenNumbers.toString();

            // Send response back to the client
            byte[] sendData = response.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
            serverSocket.send(sendPacket);

            System.out.println("Sent response to client:\n" + response);

            serverSocket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Output:
// Server started. Listening on port 12345...
// Client connected: 127.0.0.1
// Sent response to client:
// Odd Numbers: 19 17 15 13 11 9 7 5 3 1
// Even Numbers: 20 18 16 14 12 10 8 6 4 2 0
