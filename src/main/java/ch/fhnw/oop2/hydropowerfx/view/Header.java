package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Header extends HBox implements ViewMixin {

    private final RootPM rootPM;

    private VBox labelArea;
    private ImageView imageArea;
    private Label titleLabel;
    private Label nameLabel;
    private Label powerLabel;
    private Label startOfOperationFirstLabel;

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
        // label area
        labelArea = new VBox();

        titleLabel = new Label("title");
        nameLabel = new Label("name");
        powerLabel = new Label("power");
        startOfOperationFirstLabel = new Label("startofopblalba");

        // image area
        Image image = new Image("http://consumersfederation.org.au/wp-content/uploads/2015/09/hydropower.png");
        imageArea = new ImageView();
        imageArea.setImage(image);

        imageArea.setFitHeight(70);
        imageArea.setFitWidth(70);
        imageArea.setPreserveRatio(true);

        // padding
        setPadding(new Insets(5));
        setSpacing(5);

        labelArea.getChildren().addAll(titleLabel, nameLabel, powerLabel, startOfOperationFirstLabel);
        getChildren().addAll(labelArea, imageArea);

    }

    @Override
    public void layoutControls() {

    }
}
