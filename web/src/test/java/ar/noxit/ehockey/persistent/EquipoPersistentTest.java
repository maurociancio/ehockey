package ar.noxit.ehockey.persistent;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Sector;
import org.testng.annotations.Test;

public class EquipoPersistentTest extends BaseSessionTest {

    @Test
    public void testPersistentEquipo() {
        Club club = new Club("club");
        Equipo equipo = club.crearNuevoEquipo("nombre", new Division("d"), new Sector("s"));

        session.save(equipo.getClass().getSimpleName(), equipo);
    }
}
