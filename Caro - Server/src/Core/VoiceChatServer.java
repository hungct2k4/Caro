package Core;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.sound.sampled.*;

public class VoiceChatServer {
    private static final int PORT = 5000;
    private static List<ClientHandler> clients = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Server is running on port " + PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public VoiceChatServer() {
          System.out.println("Server is running on port " + PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
            

    public static void broadcast(byte[] audioData, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendAudio(audioData);
            }
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private OutputStream outputStream;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (InputStream inputStream = socket.getInputStream()) {
                outputStream = socket.getOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    broadcast(buffer, this);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendAudio(byte[] audioData) {
            try {
                outputStream.write(audioData);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
