import domains.BaseballGame;
import domains.Numbers;
import domains.RestartCommand;
import domains.Score;

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
            Score score = requestRepeatedly(this::getScore);

            baseballView.printScore(score);

            if (score.isCompleted()) {
                RestartCommand restartCommand = requestRepeatedly(this::getRestartCommand);

                if (restartCommand.equals(RestartCommand.END)) {
                    baseballView.printExitMessage();
                    break;
                }
                baseballGame.initialize();
            }
        }
    }

    private Score getScore() {
        baseballView.printPitchInputMessage();
        String pitchInput = scanner.nextLine();
        List<Integer> pitchNumbers = parsePitchInput(pitchInput);
        Score score = baseballGame.scoreOf(Numbers.of(pitchNumbers));

        return score;
    }

    private RestartCommand getRestartCommand() {
        baseballView.printCompleteMessage();
        String restartInput = scanner.nextLine();
        RestartCommand restartCommand = RestartCommand.of(restartInput);
        return restartCommand;
    }

    private List<Integer> parsePitchInput(String pitchInput) {
        List<Integer> result = new ArrayList<>();
        String[] a = pitchInput.split("");
        for (String s : a) {
            result.add(Integer.parseInt(s));
        }

        return result;
    }

    private <T> T requestRepeatedly(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            //TODO : 커스텀 예외 선언 후 처리하도록 변경
            } catch (Exception e) {
                baseballView.printErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new BaseballApp().run();
    }
}
