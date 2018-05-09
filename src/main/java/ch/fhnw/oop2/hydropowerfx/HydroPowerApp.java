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

		RootPM rootPM    = new RootPM();
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
