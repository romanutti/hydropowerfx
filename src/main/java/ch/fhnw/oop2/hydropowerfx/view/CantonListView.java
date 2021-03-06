package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.CantonPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CantonListView extends HBox implements ViewMixin {
    // model
    private final RootPM rootPM;

    // gui elements
    private TableView<CantonPM> itemTable;


    public CantonListView(RootPM rootPM) {
        this.rootPM = rootPM;
        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("summary");
    }

    @Override
    public void initializeControls() {
        itemTable = initializeCantonTable();
    }

    private TableView<CantonPM> initializeCantonTable() {
        TableView<CantonPM> tableView = new TableView<>(rootPM.getAllCantons());

        /********************************************************************************
         INITIALIZE table
        ********************************************************************************/
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

        /********************************************************************************
         LAYOUT
         Layouting sources:
         - View classes: via css
         - Added items: via java
        ********************************************************************************/

        setHgrow(getItemTable(), Priority.ALWAYS);

        /********************************************************************************
         ITEM TABLE formatting
        ********************************************************************************/
        // table element
        getTableColumnByName("cantonColumn").setPrefWidth(320);
        getTableColumnByName("cantonColumn").setMinWidth(100);
        getTableColumnByName("cantonColumn").setMaxWidth(600);

        getTableColumnByName("totalPowerColumn").setPrefWidth(150);
        getTableColumnByName("totalPowerColumn").setMinWidth(50);
        getTableColumnByName("totalPowerColumn").setMaxWidth(200);

        getTableColumnByName("powerStationCountColumn").setPrefWidth(130);
        getTableColumnByName("powerStationCountColumn").setMinWidth(50);
        getTableColumnByName("powerStationCountColumn").setMaxWidth(200);

        setHgrow(getItemTable(), Priority.ALWAYS);

        getChildren().addAll(getItemTable());

    }

    @Override
    public void setupBindings() {
        /********************************************************************************
         MULTILANGUAGE support functionality
        ********************************************************************************/
        getTableColumnByName("cantonColumn").textProperty().bind(rootPM.getLanguageSwitcherPM().cantonColumnTextProperty());
        getTableColumnByName("totalPowerColumn").textProperty().bind(rootPM.getLanguageSwitcherPM().totalPowerMwColumnTextProperty());
        getTableColumnByName("powerStationCountColumn").textProperty().bind(rootPM.getLanguageSwitcherPM().powerStationCountColumnTextProperty());

    }

    @Override
    public void setupValueChangedListeners() {
        /********************************************************************************
         SELECTED item functionality
        ********************************************************************************/
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
