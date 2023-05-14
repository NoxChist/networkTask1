package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8087;

    public static void run() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    final String name = in.readLine();
                    System.out.printf("New connection accepted. Port: %d\n", clientSocket.getPort());

                    out.printf("Hi %s, your port is %d\n", name, clientSocket.getPort());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
