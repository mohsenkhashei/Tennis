package ballgame;

class Ball extends Thread {
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;
    private boolean isActive;

    public Ball(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.isActive = true;
    }

    public void run() {
        while (isActive) {
            move();
            try {
                sleep(20); // Adjust the sleep duration as needed
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
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    
    public boolean getActive() {
    	return isActive;
    }
}