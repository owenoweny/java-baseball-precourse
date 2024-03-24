import domains.Score;

public class BaseballView {

    public void printExitMessage() {
        System.out.println("게임을 끝냅니다.");
    }

    public void printCompleteMessage() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
    }

    public void printRestartInputMessage() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
    }

    public void printPitchInputMessage() {
        System.out.print("숫자를 입력해주세요 : ");
    }

    public void printScore(Score score) {
        if (score.getBall() == 0 && score.getStrike() == 0) {
            System.out.println("낫싱");
            return;
        }
        if (score.getStrike() > 0) {
            System.out.print(score.getStrike() + "스트라이크");
        }
        if (score.getBall() > 0) {
            System.out.print(score.getBall() + "볼 ");
        }
        System.out.println();
    }

    public void printErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }
}
