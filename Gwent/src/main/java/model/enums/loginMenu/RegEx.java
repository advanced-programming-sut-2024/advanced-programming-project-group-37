package model.enums.loginMenu;

public enum RegEx {
    VALID_USERNAME("^[a-zA-Z0-9-]+$"),
    validPassword("^[a-zA-Z0-9!@#$%^&*()_+=-]+$"),
    strongPassword("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,}$"),
    validEmail("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    private final String regex;
    RegEx(String regex) {
        this.regex = regex;
    }
    public String getRegex() {
        return regex;
    }
}