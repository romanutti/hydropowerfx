package ch.fhnw.oop2.hydropowerfx;

import ch.fhnw.oop2.hydropowerfx.domain.Canton;
import ch.fhnw.oop2.hydropowerfx.domain.PowerStation;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import ch.fhnw.oop2.hydropowerfx.view.RootPanel;

import java.util.ArrayList;
import java.util.List;

public class HydroPowerApp extends Application {

	@Override
	public void start(Stage primaryStage) {

		PowerStation powerStation = new PowerStation(1, "Test", PowerStation.Type.L, "Zürich", Canton.ZH, 1.1, 2.2, 3.3, 4.4, 5.5,6.6, "In Betrieb", 7.7,"www.fhnw.ch/images");
		List<PowerStation> data = new ArrayList<>();
		data.add(powerStation);

		RootPM rootPM    = new RootPM(data);
		Parent rootPanel = new RootPanel(rootPM);

		Scene scene = new Scene(rootPanel);

		primaryStage.titleProperty().bind(rootPM.applicationTitleProperty());
		primaryStage.setScene(scene);

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
