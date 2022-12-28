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

public class PreLoginHomeScreenPage {
    
    WebDriver driver;    
    
    public PreLoginHomeScreenPage(WebDriver driver) {
        this.driver = driver;
    }

    By electronics = By.linkText("TV, Appliances, Electronics");

    By television = By.cssSelector("a[href*='television']");

    public By getBrandName(String name)
    {
        return By.linkText(name);
    }

    public By getSortResultsDropdown()
    {
        return By.id("s-result-sort-select");
    }
    

    // The login page allows the user to submit the login form
    public PreLoginHomeScreenPage clickOnHamburgerIcon() {
        WebElement hamburgerIconElement = new WebDriverWait(driver, Duration.ofSeconds(3))
        .until(driver -> driver.findElement(By.id("nav-hamburger-menu"))); 
        // This is the only place that submits the login form and expects the destination to be the home page.
        // A seperate method should be created for the instance of clicking login whilst expecting a login failure. 
        
        hamburgerIconElement.click();
        System.out.println("I am here for hamburger>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");                       
        // Return a new page object representing the destination. Should the login page ever
        // go somewhere else (for example, a legal disclaimer) then changing the method signature
        // for this method will mean that all tests that rely on this behaviour won't compile.
        return new PreLoginHomeScreenPage(driver);	
    }

    public PreLoginHomeScreenPage selectFilterSectionsByElectronics() {        
        driver.findElement(electronics).click();
        System.out.println("I am here for electronics>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");                       
        return new PreLoginHomeScreenPage(driver);	
    }

    public PreLoginHomeScreenPage selectFilterSectionsByTelevision() {        
        driver.findElement(television).click();
        System.out.println("I am here for television >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");                       
        return new PreLoginHomeScreenPage(driver);	
    }

    public PreLoginHomeScreenPage selectFilterSectionsByBrand(String brand) {        
        JavascriptExecutor scriptExecutor = (JavascriptExecutor)driver;        
        scriptExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(getBrandName(brand)));
        System.out.println("I am here for samsung >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");                       
        driver.findElement(getBrandName(brand)).click();
        return new PreLoginHomeScreenPage(driver);	
    }

    public PreLoginHomeScreenPage sortResultsBy(String sortingOption) {        
        Select se = new Select(driver.findElement(getSortResultsDropdown()));
        se.selectByValue("price-desc-rank");               
        System.out.println("I am here to sort the results by the sorting option----"+sortingOption);                               
        return new PreLoginHomeScreenPage(driver);	
    }

    public PreLoginHomeScreenPage selectResultsItemByIndex(String index){  
        System.out.println("I am here for selecting results >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");                             
        driver.findElement(By.xpath("//div[@data-index='"+index+"']")).click();        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return new PreLoginHomeScreenPage(driver);
    }

    public PreLoginHomeScreenPage switchTab() {  
        ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTb.get(1));
        System.out.println("I have switched the tabs >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");                       
        System.out.println(">>>>>>>>>>>>>>>>>>>"+driver.getTitle());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return new PreLoginHomeScreenPage(driver);
    }

    public PreLoginHomeScreenPage validateAboutItemForSelectedProduct() {  
        List<WebElement> elements = driver.findElements(By.xpath("//div[@id='feature-bullets']"));        
        if (elements.size() > 0) {
            //Can iterate the list if you expect more than one div of type someDiv.
            String myText = elements.get(0).getText();
            System.out.println("I am here for getting the about item text >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");                       
            System.out.println(myText);                                    
            assertEquals(true,myText.contains("About this item"));
        }
        return new PreLoginHomeScreenPage(driver);
    }    
}