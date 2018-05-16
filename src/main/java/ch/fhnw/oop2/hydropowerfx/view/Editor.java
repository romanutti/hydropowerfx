package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class Editor extends GridPane implements ViewMixin {
    private final RootPM rootPM;

    private Label nameLabel;
    private Label typeLabel;
    private Label siteLabel;
    private Label cantonLabel;
    private Label maxWaterVolumeLabel;
    private Label maxPowerMwLabel;
    private Label startOfOperationFirstLabel;
    private Label startOfOperationLastLabel;
    private Label latitudeLabel;
    private Label longitudeLabel;
    private Label statusLabel;
    private Label waterbodiesLabel;
    private Label imageUrlLabel;

    private TextField nameTextField;
    private TextField typeTextField;
    private TextField siteTextField;
    private TextField cantonTextField;
    private TextField maxWaterVolumeTextField;
    private TextField maxPowerMwTextField;
    private TextField startOfOperationFirstTextField;
    private TextField startOfOperationLastTextField;
    private TextField latitudeTextField;
    private TextField longitudeTextField;
    private TextField statusTextField;
    private TextField waterbodiesTextField;
    private TextField imageUrlTextField;


    public Editor(RootPM rootPM) {
        this.rootPM = rootPM;
        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("editor");
    }

    @Override
    public void initializeControls() {
        nameLabel = new Label("Name");
        typeLabel = new Label("Typ");
        siteLabel = new Label("Standort");
        cantonLabel = new Label("Kanton");
        //TODO: add square to WaterVolumeLabel
        maxWaterVolumeLabel = new Label("Wassermenge(m^3/s)");
        maxPowerMwLabel = new Label("Leistung(MW)");
        startOfOperationFirstLabel = new Label("Inbetriebnahme");
        startOfOperationLastLabel = new Label("Letzte Inbetriebnahme");
        latitudeLabel = new Label("Breitengrad");
        longitudeLabel = new Label("Längengrad");
        statusLabel = new Label("Status");
        waterbodiesLabel = new Label("Genutzte Gewässer");
        imageUrlLabel = new Label("Image Url");

        nameTextField = new TextField();
        typeTextField = new TextField();
        siteTextField = new TextField();
        cantonTextField = new TextField();
        maxWaterVolumeTextField = new TextField();
        maxPowerMwTextField = new TextField();
        startOfOperationFirstTextField = new TextField();
        startOfOperationLastTextField = new TextField();
        latitudeTextField = new TextField();
        longitudeTextField = new TextField();
        statusTextField = new TextField();
        waterbodiesTextField = new TextField();
        imageUrlTextField = new TextField();

    }

    @Override
    public void layoutControls() {
        // Set horizontal gap
        setHgap(10);
        // Set vertical gap
        setVgap(3);
        // Set padding

        setPadding(new Insets(5,5,5,5));
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.NEVER);
      //  cc.setPrefWidth(150);
        getColumnConstraints().addAll(cc, cc, cc, cc);

        RowConstraints rc = new RowConstraints();
        rc.setVgrow(Priority.NEVER);
        getRowConstraints().addAll(rc, rc, rc, rc);


        add(nameLabel,0,0);
        add(siteLabel,0,1);
        add(maxWaterVolumeLabel,0,2);
        add(startOfOperationFirstLabel,0,3);
        add(latitudeLabel,0,4);
        add(statusLabel,0,5);
        add(waterbodiesLabel,0,6);
        add(imageUrlLabel,0,7);

        add(typeLabel,2,0);
        add(cantonLabel,2,1);
        add(maxPowerMwLabel,2,2);
        add(startOfOperationLastLabel,2,3);
        add(longitudeLabel,2,4);

        add(nameTextField,1,0);
        add(siteTextField,1,1);
        add(maxWaterVolumeTextField,1,2);
        add(startOfOperationFirstTextField,1,3);
        add(latitudeTextField,1,4);
        add(statusTextField,1,5);
        add(waterbodiesTextField,1,6);
        add(imageUrlTextField,1,7);

        add(typeTextField,3,0);
        add(cantonTextField,3,1);
        add(maxPowerMwTextField,3,2);
        add(startOfOperationLastTextField,3,3);
        add(longitudeTextField,3,4);

    }
}
