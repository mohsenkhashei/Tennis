package ballgame;

import java.util.ArrayList;
import java.util.List;

class GameController {
    private List<Ball> balls;
    private Paddle paddle;
    private int caughtBalls;
    private int totalBalls;

    public GameController() {
        balls = new ArrayList<>();
        paddle = new Paddle(GamePanel.WIDTH / 2 - 50, GamePanel.HEIGHT - 30, 5);
        caughtBalls = 0;
        totalBalls = 0;
    }

    public void addBall(Ball ball) {
        balls.add(ball);
        totalBalls++;
    }

    public void update() {
        for (Ball ball : balls) {
            if (ball.getActive() && ball.getY() >= paddle.getY() && ball.getX() >= paddle.getX() &&
                    ball.getX() <= paddle.getX() + 100) {
                ball.setActive(false);
                caughtBalls++;
            }
        }
    }

    public List<Ball> getBalls() {
        return balls;
    }
    
    public int getCaughtBalls() {
        return caughtBalls;
    }

    public int getTotalBalls() {
        return totalBalls;
    }

    public Paddle getPaddle() {
        return paddle;
    }
}
