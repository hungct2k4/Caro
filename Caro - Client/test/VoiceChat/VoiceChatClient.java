package VoiceChat;

import java.io.*;
import java.net.*;
import javax.sound.sampled.*;

public class VoiceChatClient {
    private static final int PORT = 5000;
    private static final String SERVER_ADDRESS = "localhost";

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT)) {
            System.out.println("Connected to the server");

            // Start the thread to receive audio from the server
            new Thread(new AudioReceiver(socket)).start();

            // Start sending audio to the server
            captureAndSendAudio(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void captureAndSendAudio(OutputStream outputStream) {
        try {
            // Set audio format
            AudioFormat format = new AudioFormat(44100.0f, 16, 2, true, false);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            TargetDataLine microphone = (TargetDataLine) AudioSystem.getLine(info);
            microphone.open(format);
            microphone.start();
            

            byte[] buffer = new byte[4096];
            int bytesRead;

            while (true) {
                bytesRead = microphone.read(buffer, 0, buffer.length);
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    static class AudioReceiver implements Runnable {
        private Socket socket;

        public AudioReceiver(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (InputStream inputStream = socket.getInputStream()) {
                playReceivedAudio(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void playReceivedAudio(InputStream inputStream) {
            try {
                // Set audio format
                AudioFormat format = new AudioFormat(44100.0f, 16, 2, true, false);
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                SourceDataLine speakers = (SourceDataLine) AudioSystem.getLine(info);
                speakers.open(format);
                speakers.start();

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    speakers.write(buffer, 0, bytesRead);
                }
            } catch (LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
