import java.net.*;
import java.io.*;

public class Client {
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    
    public Client(Socket s) {
        socket = s;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
        } catch(IOException e) {}
    }

}
