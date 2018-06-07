package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import ch.fhnw.oop2.hydropowerfx.view.powerstationlist.PowerstationListView;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;

public class ApplicationUI extends BorderPane implements ViewMixin {
    // model
    private final RootPM model;

    // gui elements
    private SplitPane verticalMainSP;
    private SplitPane horizontalMainCenterSP;
    private ToolbarView toolbarView;
    private PowerstationListView powerstationListView;
    private StackPane centerResizingView;
    private VBox verticalCenterResizingView;
    private HeaderView headerView;
    private EditorView editorView;
    private SplitPane horizontalMainBottomSP;
    private MapView mapView;
    private CantonListView cantonListView;


    public ApplicationUI(RootPM model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeSelf() {
        String stylesheet = getClass().getResource("style.css").toExternalForm();
        getStylesheets().add(stylesheet);
    }

    @Override
    public void initializeControls() {
        verticalMainSP = new SplitPane();
        horizontalMainCenterSP = new SplitPane();
        horizontalMainBottomSP = new SplitPane();
        toolbarView = new ToolbarView(model);
        powerstationListView = new PowerstationListView(model);
        centerResizingView = new StackPane();
        verticalCenterResizingView = new VBox();
        headerView = new HeaderView(model);
        editorView = new EditorView(model);
        mapView = new MapView(model);
        cantonListView = new CantonListView(model);
    }

    @Override
    public void layoutControls() {
        verticalCenterResizingView.getChildren().addAll(headerView,editorView);
        centerResizingView.getChildren().addAll(verticalCenterResizingView);
        verticalCenterResizingView.setVgrow(headerView,Priority.ALWAYS);
        horizontalMainCenterSP.getItems().addAll(powerstationListView, centerResizingView);
        horizontalMainBottomSP.getItems().addAll(cantonListView, mapView);
        verticalMainSP.getItems().addAll(horizontalMainCenterSP, horizontalMainBottomSP);
        verticalMainSP.setOrientation(Orientation.VERTICAL);

        setTop(toolbarView);
        setCenter(verticalMainSP);
        setMinWidth(USE_PREF_SIZE);
    }

    @Override
    public void setupBindings() {
    }
}
