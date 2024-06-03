package model.enums.mainMenu;

public enum MainMenuCommands {
    logout(""),
    goToProfileMenu(""),
    goToPreGameMenu(""),
    goToPreviousMenu("");
    private final String regex;
    MainMenuCommands(String regex) {
        this.regex = regex;
    }
    public String getRegex() {
        return regex;
    }
}
