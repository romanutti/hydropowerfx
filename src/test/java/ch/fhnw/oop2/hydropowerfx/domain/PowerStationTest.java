package ch.fhnw.oop2.hydropowerfx.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PowerStationTest {
    private PowerStationPM ps;

    @BeforeEach
    void setUp() {
        //before
        String line[] = new String[]{"100100", "Val Giuf", "L", "Rueras", "GR", "0.43", "1.42", "1979", "1979", "46.67133138", "8.75072906", "im Normalbetrieb", "Aua da Milez", "www.hydro.ch/images"};

        //when
        ps = new PowerStationPM(line);

        //then
        Assertions.assertEquals(ps.getId(), 100100);
        Assertions.assertEquals(ps.getName(), "Val Giuf");
        Assertions.assertEquals(ps.getType(), PowerStationPM.Type.L);
        Assertions.assertEquals(ps.getSite(), "Rueras");
        Assertions.assertEquals(ps.getCanton(), Canton.GR);
        Assertions.assertEquals(ps.getMaxWaterVolume(),0.43);
        Assertions.assertEquals(ps.getMaxPowerMw(), 1.42);
        Assertions.assertEquals(ps.getStartOfOperationFirst(), 1979);
        Assertions.assertEquals(ps.getStartOfOperationLast(), 1979);
        Assertions.assertEquals(ps.getLatitude(), 46.67133138);
        Assertions.assertEquals(ps.getLongitude(), 8.75072906);
        Assertions.assertEquals(ps.getStatus(),"im Normalbetrieb");
        Assertions.assertEquals(ps.getWaterbodies(),"Aua da Milez");
        Assertions.assertEquals(ps.getImageUrl(), "www.hydro.ch/images");

    }

    @Test
    void infoAsLine() {
        //before

        //when

        //then
    }
}