package model.enums.card;

import javafx.animation.Animation;
import model.enums.gameMenu.Factions;
import model.toolClasses.Result;

public enum Card {
    Mardoeme("Mardoeme", Factions.SKELLIGE, 0, 3, CardType.SPELL, false, Ability.MARDROEME),
    Berserker("Berserker", Factions.SKELLIGE, 4, 1, CardType.CLOSE_COMBAT, false, Ability.BERSERKER),
    Vidkaari("Vidkaarl", Factions.SKELLIGE, 14, -1, CardType.CLOSE_COMBAT, false, Ability.MORALBOOST),
    Svanrige("Svanrige", Factions.SKELLIGE, 4, 1, CardType.CLOSE_COMBAT, false, null),
    Udalryk("Udalryk", Factions.SKELLIGE, 4, 1, CardType.CLOSE_COMBAT, false, null),
    DonarAnHindar("Donar an Hindar", Factions.SKELLIGE, 4, 1, CardType.CLOSE_COMBAT, false, null),
    ClanAnCrait("Clan an Crait", Factions.SKELLIGE, 6, 3, CardType.CLOSE_COMBAT, false, Ability.TIGHT_BOND),
    BlueboyLugos("Blueboy Lugos", Factions.SKELLIGE, 6, 1, CardType.CLOSE_COMBAT, false, null),
    MadmanLugos("Madman Lugos", Factions.SKELLIGE, 6, 1, CardType.CLOSE_COMBAT, false, null),
    Cerys("Cerys", Factions.SKELLIGE, 10, 1, CardType.CLOSE_COMBAT, true, Ability.MUSTER),
    Kambi("Kambi", Factions.SKELLIGE, 11, 1, CardType.CLOSE_COMBAT, false, null),
    BirnaBran("Birna Bran", Factions.SKELLIGE, 2, 1, CardType.CLOSE_COMBAT, false, Ability.MEDIC),
    ClanDrummondShieldmaiden("Clan Drummond Shieldmaiden", Factions.SKELLIGE, 4, 3, CardType.CLOSE_COMBAT, false, Ability.TIGHT_BOND),
    ClanTordarrochArmorsmith("Clan Tordarroch Armorsmith", Factions.SKELLIGE, 4, 1, CardType.CLOSE_COMBAT, false, null),
    ClanDimunPirate("Clan Dimun Pirate", Factions.SKELLIGE, 6, 1, CardType.RANGED_COMBAT, false, Ability.SCROCH),
    ClanBrokvarArcher("Clan Brokvar Archer", Factions.SKELLIGE, 6, 3, CardType.RANGED_COMBAT, false, null),
    Ermion("Ermion", Factions.SKELLIGE, 8, 1, CardType.RANGED_COMBAT, true, Ability.MARDROEME),
    Hjalmar("Hjalmar", Factions.SKELLIGE, 10, 1, CardType.RANGED_COMBAT, true, null),
    YoungBerserker("YoungBerserker", Factions.SKELLIGE, 2, 3, CardType.RANGED_COMBAT, false, Ability.BERSERKER),
    YoungVidkaari("Young Vidkaari", Factions.SKELLIGE, 8, -1, CardType.RANGED_COMBAT, false, Ability.TIGHT_BOND),
    LightLongship("Light Longship", Factions.SKELLIGE, 4, 3, CardType.RANGED_COMBAT, false, Ability.MUSTER),
    HolgerBlackhand("Holger Blackhand", Factions.SKELLIGE, 4, 1, CardType.SIEGE, false, null),
    WarLongship("War Longship", Factions.SKELLIGE, 6, 3, CardType.SIEGE, false, Ability.TIGHT_BOND),
    DraigBon_Dhu("Draig Bon-Dhu", Factions.SKELLIGE, 2, 1, CardType.SIEGE, false, Ability.COMMANDER_HORN),
    Olaf("Olaf", Factions.SKELLIGE, 12, 1, CardType.AGILE, false, Ability.MORALBOOST),


    //next faction:
    //      Scoia'tael

