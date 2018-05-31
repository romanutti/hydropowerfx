package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import java.util.HashMap;
import java.util.Map;

public enum Canton {
    AG("Aargau"),
    AI("Appenzell Inerrhoden"),
    AR("Appenzell Ausserrhoden"),
    BE("Bern"),
    BL("Basel-Landschaft"),
    BS("Basel-Stadt"),
    FR("Freiburg"),
    GE("Genf"),
    GL("Glarus"),
    GR("Graubünden"),
    JU("Jura"),
    LU("Luzern"),
    NE("Neuenburg"),
    NW("Nidwalden"),
    OTHER("Andere"),
    OW("Obwalden"),
    SG("St. Gallen"),
    SH("Schaffhausen"),
    SO("Solothurn"),
    SZ("Schwyz"),
    TG("Thurgau"),
    TI("Tessin"),
    UR("Uri"),
    VD("Waadt"),
    VS("Valais"),
    ZG("Zug"),
    ZH("Zürich");


    private final String name;
    private static final Map<String, Canton> lookup = new HashMap<String, Canton>();

    static {
        for (Canton c : Canton.values()) {
            lookup.put(c.getName(), c);
        }
    }

    Canton(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Canton getCanton(String name) {
        return lookup.get(name);
    }

}