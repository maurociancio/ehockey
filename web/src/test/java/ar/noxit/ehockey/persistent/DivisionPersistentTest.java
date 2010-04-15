package ar.noxit.ehockey.persistent;

import ar.noxit.ehockey.model.Division;
import org.testng.annotations.Test;

public class DivisionPersistentTest extends BaseSessionTest {

    @Test
    public void testPersistDivision() {
        Division d = new Division("division");
        session.persist(d.getClass().getSimpleName(), d);
    }
}