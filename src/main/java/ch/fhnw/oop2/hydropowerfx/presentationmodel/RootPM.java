package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import ch.fhnw.oop2.hydropowerfx.domain.Canton;
import ch.fhnw.oop2.hydropowerfx.domain.PowerStation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RootPM implements PowerStationOverviewPM, PowerStationDetailPM, CantonOverviewPM {
    private final StringProperty applicationTitle = new SimpleStringProperty("HydroPowerFX");
    private final StringProperty greeting         = new SimpleStringProperty("Hello World!");

    private List<PowerStation> allPowerStations = new ArrayList<>();
    private int currentPowerStationId;

    public RootPM(List<PowerStation> data) {
        this.allPowerStations = data;
    }

    // all getters and setters
    public String getApplicationTitle() {
        return applicationTitle.get();
    }

    public StringProperty applicationTitleProperty() {
        return applicationTitle;
    }

    public void setApplicationTitle(String applicationTitle) {
        this.applicationTitle.set(applicationTitle);
    }


    public String getGreeting() {
        return greeting.get();
    }

    public StringProperty greetingProperty() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting.set(greeting);
    }

    // powerstationoverviewpm methods
    public int getCurrentPowerStationIndex() {
        return currentPowerStationId;
    }

    public void setCurrentPowerStation(int currentPowerStationIndex) {
        this.currentPowerStationId=currentPowerStationIndex;
    }

    public void addPowerStation(PowerStation powerStation) {
        allPowerStations.add(powerStation);
    }

    public void removePowerStation(PowerStation powerStation) {
        allPowerStations.remove(powerStation);
    }

    public PowerStation getCurrentPowerStation(){
        return allPowerStations.stream()
                .filter(powerStation -> powerStation.getId()==this.currentPowerStationId)
                .findFirst()
                .orElse(null);
    }

    public int size() {
        return allPowerStations.size();
    }

    //powerstationdetailpm
    public StringProperty getName (){
        return new SimpleStringProperty(getCurrentPowerStation().getName());
    }

    public void setName (StringProperty name){
        getCurrentPowerStation().setName(name.toString());
    }

    // TODO: weitere Methoden

    //cantonoverviewpm
    public double getTotalPower(Canton canton) {
         return allPowerStations.stream()
                 .filter(powerStation -> powerStation.getCanton().equals(canton))
                 .collect(Collectors.summingDouble(PowerStation::getMaxPowerMw));
     }

    public int getPowerStationCount(Canton canton) {
        return (int) allPowerStations.stream()
                .filter(powerStation -> powerStation.getCanton().equals(canton))
                .count();
    }


}
