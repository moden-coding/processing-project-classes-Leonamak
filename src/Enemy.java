import processing.core.*;

public class Enemy {
    private int health;
    private int x;
    private int y;
    private int color;
    private PApplet canvas;

    public Enemy(int xPos, int yPos, int health, PApplet c) {
        this.health = health;
        canvas = c;
        x=xPos;
        y=yPos;
    }
    //  public void enemyDefeat() {
    //     if (health==0) {

    //     }
    //  }
     public void display() {
        color = canvas.color(100,100,100);
        canvas.fill(color);
        canvas.rect(x,y,50,100);
        
     }
     public int xLoc() {
        return x;
     }
     public int yLoc() {
        return y;
     }
}