package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RootPM {
    // TODO: Wie Powerstations ohne Kanton behandeln?
    private static final String FILE_NAME = "/data/HYDRO_POWERSTATION.csv";
    private static final String DELIMITER = ";";

    private final ObservableList<PowerStationPM> allPowerStations = FXCollections.observableArrayList();
    private final IntegerProperty selectedId = new SimpleIntegerProperty();
    private final LanguageSwitcherPM languageSwitcherPM;

    public RootPM() {
        allPowerStations.addAll(readFromFile());
        // TODO: Prüfen, ob Wert vorhanden
        //Platform.runLater(() -> setSelectedId(100100));
        this.languageSwitcherPM = new LanguageSwitcherPM();
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

    private List<PowerStationPM> readFromFile() {
        try (Stream<String> stream = getStreamOfLines(FILE_NAME)) {
            return stream.skip(1)
                    .map(line -> new PowerStationPM(line.split(DELIMITER, 22)))
                    .collect(Collectors.toList());
        }
    }

    public void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(getPath(FILE_NAME))) {
            writer.write("ENTITY_ID;NAME;TYPE;SITE;CANTON;MAX_WATER_VOLUME_M3_S;MAX_POWER_MW;START_OF_OPERATION_FIRST;START_OF_OPERATION_LAST;LATITUDE;LONGITUDE;STATUS;WATERBODIES;IMAGE_URL");
            writer.newLine();
            allPowerStations.stream()
                    .map(powerStation -> powerStation.infoAsLine(DELIMITER))
                    .forEach(line -> {
                        try {
                            writer.write(line);
                            writer.newLine();
                        } catch (IOException e) {
                            throw new IllegalStateException(e);
                        }
                    });
        } catch (IOException e) {
            throw new IllegalStateException("save failed");
        }
    }

    public ObservableList<PowerStationPM> getAllPowerStations() {
        return allPowerStations;
    }

    // all getters and setters

    public int getSelectedId() {
        return selectedId.get();
    }

    public IntegerProperty selectedIdProperty() {
        return selectedId;
    }

    public void setSelectedId(int selectedPowerStationId) {
        this.selectedId.set(selectedPowerStationId);
    }

    public LanguageSwitcherPM getLanguageSwitcherPM() {
        return languageSwitcherPM;
    }

    // overview methods
    public void addPowerStation(PowerStationPM powerStation) {
        allPowerStations.add(powerStation);
    }


    public void removePowerStation(PowerStationPM powerStation) {
        allPowerStations.remove(powerStation);
    }

    public PowerStationPM getPowerStation(int id) {
        return allPowerStations.stream()
                .filter(powerStation -> powerStation.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public IntegerProperty size() {
        return new SimpleIntegerProperty(allPowerStations.size());
    }

    // footer methods
    public ObservableList<Canton> getAllCantons(){
        return FXCollections.observableArrayList(allPowerStations.stream()
                .map(powerStationPM -> powerStationPM.getCanton())
                .distinct()
                .collect(Collectors.toList()));    }

    public DoubleProperty getTotalPower(Canton canton) {
        double result = allPowerStations.stream()
                .filter(powerStation -> powerStation.getCanton().equals(canton))
                .collect(Collectors.summingDouble(PowerStationPM::getMaxPowerMw));
        return new SimpleDoubleProperty(result);
    }

    public IntegerProperty getPowerStationCount(Canton canton) {
        int result = (int) allPowerStations.stream()
                .filter(powerStation -> powerStation.getCanton().equals(canton))
                .count();
        return new SimpleIntegerProperty(result);
    }

}
