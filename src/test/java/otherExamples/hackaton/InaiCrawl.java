package otherExamples.hackaton;
import mx.itesm.testing.otherExamples.hackaton.InaiCrawler;
import mx.itesm.testing.util.drivers.BrowserDriver;
import mx.itesm.testing.util.drivers.BrowserOption;
import mx.itesm.testing.util.drivers.EnhancedWebDriver;
import org.junit.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class InaiCrawl {
    private static EnhancedWebDriver driver;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        driver = new EnhancedWebDriver(BrowserDriver.getDriver(BrowserOption.Chrome));
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        driver.quit();
    }

    @Before
    public void setUp() throws Exception { }

    @After
    public void tearDown() throws Exception { }

    @Test
    public void testQuestion() throws InterruptedException {
        InaiCrawler.SearchTerm(driver,"Morelos");
    }
}
