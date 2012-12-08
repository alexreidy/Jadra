import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class JadraServer {
    ArrayList<Client> connections;
    ServerSocket sSocket;
    Sender sender;

    public static void main(String[] args) {
        JadraServer server = new JadraServer();
        server.go();
    }
    
    private void go() {
        connections = new ArrayList();
        // Create new Sender and pass in the current instance
        sender = new Sender(this);
        try {
            sSocket = new ServerSocket(5000);
            System.out.println("\nWelcome to the Jadra server.\n");
            
            while(true) { // Listen for connections and save them
                Socket cSocket = sSocket.accept();
                Client c = new Client(cSocket);
                connections.add(c);
                sender.createReceiver(c); // Create a separate thread to receive data
                System.out.println("Received a connection.");
            }
        } catch(IOException e) {}
    }
}