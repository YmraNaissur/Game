package ru.naissur.bubbleshooter;

import java.awt.event.*;

/**
 * naissur
 * 12.07.2017
 */
public class Listeners implements KeyListener {
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
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // этот метод нам не нужен
    }
}
