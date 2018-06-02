package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import ch.fhnw.oop2.hydropowerfx.view.powerstationoverview.Overview;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ApplicationUI extends BorderPane implements ViewMixin {
    private final RootPM model;

    private SplitPane verticalMainSP;
    private SplitPane horizontalMainCenterSP;
    private SelectorBar selectorbar;
    private Overview overview;
    private VBox center;
    private Header header;
    private Editor editor;
    private SplitPane horizontalMainBottomSP;
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
        verticalMainSP = new SplitPane();
        horizontalMainCenterSP = new SplitPane();
        horizontalMainBottomSP = new SplitPane();
        selectorbar = new SelectorBar(model);
        overview = new Overview(model);
        center = new VBox();
        header = new Header(model);
        editor = new Editor(model);
        map = new Map(model);
        footer = new Footer(model);
        }

    @Override
    public void layoutControls() {
        center.getChildren().addAll(header, editor);
        horizontalMainCenterSP.getItems().addAll(overview,center);
        horizontalMainBottomSP.getItems().addAll(footer, map);
        verticalMainSP.getItems().addAll(horizontalMainCenterSP, horizontalMainBottomSP);
        verticalMainSP.setOrientation(Orientation.VERTICAL);


        setTop(selectorbar);
        setCenter(verticalMainSP);
        setMinWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
    }

    @Override
    public void setupBindings() {
    }
}
