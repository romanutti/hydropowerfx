package ch.fhnw.oop2.hydropowerfx.view.powerstationoverview;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.Canton;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.PowerStationPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import ch.fhnw.oop2.hydropowerfx.view.ViewMixin;
import javafx.beans.binding.Bindings;
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

import java.util.Arrays;

public class Overview extends VBox implements ViewMixin {
    // model
    private final RootPM rootPM;

    // gui elements
    private TableView<PowerStationPM> itemTable;
    private Label resultCountLabel;


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
        resultCountLabel = new Label();
    }

    private TableView<PowerStationPM> initializePowerStationTable() {
        TableView<PowerStationPM> tableView = new TableView<>(rootPM.getAllPowerStations());

        // name column
        TableColumn<PowerStationPM, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());
        // enable cell editing
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(
                t -> ((PowerStationPM) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setName(t.getNewValue())
        );

        // emblem column
        TableColumn<PowerStationPM, Canton> emblemColumn = new TableColumn<>();
        emblemColumn.setCellValueFactory(cell -> cell.getValue().cantonProperty());
        emblemColumn.setCellFactory(canton -> new CantonTableCell());

        // maxpower column
        TableColumn<PowerStationPM, Number> maxPowerColumn = new TableColumn<>("Power");
        maxPowerColumn.setCellValueFactory(cell -> cell.getValue().maxPowerMwProperty());
        // enable cell editing
        maxPowerColumn.setCellFactory(TextFieldTableCell.<PowerStationPM, Number>forTableColumn(new NumberStringConverter()));
        maxPowerColumn.setOnEditCommit(
                t -> ((PowerStationPM) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setMaxPowerMw((Double) t.getNewValue())
        );

        // number column
        TableColumn<PowerStationPM, Number> startOfOperationFirstColumn = new TableColumn<>("StartOfOperationFirst");
        startOfOperationFirstColumn.setCellValueFactory(cell -> cell.getValue().startOfOperationFirstProperty());
        // enable cell editing
        startOfOperationFirstColumn.setCellFactory(TextFieldTableCell.<PowerStationPM, Number>forTableColumn(new NumberStringConverter()));
        startOfOperationFirstColumn.setOnEditCommit(
                t -> ((PowerStationPM) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setStartOfOperationFirst((Long) t.getNewValue())
        );

        // add columns
        tableView.getColumns().addAll(nameColumn, emblemColumn, maxPowerColumn, startOfOperationFirstColumn);

        return tableView;
    }


    @Override
    public void layoutControls() {

        // set ui element sizes
        setMinWidth(350);
        setMaxWidth(500);
        setPrefWidth(300);
        setMaxHeight(1000);


        // table element
        // name column formatting
        getTableColumnByName("Name").setMinWidth(150);
        getTableColumnByName("Name").setMaxWidth(250);

        // emblem Column formatting
        getTableColumnByName("").setMinWidth(22);
        getTableColumnByName("").setMaxWidth(22);

        // power column formatting
        getTableColumnByName("Power").setMinWidth(60);
        getTableColumnByName("Power").setMaxWidth(100);

        // startofoperationfirst column formatting
        getTableColumnByName("StartOfOperationFirst").setMinWidth(60);
        getTableColumnByName("StartOfOperationFirst").setMaxWidth(120);

        // enable cell editing
        itemTable.setEditable(true);
        // set min size
        itemTable.setMinWidth(USE_COMPUTED_SIZE);

        getChildren().addAll(itemTable, resultCountLabel);
        setVgrow(itemTable, Priority.ALWAYS);

    }

    @Override
    public void setupBindings() {
        // search
        SortedList<PowerStationPM> sortedData = new SortedList<>(rootPM.getFilteredPowerStations());
        sortedData.comparatorProperty().bind(itemTable.comparatorProperty());
        itemTable.setItems(sortedData);

        // multilanguage support
        getTableColumnByName("Name").textProperty().bind(rootPM.getLanguageSwitcherPM().nameLabelTextProperty());
        getTableColumnByName("Power").textProperty().bind(rootPM.getLanguageSwitcherPM().maxPowerMwLabelTextProperty());
        getTableColumnByName("StartOfOperationFirst").textProperty().bind(rootPM.getLanguageSwitcherPM().startOfOperationFirstLabelTextProperty());

        // display index nr./total count summary
        resultCountLabel.textProperty().bind(Bindings.concat(
                Bindings.selectInteger(itemTable.getFocusModel().focusedIndexProperty().add(1)),
                "/",
                Bindings.size((itemTable.getItems())).asString()));
    }

    @Override
    public void setupValueChangedListeners() {
        // selected item on table
        itemTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // set null if no item selected
            rootPM.setSelectedId((newValue == null) ? 0 : newValue.getId());

            // scroll only if powerstation out of visible area in tableview
            int selectedIndex = itemTable.getSelectionModel().getSelectedIndex();
            int[] visibleRange = getVisibleRange(itemTable);

            if (!(visibleRange[0] <= selectedIndex && selectedIndex <= visibleRange[visibleRange.length - 1])) {
                itemTable.scrollTo(rootPM.getPowerStationProxy());
            }

        });

        // selected id on model
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
