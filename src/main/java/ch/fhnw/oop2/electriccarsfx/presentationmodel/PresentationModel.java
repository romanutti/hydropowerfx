package ch.fhnw.oop2.electriccarsfx.presentationmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PresentationModel {
    private final StringProperty applicationTitle = new SimpleStringProperty("JavaFX Application");
    private final StringProperty greeting         = new SimpleStringProperty("Hello World!");


    // all getters and setters
    public String getApplicationTitle() {
        return applicationTitle.get();
    }

    public StringProperty applicationTitleProperty() {
        return applicationTitle;
    }

    public void setApplicationTitle(String applicationTitle) {
        this.applicationTitle.set(applicationTitle);
    }

    public String getGreeting() {
        return greeting.get();
    }

    public StringProperty greetingProperty() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting.set(greeting);
    }
}
