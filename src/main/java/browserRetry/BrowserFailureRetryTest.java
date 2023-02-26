package browserRetry;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testDataRetry.RetryAnalyzer;

public class BrowserFailureRetryTest
{
    BrowserDriver browserDriver;
    WebDriver driver;
    String firstName = new Faker().name().firstName();
    String lastName;

    @BeforeMethod
    public void launchBrowser()
    {
        browserDriver = (browserDriver!=null) ? BrowserFlow.testBrowser(BrowserTypes.EDGE) : BrowserFlow.testBrowser(BrowserTypes.CHROME);
        lastName = (lastName!=null) ? new Faker().name().lastName() : "";
        driver = browserDriver.launchBrowser();
    }


    @Test(retryAnalyzer = BrowserRetryAnalyzer.class)
    public void createLead()
    {
        driver.get("http://leaftaps.com/opentaps");
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("demosalesmanager");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("crmsfa");
        driver.findElement(By.xpath("//input[@class='decorativeSubmit']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'CRM/SFA')]")).click();
        driver.findElement(By.xpath("//a[text()='Leads']")).click();
        driver.findElement(By.xpath("//a[text()='Create Lead']")).click();
        driver.findElement(By.xpath("//input[@id='createLeadForm_companyName']")).sendKeys("Automation Company");
        driver.findElement(By.xpath("//input[@id='createLeadForm_firstName']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@id='createLeadForm_lastName']")).sendKeys(lastName);
        driver.findElement(By.xpath("//*[@name='submitButton']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='viewLead_firstName_sp']")).getText(),firstName);
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }
}
