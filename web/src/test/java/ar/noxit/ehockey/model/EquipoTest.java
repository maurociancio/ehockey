package ar.noxit.ehockey.model;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class EquipoTest {

    @Test
    public void testCreacionEquipo() {
        Club club = new Club("Geba");
        Equipo equipo = new Equipo(club);
        assertEquals(equipo.getClub(), club);
    }

    @Test
    public void testObtenerListaBuenaFe() {
        Club club = new Club("Geba");
        Equipo equipo = new Equipo(club);
        ListaBuenaFe lista = equipo.getListaBuenaFe(); 
        assertNotNull(lista);
        assertEquals(lista, equipo.getListaBuenaFe());
    }
}
