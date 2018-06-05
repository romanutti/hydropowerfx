package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LanguageSwitcherPMTest {

    @Test
    void testCurrentLanguage() {
        //given
        LanguageSwitcherPM switcher = new LanguageSwitcherPM();

        //when
        switcher.setLanguage(LanguageSwitcherPM.Lang.DE);

        //then
        assertEquals(LanguageSwitcherPM.Lang.DE                                          , switcher.getCurrentLanguage());
    }

    @Test
    void testSetLangLabelToGerman() {
        //given
        LanguageSwitcherPM switcher = new LanguageSwitcherPM();

        //when
        switcher.setLanguage(LanguageSwitcherPM.Lang.DE);

        //then
        assertEquals(LanguageSwitcherPM.Lang.DE.getGermanLangLabel()                     , switcher.getGermanLangLabel());
        assertEquals(LanguageSwitcherPM.Lang.EN.getGermanLangLabel()                     , switcher.getEnglishLangLabel());
    }

    @Test
    void testSetLangLabelToEnglish() {
        //given
        LanguageSwitcherPM switcher = new LanguageSwitcherPM();

        //when
        switcher.setLanguage(LanguageSwitcherPM.Lang.EN);

        //then
        assertEquals(LanguageSwitcherPM.Lang.DE.getEnglishLangLabel()                     , switcher.getGermanLangLabel());
        assertEquals(LanguageSwitcherPM.Lang.EN.getEnglishLangLabel()                     , switcher.getEnglishLangLabel());
    }

    @Test
    void testSetLanguageToGerman() {
        //given
        LanguageSwitcherPM switcher = new LanguageSwitcherPM();

        //when
        switcher.setLanguage(LanguageSwitcherPM.Lang.DE);

        //then
        assertEquals(LanguageSwitcherPM.MultiLanguageText.WINDOW_TITLE.getGermanLabel()                     , switcher.getApplicationTitle());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.APPLICATION_NAME.getGermanLabel()                 , switcher.getApplicationName());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.SEARCH_TEXTFIELD_TEXT.getGermanLabel()            , switcher.getSearchTextfieldText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.NAME_LABEL_TEXT.getGermanLabel()                  , switcher.getNameLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.TYPE_LABEL_TEXT.getGermanLabel()                  , switcher.getTypeLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.SITE_LABEL_TEXT.getGermanLabel()                  , switcher.getSiteLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.CANTON_LABEL_TEXT.getGermanLabel()                , switcher.getCantonLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.MAXWATERVOLUME_LABEL_TEXT.getGermanLabel()        , switcher.getMaxWaterVolumeLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.MAXPOWERMW_LABEL_TEXT.getGermanLabel()            , switcher.getMaxPowerMwLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.STARTOFOPERATIONFIRST_LABEL_TEXT.getGermanLabel() , switcher.getStartOfOperationFirstLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.STARTOFOPERATIONLAST_LABEL_TEXT.getGermanLabel()  , switcher.getStartOfOperationLastLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.LATITUDE_LABEL_TEXT.getGermanLabel()              , switcher.getLatitudeLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.LONGITUDE_LABEL_TEXT.getGermanLabel()             , switcher.getLongitudeLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.STATUS_LABEL_TEXT.getGermanLabel()                , switcher.getStatusLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.WATERBODIES_LABEL_TEXT.getGermanLabel()           , switcher.getWaterbodiesLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.IMAGEURL_LABEL_TEXT.getGermanLabel()              , switcher.getImageUrlLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.CANTON_COLUMN_TEXT.getGermanLabel()               , switcher.getCantonColumnText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.TOTALPOWERMW_COLUMN_TEXT.getGermanLabel()         , switcher.getTotalPowerMwColumnText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.POWERSTATIONCOUNT_COLUMN_TEXT.getGermanLabel()    , switcher.getPowerStationCountColumnText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.INPUT_VALIDATION_MESSAGE.getGermanLabel()         , switcher.getInputValidationText());
    }

    @Test
    void testSetLanguageToEnglish() {
        //given
        LanguageSwitcherPM switcher = new LanguageSwitcherPM();

        //when
        switcher.setLanguage(LanguageSwitcherPM.Lang.EN);

        //then
        assertEquals(LanguageSwitcherPM.MultiLanguageText.WINDOW_TITLE.getEnglishLabel()                     , switcher.getApplicationTitle());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.APPLICATION_NAME.getEnglishLabel()                 , switcher.getApplicationName());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.SEARCH_TEXTFIELD_TEXT.getEnglishLabel()            , switcher.getSearchTextfieldText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.NAME_LABEL_TEXT.getEnglishLabel()                  , switcher.getNameLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.TYPE_LABEL_TEXT.getEnglishLabel()                  , switcher.getTypeLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.SITE_LABEL_TEXT.getEnglishLabel()                  , switcher.getSiteLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.CANTON_LABEL_TEXT.getEnglishLabel()                , switcher.getCantonLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.MAXWATERVOLUME_LABEL_TEXT.getEnglishLabel()        , switcher.getMaxWaterVolumeLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.MAXPOWERMW_LABEL_TEXT.getEnglishLabel()            , switcher.getMaxPowerMwLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.STARTOFOPERATIONFIRST_LABEL_TEXT.getEnglishLabel() , switcher.getStartOfOperationFirstLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.STARTOFOPERATIONLAST_LABEL_TEXT.getEnglishLabel()  , switcher.getStartOfOperationLastLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.LATITUDE_LABEL_TEXT.getEnglishLabel()              , switcher.getLatitudeLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.LONGITUDE_LABEL_TEXT.getEnglishLabel()             , switcher.getLongitudeLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.STATUS_LABEL_TEXT.getEnglishLabel()                , switcher.getStatusLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.WATERBODIES_LABEL_TEXT.getEnglishLabel()           , switcher.getWaterbodiesLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.IMAGEURL_LABEL_TEXT.getEnglishLabel()              , switcher.getImageUrlLabelText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.CANTON_COLUMN_TEXT.getEnglishLabel()               , switcher.getCantonColumnText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.TOTALPOWERMW_COLUMN_TEXT.getEnglishLabel()         , switcher.getTotalPowerMwColumnText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.POWERSTATIONCOUNT_COLUMN_TEXT.getEnglishLabel()    , switcher.getPowerStationCountColumnText());
        assertEquals(LanguageSwitcherPM.MultiLanguageText.INPUT_VALIDATION_MESSAGE.getEnglishLabel()         , switcher.getInputValidationText());
    }

}