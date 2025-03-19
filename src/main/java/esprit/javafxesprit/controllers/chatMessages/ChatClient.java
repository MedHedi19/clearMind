package esprit.javafxesprit.controllers.chatMessages;
    import java.io.*;
    import java.net.*;


    public class ChatClient {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ChatClient(String serverAddress, int port) throws IOException {
            socket = new Socket(serverAddress, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        public void sendMessage(String message) {
            out.println(message);
        }

        public String receiveMessage() throws IOException {
            return in.readLine();
        }

        public void close() throws IOException {
            socket.close();
        }
    }

/**
 * version using ip
 * */
/**
package esprit.javafxesprit.controllers.chatMessages;

import java.io.*;
import java.net.*;

public class ChatClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ChatClient(String serverIP, int port) throws IOException {
        socket = new Socket(serverIP, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("Connected to server at " + serverIP + ":" + port);

        new Thread(this::listenForMessages).start(); // Start listening for messages
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    private void listenForMessages() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Server: " + message);
            }
        } catch (IOException e) {
            System.out.println("Connection lost.");
        }
    }

    public void close() throws IOException {
        socket.close();
    }

    public static void main(String[] args) {
        try {
            String serverIP = "192.168.1.100"; // Change this to your server's actual IP
            int port = 12345;
            ChatClient client = new ChatClient(serverIP, port);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while ((message = userInput.readLine()) != null) {
                client.sendMessage(message);
            }

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/
