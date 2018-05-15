package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.PowerStationPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class Overview extends VBox implements ViewMixin{
    private final RootPM rootPM;

    private TableView<PowerStationPM> itemTable;


    public Overview(RootPM rootPM) {
        this.rootPM = rootPM;
        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("overview");
    }

    @Override
    public void initializeControls() {
        itemTable = initializePowerStationTable();
    }

    private TableView<PowerStationPM> initializePowerStationTable() {
        TableView<PowerStationPM> tableView = new TableView<>(rootPM.getAllPowerStations());

        TableColumn<PowerStationPM, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());

        TableColumn<PowerStationPM, Number> maxPowerColumn = new TableColumn<>("Leistung MW");
        maxPowerColumn.setCellValueFactory(cell -> cell.getValue().maxPowerMwProperty());

        TableColumn<PowerStationPM, Number> startOfOperationFirstColumn = new TableColumn<>("Inbetriebnahme");
        startOfOperationFirstColumn.setCellValueFactory(cell -> cell.getValue().startOfOperationFirstProperty());

        tableView.getColumns().addAll(nameColumn, maxPowerColumn, startOfOperationFirstColumn);

        return tableView;
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(itemTable);
    }
}
