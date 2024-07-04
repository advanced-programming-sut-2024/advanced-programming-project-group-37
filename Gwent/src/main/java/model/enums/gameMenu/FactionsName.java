package model.enums.gameMenu;

public enum FactionsName {
    MONSTER("Monster"),
    EMPIRE_NILFGARDEN("Empire NilfGaarden"),
    REALMS_NORTHERN("Realm Northern"),
    SCOIATEAL("Scoiateal"),
    SKELLIGE("Skellige"),
    NEUTRAL("Neutral");
    private String name;
    FactionsName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
