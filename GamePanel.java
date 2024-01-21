package ballgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private GameController gameController;
    
    private Wall wall1;
    private Wall wall2;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        addKeyListener(this);

        gameController = new GameController();
        initializeWalls();
        initializeBalls(6); // Set the number of balls
        startGame();
    }

    private void initializeBalls(int numBalls) {
        for (int i = 0; i < numBalls; i++) {
            Ball ball = new Ball(
                (int) (Math.random() * (WIDTH - 20)),  // Random x within visible area
                (int) (Math.random() * (HEIGHT - 20)), // Random y within visible area
                (int) (Math.random() * 5) + 1, // speed
                (int) (Math.random() * 5) + 1, // speed
                gameController.getPaddle(),
                wall1,
                wall2
            );
            gameController.addBall(ball);
            ball.start();
        }
        gameController.setTotalBalls(numBalls);
        gameController.setBallsLeft(numBalls);
    }
 private void initializeWalls() {
        int wallWidth = 120;
        int wallHeight = 5;
        int wallY = (HEIGHT - wallHeight) / 2;

        wall1 = new Wall((WIDTH - wallWidth) / 2, wallY - 30, wallWidth, wallHeight);
        wall2 = new Wall((WIDTH - wallWidth) / 2, wallY + wallHeight + 10, wallWidth, wallHeight);
    }
    private void startGame() {
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
        g.fillRect(paddle.getX(), paddle.getY(), 50, 10);

        g.fillRect(wall1.getX(), wall1.getY(), wall1.getWidth(), wall1.getHeight());
        g.fillRect(wall2.getX(), wall2.getY(), wall2.getWidth(), wall2.getHeight());

        
        g.setColor(Color.BLACK);
        g.drawString("Score: " + gameController.getScore(), 10, 20);
        g.drawString("Total Balls: " + gameController.getTotalBalls(), 10, 40);
        g.drawString("Balls Left: " + gameController.getBallsLeft(), 10, 60);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        Paddle paddle = gameController.getPaddle();
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            paddle.moveLeft();
            gameController.paddleStartsMoving(); // Call the method when left arrow key is pressed
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            paddle.moveRight();
            gameController.paddleStartsMoving(); // Call the method when right arrow key is pressed
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Ball Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new GamePanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

