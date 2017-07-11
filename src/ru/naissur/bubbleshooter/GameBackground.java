package ru.naissur.bubbleshooter;

import java.awt.*;

/**
 * naissur
 * 11.07.2017
 */
public class GameBackground {
    // Поля
    private Color color;

    // Конструктор
    public GameBackground() {
        this.color = Color.BLUE;
    }

    // методы
    public void update() {
        // TODO реализовать метод
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
    }
}
