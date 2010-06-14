package ar.noxit.ehockey.model;

import ar.noxit.ehockey.service.transfer.EquipoPlano;
import ar.noxit.ehockey.service.transfer.PartidoInfo;
import ar.noxit.ehockey.web.pages.clubes.ClubPlano;
import ar.noxit.ehockey.web.pages.jugadores.DivisionPlano;
import ar.noxit.ehockey.web.pages.jugadores.SectorPlano;
import org.testng.annotations.Test;

public class PlanoTest {

    public PlanoTest() {
    }

    @Test
    public void testClubPlano() {
        ClubPlano clubPlano = new ClubPlano();
        clubPlano.getCiudad();
        clubPlano.getCodigoPostal();
        clubPlano.getDireccion();
        clubPlano.getEmail();
        clubPlano.getId();
        clubPlano.getNombre();
        clubPlano.getNombreCompleto();
        clubPlano.getObservaciones();
        clubPlano.getProvincia();
        clubPlano.getTelefono();
        clubPlano.getWeb();
        clubPlano.setCiudad(null);
        clubPlano.setCodigoPostal(null);
        clubPlano.setDireccion(null);
        clubPlano.setEmail(null);
        clubPlano.setId(null);
        clubPlano.setNombre(null);
        clubPlano.setNombreCompleto(null);
        clubPlano.setObservaciones(null);
        clubPlano.setProvincia(null);
        clubPlano.setTelefono(null);
        clubPlano.setWeb(null);
    }

    @Test
    public void testDivisionPlano() {
        DivisionPlano d = new DivisionPlano();
        d.getDivision();
        d.setDivision(null);
        d.getId();
        d.setId(null);
    }

    @Test
    public void testEquipoPlano() {
        EquipoPlano e = new EquipoPlano();
        e.getClubId();
        e.getDivisionId();
        e.getId();
        e.getNombre();
        e.getSectorId();
        e.setClubId(null);
        e.setDivisionId(null);
        e.setId(null);
        e.setNombre(null);
        e.setSectorId(null);
    }

    @Test
    public void testSectorPlano() {
        SectorPlano s = new SectorPlano();
        s.getId();
        s.getSector();
        s.setId(null);
        s.setSector(null);
    }

    @Test
    public void testPartidoInfo() {
        PartidoInfo p = new PartidoInfo();
        p.getEquipoLocalId();
        p.getEquipoVisitanteId();
        p.getFecha();
        p.getId();
        p.getNumeroFecha();
        p.getPartido();
        p.getRueda();
        p.setEquipoLocalId(null);
        p.setEquipoVisitanteId(null);
        p.setFecha(null);
        p.setId(null);
        p.setNumeroFecha(null);
        p.setPartido(null);
        p.setRueda(null);
    }
}
