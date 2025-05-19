import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {
    MainShooter shooter;
    ArrayList<Bullets> bullets;
    ArrayList<Enemy> enemies;
    PImage bg;
    PImage bulletImage;
    int enemyX;
    int enemyY;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        bg = loadImage("background.png");
        bulletImage = loadImage("bullet.png");
        bg.resize(1000, 900);
        bulletImage.resize(12, 12);
        shooter = new MainShooter(450, 600, 10, 5, this);
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        for (int i = 2; i > -2; i += -1) {
            int enemyX = 500 - (100 * i);
            int enemyY = 100;
            Enemy enemy = new Enemy(enemyX, enemyY, 1, this);
            enemies.add(enemy);
        }
    }

    public void settings() {
        size(1000, 800);

    }

    public void draw() {
        image(bg, 0, 0);
        shooter.display();
        for (int i = 0; i<enemies.size(); i++) {
            if (checkTouch(i)) {
                enemies.remove(i);
            }
        }
        for (Enemy e : enemies) {
            e.display();
        }
        for (Bullets b : bullets) {
            b.show();
            b.shoot();
        }

    }

    public void keyPressed() {
        if (key == 'a') {
            shooter.moveLeft();
        }
        if (key == 'd') {
            shooter.moveRight();
        }
        if (key == 'w') {
            Bullets bullet = new Bullets(shooter.x() + 25, 500, 15, this);
            bullets.add(bullet);
        }

    }

    public boolean checkTouch(int listnumb) {
        boolean check = false;
        for (Bullets b : bullets) {
            Enemy e = enemies.get(listnumb);
            System.out.println("bullet x pos " + b.xpos());
            System.out.println("enemy x pos " + e.xLoc());
            if (b.xpos() < e.xLoc() + 50 && b.xpos() > e.xLoc() && b.ypos() < e.yLoc()+100) {

                check = true;
                System.out.println(check);
            } else {
                check = false;
            }

        }
        return check;
    }
}