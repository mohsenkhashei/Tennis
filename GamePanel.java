package ballgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

class GamePanel extends JPanel implements KeyListener {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private GameController gameController;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        addKeyListener(this);

        gameController = new GameController();
        initializeBalls(3); // Set the number of balls
        startGame();
    }

    private void initializeBalls(int numBalls) {
        for (int i = 0; i < numBalls; i++) {
            Ball ball = new Ball((int) (Math.random() * (WIDTH - 20)), 0,
                    (int) (Math.random() * 5) + 1, (int) (Math.random() * 5) + 1);
            gameController.addBall(ball);
        }
    }

    private void startGame() {
        for (Ball ball : gameController.getBalls()) {
            ball.start();
        }

        Thread gameThread = new Thread(() -> {
            while (true) {
                gameController.update();
                repaint();
                try {
                    Thread.sleep(20); // Adjust the sleep duration as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ball ball : gameController.getBalls()) {
            if (ball.getActive()) {
                g.fillOval(ball.getX(), ball.getY(), 20, 20);
            }
        }

        Paddle paddle = gameController.getPaddle();
        g.fillRect(paddle.getX(), paddle.getY(), 100, 10);

        g.setColor(Color.BLACK);
        g.drawString("Caught Balls: " + gameController.getCaughtBalls(), 10, 20);
        g.drawString("Total Balls: " + gameController.getTotalBalls(), 10, 40);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Paddle paddle = gameController.getPaddle();
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            paddle.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            paddle.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
