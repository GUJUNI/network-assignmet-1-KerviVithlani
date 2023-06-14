// Multithreaded UDP 
// Write the Multithreaded UDP Program for client and server to do basic arithmetic operation on two numbers.

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPClient2 {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;

            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            int num1 = 10;
            int num2 = 5;

            // Create the request string
            String request = num1 + "," + num2;

            // Send request to the server
            sendData = request.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

            // Receive response from the server
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Response received from server: " + response);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Output:
// Response received from server: Sum: 15, Difference: 5, Product: 50, Quotient: 2
