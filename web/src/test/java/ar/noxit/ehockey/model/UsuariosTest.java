package ar.noxit.ehockey.model;

import junit.framework.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ar.noxit.ehockey.exception.ErrorDeLoginException;
import ar.noxit.hasher.Hasher;
import ar.noxit.hasher.MD5Hasher;

public class UsuariosTest {

    private Usuario usuarioAdmin1;
    private Usuario usuarioAdmin2;
    private Usuario usuarioRepres1;
    private Usuario usuarioRepres2;
    private Hasher hasher;

    @BeforeMethod
    public void setUp() {
        Club club1 = new Club("Club1");
        Club club2 = new Club("Club2");
        this.hasher = new MD5Hasher();
        this.usuarioAdmin1 = new Administrador("admin1", "passAdmin1", hasher);
        this.usuarioAdmin2 = new Administrador("admin2", "passAdmin2", hasher);
        this.usuarioRepres1 = new Representante("repres1", "passRepres1", hasher, club1);
        this.usuarioRepres2 = new Representante("repres2", "passRepres2", hasher, club2);
    }

    @Test
    public void testCompararUsuarios() {
        Assert.assertEquals(usuarioAdmin1, new Administrador("admin1", "passAdmin1", hasher));
        Assert.assertNotSame(usuarioAdmin1, new Administrador("admin1", "passAdminMal", hasher));
        Assert.assertNotSame(usuarioAdmin1, usuarioAdmin2);
        Assert.assertNotSame(usuarioAdmin1, usuarioRepres1);
        Assert.assertNotSame(usuarioRepres1, usuarioRepres2);
        Assert.assertEquals(usuarioAdmin1, usuarioAdmin1);
        Assert.assertEquals(usuarioRepres2, usuarioRepres2);
    }

    @Test
    public void testLoginBien() throws ErrorDeLoginException {
        usuarioAdmin1.loguearse("passAdmin1", hasher);
        usuarioRepres1.loguearse("passRepres1", hasher);
    }

    @Test(expectedExceptions = ErrorDeLoginException.class)
    public void testLoginMal() throws ErrorDeLoginException {
        usuarioAdmin1.loguearse("mal", hasher);
        usuarioRepres1.loguearse("mal", hasher);
    }

    @Test
    public void testEstadoLogin() throws ErrorDeLoginException {
        Assert.assertFalse(usuarioAdmin1.estaLogueado());
        Assert.assertFalse(usuarioAdmin2.estaLogueado());
        Assert.assertFalse(usuarioRepres1.estaLogueado());
        Assert.assertFalse(usuarioRepres2.estaLogueado());
        usuarioAdmin1.loguearse("passAdmin1", hasher);
        usuarioRepres1.loguearse("passRepres1", hasher);
        try {
            usuarioAdmin2.loguearse("mallogueado", hasher);
            usuarioRepres2.loguearse("mallogueado", hasher);
        } catch (ErrorDeLoginException e) {
            //no hago nada, esta bien que este mal el login
        }
        Assert.assertTrue(usuarioAdmin1.estaLogueado());
        Assert.assertTrue(usuarioRepres1.estaLogueado());
        Assert.assertFalse(usuarioAdmin2.estaLogueado());
        Assert.assertFalse(usuarioRepres2.estaLogueado());

        usuarioAdmin1.desloguearse();
        usuarioAdmin2.desloguearse();
        usuarioRepres1.desloguearse();
        usuarioRepres2.desloguearse();

        Assert.assertFalse(usuarioAdmin1.estaLogueado());
        Assert.assertFalse(usuarioRepres1.estaLogueado());
        Assert.assertFalse(usuarioAdmin2.estaLogueado());
        Assert.assertFalse(usuarioRepres2.estaLogueado());
    }
}
