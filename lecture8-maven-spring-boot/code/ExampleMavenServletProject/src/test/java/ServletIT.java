import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
*
* Adapted from http://www.wunderkraut.com/blog/creating-and-running-a-simple-selenium-webdriver-test/2011-09-15
*/
public class ServletIT {
  private String baseUrl;
  private WebDriver driver;
  private ScreenshotHelper screenshotHelper;
  private String currentTestCase;

  //Before and After runs after each test
  @Before
  public void openBrowser() {
    //baseUrl is the root url that the tester will hit
    baseUrl = "http://localhost:8080/example-maven-servlet-project";
    driver = new ChromeDriver();
    screenshotHelper = new ScreenshotHelper();
  }

  @After
  public void saveScreenshotAndCloseBrowser() throws IOException {
    screenshotHelper.saveScreenshot(currentTestCase+"-screenshot.png");
    driver.quit();
  }

  //A single test
  @Test
  public void listProjects() throws IOException {
	currentTestCase = "testListProjectsPage";
    driver.get(baseUrl+"/ListProjects");

    //Verify that page title is indeed Projects
    assertEquals("Projects", driver.getTitle());

  }
  
  @Test
  public void listProjectstoChangeRating() throws IOException {
	currentTestCase = "testListProjectsPageToRating";
    driver.get(baseUrl+"/ListProjects");
    
    driver.findElement(By.id("ChangeRating-1")).click();

    //Verify that page title is indeed Projects
    assertEquals("Change Rating", driver.getTitle());

  }

  //Opens change project rating form, modifies value, submits form, then confirms the output
  @Test
  public void changeProjectRating() throws IOException {

	  currentTestCase = "testChangeRating";
    driver.get(baseUrl+"/ChangeRating?projectId=1");

    //Get the value input WebElement
    WebElement ratingInput = driver.findElement(By.id("rating-4"));

    //Simulates select all text of the field, then type in 10000
    ratingInput.sendKeys(Keys.chord(Keys.CONTROL, "a"),"10000");

    //From javadocs: If this current element is a form, or an element within a form, then this will be submitted to the remote server.
    ratingInput.submit();

    //Wait for page title to change back to Projects, timeout in 10 seconds
    (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
        public Boolean apply(WebDriver driver) {
            return driver.getTitle().startsWith("Projects");
        }
    });

    assertEquals("Justin Beiber: 10000", driver.findElement(By.id("4-1")).getText());

  }

//Saves screenshot
  private class ScreenshotHelper {

    public void saveScreenshot(String screenshotFileName) throws IOException {
      File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(screenshot, new File("target/"+screenshotFileName));
    }
  }
}
