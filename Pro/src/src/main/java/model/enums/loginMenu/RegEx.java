package model.enums.loginMenu;

public enum RegEx {
    VALID_USERNAME(".+"),
    validPassword(".+"),
    strongPassword(".+"),
    validEmail(".+");
    private final String regex;
    RegEx(String regex) {
        this.regex = regex;
    }
    public String getRegex() {
        return regex;
    }
}