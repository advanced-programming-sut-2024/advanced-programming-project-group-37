package model.enums.loginMenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    enterMainMenu(""),
    exitMenu(""),
    showCurrentMenu(""),
    register("register -u (?<username>\\S+) -p (?<password>\\S+) (?<passwordconfirm>\\S+) -n (?<nickname>\\S+)" +
            " -e (?<email>\\S+)"),
    pickQuestion("pick -q (?<questionnum>\\S+) -a (?<answer>\\S+) -c (?<answerconfirm>\\S+)"),
    login("login -u (?<username>\\S+) -p (?<password>\\S+)"),
    forgetPassword("forget password -u (?<username>\\S+)"),
    answerQ("answer -a (?<answer>\\S+)"),
    setPassword("set password -p (?<password>\\S+)");
    private final String regex;

    LoginMenuCommands(String regex) {
        this.regex = regex;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.regex).matcher(input);
        if (matcher.matches()) {
            return matcher;
        } else return null;
    }
}