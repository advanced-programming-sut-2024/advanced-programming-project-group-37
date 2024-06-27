package model.enums.gameMenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum PreGameCommands {

    CREATE_GAME("create game -p2 (?<palyer2username>\\S+)"),
    SHOW_FACTIONS("show factions -n (?<n>\\d+)"),
    SELECT_FACTION("select faction -f (?<factionname>\\S+) -n (?<n>\\d+)"),
    SHOW_CARDS("show cards -n (?<n>\\S+)"),
    SHOW_DECK(""),
    SHOW_INFORMATION_CURRENT_USER(""),
    SAVE_DECK_WITH_ADDRESS(""),
    SAVE_DECK_WITH_NAME(""),
    LOAD_DECK_WITH_ADDRESS(""),
    LOAD_DECK_WITH_NAME(""),
    SHOW_LEADERS(""),
    SELECT_LEADER(""),
    ADD_TO_DECK(""),
    DELETE_FROM_DECK(""),
    CHANGE_TURN(""),
    START_GAME("");
    private final String regex;
    PreGameCommands(String regex){
        this.regex = regex;
    }
    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.regex).matcher(input);
        if (matcher.matches()) {
            return matcher;
        } else return null;
    }
}
