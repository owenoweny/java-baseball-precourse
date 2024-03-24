package domains;

// TODO: 역할을 더 잘 나타내는 이름으로 변경
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
