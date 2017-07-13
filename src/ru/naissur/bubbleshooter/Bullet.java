package ru.naissur.bubbleshooter;

import java.awt.*;

/**
 * naissur
 * 13.07.2017
 */
public class Bullet {
    // Поля
    private double x;   // положение по горизонтали
    private double y;   // положение по вертикали
    private int r;  // радиус

    private Color color; // цвет

    private int speed; // скорость пули

    // Конструктор
    public Bullet(double x, double y) {
        this.x = x;
        this.y = y;
        r = 2;

        color = Color.WHITE;    // пули будут белыми
        speed = 10; // пуля будет летать побыстрее, чем игрок
    }

    // Методы
    public void update() {
        y -= speed; // пуля летит вверх
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) x, (int) y, r, 2 * r);
    }
}
