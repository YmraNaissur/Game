package ru.naissur.bubbleshooter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * naissur
 * 11.07.2017
 */
public class GamePanel extends JPanel implements Runnable {

    // Поля
    public static final int WIDTH = 400;    // ширина
    public static final int HEIGHT = 400;   // высота

    private Thread thread = new Thread(this);   // создаем поток

    private BufferedImage image;
    private Graphics2D g;

    public static GameBackground background;  // фон игрового поля
    public static Player player; // игрок

    // Конструктор
    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT)); // установили размеры панели
        setFocusable(true);
        requestFocus();
    }

    public void start() {
        thread.start(); // запускаем поток
    }

    // Методы
    @Override
    public void run() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        background = new GameBackground();  // инициализируем фон
        player = new Player();  // инициализируем игрока

        while (true) { // TODO состояния игры

            gameUpdate();
            gameRender();
            gameDraw();

            try {
                Thread.sleep(33);   // TODO fps
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // обновление игровых компонентов
    public void gameUpdate() {
        // Обновление фона
        background.update();
        // Обновление игрока
        player.update();
    }

    // отрисовка игровых компонентов
    public void gameRender() {
        // Отрисовка фона
        background.draw(g);
        // Отрисовка игрока
        player.draw(g);
    }

    private void gameDraw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }
}