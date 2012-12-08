/* Copyright (C) 2012 Alex Reidy */

import java.net.*;
import java.awt.Point;
import java.io.*;

public class JadraClient implements Runnable {
    JadraGUI ui;
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    
    public JadraClient(JadraGUI ui, String ip) {
        this.ui = ui;
        try {
            socket = new Socket(ip, 5000);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
        } catch(IOException e) { System.out.println("Unable to connect to server."); }
    }
    
    void send(Point p) {
        writer.println(p.x + "," + p.y);
        writer.flush();
    }
    
    @Override
    public void run() {
        String sPoint;
        try {
            while((sPoint = reader.readLine()) != null) {
                String[] s = sPoint.split(",");
                ui.draw(new Point(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
            }
        } catch(IOException e) {}
    }
    
}
