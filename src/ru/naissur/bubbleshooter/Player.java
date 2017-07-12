package ru.naissur.bubbleshooter;

import java.awt.*;

/**
 * naissur
 * 12.07.2017
 */
public class Player {
    // Поля
    private double x;   // координата x
    private double y;   // координата y
    private int r;  // радиус
    private Color color01;  // первый цвет
    private Color color02;  // второй цвет

    // Конструктор
    public Player() {
        // игрок изначально будет в центре игрового поля
        x = GamePanel.WIDTH / 2;
        y = GamePanel.HEIGHT / 2;

        r = 5;

        color01 = Color.WHITE;
        color02 = Color.RED;
    }

    // Методы
    public void update() {
        // TODO реализовать обновление данных
    }

    // Отрисовка игрока
    public void draw(Graphics2D g) {
        g.setColor(color01);
        g.fillOval((int) x - r, (int) y - r, 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));
        g.setColor(color01.darker());
        g.drawOval((int) x - r, (int) y - r, 2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));
    }
}
