package ballgame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameController {

    private List<Ball> balls;
    private Paddle paddle;
    private int score;
    private int totalBalls;
    private int leftBalls;

    public GameController() {
        balls = new ArrayList<>();
        paddle = new Paddle(GamePanel.WIDTH / 2 - 50, GamePanel.HEIGHT - 30, 5);
        score = 0;
        totalBalls = 0;
        leftBalls = 0;
    }

    public void addBall(Ball ball) {
        balls.add(ball);
        totalBalls++;
    }

    public List<Ball> getBalls() {
        return balls;
    }
    private boolean ballsLaunched = false;

    public void update() {
        Iterator<Ball> iterator = balls.iterator();
        while (iterator.hasNext()) {
            Ball ball = iterator.next();

            if (ball.getActive()) {
                ball.move();
                System.out.println("balls lunch "+ ballsLaunched);
                if (ballsLaunched) {
                    if (ball.getY() >= GamePanel.HEIGHT) {
                        System.out.println("Ball hit the ground - Removing");
                        ball.setActive(false);
                        iterator.remove();
                        leftBalls--;

                    } else if (checkCollision(ball, paddle)) {
                        System.out.println("Ball hit the paddle - Score");
                        ball.setActive(false);
                        iterator.remove();
                        score++;
                    }
                }
            }
        }
    }
   private boolean checkCollision(Ball ball, Paddle paddle) {
        // Check bounding box collision
      return ball.getX() < paddle.getX() + 50
            && ball.getX() + 20 > paddle.getX()
            && ball.getY() < paddle.getY() + 10
            && ball.getY() + 20 > paddle.getY();
    }
    public void paddleStartsMoving() {
        // Set the flag to indicate that the balls have been launched
        ballsLaunched = true;
    }

    public void launchBalls() {
        // Set the flag to indicate that the balls have been launched
        ballsLaunched = true;
    }

  
    public int getScore() {
        return score;
    }

    public int getTotalBalls() {
        return totalBalls;
    }
    public void setTotalBalls(int value) {
        this.totalBalls = value;
    }
    public int getBallsLeft() {
        return leftBalls;
    }
    public void setBallsLeft(int value) {
        this.leftBalls = value;
    }

    public Paddle getPaddle() {
        return paddle;
    }
}
//else if (ball.getY() + 20 < paddle.getY()) {
//                    // Ball is outside the paddle at the top - remove it
//                    System.out.println("Ball outside the paddle - Removing");
//                    ball.setActive(false);
//                    iterator.remove();
//                    leftBalls--;
//                    }