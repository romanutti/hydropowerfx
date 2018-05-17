package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import static ch.fhnw.oop2.hydropowerfx.presentationmodel.LanguageSwitcherPM.MultiLanguageText.*;

public class LanguageSwitcherPM {

    public enum Lang {DE, EN}

    public enum MultiLanguageText {
        WINDOW_TITLE("AquaPowerFX hydroelectric power station of Switzerland","AquaPowerFX Wasserkraftwerke der Schweiz"),
        NAME_LABEL_TEXT("Name","Name"),
        TYPE_LABEL_TEXT("Typ","Type"),
        SITE_LABEL_TEXT("Standort","Site"),
        CANTON_LABEL_TEXT("Kanton","Canton"),
        MAXWATERVOLUME_LABEL_TEXT("Wassermenge (m^3/s)","Watervolume (m^3/s)"),
        MAXPOWERMW_LABEL_TEXT("Leistung (MW)","Power (MW)"),
        STARTOFOPERATIONFIRST_LABEL_TEXT("Inbetriebnahme","Start of operation"),
        STARTOFOPERATIONLAST_LABEL_TEXT("Letzte Inbetriebnahme","Last commissioning"),
        LATITUDE_LABEL_TEXT("Breitengrad","Latitude"),
        LONGITUDE_LABEL_TEXT("Längengrad","Longitude"),
        STATUS_LABEL_TEXT("Status","Status"),
        WATERBODIES_LABEL_TEXT("Genutze Gewässer","Waterbodies"),
        IMAGEURL_LABEL_TEXT("Image Url","Image Url"),
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
    private final StringProperty nameLabelText = new SimpleStringProperty();
    private final StringProperty typeLabelText = new SimpleStringProperty();
    private final StringProperty siteLabelText = new SimpleStringProperty();
    private final StringProperty cantonLabelText = new SimpleStringProperty();
    private final StringProperty maxWaterVolumeLabelText = new SimpleStringProperty();
    private final StringProperty maxPowerMwLabelText = new SimpleStringProperty();
    private final StringProperty startOfOperationFirstLabelText = new SimpleStringProperty();
    private final StringProperty startOfOperationLastLabelText = new SimpleStringProperty();
    private final StringProperty latitudeLabelText = new SimpleStringProperty();
    private final StringProperty longitudeLabelText = new SimpleStringProperty();
    private final StringProperty statusLabelText = new SimpleStringProperty();
    private final StringProperty waterbodiesLabelText = new SimpleStringProperty();
    private final StringProperty imageUrlLabelText = new SimpleStringProperty();

    private final StringProperty germanButtonText = new SimpleStringProperty();
    private final StringProperty englishButtonText = new SimpleStringProperty();

    public LanguageSwitcherPM() {
        setLanguage(Lang.EN);
    }

