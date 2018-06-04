package ch.fhnw.oop2.hydropowerfx.control.yearcontrol;

import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;

class BusinessSkin extends SkinBase<BusinessControl> {
    private static final String ANGLE_DOWN = "\uf107";
    private static final String ANGLE_UP   = "\uf106";

    private static final String FONTS_CSS = "/fonts/fonts.css";
    private static final String STYLE_CSS = "style.css";

    // all parts
    private TextField editableNode;
    private Label     readOnlyNode;
    private Popup     popup;
    private Pane      dropDownChooser;
    private Button    chooserButton;

    private StackPane drawingPane;

    BusinessSkin(BusinessControl control) {
        super(control);
        initializeSelf();
        initializeParts();
        layoutParts();
        setupEventHandlers();
        setupValueChangedListeners();
        setupBindings();
    }

    private void initializeSelf() {
        String fonts = getClass().getResource(FONTS_CSS).toExternalForm();
        getSkinnable().getStylesheets().add(fonts);

        String stylesheet = getClass().getResource(STYLE_CSS).toExternalForm();
        getSkinnable().getStylesheets().add(stylesheet);
    }

    private void initializeParts() {
        editableNode = new TextField();
        editableNode.getStyleClass().add("editable-node");

        readOnlyNode = new Label();
        readOnlyNode.getStyleClass().add("read-only-node");

        chooserButton = new Button(ANGLE_DOWN);
        chooserButton.getStyleClass().add("chooser-button");

        dropDownChooser = new DropDownChooser(getSkinnable());

        popup = new Popup();
        popup.getContent().addAll(dropDownChooser);

        drawingPane = new StackPane();
        drawingPane.getStyleClass().add("drawing-pane");
    }

    private void layoutParts() {
        StackPane.setAlignment(chooserButton, Pos.CENTER_RIGHT);
        drawingPane.getChildren().addAll(editableNode, chooserButton, readOnlyNode);

        StackPane.setAlignment(editableNode, Pos.CENTER_LEFT);
        StackPane.setAlignment(readOnlyNode, Pos.CENTER_LEFT);

        getChildren().add(drawingPane);
    }

    private void setupEventHandlers() {
        editableNode.setOnAction(event -> {
            getSkinnable().convertUserInput();
        });

        chooserButton.setOnAction(event -> {
            if (popup.isShowing()) {
                popup.hide();
            } else {
                popup.show(editableNode.getScene().getWindow());
            }
        });

        popup.setOnHidden(event -> chooserButton.setText(ANGLE_DOWN));

        popup.setOnShown(event -> {
            chooserButton.setText(ANGLE_UP);
            Point2D location = editableNode.localToScreen(editableNode.getWidth() - dropDownChooser.getPrefWidth() - 3,
                                                          editableNode.getHeight() -2);

            popup.setX(location.getX());
            popup.setY(location.getY());
        });

        editableNode.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ESCAPE:
                    getSkinnable().reset();
                    event.consume();
                    break;
                case UP:
                    getSkinnable().increase();
                    event.consume();
                    break;
                case DOWN:
                    getSkinnable().decrease();
                    event.consume();
                    break;
            }
        });
    }

    private void setupValueChangedListeners() { }

    private void setupBindings() {
        readOnlyNode.textProperty().bind(getSkinnable().valueProperty().asString());
        editableNode.textProperty().bindBidirectional(getSkinnable().userFacingTextProperty());

        editableNode.visibleProperty().bind(getSkinnable().readOnlyProperty().not());
        chooserButton.visibleProperty().bind(getSkinnable().readOnlyProperty().not());
        readOnlyNode.visibleProperty().bind(getSkinnable().readOnlyProperty());
    }
}
