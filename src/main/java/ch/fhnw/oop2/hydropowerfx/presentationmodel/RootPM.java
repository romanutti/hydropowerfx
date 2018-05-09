package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import ch.fhnw.oop2.hydropowerfx.domain.Canton;
import ch.fhnw.oop2.hydropowerfx.domain.PowerStation;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RootPM implements PowerStationOverviewPM, CantonOverviewPM {
    // TODO: Datenfile in anderen Folder
    // TODO: PowersStations ohne Kanton (bisher einfach gelöscht in Liste)
    private static final String FILE_NAME = "HYDRO_POWERSTATION.csv";
    private static final String DELIMITER = ";";

    private final StringProperty applicationTitle = new SimpleStringProperty("HydroPowerFX");
    private final StringProperty greeting = new SimpleStringProperty("Hello World!");
    private final ObservableList<PowerStation> allPowerStations = FXCollections.observableArrayList();

    private int currentPowerStationId;


    public RootPM() {
        allPowerStations.addAll(readFromFile());
        currentPowerStationId = 0;
    }

    private Path getPath(String fileName) {
        try {
            return Paths.get(getClass().getResource(fileName).toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Stream<String> getStreamOfLines(String fileName) {
        try {
            return Files.lines(getPath(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<PowerStation> readFromFile() {
        try (Stream<String> stream = getStreamOfLines(FILE_NAME)) {
            return stream.skip(1)
                    .map(line -> new PowerStation(line.split(DELIMITER, 22)))
                    .collect(Collectors.toList());
        }
    }

    public void save() {
        //TODO
    }

    public ObservableList<PowerStation> getAllPowerStations() {
        return allPowerStations;
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
        // TODO: check if id exists
        this.currentPowerStationId = currentPowerStationIndex;
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
    public PowerStation getCurrentPowerStation() {
        return allPowerStations.stream()
                .filter(powerStation -> powerStation.getId() == this.currentPowerStationId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public IntegerProperty size() {
        return new SimpleIntegerProperty(allPowerStations.size());
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
