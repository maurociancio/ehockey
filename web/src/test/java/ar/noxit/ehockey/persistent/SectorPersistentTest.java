package ar.noxit.ehockey.persistent;

import ar.noxit.ehockey.context.BaseTransactionalTest;
import ar.noxit.ehockey.model.Sector;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SectorPersistentTest extends BaseTransactionalTest {

    @Autowired
    private SessionFactory sf;
    private Session session;

    @BeforeMethod
    public void setUp() {
        session = sf.getCurrentSession();
    }

    @Test
    public void testPersistSector() {
        Sector s = new Sector("sector 1");
        session.persist(Sector.class.getSimpleName(), s);
    }
}