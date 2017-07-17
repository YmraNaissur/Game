package ru.naissur.bubbleshooter;

import java.awt.*;

/**
 * naissur
 * 14.07.2017
 */
public class Enemy {
    // Поля
    private double x;   // координата x
    private double y;   // координата y
    private double dx;  // коэффициент смещения по горизонтали
    private double dy;  // коэффициент смещения по вертикали
    private double speed;  // скорость
    private Color color;    // цвет
    private int r;  // радиус

    private int type;   // тип врага
    private int rank;   // ранг врага
    private double health;  // здоровье врага

    // Конструктор
    public Enemy(int type, int rank) {
        this.type = type;
        this.rank = rank;

        switch (type) {
            case 1:
                color = Color.GREEN;
                switch (rank) {
                    case 1:
                        r = 7;
                        x = Math.random() * GamePanel.WIDTH;
                        y = 0; // враг вылетает сверху экрана
                        speed = 2;
                        health = 10;
                        double angle = Math.toRadians(Math.random()*360); // случайный угол
                        dx = Math.sin(angle) * speed;
                        dy = Math.cos(angle) * speed;
                        break;
                }
                break;
            case 2:

                break;
        }
    }

    // Методы

    // Проверяем, есть ли здоровье, и если нет, удаляем врага из списка
    public boolean isRemoveNeeded() {
        if (health <= 0) {
            return true;
        }
        return false;
    }

    public void hit() {
        health--;
    }

    public void update() {
        x += dx;
        y += dy;

        // обрабатываем выход за пределы экрана - меняем смещение на противоположное
        if (x < 0 && dx < 0) dx = -dx;
        if (x > GamePanel.WIDTH && dx > 0) dx = -dx;
        if (y < 0 && dy < 0) dy = -dy;
        if (y > GamePanel.HEIGHT && dy > 0) dy = -dy;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) x - r, (int) y - r, 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));
        g.setColor(color.darker());
        g.drawOval((int) x - r, (int) y - r, 2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));
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
