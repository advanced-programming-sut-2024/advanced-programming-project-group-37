package model.enums.gameMenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    VETO_CARD("veto card (?<cardnumber>\\d+)"),
    IN_HAND_DECK("in hand deck"),
    REMAINING_CARD("remaining cards to play"),
    OUT_OF_PLAY_CARD(""),
    CARDS_IN_ROW("cards in row (?<rownumber>\\S+)"),
    SPELLS_IN_PLAY(""),
    PLACE_CARD(""),
    SHOW_COMMANDER("show commander"),
    COMMANDER_POWER_PLAY("commander power play"),
    SHOW_PLAYERS_INFO("show players info"),
    SHOW_PLAYERS_LIVE("show player lives"),
    SHOW_NUMBER_OF_CARDS_IN_HAND("show number of cards in hand"),
    SHOW_TURN_INFO("show turn info"),
    SHOW_TOTAL_SCORE(""),
    SHOW_TOTAL_SCORE_OF_ROW("show total score of row (<rownumber>)"),
    PASS_ROUND(""),
    IN_HAND_DECK_OPTION("in hand deck -option (?<place>)"),


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
