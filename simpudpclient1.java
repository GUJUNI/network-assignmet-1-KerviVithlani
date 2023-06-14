// Simple UDP
// Write the simple UDP Program for client and server to find odd and even numbers 

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPClient3 {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;

            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            int start = 20;

            // Create the request string
            String request = String.valueOf(start);

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
// Odd Numbers: 19 17 15 13 11 9 7 5 3 1
// Even Numbers: 20 18 16 14 12 10 8 6 4 2 0
