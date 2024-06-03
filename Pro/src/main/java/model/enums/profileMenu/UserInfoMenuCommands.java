package model.enums.profileMenu;

public enum UserInfoMenuCommands {
    gameHistory(""),
    goToPreviousMenu("");
    private final String regex;
    UserInfoMenuCommands(String regex) {
        this.regex = regex;
    }
    public String getRegex() {
        return regex;
    }
}
