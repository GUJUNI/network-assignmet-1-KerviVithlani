// Simple UDP 2
// Write the simple UDP Program for client and server to generate fibonacci series.

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

class UDPServer4 {
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
            int count = Integer.parseInt(request);

            // Generate Fibonacci series
            StringBuilder fibonacciSeries = new StringBuilder();
            int num1 = 0, num2 = 1;
            fibonacciSeries.append(num1).append(" ").append(num2).append(" ");

            for (int i = 2; i < count; i++) {
                int nextNum = num1 + num2;
                fibonacciSeries.append(nextNum).append(" ");
                num1 = num2;
                num2 = nextNum;
            }

            String response = "Fibonacci Series: " + fibonacciSeries.toString();

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
// Fibonacci Series: 0 1 1 2 3 5 8 13 21 34
