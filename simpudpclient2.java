// Simple UDP 2
// Write the simple UDP Program for client and server to generate fibonacci series.

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPClient4 {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;

            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            int count = 10;

            // Create the request string
            String request = String.valueOf(count);

            // Send request to the server
            sendData = request.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

            // Receive response from the server
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Response received from server:\n" + response);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Output:
// Response received from server:
// Fibonacci Series: 0 1 1 2 3 5 8 13 21 34
