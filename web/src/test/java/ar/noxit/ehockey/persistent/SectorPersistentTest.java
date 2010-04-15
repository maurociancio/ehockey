package ar.noxit.ehockey.persistent;

import ar.noxit.ehockey.model.Sector;
import org.testng.annotations.Test;

public class SectorPersistentTest extends BaseSessionTest {

    @Test
    public void testPersistSector() {
        Sector s = new Sector("sector 1");
        session.persist(Sector.class.getSimpleName(), s);
    }
}