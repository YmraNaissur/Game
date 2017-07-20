package ru.naissur.bubbleshooter;

import java.awt.*;

/**
 * naissur
 * 20.07.2017
 */
public class Menu {
    // Поля
    private int buttonWidth;
    private int buttonHeight;
    private Color buttonColor;
    private String buttonText;

    // Конструктор
    public Menu() {
        buttonWidth = 120;
        buttonHeight = 60;
        buttonColor = Color.ORANGE;
        buttonText = "Play";
    }

    // Методы
    public void draw(Graphics2D g) {
        g.setColor(buttonColor);

        g.setStroke(new BasicStroke(3));
        // Рисуем кнопку ровно по центру экрана
        g.drawRect(GamePanel.WIDTH / 2 - buttonWidth / 2, GamePanel.HEIGHT / 2 - buttonHeight / 2,
                buttonWidth, buttonHeight);
        g.setStroke(new BasicStroke(1));

        g.setFont(new Font("Consolas", Font.BOLD, 40));
        // Узнаем длину строки для дальнейшей ее центровки
        long textLenght = (int) g.getFontMetrics().getStringBounds(buttonText, g).getWidth();
        g.drawString(buttonText, (int)(GamePanel.WIDTH / 2 - (textLenght / 2)), (int)(GamePanel.HEIGHT / 2 + buttonHeight / 5));

    }
}
