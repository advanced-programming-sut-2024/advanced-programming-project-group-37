package server.model.enums.card;

import javafx.scene.image.Image;
import server.model.User;
import server.model.enums.gameMenu.Factions;
import server.model.enums.gameMenu.FactionsName;
import server.model.gameTable.UserInGame;
import server.model.toolClasses.Result;
import server.model.toolClasses.Pair;
import java.text.DecimalFormat;
import java.util.ArrayList;

public enum Card {
    Mardoeme("Mardoeme", FactionsName.SKELLIGE, 0, 3, CardType.SPELL, false, Ability.MARDROEME, new Image(Card.class.getResource(
            "/asset/img/lg/special_mardroeme.jpg").toExternalForm())),
    Berserker("Berserker", FactionsName.SKELLIGE, 4, 1, CardType.CLOSE_COMBAT, false, Ability.BERSERKER
            , new Image(Card.class.getResource("/asset/img/lg/skellige_berserker.jpg").toExternalForm())),
    Vidkaari("Vidkaarl", FactionsName.SKELLIGE, 14, 1, CardType.CLOSE_COMBAT, false, Ability.MORALBOOST
            , new Image(Card.class.getResource("/asset/img/lg/skellige_vildkaarl.jpg").toExternalForm())),
    Svanrige("Svanrige", FactionsName.SKELLIGE, 4, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/skellige_svanrige.jpg").toExternalForm())),
    Udalryk("Udalryk", FactionsName.SKELLIGE, 4, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/skellige_udalryk.jpg").toExternalForm())),
    DonarAnHindar("Donar an Hindar", FactionsName.SKELLIGE, 4, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/skellige_donar.jpg").toExternalForm())),
    ClanAnCrait("Clan an Crait", FactionsName.SKELLIGE, 6, 3, CardType.CLOSE_COMBAT, false, Ability.TIGHT_BOND,
            new Image(Card.class.getResource("/asset/img/lg/skellige_craite_warrior.jpg").toExternalForm())),
    BlueboyLugos("Blueboy Lugos", FactionsName.SKELLIGE, 6, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/skellige_blueboy.jpg").toExternalForm())),
    MadmanLugos("Madman Lugos", FactionsName.SKELLIGE, 6, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/skellige_madmad_lugos.jpg").toExternalForm())),
    Cerys("Cerys", FactionsName.SKELLIGE, 10, 1, CardType.CLOSE_COMBAT, true, Ability.MUSTER,
            new Image(Card.class.getResource("/asset/img/lg/skellige_cerys.jpg").toExternalForm())),
    Kambi("Kambi", FactionsName.SKELLIGE, 11, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/skellige_madmad_lugos.jpg").toExternalForm())),
    BirnaBran("Birna Bran", FactionsName.SKELLIGE, 2, 1, CardType.CLOSE_COMBAT, false, Ability.MEDIC,
            new Image(Card.class.getResource("/asset/img/lg/skellige_birna.jpg").toExternalForm())),
    ClanDrummondShieldmaiden("Clan Drummond Shieldmaiden", FactionsName.SKELLIGE, 4, 3, CardType.CLOSE_COMBAT, false, Ability.TIGHT_BOND,
            new Image(Card.class.getResource("/asset/img/lg/skellige_shield_maiden.jpg").toExternalForm())),
    ClanTordarrochArmorsmith("Clan Tordarroch Armorsmith", FactionsName.SKELLIGE, 4, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/skellige_tordarroch.jpg").toExternalForm())),
    ClanDimunPirate("Clan Dimun Pirate", FactionsName.SKELLIGE, 6, 1, CardType.RANGED_COMBAT, false, Ability.SCROCH,
            new Image(Card.class.getResource("/asset/img/lg/skellige_dimun_pirate.jpg").toExternalForm())),
    ClanBrokvarArcher("Clan Brokvar Archer", FactionsName.SKELLIGE, 6, 3, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/skellige_brokva_archer.jpg").toExternalForm())),
    Ermion("Ermion", FactionsName.SKELLIGE, 8, 1, CardType.RANGED_COMBAT, true, Ability.MARDROEME,
            new Image(Card.class.getResource("/asset/img/lg/skellige_ermion.jpg").toExternalForm())),
    Hjalmar("Hjalmar", FactionsName.SKELLIGE, 10, 1, CardType.RANGED_COMBAT, true, null,
            new Image(Card.class.getResource("/asset/img/lg/skellige_hjalmar.jpg").toExternalForm())),
    YoungBerserker("YoungBerserker", FactionsName.SKELLIGE, 2, 3, CardType.RANGED_COMBAT, false, Ability.BERSERKER,
            new Image(Card.class.getResource("/asset/img/lg/skellige_young_berserker.jpg").toExternalForm())),
    YoungVidkaarl("Young Vidkaari", FactionsName.SKELLIGE, 8, 1, CardType.RANGED_COMBAT, false, Ability.TIGHT_BOND,
            new Image(Card.class.getResource("/asset/img/lg/skellige_young_vildkaarl.jpg").toExternalForm())),
    LightLongship("Light Longship", FactionsName.SKELLIGE, 4, 3, CardType.RANGED_COMBAT, false, Ability.MUSTER,
            new Image(Card.class.getResource("/asset/img/lg/skellige_light_longship.jpg").toExternalForm())),
    HolgerBlackhand("Holger Blackhand", FactionsName.SKELLIGE, 4, 1, CardType.SIEGE, false, null,
            new Image(Card.class.getResource("/asset/img/lg/skellige_holger.jpg").toExternalForm())),
    WarLongship("War Longship", FactionsName.SKELLIGE, 6, 3, CardType.SIEGE, false, Ability.TIGHT_BOND,
            new Image(Card.class.getResource("/asset/img/lg/skellige_war_longship.jpg").toExternalForm())),
    DraigBon_Dhu("Draig Bon-Dhu", FactionsName.SKELLIGE, 2, 1, CardType.SIEGE, false, Ability.COMMANDER_HORN,
            new Image(Card.class.getResource("/asset/img/lg/skellige_draig.jpg").toExternalForm())),
    Olaf("Olaf", FactionsName.SKELLIGE, 12, 1, CardType.AGILE, false, Ability.MORALBOOST,
            new Image(Card.class.getResource("/asset/img/lg/skellige_olaf.jpg").toExternalForm())),


    //next faction:
    //      Scoia'tael

    ElvenSkirmisher("Elven Skirmisher", FactionsName.SCOIATEAL, 2, 3, CardType.RANGED_COMBAT, false, Ability.MUSTER,
            new Image(Card.class.getResource("/asset/img/lg/scoiatael_elf_skirmisher.jpg").toExternalForm())),
    Iorveth("Iorveth", FactionsName.SCOIATEAL, 10, 1, CardType.RANGED_COMBAT, true, null,
            new Image(Card.class.getResource("/asset/img/lg/scoiatael_iorveth.jpg").toExternalForm())),
    Yaevinn("Yaevinn", FactionsName.SCOIATEAL, 6, 1, CardType.AGILE, false, null,
            new Image(Card.class.getResource("/asset/img/lg/scoiatael_yaevinn.jpg").toExternalForm())),
    CiaranAep("Ciaran Aep", FactionsName.SCOIATEAL, 3, 1, CardType.AGILE, false, null,
            new Image(Card.class.getResource("/asset/img/lg/scoiatael_ciaran.jpg").toExternalForm())),
    DennisCranmer("Dennis Cranmer", FactionsName.SCOIATEAL, 6, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/scoiatael_dennis.jpg").toExternalForm())),
    DolBlathannaScout("Dol Blathanna Scout", FactionsName.SCOIATEAL, 6, 3, CardType.AGILE, false, null,
            new Image(Card.class.getResource("/asset/img/lg/scoiatael_dol_infantry.jpg").toExternalForm())),
    DolBlathannaArcher("Dol Blathanna Archer", FactionsName.SCOIATEAL, 4, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/scoiatael_dol_archer.jpg").toExternalForm())),
    DwarvenSkirmisher("Dwarven Skirmisher", FactionsName.SCOIATEAL, 3, 3, CardType.CLOSE_COMBAT, false, Ability.MUSTER,
            new Image(Card.class.getResource("/asset/img/lg/scoiatael_dwarf.jpg").toExternalForm())),
    Filavandrel("Filavandrel", FactionsName.SCOIATEAL, 6, 1, CardType.AGILE, false, null,
            new Image(Card.class.getResource("/asset/img/lg/scoiatael_filavandrel.jpg").toExternalForm())),
    HavekarHealer("Havekar Healer", FactionsName.SCOIATEAL, 0, 3, CardType.RANGED_COMBAT, false, Ability.MEDIC,
            new Image(Card.class.getResource("/asset/img/lg/scoiatael_havekar_nurse.jpg").toExternalForm())),
    //    HavekarSmuggler("Havekar Smuggler", FactionsName.SCOIATEAL, 5, 3, CardType.CLOSE_COMBAT, false, Ability.MUSTER,
//            new Image(Card.class.getResource("/asset/img/lg/scoiatael_.jpg").toExternalForm())),
    IdaEmeanAep("Ida Emean Aep", FactionsName.SCOIATEAL, 6, 3, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/scoiatael_ida.jpg").toExternalForm())),
    Riordain("Riordain", FactionsName.SCOIATEAL, 1, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/scoiatael_riordain.jpg").toExternalForm())),
    Toruviel("Toruviel", FactionsName.SCOIATEAL, 2, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/scoiatael_toruviel.jpg").toExternalForm())),
    VriheddBrigadeRecruit("Vrihedd Brigade Recruit", FactionsName.SCOIATEAL, 4, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/scoiatael_vrihedd_cadet.jpg").toExternalForm())),
    MahakamanDefender("Mahakaman Defender", FactionsName.SCOIATEAL, 5, 5, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/scoiatael_mahakam.jpg").toExternalForm())),
    VriheddBrigadeVeteran("Vrihedd Brigade Veteran", FactionsName.SCOIATEAL, 5, 2, CardType.AGILE, false, Ability.MORALBOOST,
            new Image(Card.class.getResource("/asset/img/lg/scoiatael_vrihedd_brigade.jpg").toExternalForm())),
    Milva("Milva", FactionsName.SCOIATEAL, 10, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/scoiatael_milva.jpg").toExternalForm())),
    //    Seasenthessis("Seasenthessis", FactionsName.SCOIATEAL, 10, 1, CardType.RANGED_COMBAT, true, Ability.SCROCH,
//            new Image(Card.class.getResource("/asset/img/lg/scoiatael_seakia.jpg").toExternalForm())),
    Schirru("Schirru", FactionsName.SCOIATEAL, 8, 1, CardType.SIEGE, false, null,
            new Image(Card.class.getResource("/asset/img/lg/scoiatael_schirru.jpg").toExternalForm())),
    Eithne("Eithne", FactionsName.SCOIATEAL, 10, 1, CardType.RANGED_COMBAT, true, null,
            new Image(Card.class.getResource("/asset/img/lg/scoiatael_eithne.jpg").toExternalForm())),
    IsengrimFaoiliarna("Isengrim Faoiliarna", FactionsName.SCOIATEAL, 10, 1, CardType.CLOSE_COMBAT, true, Ability.MORALBOOST,
            new Image(Card.class.getResource("/asset/img/lg/scoiatael_isengrim.jpg").toExternalForm())),


    // NORTHERN REALM:
    Ballista("Ballista", FactionsName.REALMS_NORTHERN, 6, 2, CardType.SIEGE, false, null,
            new Image(Card.class.getResource("/asset/img/lg/realms_ballista.jpg").toExternalForm())),
    BlueStripesCommando("Blue Stripes Commando", FactionsName.REALMS_NORTHERN, 4, 3, CardType.CLOSE_COMBAT, false, Ability.TIGHT_BOND,
            new Image(Card.class.getResource("/asset/img/lg/realms_blue_stripes.jpg").toExternalForm())),
    Catapult("Catapult", FactionsName.REALMS_NORTHERN, 8, 2, CardType.SIEGE, false, Ability.TIGHT_BOND,
            new Image(Card.class.getResource("/asset/img/lg/realms_catapult_1.jpg").toExternalForm())),
    DragonHunter("Dragon Hunter", FactionsName.REALMS_NORTHERN, 5, 3, CardType.RANGED_COMBAT, false, Ability.TIGHT_BOND,
            new Image(Card.class.getResource("/asset/img/lg/realms_crinfrid.jpg").toExternalForm())),
    Dethmold("Dethmold", FactionsName.REALMS_NORTHERN, 6, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/realms_dethmold.jpg").toExternalForm())),
    DunBannerMedic("Dun Banner Medic", FactionsName.REALMS_NORTHERN, 5, 1, CardType.SIEGE, false, Ability.MEDIC,
            new Image(Card.class.getResource("/asset/img/lg/realms_banner_nurse.jpg").toExternalForm())),
    EsteradThyssen("Esterad Thyssen", FactionsName.REALMS_NORTHERN, 10, 1, CardType.CLOSE_COMBAT, true, null,
            new Image(Card.class.getResource("/asset/img/lg/realms_esterad.jpg").toExternalForm())),
    JohnNatalis("John Natalis", FactionsName.REALMS_NORTHERN, 10, 1, CardType.CLOSE_COMBAT, true, null,
            new Image(Card.class.getResource("/asset/img/lg/realms_natalis.jpg").toExternalForm())),
    KaedweniSiegeExpert("Kaedweni Siege Expert", FactionsName.REALMS_NORTHERN, 1, 3, CardType.SIEGE, false, Ability.MORALBOOST,
            new Image(Card.class.getResource("/asset/img/lg/realms_kaedwen_siege.jpg").toExternalForm())),
    KeiraMetz("Keira Metz", FactionsName.REALMS_NORTHERN, 5, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/realms_keira.jpg").toExternalForm())),
    PhilippaEilhart("Philippa Eilhart", FactionsName.REALMS_NORTHERN, 10, 1, CardType.RANGED_COMBAT, true, null,
            new Image(Card.class.getResource("/asset/img/lg/realms_philippa.jpg").toExternalForm())),
    //    PoorFuckingInfantry("Poor Fucking Infantry", FactionsName.REALMS_NORTHERN, 1, 4, CardType.CLOSE_COMBAT, false, Ability.TIGHT_BOND,
//            new Image(Card.class.getResource("/asset/img/lg/infantry.jpg").toExternalForm())),
    PrinceStennis("Prince Stennis", FactionsName.REALMS_NORTHERN, 5, 1, CardType.CLOSE_COMBAT, false, Ability.SPY,
            new Image(Card.class.getResource("/asset/img/lg/realms_stennis.jpg").toExternalForm())),
    RedanianFootSoldier("Redanian Foot Soldier", FactionsName.REALMS_NORTHERN, 1, 2, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/realms_redania.jpg").toExternalForm())),
    SabrinaGlevissing("Sabrina Glevissing", FactionsName.REALMS_NORTHERN, 4, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/realms_sabrina.jpg").toExternalForm())),
    SheldonSkagg("Sheldon Skagg", FactionsName.REALMS_NORTHERN, 4, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/realms_sheldon.jpg").toExternalForm())),
    SiegeTower("Siege Tower", FactionsName.REALMS_NORTHERN, 6, 1, CardType.SIEGE, false, null,
            new Image(Card.class.getResource("/asset/img/lg/realms_siege_tower.jpg").toExternalForm())),
    //    SiegfriedOfDenesle("Siegfried Of Denesle", FactionsName.REALMS_NORTHERN, 5, 1, CardType.CLOSE_COMBAT, false, null,
//            new Image(Card.class.getResource("/asset/img/lg/realms_siegefried.jpg").toExternalForm())),
//    SigismundDijkstra("Sigismund Dijkstra", FactionsName.REALMS_NORTHERN, 4, 1, CardType.CLOSE_COMBAT, false, Ability.SPY,
//            new Image(Card.class.getResource("/asset/img/lg/realms_dikstra.jpg").toExternalForm())),
    SileDeTansarville("Sile De Tansarville", FactionsName.REALMS_NORTHERN, 5, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/realms_sheala.jpg").toExternalForm())),
    Thaler("Thaler", FactionsName.REALMS_NORTHERN, 1, 1, CardType.SIEGE, false, Ability.SPY,
            new Image(Card.class.getResource("/asset/img/lg/realms_thaler.jpg").toExternalForm())),
    Trebuchet("Trebuchet", FactionsName.REALMS_NORTHERN, 6, 2, CardType.SIEGE, false, null,
            new Image(Card.class.getResource("/asset/img/lg/realms_trebuchet.jpg").toExternalForm())),
    VernonRoche("Vernon Roche", FactionsName.REALMS_NORTHERN, 10, 1, CardType.CLOSE_COMBAT, true, null,
            new Image(Card.class.getResource("/asset/img/lg/realms_vernon.jpg").toExternalForm())),
    Ves("Ves", FactionsName.REALMS_NORTHERN, 5, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/realms_ves.jpg").toExternalForm())),
    YarpenZirgrin("Yarpen Zirgrin", FactionsName.REALMS_NORTHERN, 2, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/realms_yarpen.jpg").toExternalForm())),


    //Nilfgaard Faction:
    ImperaBrigadeGuard("Impera Brigade Guard", FactionsName.EMPIRE_NILFGARDEN, 3, 4, CardType.CLOSE_COMBAT, false, Ability.TIGHT_BOND,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_imperal_brigade.jpg").toExternalForm())),
    StefanSkellen("Stefan Skellen", FactionsName.EMPIRE_NILFGARDEN, 9, 1, CardType.CLOSE_COMBAT, false, Ability.SPY,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_stefan.jpg").toExternalForm())),
    ShilardFitzOesterlen("Shilard Fitz Oesterlen", FactionsName.EMPIRE_NILFGARDEN, 7, 1, CardType.CLOSE_COMBAT, false, Ability.SPY,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_shilard.jpg").toExternalForm())),
    YoungEmissary("Young Emissary", FactionsName.EMPIRE_NILFGARDEN, 5, 2, CardType.CLOSE_COMBAT, false, Ability.TIGHT_BOND,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_young_emissary.jpg").toExternalForm())),
    CahirMawrDyffrynAepCeallach("Cahir Mawr Dyffryn Aep Ceallach", FactionsName.EMPIRE_NILFGARDEN, 6, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_cahir.jpg").toExternalForm())),
    VattierDeRideaux("Vattier De Rideaux", FactionsName.EMPIRE_NILFGARDEN, 4, 1, CardType.CLOSE_COMBAT, false, Ability.SPY,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_vattier.jpg").toExternalForm())),
    MennoCoehorn("Menno Coehorn", FactionsName.EMPIRE_NILFGARDEN, 10, 1, CardType.CLOSE_COMBAT, true, Ability.MEDIC,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_menno.jpg").toExternalForm())),
    Puttkammer("Puttkammer", FactionsName.EMPIRE_NILFGARDEN, 3, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_puttkammer.jpg").toExternalForm())),
    AssireVarAnahid("Assire Var Anahid", FactionsName.EMPIRE_NILFGARDEN, 6, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_assire.jpg").toExternalForm())),
    BlackInfantryArcher("Black Infantry Archer", FactionsName.EMPIRE_NILFGARDEN, 10, 2, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_black_archer.jpg").toExternalForm())),
    TiborEggebracht("Tibor Eggebracht", FactionsName.EMPIRE_NILFGARDEN, 10, 1, CardType.RANGED_COMBAT, true, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_tibor.jpg").toExternalForm())),
    RenualdAepMatsen("Renuald Aep Matsen", FactionsName.EMPIRE_NILFGARDEN, 5, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_renuald.jpg").toExternalForm())),
    FringilaVigo("Fringilla Vigo", FactionsName.EMPIRE_NILFGARDEN, 6, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_fringilla.jpg").toExternalForm())),
    RottenMangonel("Rotten Mangonel", FactionsName.EMPIRE_NILFGARDEN, 3, 1, CardType.SIEGE, false, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_rotten.jpg").toExternalForm())),
    HeavyZerrikanianFireScorpion("Heavy Zerrikanian Fire Scorpion", FactionsName.EMPIRE_NILFGARDEN, 10, 1, CardType.SIEGE, false, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_heavy_zerri.jpg").toExternalForm())),
    ZerrikanianFireScorpion("Zerrikanian Fire Scorpion", FactionsName.EMPIRE_NILFGARDEN, 5, 1, CardType.SIEGE, false, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_zerri.jpg").toExternalForm())),
    SiegeEngineer("Siege Engineer", FactionsName.EMPIRE_NILFGARDEN, 6, 1, CardType.SIEGE, false, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_siege_engineer.jpg").toExternalForm())),
    MorvranVoohis("Morvran Voohis", FactionsName.EMPIRE_NILFGARDEN, 10, 1, CardType.SIEGE, true, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_moorvran.jpg").toExternalForm())),
    Albrich("Albrich", FactionsName.EMPIRE_NILFGARDEN, 2, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_albrich.jpg").toExternalForm())),
    Cynthia("Cynthia", FactionsName.EMPIRE_NILFGARDEN, 4, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_cynthia.jpg").toExternalForm())),
    EtolianAuxiliaryArchers("Etolian Auxiliary Archers", FactionsName.EMPIRE_NILFGARDEN, 1, 2, CardType.RANGED_COMBAT, false, Ability.MEDIC,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_archer_support.jpg").toExternalForm())),
    LethoOfGulet("Letho Of Gulet", FactionsName.EMPIRE_NILFGARDEN, 10, 1, CardType.CLOSE_COMBAT, true, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_letho.jpg").toExternalForm())),
    MennoCoehoorn("Menno Coehoorn", FactionsName.EMPIRE_NILFGARDEN, 10, 1, CardType.CLOSE_COMBAT, true, Ability.MEDIC,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_menno.jpg").toExternalForm())),
    Morteisen("Morteisen", FactionsName.EMPIRE_NILFGARDEN, 3, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_morteisen.jpg").toExternalForm())),
    MorvranVoorhis("Morvran Voorhis", FactionsName.EMPIRE_NILFGARDEN, 10, 1, CardType.SIEGE, true, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_moorvran.jpg").toExternalForm())),
    //    NausicaaCavalryRider("Nausicaa Cavalry Rider", FactionsName.EMPIRE_NILFGARDEN, 2, 3, CardType.CLOSE_COMBAT, false, Ability.TIGHT_BOND,
//            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_nauzicaa.jpg").toExternalForm())),
    Rainfarn("Rainfarn", FactionsName.EMPIRE_NILFGARDEN, 4, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_rainfarn.jpg").toExternalForm())),
    SiegeTechnician("Siege Technician", FactionsName.EMPIRE_NILFGARDEN, 0, 1, CardType.SIEGE, false, Ability.MEDIC,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_siege_support.jpg").toExternalForm())),
    Sweers("Sweers", FactionsName.EMPIRE_NILFGARDEN, 2, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_sweers.jpg").toExternalForm())),
    Vanhemar("Vanhemar", FactionsName.EMPIRE_NILFGARDEN, 2, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_vanhemar.jpg").toExternalForm())),
    Vreemde("Vreemde", FactionsName.EMPIRE_NILFGARDEN, 2, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/nilfgaard_vreemde.jpg").toExternalForm())),


    //Monster
    Draug("Draug", FactionsName.MONSTER, 10, 1, CardType.CLOSE_COMBAT, true, null,
            new Image(Card.class.getResource("/asset/img/lg/monsters_draug.jpg").toExternalForm())),
    Imlerith("Imlerith", FactionsName.MONSTER, 10, 1, CardType.CLOSE_COMBAT, true, null,
            new Image(Card.class.getResource("/asset/img/lg/monsters_imlerith.jpg").toExternalForm())),
    Leshen("Leshen", FactionsName.MONSTER, 10, 1, CardType.CLOSE_COMBAT, true, null,
            new Image(Card.class.getResource("/asset/img/lg/monsters_leshen.jpg").toExternalForm())),
    Kayran("Kayran", FactionsName.MONSTER, 8, 1, CardType.AGILE, true, Ability.MORALBOOST,
            new Image(Card.class.getResource("/asset/img/lg/monsters_kayran.jpg").toExternalForm())),
    Toad("Toad", FactionsName.MONSTER, 7, 1, CardType.RANGED_COMBAT, false, Ability.SCROCH,
            new Image(Card.class.getResource("/asset/img/lg/monsters_toad.jpg").toExternalForm())),
    ArachasBehemoth("Arachas Behemoth", FactionsName.MONSTER, 6, 1, CardType.SIEGE, false, Ability.MUSTER,
            new Image(Card.class.getResource("/asset/img/lg/monsters_arachas_behemoth.jpg").toExternalForm())),
    CroneBrewess("Crone Brewess", FactionsName.MONSTER, 6, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER,
            new Image(Card.class.getResource("/asset/img/lg/monsters_witch_velen.jpg").toExternalForm())),
    CroneWeavess("Crone Weavess", FactionsName.MONSTER, 6, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER,
            new Image(Card.class.getResource("/asset/img/lg/monsters_witch_velen_1.jpg").toExternalForm())),
    CroneWhispess("Crone Whispess", FactionsName.MONSTER, 6, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER,
            new Image(Card.class.getResource("/asset/img/lg/monsters_witch_velen_2.jpg").toExternalForm())),
    EarthElemental("Earth Elemental", FactionsName.MONSTER, 6, 1, CardType.SIEGE, false, null,
            new Image(Card.class.getResource("/asset/img/lg/monsters_earth_elemental.jpg").toExternalForm())),
    Fiend("Fiend", FactionsName.MONSTER, 6, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/monsters_fiend.jpg").toExternalForm())),
    FireElemental("Fire Elemental", FactionsName.MONSTER, 6, 1, CardType.SIEGE, false, null,
            new Image(Card.class.getResource("/asset/img/lg/monsters_fire_elemental.jpg").toExternalForm())),
    Forktail("Forktail", FactionsName.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/monsters_forktail.jpg").toExternalForm())),
    Frightener("Frightener", FactionsName.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/monsters_frightener.jpg").toExternalForm())),
    GraveHag("Grave Hag", FactionsName.MONSTER, 5, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/monsters_graveHag.jpg").toExternalForm())),
    Griffin("Griffin", FactionsName.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/monsters_gryffin.jpg").toExternalForm())),
    IceGiant("Ice Giant", FactionsName.MONSTER, 5, 1, CardType.SIEGE, false, null,
            new Image(Card.class.getResource("/asset/img/lg/monsters_frost_giant.jpg").toExternalForm())),
    PlagueMaiden("Plague Maiden", FactionsName.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/monsters_mighty_maiden.jpg").toExternalForm())),
    VampireKatakan("Vampire Katakan", FactionsName.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER,
            new Image(Card.class.getResource("/asset/img/lg/monsters_katakan.jpg").toExternalForm())),
    Werewolf("Werewolf", FactionsName.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/monsters_werewolf.jpg").toExternalForm())),
    Arachas("Arachas", FactionsName.MONSTER, 5, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER,
            new Image(Card.class.getResource("/asset/img/lg/monsters_arachas.jpg").toExternalForm())),
    Botchling("Botchling", FactionsName.MONSTER, 4, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/monsters_poroniec.jpg").toExternalForm())),
    VampireBruxa("Vampire Bruxa", FactionsName.MONSTER, 4, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER,
            new Image(Card.class.getResource("/asset/img/lg/monsters_bruxa.jpg").toExternalForm())),
    VampireEkimmara("Vampire Ekimmara", FactionsName.MONSTER, 4, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER,
            new Image(Card.class.getResource("/asset/img/lg/monsters_ekkima.jpg").toExternalForm())),
    VampireFleder("Vampire Fleder", FactionsName.MONSTER, 4, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER,
            new Image(Card.class.getResource("/asset/img/lg/monsters_fleder.jpg").toExternalForm())),
    VampireGarkain("Vampire Garkain", FactionsName.MONSTER, 4, 1, CardType.CLOSE_COMBAT, false, Ability.MUSTER,
            new Image(Card.class.getResource("/asset/img/lg/monsters_garkain.jpg").toExternalForm())),
    CelaenoHarpy("Celaeno Harpy", FactionsName.MONSTER, 2, 1, CardType.AGILE, false, null,
            new Image(Card.class.getResource("/asset/img/lg/monsters_celaeno_harpy.jpg").toExternalForm())),
    Endrega("Endrega", FactionsName.MONSTER, 2, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/monsters_endrega.jpg").toExternalForm())),
    Foglet("Foglet", FactionsName.MONSTER, 2, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/monsters_fogling.jpg").toExternalForm())),
    Gargoyle("Gargoyle", FactionsName.MONSTER, 2, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/monsters_gargoyle.jpg").toExternalForm())),
    Harpy("Harpy", FactionsName.MONSTER, 2, 1, CardType.AGILE, false, null,
            new Image(Card.class.getResource("/asset/img/lg/monsters_harpy.jpg").toExternalForm())),
    Nekker("Nekker", FactionsName.MONSTER, 2, 3, CardType.CLOSE_COMBAT, false, Ability.MUSTER,
            new Image(Card.class.getResource("/asset/img/lg/monsters_nekker.jpg").toExternalForm())),
    Wyvern("Wyvern", FactionsName.MONSTER, 2, 1, CardType.RANGED_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/monsters_wyvern.jpg").toExternalForm())),
//    Ghoul("Ghoul", FactionsName.MONSTER, 1, 3, CardType.CLOSE_COMBAT, false, Ability.MUSTER,
//            new Image(Card.class.getResource("/asset/img/lg/monsters_ghoul_.jpg").toExternalForm())),


    //neutral
    BitingForest("Biting Forest", FactionsName.NEUTRAL, 1, 3, CardType.WEATHER, false, null,
            new Image(Card.class.getResource("/asset/img/lg/weather_frost.jpg").toExternalForm())),
    ImpenetrableFog("Impenetrable Fog", FactionsName.NEUTRAL, 1, 3, CardType.WEATHER, false, null,
            new Image(Card.class.getResource("/asset/img/lg/weather_fog.jpg").toExternalForm())),
    TorrentialRain("Torrential Rain", FactionsName.NEUTRAL, 1, 3, CardType.WEATHER, false, null,
            new Image(Card.class.getResource("/asset/img/lg/weather_rain.jpg").toExternalForm())),
    SkelligeStorm("Skellige Storm", FactionsName.NEUTRAL, 1, 3, CardType.WEATHER, false, null,
            new Image(Card.class.getResource("/asset/img/lg/weather_storm.jpg").toExternalForm())),
    ClearWeather("Clear Weather", FactionsName.NEUTRAL, 1, 3, CardType.WEATHER, false, null,
            new Image(Card.class.getResource("/asset/img/lg/weather_clear.jpg").toExternalForm())),
    Scorch("Scorch", FactionsName.NEUTRAL, 1, 3, CardType.SPELL, false, null,
            new Image(Card.class.getResource("/asset/img/lg/special_scorch.jpg").toExternalForm())),
    CommanderHorn("Commander’s Horn", FactionsName.NEUTRAL, 1, 3, CardType.SPELL, false, null,
            new Image(Card.class.getResource("/asset/img/lg/special_horn.jpg").toExternalForm())),
    Decoy("Decoy", FactionsName.NEUTRAL, 1, 3, CardType.SPELL, false, null,
            new Image(Card.class.getResource("/asset/img/lg/special_decoy.jpg").toExternalForm())),
    Dandelion("Dandelion", FactionsName.NEUTRAL, 2, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/neutral_dandelion.jpg").toExternalForm())),
    Cow("Cow", FactionsName.NEUTRAL, 0, 1, CardType.RANGED_COMBAT, false, Ability.COMMANDER_HORN,
            new Image(Card.class.getResource("/asset/img/lg/neutral_cow.jpg").toExternalForm())),
    EmielRegis("Emiel Regis", FactionsName.NEUTRAL, 5, 1, CardType.CLOSE_COMBAT, false, Ability.TRANSFORMERS,
            new Image(Card.class.getResource("/asset/img/lg/neutral_emiel.jpg").toExternalForm())),
    //    GaunterODimm("Gaunter O’Dimm", FactionsName.NEUTRAL, 2, 1, CardType.SIEGE, false, Ability.MUSTER,
//            new Image(Card.class.getResource("/asset/img/lg/neutral_.jpg").toExternalForm())),
    GaunterODImmDarkness("Gaunter O’DImm Darkness", FactionsName.NEUTRAL, 4, 3, CardType.RANGED_COMBAT, false, Ability.MUSTER,
            new Image(Card.class.getResource("/asset/img/lg/neutral_gaunter_odimm.jpg").toExternalForm())),
    GeraltOfRivia("Geralt of Rivia", FactionsName.NEUTRAL, 15, 1, CardType.CLOSE_COMBAT, true, null,
            new Image(Card.class.getResource("/asset/img/lg/neutral_gaunter_odimm_darkness.jpg").toExternalForm())),
    MysteriousElf("Mysterious Elf", FactionsName.NEUTRAL, 0, 1, CardType.CLOSE_COMBAT, true, Ability.SPY,
            new Image(Card.class.getResource("/asset/img/lg/neutral_mysterious_elf.jpg").toExternalForm())),
    OlgierdVonEverc("Olgierd Von Everc", FactionsName.NEUTRAL, 6, 1, CardType.AGILE, false, Ability.MORALBOOST,
            new Image(Card.class.getResource("/asset/img/lg/neutral_olgierd.jpg").toExternalForm())),
    TrissMerigold("Triss Merigold", FactionsName.NEUTRAL, 7, 1, CardType.CLOSE_COMBAT, true, null,
            new Image(Card.class.getResource("/asset/img/lg/neutral_triss.jpg").toExternalForm())),
    Vesemir("Vesemir", FactionsName.NEUTRAL, 6, 1, CardType.CLOSE_COMBAT, true, null,
            new Image(Card.class.getResource("/asset/img/lg/neutral_vesemir.jpg").toExternalForm())),
    Villentretenmerth("Villentretenmerth", FactionsName.NEUTRAL, 7, 1, CardType.CLOSE_COMBAT, false, Ability.SCROCH,
            new Image(Card.class.getResource("/asset/img/lg/neutral_villen.jpg").toExternalForm())),
    YenneferOfVengerberg("Yennefer of Vengerberg", FactionsName.NEUTRAL, 7, 1, CardType.RANGED_COMBAT, true, Ability.MEDIC,
            new Image(Card.class.getResource("/asset/img/lg/neutral_yennefer.jpg").toExternalForm())),
    ZoltanChivay("Zoltan Chivay", FactionsName.NEUTRAL, 5, 1, CardType.CLOSE_COMBAT, false, null,
            new Image(Card.class.getResource("/asset/img/lg/neutral_zoltan.jpg").toExternalForm()));

    private final FactionsName factionsName;

    private final int power;
    private final int numberOfCardInGame;
    private final CardType type;
    private final boolean isHero;
    private final Ability ability;
    private final Image image;

    Card(String name, FactionsName factionsName, int power, int numberOfCardInGame, CardType type, boolean isHero, Ability ability, Image image) {
        this.factionsName = factionsName;
        this.power = power;
        this.numberOfCardInGame = numberOfCardInGame;
        this.type = type;
        this.isHero = isHero;
        this.ability = ability;
        this.image = image;
    }

    public static Card getCardByName(String name) {
        for (Card card : Card.values()) {
            if (card.getName().equals(name))
                return card;
        }
        return null;
    }

    public static Card getCardByImage(Image image) {
        for (Card card : Card.values()) {
            if (card.getImage().equals(image))
                return card;
        }
        return null;
    }

    public Image getImage() {
        return image;
    }

    public FactionsName getFaction() {
        return factionsName;
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
    public static Result commandsHorn(UserInGame current, int rowNumber) {
        if (rowNumber == 1) {
            current.setSiegeDouble(true);
        } else if (rowNumber == 2) {
            current.setRangedDouble(true);
        } else if (rowNumber == 3) {
            current.setCloseCombatDouble(true);
        }
        return new Result(true, "command horn changed");
    }

    public static Result medic(UserInGame current) {
        if (current.getGameTable().getDeadCards().isEmpty())
            return new Result(false, "no card in dead");

        ArrayList<Card> dead = current.getGameTable().getDeadCards();
        ArrayList<Card> hand = current.getGameTable().getInHandsCards();
        Card card = dead.get(0);
        hand.add(card);
        return new Result(true, "one card from discard passed to hand");
    }

    public static Result moralBoost(UserInGame current, Card card, int rowNumber) {
        return null;
    }

    public static Result muster(UserInGame current, Card card, int rowNumber) {
        ArrayList<Card> cardInRow = current.getGameTable().getCardsOfRow()[rowNumber - 1].getSecond();
        ArrayList<Card> deck = current.getGameTable().getDeckCards();

        for (int i = deck.size() - 1; i >= 0; i--) {
            if (deck.get(i) == card){
                cardInRow.add(card);
                deck.remove(i);
            }
        }

        return new Result(true, "removed successfully");
    }

    public static Result spy(UserInGame current) {
        ArrayList<Card> deck = current.getGameTable().getDeckCards();
        ArrayList<Card> hand = current.getGameTable().getDeckCards();

        int n = deck.size();
        hand.add(deck.get(n-1));
        hand.add(deck.get(n-2));
        deck.remove(n-1);
        deck.remove(n-2);

        return new Result(true, "removed successfully");
    }

    public static Result tightBond() {
        //TODO HARD
        return null;
    }

    public static Result scroch(UserInGame current, UserInGame opponent) {
        Card cardWithHighest = null;
        int rowOfPlace = -1;
        UserInGame user = current;
        for (int i = 0; i < 3; i++) {
            ArrayList<Card> cards = current.getGameTable().getCardsOfRow()[i].getSecond();
            if (cardWithHighest == null && !cards.isEmpty()) {
                cardWithHighest = cards.get(0);
            }
            for (int j = 0; j < cards.size(); j++) {
                if (cardWithHighest.getPower() < cards.get(j).getPower()){
                    cardWithHighest = cards.get(j);
                    rowOfPlace = i;
                    user = current;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            ArrayList<Card> cards = opponent.getGameTable().getCardsOfRow()[i].getSecond();
            if (cardWithHighest == null && !cards.isEmpty()) {
                cardWithHighest = cards.get(0);
            }
            for (int j = 0; j < cards.size(); j++) {
                if (cardWithHighest.getPower() < cards.get(j).getPower()){
                    cardWithHighest = cards.get(j);
                    rowOfPlace = i;
                    user = opponent;
                }
            }
        }
        ArrayList<Card> cards = user.getGameTable().getCardsOfRow()[rowOfPlace].getSecond();
        cards.remove(cardWithHighest);
        return new Result(true, "card removed successfully");
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