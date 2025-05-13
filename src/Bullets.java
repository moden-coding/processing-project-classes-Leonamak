import java.util.ArrayList;

public class Bullets {
    private int x;
    private int y;
    private int speed;
    private boolean touching;

    public Bullets(int xPos, int yPos, boolean checkTouch) {
        x = xPos;
        y = yPos;
        touching=checkTouch;
        speed=5;
    }

    public void move() {
        y=y+speed;
    }
}
