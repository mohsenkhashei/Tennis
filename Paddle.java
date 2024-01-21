package ballgame;

public class Paddle {
    private int x;
    private int y;
    private int speed;

    public Paddle(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void moveLeft() {
        x -= speed;
        if (x < 0) {
            x = 0;
        }
    }

    public void moveRight() {
        x += speed;
        if (x > GamePanel.WIDTH - 50) {
            x = GamePanel.WIDTH - 50;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
 
}
