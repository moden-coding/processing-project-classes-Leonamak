import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
        bg = loadImage("background.png");
        bulletImage = loadImage("bullet.png");
        bg.resize(1000, 900);
        bulletImage.resize(12, 12);
        score = new Score();
        shooter = new MainShooter(450, 600, 10, 5, this);
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        enemiesKilled = 0;
        scene = 0;
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
        if (scene==1) {
            textSize(45);
            textAlign(CENTER);
            text("YOU LOST! SCORE:", 500,400);
            text(enemiesKilled, 500, 450 );
        }
        if (scene==2) {
            textSize(45);
            textAlign(CENTER);
            text("YOU WON!", 500,400);
        }
        else if (scene==0) {
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

        for(int i=0; i<bullets.size(); i++){
            Bullet b = bullets.get(i);
            if(b.ypos()<0){
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
                shootable=true;
            }
            };
            timer.schedule(task, 100);


        }
        textSize(20);
        fill(255);
        String timed = String.valueOf(score.run());
        text(timed, 50, 50);
        text(enemiesKilled, 50, 70);
        if (score.run()>60 && cont==false) {
            scene=1;
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
            scene=1;
        }
        if (key == 'l') {
            scene=2;
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
        for (int ii=0; ii<bullets.size(); ii++) {
            Bullet b = bullets.get(ii);
            Enemy e = enemies.get(listnumb);
            // System.out.println("bullet x pos " + b.xpos());
            // System.out.println("enemy x pos " + e.xLoc());
            if (b.xpos() < e.xLoc() + 50 && b.xpos() > e.xLoc() && b.ypos() < e.yLoc() + 100 && b.ypos() > e.yLoc()) {
                bullets.remove(b);
                score.take(25);
                enemiesKilled+=1;
                return true;
                // System.out.println(check);
            }
        }
        return false;
    }
}