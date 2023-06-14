// Multithreaded TCP 1:
// Write the Multithreaded TCP Program for client and serve to print prime numbers.


import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String serverResponse;
            String clientMessage;
            while (true) {
                System.out.print("Enter a number to find prime numbers up to that number (or 'exit' to quit): ");
                clientMessage = reader.readLine();

                if ("exit".equalsIgnoreCase(clientMessage)) {
                    writer.println(clientMessage);
                    break;
                }

                writer.println(clientMessage);

                serverResponse = serverReader.readLine();
                System.out.println("Prime numbers received from server: " + serverResponse);
            }

            reader.close();
            writer.close();
            serverReader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

Output:
Enter a number to find prime numbers up to that number (or 'exit' to quit): 20
Prime numbers received from server: 2 3 5 7 11 13 17 19
Enter a number to find prime numbers up to that number (or 'exit' to quit): 30
Prime numbers received from server: 2 3 5 7 11 13 17 19 23 29
Enter a number to find prime numbers up to that number (or 'exit' to quit):
