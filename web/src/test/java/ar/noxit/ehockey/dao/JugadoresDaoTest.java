package ar.noxit.ehockey.dao;

import ar.noxit.ehockey.dao.impl.JugadorDao;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "classpath:ehockey_dev.xml")
public class JugadoresDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private JugadorDao jugadorDao;

    @Test
    public void test() {
        ArrayList<Integer> fichasJugadores = new ArrayList<Integer>();
        fichasJugadores.add(1);
        jugadorDao.getJugadoresFromClub(1, fichasJugadores);
    }
}
