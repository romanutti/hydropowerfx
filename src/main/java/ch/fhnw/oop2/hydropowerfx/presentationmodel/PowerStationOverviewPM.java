package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import ch.fhnw.oop2.hydropowerfx.domain.PowerStationPM;
import javafx.beans.property.IntegerProperty;

public interface PowerStationOverviewPM {

    int getCurrentPowerStationIndex();

    void setCurrentPowerStation(int currentPowerStationIndex);

    void addPowerStation(PowerStationPM powerStation);

    void removePowerStation(PowerStationPM powerStation);

    PowerStationPM getCurrentPowerStation();

    IntegerProperty size();

}

