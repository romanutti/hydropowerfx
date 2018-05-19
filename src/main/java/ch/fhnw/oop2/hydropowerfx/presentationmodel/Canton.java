package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Map;

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
    FR("Freiburg"),
    SO("Solothurn"),
    BS("Basel-Stadt"),
    BL("Basel-Landschaft"),
    SH("Schaffhausen"),
    AR("Appenzell Ausserrhoden"),
    AI("Appenzell Inerrhoden"),
    SG("St. Gallen"),
    GR("Graubünden"),
    AG("Aargau"),
    TG("Thurgau"),
    TI("Tessin"),
    VD("Waadt"),
    VS("Valais"),
    NE("Neuenburg"),
    GE("Genf"),
    JU("Jura");

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