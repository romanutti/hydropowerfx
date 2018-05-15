package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.scene.layout.HBox;

public class Header extends HBox implements ViewMixin {
    private final RootPM rootPM;

    public Header(RootPM rootPM) {
        this.rootPM = rootPM;
        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("header");
    }

    @Override
    public void initializeControls() {
    }

    @Override
    public void layoutControls() {

    }
}
