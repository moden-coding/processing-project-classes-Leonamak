import processing.core.*;

public class Enemy {
   private int health;
   private int x;
   private int y;
   private int color;
   private PApplet canvas;
   private int speed;
   PImage image;

   public Enemy(int xPos, int yPos, int health, PApplet c, int speed) {
      this.health = health;
      canvas = c;
      x = xPos;
      y = yPos;
      this.speed = speed;
      this.image = canvas.loadImage("Images/enemy.png");
   }
   // public void enemyDefeat() {
   // if (health==0) {

   // }
   // }
   public void display() {
      color = canvas.color(100, 100, 100);
      canvas.fill(color);
      canvas.image(image, x-40, y);

   }

   public int xLoc() {
      return x;
   }

   public int yLoc() {
      return y;
   }

   public void move() {
      x+=speed;
      if (x > canvas.width) {
         speed= - speed;
      }
   }
}