    public void setLanguage(Lang lang) {
        setApplicationTitle(WINDOW_TITLE.getText(lang));
        setNameLabelText(NAME_LABEL_TEXT.getText(lang));
        setTypeLabelText(TYPE_LABEL_TEXT.getText(lang));
        setSiteLabelText(SITE_LABEL_TEXT.getText(lang));
        setCantonLabelText(CANTON_LABEL_TEXT.getText(lang));
        setMaxWaterVolumeLabelText(MAXWATERVOLUME_LABEL_TEXT.getText(lang));
        setMaxPowerMwLabelText(MAXPOWERMW_LABEL_TEXT.getText(lang));
        setStartOfOperationFirstLabelText(STARTOFOPERATIONFIRST_LABEL_TEXT.getText(lang));
        setStartOfOperationLastLabelText(STARTOFOPERATIONLAST_LABEL_TEXT.getText(lang));
        setLatitudeLabelText(LATITUDE_LABEL_TEXT.getText(lang));
        setLongitudeLabelText(LONGITUDE_LABEL_TEXT.getText(lang));
        setStatusLabelText(STATUS_LABEL_TEXT.getText(lang));
        setWaterbodiesLabelText(WATERBODIES_LABEL_TEXT.getText(lang));
        setImageUrlLabelText(IMAGEURL_LABEL_TEXT.getText(lang));
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

    public String getTypeLabelText() {
        return typeLabelText.get();
    }

    public StringProperty typeLabelTextProperty() {
        return typeLabelText;
    }

    public void setTypeLabelText(String typeLabelText) {
        this.typeLabelText.set(typeLabelText);
    }

    public String getSiteLabelText() {
        return siteLabelText.get();
    }

    public StringProperty siteLabelTextProperty() {
        return siteLabelText;
    }

    public void setSiteLabelText(String siteLabelText) {
        this.siteLabelText.set(siteLabelText);
    }

    public String getCantonLabelText() {
        return cantonLabelText.get();
    }

    public StringProperty cantonLabelTextProperty() {
        return cantonLabelText;
    }

    public void setCantonLabelText(String cantonLabelText) {
        this.cantonLabelText.set(cantonLabelText);
    }

    public String getMaxWaterVolumeLabelText() {
        return maxWaterVolumeLabelText.get();
    }

    public StringProperty maxWaterVolumeLabelTextProperty() {
        return maxWaterVolumeLabelText;
    }

    public void setMaxWaterVolumeLabelText(String maxWaterVolumeLabelText) {
        this.maxWaterVolumeLabelText.set(maxWaterVolumeLabelText);
    }

    public String getMaxPowerMwLabelText() {
        return maxPowerMwLabelText.get();
    }

    public StringProperty maxPowerMwLabelTextProperty() {
        return maxPowerMwLabelText;
    }

    public void setMaxPowerMwLabelText(String maxPowerMwLabelText) {
        this.maxPowerMwLabelText.set(maxPowerMwLabelText);
    }

    public String getStartOfOperationFirstLabelText() {
        return startOfOperationFirstLabelText.get();
    }

    public StringProperty startOfOperationFirstLabelTextProperty() {
        return startOfOperationFirstLabelText;
    }

    public void setStartOfOperationFirstLabelText(String startOfOperationFirstLabelText) {
        this.startOfOperationFirstLabelText.set(startOfOperationFirstLabelText);
    }

    public String getStartOfOperationLastLabelText() {
        return startOfOperationLastLabelText.get();
    }

    public StringProperty startOfOperationLastLabelTextProperty() {
        return startOfOperationLastLabelText;
    }

    public void setStartOfOperationLastLabelText(String startOfOperationLastLabelText) {
        this.startOfOperationLastLabelText.set(startOfOperationLastLabelText);
    }

    public String getLatitudeLabelText() {
        return latitudeLabelText.get();
    }

    public StringProperty latitudeLabelTextProperty() {
        return latitudeLabelText;
    }

    public void setLatitudeLabelText(String latitudeLabelText) {
        this.latitudeLabelText.set(latitudeLabelText);
    }

    public String getLongitudeLabelText() {
        return longitudeLabelText.get();
    }

    public StringProperty longitudeLabelTextProperty() {
        return longitudeLabelText;
    }

    public void setLongitudeLabelText(String longitudeLabelText) {
        this.longitudeLabelText.set(longitudeLabelText);
    }

    public String getStatusLabelText() {
        return statusLabelText.get();
    }

    public StringProperty statusLabelTextProperty() {
        return statusLabelText;
    }

    public void setStatusLabelText(String statusLabelText) {
        this.statusLabelText.set(statusLabelText);
    }

    public String getWaterbodiesLabelText() {
        return waterbodiesLabelText.get();
    }

    public StringProperty waterbodiesLabelTextProperty() {
        return waterbodiesLabelText;
    }

    public void setWaterbodiesLabelText(String waterbodiesLabelText) {
        this.waterbodiesLabelText.set(waterbodiesLabelText);
    }

    public String getImageUrlLabelText() {
        return imageUrlLabelText.get();
    }

    public StringProperty imageUrlLabelTextProperty() {
        return imageUrlLabelText;
    }

    public void setImageUrlLabelText(String imageUrlLabelText) {
        this.imageUrlLabelText.set(imageUrlLabelText);
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

