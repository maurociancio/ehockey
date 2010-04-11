package ar.noxit.ehockey.context;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "classpath:ehockey_dev.xml")
public class SpringContextTest extends AbstractTestNGSpringContextTests {

    @Test
    public void test() {
    }
}
