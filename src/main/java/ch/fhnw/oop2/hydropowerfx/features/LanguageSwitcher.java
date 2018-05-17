package ch.fhnw.oop2.hydropowerfx.features;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import static ch.fhnw.oop2.hydropowerfx.features.LanguageSwitcher.MultiLanguageText.*;

public class LanguageSwitcher {

    //TODO: Correct implementation and relation to RootPM

    enum Lang {DE, EN}

    enum MultiLanguageText {
        WINDOW_TITLE       ("AquaPowerFX hydroelectric power station of Switzerland","AquaPowerFX Wasserkraftwerke der Schweiz"),
        NAME_LABEL_TEXT    ("Name","Name"),
        GERMAN_BUTTON_TEXT ("German","Deutsch"),
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

        public String getText(Lang lang){
            switch (lang){
                case DE:
                    return getGermanLabel();
                case EN:
                    return getEnglishLabel();
                default:
                    return getGermanLabel();
            }
        }
    }

    private final StringProperty applicationTitle  = new SimpleStringProperty();
    private final StringProperty nameLabelText  = new SimpleStringProperty();
    private final StringProperty germanButtonText  = new SimpleStringProperty();
    private final StringProperty englishButtonText = new SimpleStringProperty();


    public LanguageSwitcher() {
        setLanguage(Lang.DE);
    }



    public void setLanguage(Lang lang) {
        setApplicationTitle(WINDOW_TITLE.getText(lang));
        setNameLabelText(NAME_LABEL_TEXT.getText(lang));
        setGermanButtonText(GERMAN_BUTTON_TEXT.getText(lang));
        setEnglishButtonText(ENGLISH_BUTTON_TEXT.getText(lang));

    }


    public String getApplicationTitle() {
        return applicationTitle.get();
    }

    public StringProperty applicationTitleProperty() {
        return applicationTitle;
    }

    public void setApplicationTitle(String applicationTitle) {
        this.applicationTitle.set(applicationTitle);
    }


    public String getNameLabelText() {
        return nameLabelText.get();
    }

    public StringProperty nameLabelTextProperty() {
        return nameLabelText;
    }

    public void setNameLabelText(String nameLabelText) {
        this.nameLabelText.set(nameLabelText);
    }

    public String getGermanButtonText() {
        return germanButtonText.get();
    }

    public StringProperty germanButtonTextProperty() {
        return germanButtonText;
    }

    public void setGermanButtonText(String germanButtonText) {
        this.germanButtonText.set(germanButtonText);
    }

    public String getEnglishButtonText() {
        return englishButtonText.get();
    }

    public StringProperty englishButtonTextProperty() {
        return englishButtonText;
    }

    public void setEnglishButtonText(String englishButtonText) {
        this.englishButtonText.set(englishButtonText);
    }
}
