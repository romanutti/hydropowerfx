package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.Canton;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Footer extends VBox implements ViewMixin{
    private final RootPM rootPM;

    private TableView<Canton> itemTable;


    public Footer(RootPM rootPM) {
        this.rootPM = rootPM;
        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("overview");
    }

    @Override
    public void initializeControls() {
        itemTable = initializeCantonTable();
    }

    private TableView<Canton> initializeCantonTable() {
        TableView<Canton> tableView = new TableView<>(rootPM.getAllCantons());

        TableColumn<Canton, String> nameColumn = new TableColumn<>();
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));

        TableColumn<Canton, Number> maxPowerColumn = new TableColumn<>("");
        maxPowerColumn.setCellValueFactory(cell -> rootPM.getTotalPower(cell.getValue()));

        TableColumn<Canton, Number> powerStationCountColumn = new TableColumn<>("");
        powerStationCountColumn.setCellValueFactory(cell -> rootPM.getPowerStationCount(cell.getValue()));

        tableView.getColumns().addAll(nameColumn, maxPowerColumn, powerStationCountColumn);

        return tableView;
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(itemTable);
        setVgrow(itemTable,Priority.ALWAYS);

    }
}
