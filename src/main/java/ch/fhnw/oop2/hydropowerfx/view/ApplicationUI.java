package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ApplicationUI extends BorderPane implements ViewMixin {
    private final RootPM model;

    private SelectorBar selectorbar;
    private Overview overview;
    private VBox center;
    private Header header;
    private Editor editor;
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
        selectorbar = new SelectorBar(model);
        overview = new Overview(model);
        center = new VBox();
        header = new Header(model);
        editor = new Editor(model);
        footer = new Footer(model);
        }

    @Override
    public void layoutControls() {
        center.getChildren().addAll(header, editor);

        setTop(selectorbar);
        setLeft(overview);
        setCenter(center);
        setBottom(footer);
    }

    @Override
    public void setupBindings() {
    }
}
