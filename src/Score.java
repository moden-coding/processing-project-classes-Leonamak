import processing.core.*;
public class Score {
    private float time;
    public Score() {
        time = 0;
    }
    public float run() {
        time = time+1;
        return time/100;
    }
    public float take(float subtract) {
        if (time<0) {
            time=0;
        } else {
        time = time-subtract;
        }
        return time/10;
    }
    public float stop() {
        return time/100;
    }

}
