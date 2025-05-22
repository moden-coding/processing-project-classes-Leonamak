public class Score {
    private int time;
    public Score() {
        time = 0;
    }
    public int run() {
        time = time+1;
        return time;
    }
    public int take(int subtract) {
        time = time-subtract;
        return time;
    }
}
