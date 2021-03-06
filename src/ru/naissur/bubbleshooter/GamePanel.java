package ru.naissur.bubbleshooter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * naissur
 * 11.07.2017
 */
public class GamePanel extends JPanel implements Runnable {
    // Поля
    public static final int WIDTH = 600;    // ширина
    public static final int HEIGHT = 600;   // высота

    public static int mouseX;   // x-координата указателя мыши
    public static int mouseY;   // y-координата указателя мыши
    public static boolean isLeftMousePressed;   // нажата ли левая клавиша мыши

    private Thread thread = new Thread(this);   // создаем поток

    private BufferedImage image;
    private Graphics2D g;

    private int fps; // количество кадров в секунду
    private double millisPerFrame; // количество миллисекунд на один кадр
    private long fpsTimer;
    private int sleepTime;  // результирующее время для Thread.sleep()

    // состояния игры
    public static enum STATES {
        MENU,
        PLAY
    }

    public static STATES state = STATES.MENU; // по умолчанию находимся в меню

    public static GameBackground background;  // фон игрового поля
    public static Player player; // игрок
    public static ArrayList<Bullet> bullets;    // список с пулями
    public static ArrayList<Enemy> enemies; // список с врагами
    public static Wave wave;    // волна врагов
    public static Menu menu;    // игровое меню

    // Конструктор
    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT)); // установили размеры панели
        setFocusable(true);
        requestFocus();
        addKeyListener(new Listeners()); // добавляем слушателя клавиатуры
        addMouseMotionListener(new Listeners());    // добавляем слушателя движения мыши
        addMouseListener(new Listeners());  // добавляем слушателя мыши

        // Создаем свой курсор-прицел
        Toolkit kit = Toolkit.getDefaultToolkit();
        BufferedImage buffered = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = (Graphics2D) buffered.getGraphics();
        g3.setColor(Color.GRAY);
        g3.drawOval(0, 0, 4, 4);
        g3.drawLine(2, 0, 2, 4);
        g3.drawLine(0, 2, 4, 2);
        Cursor cursor = kit.createCustomCursor(buffered, new Point(3, 3), "Aim");
        g3.dispose();

        // Устанавливаем созданный ранее курсор
        this.setCursor(cursor);
    }

    public void start() {
        thread.start(); // запускаем поток
    }

    // Методы
    @Override
    public void run() {

        fps = 30;   // указываем желаемое значение FPS
        millisPerFrame = 1000 / fps;    // сколько миллисекунд должно уходить на один кадр с указанным FPS
        sleepTime = 0;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        isLeftMousePressed = false; // по умолчанию левая кнопка мыши не нажата
        background = new GameBackground();  // инициализируем фон
        player = new Player();  // инициализируем игрока
        bullets = new ArrayList<>();    // инициализируем список с пулями
        enemies = new ArrayList<>();    // инициализируем список с врагами
        wave = new Wave();  // инициализируем объект, отвечающий за волны врагов
        menu = new Menu();  // инициализируем игровое меню

        while (true) {
            // инициализируем таймер PFS текущим временем
            fpsTimer = System.currentTimeMillis();

            // Если приложение находится в состоянии меню
            if (state == STATES.MENU) {
                background.update();    // обновление фона
                background.draw(g);     // отрисовка фона
                menu.update();
                menu.draw(g);   // отрисовка меню
                gameDraw();
            }

            // Если приложение находится в состоянии игры
            if (state == STATES.PLAY) {
                gameUpdate();
                gameRender();
                gameDraw();
            }

            /*
                Следующая строка и условный оператор нужны для того, чтобы получать
                примерно одинаковое время смены кадров независимо от того, как долго выполняется цикл
            */
            fpsTimer = (System.currentTimeMillis() - fpsTimer);

            if (millisPerFrame > fpsTimer) {
                sleepTime = (int) (millisPerFrame - fpsTimer);
            } else {
                sleepTime = 1;
            }

            try {
                Thread.sleep(sleepTime);

                // Чтобы мониторить FPS, раскомментить следующую строку
                // System.out.println(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            fpsTimer = 0;
            sleepTime = 1;
        }
    }

    // обновление игровых компонентов
    public void gameUpdate() {
        // Обновление фона
        background.update();

        // Обновление игрока
        player.update();

        // Обновление пуль
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            b.update();
            // если пуля вышла за границы экрана, удаляем ее
            if (b.isRemoveNeeded()) {
                bullets.remove(i);
                i--;
            }
        }

        // Обновление врагов
        for (Enemy e : enemies) {
            e.update();
        }

        // Анализ столкновений врагов и пуль
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            double ex = e.getX();   // получаем координату х врага
            double ey = e.getY();   // получаем координату y врага
            for (int j = 0; j < bullets.size(); j++) {
                Bullet b = bullets.get(j);
                double bx = b.getX();   // получаем координату х пули
                double by = b.getY();   // получаем координату y пули

                double dx = ex - bx;
                double dy = ey - by;
                double dist = Math.sqrt(dx * dx + dy * dy);

                // если дистанция меньше радиуса врага + радиус пули, то удаляем врага из списка
                if ((int)dist <= e.getR() + b.getR()) {
                    e.hit();
                    bullets.remove(j);
                    j--;

                    // если здоровье у врага закончилось, удаляем его из списка
                    if (e.isRemoveNeeded()) {
                        enemies.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }

        // Анализ столкновения игрока и врага
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            double ex = e.getX();   // получаем координату x врага
            double ey = e.getY();   // получаем координатy y врага
            double px = player.getX();  // получаем координату x игрока
            double py = player.getY();  // получаем координату y игрока
            double dx = ex - px;
            double dy = ey - py;
            double dist = Math.sqrt(dx * dx + dy * dy);

            // если дистанция меньше радиуса врага + радиус игрока, то столкновение
            if ((int)dist <= e.getR() + player.getR()) {
                e.hit();
                player.hit();

                if (e.isRemoveNeeded()) {
                    enemies.remove(i);
                    i--;
                }
            }
        }

        // Обновление волны
        wave.update();
    }

    // отрисовка игровых компонентов
    public void gameRender() {
        // Отрисовка фона
        background.draw(g);

        // Отрисовка игрока
        player.draw(g);

        // Отрисовка пуль
        for (Bullet b : bullets) {
            b.draw(g);
        }

        // Отрисовка врагов
        for (Enemy e : enemies) {
            e.draw(g);
        }

        // Отрисовка волны
        if (wave.isWaveTextToBeShowed()) {
            wave.draw(g);
        }
    }

    private void gameDraw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }
}