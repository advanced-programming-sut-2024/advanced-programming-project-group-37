package server.model.enums.card;

//import controller.GameControllers.GameMenuController;
import javafx.scene.image.Image;
import server.model.User;
import server.model.enums.gameMenu.Factions;
import server.model.gameTable.GameTable;
import server.model.gameTable.UserInGame;
import server.model.toolClasses.Result;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public enum Leaders {
    TheSiegemaster(Factions.REALMS_NORTHERN,
            new Image(Leaders.class.getResource("/asset/img/lg/realms_foltest_silver.jpg").toExternalForm())),
    KingOfTemeria(Factions.REALMS_NORTHERN,
            new Image(Leaders.class.getResource("/asset/img/lg/realms_foltest_copper.jpg").toExternalForm())),
    LordCommanderOfTheNorth(Factions.REALMS_NORTHERN,
            new Image(Leaders.class.getResource("/asset/img/lg/realms_foltest_bronze.jpg").toExternalForm())),
    SonOfMedell(Factions.REALMS_NORTHERN
            , new Image(Leaders.class.getResource("/asset/img/lg/realms_foltest_son_of_medell.jpg").toExternalForm())),
    TheWhiteFlame(Factions.EMPIRE_NILFGARDEN,
            new Image(Leaders.class.getResource("/asset/img/lg/nilfgaard_emhyr_silver.jpg").toExternalForm())),
    HisImperialMajesty(Factions.EMPIRE_NILFGARDEN,
            new Image(Leaders.class.getResource("/asset/img/lg/nilfgaard_emhyr_copper.jpg").toExternalForm())),
    EmperorOfNilfgaard(Factions.EMPIRE_NILFGARDEN,
            new Image(Leaders.class.getResource("/asset/img/lg/nilfgaard_emhyr_bronze.jpg").toExternalForm())),
    TheRelentless(Factions.EMPIRE_NILFGARDEN,
            new Image(Leaders.class.getResource("/asset/img/lg/nilfgaard_emhyr_gold.jpg").toExternalForm())),
    InvaderOfTheNorth(Factions.EMPIRE_NILFGARDEN,
            new Image(Leaders.class.getResource("/asset/img/lg/nilfgaard_emhyr_invader_of_the_north.jpg").toExternalForm())),
    BringerOfDeath(Factions.MONSTER,
            new Image(Leaders.class.getResource("/asset/img/lg/monsters_eredin_silver.jpg").toExternalForm())),
    KingOfTheWildHunt(Factions.MONSTER,
            new Image(Leaders.class.getResource("/asset/img/lg/monsters_eredin_bronze.jpg").toExternalForm())),
    DestroyerOfWorlds(Factions.MONSTER,
            new Image(Leaders.class.getResource("/asset/img/lg/monsters_eredin_gold.jpg").toExternalForm())),
    CommanderOfTheRedRiders(Factions.MONSTER,
            new Image(Leaders.class.getResource("/asset/img/lg/monsters_eredin_copper.jpg").toExternalForm())),
    TheTreacherous(Factions.MONSTER,
            new Image(Leaders.class.getResource("/asset/img/lg/monsters_eredin_the_treacherous.jpg").toExternalForm())),
    QueenOfDolBlathanna(Factions.SCOIATEAL,
            new Image(Leaders.class.getResource("/asset/img/lg/scoiatael_francesca_silver.jpg").toExternalForm())),
    TheBeautiful(Factions.SCOIATEAL,
            new Image(Leaders.class.getResource("/asset/img/lg/scoiatael_francesca_gold.jpg").toExternalForm())),
    DaisyOfTheValley(Factions.SCOIATEAL,
            new Image(Leaders.class.getResource("/asset/img/lg/scoiatael_francesca_copper.jpg").toExternalForm())),
    PurebloodElf(Factions.SCOIATEAL,
            new Image(Leaders.class.getResource("/asset/img/lg/scoiatael_francesca_bronze.jpg").toExternalForm())),
    HopeOfTheAenSeidhe(Factions.SCOIATEAL,
            new Image(Leaders.class.getResource("/asset/img/lg/scoiatael_francesca_hope_of_the_aen_seidhe.jpg").toExternalForm())),
    CrachAnCraite(Factions.SKELLIGE,
            new Image(Leaders.class.getResource("/asset/img/lg/skellige_crach_an_craite.jpg").toExternalForm())),
    KingBran(Factions.SKELLIGE,
            new Image(Leaders.class.getResource("/asset/img/lg/skellige_king_bran.jpg").toExternalForm()));

    public static ArrayList<Leaders> getLeadersByFaction(Factions faction) {
        ArrayList<Leaders> leaders = new ArrayList<>();
        for (Leaders leader : Leaders.values()) {
            if (leader.getFaction().equals(faction))
                leaders.add(leader);
        }
        return leaders;
    }

    private final Image image;
    private final Factions faction;

    Leaders(Factions faction, Image image) {
        this.faction = faction;
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public Result passiveBeringerOfDeath(UserInGame player) {
        //close 3
        GameTable gameTable = player.getGameTable();
        if (!player.isCloseCombatDouble()) {
            player.setCloseCombatDouble(true);// double the power
            return new Result(true, "close combat doubled");
        }
        return new Result(false, "horn was existed");
    }
    //Leader abilities
    /*

    public Result passiveCommanderOfTheRedRiders(UserInGame player, GameMenuController game) {
        //choose one weather card from deck and play it
        Card card = null;
        for (Card card1 : player.getGameTable().getDeckCards()){
            if (card.getType() == CardType.SPECIAL){
                card = card1;
            }
        }
        if (card == null) {
            return new Result(false, "no weather card was found");
        }

        game.placeCard(card.getName(), 4);

        return new Result(true, "weather card played successfully");
    }

    public Result passiveCrachAnCraite(UserInGame player1 , UserInGame player2) {
        //returns dead card to card deck for both playe

        deadToDeck(player1);
        deadToDeck(player2);

        //done

        return new Result(true, "Cards removed from dead collection to deck collection");
    }

    private void deadToDeck(UserInGame player1) {
        ArrayList<Card> dead = player1.getGameTable().getDeadCards();
        ArrayList<Card> deck = player1.getGameTable().getDeckCards();
        for (int i = dead.size() - 1; i >= 0; i--){
            deck.add(deck.get(i));
            dead.remove(dead.get(i));
        }
    }

    public Result passiveDaisyOfTheValley(UserInGame player) {
        //gives a random card to its hand
        Random random = new Random();

        //get a list of special cards
        int upperBound = Factions.SCOIATEAL.getDeepCopyOfArraylist().size();
        int randomNum = random.nextInt(upperBound);

        Card card = Factions.SCOIATEAL.getDeepCopyOfArraylist().get(randomNum - 1).getFirst();

        //add card to hand
        ArrayList<Card> inHand = player.getGameTable().getInHandsCards();
        inHand.add(card);

        return new Result(true, "card " + card.getName() + " to hand");
    }

    public Result passiveDestroyerOfWorlds() {
        //TODO HARD
        return null;
    }

    public Result passiveEmperorOfNifgaard() {
        //TODO Hard
        return null;
    }

    public Result passiveHisImperialMajesty(UserInGame player) {
        ArrayList<Card> cards = player.getGameTable().getInHandsCards();

        Set<Integer> randomNum = new HashSet<>();
        Random random = new Random();

        while (randomNum.size() < 3) {
            randomNum.add(random.nextInt(cards.size()));
        }

        ArrayList<Card> cardsToShow = new ArrayList<>();
        for (Integer integer : randomNum) {
            cardsToShow.add(cards.get(integer));
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (Card card : cardsToShow) {
            stringBuilder.append(card.getName()).append("\n");
        }
        return new Result(true, stringBuilder.toString());
    }

    public Result passiveHopeOfTheAenSeidhe() {
        //TODO HARD
        return null;
    }

    public Result passiveInvaderOfTheNorth(UserInGame player1, UserInGame player2) {
        //give a random card from dead to hand
        randomDeadToHand(player1);
        randomDeadToHand(player2);

        return new Result(true, "random card from dead to players hand");
    }

    private void randomDeadToHand(UserInGame player) {
        Random random = new Random();
        //add random card from dead to hand
        ArrayList<Card> inHand = player.getGameTable().getInHandsCards();
        ArrayList<Card> dead = player.getGameTable().getDeadCards();
        //check if null
        if (dead.isEmpty())
            return;
        int randomNum = random.nextInt(dead.size());
        inHand.add(dead.get(randomNum - 1));
    }

    public Result passiveKingBran() {
        //TODO HARD
        return null;
    }

    public Result passiveKingOfTemeria(UserInGame player) {
        player.setSiegeDouble(true);
        return new Result(true, "siege powers doubled");
    }

    public Result passiveKingOfTheWildHunt() {
        //TODO HARD
        return null;
    }

    public Result passiveTheWhiteFalme(UserInGame player, GameMenuController game) {
        ArrayList<Card> cardsInDeck = player.getGameTable().getDeckCards();
        ArrayList<Card> cardsInHand = player.getGameTable().getInHandsCards();
        if (cardsInDeck.contains(Card.TorrentialRain)) {
            //get game table
            GameTable gameTable = player.getGameTable();
            //add fog to spells
            ArrayList<Card> spells = game.getSpells();
            spells.add(Card.TorrentialRain);
            game.setSpells(spells);
            //remove fog from card in deck
            cardsInDeck.remove(Card.TorrentialRain);
            gameTable.setDeckCards(cardsInDeck);
            //set everything
            player.setGameTable(gameTable);
            return new Result(true, "rain found");
        } else if (cardsInHand.contains(Card.TorrentialRain)) {
            //get game table
            GameTable gameTable = player.getGameTable();
            //add fog to spells
            ArrayList<Card> spells = game.getSpells();
            spells.add(Card.TorrentialRain);
            game.setSpells(spells);
            //remove fog from card in deck
            cardsInHand.remove(Card.TorrentialRain);
            gameTable.setInHandsCards(cardsInHand);
            //set everything
            player.setGameTable(gameTable);
            return new Result(true, "fog found");
        } else return new Result(false, "no fog card");

    }

    public Result passiveTheTreacherous(UserInGame player1, UserInGame player2) {
        //double both side spy
        player1.setSpyDouble(true);
        return new Result(true, "spy power doubled");
    }

    public Result passiveTheSteelForged(UserInGame player1, GameMenuController game) {
        ArrayList<Card> cards = game.getSpells();
        cards.remove(Card.ImpenetrableFog);
        cards.remove(Card.BitingForest);
        cards.remove(Card.TorrentialRain);
        return new Result(true, "spells cleared successfully");
    }

    public Result passiveTheSiegemaster(UserInGame player, GameMenuController game) {
        ArrayList<Card> cardsInDeck = player.getGameTable().getDeckCards();
        ArrayList<Card> cardsInHand = player.getGameTable().getInHandsCards();
        if (cardsInDeck.contains(Card.ImpenetrableFog)) {
            //get game table
            GameTable gameTable = player.getGameTable();
            //add fog to spells
            ArrayList<Card> spells = game.getSpells();
            spells.add(Card.ImpenetrableFog);
            game.setSpells(spells);
            //remove fog from card in deck
            cardsInDeck.remove(Card.ImpenetrableFog);
            gameTable.setDeckCards(cardsInDeck);
            //set everything
            player.setGameTable(gameTable);
            return new Result(true, "fog found");
        } else if (cardsInHand.contains(Card.ImpenetrableFog)) {
            //get game table
            GameTable gameTable = player.getGameTable();
            //add fog to spells
            ArrayList<Card> spells = game.getSpells();
            spells.add(Card.ImpenetrableFog);
            game.setSpells(spells);
            //remove fog from card in deck
            cardsInHand.remove(Card.ImpenetrableFog);
            gameTable.setInHandsCards(cardsInHand);
            //set everything
            player.setGameTable(gameTable);
            return new Result(true, "fog found");
        } else return new Result(false, "no fog card");
    }

    public Result passiveTheRelentless() {
        //TODO HARD
        return null;
    }

    public Result passiveTheBeautiful(UserInGame player) {
        //ranged 3
        GameTable gameTable = player.getGameTable();
        if (!player.isRangedDouble()) {
            player.setRangedDouble(true);// double the power
            return new Result(true, "ranged combat doubled");
        }
        return new Result(false, "horn was existed");
    }

    public Result passiveSonOfMedell(UserInGame opponentPlayer) {
        //destroy the most powerful ranged card
        GameTable gameTable = opponentPlayer.getGameTable();
        ArrayList<Card> cards = gameTable.CardsOfRow[2].getSecond();
        if (cards.isEmpty())
            return new Result(false, "no ranged card");

        Card mostPower = opponentPlayer.getGameTable().getCardsOfRow()[2].getSecond().get(0);
        for (Card card : cards) {
            if (card.getPower() > mostPower.getPower())
                mostPower = card;
        }

        //remove most power card if its power is greater than 10

        if (mostPower.getPower() > 10) {
            gameTable.CardsOfRow[2].getSecond().remove(mostPower);
            opponentPlayer.setGameTable(gameTable);
            return new Result(true, "removed successfully");
        } else return new Result(false, "no ranged card");

    }

    public Result passiveQueenOfDolBlathanna(UserInGame opponentPlayer) {
        //destroy the most powerful ranged card if there was a clos combat card with more than 10 power
        GameTable gameTable = opponentPlayer.getGameTable();
        ArrayList<Card> cards = gameTable.CardsOfRow[2].getSecond();
        if (cards.isEmpty())
            return new Result(false, "no close card");

        Card mostPower = opponentPlayer.getGameTable().getCardsOfRow()[2].getSecond().get(0);
        for (Card card : cards) {
            if (card.getPower() > mostPower.getPower())
                mostPower = card;
        }

        //remove most power card if its power is greater than 10

        if (mostPower.getPower() > 10) {
            gameTable.CardsOfRow[2].getSecond().remove(mostPower);
            opponentPlayer.setGameTable(gameTable);
            return new Result(true, "removed successfully");
        } else return new Result(false, "no ranged card");

    }

    public Result passiveLordCommanderOfNorth(UserInGame opponentPlayer) {
        //destroy the most powerful siege card
        GameTable gameTable = opponentPlayer.getGameTable();
        ArrayList<Card> cards = gameTable.CardsOfRow[1].getSecond();
        if (cards.isEmpty())
            return new Result(false, "no siege card");

        Card mostPower = opponentPlayer.getGameTable().getCardsOfRow()[3].getSecond().get(0);
        for (Card card : cards) {
            if (card.getPower() > mostPower.getPower())
                mostPower = card;
        }

        //remove most power card if its power is greater than 10

        if (mostPower.getPower() > 10) {
            gameTable.CardsOfRow[1].getSecond().remove(mostPower);
            opponentPlayer.setGameTable(gameTable);
            return new Result(true, "removed successfully");
        } else return new Result(false, "no siege card");
    }

    public Result passivePurebloodElf(GameMenuController game) {
        //add new biting forest
        game.placeCard(Card.BitingForest.getName(), 4);
        return new Result(true, "card biting frost played successfully");
    }
    */
    public Factions getFaction() {
        return faction;
    }
}
