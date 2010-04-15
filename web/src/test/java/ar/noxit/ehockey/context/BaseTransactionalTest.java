package ar.noxit.ehockey.context;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

@ContextConfiguration(locations = "classpath:ehockey_dev.xml")
public abstract class BaseTransactionalTest extends AbstractTransactionalTestNGSpringContextTests {
}
