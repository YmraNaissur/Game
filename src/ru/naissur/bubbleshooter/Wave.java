package ru.naissur.bubbleshooter;

import java.awt.*;

/**
 * naissur
 * 18.07.2017
 */
public class Wave {
    // Поля
    private int waveNumber; // номер волны
    private int waveMultiplier; // коэффициент количества врагов
    private String waveText;    // текст, отображающийся перед началом волны

    // Следующие три поля нужны для создания эффекта паузы между волнами
    private long waveTimer;
    private long waveDelay; // промежуток между волнами
    private long waveTimerDiff;

    // Конструктор
    public Wave() {
        waveNumber = 1; // Начинаем, естественно, с первой волны
        waveMultiplier = 5; // Количество врагов будет 5 * waveNumber

        // Инициализируем таймеры для эффекта паузы между волнами
        waveTimer = 0;
        waveDelay = 5000; // 5 сек
        waveTimerDiff = 0;

        waveText = "W A V E - ";
    }

    // Методы

    // Создание врагов на игровом поле
    public void createEnemies() {
        int enemyCount = waveMultiplier * waveNumber;   // Кол-во врагов в волне
        if (waveNumber < 4) {   // Врагов первого типа и ранга будем создавать первые 3 волны
            while (enemyCount > 0) {
                int type = 1;
                int rank = 1;
                GamePanel.enemies.add(new Enemy(type, rank));
                enemyCount -= type * rank;  // Чем круче будут враги, тем меньше их будет появляться
            }
        }
        // Нужно увеличить номер волны
        waveNumber++;
    }

    public void update() {
        // если список врагов на игровой панели пуст и игровой таймер равен нулю, запускаем следующую волну
        if(GamePanel.enemies.isEmpty() && waveTimer == 0) {
            waveTimer = System.nanoTime();
        }

        // Ждем в течение времени waveDelay, прежде чем создать новых врагов
        if (waveTimer > 0) {
            waveTimerDiff += (System.nanoTime() - waveTimer) / 1_000_000;
            waveTimer = System.nanoTime();
        }

        if (waveTimerDiff > waveDelay) {
            createEnemies();    // Вызовом этого метода мы создаем врагов
            waveTimer = 0;
            waveTimerDiff = 0;
        }
    }

    // нужно ли показывать текст с номером волны
    public boolean isWaveTextToBeShowed() {
        return waveTimer != 0;
    }

    public void draw(Graphics2D g) {
        // Надпись будет плавно появляться и затем плавно исчезать
        // Для этого немного поработаем с альфа-каналом
        double divider = waveDelay / 180;
        double alpha = waveTimerDiff / divider;
        alpha = 255 * Math.sin(Math.toRadians(alpha));

        if (alpha < 0) alpha = 0;
        if (alpha > 255) alpha = 255;

        // Установим шрифт текста
        g.setFont(new Font("consolas", Font.PLAIN, 20));
        String s = waveText + waveNumber;   // Результирующая надпись с номером волны
        // Узнаем ширину надписи в пикселях (понадобится для центровки)
        long textLength = (long)g.getFontMetrics().getStringBounds(s, g).getWidth();

        // Устанавливаем цвет и значение альфа-канала
        g.setColor(new Color(255, 255, 255, (int)alpha));
        // Отображаем текст, центруем его по центру экрана
        g.drawString(s, GamePanel.WIDTH/2 - (int)textLength / 2, GamePanel.HEIGHT/2);
    }
}
