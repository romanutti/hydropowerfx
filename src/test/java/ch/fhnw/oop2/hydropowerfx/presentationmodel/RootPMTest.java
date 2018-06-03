package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import ch.fhnw.oop2.hydropowerfx.HydroPowerApp;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//TODO: Disable RUN later for testing!!!

class RootPMTest {
    private RootPM sut;

    @BeforeEach
    void setUp() {
        // given
        sut = new RootPM();

        //when

        //then

       //assertTrue(sut.getAllPowerStations().size() > 0);
        assertEquals(sut.getSelectedId(), 0);

    }

    @Test
    void testSave() {
        //when
        sut.getAllPowerStations().get(0).setName("NNN");
        sut.save();
        RootPM secondPM = new RootPM();

        //then
        assertEquals(sut.getAllPowerStations().size(), secondPM.getAllPowerStations().size());
        assertEquals("NNN", secondPM.getAllPowerStations().get(0).getName());
        for (int i = 0; i < sut.getAllPowerStations().size(); i++) {
            assertEquals(sut.getAllPowerStations().get(i).getName(),
                    secondPM.getAllPowerStations().get(i).getName());
        }

        //after
        sut.getAllPowerStations().get(0).setName("Val Giuf");
        sut.save();
    }

    @Test
    void testGetAllPowerStations() {
        //given
        ObservableList<PowerStationPM> allPowerStations = sut.getAllPowerStations();

        //then
        assertTrue(allPowerStations.size() > 1);
        assertEquals(505110, allPowerStations.get(0).getId());
        assertEquals(301250, allPowerStations.get(allPowerStations.size() - 1).getId());
    }

    @Test
    void getAllCantonss() {
        //given
        ObservableList<CantonPM> allCantons = sut.getAllCantons();

        //then
        assertTrue(allCantons.size() > 1);
        assertEquals(Canton.values().length - 1, allCantons.size());
    }

    @Test
    void getApplicationTitle() {
        //given
        sut.getLanguageSwitcherPM().setLanguage(LanguageSwitcherPM.Lang.DE);
        //when

        //then
        assertEquals("HydroPowerFX Wasserkraftwerke der Schweiz", sut.getLanguageSwitcherPM().getApplicationTitle());
    }


    @Test
    void setApplicationTitle() {
        //given
        String title = "TefalPower";

        //when
        //sut.setApplicationTitle(title);

        //then
        //assertEquals(title, sut.getApplicationTitle());
    }

    @Test
    void getCurrentPowerStationIndex() {
        //given

        //when

        //then
        assertEquals(0, sut.getSelectedId());
    }

    @Test
    void setCurrentPowerStation() {
        //given
        int index = 500200;

        //when
        sut.setSelectedId(index);

        //then
        assertEquals(index, sut.getSelectedId());

        //TODO: Nicht-vorhandene ID setzen
    }

    @Test
    void addPowerStation() {
        //TODO
        //before
        String line[] = new String[]{"999999", "Val Giuf", "L", "Rueras", "BS", "0.43", "1.42", "1979", "1979", "46.4374", "8.75072906", "im Normalbetrieb", "Aua da Tefal", "www.hydro.ch/images"};
        PowerStationPM ps = new PowerStationPM(line);
        int size = sut.getAllPowerStations().size();

        //when
        //sut.addPowerStation(ps);

        //then
        assertTrue(sut.getAllPowerStations().contains(ps));
        assertEquals(size + 1, sut.getAllPowerStations().size());
    }

    @Test
    void removePowerStation() {
        //before
        String line[] = new String[]{"999999", "Val Giuf", "L", "Rueras", "BS", "0.43", "1.42", "1979", "1979", "46.4374", "8.75072906", "im Normalbetrieb", "Aua da Tefal", "www.hydro.ch/images"};
        PowerStationPM ps = new PowerStationPM(line);
        int size = sut.getAllPowerStations().size();
        //sut.addPowerStation(ps);

        //when
        //sut.removePowerStation(ps);

        //then
        assertFalse(sut.getAllPowerStations().contains(ps));
        assertEquals(size, sut.getAllPowerStations().size());
    }

    @Test
    void getCurrentPowerStation() {
        //TODO
        //given
        String line[] = new String[]{
                "999999",
                "Val Giuf",
                "L",
                "Rueras",
                "GR",
                "0.43",
                "1.42",
                "1979",
                "1979",
                "46.67133138",
                "8.75072906",
                "im Normalbetrieb",
                "Aua da Milez",
                "www.hydro.ch/images"};
        PowerStationPM ps = new PowerStationPM(line);
        int index = ps.getId();
        sut.addPowerStation();
        sut.setSelectedId(index);

        //when
        PowerStationPM pt = sut.getPowerStation(sut.getSelectedId());

        //then
        assertEquals(ps.getId(), pt.getId());
    }

    @Test
    void getNewPowerStationID(){
        //given
        int newID = sut.getNewPowerStationID();

        //when

        //then
        assertNull(sut.getPowerStation(newID));
    }


    @Test
    void size() {
        //given
        int size = 669;

        //when

        //then
        assertEquals(sut.size().intValue(), size);
    }

    @Test
    void getTotalPower() {
        //given
        double sumZh = 104.07;
        double sumAg = 482.67;

        //when

        //then
        //assertEquals(sumZh, sut.getTotalPower(Canton.ZH).doubleValue(), 0.01);
        //assertEquals(sumAg, sut.getTotalPower(Canton.AG).doubleValue(), 0.01);
    }

    @Test
    void testGetPowerStationCount() {
        //given
        int countZh = 14;
        int countAg = 29;

        //when

        //then
        assertEquals(countZh, sut.getPowerStationCount(Canton.ZH));
        assertEquals(countAg, sut.getPowerStationCount(Canton.AG));
    }

    @Test
    void getCanton() {
        //before
        sut.setSelectedId(101300);

        //when
        Canton ca = Canton.GR;

        //then
        assertEquals(ca,sut.getPowerStationProxy().getCanton());
    }





    @Test
    void undo() {
        //before

        //when

        //then
    }

    @Test
    void redo() {
        //before

        //when

        //then
    }
}