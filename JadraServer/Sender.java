import java.awt.Point;
import java.net.*;
import java.io.*;

public class Sender {
    JadraServer server;
    
    public Sender(JadraServer server) {
        this.server = server;
    }
    
    private void send(String point) {
        for (int i = 0; i < server.connections.size(); i++) {
            server.connections.get(i).writer.println(point);
            server.connections.get(i).writer.flush();
        }
    }
    
    void createReceiver(Client c) {
        Thread rt = new Thread(new Receiver(c));
        rt.start();
    }
    
    class Receiver implements Runnable {
        Client client;
        public Receiver(Client c) {
            client = c;
        }
        
        public void run() {
            String point;
            try {
                while((point = client.reader.readLine()) != null) {
                    send(point);
                }
            } catch(IOException e) {}
        }
    }
}
