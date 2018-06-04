package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.PowerStationPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.util.converter.NumberStringConverter;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

public class MapView extends HBox implements ViewMixin {

    // contants
    private static final String DEFAULT_CENTER_LOCATION_ = "Schweiz";
    private static final String GOOGLE_API_KEY = "AIzaSyAzMrORyFTTVCWFlAeKcSfhWr7lkq3VRnQ";

    // model
    private final RootPM rootPM;

    // gui elements
    private ImageView imageArea;


    public MapView(RootPM rootPM) {
        this.rootPM = rootPM;
        init();
    }


    @Override
    public void initializeSelf() {
        getStyleClass().add("map");
    }

    @Override
    public void initializeControls() {
        // image area
        imageArea = new ImageView();
    }

    @Override
    public void layoutControls() {

        // sizing
        setMinHeight(150);
        setPrefWidth(220);
        setMaxWidth(220);
        setPrefHeight(215);
        setMaxHeight(215);
        //setHgrow(imageArea, Priority.ALWAYS);

        // image area
        imageArea.setFitHeight(210);
        //imageArea.setPreserveRatio(true);
        //imageArea.setSmooth(false);

        // padding
        setPadding(new Insets(5));
        setSpacing(5);

        getChildren().addAll(imageArea);

    }

    @Override
    public void setupValueChangedListeners() {
        PowerStationPM proxy = rootPM.getPowerStationProxy();

        // refresh Image
        // change of center
        proxy.siteProperty().addListener((observable, oldValue, newValue) -> {
            try {
                URL url = new URL(getImageUrl(newValue, null, null));
                imageArea.setImage(new Image(url.toString()));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        });
        // change of latitude
        proxy.latitudeProperty().addListener((observable, oldValue, newValue) -> {
            try {
                URL url = new URL(getImageUrl(null, newValue.toString(), null));
                imageArea.setImage(new Image(url.toString()));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        });
        // change of longitude
        proxy.longitudeProperty().addListener((observable, oldValue, newValue) -> {
            try {
                URL url = new URL(getImageUrl(null, null, newValue.toString()));
                imageArea.setImage(new Image(url.toString()));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        });

    }

    private String getImageUrl(String site, String latitude, String longitude) {
        PowerStationPM proxy = rootPM.getPowerStationProxy();
        String marker = "";

        site = (site == null) ? proxy.getSite() : site;
        latitude = (latitude == null) ? String.valueOf(proxy.getLatitude()) : latitude;
        longitude = (longitude == null) ? String.valueOf(proxy.getLongitude()) : longitude;

        try {
            // replace malformed url components
            site = URLEncoder.encode(site, "UTF-8");
            latitude = latitude.replaceAll("\\.", "%2E");
            longitude = longitude.replaceAll("\\.", "%2E");
            marker = latitude + "," + longitude;
        } catch (UnsupportedEncodingException e) {
            site = DEFAULT_CENTER_LOCATION_;
        }
        return "http://maps.googleapis.com/maps/api/staticmap?center=" + site + "&scale=1&size=300x200&maptype=roadmap&format=png&visual_refresh=true&markers=size:mid%7Ccolor:0xff0000%7Clabel:%7C" + marker + "&key=" + GOOGLE_API_KEY;

    }
}
