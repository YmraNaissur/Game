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
    private double dx;  // смещение по горизонтали
    private double dy; // смещение по вертикали
    private int r;  // радиус

    private Color color; // цвет

    private double speed; // скорость пули

    // Конструктор
    public Bullet(double x, double y) {
        this.x = x;
        this.y = y;
        r = 2;

        color = Color.WHITE;    // пули будут белыми
        speed = 10; // пуля будет летать побыстрее, чем игрок

        // Вычисляем расстояние от курсора до пули по теореме Пифагора
        double distX = GamePanel.mouseX - x;
        double distY = GamePanel.mouseY - y;
        double dist = Math.sqrt(distX * distX + distY * distY);

        // Вычисляем величины смещения пули по горизонтали и вертикали
        dx = (distX / dist) * speed;
        dy = (distY / dist) * speed;
    }

    // Методы
    public void update() {
        // меняем координаты в соответствии со смещениями по горизонтали и вертикали
        y += dy;
        x += dx;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) x, (int) y, r, 2 * r);
    }

    // если пуля улетела за любую границу экрана, возвращает true
    public boolean isRemoveNeeded() {
        return (y < 0 || y > GamePanel.HEIGHT || x < 0 || x > GamePanel.WIDTH);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }
}
