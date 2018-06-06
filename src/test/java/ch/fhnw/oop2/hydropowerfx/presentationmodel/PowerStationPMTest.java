package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PowerStationPMTest {

    //TODO Review
    private PowerStationPM ps;

    @Test
    void setUp() {
        //before
        String line[] = new String[]{"100100", "Val Giuf", "L", "Rueras", "GR", "0.43", "1.42", "1979", "1979", "46.67133138", "8.75072906", "im Normalbetrieb", "Aua da Milez", "www.hydro.ch/images"};

        //when
        ps = new PowerStationPM(line);

        int yearFirst = ps.getStartOfOperationFirst();
        int yearLast = ps.getStartOfOperationLast();

        //then
        Assertions.assertEquals(ps.getId(), 100100);
        Assertions.assertEquals(ps.getName(), "Val Giuf");
        Assertions.assertEquals(ps.getType(), PowerStationPM.Type.L);
        Assertions.assertEquals(ps.getSite(), "Rueras");
        Assertions.assertEquals(ps.getCanton(), Canton.GR);
        Assertions.assertEquals(ps.getMaxWaterVolume(),0.43);
        Assertions.assertEquals(ps.getMaxPowerMw(), 1.42);
        Assertions.assertEquals(yearFirst, 1979);
        Assertions.assertEquals(yearLast, 1979);
        Assertions.assertEquals(ps.getLatitude(), 46.67133138);
        Assertions.assertEquals(ps.getLongitude(), 8.75072906);
        Assertions.assertEquals(ps.getStatus(),Status.NORMAL);
        Assertions.assertEquals(ps.getWaterbodies(),"Aua da Milez");
        Assertions.assertEquals(ps.getImageUrl(), "www.hydro.ch/images");

    }


}