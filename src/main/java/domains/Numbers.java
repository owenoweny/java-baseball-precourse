package domains;

import java.util.*;

public class Numbers {
    private List<Integer> numbers;

    private Numbers(List<Integer> numbers) {
        validateSize(numbers);
        validateNumberRange(numbers);
        validateDuplicate(numbers);
        this.numbers = numbers;
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 3) {
            throw new RuntimeException("세 개의 숫자여야합니다.");
        }
    }

    public static Numbers getRandomNumbers() {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            result.add(numbers.get(i));
        }

        return new Numbers(result);
    }

    public static Numbers of(List<Integer> numbers) {
        return new Numbers(numbers);
    }

    //TODO : rename
    public Score scoreOf(Numbers pitchNumbers) {
        Integer ball = 0;
        Integer strike = 0;

        for (int index = 0; index < numbers.size(); index++) {
            if (numbers.get(index).equals(pitchNumbers.find(index))) {
                strike++;
                continue;
            }
            ball += countBalls(pitchNumbers, index);
        }

        return new Score(strike, ball);
    }
    private Integer countBalls(Numbers pitchNumbers, int index) {
        for (int i = 1; i < 3; i++) {
            if (numbers.get((index + i) % 3).equals(pitchNumbers.find(index))) {
                return 1;
            }
        }
        return 0;
    }

    public Integer find(int index) {
        return numbers.get(index);
    }

    private void validateNumberRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number > 9 || number < 1) {
                throw new RuntimeException("1에서 9 사이의 숫자가 아닙니다.");
            }
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>(numbers);

        if (set.size() != numbers.size()) {
            throw new RuntimeException("중복된 숫자가 존재합니다.");
        }
    }
}
