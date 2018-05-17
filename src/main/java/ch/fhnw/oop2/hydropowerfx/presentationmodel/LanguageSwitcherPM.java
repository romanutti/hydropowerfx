package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import static ch.fhnw.oop2.hydropowerfx.presentationmodel.LanguageSwitcherPM.MultiLanguageText.*;

public class LanguageSwitcherPM {

    // TODO: Enums waren initial nicht public
    public enum Lang {DE, EN}

    public enum MultiLanguageText {
        WINDOW_TITLE("AquaPowerFX hydroelectric power station of Switzerland","AquaPowerFX Wasserkraftwerke der Schweiz"),
        LABEL_TEXT("some Label", "eine Beschriftung"),
        GERMAN_BUTTON_TEXT("German", "Deutsch"),
        ENGLISH_BUTTON_TEXT("English", "Englisch");

        private final String englishLabel;
        private final String germanLabel;

        MultiLanguageText(String englishLabel, String germanLabel) {
            this.englishLabel = englishLabel;
            this.germanLabel = germanLabel;
        }

        public String getEnglishLabel() {
            return englishLabel;
        }

        public String getGermanLabel() {
            return germanLabel;
        }

        public String getText(Lang lang) {
            switch (lang) {
                case DE:
                    return getGermanLabel();
                case EN:
                    return getEnglishLabel();
                default:
                    return getEnglishLabel();
            }
        }
    }

    private final StringProperty applicationTitle = new SimpleStringProperty();

    private final StringProperty labelText = new SimpleStringProperty();
    private final StringProperty germanButtonText = new SimpleStringProperty();
    private final StringProperty englishButtonText = new SimpleStringProperty();

    public LanguageSwitcherPM() {
        setLanguage(Lang.EN);
    }

    public void setLanguage(Lang lang) {
        setApplicationTitle(WINDOW_TITLE.getText(lang));
        setLabelText(LABEL_TEXT.getText(lang));
        setGermanButtonText(GERMAN_BUTTON_TEXT.getText(lang));
        setEnglishButtonText(ENGLISH_BUTTON_TEXT.getText(lang));
    }

    public String getApplicationTitle() {
        return applicationTitle.get();
    }

    public StringProperty applicationTitleProperty() {
        return applicationTitle;
    }

    private void setApplicationTitle(String applicationTitle) {
        this.applicationTitle.set(applicationTitle);
    }

    public String getLabelText() {
        return labelText.get();
    }

    public StringProperty labelTextProperty() {
        return labelText;
    }

    private void setLabelText(String labelText) {
        this.labelText.set(labelText);
    }

    public String getGermanButtonText() {
        return germanButtonText.get();
    }

    public StringProperty germanButtonTextProperty() {
        return germanButtonText;
    }

    private void setGermanButtonText(String germanButtonText) {
        this.germanButtonText.set(germanButtonText);
    }

    public String getEnglishButtonText() {
        return englishButtonText.get();
    }

    public StringProperty englishButtonTextProperty() {
        return englishButtonText;
    }

    private void setEnglishButtonText(String englishButtonText) {
        this.englishButtonText.set(englishButtonText);
    }
}

