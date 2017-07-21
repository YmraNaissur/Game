package ru.naissur.bubbleshooter;

import java.awt.event.*;

/**
 * naissur
 * 12.07.2017
 */
public class Listeners implements KeyListener, MouseListener, MouseMotionListener {
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // если какая-либо кнопка нажата, значение переменной в Player устанавливается в true
        if (key == KeyEvent.VK_W) {
            Player.up = true;
        }

        if (key == KeyEvent.VK_S) {
            Player.down = true;
        }

        if (key == KeyEvent.VK_A) {
            Player.left = true;
        }

        if (key == KeyEvent.VK_D) {
            Player.right = true;
        }

        if (key == KeyEvent.VK_SPACE) {
            Player.isFiring = true;
        }

        // Если нажимаем Esc, то возвращаемся в меню
        if (key == KeyEvent.VK_ESCAPE) {
            GamePanel.state = GamePanel.STATES.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        // если какая-либо кнопка отжата, значение переменной в Player устанавливается в false
        if (key == KeyEvent.VK_W) {
            Player.up = false;
        }

        if (key == KeyEvent.VK_S) {
            Player.down = false;
        }

        if (key == KeyEvent.VK_A) {
            Player.left = false;
        }

        if (key == KeyEvent.VK_D) {
            Player.right = false;
        }

        if (key == KeyEvent.VK_SPACE) {
            Player.isFiring = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // этот метод нам не нужен
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    // Действия при нажатии кнопки мыши
    public void mousePressed(MouseEvent e) {
        // Если нажата левая кнопка мыши
        if (e.getButton() == MouseEvent.BUTTON1) {
            Player.isFiring = true; // Игрок начинает стрелять
            GamePanel.isLeftMousePressed = true; // Нажата левая кнопка мыши

        }
    }

    @Override
    // Действия при отпускании кнопки мыши
    public void mouseReleased(MouseEvent e) {
        // Если отпущена левая кнопка мыши
        if (e.getButton() == MouseEvent.BUTTON1) {
            Player.isFiring = false;    // Игрок перестает стрелять
            GamePanel.isLeftMousePressed = false;   // Отпущена левая кнопка мыши
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // передаем координаты указателя мыши на игровую панель
        GamePanel.mouseX = e.getX();
        GamePanel.mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // передаем координаты указателя мыши на игровую панель
        GamePanel.mouseX = e.getX();
        GamePanel.mouseY = e.getY();
    }
}
