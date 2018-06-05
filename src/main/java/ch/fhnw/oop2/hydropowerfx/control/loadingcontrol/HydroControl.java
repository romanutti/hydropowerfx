package ch.fhnw.oop2.hydropowerfx.control.loadingcontrol;

import java.net.URL;
import java.util.List;
import java.util.Locale;

import ch.fhnw.oop2.hydropowerfx.control.loadingcontrol.demo.PresentationModel;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleablePropertyFactory;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;


/**
 * ToDo: CustomControl kurz beschreiben
 *
 * @author Daniel Y.Navarro & Arbnor Aliaj
 */
//Todo: Umbenennen.
public class HydroControl extends GridPane {
    // needed for StyleableProperties
    private static final StyleablePropertyFactory<HydroControl> FACTORY = new StyleablePropertyFactory<>(Region.getClassCssMetaData());

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return FACTORY.getCssMetaData();
    }

    private static final Locale CH = new Locale("de", "CH");

    private static final double ARTBOARD_WIDTH = 813;  // Todo: Breite der "Zeichnung" aus dem Grafik-Tool übernehmen
    private static final double ARTBOARD_HEIGHT = 813;  // Todo: Anpassen an die Breite der Zeichnung

    private static final double ASPECT_RATIO = ARTBOARD_WIDTH / ARTBOARD_HEIGHT;

    private static final double MINIMUM_WIDTH = 300;    // Todo: Anpassen
    private static final double MINIMUM_HEIGHT = MINIMUM_WIDTH / ASPECT_RATIO;

    private static final double MAXIMUM_WIDTH = 1000;    // Todo: Anpassen


    PresentationModel pm = new PresentationModel();


    private Image img;
    private ImageView imgView;


    private Image waterfallparticles2;
    private ImageView waterfallparticles2View;


    private Image light;
    private ImageView lightView;

    private Image smoke;
    private ImageView smokeView;

    private Image car;
    private ImageView carView;


    private URL carSound;
    private AudioClip carClip;

    private URL waterSound;
    private AudioClip waterClip;

    private URL electricitySound;
    private AudioClip elecricityClip;

    private static final CssMetaData<HydroControl, Color> BASE_COLOR_META_DATA = FACTORY.createColorCssMetaData("-base-color", s -> s.baseColor);

    // Transitions
    PathTransition pathTransition1 = new PathTransition();
    PathTransition pathTransition2 = new PathTransition();
    PathTransition pathTransition3 = new PathTransition();
    PathTransition pathTransition5 = new PathTransition();
    PathTransition pathTransition6 = new PathTransition();


    private final StyleableObjectProperty<Color> baseColor = new SimpleStyleableObjectProperty<Color>(BASE_COLOR_META_DATA, this, "baseColor") {
        @Override
        protected void invalidated() {
            setStyle(getCssMetaData().getProperty() + ": " + colorToCss(get()) + ";");
            applyCss();
        }
    };


    // needed for resizing
    private Pane drawingPane;

    public HydroControl() {
        initializeSelf();
        initializeParts();
        initializeDrawingPane();
        layoutParts();
        setupEventHandlers();
        setupValueChangeListeners();
        setupBindings();
    }

    private void initializeSelf() {
        // load stylesheets
        String fonts = getClass().getResource("/fonts/fonts.css").toExternalForm();
        getStylesheets().add(fonts);

        String stylesheet = getClass().getResource("style.css").toExternalForm();
        getStylesheets().add(stylesheet);

        getStyleClass().add("hydro-control");
    }

    private void initializeParts() {
        double center = ARTBOARD_WIDTH * 0.5;


        img = new Image(HydroControl.class.getResource("/imgs/Hydro.png").toExternalForm());
        imgView = new ImageView(img);
        imgView.setFitHeight(700);
        imgView.setFitWidth(700);

        imgView.setPreserveRatio(true);

        ///////////////////

        waterfallparticles2 = new Image(HydroControl.class.getResource("/imgs/waterfall_particles2.png").toExternalForm());
        waterfallparticles2View = new ImageView(waterfallparticles2);
        waterfallparticles2View.setX(-550);
        waterfallparticles2View.setY(-310);

        Path path1 = new Path();
        path1.getElements().add(new MoveTo(1, -60));
        path1.getElements().add(new CubicCurveTo(0, 0, 0, 0, 0, 0));

        pathTransition1.setDuration(Duration.millis(2000));
        pathTransition1.setPath(path1);
        pathTransition1.setCycleCount(Timeline.INDEFINITE);
        pathTransition1.setNode(waterfallparticles2View);
        pathTransition1.play();

        ///////////////////
        light = new Image(HydroControl.class.getResource("/imgs/light.png").toExternalForm());
        lightView = new ImageView(light);
        lightView.setX(-220);
        lightView.setY(0);

        Path path2 = new Path();
        path2.getElements().add(new MoveTo(5, 5));
        path2.getElements().add(new CubicCurveTo(200, -10, 0, 5, 200, -30));
        pathTransition2.setDuration(Duration.millis(2000));
        pathTransition2.setPath(path2);
        pathTransition2.setNode(lightView);
        pathTransition2.setCycleCount(Timeline.INDEFINITE);
        pathTransition2.setAutoReverse(true);
        pathTransition2.play();



        ///////////////////


                smoke = new Image(HydroControl.class.getResource("/imgs/smoke.png").toExternalForm());
                smokeView = new ImageView(smoke);
                smokeView.setX(-190);
                smokeView.setY(120);

                Path path3 = new Path();
                path3.getElements().add(new MoveTo(1, 75));
                path3.getElements().add(new CubicCurveTo(0, 0, 0, 0, 0, 0));
                pathTransition3 = new PathTransition();
                pathTransition3.setDuration(Duration.millis(2000));
                pathTransition3.setCycleCount(Timeline.INDEFINITE);
                pathTransition3.setPath(path3);
                pathTransition3.setNode(smokeView);
                pathTransition3.play();




        ///////////////////

        car = new Image(HydroControl.class.getResource("/imgs/car.png").toExternalForm());
        carView = new ImageView(car);
        carView.setX(-250);
        carView.setY(-215);
        Path path5 = new Path();
        path5.getElements().add(new MoveTo(450, -260));
        path5.getElements().add(new CubicCurveTo(0, 0, 0, 0, 0, 0));
        pathTransition5 = new PathTransition();
        pathTransition5.setDuration(Duration.millis(2000));
        pathTransition5.setCycleCount(Timeline.INDEFINITE);
        pathTransition5.setPath(path5);
        pathTransition5.setNode(carView);//
        pathTransition5.play();

        ///////////////////SOUND

        carSound = getClass().getResource("/sounds/car.wav");
        carClip = new AudioClip(carSound.toExternalForm());


        waterSound = getClass().getResource("/sounds/waterfall.wav");
        waterClip = new AudioClip(waterSound.toExternalForm());


        electricitySound = getClass().getResource("/sounds/electricity.wav");
        elecricityClip = new AudioClip(electricitySound.toExternalForm());

    }

    private void initializeDrawingPane() {
        drawingPane = new GridPane();
        drawingPane.getStyleClass().add("drawing-pane");
        drawingPane.setMaxSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
        drawingPane.setMinSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
        drawingPane.setPrefSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
    }

    private void layoutParts() {
        drawingPane.getChildren().addAll(imgView, waterfallparticles2View, lightView, smokeView, carView);

        getChildren().add(drawingPane);
    }

    private void setupEventHandlers() {


    }

    private void setupValueChangeListeners() {
        pm.waterValueProperty().addListener((observable, oldValue, newValue) -> {
            pathTransition1.stop();
            pathTransition1.setDuration(Duration.millis((newValue.doubleValue())));
            pathTransition1.play();
            waterClip.play(8.0);
        });

        pm.powerValueProperty().addListener((observable, oldValue, newValue) -> {
            pathTransition2.stop();
            pathTransition2.setDuration(Duration.millis((newValue.doubleValue())));
            pathTransition2.play();
            elecricityClip.play(8.0);
        });

        pm.isOnValueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                pathTransition1.stop();
                pathTransition2.stop();
                pathTransition3.stop();
                pathTransition5.stop();


                carClip.stop();
                elecricityClip.stop();
                waterClip.stop();


            } else {
                pathTransition1.play();
                pathTransition2.play();
                pathTransition3.play();
                pathTransition5.play();
                carClip.play(8);


            }

        });


    }

    private void setupBindings() {


    }

    ///////////////////////////////////////


    //resize by scaling
    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        resize();
    }

    private void resize() {
        Insets padding = getPadding();
        double availableWidth = getWidth() - padding.getLeft() - padding.getRight();
        double availableHeight = getHeight() - padding.getTop() - padding.getBottom();

        double width = Math.max(Math.min(Math.min(availableWidth, availableHeight * ASPECT_RATIO), MAXIMUM_WIDTH), MINIMUM_WIDTH);

        double scalingFactor = width / ARTBOARD_WIDTH;

        if (availableWidth > 0 && availableHeight > 0) {
            relocateCentered();
            drawingPane.setScaleX(scalingFactor);
            drawingPane.setScaleY(scalingFactor);
        }
    }

    private void relocateCentered() {
        drawingPane.relocate((getWidth() - ARTBOARD_WIDTH) * 0.5, (getHeight() - ARTBOARD_HEIGHT) * 0.5);
    }


