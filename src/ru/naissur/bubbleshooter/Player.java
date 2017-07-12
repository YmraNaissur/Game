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

    private int speed; // скорость

    public static boolean up;
    public static boolean down;
    public static boolean left;
    public static boolean right;

    // Конструктор
    public Player() {
        // игрок изначально будет в центре игрового поля
        x = GamePanel.WIDTH / 2;
        y = GamePanel.HEIGHT / 2;

        r = 5;
        speed = 5;

        color01 = Color.WHITE;
        color02 = Color.RED;

        // ни одна кнопка пока не нажата, все переменные направления равны false
        up = false;
        down = false;
        left = false;
        right = false;
    }

    // Методы
    public void update() {
        if (up && (y > r)) {
            y -= speed;
        }
        if (down && (y < GamePanel.HEIGHT - r)) {
            y += speed;
        }
        if (left && (x > r)) {
            x -= speed;
        }
        if (right && (x < GamePanel.WIDTH - r)) {
            x += speed;
        }
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
