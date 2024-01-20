package ballgame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Ball extends Thread {
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;
    private boolean isActive;
    private int initialY;

    private Paddle paddle;

    public Ball(int x, int y, int xSpeed, int ySpeed, Paddle paddle) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.isActive = true;
        this.initialY = y;
        this.paddle = paddle;
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
        System.out.println(y);
//  if (y >= GamePanel.HEIGHT - 20) {
//        if (!(ySpeed > 0 && y + 20 >= paddle.getY() &&
//              x + 20 >= paddle.getX() && x <= paddle.getX() + 100)) {
//            setActive(false);
//            return; // Stop further processing for this ball
//        }
//    }

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