//
//    private Group createTicks(double cx, double cy, int numberOfTicks, double overallAngle, double tickLength, double indent, double startingAngle, String styleClass) {
//        Group group = new Group();
//
//        double degreesBetweenTicks = overallAngle == 360 ?
//                                     overallAngle /numberOfTicks :
//                                     overallAngle /(numberOfTicks - 1);
//        double outerRadius         = Math.min(cx, cy) - indent;
//        double innerRadius         = Math.min(cx, cy) - indent - tickLength;
//
//        for (int i = 0; i < numberOfTicks; i++) {
//            double angle = 180 + startingAngle + i * degreesBetweenTicks;
//
//            Point2D startPoint = pointOnCircle(cx, cy, outerRadius, angle);
//            Point2D endPoint   = pointOnCircle(cx, cy, innerRadius, angle);
//
//            Line tick = new Line(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
//            tick.getStyleClass().add(styleClass);
//            group.getChildren().add(tick);
//        }
//
//        return group;
//    }
//
//    private Group createNumberedTicks(double cx, double cy, int numberOfTicks, double overallAngle, double tickLength, double indent, double startingAngle, String styleClass) {
//            Group group = new Group();
//
//            int width = 30;
//            double degreesBetweenTicks = overallAngle == 360 ?
//                    overallAngle / numberOfTicks :
//                    overallAngle / (numberOfTicks - 1);
//            double outerRadius = Math.min(cx - width, cy - width) - indent;
//            double innerRadius = Math.min(cx - width, cy - width) - indent - tickLength;
//
//            for (int i = 0; i < numberOfTicks; i++) {
//                double angle = 180 + startingAngle + i * degreesBetweenTicks;
//
//                if (i % 5 == 0 && i % 2 != 0) {
//                    Point2D startPoint = pointOnCircle(cx, cy, outerRadius, angle);
//                    Point2D endPoint = pointOnCircle(cx, cy, innerRadius * 0.95, angle);
//                    Line tick = new Line(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
//                    tick.getStyleClass().add(styleClass);
//                    group.getChildren().add(tick);
//                }
//                if (i % 10 == 0) {
//                    Point2D startPoint = pointOnCircle(cx, cy, outerRadius, angle);
//                    Point2D endP

    private String colorToCss(final Color color) {
        return color.toString().replace("0x", "#");
    }


    // compute sizes

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

    @Override
    protected double computePrefHeight(double width) {
        Insets padding = getPadding();
        double verticalPadding = padding.getTop() + padding.getBottom();

        return ARTBOARD_HEIGHT + verticalPadding;
    }

    // alle getter und setter  (generiert via "Code -> Generate... -> Getter and Setter)

    // ToDo: ersetzen durch die Getter und Setter Ihres CustomControls


    public static StyleablePropertyFactory<HydroControl> getFACTORY() {
        return FACTORY;
    }

    public static Locale getCH() {
        return CH;
    }

    public static double getArtboardWidth() {
        return ARTBOARD_WIDTH;
    }

    public static double getArtboardHeight() {
        return ARTBOARD_HEIGHT;
    }

    public static double getAspectRatio() {
        return ASPECT_RATIO;
    }

    public static double getMinimumWidth() {
        return MINIMUM_WIDTH;
    }

    public static double getMinimumHeight() {
        return MINIMUM_HEIGHT;
    }

    public static double getMaximumWidth() {
        return MAXIMUM_WIDTH;
    }

    public PresentationModel getPm() {
        return pm;
    }

    public void setPm(PresentationModel pm) {
        this.pm = pm;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public ImageView getImgView() {
        return imgView;
    }

    public void setImgView(ImageView imgView) {
        this.imgView = imgView;
    }


    public Image getWaterfallparticles2() {
        return waterfallparticles2;
    }

    public void setWaterfallparticles2(Image waterfallparticles2) {
        this.waterfallparticles2 = waterfallparticles2;
    }

    public ImageView getWaterfallparticles2View() {
        return waterfallparticles2View;
    }

    public void setWaterfallparticles2View(ImageView waterfallparticles2View) {
        this.waterfallparticles2View = waterfallparticles2View;
    }



    public Image getLight() {
        return light;
    }

    public void setLight(Image light) {
        this.light = light;
    }

    public ImageView getLightView() {
        return lightView;
    }

    public void setLightView(ImageView lightView) {
        this.lightView = lightView;
    }

    public Image getSmoke() {
        return smoke;
    }

    public void setSmoke(Image smoke) {
        this.smoke = smoke;
    }

    public ImageView getSmokeView() {
        return smokeView;
    }

    public void setSmokeView(ImageView smokeView) {
        this.smokeView = smokeView;
    }

    public Image getCar() {
        return car;
    }

    public void setCar(Image car) {
        this.car = car;
    }

    public ImageView getCarView() {
        return carView;
    }

    public void setCarView(ImageView carView) {
        this.carView = carView;
    }


    public static CssMetaData<HydroControl, Color> getBaseColorMetaData() {
        return BASE_COLOR_META_DATA;
    }

    public PathTransition getPathTransition5() {
        return pathTransition2;
    }

    public void setPathTransition5(PathTransition pathTransition5) {
        this.pathTransition2 = pathTransition5;
    }

    public PathTransition getPathTransition1() {
        return pathTransition1;
    }

    public void setPathTransition1(PathTransition pathTransition1) {
        this.pathTransition1 = pathTransition1;
    }

    public PathTransition getPathTransition2() {
        return pathTransition2;
    }

    public void setPathTransition2(PathTransition pathTransition2) {
        this.pathTransition2 = pathTransition2;
    }

    public PathTransition getPathTransition3() {
        return pathTransition3;
    }

    public void setPathTransition3(PathTransition pathTransition3) {
        this.pathTransition3 = pathTransition3;
    }

    public Color getBaseColor() {
        return baseColor.get();
    }

    public StyleableObjectProperty<Color> baseColorProperty() {
        return baseColor;
    }

    public void setBaseColor(Color baseColor) {
        this.baseColor.set(baseColor);
    }

    public Pane getDrawingPane() {
        return drawingPane;
    }

    public void setDrawingPane(Pane drawingPane) {
        this.drawingPane = drawingPane;
    }

}
