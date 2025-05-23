public class Score {
    private double time;
    public Score() {
        time = 0;
    }
    public double run() {
        time = time+1;
        return time/10;
    }
    public double take(double subtract) {
        if (time<0) {
            time=0;
        } else {
        time = time-subtract;
        }
        return time/10;
}
    
}
