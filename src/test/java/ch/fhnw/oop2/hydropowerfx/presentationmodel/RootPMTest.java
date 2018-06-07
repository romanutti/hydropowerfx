package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

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
        int id = 100100;
        sut.getPowerStation(id).setName("NNN");
        sut.save();
        RootPM secondPM = new RootPM();

        //then
        assertEquals(sut.getAllPowerStations().size(), secondPM.getAllPowerStations().size());
        assertEquals("NNN", secondPM.getPowerStation(id).getName());

        // sort list for comparison, as list internally get sort in a different order
        secondPM.getAllPowerStations().sort(Comparator.comparing(PowerStationPM::getId));
        sut.getAllPowerStations().sort(Comparator.comparing(PowerStationPM::getId));

        for (int i = 1; i < sut.getAllPowerStations().size(); i++) {
                //TODO test fail on different devices due to different sorting in out\data\ressource\HYDRO_POWERSTATION.csv

                assertEquals(sut.getAllPowerStations().get(i).getName(), secondPM.getAllPowerStations().get(i).getName());
        }

        //after
        sut.getPowerStation(id).setName("Val Giuf");
        sut.save();
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
        }

    @Test
    void addPowerStation() {
        //before
        String line[] = new String[]{"999999", "Val Giuf", "L", "Rueras", "BS", "0.43", "1.42", "1979", "1979", "46.4374", "8.75072906", "im Normalbetrieb", "Aua da Tefal", "www.hydro.ch/images"};
        PowerStationPM ps = new PowerStationPM(line);
        int size = sut.getAllPowerStations().size();

        //when
        sut.getAllPowerStations().add(ps);

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
        sut.getAllPowerStations().add(ps);

        //when
        sut.setSelectedId(ps.getId());
        sut.removePowerStation();

        //then
        assertFalse(sut.getAllPowerStations().contains(ps));
        assertEquals(size, sut.getAllPowerStations().size());
    }

    @Test
    void getCurrentPowerStation() {
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
        sut.getAllPowerStations().add(ps);
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
        assertEquals(sumZh, sut.getTotalPower(Canton.ZH), 0.1);
        assertEquals(sumAg, sut.getTotalPower(Canton.AG), 0.1);
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
        sut.setSelectedId(101300);
        String name = sut.getPowerStationProxy().getName();
        sut.getPowerStationProxy().setName("abc");

        //when
        sut.undo();

        //then
        assertEquals(name,sut.getPowerStationProxy().getName());
    }

    @Test
    void redo() {
        //before
        sut.setSelectedId(101300);
        String name = sut.getPowerStationProxy().getName();
        sut.getPowerStationProxy().setName("abc");
        sut.undo();

        //when
        sut.redo();

        //then
        assertEquals(name,sut.getPowerStationProxy().getName());
    }

    @Test
    void testProxy() {
        //given

        //when
        sut.setSelectedId(101300);

        //then
        assertEquals(sut.getPowerStation(101300).getId()  , sut.getPowerStationProxy().getId());
        assertEquals(sut.getPowerStation(101300).getName(), sut.getPowerStationProxy().getName());
        assertEquals(sut.getPowerStation(101300).getMaxWaterVolume(), sut.getPowerStationProxy().getMaxWaterVolume());
    }

    @Test
    void testSummaryFunction() {
        //given
        sut.setSelectedId(101300);
        double maxPowerGR = sut.getTotalPower(Canton.GR);
        double maxPowerSelected = sut.getPowerStationProxy().getMaxPowerMw();
        double maxPowerGRNew = maxPowerGR - maxPowerSelected;

        //when
        sut.getPowerStationProxy().setMaxPowerMw(10);

        //then
        assertEquals((maxPowerGRNew + 10), sut.getTotalPower(Canton.GR));
    }

    @Test
    void testSearch() {
        // given
        String dist1 = "Aarberg";
        String dist2 = "Arbrg";
        String dist3 = "Aarbitk";
        String ignoreCase = "aarburg" ;

        //when
        sut.filterMatches(dist1);
        int size1 = sut.getFilteredPowerStations().size();
        sut.filterMatches(dist2);
        int size2 = sut.getFilteredPowerStations().size();
        sut.filterMatches(dist3);
        int size3 = sut.getFilteredPowerStations().size();
        sut.filterMatches(ignoreCase);
        int size4 = sut.getFilteredPowerStations().size();

        //then
        assertEquals(1, size1);
        assertEquals(1, size2);
        assertEquals(0, size3);
        assertEquals(1, size4);
    }
}