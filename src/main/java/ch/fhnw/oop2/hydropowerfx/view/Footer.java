package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.Canton;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.PowerStationPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
        // TODO: CantonTable implementieren

        return null;
    }

    @Override
    public void layoutControls() {

    }
}
