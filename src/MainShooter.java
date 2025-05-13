import processing.core.PApplet;

public class MainShooter {
    private int x;
    private int y;
    private int speed;
    private int health;
    private int color;
    private PApplet canvas;

    public MainShooter(int xPos, int yPos, int speed, int health, PApplet c) {
        x = xPos;
        y = yPos;
        this.speed=speed;
        this.health=health;
        canvas = c;
    }
    public void display() {
        color = canvas.color(100,100,100);
        canvas.fill(color);
        canvas.rect(x,y,100,100);
    }

    public void moveLeft() {
        x=x-speed;
    }
    public void moveRight() {
        x=x+speed;
    }
    public int x() {
        return x;
    }
    public int y() {
        return y;
    }
}
