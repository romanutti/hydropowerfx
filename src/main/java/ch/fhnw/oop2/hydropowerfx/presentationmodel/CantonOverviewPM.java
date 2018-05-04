package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import ch.fhnw.oop2.hydropowerfx.domain.Canton;

public interface CantonOverviewPM {
    double getTotalPower(Canton canton);

    int getPowerStationCount(Canton canton);
}
