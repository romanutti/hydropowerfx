package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import ch.fhnw.oop2.hydropowerfx.domain.Canton;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;

public interface CantonOverviewPM {
    DoubleProperty getTotalPower(Canton canton);

    IntegerProperty getPowerStationCount(Canton canton);
}
