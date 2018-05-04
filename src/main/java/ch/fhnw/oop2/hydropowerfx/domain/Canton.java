package ch.fhnw.oop2.hydropowerfx.domain;

public enum Canton {
    ZH("Zürich"),
    BE("Bern"),
    LU("Luzern"),
    UR("Uri"),
    SZ("Schwyz"),
    OW("Obwalden"),
    NW("Nidwalden");
    // TODO: Liste abschliessen

    private final String name;

    Canton(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}