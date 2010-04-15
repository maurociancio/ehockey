package ar.noxit.ehockey.persistent;

import ar.noxit.ehockey.context.BaseTransactionalTest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;

public abstract class BaseSessionTest extends BaseTransactionalTest {

    @Autowired
    private SessionFactory sf;
    protected Session session;

    @BeforeMethod
    public void setUp() {
        session = sf.getCurrentSession();
    }
}
