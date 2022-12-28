import amazon.config.EnvFactory;
import amazon.factories.DriverFactory;
import pageSources.PreLoginHomeScreenPage;

import com.typesafe.config.Config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ValidateProductAboutSectionTest {
    private static Config config = EnvFactory.getInstance().getConfig();
    
    private static final String HOME_PAGE_URL = config.getString("HOME_PAGE_URL");
    private WebDriver driver = DriverFactory.getDriver();        
    
    PreLoginHomeScreenPage preLoginPage = new PreLoginHomeScreenPage(driver);

    @BeforeAll   
    public void setup(){
        // Navigate to the amazon home page - India
        System.out.println(config);       
    }
    
    @AfterAll
    public void tearDown(){
        driver.quit();
    }

    @Tag("smokeTest")
    @DisplayName("This test is part of the automaton take home assignment")            
    @Test
    void assertThatAboutSectionOfSelectedItemExists() {
        driver.get(HOME_PAGE_URL); 
        // navigateToHomePage, validate the title  and click on the hamburger icon                      
        assertEquals("Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in", driver.getTitle());        
        
        preLoginPage.clickOnHamburgerIcon();
        
        preLoginPage.selectFilterSectionsByElectronics();

        preLoginPage.selectFilterSectionsByTelevision();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                
        preLoginPage.selectFilterSectionsByBrand("Samsung");

        preLoginPage.selectResultsItemByIndex("2");

        preLoginPage.switchTab();

        preLoginPage.validateAboutItemForSelectedProduct();  
    }
}