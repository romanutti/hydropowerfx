package ch.fhnw.oop2.hydropowerfx;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import ch.fhnw.oop2.hydropowerfx.view.ApplicationUI;

public class HydroPowerApp extends Application {

	@Override
	public void start(Stage primaryStage) {

		RootPM model    = new RootPM();
		Parent rootPanel = new ApplicationUI(model);

		Scene scene = new Scene(rootPanel);

		primaryStage.titleProperty().bind(model.applicationTitleProperty());
		primaryStage.setScene(scene);

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
