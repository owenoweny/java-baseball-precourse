package domains;

public class BaseballGame {
    private Numbers numbers;

    public BaseballGame() {}

    public void initialize() {
        numbers = Numbers.getRandomNumbers();
    }

    public Score scoreOf(Numbers pitchNumbers) {
        return numbers.scoreOf(pitchNumbers);
    }
}
