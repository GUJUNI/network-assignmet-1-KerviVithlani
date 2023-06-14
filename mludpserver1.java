//Multithreaded UDP
//Write the Multithreaded UDP Program for client and server to print system time

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

class UDPServer {
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
            String response = getSystemTime();

            // Send response back to the client
            sendData = response.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
            serverSocket.send(sendPacket);

            System.out.println("Sent response to client: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getSystemTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}

// Output:
// Server started. Listening on port 12345...
// Client connected: 127.0.0.1
// Sent response to client: 2023-06-13 23:51:26
