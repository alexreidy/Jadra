/* Copyright (C) 2012 Alex Reidy */

import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;

public class JadraGUI implements MouseMotionListener {
    JFrame frame;
    DrawArea drawArea;
    ArrayList<Point> points;
    private int x, y;
    JadraClient client;
    String ip = "127.0.0.1";
    
    public void initializeGUI() {
        points = new ArrayList();
        client = new JadraClient(this, ip);
        
        frame = new JFrame("Jadra");
        frame.setSize(650, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        drawArea = new DrawArea();
        frame.setBackground(new Color(10, 60, 90));
        drawArea.addMouseMotionListener(this);

        Thread pt = new Thread(new Painter());
        Thread rt = new Thread(client);
        pt.start();
        rt.start();
        
        frame.getContentPane().add(drawArea);
        frame.setVisible(true);
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
    
    private void sleep(int time) {
        try {Thread.sleep(time);}
        catch(InterruptedException e) {}
    }
    
    void draw(Point p) {
        points.add(p);
        x = p.x; y = p.y;
        drawArea.repaint();
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        // Add point to ArrayList when mouse is dragged
        points.add(new Point(e.getX(), e.getY()));
        x = e.getX(); y = e.getY(); // Set (x,y) to be painted
        client.send(new Point(x, y));
        drawArea.repaint(); // Render
    }
    
    class Painter implements Runnable {
        // Separate thread to paint everything on a regular basis
        int times = 0;
        public void run() {
            while(true) {
                if (times > 50000) {
                    points.clear();
                    times = 0;
                }
                
                for (int i = 0; i < points.size(); i++) {
                    x = points.get(i).x;
                    y = points.get(i).y;
                    points.remove(i);
                    drawArea.repaint();
                    sleep(2);
                }
                sleep(2);
                times++;
            }
        }
    }
    
    class DrawArea extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillOval(x-3, y-3, 7, 7);
        }
    }
}