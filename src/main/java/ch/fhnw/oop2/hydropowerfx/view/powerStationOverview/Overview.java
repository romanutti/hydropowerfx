package ch.fhnw.oop2.hydropowerfx.view.powerStationOverview;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.PowerStationPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import ch.fhnw.oop2.hydropowerfx.view.ViewMixin;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.skin.TableViewSkin;
import javafx.scene.control.skin.VirtualFlow;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

public class Overview extends VBox implements ViewMixin {
    private final RootPM rootPM;

    private TableView<PowerStationPM> itemTable;

    private Label resultCountLabal;


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
        resultCountLabal = new Label();


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
        // add sorted Column name
        return tableView;
    }


    @Override
    public void layoutControls() {

        setMinWidth(300);
        setMaxWidth(405);

        getTableColumnByName("Name").setMinWidth(130);
        getTableColumnByName("Name").setMaxWidth(150);

        getTableColumnByName("Power").setMinWidth(60);
        getTableColumnByName("Power").setMaxWidth(150);

        getTableColumnByName("StartOfOperationFirst").setMinWidth(60);
        getTableColumnByName("StartOfOperationFirst").setMaxWidth(150);

        itemTable.setMinWidth(USE_COMPUTED_SIZE);

        getChildren().addAll(itemTable, resultCountLabal);
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

        //TODO: total labal to show visible/totalRows
        //resultCountLabal.textProperty().bind(Bindings.size((rootPM.size().asString()));
        resultCountLabal.textProperty().bind(Bindings.size((itemTable.getItems())).asString());


    }

    @Override
    public void setupValueChangedListeners() {
        itemTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // set null if no item selected
            rootPM.setSelectedId((newValue == null) ? 0 : newValue.getId());

            // scroll only if powerstation out of visible area in tableview
            int selectedId = rootPM.getSelectedId();
            int selectedIndex = rootPM.getIndexOfPowerStation(selectedId);
            int[] visibleRange = getVisibleRange(itemTable);

            if (!(visibleRange[0] <= selectedIndex && selectedIndex <= visibleRange[visibleRange.length - 1])) {
                itemTable.scrollTo(rootPM.getIndexOfPowerStation(rootPM.getSelectedId()));
            }

        });

        rootPM.selectedIdProperty().addListener((observable, oldValue, newValue) -> itemTable.getSelectionModel().select(rootPM.getPowerStation((int) newValue)));
    }

    private int[] getVisibleRange(TableView table) {
        TableViewSkin<?> skin = (TableViewSkin) table.getSkin();
        if (skin == null) {
            return new int[]{0, 0};
        }
        VirtualFlow<?> flow = (VirtualFlow) skin.getChildren().get(1);
        int indexFirst;
        int indexLast;
        if (flow != null && flow.getFirstVisibleCell() != null
                && flow.getLastVisibleCell() != null) {
            indexFirst = flow.getFirstVisibleCell().getIndex();
            if (indexFirst >= table.getItems().size())
                indexFirst = table.getItems().size() - 1;
            indexLast = flow.getLastVisibleCell().getIndex();
            if (indexLast >= table.getItems().size())
                indexLast = table.getItems().size() - 1;
        } else {
            indexFirst = 0;
            indexLast = 0;
        }
        return new int[]{indexFirst, indexLast};
    }

    private TableColumn getTableColumnByName(String name) {
        for (TableColumn col : itemTable.getColumns())
            if (col.getText().equals(name)) return col;
        return null;
    }


}
