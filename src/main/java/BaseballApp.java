import domains.BaseballGame;
import domains.Numbers;
import domains.RestartCommand;
import domains.Score;
import exceptions.BaseballException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public class BaseballApp {
    private final BaseballGame baseballGame;
    private final BaseballView baseballView;
    private final Scanner scanner;

    public BaseballApp() {
        this.baseballGame = new BaseballGame();
        this.baseballView = new BaseballView();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        baseballGame.initialize();
        while (true) {
            if (processTurn()) break;
        }
    }

    private boolean processTurn() {
        Score score = requestRepeatedly(this::getScore);
        baseballView.printScore(score);
        if (score.isCompleted()) {
            baseballView.printCompleteMessage();
            RestartCommand restartCommand = requestRepeatedly(this::getRestartCommand);
            if (restartCommand.equals(RestartCommand.END)) {
                baseballView.printExitMessage();
                return true;
            }
            baseballGame.initialize();
        }
        return false;
    }

    private Score getScore() {
        baseballView.printPitchInputMessage();
        String pitchInput = scanner.nextLine();
        List<Integer> pitchNumbers = parsePitchInput(pitchInput);
        Score score = baseballGame.scoreOf(Numbers.of(pitchNumbers));

        return score;
    }

    private RestartCommand getRestartCommand() {
        baseballView.printRestartInputMessage();
        String restartInput = scanner.nextLine();
        RestartCommand restartCommand = RestartCommand.of(restartInput);
        return restartCommand;
    }

    private List<Integer> parsePitchInput(String pitchInput) {
        List<Integer> result;
        try {
            result = new ArrayList<>();
            String[] a = pitchInput.split("");
            for (String s : a) {
                result.add(Integer.parseInt(s));
            }
        } catch (NumberFormatException e) {
            throw new BaseballException("숫자 형태의 입력이어야합니다.");
        }

        return result;
    }

    private <T> T requestRepeatedly(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (BaseballException e) {
                baseballView.printErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new BaseballApp().run();
    }
}
