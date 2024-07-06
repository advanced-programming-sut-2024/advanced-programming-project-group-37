package message.enums.profileMenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {
    changeUsername("change username -u (?<username>\\S+)"),
    changeNickname("change nickname -n (?<nickname>\\S+)"),
    changeEmail("change email -e (?<email>\\S+)"),
    changePassword("change password -p (?<newPassword>\\S+) -o (?<oldPassword>\\S+)"), // todo: foad dorostan?????
    enterOtherMenu("menu enter (.+)");

    private final String regex;

    ProfileMenuCommands(String regex) {
        this.regex = regex;
    }

    public Matcher getMatcher(String input) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}