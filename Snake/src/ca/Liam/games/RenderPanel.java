package ca.Liam.games;

import javax.swing.*;
import java.awt.*;


public class RenderPanel extends JPanel {

    private Color green = new Color(0, 255, 0);
    static String message;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 700);
        Snake snake = Snake.snake;
        g.setColor(green);
        g.setFont(new Font("BankGothic", Font.PLAIN, 20));
        for (Point point : snake.snakeParts){
            g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE,
                    Snake.SCALE, Snake.SCALE);
        }
        g.fillRect(snake.head.x * Snake.SCALE,
                snake.head.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
        g.setColor(Color.red);
        g.fillRect(snake.cherry.x * Snake.SCALE,
                snake.cherry.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
        g.setColor(Color.YELLOW);
        g.drawString("score: " + snake.score, 10,20);
        g.drawString("Length: " + snake.tailLength, 10,50);

        if (snake.paused || snake.over){
            g.setColor(Color.MAGENTA);
            g.setFont(new Font("BankGothic", Font.PLAIN, 30));
            g.drawString(message, 10, 90);
        }
    }
}