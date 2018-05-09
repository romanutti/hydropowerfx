package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import ch.fhnw.oop2.hydropowerfx.domain.PowerStation;
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
        assertEquals(sut.getCurrentPowerStationIndex(), 0);

    }

    @Test
    void testGetAllPowerStations() {
        //given
        ObservableList<PowerStation> allPowerStations = sut.getAllPowerStations();

        //then
        assertTrue(allPowerStations.size() > 1);
        assertEquals(100100, allPowerStations.get(0).getId());
        assertEquals(900200, allPowerStations.get(allPowerStations.size() - 1).getId());
    }


}