    ElvenSkirmisher(Factions.SCOIATEAL, 2, 3, CardType.RANGED_COMBAT, false, Ability.MUSTER),
    Iorveth(Factions.SCOIATEAL, 10, 1, CardType.RANGED_COMBAT, true, null),
    Yaevinn(Factions.SCOIATEAL, 6, 1, CardType.AGILE, false, null),
    CiaranAep(Factions.SCOIATEAL, 3, 1, CardType.AGILE, false, null),
    DennisCranmer(Factions.SCOIATEAL, 6, 1, CardType.CLOSE_COMBAT, false, null),
    DolBlathannaScout(Factions.SCOIATEAL, 6, 3, CardType.AGILE, false, null),
    DolBlathannaArcher(Factions.SCOIATEAL, 4, 1, CardType.RANGED_COMBAT, false, null),
    DwarvenSkirmisher(Factions.SCOIATEAL, 3, 3, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    Filavandrel(Factions.SCOIATEAL, 6, 1, CardType.AGILE, false, null),
    HavekarHealer(Factions.SCOIATEAL, 0, 3, CardType.RANGED_COMBAT, false, Ability.MEDIC),
    HavekarSmuggler(Factions.SCOIATEAL, 5, 3, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    IdaEmeanAep(Factions.SCOIATEAL, 6, 3, CardType.RANGED_COMBAT, false, null),
    Riordain(Factions.SCOIATEAL, 1, 1, CardType.RANGED_COMBAT, false, null),
    Toruviel(Factions.SCOIATEAL, 2, 1, CardType.RANGED_COMBAT, false, null),
    VriheddBrigadeRecruit(Factions.SCOIATEAL, 4, 1, CardType.RANGED_COMBAT, false, null),
    MahakamanDefender(Factions.SCOIATEAL, 5, 5, CardType.CLOSE_COMBAT, false, null),
    VriheddBrigadeVeteran(Factions.SCOIATEAL, 5, 2, CardType.AGILE, false, Ability.MORALBOOST),
    Milva(Factions.SCOIATEAL, 10, 1, CardType.RANGED_COMBAT, false, null),
    Seasenthessis(Factions.SCOIATEAL, 10, 1, CardType.RANGED_COMBAT, true, Ability.SCROCH),
    Schirru(Factions.SCOIATEAL, 8, 1, CardType.SIEGE, false, null),
    Eithne(Factions.SCOIATEAL, 10, 1, CardType.RANGED_COMBAT, true, null),
    IsengrimFaoiliarna(Factions.SCOIATEAL, 10, 1, CardType.CLOSE_COMBAT, true, Ability.MORALBOOST),


    // NORTHERN REALM:
    Ballista(Factions.REALMS_NORTHERN, 6, 2, CardType.SIEGE, false, null),
    BlueStripesCommando(Factions.REALMS_NORTHERN, 4, 3, CardType.CLOSE_COMBAT, false, Ability.TIGHT_BOND),
    Catapult(Factions.REALMS_NORTHERN, 8, 2, CardType.SIEGE, false, Ability.TIGHT_BOND),
    DragonHunter(Factions.REALMS_NORTHERN, 5, 3, CardType.RANGED_COMBAT, false, Ability.TIGHT_BOND),
    Dethmold(Factions.REALMS_NORTHERN, 6, 1, CardType.RANGED_COMBAT, false, null),
    DunBannerMedic(Factions.REALMS_NORTHERN, 5, 1, CardType.SIEGE, false, Ability.MEDIC),
    EsteradThyssen(Factions.REALMS_NORTHERN, 10, 1, CardType.CLOSE_COMBAT, true, null),
    JohnNatalis(Factions.REALMS_NORTHERN, 10, 1, CardType.CLOSE_COMBAT, true, null),
    KaedweniSiegeExpert(Factions.REALMS_NORTHERN, 1, 3, CardType.SIEGE, false, Ability.MORALBOOST),
    KeiraMetz(Factions.REALMS_NORTHERN, 5, 1, CardType.RANGED_COMBAT, false, null),
    PhilippaEilhart(Factions.REALMS_NORTHERN, 10, 1, CardType.RANGED_COMBAT, true, null),
    PoorFuckingInfantry(Factions.REALMS_NORTHERN, 1, 4, CardType.CLOSE_COMBAT, false, Ability.TIGHT_BOND),
    PrinceStennis(Factions.REALMS_NORTHERN, 5, 1, CardType.CLOSE_COMBAT, false, Ability.SPY),
    RedanianFootSoldier(Factions.REALMS_NORTHERN, 1, 2, CardType.CLOSE_COMBAT, false, null),
    SabrinaGlevissing(Factions.REALMS_NORTHERN, 4, 1, CardType.RANGED_COMBAT, false, null),
    SheldonSkagg(Factions.REALMS_NORTHERN, 4, 1, CardType.RANGED_COMBAT, false, null),
    SiegeTower(Factions.REALMS_NORTHERN, 6, 1, CardType.SIEGE, false, null),
    SiegfriedOfDenesle(Factions.REALMS_NORTHERN, 5, 1, CardType.CLOSE_COMBAT, false, null),
    SigismundDijkstra(Factions.REALMS_NORTHERN, 4, 1, CardType.CLOSE_COMBAT, false, Ability.SPY),
    SileDeTansarville(Factions.REALMS_NORTHERN, 5, 1, CardType.RANGED_COMBAT, false, null),
    Thaler(Factions.REALMS_NORTHERN, 1, 1, CardType.SIEGE, false, Ability.SPY),
    Trebuchet(Factions.REALMS_NORTHERN, 6, 2, CardType.SIEGE, false, null),
    VernonRoche(Factions.REALMS_NORTHERN, 10, 1, CardType.CLOSE_COMBAT, true, null),
    Ves(Factions.REALMS_NORTHERN, 5, 1, CardType.CLOSE_COMBAT, false, null),
    YarpenZirgrin(Factions.REALMS_NORTHERN, 2, 1, CardType.CLOSE_COMBAT, false, null),


    //Nilfgaard Faction:
    ImperaBrigadeGuard(Factions.EMPIRE_NILFGARDEN, 3, 4, CardType.CLOSE_COMBAT, false, Ability.TIGHT_BOND),
    StefanSkellen(Factions.EMPIRE_NILFGARDEN, 9, 1, CardType.CLOSE_COMBAT, false, Ability.SPY),
    ShilardFitzOesterlen(Factions.EMPIRE_NILFGARDEN, 7, 1, CardType.CLOSE_COMBAT, false, Ability.SPY),
    YoungEmissary(Factions.EMPIRE_NILFGARDEN, 5, 2, CardType.CLOSE_COMBAT, false, Ability.TIGHT_BOND),
    CahirMawrDyffrynAepCeallach(Factions.EMPIRE_NILFGARDEN, 6, 1, CardType.CLOSE_COMBAT, false, null),
    VattierDeRideaux(Factions.EMPIRE_NILFGARDEN, 4, 1, CardType.CLOSE_COMBAT, false, Ability.SPY),
    MennoCoehorn(Factions.EMPIRE_NILFGARDEN, 10, 1, CardType.CLOSE_COMBAT, true, Ability.MEDIC),
    PuttKammer(Factions.EMPIRE_NILFGARDEN, 3, 1, CardType.RANGED_COMBAT, false, null),
    AssireVarAnahid(Factions.EMPIRE_NILFGARDEN, 6, 1, CardType.RANGED_COMBAT, false, null),
    BlackInfantryArcher(Factions.EMPIRE_NILFGARDEN, 10, 2, CardType.RANGED_COMBAT, false, null),
    TiborEggebracht(Factions.EMPIRE_NILFGARDEN, 10, 1, CardType.RANGED_COMBAT, true, null),
    RenualdAepMatsen(Factions.EMPIRE_NILFGARDEN, 5, 1, CardType.RANGED_COMBAT, false, null),
    FringilaVigo(Factions.EMPIRE_NILFGARDEN, 6, 1, CardType.RANGED_COMBAT, false, null),
    RottenMangonel(Factions.EMPIRE_NILFGARDEN, 3, 1, CardType.SIEGE, false, null),
    HeavyZerrikanianFireScorpion(Factions.EMPIRE_NILFGARDEN, 10, 1, CardType.SIEGE, false, null),
    ZerrikanianFireScorpion(Factions.EMPIRE_NILFGARDEN, 5, 1, CardType.SIEGE, false, null),
    SiegeEngineer(Factions.EMPIRE_NILFGARDEN, 6, 1, CardType.SIEGE, false, null),
    MorvranVoohis(Factions.EMPIRE_NILFGARDEN, 10, 1, CardType.SIEGE, true, null),
    Albrich(Factions.EMPIRE_NILFGARDEN, 2, 1, CardType.RANGED_COMBAT, false, null),
    Cynthia(Factions.EMPIRE_NILFGARDEN, 4, 1, CardType.RANGED_COMBAT, false, null),
    EtolianAuxiliaryArchers(Factions.EMPIRE_NILFGARDEN, 1, 2, CardType.RANGED_COMBAT, false, Ability.MEDIC),
    LethoOfGulet(Factions.EMPIRE_NILFGARDEN, 10, 1, CardType.CLOSE_COMBAT, true, null),
    MennoCoehoorn(Factions.EMPIRE_NILFGARDEN, 10, 1, CardType.CLOSE_COMBAT, true, Ability.MEDIC),
    Morteisen(Factions.EMPIRE_NILFGARDEN, 3, 1, CardType.CLOSE_COMBAT, false, null),
    MorvranVoorhis(Factions.EMPIRE_NILFGARDEN, 10, 1, CardType.SIEGE, true, null),
    NausicaaCavalryRider(Factions.EMPIRE_NILFGARDEN, 2, 3, CardType.CLOSE_COMBAT, false, Ability.TIGHT_BOND),
    Rainfarn(Factions.EMPIRE_NILFGARDEN, 4, 1, CardType.CLOSE_COMBAT, false, null),
    SiegeTechnician(Factions.EMPIRE_NILFGARDEN, 0, 1, CardType.SIEGE, false, Ability.MEDIC),
    Sweers(Factions.EMPIRE_NILFGARDEN, 2, 1, CardType.RANGED_COMBAT, false, null),
    Vanhemar(Factions.EMPIRE_NILFGARDEN, 2, 1, CardType.RANGED_COMBAT, false, null),
    Vreemde(Factions.EMPIRE_NILFGARDEN, 2, -1, CardType.RANGED_COMBAT, false, null),


    //Monster
    Draug(Factions.MONSTER, 10, 1, CardType.CLOSE_COMBAT, true, null),
    Imlerith(Factions.MONSTER, 10, 1, CardType.CLOSE_COMBAT, true, null),
    Leshen(Factions.MONSTER, 10, 1, CardType.CLOSE_COMBAT, true, null),
    Kayran(Factions.MONSTER, 8, 1, CardType.AGILE, true, Ability.MORALBOOST),
    Toad(Factions.MONSTER, 7, 1, CardType.RANGED_COMBAT, false, Ability.SCROCH),
    ArachasBehemoth(Factions.MONSTER, 6, 1, CardType.SIEGE, false, Ability.MUSTER),
    CroneBrewess(Factions.MONSTER, 6, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    CroneWeavess(Factions.MONSTER, 6, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    CroneWhispess(Factions.MONSTER, 6, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    EarthElemental(Factions.MONSTER, 6, 1, CardType.SIEGE, false, null),
    Fiend(Factions.MONSTER, 6, 1, CardType.CLOSE_COMBAT, false, null),
    FireElemental(Factions.MONSTER, 6, 1, CardType.SIEGE, false, null),
    Forktail(Factions.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, null),
    Frightener(Factions.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, null),
    GraveHag(Factions.MONSTER, 5, 1, CardType.RANGED_COMBAT, false, null),
    Griffin(Factions.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, null),
    IceGiant(Factions.MONSTER, 5, 1, CardType.SIEGE, false, null),
    PlagueMaiden(Factions.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, null),
    VampireKatakan(Factions.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    Werewolf(Factions.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, null),
    Arachas(Factions.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    Botchling(Factions.MONSTER, 4, 1, CardType.CLOSE_COMBAT, false, null),
    VampireBruxa(Factions.MONSTER, 4, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    VampireEkimmara(Factions.MONSTER, 4, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    VampireFleder(Factions.MONSTER, 4, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    VampireGarkain(Factions.MONSTER, 4, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    CelaenoHarpy(Factions.MONSTER, 2, 1, CardType.AGILE, false, null),
    Endrega(Factions.MONSTER, 2, 1, CardType.RANGED_COMBAT, false, null),
    Foglet(Factions.MONSTER, 2, 1, CardType.CLOSE_COMBAT, false, null),
    Gargoyle(Factions.MONSTER, 2, 1, CardType.RANGED_COMBAT, false, null),
    Harpy(Factions.MONSTER, 2, 1, CardType.AGILE, false, null),
    Nekker(Factions.MONSTER, 2, 3, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    Wyvern(Factions.MONSTER, 2, 1, CardType.RANGED_COMBAT, false, null),
    Ghoul(Factions.MONSTER, 1, 3, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    ;

    private final Factions faction;
    private final int power;
    private final int numberOfCardInGame;
    private final CardType type;
    private final boolean isHero;
    private final Ability ability;

    Card(String name, Factions faction, int power, int numberOfCardInGame, CardType type, boolean isHero, Ability ability) {
        this.faction = faction;
        this.power = power;
        this.numberOfCardInGame = numberOfCardInGame;
        this.type = type;
        this.isHero = isHero;
        this.ability = ability;
    }

    public Factions getFaction() {
        return faction;
    }

    public int getPower() {
        return power;
    }

    public int getNumberOfCardInGame() {
        return numberOfCardInGame;
    }

    public CardType getType() {
        return type;
    }

    public boolean getIsHero() {
        return isHero;
    }

    // methods for ability
    public static Result commandsHorn() {
        return null;
    }

    public static Result medic() {
        return null;
    }

    public static Result moralBoost() {
        return null;
    }

    public static Result muster() {
        return null;
    }

    public static Result spy() {
        return null;
    }

    public static Result tightBond() {
        return null;
    }

    public static Result scroch() {
        return null;
    }

    public static Result berserker() {
        return null;
    }

    public static Result mardroeme() {
        return null;
    }

    public static Result transformers() {
        return null;
    }


}
