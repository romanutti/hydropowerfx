package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import ch.fhnw.oop2.hydropowerfx.view.powerStationOverview.Overview;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ApplicationUI extends BorderPane implements ViewMixin {
    private final RootPM model;

    private SplitPane spVerticalMain;
    private SplitPane spHorizontalCenter;
    private SelectorBar selectorbar;
    private Overview overview;
    private VBox center;
    private Header header;
    private Editor editor;
    private HBox bottom;
    private Map map;
    private Footer footer;

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
        spVerticalMain = new SplitPane();
        spHorizontalCenter = new SplitPane();
        selectorbar = new SelectorBar(model);
        overview = new Overview(model);
        center = new VBox();
        header = new Header(model);
        editor = new Editor(model);
        bottom = new HBox();
        map = new Map(model);
        footer = new Footer(model);
        }

    @Override
    public void layoutControls() {
        center.getChildren().addAll(header, editor);
        spHorizontalCenter.getItems().addAll(overview,center);
        bottom.getChildren().addAll(footer, map);
        spVerticalMain.getItems().addAll(spHorizontalCenter,bottom);
        spVerticalMain.setOrientation(Orientation.VERTICAL);

        setTop(selectorbar);
        setCenter(spVerticalMain);
        setMinWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
    }

    @Override
    public void setupBindings() {
    }
}
