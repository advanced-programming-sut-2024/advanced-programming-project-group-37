package model.enums.gameMenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum PreGameCommands {

    CREATE_GAME("create game -p2 (?<palyer2username>\\S+)"),
    SHOW_FACTIONS("show factions -n (?<n>\\d+)"),
    SELECT_FACTION("select faction -f (?<factionname>\\S+) -n (?<n>\\d+)"),
    SHOW_CARDS("show cards -n (?<n>\\S+)"),
    SHOW_DECK("show deck -n (?<n>\\S+)"),
    SHOW_INFORMATION_CURRENT_USER("show information -n (?<n>\\S+)"),
    SAVE_DECK_WITH_ADDRESS(""),
    SAVE_DECK_WITH_NAME(""),
    LOAD_DECK_WITH_ADDRESS(""),
    LOAD_DECK_WITH_NAME(""),
    SHOW_LEADERS("show leaders -n (?<n>\\S+)"),
    SELECT_LEADER("select leader -l (?<leadernum>\\S+) -n (?<"),
    ADD_TO_DECK("add to deck -c (?<cardname>\\S+\\s*\\S+) (?<count>\\d+) -n (?<n>\\d+)"),
    DELETE_FROM_DECK("delete from deck -c (?<cardname>\\S+) (?<count>\\S+) -n (?<n>\\d+)"),
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
