package ch.fhnw.oop2.hydropowerfx;

import ch.fhnw.oop2.hydropowerfx.control.loadingcontrol.demo.DemoStarter;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import ch.fhnw.oop2.hydropowerfx.view.ApplicationUI;

public class HydroPowerApp extends Application {

	// Just a counter to create some delay while showing preloader.
	private static final int COUNT_LIMIT = 80000;

	private Stage applicationStage;

	public static void main(String[] args) {
		LauncherImpl.launchApplication(HydroPowerApp.class, DemoStarter.class, args);
	}


	@Override
	public void init() throws Exception {
		for (int i = 0; i < COUNT_LIMIT; i++) {
			double progress = (100 * i) / COUNT_LIMIT;
			LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
		}
	}

	@Override
	public void start(Stage primaryStage) {

		applicationStage = primaryStage;

		RootPM model    = new RootPM();
		Parent rootPanel = new ApplicationUI(model);

		Scene scene = new Scene(rootPanel);

		primaryStage.titleProperty().bind(model.getLanguageSwitcherPM().applicationTitleProperty());
		primaryStage.setScene(scene);
		primaryStage.setHeight(800);
		primaryStage.setMinWidth(1100);
		primaryStage.setMinHeight(800);

		primaryStage.show();

        // set selected id
		model.setSelectedId(model.getFirstPowerStation());
	}

}
