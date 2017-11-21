package ch.fhnw.oop2.electriccarsfx;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import ch.fhnw.oop2.electriccarsfx.presentationmodel.PresentationModel;
import ch.fhnw.oop2.electriccarsfx.view.ApplicationUI;

public class ElectricCarsApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		PresentationModel pm = new PresentationModel();
		Parent rootPanel     = new ApplicationUI(pm);

		Scene scene = new Scene(rootPanel);

		primaryStage.titleProperty().bind(pm.applicationTitleProperty());
		primaryStage.setScene(scene);

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
