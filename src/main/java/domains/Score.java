package domains;

public class Score {
    private final Integer strike;
    private final Integer ball;

    public Score(Integer strike, Integer ball) {
        this.strike = strike;
        this.ball = ball;
    }

    public boolean isCompleted() {
        return strike == 3;
    }

    public Integer getStrike() {
        return strike;
    }

    public Integer getBall() {
        return ball;
    }
}
