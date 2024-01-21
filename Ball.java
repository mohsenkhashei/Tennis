package ballgame;

public class Ball extends Thread {
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;
    private boolean isActive;
    private int initialY;
    private Wall wall1;
    private Wall wall2;

    private Paddle paddle;

    public Ball(int x, int y, int xSpeed, int ySpeed, Paddle paddle, Wall wall1, Wall wall2) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.isActive = true;
        this.initialY = y;
        this.paddle = paddle;
         this.wall1 = wall1;
        this.wall2 = wall2;
    }

    public void run() {
        while (isActive) {
            move();
            try {
                sleep(20); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;

        // Reflect off walls
        if (x <= 0 || x >= GamePanel.WIDTH - 20) {
            xSpeed = -xSpeed;
        }

        
        if (y <= 0 || y >= GamePanel.HEIGHT - 20) {
            ySpeed = -ySpeed;
        }
        
  // Reflect off walls (walls behave like they are hitting the ball)
        if ((x + 20 >= wall1.getX() && x <= wall1.getX() + wall1.getWidth() && y + 20 >= wall1.getY() && y <= wall1.getY() + wall1.getHeight())
            || (x + 20 >= wall2.getX() && x <= wall2.getX() + wall2.getWidth() && y + 20 >= wall2.getY() && y <= wall2.getY() + wall2.getHeight())) {
            ySpeed = -ySpeed;
        }

        // Reverse direction when hitting the paddle
        if (ySpeed > 0 && y + 20 >= paddle.getY() &&
                x + 20 >= paddle.getX() && x <= paddle.getX() + 100) {
            ySpeed = -ySpeed;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getInitialY() {
        return initialY;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    public boolean getActive() {
        return isActive;
    }
}
