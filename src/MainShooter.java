import processing.core.*;

public class MainShooter {
    private int x;
    private int y;
    private int speed;
    private int health;
    private int color;
    private PApplet canvas;
    PImage image;

    public MainShooter(int xPos, int yPos, int speed, int health, PApplet c) {
        x = xPos;
        y = yPos;
        this.speed=speed;
        this.health=health;
        canvas = c;
        this.image = canvas.loadImage("shooter.png");
        this.image.resize(128,128);
    }
    public void display() {
        color = canvas.color(100,100,100);
        canvas.image(image, x-15, y);
    }

    public void moveLeft() {
        if (x>0) {
        x=x-speed;
        }
    }
    public void moveRight() {
        if (x<900) {
        x=x+speed;
        }
    }
    public int x() {
        return x;
    }
    public int y() {
        return y;
    }
}
