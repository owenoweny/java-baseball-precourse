package domains;

public enum RestartCommand {
    RESTART("1"), END("2");

    public String getCommandNumber() {
        return commandNumber;
    }

    private final String commandNumber;

    RestartCommand(String commandNumber) {
        this.commandNumber = commandNumber;
    }

    public static RestartCommand of(String input) {
        for (RestartCommand command : RestartCommand.values()) {
            if (command.getCommandNumber().equals(input)) {
                return command;
            }
        }

        throw new RuntimeException("재시작 명령어는 1 또는 2여야합니다.");
    }
}
