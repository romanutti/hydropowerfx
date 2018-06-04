package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import ch.fhnw.oop2.hydropowerfx.view.powerstationoverview.DetailView;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;

public class ApplicationUI extends BorderPane implements ViewMixin {
    // model
    private final RootPM model;

    // gui elements
    private SplitPane verticalMainSP;
    private SplitPane horizontalMainCenterSP;
    private ToolbarView toolbarView;
    private DetailView detailView;
    private GridPane centerView;
    private HeaderView headerView;
    private EditorView editorView;
    private SplitPane horizontalMainBottomSP;
    private MapView mapView;
    private SummaryView summaryView;


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
        detailView = new DetailView(model);
        centerView = new GridPane();
        headerView = new HeaderView(model);
        editorView = new EditorView(model);
        mapView = new MapView(model);
        summaryView = new SummaryView(model);
    }

    @Override
    public void layoutControls() {

        centerView.add(headerView,0,0);
        centerView.add(editorView,0,1);
        ColumnConstraints ccCenterView = new ColumnConstraints();
        ccCenterView.setHgrow(Priority.ALWAYS);
        centerView.getColumnConstraints().addAll(ccCenterView);
        horizontalMainCenterSP.getItems().addAll(detailView, centerView);
        horizontalMainBottomSP.getItems().addAll(summaryView, mapView);
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
