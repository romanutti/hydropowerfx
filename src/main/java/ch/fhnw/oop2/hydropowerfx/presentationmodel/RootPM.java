package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RootPM {
    // constants
    private static final String FILE_NAME = "/data/HYDRO_POWERSTATION.csv";
    private static final String DELIMITER = ";";
    private static final String HEADER = "ENTITY_ID;NAME;TYPE;SITE;CANTON;MAX_WATER_VOLUME_M3_S;MAX_POWER_MW;START_OF_OPERATION_FIRST;START_OF_OPERATION_LAST;LATITUDE;LONGITUDE;STATUS;WATERBODIES;IMAGE_URL";

    // und/redo stacks
    private final ObservableList<Command> undoStack = FXCollections.observableArrayList();
    private final ObservableList<Command> redoStack = FXCollections.observableArrayList();

    // main data
    private final ObservableList<PowerStationPM> allPowerStations = FXCollections.observableArrayList(
            powerstation -> new Observable[]{powerstation.maxPowerMwProperty(), powerstation.cantonProperty()});
    private final ObservableList<CantonPM> allCantons = FXCollections.observableArrayList();
    private final IntegerProperty selectedId = new SimpleIntegerProperty();
    private final PowerStationPM powerStationProxy = PowerStationPM.getDefaultPowerStationPM();

    // filtered list for search
    private final FilteredList<PowerStationPM> filteredPowerStations;

    // mulitlanguage support
    private final LanguageSwitcherPM languageSwitcherPM;

    // input valitation
    private final BooleanProperty invalidInputEntered = new SimpleBooleanProperty();

    // enable disable of components
    private final BooleanProperty undoDisabled = new SimpleBooleanProperty();
    private final BooleanProperty redoDisabled = new SimpleBooleanProperty();
    private final BooleanProperty deleteEnabled = new SimpleBooleanProperty();
    private final BooleanProperty labelsEnabled = new SimpleBooleanProperty();

    private final ChangeListener propertyChangeListenerForUndoSupport = (observable, oldValue, newValue) -> {
        redoStack.clear();
        undoStack.add(0, new ValueChangeCommand(RootPM.this, (Property) observable, oldValue, newValue));
    };

    // constructors
    public RootPM() {
        // initialize data
        init(createAllPowerStations());

        languageSwitcherPM = new LanguageSwitcherPM();
        filteredPowerStations = new FilteredList<>(allPowerStations, p -> true);

        // initialize listener
        setupValueChangedListeners();
    }

    public RootPM(List<PowerStationPM> powerStationList) {
        // initialize data
        init(powerStationList);

        languageSwitcherPM = new LanguageSwitcherPM();
        filteredPowerStations = new FilteredList<>(allPowerStations, p -> true);

        // initialize listener
        setupValueChangedListeners();
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            return;
        }
        Command cmd = undoStack.get(0);
        undoStack.remove(0);
        redoStack.add(0, cmd);

        cmd.undo();
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            return;
        }
        Command cmd = redoStack.get(0);
        redoStack.remove(0);
        undoStack.add(0, cmd);

        cmd.redo();
    }

    public void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(getPath(FILE_NAME))) {
            writer.write(HEADER);
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

    private void init(List<PowerStationPM> powerStationList) {
        // add data
        allPowerStations.addAll(powerStationList);

        // enable components
        undoDisabled.bind(Bindings.isEmpty(undoStack));
        redoDisabled.bind(Bindings.isEmpty(redoStack));

        // setup proxy bindings
        selectedIdProperty().addListener((observable, oldValue, newValue) -> {
                    PowerStationPM oldSelection = getPowerStation(oldValue.intValue());
                    PowerStationPM newSelection = getPowerStation(newValue.intValue());

                    if (oldSelection != null) {
                        unbindFromProxy(oldSelection);
                        disableUndoSupport(oldSelection);
                    }

                    if (newSelection != null) {
                        bindToProxy(newSelection);
                        enableUndoSupport(newSelection);
                    }
                }
        );

        // add canton list
        allCantons.addAll(getCantons());

        // set selected id
        Platform.runLater(() -> setSelectedId(getFirstPowerStation()));
        invalidInputEntered.setValue(false);
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
                    .sorted(Comparator.comparing(PowerStationPM::getName))
                    .collect(Collectors.toList());
        }
    }

    private void bindToProxy(PowerStationPM powerStation) {
        powerStationProxy.idProperty().bindBidirectional(powerStation.idProperty());
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
        powerStationProxy.idProperty().unbindBidirectional(powerStation.idProperty());
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

    private void disableUndoSupport(PowerStationPM powerstation) {
        powerstation.idProperty().removeListener(propertyChangeListenerForUndoSupport);
        powerstation.nameProperty().removeListener(propertyChangeListenerForUndoSupport);
        powerstation.typeProperty().removeListener(propertyChangeListenerForUndoSupport);
        powerstation.siteProperty().removeListener(propertyChangeListenerForUndoSupport);
        powerstation.cantonProperty().removeListener(propertyChangeListenerForUndoSupport);
        powerstation.maxWaterVolumeProperty().removeListener(propertyChangeListenerForUndoSupport);
        powerstation.maxPowerMwProperty().removeListener(propertyChangeListenerForUndoSupport);
        powerstation.startOfOperationFirstProperty().removeListener(propertyChangeListenerForUndoSupport);
        powerstation.startOfOperationLastProperty().removeListener(propertyChangeListenerForUndoSupport);
        powerstation.latitudeProperty().removeListener(propertyChangeListenerForUndoSupport);
        powerstation.longitudeProperty().removeListener(propertyChangeListenerForUndoSupport);
        powerstation.statusProperty().removeListener(propertyChangeListenerForUndoSupport);
        powerstation.waterbodiesProperty().removeListener(propertyChangeListenerForUndoSupport);
        powerstation.imageUrlProperty().removeListener(propertyChangeListenerForUndoSupport);

    }

    private void enableUndoSupport(PowerStationPM powerstation) {
        powerstation.idProperty().addListener(propertyChangeListenerForUndoSupport);
        powerstation.nameProperty().addListener(propertyChangeListenerForUndoSupport);
        powerstation.idProperty().addListener(propertyChangeListenerForUndoSupport);
        powerstation.nameProperty().addListener(propertyChangeListenerForUndoSupport);
        powerstation.typeProperty().addListener(propertyChangeListenerForUndoSupport);
        powerstation.siteProperty().addListener(propertyChangeListenerForUndoSupport);
        powerstation.cantonProperty().addListener(propertyChangeListenerForUndoSupport);
        powerstation.maxWaterVolumeProperty().addListener(propertyChangeListenerForUndoSupport);
        powerstation.maxPowerMwProperty().addListener(propertyChangeListenerForUndoSupport);
        powerstation.startOfOperationFirstProperty().addListener(propertyChangeListenerForUndoSupport);
        powerstation.startOfOperationLastProperty().addListener(propertyChangeListenerForUndoSupport);
        powerstation.latitudeProperty().addListener(propertyChangeListenerForUndoSupport);
        powerstation.longitudeProperty().addListener(propertyChangeListenerForUndoSupport);
        powerstation.statusProperty().addListener(propertyChangeListenerForUndoSupport);
        powerstation.waterbodiesProperty().addListener(propertyChangeListenerForUndoSupport);
        powerstation.imageUrlProperty().addListener(propertyChangeListenerForUndoSupport);

    }

    <T> void setPropertyValueWithoutUndoSupport(Property<T> property, T newValue) {
        property.removeListener(propertyChangeListenerForUndoSupport);
        property.setValue(newValue);
        property.addListener(propertyChangeListenerForUndoSupport);
    }

    // overview methods
    public void addPowerStation() {
        PowerStationPM newItem = PowerStationPM.getDefaultPowerStationPM();
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

    public CantonPM getCantonPM(Canton canton) {
        return allCantons.stream()
                .filter(cantonpm -> cantonpm.getCanton().getName().equals(canton.getName()))
                .findFirst().orElse(null);
    }

    public int getFirstPowerStation() {
        return allPowerStations.stream()
                .map(powerStationPM -> powerStationPM.getId())
                .findFirst()
                .orElse(null);
    }

    public int getNewPowerStationID() {
        int newID = allPowerStations.stream()
                .mapToInt(powerStation -> powerStation.getId())
                .reduce(Integer::max)
                .getAsInt();

        while (getPowerStation(newID) != null) {
            ++newID;
        }
        return newID;
    }

    public IntegerProperty size() {
        return new SimpleIntegerProperty(allPowerStations.size());
    }

    // footer methods
    public ObservableList<CantonPM> getCantons() {
        return FXCollections.observableArrayList(allPowerStations.stream()
                .map(powerStationPM -> powerStationPM.getCanton())
                .distinct()
                .sorted(Comparator.comparing(Canton::getName))
                .map(canton -> new CantonPM(canton, String.valueOf(getTotalPower(canton)), getPowerStationCount(canton)))
                .collect(Collectors.toList()));
    }

    public double getTotalPower(Canton canton) {
        return round(allPowerStations.stream()
                .filter(powerStation -> powerStation.getCanton().equals(canton))
                .collect(Collectors.summingDouble(PowerStationPM::getMaxPowerMw)), 1);
    }

    public int getPowerStationCount(Canton canton) {
        return (int) allPowerStations.stream()
                .filter(powerStation -> powerStation.getCanton().equals(canton))
                .count();
    }

    public void updateAllCantons() {
        allCantons.stream()
                .forEach(cantonPM -> {
                    cantonPM.setPowerStationCount(getPowerStationCount(cantonPM.getCanton()));
                    cantonPM.setTotalPowerMw(String.valueOf(getTotalPower(cantonPM.getCanton())));
                });
    }

    public Boolean isValidInput(String input, String type) {
        try {
            if (type.equalsIgnoreCase("float")) {
                Float.parseFloat(input);
            } else if (type.equalsIgnoreCase("int")) {
                Integer.parseInt(input);
            } else if (type.equalsIgnoreCase("double")) {
                Double.parseDouble(input);
            }
            setInvalidInputEntered(false);
            return false ;

        } catch(Exception e) {
            setInvalidInputEntered(true);
            return true ;
        }

    }

    private static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    // getters and setters
    public ObservableList<PowerStationPM> getAllPowerStations() {
        return allPowerStations;
    }

    public ObservableList<CantonPM> getAllCantons() {
        return allCantons;
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
        setInvalidInputEntered(false);

        if (selectedPowerStationId == 0) {
            setLabelsEnabled(true);
            setDeleteEnabled(true);
        } else {
            setLabelsEnabled(false);
            setDeleteEnabled(false);
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

    public boolean isDeleteEnabled() {
        return deleteEnabled.get();
    }

    public BooleanProperty deleteEnabledProperty() {
        return deleteEnabled;
    }

    public void setDeleteEnabled(boolean deleteEnabled) {
        this.deleteEnabled.set(deleteEnabled);
    }

    public int getIndexOfPowerStation(int id) {
        return getAllPowerStations().indexOf(getPowerStation(id));
    }

    public boolean getUndoDisabled() {
        return undoDisabled.get();
    }

    public BooleanProperty undoDisabledProperty() {
        return undoDisabled;
    }

    public void setUndoDisabled(boolean undoDisabled) {
        this.undoDisabled.set(undoDisabled);
    }

    public boolean getRedoDisabled() {
        return redoDisabled.get();
    }

    public BooleanProperty redoDisabledProperty() {
        return redoDisabled;
    }

    public void setRedoDisabled(boolean redoDisabled) {
        this.redoDisabled.set(redoDisabled);
    }

    public boolean isInvalidInputEntered() {
        return invalidInputEntered.get();
    }

    public BooleanProperty invalidInputEnteredProperty() {
        return invalidInputEntered;
    }

    public void setInvalidInputEntered(boolean invalidInputEntered) {
        this.invalidInputEntered.set(invalidInputEntered);
    }

    public void setupValueChangedListeners() {
        // selection changes are undoable
        selectedIdProperty().addListener(propertyChangeListenerForUndoSupport);

        allPowerStations.addListener((ListChangeListener<PowerStationPM>) event -> {
            while (event.next()) {
                if (event.wasUpdated() || event.wasRemoved() || event.wasAdded()) {
                    updateAllCantons();
                }
            }
        });
    }
}
