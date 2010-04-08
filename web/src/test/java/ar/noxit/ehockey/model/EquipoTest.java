package ar.noxit.ehockey.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Test;

public class EquipoTest {

    @Test
    public void testCreacionEquipo() {
        Club club = new Club("Geba");
        Equipo equipo = club.crearNuevoEquipo("equipo", new Division("division"), new Sector("sector"));
        assertEquals(equipo.getClub(), club);
        assertEquals(equipo.getNombre(), "equipo");
    }

    @Test
    public void testObtenerListaBuenaFe() {
        Club club = new Club("Geba");
        Equipo equipo = club.crearNuevoEquipo("equipo", new Division("division"), new Sector("sector"));
        ListaBuenaFe lista = equipo.getListaBuenaFe();
        assertNotNull(lista);
        assertEquals(lista, equipo.getListaBuenaFe());
    }
}
