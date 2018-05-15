package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RootPMTest {
    private RootPM sut;

    @BeforeEach
    void setUp() {
        // given
        sut = new RootPM();

        //when

        //then
        assertTrue(sut.getAllPowerStations().size() > 0);
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
    void getAllPowerStations() {
        //given
        ObservableList<PowerStationPM> allPowerStations = sut.getAllPowerStations();

        //then
        assertTrue(allPowerStations.size() > 1);
        assertEquals(100100, allPowerStations.get(0).getId());
        assertEquals(900200, allPowerStations.get(allPowerStations.size() - 1).getId());
    }

    @Test
    void getAllCantonss() {
        //given
        ObservableList<Canton> allCantons = sut.getAllCantons();

        //then
        assertTrue(allCantons.size() > 1);
        assertEquals(Canton.values().length - 1, allCantons.size());
    }

    @Test
    void getApplicationTitle() {
        //given

        //when

        //then
        assertEquals("HydroPowerFX", sut.getApplicationTitle());
    }


    @Test
    void setApplicationTitle() {
        //given
        String title = "TefalPower";

        //when
        sut.setApplicationTitle(title);

        //then
        assertEquals(title, sut.getApplicationTitle());
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
        //before
        String line[] = new String[]{"999999", "Val Giuf", "L", "Rueras", "BS", "0.43", "1.42", "1979", "1979", "46.4374", "8.75072906", "im Normalbetrieb", "Aua da Tefal", "www.hydro.ch/images"};
        PowerStationPM ps = new PowerStationPM(line);
        int size = sut.getAllPowerStations().size();

        //when
        sut.addPowerStation(ps);

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
        sut.addPowerStation(ps);

        //when
        sut.removePowerStation(ps);

        //then
        assertFalse(sut.getAllPowerStations().contains(ps));
        assertEquals(size, sut.getAllPowerStations().size());
    }

    @Test
    void getCurrentPowerStation() {
        //given
        String line[] = new String[]{"999999", "Val Giuf", "L", "Rueras", "BS", "0.43", "1.42", "1979", "1979", "46.4374", "8.75072906", "im Normalbetrieb", "Aua da Tefal", "www.hydro.ch/images"};
        PowerStationPM ps = new PowerStationPM(line);
        int index = ps.getId();
        sut.addPowerStation(ps);
        sut.setSelectedId(index);

        //when
        PowerStationPM pt = sut.getPowerStation(sut.getSelectedId());

        //then
        assertEquals(ps.getId(), pt.getId());
    }

    @Test
    void size() {
        //given
        int size = 656;

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
        assertEquals(sumZh, sut.getTotalPower(Canton.ZH).doubleValue(), 0.01);
        assertEquals(sumAg, sut.getTotalPower(Canton.AG).doubleValue(), 0.01);
    }

    @Test
    void getPowerStationCount() {
        //given
        int countZh = 14;
        int countAg = 29;

        //when

        //then
        assertEquals(countZh, sut.getPowerStationCount(Canton.ZH).intValue());
        assertEquals(countAg, sut.getPowerStationCount(Canton.AG).intValue());
    }
}