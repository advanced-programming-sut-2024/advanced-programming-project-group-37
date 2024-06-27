package model.enums.profileMenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {
    changeUsername("change username -u (?<username>\\S+)"),
    changeNickname("change nickname -n (?<nickname>\\S+)"),
    changeEmail("change email -e (?<email>\\S+)"),
    changePassword("change password -p (?<newpassword>\\S+) -o (?<oldpassword>\\S+)"),
    enterUserInfoMenu(""), //todo ILIYA
    goToPreviousMenu(""); //todo ILIYA
    private final String regex;

    ProfileMenuCommands(String regex) {
        this.regex = regex;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.regex).matcher(input);
        if (matcher.matches()) {
            return matcher;
        } else return null;    }
}
