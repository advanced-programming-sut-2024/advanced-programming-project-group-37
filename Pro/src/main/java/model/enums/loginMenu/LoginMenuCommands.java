package model.enums.loginMenu;

public enum LoginMenuCommands {
    enterMainMenu(""),
    exitMenu(""),
    showCurrentMenu(""),
    register(""),
    pickQuestion(""),
    login(""),
    forgetPassword(""),
    answerQ(""),
    setPassword("");
    private final String regex;
    LoginMenuCommands(String regex) {
        this.regex = regex;
    }
    public String getRegex() {
        return regex;
    }
}