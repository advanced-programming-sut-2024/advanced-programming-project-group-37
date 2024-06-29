package model.enums.profileMenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum UserInfoMenuCommands {
    gameHistory("game history -n (?<n>\\d+)"),
    goToPreviousMenu("");
    private final String regex;

    UserInfoMenuCommands(String regex) {
        this.regex = regex;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.regex).matcher(input);
        if (matcher.matches()) {
            return matcher;
        } else return null;
    }
}
