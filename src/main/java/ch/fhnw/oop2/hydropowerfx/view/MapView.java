package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.PowerStationPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.MalformedURLException;
import java.net.URL;

public class MapView extends HBox implements ViewMixin {

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

        /********************************************************************************
         LAYOUT
         Layouting sources:
         - View classes: via css
         - Added items: via java
         ********************************************************************************/

        /********************************************************************************
         IMAGE area formatting
         *******************************************************************************/
        imageArea.setFitHeight(220);


        getChildren().addAll(imageArea);

    }

    @Override
    public void setupValueChangedListeners() {
        PowerStationPM proxy = rootPM.getPowerStationProxy();

        /********************************************************************************
         REFRESH IMAGE functionality
         ********************************************************************************/
        // change of center
        proxy.siteProperty().addListener((observable, oldValue, newValue) -> {
            try {
                URL url = new URL(rootPM.getImageUrl(newValue, null, null));
                imageArea.setImage(new Image(url.toString()));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        });
        // change of latitude
        proxy.latitudeProperty().addListener((observable, oldValue, newValue) -> {
            try {
                URL url = new URL(rootPM.getImageUrl(null, newValue.toString(), null));
                imageArea.setImage(new Image(url.toString()));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        });
        // change of longitude
        proxy.longitudeProperty().addListener((observable, oldValue, newValue) -> {
            try {
                URL url = new URL(rootPM.getImageUrl(null, null, newValue.toString()));
                imageArea.setImage(new Image(url.toString()));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        });

    }
}
