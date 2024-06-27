package model.enums.card;

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

    ElvenSkirmisher("Elven Skirmisher", Factions.SCOIATEAL, 2, 3, CardType.RANGED_COMBAT, false, Ability.MUSTER),
    Iorveth("Iorveth", Factions.SCOIATEAL, 10, 1, CardType.RANGED_COMBAT, true, null),
    Yaevinn("Yaevinn", Factions.SCOIATEAL, 6, 1, CardType.AGILE, false, null),
    CiaranAep("Ciaran Aep", Factions.SCOIATEAL, 3, 1, CardType.AGILE, false, null),
    DennisCranmer("Dennis Cranmer", Factions.SCOIATEAL, 6, 1, CardType.CLOSE_COMBAT, false, null),
    DolBlathannaScout("Dol Blathanna Scout", Factions.SCOIATEAL, 6, 3, CardType.AGILE, false, null),
    DolBlathannaArcher("Dol Blathanna Archer", Factions.SCOIATEAL, 4, 1, CardType.RANGED_COMBAT, false, null),
    DwarvenSkirmisher("Dwarven Skirmisher", Factions.SCOIATEAL, 3, 3, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    Filavandrel("Filavandrel", Factions.SCOIATEAL, 6, 1, CardType.AGILE, false, null),
    HavekarHealer("Havekar Healer", Factions.SCOIATEAL, 0, 3, CardType.RANGED_COMBAT, false, Ability.MEDIC),
    HavekarSmuggler("Havekar Smuggler", Factions.SCOIATEAL, 5, 3, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    IdaEmeanAep("Ida Emean Aep", Factions.SCOIATEAL, 6, 3, CardType.RANGED_COMBAT, false, null),
    Riordain("Riordain", Factions.SCOIATEAL, 1, 1, CardType.RANGED_COMBAT, false, null),
    Toruviel("Toruviel", Factions.SCOIATEAL, 2, 1, CardType.RANGED_COMBAT, false, null),
    VriheddBrigadeRecruit("Vrihedd Brigade Recruit", Factions.SCOIATEAL, 4, 1, CardType.RANGED_COMBAT, false, null),
    MahakamanDefender("Mahakaman Defender", Factions.SCOIATEAL, 5, 5, CardType.CLOSE_COMBAT, false, null),
    VriheddBrigadeVeteran("Vrihedd Brigade Veteran", Factions.SCOIATEAL, 5, 2, CardType.AGILE, false, Ability.MORALBOOST),
    Milva("Milva", Factions.SCOIATEAL, 10, 1, CardType.RANGED_COMBAT, false, null),
    Seasenthessis("Seasenthessis", Factions.SCOIATEAL, 10, 1, CardType.RANGED_COMBAT, true, Ability.SCROCH),
    Schirru("Schirru", Factions.SCOIATEAL, 8, 1, CardType.SIEGE, false, null),
    Eithne("Eithne", Factions.SCOIATEAL, 10, 1, CardType.RANGED_COMBAT, true, null),
    IsengrimFaoiliarna("Isengrim Faoiliarna", Factions.SCOIATEAL, 10, 1, CardType.CLOSE_COMBAT, true, Ability.MORALBOOST),


    // NORTHERN REALM:
    Ballista("Ballista", Factions.REALMS_NORTHERN, 6, 2, CardType.SIEGE, false, null),
    BlueStripesCommando("Blue Stripes Commando", Factions.REALMS_NORTHERN, 4, 3, CardType.CLOSE_COMBAT, false, Ability.TIGHT_BOND),
    Catapult("Catapult", Factions.REALMS_NORTHERN, 8, 2, CardType.SIEGE, false, Ability.TIGHT_BOND),
    DragonHunter("Dragon Hunter", Factions.REALMS_NORTHERN, 5, 3, CardType.RANGED_COMBAT, false, Ability.TIGHT_BOND),
    Dethmold("Dethmold", Factions.REALMS_NORTHERN, 6, 1, CardType.RANGED_COMBAT, false, null),
    DunBannerMedic("Dun Banner Medic", Factions.REALMS_NORTHERN, 5, 1, CardType.SIEGE, false, Ability.MEDIC),
    EsteradThyssen("Esterad Thyssen", Factions.REALMS_NORTHERN, 10, 1, CardType.CLOSE_COMBAT, true, null),
    JohnNatalis("John Natalis", Factions.REALMS_NORTHERN, 10, 1, CardType.CLOSE_COMBAT, true, null),
    KaedweniSiegeExpert("Kaedweni Siege Expert", Factions.REALMS_NORTHERN, 1, 3, CardType.SIEGE, false, Ability.MORALBOOST),
    KeiraMetz("Keira Metz", Factions.REALMS_NORTHERN, 5, 1, CardType.RANGED_COMBAT, false, null),
    PhilippaEilhart("Philippa Eilhart", Factions.REALMS_NORTHERN, 10, 1, CardType.RANGED_COMBAT, true, null),
    PoorFuckingInfantry("Poor Fucking Infantry", Factions.REALMS_NORTHERN, 1, 4, CardType.CLOSE_COMBAT, false, Ability.TIGHT_BOND),
    PrinceStennis("Prince Stennis", Factions.REALMS_NORTHERN, 5, 1, CardType.CLOSE_COMBAT, false, Ability.SPY),
    RedanianFootSoldier("Redanian Foot Soldier", Factions.REALMS_NORTHERN, 1, 2, CardType.CLOSE_COMBAT, false, null),
    SabrinaGlevissing("Sabrina Glevissing", Factions.REALMS_NORTHERN, 4, 1, CardType.RANGED_COMBAT, false, null),
    SheldonSkagg("Sheldon Skagg", Factions.REALMS_NORTHERN, 4, 1, CardType.RANGED_COMBAT, false, null),
    SiegeTower("Siege Tower", Factions.REALMS_NORTHERN, 6, 1, CardType.SIEGE, false, null),
    SiegfriedOfDenesle("Siegfried Of Denesle", Factions.REALMS_NORTHERN, 5, 1, CardType.CLOSE_COMBAT, false, null),
    SigismundDijkstra("Sigismund Dijkstra", Factions.REALMS_NORTHERN, 4, 1, CardType.CLOSE_COMBAT, false, Ability.SPY),
    SileDeTansarville("Sile De Tansarville", Factions.REALMS_NORTHERN, 5, 1, CardType.RANGED_COMBAT, false, null),
    Thaler("Thaler", Factions.REALMS_NORTHERN, 1, 1, CardType.SIEGE, false, Ability.SPY),
    Trebuchet("Trebuchet", Factions.REALMS_NORTHERN, 6, 2, CardType.SIEGE, false, null),
    VernonRoche("Vernon Roche", Factions.REALMS_NORTHERN, 10, 1, CardType.CLOSE_COMBAT, true, null),
    Ves("Ves", Factions.REALMS_NORTHERN, 5, 1, CardType.CLOSE_COMBAT, false, null),
    YarpenZirgrin("Yarpen Zirgrin", Factions.REALMS_NORTHERN, 2, 1, CardType.CLOSE_COMBAT, false, null),


    //Nilfgaard Faction:
    ImperaBrigadeGuard("Impera Brigade Guard", Factions.EMPIRE_NILFGARDEN, 3, 4, CardType.CLOSE_COMBAT, false, Ability.TIGHT_BOND),
    StefanSkellen("Stefan Skellen", Factions.EMPIRE_NILFGARDEN, 9, 1, CardType.CLOSE_COMBAT, false, Ability.SPY),
    ShilardFitzOesterlen("Shilard Fitz Oesterlen", Factions.EMPIRE_NILFGARDEN, 7, 1, CardType.CLOSE_COMBAT, false, Ability.SPY),
    YoungEmissary("Young Emissary", Factions.EMPIRE_NILFGARDEN, 5, 2, CardType.CLOSE_COMBAT, false, Ability.TIGHT_BOND),
    CahirMawrDyffrynAepCeallach("Cahir Mawr Dyffryn Aep Ceallach", Factions.EMPIRE_NILFGARDEN, 6, 1, CardType.CLOSE_COMBAT, false, null),
    VattierDeRideaux("Vattier De Rideaux", Factions.EMPIRE_NILFGARDEN, 4, 1, CardType.CLOSE_COMBAT, false, Ability.SPY),
    MennoCoehorn("Menno Coehorn", Factions.EMPIRE_NILFGARDEN, 10, 1, CardType.CLOSE_COMBAT, true, Ability.MEDIC),
    Puttkammer("Puttkammer", Factions.EMPIRE_NILFGARDEN, 3, 1, CardType.RANGED_COMBAT, false, null),
    AssireVarAnahid("Assire Var Anahid", Factions.EMPIRE_NILFGARDEN, 6, 1, CardType.RANGED_COMBAT, false, null),
    BlackInfantryArcher("Black Infantry Archer", Factions.EMPIRE_NILFGARDEN, 10, 2, CardType.RANGED_COMBAT, false, null),
    TiborEggebracht("Tibor Eggebracht", Factions.EMPIRE_NILFGARDEN, 10, 1, CardType.RANGED_COMBAT, true, null),
    RenualdAepMatsen("Renuald Aep Matsen", Factions.EMPIRE_NILFGARDEN, 5, 1, CardType.RANGED_COMBAT, false, null),
    FringilaVigo("Fringila Vigo", Factions.EMPIRE_NILFGARDEN, 6, 1, CardType.RANGED_COMBAT, false, null),
    RottenMangonel("Rotten Mangonel", Factions.EMPIRE_NILFGARDEN, 3, 1, CardType.SIEGE, false, null),
    HeavyZerrikanianFireScorpion("Heavy Zerrikanian Fire Scorpion", Factions.EMPIRE_NILFGARDEN, 10, 1, CardType.SIEGE, false, null),
    ZerrikanianFireScorpion("Zerrikanian Fire Scorpion", Factions.EMPIRE_NILFGARDEN, 5, 1, CardType.SIEGE, false, null),
    SiegeEngineer("Siege Engineer", Factions.EMPIRE_NILFGARDEN, 6, 1, CardType.SIEGE, false, null),
    MorvranVoohis("Morvran Voohis", Factions.EMPIRE_NILFGARDEN, 10, 1, CardType.SIEGE, true, null),
    Albrich("Albrich", Factions.EMPIRE_NILFGARDEN, 2, 1, CardType.RANGED_COMBAT, false, null),
    Cynthia("Cynthia", Factions.EMPIRE_NILFGARDEN, 4, 1, CardType.RANGED_COMBAT, false, null),
    EtolianAuxiliaryArchers("Etolian Auxiliary Archers", Factions.EMPIRE_NILFGARDEN, 1, 2, CardType.RANGED_COMBAT, false, Ability.MEDIC),
    LethoOfGulet("Letho Of Gulet", Factions.EMPIRE_NILFGARDEN, 10, 1, CardType.CLOSE_COMBAT, true, null),
    MennoCoehoorn("Menno Coehoorn", Factions.EMPIRE_NILFGARDEN, 10, 1, CardType.CLOSE_COMBAT, true, Ability.MEDIC),
    Morteisen("Morteisen", Factions.EMPIRE_NILFGARDEN, 3, 1, CardType.CLOSE_COMBAT, false, null),
    MorvranVoorhis("Morvran Voorhis", Factions.EMPIRE_NILFGARDEN, 10, 1, CardType.SIEGE, true, null),
    NausicaaCavalryRider("Nausicaa Cavalry Rider", Factions.EMPIRE_NILFGARDEN, 2, 3, CardType.CLOSE_COMBAT, false, Ability.TIGHT_BOND),
    Rainfarn("Rainfarn", Factions.EMPIRE_NILFGARDEN, 4, 1, CardType.CLOSE_COMBAT, false, null),
    SiegeTechnician("Siege Technician", Factions.EMPIRE_NILFGARDEN, 0, 1, CardType.SIEGE, false, Ability.MEDIC),
    Sweers("Sweers", Factions.EMPIRE_NILFGARDEN, 2, 1, CardType.RANGED_COMBAT, false, null),
    Vanhemar("Vanhemar", Factions.EMPIRE_NILFGARDEN, 2, 1, CardType.RANGED_COMBAT, false, null),
    Vreemde("Vreemde", Factions.EMPIRE_NILFGARDEN, 2, -1, CardType.RANGED_COMBAT, false, null),


    //Monster
    Draug("Draug", Factions.MONSTER, 10, 1, CardType.CLOSE_COMBAT, true, null),
    Imlerith("Imlerith", Factions.MONSTER, 10, 1, CardType.CLOSE_COMBAT, true, null),
    Leshen("Leshen", Factions.MONSTER, 10, 1, CardType.CLOSE_COMBAT, true, null),
    Kayran("Kayran", Factions.MONSTER, 8, 1, CardType.AGILE, true, Ability.MORALBOOST),
    Toad("Toad", Factions.MONSTER, 7, 1, CardType.RANGED_COMBAT, false, Ability.SCROCH),
    ArachasBehemoth("Arachas Behemoth", Factions.MONSTER, 6, 1, CardType.SIEGE, false, Ability.MUSTER),
    CroneBrewess("Crone Brewess", Factions.MONSTER, 6, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    CroneWeavess("Crone Weavess", Factions.MONSTER, 6, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    CroneWhispess("Crone Whispess", Factions.MONSTER, 6, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    EarthElemental("Earth Elemental", Factions.MONSTER, 6, 1, CardType.SIEGE, false, null),
    Fiend("Fiend", Factions.MONSTER, 6, 1, CardType.CLOSE_COMBAT, false, null),
    FireElemental("Fire Elemental", Factions.MONSTER, 6, 1, CardType.SIEGE, false, null),
    Forktail("Forktail", Factions.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, null),
    Frightener("Frightener", Factions.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, null),
    GraveHag("Grave Hag", Factions.MONSTER, 5, 1, CardType.RANGED_COMBAT, false, null),
    Griffin("Griffin", Factions.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, null),
    IceGiant("Ice Giant", Factions.MONSTER, 5, 1, CardType.SIEGE, false, null),
    PlagueMaiden("Plague Maiden", Factions.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, null),
    VampireKatakan("Vampire Katakan", Factions.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    Werewolf("Werewolf", Factions.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, null),
    Arachas("Arachas", Factions.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    Botchling("Botchling", Factions.MONSTER, 4, 1, CardType.CLOSE_COMBAT, false, null),
    VampireBruxa("Vampire Bruxa", Factions.MONSTER, 4, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    VampireEkimmara("Vampire Ekimmara", Factions.MONSTER, 4, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    VampireFleder("Vampire Fleder", Factions.MONSTER, 4, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    VampireGarkain("Vampire Garkain", Factions.MONSTER, 4, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    CelaenoHarpy("Celaeno Harpy", Factions.MONSTER, 2, 1, CardType.AGILE, false, null),
    Endrega("Endrega", Factions.MONSTER, 2, 1, CardType.RANGED_COMBAT, false, null),
    Foglet("Foglet", Factions.MONSTER, 2, 1, CardType.CLOSE_COMBAT, false, null),
    Gargoyle("Gargoyle", Factions.MONSTER, 2, 1, CardType.RANGED_COMBAT, false, null),
    Harpy("Harpy", Factions.MONSTER, 2, 1, CardType.AGILE, false, null),
    Nekker("Nekker", Factions.MONSTER, 2, 3, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
    Wyvern("Wyvern", Factions.MONSTER, 2, 1, CardType.RANGED_COMBAT, false, null),
    Ghoul("Ghoul", Factions.MONSTER, 1, 3, CardType.CLOSE_COMBAT, false, Ability.MUSTER),
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

    public Ability getAbility() {
        return ability;
    }

    public String getName() {
        return name();
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
