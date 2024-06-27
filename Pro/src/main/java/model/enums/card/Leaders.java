package model.enums.card;

import model.enums.gameMenu.Factions;
import model.toolClasses.Result;

public enum Leaders {
    //    BeringerOfDeath(),
//    CommanderOfTheRedRiders(),
//    CrachAnCraite(),
//    DaisyOfTheValley(),
//    DestroyerOfWorlds(),
//    EmperorOfNifgaard(),
//    HisImperialMajesty(),
//    HopeOfTheAenSeidhe(),
//    InvaderOfTheNorth(),
//    KingBran(),
//    KingOfTemeria(),
//    KingOfTheWildHunt(),
//    TheWhiteFalme(),
//    TheTreacherous(),
//    TheSteelForged(),
//    TheSiegemaster(),
//    TheRelentless(),
//    TheBeautiful(),
//    SonOfMedell(),
//    QueenOfDolBlathanna(),
//    LordCommanderOfNorth(),
    TheSiegemaster(Factions.REALMS_NORTHERN),
    KingOfTemeria(Factions.REALMS_NORTHERN),
    LordCommanderOfTheNorth(Factions.REALMS_NORTHERN),
    SonOfMedell(Factions.REALMS_NORTHERN),
    TheWhiteFlame(Factions.EMPIRE_NILFGARDEN),
    HisImperialMajesty(Factions.EMPIRE_NILFGARDEN),
    EmperorOfNilfgaard(Factions.EMPIRE_NILFGARDEN),
    TheRelentless(Factions.EMPIRE_NILFGARDEN),
    InvaderOfTheNorth(Factions.EMPIRE_NILFGARDEN),
    BringerOfDeath(Factions.MONSTER),
    KingOfTheWildHunt(Factions.MONSTER),
    DestroyerOfWorlds(Factions.MONSTER),
    CommanderOfTheRedRiders(Factions.MONSTER),
    TheTreacherous(Factions.MONSTER),
    QueenOfDolBlathanna(Factions.SCOIATEAL),
    TheBeautiful(Factions.SCOIATEAL),
    DaisyOfTheValley(Factions.SCOIATEAL),
    PurebloodElf(Factions.SCOIATEAL),
    HopeOfTheAenSeidhe(Factions.SCOIATEAL),
    CrachAnCraite(Factions.SKELLIGE),
    KingBran(Factions.SKELLIGE);

    private final Factions faction;

    Leaders(Factions faction) {
        this.faction = faction;
    }

    public Result passiveBeringerOfDeath() {
        return null;
    }

    public Result passiveCommanderOfTheRedRiders() {
        return null;
    }

    public Result passiveCrachAnCraite() {
        return null;
    }

    public Result passiveDaisyOfTheValley() {
        return null;
    }

    public Result passiveDestroyerOfWorlds() {
        return null;
    }

    public Result passiveEmperorOfNifgaard() {
        return null;
    }

    public Result passiveHisImperialMajesty() {
        return null;
    }

    public Result passiveHopeOfTheAenSeidhe() {
        return null;
    }

    public Result passiveInvaderOfTheNorth() {
        return null;
    }

    public Result passiveKingBran() {
        return null;
    }

    public Result passiveKingOfTemeria() {
        return null;
    }

    public Result passiveKingOfTheWildHunt() {
        return null;
    }

    public Result passiveTheWhiteFalme() {
        return null;
    }

    public Result passiveTheTreacherous() {
        return null;
    }

    public Result passiveTheSteelForged() {
        return null;
    }

    public Result passiveTheSiegemaster() {
        return null;
    }

    public Result passiveTheRelentless() {
        return null;
    }

    public Result passiveTheBeautiful() {
        return null;
    }

    public Result passiveSonOfMedell() {
        return null;
    }

    public Result passiveQueenOfDolBlathanna() {
        return null;
    }

    public Result passiveLordCommanderOfNorth() {
        return null;
    }

    public Result passivePurebloodElf() {
        return null;
    }
}
