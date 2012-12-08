/* Copyright (C) 2012 Alex Reidy */

public class Jadra {
    JadraGUI ui;
    JadraSettings settings;
    String[] args;
    
    public static void main(String[] args) {
        Jadra jadra = new Jadra();
        jadra.go(args);
    }
    
    private void go(String[] args) {
        ui = new JadraGUI();
        
        if (args.length == 2) {
            if (args[0].equals("ip"))
                ui.ip = args[1];
        }
        
        settings = new JadraSettings(this);
        settings.initializeGUI();
    }
    
    public void startJadra(String ip) {
        ui.ip = ip;
        ui.initializeGUI();
    }
}
