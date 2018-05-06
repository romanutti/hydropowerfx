package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import ch.fhnw.oop2.hydropowerfx.domain.PowerStation;
import javafx.beans.property.IntegerProperty;

public interface PowerStationOverviewPM {

    int getCurrentPowerStationIndex();

    void setCurrentPowerStation(int currentPowerStationIndex);

    void addPowerStation(PowerStation powerStation);

    void removePowerStation(PowerStation powerStation);

    PowerStation getCurrentPowerStation();

    IntegerProperty size();

}

