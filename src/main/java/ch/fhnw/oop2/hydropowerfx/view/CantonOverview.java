package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.domain.PowerStationPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

public class CantonOverview extends StackPane {

    private final RootPM model;
    private TableView<PowerStationPM> cantonOverview;

    public CantonOverview(RootPM model){
        this.model = model;
        initializeSelf();
        layoutControls();
        initializeControls();
        setupBindings();
    }

    private void initializeSelf() {
        String stylesheet = getClass().getResource("style.css").toExternalForm();
        getStylesheets().add(stylesheet);
    }

    private void initializeControls() {
        cantonOverview = new TableView<>(model.getAllPowerStations());
    }

    private void layoutControls() {
        TableColumn<PowerStationPM, String> nameColumn = new TableColumn<>("Test");
        cantonOverview.getColumns().addAll(nameColumn);
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().cantonProperty().toString()));
    }

    public void setupBindings() {

    }

}
