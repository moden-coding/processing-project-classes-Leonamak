import processing.core.*;

public class Bullet {
    private int x;
    private int y;
    private int speed;
    private boolean touching;
    PImage image;
    private PApplet canvas;

    public Bullet(int xPos, int yPos, int speed, PApplet c) {
        canvas = c;
        x = xPos;
        y = yPos;
        touching=false;
        this.speed=speed;
        this.image = canvas.loadImage("bullet.png");
        this.image.resize(60,60);
    }

    public void shoot() {
        y=y-speed;
        x=x;
    }

    public void show() {
        canvas.fill(255,0,0);
        // canvas.circle(x,y,10);
        canvas.image(image, x, y);
    } 
    public int xpos() {
        return x+20;
     }
     public int ypos() {
        return y;
     }
}
