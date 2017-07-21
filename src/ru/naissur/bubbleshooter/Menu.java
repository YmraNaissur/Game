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
    private int transparency;

    // Конструктор
    public Menu() {
        buttonWidth = 120;
        buttonHeight = 60;
        buttonColor = Color.ORANGE;
        buttonText = "Play";
        transparency = 0;
    }

    // Методы
    public void update() {
        // Если указатель мыши находится в пределах кнопки (Play)
        if (GamePanel.mouseX > GamePanel.WIDTH / 2 - buttonWidth / 2 &&
                GamePanel.mouseX < GamePanel.WIDTH / 2 + buttonWidth / 2 &&
                GamePanel.mouseY > GamePanel.HEIGHT / 2 - buttonHeight / 2 &&
                GamePanel.mouseY < GamePanel.HEIGHT / 2 + buttonHeight / 2) {
            transparency = 60; // то уменьшаем прозрачность заливки кнопки (она подсвечивается)
            if (GamePanel.isLeftMousePressed) {
                GamePanel.state = GamePanel.STATES.PLAY;    // если нажата ЛКМ, меняем состояние на Play
            }
        } else {
            transparency = 0;   // иначе делаем так, чтобы кнопка перестала подсвечиваться
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(buttonColor);

        g.setStroke(new BasicStroke(3));
        // Рисуем кнопку ровно по центру экрана
        g.drawRect(GamePanel.WIDTH / 2 - buttonWidth / 2, GamePanel.HEIGHT / 2 - buttonHeight / 2,
                buttonWidth, buttonHeight);

        // Заливаем эту кнопку, но заливка будет полностью прозрачной до тех пор, пока
        // Мы не наведем на кнопку курсор мыши
        g.setColor(new Color(255, 255, 255, transparency));
        g.fillRect(GamePanel.WIDTH / 2 - buttonWidth / 2, GamePanel.HEIGHT / 2 - buttonHeight / 2,
                buttonWidth, buttonHeight);

        g.setStroke(new BasicStroke(1));
        g.setColor(buttonColor);
        g.setFont(new Font("Consolas", Font.BOLD, 40));
        // Узнаем длину строки для дальнейшей ее центровки
        long textLenght = (int) g.getFontMetrics().getStringBounds(buttonText, g).getWidth();
        g.drawString(buttonText, (int)(GamePanel.WIDTH / 2 - (textLenght / 2)), (int)(GamePanel.HEIGHT / 2 + buttonHeight / 5));

    }
}
