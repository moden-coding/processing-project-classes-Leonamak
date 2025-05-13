import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {
    MainShooter shooter;
    ArrayList<Bullets> bullets;
    ArrayList<Enemies> enemies;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        shooter = new MainShooter(400, 500, 10, 5, this);
        background(0, 0, 50);
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            fill(255);
            noStroke();
            rect(random(1000), random(900), 5, 5);
        }
    }

    public void settings() {
        size(1000, 800);

    }

    public void draw() {
        shooter.display();
    }

    public void keyPressed() {
        if (key == 'a') {
            fill(0, 0, 50);
            rect(shooter.x(), shooter.y(), 100, 100);
            shooter.moveLeft();
        }
        if (key == 'd') {
            fill(0, 0, 50);
            rect(shooter.x(), shooter.y(), 100, 100);
            shooter.moveRight();
        }
    }
}