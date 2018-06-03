package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.CantonPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class SummaryView extends VBox implements ViewMixin {
    // model
    private final RootPM rootPM;

    // gui elements
    private TableView<CantonPM> itemTable;


    public SummaryView(RootPM rootPM) {
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

    private TableView<CantonPM> initializeCantonTable() {
        TableView<CantonPM> tableView = new TableView<>(rootPM.getAllCantons());

        // name column
        TableColumn<CantonPM, String> nameColumn = new TableColumn<>("cantonColumn");
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCanton().getName()));

        // totalpower column
        TableColumn<CantonPM, String> maxPowerColumn = new TableColumn<>("totalPowerColumn");
        maxPowerColumn.setCellValueFactory(cell -> cell.getValue().totalPowerMwProperty());

        // powerstationcount column
        TableColumn<CantonPM, Number> powerStationCountColumn = new TableColumn<>("powerStationCountColumn");
        powerStationCountColumn.setCellValueFactory(cell -> cell.getValue().powerStationCountProperty());

        tableView.getColumns().addAll(nameColumn, maxPowerColumn, powerStationCountColumn);

        return tableView;
    }


    @Override
    public void layoutControls() {

        // sizing
        setMinHeight(220);
        setMaxHeight(220);
        setPrefWidth(650);
        setPrefHeight(220);

        setVgrow(getItemTable(), Priority.ALWAYS);


        // table element
        getTableColumnByName("cantonColumn").setPrefWidth(370);
        getTableColumnByName("cantonColumn").setMinWidth(100);
        getTableColumnByName("cantonColumn").setMaxWidth(600);

        getTableColumnByName("totalPowerColumn").setPrefWidth(150);
        getTableColumnByName("totalPowerColumn").setMinWidth(50);
        getTableColumnByName("totalPowerColumn").setMaxWidth(200);

        getTableColumnByName("powerStationCountColumn").setPrefWidth(150);
        getTableColumnByName("powerStationCountColumn").setMinWidth(50);
        getTableColumnByName("powerStationCountColumn").setMaxWidth(200);

        itemTable.setMinWidth(USE_COMPUTED_SIZE);
        getChildren().addAll(getItemTable());

    }

    @Override
    public void setupBindings() {
        // multilanguage support
        getTableColumnByName("cantonColumn").textProperty().bind(rootPM.getLanguageSwitcherPM().cantonColumnTextProperty());
        getTableColumnByName("totalPowerColumn").textProperty().bind(rootPM.getLanguageSwitcherPM().totalPowerMwColumnTextProperty());
        getTableColumnByName("powerStationCountColumn").textProperty().bind(rootPM.getLanguageSwitcherPM().powerStationCountColumnTextProperty());

    }

    @Override
    public void setupValueChangedListeners() {
        rootPM.selectedIdProperty().addListener((observable, oldValue, newValue) -> itemTable.getSelectionModel().select(((int) newValue)));
    }

    private TableView<CantonPM> getItemTable() {
        return itemTable;
    }

    private TableColumn getTableColumnByName(String name) {
        for (TableColumn col : itemTable.getColumns())
            if (col.getText().equals(name)) return col;
        return null;
    }


}
