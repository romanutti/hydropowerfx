package ch.fhnw.oop2.hydropowerfx.control.watercontrol.custom;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;

public class WaterLevelControl extends Region {

    private static final double ARTBOARD_WIDTH = 200;
    private static final double ARTBOARD_HEIGHT = 200;
    private static final double ARTBOARD_WAVE_HEIGHT = 10;
    private static final double ASPECT_RATIO = ARTBOARD_WIDTH / ARTBOARD_HEIGHT;
    private static final double MINIMUM_WIDTH = 50;
    private static final double MINIMUM_HEIGHT = MINIMUM_WIDTH / ASPECT_RATIO;
    private static final double MAXIMUM_WIDTH = 400;

    private Pane pane;
    private ImageView waterImageView;
    private Text textDisplay;
    private Circle backgroundClipCircle;

    private final DoubleProperty value = new SimpleDoubleProperty();

    public WaterLevelControl() {
        initializeSelf();
        initializeParts();
        initializeDrawingPane();
        layoutParts();
        setupEventHandlers();
        setupValueChangeListeners();
        setupBinding();
    }

    private void initializeSelf() {
        String stylesheet = getClass().getResource("../style.css").toExternalForm();
        getStylesheets().add(stylesheet);
    }

    private void initializeParts() {

        // Wir laden die reguläre Wellengrafik mit 500px auf 900px (Leider keine frei skalierbare Vektorgrafik, but who cares)
        Image waterImage = new Image("/images/Water.gif");

        // Diese Grafik laden wir in einen Image View
        waterImageView = new ImageView();
        waterImageView.setPreserveRatio(true);
        waterImageView.setImage(waterImage);
        waterImageView.setFitWidth(ARTBOARD_WIDTH + ARTBOARD_WAVE_HEIGHT * 2);
        waterImageView.setFitHeight(ARTBOARD_HEIGHT * 2 + ARTBOARD_WAVE_HEIGHT);

        // Um das Runde "Gauge" zu simulieren, clippen wir den Viewport auf einen Circle
        backgroundClipCircle = new Circle(ARTBOARD_WIDTH * 0.5 , ARTBOARD_HEIGHT * 0.5 , ARTBOARD_HEIGHT * 0.5);
        waterImageView.setClip(backgroundClipCircle);
        textDisplay = createCenteredText("text-display");
    }

    private void initializeDrawingPane() {
        pane = new Pane();
        pane.setMaxSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
        pane.setMinSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
        pane.setPrefSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
    }

    private void layoutParts() {
        pane.getChildren().addAll(waterImageView,textDisplay);
        getChildren().add(pane);
    }

    private void setupEventHandlers() {
        // No-op
    }

    private void setupValueChangeListeners() {
        valueProperty().addListener((observable, oldValue, newValue) -> {

            int sliderSteps = 7;

            // Relatives ausrechnen der Y Koordinate für den Viewport. Dabei muss durch die Höhe des Viewports ein Sliderstep ignoriert werden
            int yPosition = (int) (newValue.intValue() * ((ARTBOARD_HEIGHT + ARTBOARD_WAVE_HEIGHT) / (sliderSteps - 1)));

            // Es wird ein neuer Viewport erstellt und gesetzt. Falls die Arthboard ändert, muss auch die yPosition angepasst werden
            Rectangle2D viewportRectangle = new Rectangle2D(20, yPosition+200, ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
            waterImageView.setViewport(viewportRectangle);
            textDisplay.setText("Level: " + (newValue.intValue() + 1));

        });
    }

    private void setupBinding() {
        // Trigger an initial input
        valueProperty().setValue(3);
    }


    //resize by scaling
    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        Insets padding = getPadding();
        double availableWidth = getWidth() - padding.getLeft() - padding.getRight();
        double availableHeight = getHeight() - padding.getTop() - padding.getBottom();

        double width = Math.max(Math.min(Math.min(availableWidth, availableHeight * ASPECT_RATIO), MAXIMUM_WIDTH), MINIMUM_WIDTH);

        double scalingFactor = width / ARTBOARD_WIDTH;

        if (availableWidth > 0 && availableHeight > 0) {
            pane.relocate((getWidth() - ARTBOARD_WIDTH) * 0.5, (getHeight() - ARTBOARD_HEIGHT) * 0.5);
            pane.setScaleX(scalingFactor);
            pane.setScaleY(scalingFactor);
        }
    }

    @Override
    protected double computeMinWidth(double height) {
        Insets padding = getPadding();
        double horizontalPadding = padding.getLeft() + padding.getRight();

        return MINIMUM_WIDTH + horizontalPadding;
    }

    @Override
    protected double computeMinHeight(double width) {
        Insets padding = getPadding();
        double verticalPadding = padding.getTop() + padding.getBottom();

        return MINIMUM_HEIGHT + verticalPadding;
    }

    @Override
    protected double computePrefWidth(double height) {
        Insets padding = getPadding();
        double horizontalPadding = padding.getLeft() + padding.getRight();

        return ARTBOARD_WIDTH + horizontalPadding;
    }

    private Text createCenteredText(String styleClass) {
        return createCenteredText(ARTBOARD_WIDTH * 0.5, ARTBOARD_HEIGHT * 0.5, styleClass);
    }

    private Text createCenteredText(double cx, double cy, String styleClass) {
        Text text = new Text();
        text.getStyleClass().add(styleClass);
        text.setTextOrigin(VPos.CENTER);
        text.setTextAlignment(TextAlignment.CENTER);
        double width = cx > ARTBOARD_WIDTH * 0.5 ? ((ARTBOARD_WIDTH - cx) * 2.0) : cx * 2.0;
        text.setWrappingWidth(width - 6);
        text.setBoundsType(TextBoundsType.VISUAL);
        text.setY(cy + 3);
        text.setX(cx - (width / 2.0) + 3);

        return text;
    }


    @Override
    protected double computePrefHeight(double width) {
        Insets padding = getPadding();
        double verticalPadding = padding.getTop() + padding.getBottom();

        return ARTBOARD_HEIGHT + verticalPadding;
    }

    public double getValue() {
        return value.get();
    }

    public DoubleProperty valueProperty() {
        return value;
    }

    public void setValue(double value) {
        this.value.set(value);
    }
}
