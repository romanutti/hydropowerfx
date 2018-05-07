package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import ch.fhnw.oop2.hydropowerfx.domain.Canton;
import ch.fhnw.oop2.hydropowerfx.domain.PowerStation;
import javafx.beans.property.*;

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
    @Override
    public int getCurrentPowerStationIndex() {
        return currentPowerStationId;
    }

    @Override
    public void setCurrentPowerStation(int currentPowerStationIndex) {
        this.currentPowerStationId=currentPowerStationIndex;
    }

    @Override
    public void addPowerStation(PowerStation powerStation) {
        allPowerStations.add(powerStation);
    }

    @Override
    public void removePowerStation(PowerStation powerStation) {
        allPowerStations.remove(powerStation);
    }

    @Override
    public PowerStation getCurrentPowerStation(){
        return allPowerStations.stream()
                .filter(powerStation -> powerStation.getId()==this.currentPowerStationId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public IntegerProperty size() {
        return new SimpleIntegerProperty(allPowerStations.size());
    }

    //powerstationdetailpm

    @Override
    public StringProperty getName() {
        return new SimpleStringProperty("Test");
    }

    @Override
    public void setName(StringProperty name) {

    }

    @Override
    public ObjectProperty<PowerStation.Type> getType() {
        return null;
    }

    @Override
    public void setType(ObjectProperty<PowerStation.Type> type) {

    }

    @Override
    public StringProperty getSite() {
        return null;
    }

    @Override
    public void setSite(StringProperty site) {

    }

    @Override
    public ObjectProperty<Canton> getCanton() {
        return null;
    }

    @Override
    public void setCanton(Canton canton) {

    }

    @Override
    public DoubleProperty getMaxWaterVolume() {
        return null;
    }

    @Override
    public void setMaxWaterVolume(DoubleProperty maxWaterVolume) {

    }

    @Override
    public DoubleProperty getMaxPowerMw() {
        return null;
    }

    @Override
    public void setMaxPowerMw(DoubleProperty maxPowerMw) {

    }

    @Override
    public DoubleProperty getStartOfOperationFirst() {
        return null;
    }

    @Override
    public void setStartOfOperationFirst(DoubleProperty startOfOperationFirst) {

    }

    @Override
    public DoubleProperty getStartOfOperationLast() {
        return null;
    }

    @Override
    public void setStartOfOperationLast(DoubleProperty startOfOperationLast) {

    }

    @Override
    public DoubleProperty getLatitude() {
        return null;
    }

    @Override
    public void setLatitude(DoubleProperty latitude) {

    }

    @Override
    public DoubleProperty getLongitude() {
        return null;
    }

    @Override
    public void setLongitude(DoubleProperty longitude) {

    }

    @Override
    public StringProperty getStatus() {
        return null;
    }

    @Override
    public void setStatus(StringProperty status) {

    }

    @Override
    public DoubleProperty getWaterbodies() {
        return null;
    }

    @Override
    public void setWaterbodies(DoubleProperty waterbodies) {

    }

    @Override
    public StringProperty getImageUrl() {
        return null;
    }

    @Override
    public void setImageUrl(StringProperty imageUrl) {

    }

    //cantonoverviewpm
    @Override
    public DoubleProperty getTotalPower(Canton canton) {
        double result = allPowerStations.stream()
                .filter(powerStation -> powerStation.getCanton().equals(canton))
                .collect(Collectors.summingDouble(PowerStation::getMaxPowerMw));
         return new SimpleDoubleProperty(result);
     }

    @Override
     public IntegerProperty getPowerStationCount(Canton canton) {
        int result = (int) allPowerStations.stream()
                .filter(powerStation -> powerStation.getCanton().equals(canton))
                .count();
        return new SimpleIntegerProperty(result);
    }


}
