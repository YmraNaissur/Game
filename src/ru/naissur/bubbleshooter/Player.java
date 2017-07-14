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

    private double dx;  // коэффициент смещения по x
    private double dy;  // коэффициент смещения по y

    private Color color01;  // первый цвет
    private Color color02;  // второй цвет

    private int speed; // скорость

    public static boolean up;
    public static boolean down;
    public static boolean left;
    public static boolean right;
    public static boolean isFiring; // стреляет ли игрок

    // Конструктор
    public Player() {
        // игрок изначально будет в центре игрового поля
        x = GamePanel.WIDTH / 2;
        y = GamePanel.HEIGHT / 2;

        r = 5;
        dx = 0;
        dy = 0;

        speed = 5;

        color01 = Color.WHITE;
        color02 = Color.RED;

        // ни одна кнопка пока не нажата, все переменные направления и стрельбы равны false
        up = false;
        down = false;
        left = false;
        right = false;
        isFiring = false;
    }

    // Методы
    public void update() {
        if (isFiring) {
            GamePanel.bullets.add(new Bullet(x, y));
        }

        if (up && (y > r)) {
            dy = -speed;    // при движении вверх нужно вычесть коэффициент смещения из координаты y
        }
        if (down && (y < GamePanel.HEIGHT - r)) {
            dy = speed;     // при движении вниз нужно прибавить коэффициент смещения к координате y
        }
        if (left && (x > r)) {
            dx = -speed;    // при движении влево нужно вычесть коэффициент смещения из координаты x
        }
        if (right && (x < GamePanel.WIDTH - r)) {
            dx = speed; // при движении вправо нужно прибавить коэффициент смещения к координате x
        }

        // если движемся по диагонали, коэффициенты смещения рассчитываются немного по-другому
        // иначе скорость по диагонали будет больше, чем скорость по горизонтали/вертикали
        if (up && left || up && right || down && left || down && right) {
            double angle = Math.toRadians(45); // переведем 45 градусов в радианы
            dy = dy * Math.sin(angle);
            dx = dx * Math.cos(angle);
        }

        // обновляем координаты
        y += dy;
        x += dx;

        // обнуляем коэффициенты смещения, чтобы игрок останавливался,
        // если мы ничего не нажимаем, и не набирал скорость
        dy = 0;
        dx = 0;
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
