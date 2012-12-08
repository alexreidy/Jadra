/* Copyright (C) 2012 Alex Reidy */

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class JadraSettings {
    JFrame frame;
    JTextField ipBox;
    Jadra app;
    
    public JadraSettings(Jadra app) {
        this.app = app;
    }
    
    public void initializeGUI() {
        frame = new JFrame("Jadra Settings");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 80);
        frame.setResizable(false);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(3, 60, 90));
        
        JLabel ipLabel = new JLabel("IP address: ");
        ipLabel.setForeground(Color.WHITE);
        
        ipBox = new JTextField(12);
        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(new ContinueButtonListener());
        panel.add(ipLabel); panel.add(ipBox); panel.add(continueButton);
        
        frame.add(panel);
        frame.setVisible(true);
    }
    
    class ContinueButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            frame.setVisible(false);
            app.startJadra(ipBox.getText());
        }
    }
}
