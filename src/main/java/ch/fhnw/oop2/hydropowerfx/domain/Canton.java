package ch.fhnw.oop2.hydropowerfx.domain;

public enum Canton {
    ZH("Zürich"),
    BE("Bern"),
    LU("Luzern"),
    UR("Uri"),
    SZ("Schwyz"),
    OW("Obwalden"),
    NW("Nidwalden"),
    GL("Glarus"),
    ZG("Zug"),
    FR("Freburg"),
    BS("Basel-Stadt"),
    BL("Basel-Landschaft"),
    SH("Schaffhausen"),
    AR("Appenzell Ausserrhoden"),
    AI("Appenzell Inerrhoden"),
    SG("St. Gallen"),
    TI("Tessin"),
    GE("Genf"),
    JU("Jura");

    private final String name;

    Canton(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}