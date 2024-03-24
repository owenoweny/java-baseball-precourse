package domains;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return Objects.equals(strike, score.strike) && Objects.equals(ball, score.ball);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strike, ball);
    }
}
