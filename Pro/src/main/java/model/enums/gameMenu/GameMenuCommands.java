package model.enums.gameMenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    VETO_CARD("veto card (?<cardnumber>\\d+)"),
    IN_HAND_DECK(""),
    REMAINING_CARD(""),
    OUT_OF_PLAY_CARD(""),
    CARDS_IN_ROW(""),
    SPELLS_IN_PLAY(""),
    PLACE_CARD(""),
    SHOW_COMMANDER(""),
    COMMANDER_POWER_PLAY(""),
    SHOW_PLAYERS_INFO(""),
    SHOW_PLAYERS_LIVE(""),
    SHOW_NUMBER_OF_CARDS_IN_HAND(""),
    SHOW_TURN_INFO(""),
    SHOW_TOTAL_SCORE(""),
    SHOW_TOTAL_SCORE_OF_ROW(""),
    PASS_ROUND(""),
    IN_HAND_DECK_OPTION(""),


    ;
    private final String regex;
    GameMenuCommands(String regex){
        this.regex = regex;
    }
    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.regex).matcher(input);
        if (matcher.matches()) {
            return matcher;
        } else return null;
    }
}
