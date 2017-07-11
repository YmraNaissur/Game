package ru.naissur.bubbleshooter;

import javax.swing.*;
import java.awt.*;

/**
 * naissur
 * 11.07.2017
 */
public class GamePanel extends JPanel {

    // Игровое поле
    public static final int WIDTH = 400;    // ширина
    public static final int HEIGHT = 400;   // высота

    // Конструктор
    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT)); // установили размеры панели
        setFocusable(true);
        requestFocus();
    }
}