package ar.noxit.ehockey.dao;

import ar.noxit.ehockey.context.BaseTransactionalTest;
import ar.noxit.ehockey.dao.impl.JugadorDao;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class JugadoresDaoTest extends BaseTransactionalTest {

    @Autowired
    private JugadorDao jugadorDao;

    @Test
    public void test() {
        ArrayList<Integer> fichasJugadores = new ArrayList<Integer>();
        fichasJugadores.add(1);
        jugadorDao.getJugadoresFromClub(1, fichasJugadores);
    }
}
