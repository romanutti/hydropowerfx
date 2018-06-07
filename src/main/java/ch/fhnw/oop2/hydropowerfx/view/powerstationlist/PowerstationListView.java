package ch.fhnw.oop2.hydropowerfx.view.powerstationlist;

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
import static ch.fhnw.oop2.hydropowerfx.util.NumberFormatUtil.*;


public class PowerstationListView extends VBox implements ViewMixin {
    // model
    private final RootPM rootPM;

    // gui elements
    private TableView<PowerStationPM> itemTable;
    private Label resultCountLabel;


    public PowerstationListView(RootPM rootPM) {
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

    @Override
    public void layoutControls() {

        /********************************************************************************
            LAYOUT
            Layouting sources:
            - View classes: via css
            - Added items: via java
        ********************************************************************************/

        /********************************************************************************
             ITEM TABLE formatting
        ********************************************************************************/
        // name column
        getTableColumnByName("Name").setMinWidth(150);
        getTableColumnByName("Name").setMaxWidth(250);
        // emblem column
        getTableColumnByName("").setMinWidth(22);
        getTableColumnByName("").setMaxWidth(22);
        // power column
        getTableColumnByName("Power").setMinWidth(60);
        getTableColumnByName("Power").setMaxWidth(100);
        // startofoperationfirst column
        getTableColumnByName("StartOfOperationFirst").setMinWidth(60);
        getTableColumnByName("StartOfOperationFirst").setMaxWidth(120);

        itemTable.setEditable(true); // enable cell editing
        itemTable.setMinWidth(USE_COMPUTED_SIZE); // set min size
        itemTable.setPrefWidth(USE_COMPUTED_SIZE); // set min size
        setVgrow(itemTable, Priority.ALWAYS); // set vertical grow


        getChildren().addAll(itemTable, resultCountLabel);
    }

    @Override
    public void setupBindings() {

        /********************************************************************************
         SEARCH functionality
        ********************************************************************************/
        SortedList<PowerStationPM> sortedData = new SortedList<>(rootPM.getFilteredPowerStations());
        sortedData.comparatorProperty().bind(itemTable.comparatorProperty());
        itemTable.setItems(sortedData);

        /********************************************************************************
         MULTI-LANGUAGE functionality
        ********************************************************************************/
        getTableColumnByName("Name").textProperty().bind(rootPM.getLanguageSwitcherPM().nameLabelTextProperty());
        getTableColumnByName("Power").textProperty().bind(rootPM.getLanguageSwitcherPM().maxPowerMwLabelTextProperty());
        getTableColumnByName("StartOfOperationFirst").textProperty().bind(rootPM.getLanguageSwitcherPM().startOfOperationFirstLabelTextProperty());

        /********************************************************************************
         NUMBER OF SEARCH RESULTS functionality
        ********************************************************************************/
        resultCountLabel.textProperty().bind(Bindings.concat(
                Bindings.selectInteger(itemTable.getFocusModel().focusedIndexProperty().add(1)), "/",
                Bindings.size((itemTable.getItems())).asString())); // display suffix "MW"
    }

    @Override
    public void setupValueChangedListeners() {

        /********************************************************************************
         SCROLL to item out of visible range
        ********************************************************************************/
        itemTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            rootPM.setSelectedId((newValue == null) ? 0 : newValue.getId()); // set null if no item selected
            int selectedIndex = itemTable.getSelectionModel().getSelectedIndex();
            int[] visibleRange = getVisibleRange(itemTable);

            // scroll only if powerstation out of visible area in tableview
            if (!(visibleRange[0] <= selectedIndex && selectedIndex <= visibleRange[visibleRange.length - 1])) {
                itemTable.scrollTo(rootPM.getPowerStationProxy());
            }
        });

        /********************************************************************************
         SELECT current item in table
        ********************************************************************************/
        rootPM.selectedIdProperty().addListener((observable, oldValue, newValue) -> itemTable.getSelectionModel().select(rootPM.getPowerStation((int) newValue)));
    }

    private TableView<PowerStationPM> initializePowerStationTable() {

        /********************************************************************************
         INITIALIZE table
        ********************************************************************************/
        TableView<PowerStationPM> tableView = new TableView<>(rootPM.getAllPowerStations());

        // name column
        TableColumn<PowerStationPM, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());
        // emblem column
        TableColumn<PowerStationPM, Canton> emblemColumn = new TableColumn<>();
        emblemColumn.setCellValueFactory(cell -> cell.getValue().cantonProperty());
        emblemColumn.setCellFactory(canton -> new CantonTableCell());
        // maxpower column
        TableColumn<PowerStationPM, Number> maxPowerColumn = new TableColumn<>("Power");
        maxPowerColumn.setCellValueFactory(cell -> cell.getValue().maxPowerMwProperty());
        // start of Operations column
        TableColumn<PowerStationPM, Number> startOfOperationFirstColumn = new TableColumn<>("StartOfOperationFirst");
        startOfOperationFirstColumn.setCellValueFactory(cell -> cell.getValue().startOfOperationFirstProperty());

        /********************************************************************************
         TABLE EDITING functionality
        ********************************************************************************/
        // name column
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(
                t -> ((PowerStationPM) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setName(t.getNewValue())
        );
        // maxpower column
        maxPowerColumn.setCellFactory(TextFieldTableCell.<PowerStationPM, Number>forTableColumn(new NumberStringConverter()));
        maxPowerColumn.setOnEditCommit(
                t -> ((PowerStationPM) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setMaxPowerMw(t.getNewValue().doubleValue())
        );
        // operationfirst column
        startOfOperationFirstColumn.setCellFactory(TextFieldTableCell.<PowerStationPM, Number>forTableColumn(new NumberStringConverter(YEAR_FORMAT)));
        startOfOperationFirstColumn.setOnEditCommit(
                t -> ((PowerStationPM) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setStartOfOperationFirst(t.getNewValue().intValue())
        );

        tableView.getColumns().addAll(nameColumn, emblemColumn, maxPowerColumn, startOfOperationFirstColumn); // add columns
        return tableView;
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
