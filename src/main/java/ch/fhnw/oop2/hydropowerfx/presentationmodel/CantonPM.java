package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import javafx.beans.property.*;

public class CantonPM {

    private final ObjectProperty<Canton> canton = new SimpleObjectProperty<>();
    private final DoubleProperty totalPowerMw = new SimpleDoubleProperty();
    private final IntegerProperty powerStationCount = new SimpleIntegerProperty();

    public CantonPM(Canton canton, Double totalPowerMw, Integer powerStationCount) {
        this.canton.setValue(canton);
        this.totalPowerMw.setValue(totalPowerMw);
        this.powerStationCount.setValue(powerStationCount);
    }

    public Canton getCanton() {
        return canton.get();
    }

    public ObjectProperty<Canton> cantonProperty() {
        return canton;
    }

    public void setCanton(Canton canton) {
        this.canton.set(canton);
    }

    public double getTotalPowerMw() {
        return totalPowerMw.get();
    }

    public DoubleProperty totalPowerMwProperty() {
        return totalPowerMw;
    }

    public void setTotalPowerMw(double totalPowerMw) {
        this.totalPowerMw.set(totalPowerMw);
    }

    public int getPowerStationCount() {
        return powerStationCount.get();
    }

    public IntegerProperty powerStationCountProperty() {
        return powerStationCount;
    }

    public void setPowerStationCount(int powerStationCount) {
        this.powerStationCount.set(powerStationCount);
    }
}
