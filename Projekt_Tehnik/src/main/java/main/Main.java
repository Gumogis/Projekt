package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
            System.out.println("Hello World!");
        SwingUtilities.invokeLater(() ->{
            try {
                new MainFrame();
            } catch (Exception e) {
                System.out.println("GUI ERROR: " + e);
            }
        });
    }
}