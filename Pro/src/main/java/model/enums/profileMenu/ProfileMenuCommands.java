package model.enums.profileMenu;

public enum ProfileMenuCommands {
    changeUsername(""),
    changeNickname(""),
    changeEmail(""),
    changePassword(""),
    enterUserInfoMenu(""),
    goToPreviousMenu("");
    private final String regex;
    ProfileMenuCommands(String regex) {
        this.regex = regex;
    }
    public String getRegex() {
        return regex;
    }
}
