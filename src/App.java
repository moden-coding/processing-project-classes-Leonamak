import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.io.PrintWriter;
import java.io.IOException;

import processing.core.*;

public class App extends PApplet {
    MainShooter shooter;
    Score score;
    ArrayList<Bullet> bullets;
    ArrayList<Enemy> enemies;
    PImage bg;
    PImage bulletImage;
    int enemyX;
    int enemyY;
    boolean left = false;
    boolean right = false;
    boolean shoot = false;
    boolean shootable = true;
    int enemiesKilled;
    int scene;
    boolean cont = false;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        //this sets up the framework of my game - arraylists, classes, etc.
        bg = loadImage("Images/background.png");
        bulletImage = loadImage("Images/bullet.png");
        bg.resize(1000, 900);
        bulletImage.resize(12, 12);
        score = new Score();
        shooter = new MainShooter(450, 600, 10, 5, this);
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        enemiesKilled = 0;
        scene = 0;
        frameRate(60);
        //this creates the starting locations of my enemies
        for (int i = 2; i > -2; i += -1) {
            int enemyX = 500 - (100 * i);
            int enemyY = 100;
            Enemy enemy = new Enemy(enemyX, enemyY, 1, this, 1);
            enemies.add(enemy);
        }
    }

    public void settings() {
        size(1000, 800);

    }

    public void draw() {
        image(bg, 0, 0);
        if (scene == 0) {
            //this is my initial start scene! pretty nice right
            textSize(50);
            textAlign(CENTER);
            text("You have around 60 seconds.", 500, 300);
            text("Every enemy you shoot down adds time.", 500, 400);
            text(" Try to get to 100 points!", 500, 500);
            textSize(30);
            text("Insert credit to play", 500, 700);
            textSize(20);
            text("Spacebar to reload", 500, 730);
        }
        if (scene == 1) {
            //this is actually the lose scene. if you see this, sorry :(
            textSize(45);
            textAlign(CENTER);
            text("YOU LOST! SCORE:", 500, 400);
            text(enemiesKilled, 500, 450);
        }
        if (scene == 2) {
            //yayy!! you probably won (or cheated) if you see this
            textSize(45);
            textAlign(CENTER);
            text("YOU WON!", 500, 400);
            text("Time:"+score.stop(), 500, 450);
        } else if (scene == 3) {
            //finally, the playing screen.
            shooter.display();
            for (int i = 0; i < enemies.size(); i++) {
                if (checkTouch(i)) {
                    enemies.remove(i);
                }
            }
            for (int i = 0; i < enemies.size(); i++) {
                Enemy e = enemies.get(i);
                e.display();
                e.move();
            }

            for (int i = 0; i < bullets.size(); i++) {
                Bullet b = bullets.get(i);
                if (b.ypos() < 0) {
                    bullets.remove(b);
                }
            }
            if (enemies.size() == 1) {
                for (int i = 0; i < 4; i++) {
                    int enemyX = (int) random(100, 900);
                    int enemyY = (int) random(100, 400);
                    Enemy enemy = new Enemy(enemyX, enemyY, 1, this, 1);
                    enemies.add(enemy);
                }

            }
            for (Bullet b : bullets) {
                b.show();
                b.shoot();
            }
            if (left) {
                shooter.moveLeft();
            }
            if (right) {
                shooter.moveRight();
            }
            if (shoot && shootable) {
                Bullet bullet = new Bullet(shooter.x() + 25, 500, 15, this);
                bullets.add(bullet);
                shootable = false;
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    public void run() {
                        shootable = true;
                    }
                };
                timer.schedule(task, 100);

            }
            textSize(20);
            fill(255);
            String timed = String.valueOf(nf(score.run(), 1, 2));
            text(timed, 50, 50);
            text(enemiesKilled, 50, 70);
            if (score.run() > 60 && cont == false) {
                scene = 1;
            }
            if (enemiesKilled == 100){
                System.out.println("killrdf 200");
                scene = 2;
            }
        }

    }

    public void keyPressed() {
        if (key == 'a') {
            left = true;
        }
        if (key == 'd') {
            shooter.moveRight();
            right = true;
        }
        if (key == 'w') {
            shoot = true;
        }
        if (key == 'p') {
            scene = 1;
        }
        if (key == 'l') {
            scene=2;
        }
        if (key == ' ') {
            scene = 3;
        }

    }

    public void keyReleased() {
        if (key == 'w' || key == 'W') {
            shoot = false;
        }
        if (key == 'a' || key == 'A') {
            left = false;
        }
        if (key == 'd' || key == 'D') {
            right = false;
        }
    }

    public boolean checkTouch(int listnumb) {
        for (int ii = 0; ii < bullets.size(); ii++) {
            Bullet b = bullets.get(ii);
            Enemy e = enemies.get(listnumb);
            // System.out.println("bullet x pos " + b.xpos());
            // System.out.println("enemy x pos " + e.xLoc());
            if (b.xpos() < e.xLoc() + 50 && b.xpos() > e.xLoc() && b.ypos() < e.yLoc() + 100 && b.ypos() > e.yLoc()) {
                bullets.remove(b);
                System.out.println("hi");
                score.take(25);
                enemiesKilled += 1;
                return true;
                // System.out.println(check);
            }
        }
        return false;
    }
    // public int highscore() {
    //     int sigma;
    //     return sigma;
    // }
}