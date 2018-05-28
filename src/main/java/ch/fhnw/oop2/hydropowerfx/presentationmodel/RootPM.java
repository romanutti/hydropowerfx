package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

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
    private static final String FILE_NAME = "/data/HYDRO_POWERSTATION.csv";
    private static final String DELIMITER = ";";

    private final ObservableList<PowerStationPM> allPowerStations = FXCollections.observableArrayList();
    private final IntegerProperty selectedId = new SimpleIntegerProperty();
    private final PowerStationPM powerStationProxy = new PowerStationPM();

    private final FilteredList<PowerStationPM> filteredPowerStations;

    private final LanguageSwitcherPM languageSwitcherPM;
    private final BooleanProperty labelsEnabled = new SimpleBooleanProperty();


    public RootPM() {
        init(createAllPowerStations());
        this.languageSwitcherPM = new LanguageSwitcherPM();

        // TODO: DRY
        filteredPowerStations =  new FilteredList<>(allPowerStations, p -> true);
    }

    public RootPM(List<PowerStationPM> powerStationList) {
        init(powerStationList);
        this.languageSwitcherPM = new LanguageSwitcherPM();

        // TODO: DRY
        filteredPowerStations =  new FilteredList<>(allPowerStations, p -> true);
    }

    private void init(List<PowerStationPM> powerStationList){
        allPowerStations.addAll(powerStationList);
        Platform.runLater(() -> setSelectedId(getFirstPowerStation()));

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



    // all getters and setters


    public ObservableList<PowerStationPM> getAllPowerStations() {
        return allPowerStations;
    }

    public FilteredList<PowerStationPM> getFilteredPowerStations() {
        return filteredPowerStations;
    }

    public PowerStationPM getPowerStationProxy() {
        return powerStationProxy;
    }

    public ObservableList<PowerStationPM> allCountries() {
        return allPowerStations;
    }

    public int getSelectedId() {
        return selectedId.get();
    }

    public IntegerProperty selectedIdProperty() {
        return selectedId;
    }


    public LanguageSwitcherPM getLanguageSwitcherPM() {
        return languageSwitcherPM;
    }

    public void setSelectedId(int selectedPowerStationId) {
        this.selectedId.set(selectedPowerStationId);

        if (selectedPowerStationId == 0){
            setLabelsEnabled(true);
        } else {
            setLabelsEnabled(false);
        }
    }

    public boolean isLabelsEnabled() {
        return labelsEnabled.get();
    }

    public BooleanProperty labelsEnabledProperty() {
        return labelsEnabled;
    }

    public void setLabelsEnabled(boolean labelsEnabled) {
        this.labelsEnabled.set(labelsEnabled);
    }

    public int getIndexOfPowerStation(int id){
        return getAllPowerStations().indexOf(getPowerStation(id));
    }

    // overview methods
    public void addPowerStation() {
        String line[] = new String[]{
                "999999",
                "A",
                "L",
                "Rueras",
                "GR",
                "0.43",
                "1.42",
                "1979",
                "1979",
                "46.67133138",
                "8.75072906",
                "im Normalbetrieb",
                "Aua da Milez",
                "www.hydro.ch/images"};
        PowerStationPM newItem = new PowerStationPM(line);
        newItem.setId(getNewPowerStationID());
        allPowerStations.add(newItem);
        setSelectedId(newItem.getId());
    }



    public void removePowerStation() {
        if (getSelectedId() != 0) {
            allPowerStations.remove(allPowerStations.indexOf(getPowerStation(getSelectedId())));
            setSelectedId(0);
        }
    }


    public PowerStationPM getPowerStation(int id) {
        return allPowerStations.stream()
                .filter(powerStation -> powerStation.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public int getFirstPowerStation() {
        return allPowerStations.stream()
                .map(powerStationPM -> powerStationPM.getId())
                .findFirst()
                .orElse(null);
    }

    public int getNewPowerStationID(){
        int newID = allPowerStations.stream()
                .mapToInt(powerStation -> powerStation.getId())
                .reduce(Integer::max)
                .getAsInt();

        while (getPowerStation(newID) != null){
            ++newID;
        }

        return newID;
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
