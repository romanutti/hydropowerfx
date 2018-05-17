package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.PowerStationPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
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

        TableColumn<PowerStationPM, String> nameColumn = new TableColumn<>();
        nameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());

        TableColumn<PowerStationPM, Number> maxPowerColumn = new TableColumn<>();
        maxPowerColumn.setCellValueFactory(cell -> cell.getValue().maxPowerMwProperty());

        TableColumn<PowerStationPM, Number> startOfOperationFirstColumn = new TableColumn<>();
        startOfOperationFirstColumn.setCellValueFactory(cell -> cell.getValue().startOfOperationFirstProperty());

        tableView.getColumns().addAll(nameColumn, maxPowerColumn, startOfOperationFirstColumn);




        return tableView;
    }


    @Override
    public void layoutControls() {

        setMinWidth(410);
        //setMaxWidth(405);
        //TODO: Column selection via index is not ideal
        getItemTable().getColumns().get(2).setMinWidth(115);
        getItemTable().setMinWidth(USE_PREF_SIZE);
        getChildren().addAll(getItemTable());
        setVgrow(getItemTable(),Priority.ALWAYS);

    }


    @Override
    public void setupBindings() {
        //TODO: Better method to identify the right column; not just by id
        getItemTable().getColumns().get(0).textProperty().bind(rootPM.getLanguageSwitcherPM().nameLabelTextProperty());
        getItemTable().getColumns().get(1).textProperty().bind(rootPM.getLanguageSwitcherPM().maxPowerMwLabelTextProperty());
        getItemTable().getColumns().get(2).textProperty().bind(rootPM.getLanguageSwitcherPM().startOfOperationFirstLabelTextProperty());

    }

    //TODO: Check if getter and setter are allowed
    public TableView<PowerStationPM> getItemTable() {
        return itemTable;
    }


}
