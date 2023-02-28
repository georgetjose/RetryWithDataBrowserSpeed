package speedRetry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SpeedFailureRetryTest extends SpeedRetryAnalyzer
{

    WebDriver driver;

    @BeforeMethod
    public void launchBrowser()
    {
        driver = new ChromeDriver();
        if(waitingTime==null)
            setWaitingTime(waitTime.retry_high());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(getWaitingTime()));
    }

    @Test(retryAnalyzer = SpeedRetryAnalyzer.class)
    public void verifyButtonDisplay()
    {
        driver.get("https://leafground.com/waits.xhtml");
        driver.findElement(By.xpath("//h5[contains(text(),'Wait for Visibility')]/following::button[1]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='I am here']/parent::button")).isDisplayed());
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }
}
