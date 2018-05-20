package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import javafx.application.Platform;
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
    private final PowerStationPM powerStationProxy = new PowerStationPM();
    private final LanguageSwitcherPM languageSwitcherPM;

    public RootPM() {
        init(createAllPowerStations());
        this.languageSwitcherPM = new LanguageSwitcherPM();

    }

    public RootPM(List<PowerStationPM> powerStationList) {
        init(powerStationList);
        this.languageSwitcherPM = new LanguageSwitcherPM();
    }

    private void init(List<PowerStationPM> powerStationList){
        allPowerStations.addAll(powerStationList);
        // TODO: Prüfen, ob Wert vorhanden
        Platform.runLater(() -> setSelectedId(100100));

        selectedIdProperty().addListener((observable, oldValue, newValue) -> {
                    PowerStationPM oldSelection = getPowerStation(oldValue.intValue());
                    PowerStationPM newSelection = getPowerStation(newValue.intValue());

                    if (oldSelection != null) {
                        unbindFromProxy(oldSelection);
                    }

                    if (newSelection != null) {
                        bindToProxy(newSelection);
                    }
                }
        );
        }

    private List<PowerStationPM> createAllPowerStations() {
        return readFromFile();
    }

    public ObservableList<PowerStationPM> allCountries() {
        return allPowerStations;
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

    public PowerStationPM getPowerStationProxy() {
        return powerStationProxy;
    }

    private void bindToProxy(PowerStationPM powerStation) {
        powerStationProxy.idProperty()  .bindBidirectional(powerStation.idProperty());
        powerStationProxy.nameProperty().bindBidirectional(powerStation.nameProperty());
        powerStationProxy.typeProperty().bindBidirectional(powerStation.typeProperty());
        powerStationProxy.siteProperty().bindBidirectional(powerStation.siteProperty());
        powerStationProxy.cantonProperty().bindBidirectional(powerStation.cantonProperty());
        powerStationProxy.maxWaterVolumeProperty().bindBidirectional(powerStation.maxWaterVolumeProperty());
        powerStationProxy.maxPowerMwProperty().bindBidirectional(powerStation.maxPowerMwProperty());
        powerStationProxy.startOfOperationFirstProperty().bindBidirectional(powerStation.startOfOperationFirstProperty());
        powerStationProxy.startOfOperationLastProperty().bindBidirectional(powerStation.startOfOperationLastProperty());
        powerStationProxy.latitudeProperty().bindBidirectional(powerStation.latitudeProperty());
        powerStationProxy.longitudeProperty().bindBidirectional(powerStation.longitudeProperty());
        powerStationProxy.statusProperty().bindBidirectional(powerStation.statusProperty());
        powerStationProxy.waterbodiesProperty().bindBidirectional(powerStation.waterbodiesProperty());
        powerStationProxy.imageUrlProperty().bindBidirectional(powerStation.imageUrlProperty());
    }

    private void unbindFromProxy(PowerStationPM powerStation) {
        powerStationProxy.idProperty()  .unbindBidirectional(powerStation.idProperty());
        powerStationProxy.nameProperty().unbindBidirectional(powerStation.nameProperty());
        powerStationProxy.typeProperty().unbindBidirectional(powerStation.typeProperty());
        powerStationProxy.siteProperty().unbindBidirectional(powerStation.siteProperty());
        powerStationProxy.cantonProperty().unbindBidirectional(powerStation.cantonProperty());
        powerStationProxy.maxWaterVolumeProperty().unbindBidirectional(powerStation.maxWaterVolumeProperty());
        powerStationProxy.maxPowerMwProperty().unbindBidirectional(powerStation.maxPowerMwProperty());
        powerStationProxy.startOfOperationFirstProperty().unbindBidirectional(powerStation.startOfOperationFirstProperty());
        powerStationProxy.startOfOperationLastProperty().unbindBidirectional(powerStation.startOfOperationLastProperty());
        powerStationProxy.latitudeProperty().unbindBidirectional(powerStation.latitudeProperty());
        powerStationProxy.longitudeProperty().unbindBidirectional(powerStation.longitudeProperty());
        powerStationProxy.statusProperty().unbindBidirectional(powerStation.statusProperty());
        powerStationProxy.waterbodiesProperty().unbindBidirectional(powerStation.waterbodiesProperty());
        powerStationProxy.imageUrlProperty().unbindBidirectional(powerStation.imageUrlProperty());
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
