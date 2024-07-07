package model.enums.loginMenu;

public enum RegEx {
    VALID_USERNAME("^[a-zA-Z0-9-]+$\n"),
    validPassword("^[a-zA-Z0-9!@#$%^&*()_+=-]+$\n"),
    strongPassword("/^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,}$/gm"),
    validEmail("/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$/gm");
    private final String regex;
    RegEx(String regex) {
        this.regex = regex;
    }
    public String getRegex() {
        return regex;
    }
}