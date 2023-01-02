package pageSources;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

/**
* This is the class for the Home page before the login
* The page consists of a set of page objects used for this automation exercise and
* also the actions performed on those objects.
*/
public class PreLoginHomeScreenPage {
    
    WebDriver driver;    
    
    public PreLoginHomeScreenPage(WebDriver driver) {
        this.driver = driver;
    }
    
    static Logger log = Logger.getLogger(PreLoginHomeScreenPage.class);
    /**
    * Returns the page object for the electronics category
    */
    By electronics = By.linkText("TV, Appliances, Electronics");

    /**
    * Returns the page object for the television sub category
    */
    By television = By.cssSelector("a[href*='television']");
   
    /**
    * Returns the page object for the brand name. 
    * @param  name is the brand name
    * @return DOM element
    */
    public By getBrandName(String name)
    {        
        return By.linkText(name);
    }

    /**
    * Returns the page object for the dropdown to sort search results
    * @param  none
    * @return DOM element
    */
    public By getSortResultsDropdown()
    {
        return By.id("s-result-sort-select");
    }
    

    /**
    * This is the method for clicking on the hamburger icon on the home page
    * @param  none
    * @return this prelogin screen page
    */
    public PreLoginHomeScreenPage clickOnHamburgerIcon() {
        WebElement hamburgerIconElement = new WebDriverWait(driver, Duration.ofSeconds(3))
        .until(driver -> driver.findElement(By.id("nav-hamburger-menu"))); 
        // This is the only place that submits the login form and expects the destination to be the home page.
        // A seperate method should be created for the instance of clicking login whilst expecting a login failure. 
        
        hamburgerIconElement.click();
        
        log.info("I am here for hamburger>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");                       
        // Return a new page object representing the destination. Should the login page ever
        // go somewhere else (for example, a legal disclaimer) then changing the method signature
        // for this method will mean that all tests that rely on this behaviour won't compile.
        return new PreLoginHomeScreenPage(driver);	
    }

    /**
    * This is the method for filtering by category
    * @param  none
    * @return this prelogin screen page
    */
    public PreLoginHomeScreenPage selectFilterSectionsByElectronics() {        
        driver.findElement(electronics).click();
        log.info("I am here for electronics>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");                       
        return new PreLoginHomeScreenPage(driver);	
    }

    /**
    * This is the method for filtering by sub category upon selection in the previous step
    * @param  none
    * @return this prelogin screen page
    */
    public PreLoginHomeScreenPage selectFilterSectionsByTelevision() {        
        driver.findElement(television).click();
        log.info("I am here for television >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");                       
        return new PreLoginHomeScreenPage(driver);	
    }

    /**
    * This is the method for filtering the results by brand
    * @param  brand - Brand name can be like Samsung, LG, Sony etc.
    * @return this prelogin screen page
    */
    public PreLoginHomeScreenPage selectFilterSectionsByBrand(String brand) {        
        JavascriptExecutor scriptExecutor = (JavascriptExecutor)driver;        
        scriptExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(getBrandName(brand)));
        log.info("I am here for samsung >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");                       
        driver.findElement(getBrandName(brand)).click();
        return new PreLoginHomeScreenPage(driver);	
    }

    /**
    * This is the method for sorting the results by the desired 
    * @param  sortingOption can be by relevance or price high to low or vice versa or by price.
    * @return this prelogin screen page
    */
    public PreLoginHomeScreenPage sortResultsBy(String sortingOption) {        
        Select se = new Select(driver.findElement(getSortResultsDropdown()));
        se.selectByValue("price-desc-rank");               
        log.info("I am here to sort the results by the sorting option----"+sortingOption);                               
        return new PreLoginHomeScreenPage(driver);	
    }

    /**
    * This is the method for clicking on the hamburger icon on the home page
    * @param  index - The index helps in selecting the item from the search results
    * @return this prelogin screen page
    */
    public PreLoginHomeScreenPage selectResultsItemByIndex(String index){  
        log.info("I am here for selecting results >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");                             
        driver.findElement(By.xpath("//div[@data-index='"+index+"']")).click();        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return new PreLoginHomeScreenPage(driver);
    }

    /**
    * This is the method for switching tabs to get the about details for the selected item.
    * @param  none
    * @return this prelogin screen page
    */
    public PreLoginHomeScreenPage switchTab() {  
        ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTb.get(1));
        log.info("I have switched the tabs >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");                       
        log.info(">>>>>>>>>>>>>>>>>>>"+driver.getTitle());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return new PreLoginHomeScreenPage(driver);
    }

    /**
    * This is the method for getting the about section of the item and displaying it on the console log
    * @param  none
    * @return this prelogin screen page
    */
    public PreLoginHomeScreenPage validateAboutItemForSelectedProduct() {
        WebElement hamburgerIconElement = new WebDriverWait(driver, Duration.ofSeconds(5))
        .until(driver ->  driver.findElement(By.xpath("//div[@id='feature-bullets']")));

        List<WebElement> elements = driver.findElements(By.xpath("//div[@id='feature-bullets']"));        
        if (elements.size() > 0) {
            //Can iterate the list if you expect more than one div of type someDiv.
            String myText = elements.get(0).getText();
            log.info("I am here for getting the about item text >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");                       
            log.info(myText);                                    
            assertEquals(true,myText.contains("About this item"));
        }
        return new PreLoginHomeScreenPage(driver);
    }    
}