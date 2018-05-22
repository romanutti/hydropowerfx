package ch.fhnw.oop2.hydropowerfx.view.powerStationOverview;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.PowerStationPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import ch.fhnw.oop2.hydropowerfx.view.ViewMixin;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

public class Overview extends VBox implements ViewMixin {
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

        // enable cell editing
        itemTable.setEditable(true);

    }

    private TableView<PowerStationPM> initializePowerStationTable() {
        TableView<PowerStationPM> tableView = new TableView<>(rootPM.getAllPowerStations());

        TableColumn<PowerStationPM, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());

        // enable cell editing
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(
                t -> ((PowerStationPM) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setName(t.getNewValue())
        );

        TableColumn<PowerStationPM, String> emblemColumn = new TableColumn<>();
        // TODO: Kann hier einfach new SimpleStringProperty gemacht werden?
        emblemColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCanton().getName()));
        emblemColumn.setCellFactory(canton -> new CantonTableCell());

        // TODO: cell editing for canton?

        TableColumn<PowerStationPM, Number> maxPowerColumn = new TableColumn<>("Power");
        maxPowerColumn.setCellValueFactory(cell -> cell.getValue().maxPowerMwProperty());

        // enable cell editing
        maxPowerColumn.setCellFactory(TextFieldTableCell.<PowerStationPM, Number>forTableColumn(new NumberStringConverter()));
        maxPowerColumn.setOnEditCommit(
                t -> ((PowerStationPM) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setMaxPowerMw((Double) t.getNewValue())
        );

        TableColumn<PowerStationPM, Number> startOfOperationFirstColumn = new TableColumn<>("StartOfOperationFirst");
        startOfOperationFirstColumn.setCellValueFactory(cell -> cell.getValue().startOfOperationFirstProperty());

        // enable cell editing
        startOfOperationFirstColumn.setCellFactory(TextFieldTableCell.<PowerStationPM, Number>forTableColumn(new NumberStringConverter()));
        startOfOperationFirstColumn.setOnEditCommit(
                t -> ((PowerStationPM) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setStartOfOperationFirst((Long) t.getNewValue())
        );

        tableView.getColumns().addAll(nameColumn, emblemColumn, maxPowerColumn, startOfOperationFirstColumn);

        return tableView;
    }


    @Override
    public void layoutControls() {

        setMinWidth(300);
        setMaxWidth(405);

        getTableColumnByName("Name").setMinWidth(130);
        getTableColumnByName("Name").setMaxWidth(150);

        getTableColumnByName("Power").setMinWidth(60);
        getTableColumnByName("Power").setMaxWidth(60);

        getTableColumnByName("StartOfOperationFirst").setMinWidth(60);
        getTableColumnByName("StartOfOperationFirst").setMaxWidth(60);

        itemTable.setMinWidth(USE_COMPUTED_SIZE);

        getChildren().addAll(itemTable);
        setVgrow(itemTable, Priority.ALWAYS);

    }


    @Override
    public void setupBindings() {
        // Search
        SortedList<PowerStationPM> sortedData = new SortedList<>(rootPM.getFilteredPowerStations());
        sortedData.comparatorProperty().bind(itemTable.comparatorProperty());
        itemTable.setItems(sortedData);

        // Language Switcher
        getTableColumnByName("Name").textProperty().bind(rootPM.getLanguageSwitcherPM().nameLabelTextProperty());
        getTableColumnByName("Power").textProperty().bind(rootPM.getLanguageSwitcherPM().maxPowerMwLabelTextProperty());
        getTableColumnByName("StartOfOperationFirst").textProperty().bind(rootPM.getLanguageSwitcherPM().startOfOperationFirstLabelTextProperty());

    }

    @Override
    public void setupValueChangedListeners() {
        itemTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> rootPM.setSelectedId(newValue.getId()));
        rootPM.selectedIdProperty().addListener((observable, oldValue, newValue) -> itemTable.getSelectionModel().select(((int) newValue)));

    }

    private TableColumn getTableColumnByName(String name) {
        for (TableColumn col : itemTable.getColumns())
            if (col.getText().equals(name)) return col;
        return null;
    }

}
