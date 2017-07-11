package ru.naissur.bubbleshooter;

import javax.swing.*;

/**
 * naissur
 * 11.07.2017
 */
public class GameStart {
    public static void main(String[] args) {
        GamePanel panel = new GamePanel();

        JFrame startFrame = new JFrame("Bubble shooter");
        startFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        startFrame.setContentPane(panel);
        startFrame.pack();
        startFrame.setLocationRelativeTo(null); // т.к. в аргументе null, то фрейм будет по центру
        startFrame.setVisible(true);
        panel.start();
    }
}