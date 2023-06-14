//Multithreaded UDP
//Write the Multithreaded UDP Program for client and server to print system time

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;

            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            String request = "TimeRequest";

            // Send request to the server
            sendData = request.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

            // Receive response from the server
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("System time received from server: " + response);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Output:
// System time received from server: 2023-06-13 23:51:26
