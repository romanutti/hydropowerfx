package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import ch.fhnw.oop2.hydropowerfx.domain.Canton;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import ch.fhnw.oop2.hydropowerfx.domain.PowerStation;

public interface PowerStationDetailPM {
    StringProperty getName();

    void setName(StringProperty name);

    // TODO: Enum als eigene Klasse?
    ObjectProperty<PowerStation.Type> getType();

    void setType(ObjectProperty<PowerStation.Type> type);

    StringProperty getSite();

    void setSite(StringProperty site);

    ObjectProperty<Canton> getCanton();

    void setCanton(Canton canton);

    DoubleProperty getMaxWaterVolume();

    void setMaxWaterVolume(DoubleProperty maxWaterVolume);

    DoubleProperty getMaxPowerMw();

    void setMaxPowerMw(DoubleProperty maxPowerMw);

    DoubleProperty getStartOfOperationFirst();

    void setStartOfOperationFirst(DoubleProperty startOfOperationFirst);

    DoubleProperty getStartOfOperationLast();

    void setStartOfOperationLast(DoubleProperty startOfOperationLast);

    DoubleProperty getLatitude();

    void setLatitude(DoubleProperty latitude);

    DoubleProperty getLongitude();

    void setLongitude(DoubleProperty longitude);

    // TODO: Status als enum?
    StringProperty getStatus();

    void setStatus(StringProperty status);

    DoubleProperty getWaterbodies();

    void setWaterbodies(DoubleProperty waterbodies);

    StringProperty getImageUrl();

    void setImageUrl(StringProperty imageUrl);

}


